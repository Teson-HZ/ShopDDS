package main;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        double[] a = {50, 70};  // 产量
        double[] b = {16, 1, 21, 11, 1};  // 销量
        double[][] c = {
                {8.5, 9.0, 3.5, 4.0, 9.0},
                {12, 8.8, 4.0, 4.5, 8.8}
        };
        double[][] x = TP_vogel(c, a, b);
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
                rowMini2[i] = row[1];
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
                colMini2[j] = col[1];
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
            } else {
                int colIndex = maxPunIndex - m;
                double minVal = Double.MAX_VALUE;
                int minRowIndex = -1;
                for (int i = 0; i < m; i++) {
                    if (c[i][colIndex] < minVal) {
                        minVal = c[i][colIndex];
                        minRowIndex = i;
                    }
                }
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
}
