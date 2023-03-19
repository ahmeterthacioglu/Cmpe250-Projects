import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class project4 {

	public static void main(String [] args) throws FileNotFoundException{
    	PrintStream fileOut = null;
		try {
			fileOut = new PrintStream(args[1]);
			//fileOut = new PrintStream("C:\\Users\\ahmet\\eclipse-workspace\\Graphs\\output.txt");
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		System.setOut(fileOut);
		
		File file = new File(args[0]);
		//File file = new File("C:\\Users\\ahmet\\eclipse-workspace\\Graphs\\input\\stress3.txt");
		
		ArrayList<String> input = new ArrayList<>();
		Scanner data = new Scanner(file);
		
		HashMap<String, Node> totalNodes = new HashMap<>();
        Graph graph = new Graph();


		while(data.hasNextLine()) {
			input.add(data.nextLine());
		}
		int numberOfNodes = Integer.parseInt(input.get(0));
		int numberOfFlags = Integer.parseInt(input.get(1));
		String startingNodeName = input.get(2).split(" ")[0];
		String finishingNodeName = input.get(2).split(" ")[1];
		Node startingNode = new Node(startingNodeName);
		Node finishingNode = new Node(finishingNodeName);
		totalNodes.put(startingNodeName, startingNode);
		totalNodes.put(finishingNodeName, finishingNode);
		

		
		for(int index=4;index<numberOfNodes+4;index++) {
			String[] info = input.get(index).split(" ");
			String nodeName = info[0];
			if(totalNodes.get(nodeName) == null) {
				totalNodes.put(nodeName, new Node(nodeName));
			}
			for(int j=1;j<info.length;j+=2) {
				String relatedNodeName = info[j];
				if(totalNodes.get(relatedNodeName) == null) {
					totalNodes.put(relatedNodeName, new Node(relatedNodeName));
				}
			
					
				int cost = Integer.parseInt(info[j+1]);
				totalNodes.get(nodeName).add(totalNodes.get(relatedNodeName), cost);
				totalNodes.get(relatedNodeName).add(totalNodes.get(nodeName), cost);
			}
			graph.addNode(totalNodes.get(nodeName));
		}
		
		String[] flagInfo = input.get(3).split(" ");
		for(int i = 0;i<flagInfo.length;i++) {
			String flagNodeName = flagInfo[i];
			totalNodes.get(flagNodeName).setFlag();
		} 	
		
		System.out.println(graph.dijkstra(startingNode,finishingNode));
	
		
		for(Map.Entry<String, Node> node: totalNodes.entrySet()) {
			node.getValue().setCost(Integer.MAX_VALUE);
		}
		
		
		System.out.println(graph.FlagCheckerLast(totalNodes.get((flagInfo)[0]), flagInfo.length));
		
		
		
		
    }

}
