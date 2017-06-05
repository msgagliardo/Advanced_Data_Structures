import java.io.*;
import java.util.Scanner;


/**********************************************************************
* This class is a homework assignment #03;
**********************************************************************/
// use HashBag.java; 


/**
 * class HashTest which tests HashBag class and works with Hash tables
 * HW-03, CS3
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HashTest {
    public static final int H=100;
    public static final int M=10;
  
    public static void main(String[] args){


        
       //******************************************************************
       // (1) Generate a HASH TABLE randomly:
       //******************************************************************
    	
    	
    	System.out.println("1) Generate a random data set and write to dataS.txt:");
    	System.out.println("=====================================================\n");
    	try (PrintWriter outFile = new PrintWriter("dataS.txt")) {
    		int randSSN;
        	for (int i = 0; i < 10; i++ ) {
        		randSSN = (int) (Math.random()*1000000000);
        		System.out.printf("%09d%n", randSSN);
        		outFile.printf("%09d%n", randSSN);
        	}
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("\n");
            
       //************************************************************       
       // 2)READ SSN from file:
       //************************************************************
    	System.out.println("2) Read data from DataP.txt and put it in a hash table:");
    	System.out.println("=======================================================");
    	System.out.println();
    	HashBag hb = new HashBag(0);
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("DataP.txt"))) {
    		String line;
    		int ssn;
    		while ((line = br.readLine()) != null) {
    			line = line.trim();
    			if (line.length() == 9){
    				ssn = Integer.parseInt(line);
    				hb.put(ssn);
    				System.out.println("Key = " + ssn + " -> Hash value = " + (ssn % 100));
    			}
    		}
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("\n");
        
        //***************************************************************
        // 3) Print the HashTable
        //***************************************************************
    	System.out.println("3) Display the hash table:");
    	System.out.println("==========================");
    	System.out.println();
    	for (int i = 0; i < 100; i++) {
    		System.out.println("t[" + i + "] = " + hb.readData(i));
    	}
    	System.out.println("\n");
        //***************************************************************
        //  4) SEARCH: Let the user ENTER a SSN: A. Search to see if A is in T. 
        //  Print out A and the search result(if A is found or not and index of A if it is found) 
        //***************************************************************
    	System.out.println("4) Search for a key:");
    	System.out.println("====================");
    	System.out.println();
        
    	System.out.print("Enter the value of key (SSN) to search: ");
    	Scanner sc = new Scanner(System.in);
    	int keySearch = sc.nextInt();
    	System.out.println();
    	
    	System.out.println("Key = " + keySearch + " -> Hash value = " + (keySearch % 100));
    	int indexSearch = hb.hasKey(keySearch);
    	if (indexSearch != -1)
    		System.out.println("Search result: " + keySearch + " was found at t[" + indexSearch + "]");
    	else
    		System.out.println("Search result: " + keySearch + " was not found");
    	
    	System.out.println("\n");
        //****************************************************************************
        //   5)	REMOVE: Let the user enter a SSN: B. Remove B if there is B in T. 
        //      Print out B and the remove result(if B is found and removed or not and index where B is removed) 
        //*****************************************************************************        
    	System.out.println("5) Remove a key:");
    	System.out.println("=================");
    	System.out.println();
    	
    	System.out.print("Enter the value of a key (SSN) to remove: ");
    	int keyRemove = sc.nextInt();
    	System.out.println();
    	
    	System.out.println("Key = " + keyRemove + " -> Hash value = " + (keyRemove % 100));
    	int indexRemove = hb.remove(keyRemove);
    	if (indexRemove != -1)
    		System.out.println("Remove result: " + keyRemove + " was removed at t[" + indexRemove + "]");
    	else 
    		System.out.println("Remove result: " + keyRemove + " was not found");
    	
    	System.out.println("\n");
        //****************************************************************************
        //   6) ADD: Let the user enter a SSN: C. Insert/add C to Hash Table/array T.  
        //*****************************************************************************        
    	System.out.println("5) Add a key to the hash table:");
    	System.out.println("===============================");
    	System.out.println();
    	
    	System.out.print("Enter the value of a key (SSN) to insert: ");
    	int keyAdd = sc.nextInt();
    	System.out.println();
    	
    	System.out.println("Key = " + keyAdd + " -> Hash value = " + (keyAdd % 100));
    	int indexAdd = hb.put(keyAdd);
    	if (indexAdd != -1)
    		System.out.println("Insert result: " + keyAdd + " was inserted at t[" + indexAdd + "]");
    	else
    		System.out.println("Insert result: " + keyAdd + " could not be "
    				+ "added because the hash table is full");
    	
    	System.out.println("\n");
        //***************************************************************
        //  7) Print the HashTable
        //***************************************************************
    	System.out.println("6) Display the new hash table:");
    	System.out.println("==============================");
    	System.out.println();
    	
    	for (int i = 0; i < 100; i++) {
    		System.out.println("t[" + i + "] = " + hb.readData(i));
    	}
    	System.out.println("\n");
    	
    	sc.close();
    }
}
