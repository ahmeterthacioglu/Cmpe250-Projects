import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Graph {
	
	private HashSet<Node> nodeStorage;
    private PriorityQueue<Node> heap;
    private PriorityQueue<Node> heapFlag;



    public Graph(){
        nodeStorage = new HashSet<>();
        heap = new PriorityQueue<>(new Node.Comparator());
        heapFlag = new PriorityQueue<>(new Node.Comparator());
        

    }

    public void addNode(Node newNode){
        nodeStorage.add(newNode);
    }
    
    
    
    public int FlagCheckerLast(Node sourceNode,int flagNumber) {
    	int flagCounter = 0;
    	int distance = 0;
    	sourceNode.setCost(0);
        Set<Node> permanentNodes = new HashSet<>();
        
        
        heap.add(sourceNode);
        sourceNode.setHeapChecker(true);
        
        
        while(flagCounter<flagNumber) {
        	if(heap.isEmpty()) {
        		return -1;
        	}
        	Node closestNode = heap.poll();
        	closestNode.setHeapChecker(false);
        	if (permanentNodes.contains(closestNode)) {             	
                 continue;
            }
        	
        	if(closestNode.flagChecker== true) {
        		permanentNodes.add(closestNode);
           	 	 flagCounter++;
        		 distance += closestNode.cost;
            }
        	
        	for(Map.Entry<Node, Integer> adjacentPair: closestNode.adjacentNodes.entrySet()){
                 Node adjacentNode = adjacentPair.getKey();

                 Integer weight = adjacentPair.getValue();
                 int costOfClosestNode = closestNode.cost;
                 
                 if(closestNode.flagChecker == true)
                	 costOfClosestNode = 0;
                 
                 if (!permanentNodes.contains(adjacentNode) && (costOfClosestNode + weight < adjacentNode.cost)){
                     if(costOfClosestNode + weight < adjacentNode.cost){
                         adjacentNode.setCost(costOfClosestNode + weight);
                         
                         if(adjacentNode.heapChecker == true) {
                        	 heap.remove(adjacentNode);
                        	 adjacentNode.setHeapChecker(false);
                         }
                         heap.add(adjacentNode);
                         adjacentNode.setHeapChecker(true);
                     }      
                 }
             }
        }
        return distance;


    }
    
    
   

    public int dijkstra(Node sourceNode,Node finishingNode){
        sourceNode.setCost(0);
        Set<Node> permanentNodes = new HashSet<>();
        heap.add(sourceNode);
        while (permanentNodes.size() != nodeStorage.size()){
            if (heap.isEmpty()){
                return -1;
            }
            Node closestNode = heap.poll();
            if (permanentNodes.contains(closestNode)) {
                continue;
            }
            permanentNodes.add(closestNode);
            for(Map.Entry<Node, Integer> adjacentPair: closestNode.adjacentNodes.entrySet()){
                Node adjacentNode = adjacentPair.getKey();
                Integer weight = adjacentPair.getValue();
                if (!permanentNodes.contains(adjacentNode)){
                	
                    int costOfClosestNode = closestNode.cost;
                    if(costOfClosestNode + weight < adjacentNode.cost){
                        adjacentNode.setCost(costOfClosestNode + weight);
                    }
                    heap.add(adjacentNode);
                }
            }
        }
        return finishingNode.cost;
    }
}
