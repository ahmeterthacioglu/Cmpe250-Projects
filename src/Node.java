import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


class Node implements Comparable<Node>{
	public String name;
	public Map<Node, Integer> adjacentNodes; 
	boolean flagChecker = false;
	boolean heapChecker = false;
	public int cost;

	public void setCost(int cost) {
	    this.cost = cost;
	}
	
	
	public Node(String name){
	    this.name = name;
	    adjacentNodes = new HashMap<>();
	    cost = Integer.MAX_VALUE;
	}
	public static class Comparator implements java.util.Comparator<Node>{
		public int compare(Node node1,Node node2) {
			if(node1.cost != node2.cost)
				return node1.cost-node2.cost;
			return 1;
				
		}
	}
	public void setFlag() {
		this.flagChecker = true;
	}
	public void setHeapChecker(boolean boolean1) {
		this.heapChecker = boolean1;
	}
	
	public void add(Node neighborNode, int cost){
	    adjacentNodes.put(neighborNode, cost);
	}
	
	
	@Override
	public int compareTo(Node otherNode) {
	    return Integer.compare(this.cost, otherNode.cost);
	}
}