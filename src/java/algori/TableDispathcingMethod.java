package algori;

import java.util.Arrays;

public class TableDispathcingMethod {
    //运价表
    int[][] cost;

    //存放调运方案
    int[][] initialSolution;

    //检验数表
    int[][] locationOfBasicVar;

    // 检验数
    int[][] reducedCost;

    int[][] optimalSolution;

    public TableDispathcingMethod(int[][] cost, int[] supply, int[] demand) {

        this.cost = new int[supply.length][demand.length];
        for (int i = 0; i < cost.length; i++) {
            this.cost[i] = Arrays.copyOf(cost[i], cost[i].length);
        }

        this.initialSolution = new int[supply.length][demand.length];
        this.reducedCost = new int[supply.length][demand.length];
        for (int[] ints : reducedCost) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        //最小元素法 获得初始基本可行解
        GreedyAlgori greedyAlgori = new GreedyAlgori(cost, supply, demand, initialSolution);
        //基变量位置
        this.locationOfBasicVar = greedyAlgori.basicVar;
        //求检验数
        new ReducedCost(this.cost, reducedCost, locationOfBasicVar);
        //寻找闭合回路
        ImprovedClosedLoop ilp = new ImprovedClosedLoop(reducedCost);
        optimalSolution = ilp.optimize(initialSolution);
        writeAns();
    }

    public void writeAns() {
        System.out.println("\n最优解： ");
        for (int i = 0; i < optimalSolution.length; i++) {
            System.out.println(Arrays.toString(optimalSolution[i]));
        }
    }
}