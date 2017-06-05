import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Write a description of class GraphBag here.
 */
public class GraphBag
{
    private final int M = 20;
    private int[][] E;  //edge-link, 0 means no connection
    private int[] A; //NodeValue
    private int size;

    /**
     * Constructor for objects of class GraphBag
     */
    public GraphBag(int S)
    {
    	size = S;
    	A = new int[S];
    	E = new int[S][S];
    }

    // Get NodeValue based on index i
    public int ReadNodeValue(int i)
    {
    	return A[i];
    }    

    // Set NodeValue v based on index i    
    public void WriteNodeValue(int i, int v)
    {
    	A[i] = v;
    }
    
    // Get EdgeValue based on indexes i,j
    public int ReadEdgeValue(int i, int j)
    {
    	return E[i][j];
    }    
    
    // Set EdgeValue v based on indexes i,j
    public void WriteEdgeValue(int i, int j, int v)
    {
    	E[i][j] = v;
    }
	public int getSize() {
		return size;
	}

    // Write data about graph to a file     
    public void PrintGraph(String Filename)
    {
    	try (PrintWriter file = new PrintWriter(Filename)) {
    		file.println(A.length);
        	for (int i = 0; i < E.length; i++) {
        		for (int j = 0; j < E[i].length; j++) {
        			file.printf("%3d" + ((j == (E[i].length - 1)) ? "\n": " "), E[i][j]);
        		}
        	}
        	for (int i = 0; i < A.length; i++)
        		file.print(A[i] + ((i == (A.length - 1)) ? "\n": " "));
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    // Display data about graph on the screen     
    public void DisplayGraph()
    {
    	System.out.println(A.length);
    	for (int i = 0; i < E.length; i++) {
    		for (int j = 0; j < E[i].length; j++) {
    			System.out.printf("%3d" + ((j == (E[i].length - 1)) ? "\n": " "), E[i][j]);
    		}
    	}
    	for (int i = 0; i < A.length; i++)
    		System.out.print(A[i] + ((i == (A.length - 1)) ? "\n": " "));
    }
    
    // Graph travesal DFS starting at indes si, search for key
    public int DFS(int si, boolean[] marked, int key)
    {   
    	LinkedStack<Integer> visitedNodes = new LinkedStack<Integer>();
    	visitedNodes.push(Integer.valueOf(si));
    	marked[si] = true;
        int i;
        
        do {
        	if (A[visitedNodes.peek()] == key) {
        		for (int k = 0; k < marked.length; k++)
        			marked[k] = false;
        		// print out found result
        		System.out.println("Found at A[" + visitedNodes.peek() + "]=" + key + "\n");
        		return visitedNodes.peek();
        	}
        	// print out visited node where key wasn't found
        	System.out.println("Visiting at A[" + visitedNodes.peek() + "]=" + A[visitedNodes.peek()] + "\n");
        	for (i = 0; i < E[visitedNodes.peek()].length; i++) {
        		if (E[visitedNodes.peek()][i] > 0 && !marked[i]) {
        			visitedNodes.push(Integer.valueOf(i));
        			marked[i] = true;
        			break;
        		}
        	}
        	if (i == E[visitedNodes.peek()].length)
        		visitedNodes.pop();
        	
        }while (!visitedNodes.isEmpty());
        
        for (int k = 0; k < marked.length; k++)
        	marked[k] = false;
        
		System.out.println("NODE NOT FOUND\n");
        return -1;
    }    
    
    // Graph travesal BFS starting at indes si, search for key    
    public int BFS(int si, int key)
    {
        LinkedQueue<Integer> visitedNodes = new LinkedQueue<Integer>();
        boolean[] marked = new boolean[size];
        
        if (A[si] == key) {
        	// print out found result
        	System.out.println("Found at A[" + si + "]=" + key + "\n");
        	return si;
        }else {
        	visitedNodes.add(Integer.valueOf(si));
        	// print out visited node where key wasn't found
        	System.out.println("Visiting at A[" + si + "]=" + A[si] + "\n");
        	marked[si] = true;
        }
        while (!visitedNodes.isEmpty()) {
        	for (int i = 0; i < E[visitedNodes.peek()].length; i++) 
        		if (E[visitedNodes.peek()][i] > 0 && !marked[i])
        			if (A[i] == key) {
        				// print out found result
        				System.out.println("Found at A[" + i + "]=" + key + "\n");
        				return i;
        			}else {
        				// print out visited node where key wasn't found
        				System.out.println("Visiting at A[" + i + "]=" + A[i] + "\n");
        				visitedNodes.add(Integer.valueOf(i));
        				marked[i] = true;
        			}
        	visitedNodes.remove();
        }
		
		System.out.println("NODE NOT FOUND\n");
        return -1;
    }
         
    // Dijkstra's algorithm to find shortest path from nX to all other nodes
    public int[] Dijskra(int nX)
    {
    	int minIndex = 0;
    	boolean[] settled = new boolean[size];
    	settled[nX] = true;
    	int numSettled = 1;
    	int[] parents = new int[size];
    	parents[nX] = nX;
    	int[] distances = new int[size];
    	distances[nX] = 0;
    	
    	for (int i = 0; i < E[nX].length; i++) {
    		if (!settled[i]) {
    			if (E[nX][i] != 0) {
    				distances[i] = E[nX][i];
    				parents[i] = nX;
    			}
    			else {
    				distances[i] = Integer.MAX_VALUE;
    				parents[i] = i;
    			}
    		}		
    	}
    	while (numSettled < size) {
    		minIndex = findSmallestWeight(settled, distances);
    		settled[minIndex] = true;
    		numSettled++;
    		for (int j = 0; j < E[minIndex].length; j++) {
    			if (!settled[j] && E[minIndex][j] != 0 && (distances[minIndex] + E[minIndex][j] < distances[j])) {
    				distances[j] = distances[minIndex] + E[minIndex][j];
    				parents[j] = minIndex;
    			}
    		}
    	}
    	return parents;
    }
    private int findSmallestWeight(boolean[] settled, int[] dist) {
    	int min = Integer.MAX_VALUE;
    	int minIndex = 0;
		ArrayList<Integer> lessThanMax = new ArrayList<Integer>();
		
    	for (int i = 0; i < dist.length; i++) {
			if (dist[i] < min) {
				lessThanMax.add(i);
			}
		}
		if (lessThanMax.size() == 1)
			return lessThanMax.get(0);
		
    	for (int i = 0; i < dist.length; i++) {
    		if (!settled[i] && dist[i] < min) {
    			min = dist[i];
    			minIndex = i;
    		}
    	}
    	return minIndex;
    }
    
    // Shortest Path algorithm to find the shortest path from nX to nY
    public int[] ShortestPath(int nX, int nY)
    {
    	int[] dijkResult = Dijskra(nX);
    	int[] tempAnswer = new int[size];
    	int[] answer;
    	int i = 0;
    	int node;
    	
    	node = nY;
		tempAnswer[i] = node;
		i++;
		while (dijkResult[node] != node) {
			node = dijkResult[node];
			tempAnswer[i] = node;
			i++;
		}
		answer = new int[i];
		for (int j = 0; j < answer.length; j++)
			answer[j] = tempAnswer[j];
		
		return answer;
    }
    
    // find ALL Shortest Path algorithm in the given graph
    public void AllPairSP(String filename)
    {
    	try (PrintWriter pw = new PrintWriter(filename)) {
    		pw.println(size);
    		for (int i = 0; i < size; i++) {
    			for (int j = 0; j < size; j++) {
    				PrintOut(pw, i, j);
    			}
    		}
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    // print the path from index p to index q
    private void PrintOut(PrintWriter pw, int p, int q)
    {
    	int[] result = ShortestPath(p, q);
    	
    	for (int i = result.length  - 1; i >= 0; i--) {
			if (result.length == 1)
				pw.append(-1 + "\n");
			else
				pw.append(result[i] + ((i == 0) ? "\n": ", "));
    	}
    }
   
   // Find the shortest paths for ALL pairs of nodes 
   public int[] SPfromAllPairResult(String filename,int ix,int iy) throws IOException
   {
	   BufferedReader br = new BufferedReader(new FileReader(filename));
	   
	   int index1 = BFS(0, ix);
	   int index2 = BFS(0, iy);
	   int lineNumber = size * index1 + (index2 + 2);
	   String resultLine;
	   int[] tempResult = new int[size];
	   int[] result;
	   int j;
	   
	   System.out.println("\nP is at A[" + index1 + "] and Q is at A[" + index2 + "]\n");
	   
	   for(int i = 1; i < lineNumber; i++)
		   br.readLine();
	   
	   resultLine = br.readLine();
	   br.close();
	   Scanner sc = new Scanner(resultLine);
	   sc.useDelimiter(", ");
	   
	   for (j = 0; sc.hasNextInt(); j++)
		   tempResult[j] = sc.nextInt();
	   
	   sc.close();
	   
	   result = new int[j];
	   for (int k = 0; k < result.length; k++)
		   result[k] = tempResult[k];
	   
	   return result;
   }   
}
