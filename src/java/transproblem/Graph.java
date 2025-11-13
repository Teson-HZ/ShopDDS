package transproblem;


public class Graph {
    private Demand d;
    private Supply s;
    private final int[][] cost;

    public Graph(int[][] cost, int[] supply, int[] demand) {
        d = new Demand(demand);
        s = new Supply(supply);
        this.cost = cost;
    }

    public int[][] getCost() {
        return cost;
    }

    public void run() {

    }
}
