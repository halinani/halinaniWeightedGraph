import java.util.ArrayList;
import java.util.PriorityQueue;

public class WeightedGraph {
    ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

    public WeightedGraph(ArrayList<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }

//Dijkstra algorithm
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

//    Floyd Warshall
    public void floydWarshall(){
        int size = nodeList.size();
        int[][] V = new int[size][size];

        // Initializing Distance table from adjacency list
        for (int i = 0; i < size; i++) {
            WeightedNode first = nodeList.get(i);
            for (int j = 0; j < size; j++) {
                WeightedNode second = nodeList.get(j);
                //we have direct edge between i & j
                //no direct edge between i & j, so mark it as infinity [divided by 10 to avoid arithmetic overflow]
                if (i == j)
                    V[i][j] = 0;
                else V[i][j] = first.weightMap.getOrDefault(second, Integer.MAX_VALUE / 10);
            }
        }//end of loop

        for (int k = 0; k < nodeList.size(); k++) {
            for (int i = 0; i < nodeList.size(); i++) {
                for (int j = 0; j < nodeList.size(); j++) {
                    if (V[i][j] > V[i][k] + V[k][j]) {  // if update possible, then update path
                        V[i][j] = V[i][k] + V[k][j];    // this will keep a track on path
                    }
                }
            }
        }//end of loop

        for(int i = 0;i < size;i++){
            System.out.print("List for row "+nodeList.get(i)+" ");
            for(int j = 0;j < size;j++){
                int num = V[i][j];
                System.out.print( num+" ");
            }
            System.out.println();
        }
    }
    




}
