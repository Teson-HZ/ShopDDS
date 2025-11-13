package servlet1;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import bean1.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import static util.vogel.TP_vogel;

@WebServlet("/AllocationServlet")
public class AllocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultMessage = processCommodities();
        request.setAttribute("resultMessage", resultMessage);
        request.getRequestDispatcher("HeadadminMainPage.jsp").forward(request, response);
    }
    
    private static final double M = Double.MAX_VALUE; // A large number for masking
    
    private String processCommodities() {
        StringBuilder resultMessage = new StringBuilder();
        List<String> commodityIds = fetchCommodityIds();

        if (commodityIds.isEmpty()) {
            return "没有找到需要处理的商品。";
        }

        for (String commodityId : commodityIds) {
            try {
                System.out.println("正在处理商品 ID: " + commodityId);
                SupplyDemandDetails details = fetchSupplyDemandDetails(commodityId);

                if (details.warehouseIds.length == 0 || details.supermarketIds.length == 0) {
                    resultMessage.append("商品 ID: ").append(commodityId).append(" 无仓库或超市数据。").append("<br>");
                    continue;
                }

                double[][] costs = fetchDistanceMatrix(details.warehouseIds, details.supermarketIds);

                if (Arrays.stream(costs).flatMapToDouble(Arrays::stream).anyMatch(d -> d == Double.MAX_VALUE)) {
                    resultMessage.append("距离数据不完整，无法处理商品 ID: ").append(commodityId).append("<br>");
                    continue;
                }

                double[][] result = TP_vogel(costs, details.supplies, details.demands);
                storeAllocationResults(result, details.warehouseIds, details.supermarketIds, commodityId);

                resultMessage.append("成功处理商品 ID: ").append(commodityId).append("<br>");
            } catch (Exception e) {
                resultMessage.append("处理商品 ID: ").append(commodityId).append(" 时出错 - ").append(e.getMessage()).append("<br>");
            }
        }

        resultMessage.append("所有商品已处理完毕。");
        return resultMessage.toString();
    }


    public static List<String> fetchCommodityIds() {
        String sql = "SELECT DISTINCT commodity_Id FROM assign";
        List<String> ids = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getString("commodity_Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static SupplyDemandDetails fetchSupplyDemandDetails(String commodityId) {
        List<String> warehouseIds = new ArrayList<>();
        List<String> supermarketIds = new ArrayList<>();
        List<Double> supplyList = new ArrayList<>();
        List<Double> demandList = new ArrayList<>();

        // Fetch supplies
        String supplySql = "SELECT warehouse_Id, inv_Amount FROM inv WHERE commodity_Id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(supplySql)) {
            pstmt.setString(1, commodityId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    warehouseIds.add(rs.getString("warehouse_Id"));
                    supplyList.add(rs.getDouble("inv_Amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch demands
        String demandSql = "SELECT supermarket_Id, assign_Amount FROM assign WHERE commodity_Id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(demandSql)) {
            pstmt.setString(1, commodityId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    supermarketIds.add(rs.getString("supermarket_Id"));
                    demandList.add(rs.getDouble("assign_Amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SupplyDemandDetails details = new SupplyDemandDetails();
        details.warehouseIds = warehouseIds.toArray(new String[0]);
        details.supermarketIds = supermarketIds.toArray(new String[0]);
        details.supplies = supplyList.stream().mapToDouble(Double::doubleValue).toArray();
        details.demands = demandList.stream().mapToDouble(Double::doubleValue).toArray();

        return details;
    }

    public static double[][] fetchDistanceMatrix(String[] warehouseIds, String[] supermarketIds) {
        double[][] costs = new double[warehouseIds.length][supermarketIds.length];
        String sql = "SELECT warehouse_Id, supermarket_Id, distance FROM dis WHERE warehouse_Id = ? AND supermarket_Id = ?";

        try (Connection conn = DBUtil.getConnection()) {
            for (int i = 0; i < warehouseIds.length; i++) {
                for (int j = 0; j < supermarketIds.length; j++) {
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, warehouseIds[i]);
                        pstmt.setString(2, supermarketIds[j]);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                costs[i][j] = rs.getDouble("distance");
                            } else {
                                costs[i][j] = Double.MAX_VALUE; // 假设缺失的距离表示无法运输
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return costs;
    }
    
    public static void storeAllocationResults(double[][] result, String[] warehouseIds, String[] supermarketIds, String commodityId) {
        String insertOrUpdateSql = "INSERT INTO allocate (supermarket_Id, warehouse_Id, commodity_Id, allocate_Amount) " +
                                   "VALUES (?, ?, ?, ?) " +
                                   "ON DUPLICATE KEY UPDATE allocate_Amount = VALUES(allocate_Amount)";

        try (Connection conn = DBUtil.getConnection()) {
            for (int i = 0; i < warehouseIds.length; i++) {
                for (int j = 0; j < supermarketIds.length; j++) {
                    try (PreparedStatement pstmt = conn.prepareStatement(insertOrUpdateSql)) {
                        pstmt.setString(1, supermarketIds[j]);
                        pstmt.setString(2, warehouseIds[i]);
                        pstmt.setString(3, commodityId);
                        pstmt.setDouble(4, result[i][j]);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store allocation results for commodity ID: " + commodityId, e);
        }
    }
    
    static class SupplyDemandDetails {
        String[] warehouseIds;
        String[] supermarketIds;
        double[] supplies;
        double[] demands;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
