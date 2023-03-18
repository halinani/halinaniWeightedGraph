import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<WeightedNode> list = new ArrayList<>();
        list.add(new WeightedNode("A",0));
        list.add(new WeightedNode("B",1));
        list.add(new WeightedNode("C",2));
        list.add(new WeightedNode("D",3));
        list.add(new WeightedNode("E",4));
        list.add(new WeightedNode("F",5));
        list.add(new WeightedNode("G",6));

        WeightedGraph g = new WeightedGraph(list);
        g.addWeighted(0,1,2);
        g.addWeighted(0,2,5);
        g.addWeighted(1,2,6);
        g.addWeighted(1,3,1);
        g.addWeighted(1,4,3);
        g.addWeighted(2,5,8);
        g.addWeighted(3,4,4);
        g.addWeighted(4,6,9);
        g.addWeighted(5,6,7);


        g.dijkstra(list.get(0));
        g.bellManFord(list.get(0));
    }
}