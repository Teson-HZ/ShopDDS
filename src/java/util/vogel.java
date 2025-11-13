package util;
import main.*;
import bean1.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class vogel {
    
    public static void main(String[] args) {

        List<String> commodityIds = fetchCommodityIds();

        for (String commodityId : commodityIds) {
            System.out.println("Processing commodity ID: " + commodityId);
            SupplyDemandDetails details = fetchSupplyDemandDetails(commodityId);
            double[][] costs = fetchDistanceMatrix(details.warehouseIds, details.supermarketIds);

            // 在这里调用运输问题的解算函数，传递costs, supplies, demands
            double[][] result = TP_vogel(costs, details.supplies, details.demands); 
            // 存储调拨结果到数据库
            storeAllocationResults(result, details.warehouseIds, details.supermarketIds, commodityId);
            // 输出或处理result的代码
            printMatrix(result); 
            System.out.println("Completed processing for commodity ID: " + commodityId);
        }
        System.out.println("All commodities have been processed.");
    }
    
    public static double[][] TP_vogel(double[][] c, double[] a, double[] b) {
    int m = c.length;
    int n = c[0].length;
    double[][] cost = deepCopyMatrix(c);
    double[][] x = new double[m][n];
    double M = 1e9;

    while (true) {
        if (allElementsEqual(c, M)) break;

        double[] rowMini1 = new double[m];
        double[] rowMini2 = new double[m];
        for (int i = 0; i < m; i++) {
            double[] row = c[i].clone();
            Arrays.sort(row);
            rowMini1[i] = row[0];
            rowMini2[i] = (row.length > 1) ? row[1] : row[0];  // Ensure no out of bounds
        }
        double[] rPun = new double[m];
        for (int i = 0; i < m; i++) {
            rPun[i] = rowMini2[i] - rowMini1[i];
        }

        double[] colMini1 = new double[n];
        double[] colMini2 = new double[n];
        for (int j = 0; j < n; j++) {
            double[] col = new double[m];
            for (int i = 0; i < m; i++) {
                col[i] = c[i][j];
            }
            Arrays.sort(col);
            colMini1[j] = col[0];
            colMini2[j] = (col.length > 1) ? col[1] : col[0];  // Ensure no out of bounds
        }
        double[] cPun = new double[n];
        for (int j = 0; j < n; j++) {
            cPun[j] = colMini2[j] - colMini1[j];
        }

        double[] pun = new double[m + n];
        System.arraycopy(rPun, 0, pun, 0, m);
        System.arraycopy(cPun, 0, pun, m, n);
        double maxPun = Arrays.stream(pun).max().getAsDouble();
        int maxPunIndex = 0;
        for (int i = 0; i < pun.length; i++) {
            if (pun[i] == maxPun) {
                maxPunIndex = i;
                break;
            }
        }

        if (maxPunIndex < m) {
            int rowIndex = maxPunIndex;
            double minVal = Double.MAX_VALUE;
            int minColIndex = -1;
            for (int j = 0; j < n; j++) {
                if (c[rowIndex][j] < minVal) {
                    minVal = c[rowIndex][j];
                    minColIndex = j;
                }
            }
            if (minColIndex != -1) {  // Ensure valid index
                if (a[rowIndex] <= b[minColIndex]) {
                    x[rowIndex][minColIndex] = a[rowIndex];
                    a[rowIndex] = 0;
                    b[minColIndex] -= x[rowIndex][minColIndex];
                    Arrays.fill(c[rowIndex], M);
                } else {
                    x[rowIndex][minColIndex] = b[minColIndex];
                    b[minColIndex] = 0;
                    a[rowIndex] -= x[rowIndex][minColIndex];
                    for (int i = 0; i < m; i++) {
                        c[i][minColIndex] = M;
                    }
                }
            }
        } else {
            int colIndex = maxPunIndex - m;
            if (colIndex < n) {  // Ensure colIndex is within bounds
                double minVal = Double.MAX_VALUE;
                int minRowIndex = -1;
                for (int i = 0; i < m; i++) {
                    if (c[i][colIndex] < minVal) {
                        minVal = c[i][colIndex];
                        minRowIndex = i;
                    }
                }
                if (minRowIndex != -1) {  // Ensure valid index
                    if (a[minRowIndex] <= b[colIndex]) {
                        x[minRowIndex][colIndex] = a[minRowIndex];
                        a[minRowIndex] = 0;
                        b[colIndex] -= x[minRowIndex][colIndex];
                        Arrays.fill(c[minRowIndex], M);
                    } else {
                        x[minRowIndex][colIndex] = b[colIndex];
                        b[colIndex] = 0;
                        a[minRowIndex] -= x[minRowIndex][colIndex];
                        for (int i = 0; i < m; i++) {
                            c[i][colIndex] = M;
                        }
                    }
                }
            }
        }
    }

    double totalCost = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            totalCost += x[i][j] * cost[i][j];
        }
    }

    System.out.println(">>>初始基本可行解x*：");
    for (double[] row : x) {
        System.out.println(Arrays.toString(row));
    }
    System.out.println(">>>当前总成本：" + totalCost);

    int varnum = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (x[i][j] != 0) {
                varnum++;
            }
        }
    }
    if (varnum != m + n - 1) {
        System.out.println("【注意：问题含有退化解】");
    }

    return x;
}

    public static boolean allElementsEqual(double[][] matrix, double value) {
        for (double[] row : matrix) {
            for (double elem : row) {
                if (elem != value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static double[][] deepCopyMatrix(double[][] original) {
        if (original == null) {
            return null;
        }

        final double[][] result = new double[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
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
}