import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<WeightedNode> list = new ArrayList<>();
        list.add(new WeightedNode("A",0));
        list.add(new WeightedNode("B",1));
        list.add(new WeightedNode("C",2));
        list.add(new WeightedNode("D",3));


        WeightedGraph g = new WeightedGraph(list);
        g.addWeighted(0,1,8);
        g.addWeighted(0,3,1);
        g.addWeighted(1,2,1);
        g.addWeighted(2,0,4);
        g.addWeighted(3,1,2);
        g.addWeighted(3,2,9);


        g.dijkstra(list.get(0));
        g.bellManFord(list.get(0));
        g.floydWarshall();
    }
}