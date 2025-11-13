package main;

import algori.TableDispathcingMethod;
import transproblem.Demand;
import transproblem.Graph;
import transproblem.Supply;

public class Test {
    public static void main(String[] args) {

        //运输单价
        int[][] cost = {{8, 6, 10, 9}, {9, 12, 13, 7}, {14, 12, 16, 5}};
        //产地供应量
        int[] supply = {18, 18, 19};
        //需地需求量
        int[] demand = {16, 15, 7, 17};
        new Graph(cost, supply, demand);
        new TableDispathcingMethod(cost, supply, demand);
    }
}
