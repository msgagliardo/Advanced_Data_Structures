/**
 * class HashBag: use open-address Hashing.
 * HW-03, CS3
 */
public class HashBag
{
    // instance variables
    private final int M=100;
    private int[] t = new int[M];
    private boolean[] w = new boolean[M]; // true means there used to be an element at t[i]
    private int size;
  
    /**
     * Constructor for class HashBag
     * @param  integer "N" 
     * @return a Hash table with size N
     */
    public HashBag(int N) {
    	size = N;
    }


   /**
     * method hashValue: calculate Hash value of an element key
     * @param  key,   a SSN
     * @return the Hash value = HashFunction(key)
     */ 
    private int hashValue(int key) {
        return key % t.length;
    }

   /**
     * method readData: get/read the element content of a Hash Table
     * @param  "i" is the Hash value (index)
     * @return the content of "i" index or the Data whose Hash value = i
     */     
    public int readData(int i) {
    	return t[i];
    }
    

    /**
     *  method PUT: add a new element "key" to this HashBag
     * @param  "key",   a SSN, value of the new element to be added 
     * @return -1 (if can't add, i.e. full) or index "i" where key is placed (if added)
     */     
    public int put(int key) {
    	if (size == t.length)
    		return -1;
    	
    	int index = hashValue(key);
    	while (t[index] != 0) {
    		index = (index == t.length - 1) ? 0: index + 1;
    	}
    	t[index] = key;
    	w[index] = true;
    	size++;
    	return index;
    	
    }
    
    /**
     *  method hasKey: search for an element key in this HashBag
     * @param  key,   a SSN, value of the element to search for 
     * @return -1 (if not found) or index i where key is placed (if found)
     */     
    public int hasKey(int key) {
    	int count = 0;
    	int i = hashValue(key);
    	
    	while (count < t.length && w[i]) {
    		if (key == t[i])
    			return i;
    		else {
    			i = (i == t.length - 1) ? 0: i + 1;
    			count++;
    		}
    	}
    	return -1;
    }
    
    /**
     *  method remove: delete an element key in this HashBag
     * @param  key,   a SSN, value of the element to be removed
     * @return -1 (if not deleted or not found) or index i where key was deleted (if found)
     */     
    public int remove(int key) {
    	int index = hasKey(key);
    	
    	if (index != -1) {
    		t[index] = 0;
    		size--;
    	}
    	return index;
    }   
}
