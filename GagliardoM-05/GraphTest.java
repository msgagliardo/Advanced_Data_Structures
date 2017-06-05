import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphTest
{
	public static GraphBag createGraph(int size, int highNode, int lowNode,
												 int highEdge, int lowEdge) {
		
		int nodeValue, edgeValue;
		boolean isNodeRepeated;
		GraphBag graph = new GraphBag(size);
		
		for (int i = 0; i < size; i++) {
			do {
				isNodeRepeated = false;
				nodeValue = (int)(Math.random() * (highNode - lowNode + 1)) + lowNode;
				for (int k = 0; k < i; k++) 
					if (graph.ReadNodeValue(k) == nodeValue) { 
						isNodeRepeated = true;
						break;
					}
			}while (isNodeRepeated);
			graph.WriteNodeValue(i, nodeValue);
		}
		for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++) {
				if (i == j) {
					edgeValue = 0;
					graph.WriteEdgeValue(i, j, edgeValue);
				}else {
					edgeValue = (int)(Math.random() * (highEdge - lowEdge + 1)) + lowEdge;
					graph.WriteEdgeValue(i, j, edgeValue);
				}
			}
		return graph;
	}	
	public static GraphBag createGraphFromFile(String fileName) {
		
		int size;
		int weight;
		int nodeValue;
		GraphBag graph = null;
		
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
			size = sc.nextInt();
			graph = new GraphBag(size);
			// display size
			System.out.println("N=" + size + "\n");
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// display edge values
					weight = sc.nextInt();
					System.out.println("E[" + i + "][" + j + "]=" + weight + "\n");
					graph.WriteEdgeValue(i, j, weight);
				}
			}
			for (int k = 0; k < size; k++) {
				// display node values
				nodeValue = sc.nextInt();
				System.out.println("A[" + k + "]=" + nodeValue);
				graph.WriteNodeValue(k, nodeValue);
			}
				
		}catch (InputMismatchException e) {
			System.err.println("The graph in the file is not in the proper format");
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return graph;
	}
	public static void printShortestPaths(int[] dijkResult) {
		int index;
		
		for (int i = 0; i < dijkResult.length; i++) {
			index = i;
			System.out.print(index);
			while (dijkResult[index] != index) {
				index = dijkResult[index];
				System.out.print(", " + index);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		System.out.println("*****************************************\n");
		System.out.println("Step 1: Generating: (see GraphA.txt)\n");
		System.out.println("*****************************************\n");
		
		GraphBag graphA = createGraph(8, 100, 1, 100, 0);
		graphA.PrintGraph("graphA.txt");
		graphA.DisplayGraph();
		
		System.out.println();
		System.out.println("*****************************************\n");
		System.out.println("Step 2: Input GRAPH from FILE (graphB.txt):\n");
		System.out.println("*****************************************\n");
		
		GraphBag graphB = createGraphFromFile("graphB.txt");
		
		System.out.println("*****************************************\n");
		System.out.println("Step 3: Search Using DFS:\n");
		System.out.println("*****************************************\n");
		System.out.println("Enter the Value of a node X(integer) to SEARCH:\n");
		
		Scanner sc = new Scanner(System.in);
		boolean[] marked = new boolean[8];
		int dfsSearchValue = sc.nextInt();
		graphB.DFS(0, marked, dfsSearchValue);
		
		
		System.out.println("*****************************************\n");
		System.out.println("Step 4: Search Using BFS:\n");
		System.out.println("*****************************************\n");
		System.out.println("Enter the Value of a node Y(integer) to SEARCH:\n");
		
		int bfsSearchValue = sc.nextInt();
		graphB.BFS(0, bfsSearchValue);
		
		System.out.println("*****************************************\n");
		System.out.println("Step 5: Find Shortest Path from X to Y (REAL-TIME)using Dijskra:\n");
		System.out.println("*****************************************\n");
		
		System.out.println("Shortest Path from A[5] to each other node(using index as node ID):");
		printShortestPaths(graphB.Dijskra(5));
		System.out.println();
		System.out.println("Shortest Path from A[6] to each other node(using index as node ID):");
		printShortestPaths(graphB.Dijskra(6));
		
		System.out.println("*****************************************\n");
		System.out.println("Step 6: Find ALL Shortest Path for Graph B: write results to file (graphB-allSP.txt)\n");
		System.out.println("*****************************************\n");
		
		System.out.println("WRITING shortest paths to file graphB-allSP.txt ... BUT :\n");
		int[] dkResult = new int[graphB.getSize()];
		for (int i = 0; i < graphB.getSize(); i++) {
		    dkResult = graphB.Dijskra(i);
			for (int j = 0; j < dkResult.length; j++) {
				if (dkResult[j] == j)
					System.out.println("No path from A[" + i + "] to A[" + j + "]\n");
			}
		}
		graphB.AllPairSP("graphB-allSP.txt");
		
		System.out.println("*****************************************\n");
		System.out.println("Step 7: Find Shortest Path from P to Q (OFF-LINE) using recorded results in graphB-allSP.txt\n");
		System.out.println("*****************************************\n");
		System.out.println();
		
		System.out.println("Enter the Value of a node P(integer):\n");
		int nodeP = sc.nextInt();
		System.out.println("Enter the Value of a node Q(integer):\n");
		int nodeQ = sc.nextInt();
		
		try {
			int[] offlineResult = graphB.SPfromAllPairResult("graphB-allSP.txt", nodeP, nodeQ);
			System.out.printf("Shortest path from " + nodeP + " to " + nodeQ + " is ");
			for (int result: offlineResult)
				System.out.print(result + " ");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
