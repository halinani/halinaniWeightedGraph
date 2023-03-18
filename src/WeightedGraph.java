import java.util.ArrayList;
import java.util.PriorityQueue;

public class WeightedGraph {
    ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

    public WeightedGraph(ArrayList<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }


    public void dijkstra(WeightedNode node){
        PriorityQueue<WeightedNode> q = new PriorityQueue<>();
        node.distance = 0;
        q.addAll(nodeList);
        while(!q.isEmpty()){
         WeightedNode currNode = q.remove();
         for(WeightedNode neighbor : currNode.neighnors){
             if(q.contains(neighbor)){
                 if(neighbor.distance > currNode.distance + currNode.weightMap.get(neighbor)){
                    neighbor.distance = currNode.distance + currNode.weightMap.get(neighbor);
                    neighbor.parent = currNode;
                    q.remove(neighbor);
                    q.add(neighbor);
                 }
             }
         }
        }

        for(WeightedNode nodeToCheck : nodeList){
            System.out.print("Node " +nodeToCheck+", distance: "+nodeToCheck.distance+", Path: ");
            printPath(nodeToCheck);
            System.out.println();
        }
    }

    public void addWeighted(int i, int j, int d){
        WeightedNode first = nodeList.get(i);
        WeightedNode second = nodeList.get(j);
        first.neighnors.add(second);
        first.weightMap.put(second,d);
    }



    public void printPath(WeightedNode node){
        if(node.parent != null){
            printPath(node.parent);
        }
        System.out.print(node.name+" ");
    }

//    Bellman ford algorithm

    public void bellManFord(WeightedNode node){
        node.distance = 0;
        for(int i = 0;i < nodeList.size();i++){
            for(WeightedNode currentNode : nodeList){
                for(WeightedNode neighbor : currentNode.neighnors){
                    if(neighbor.distance > (currentNode.distance +  currentNode.weightMap.get(neighbor))){
                        neighbor.distance = currentNode.distance + currentNode.weightMap.get(neighbor);
                        neighbor.parent = currentNode;
                    }
                }
            }
        }

        System.out.println("Checkong for negative cycles");
        for(WeightedNode nodeCheck : nodeList){
            for (WeightedNode neighbor : nodeCheck.neighnors){
                if(neighbor.distance > nodeCheck.distance + nodeCheck.weightMap.get(neighbor)){
                    System.out.println("Negative cycle found: \n");
                    System.out.println("Vertex name: " + neighbor.name);
                    System.out.println("Old cost: " + neighbor.distance);
                    int newWeight = nodeCheck.distance + nodeCheck.weightMap.get(neighbor);
                    System.out.println("the new cost is: "+ newWeight);
                    return;
                }
            }
        }
        System.out.println("No negative cycles found");
        System.out.println();
        System.out.println("cost from source "+node.name);

//        print all the node with their names, distance..
        for(WeightedNode checkNode : nodeList){
            System.out.print("name "+checkNode.name + " with distance "+ checkNode.distance+" ");
            printPath(checkNode);
            System.out.println();
        }

    }




}
