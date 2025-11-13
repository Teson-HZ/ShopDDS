package servlet1;

import bean1.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DistributeitemServlet")
public class DistributeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();

            // Fetch total inventory
            Map<String, Integer> totalInventory = new HashMap<>();
            String sqlAllInv = "SELECT commodity_Id, allinv_Amount FROM allinv";
            ps = connection.prepareStatement(sqlAllInv);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalInventory.put(rs.getString("commodity_Id"), rs.getInt("allinv_Amount"));
            }
            DBUtil.close(rs, ps, null);

            // Fetch total application
            Map<String, Integer> totalApplication = new HashMap<>();
            String sqlAllApl = "SELECT commodity_Id, allapl_Amount FROM allapl";
            ps = connection.prepareStatement(sqlAllApl);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalApplication.put(rs.getString("commodity_Id"), rs.getInt("allapl_Amount"));
            }
            DBUtil.close(rs, ps, null);

            // Fetch individual supermarket applications
            Map<String, Map<String, Integer>> supermarketApplications = new HashMap<>();
            String sqlApl = "SELECT supermarket_Id, commodity_Id, apl_Amount FROM apl";
            ps = connection.prepareStatement(sqlApl);
            rs = ps.executeQuery();
            while (rs.next()) {
                String supermarketId = rs.getString("supermarket_Id");
                String commodityId = rs.getString("commodity_Id");
                int aplAmount = rs.getInt("apl_Amount");
                supermarketApplications.computeIfAbsent(supermarketId, k -> new HashMap<>())
                        .put(commodityId, aplAmount);
            }
            DBUtil.close(rs, ps, null);

            // Calculate and insert distribution
            String sqlInsertAssign = "INSERT INTO assign (supermarket_Id, commodity_Id, assign_Amount) VALUES (?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE assign_Amount = VALUES(assign_Amount)";
            ps = connection.prepareStatement(sqlInsertAssign);

            for (String commodityId : totalApplication.keySet()) {
                int totalApl = totalApplication.getOrDefault(commodityId, 0);
                int totalInv = totalInventory.getOrDefault(commodityId, 0);

                if (totalApl == 0) continue; // Avoid division by zero
                if (totalInv >= totalApl) {
                    // 当供给大于等于需求时，直接分配
                    for (String supermarketId : supermarketApplications.keySet()) {
                        int aplAmount = supermarketApplications.get(supermarketId).getOrDefault(commodityId, 0);

                        // 只在需求不为0时才进行分配
                        if (aplAmount > 0) {
                            ps.setString(1, supermarketId);
                            ps.setString(2, commodityId);
                            ps.setInt(3, aplAmount);
                            ps.addBatch();
                        }
                    }
                }else {
                    // 当供给小于需求时，使用等比例缩减分配
                    double scaleFactor = (double) totalInv / totalApl;
                    int totalAssigned = 0;
                    Map<String, Integer> assignAmounts = new HashMap<>();
                    List<Map.Entry<String, Double>> remainders = new ArrayList<>();

                    for (String supermarketId : supermarketApplications.keySet()) {
                        int aplAmount = supermarketApplications.get(supermarketId).getOrDefault(commodityId, 0);
                        double exactAmount = aplAmount * scaleFactor;
                        int assignAmount = (int) Math.floor(exactAmount);

                        // Track how much we assign and the remainder for later adjustment
                        totalAssigned += assignAmount;
                        remainders.add(new java.util.AbstractMap.SimpleEntry<>(supermarketId, exactAmount - assignAmount));
                        assignAmounts.put(supermarketId, assignAmount);

                        ps.setString(1, supermarketId);
                        ps.setString(2, commodityId);
                        ps.setInt(3, assignAmount);
                        ps.addBatch();
                    }

                    // Distribute the remaining inventory based on the largest remainders
                    int remainingInventory = totalInv - totalAssigned;
                    remainders.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // Sort by remainder descending

                    // Ensure we do not exceed the size of the remainders list
                    int distributeCount = Math.min(remainingInventory, remainders.size());
                    for (int i = 0; i < distributeCount; i++) {
                        String supermarketId = remainders.get(i).getKey();
                        int currentAmount = assignAmounts.get(supermarketId);
                        assignAmounts.put(supermarketId, currentAmount + 1);

                        ps.setString(1, supermarketId);
                        ps.setString(2, commodityId);
                        ps.setInt(3, currentAmount + 1);
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
            request.setAttribute("addemployee", "物资分配已完成");
            request.getRequestDispatcher("HeadadminMainPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("addemployee", "分配失败");
            request.getRequestDispatcher("HeadadminMainPage.jsp").forward(request, response);
        } finally {
            DBUtil.close(null, ps, connection);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
