/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withLists;

import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.UnsortedMovieList;
import parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
abstract public class LMovieHashTable {
    /**
     *  These read-only variables are used inside this class.
     *  initialTableSize: The initial tableSize 
     */
    protected static final int initialTableSize = 11;
    protected int position;
    protected int nElements;     // The total number of elements inside the hash table
    protected int tableSize;
     protected int oldtableSize; 
    protected MovieList[] oldtable; // The table with lists
    protected MovieList[] table;
    
    /**
     * Initializes the nElements and tableSize variables
     */
    protected LMovieHashTable() {
    	position=-1;
    	nElements=0;
    	tableSize=initialTableSize;
    	table= new MovieList[tableSize];
    }
    

    
    
    /**
     * αυτη ειναι η hash function του hash table. υπολογιζει το δεικτη του table
     * οπου το στοιχειο επρεπε να ειναι.
     *μια καλη hash function θα πρεπει να διανείμει τα στοιχεία ομοιόμορφα στο table
     */
    protected int getHashIndex(int movieId){
    	movieId^=(movieId>>>20)^(movieId>>>12);
		return ((movieId^(movieId>>>7)^(movieId>>>4))%(tableSize));
    }
    
    
    
    /**
     * ! DO NOT IMPLEMENT HERE !
     * αυτη η μεθοδος ειναι list-specific και θα πρεπει να αναλυθει μεσα στις υποκλασεις.
     * οταν το πληθοσ των στοιχειων μεσα σε ενα table ειναι μεγαλυτερο απο 2*tableSize, αυτη η μεθοδοσ τηα πρεπει
     * να καλειται. Δημιουργει ενα καινουριο table με μεγεθος: ενα πωτο αριθμο μεγαλυτερο απο 2*tableSize και
     * να ξανα εισαγει ολα τα στοιχεια στο καινουριο table.
     * ! DO NOT IMPLEMENT HERE !
     */
    abstract protected void rehash();
    
    
    
    /**
     * εισαγει ενα MovieListNode μεσα στο hash table. το καταλλιλο κερι για το hash 
     * table προσδιοριζεται απο τη hash function και το MovieData εισαγεται στη pointing-list(chain)
     * @param data
     */
    public void insert(MovieData data) {
    	position=getHashIndex(data.getId());
    	table[position].insert(data);
    	nElements++;
    	if (nElements > 2*tableSize){rehash();}
    } 
    
    
    /**
     * Removes the first occurrence of the specified element (id) from this table, if it is
     * present (optional operation). If this table does not contain the element, it is unchanged
     * @param id
     * @return the element which have been deleted or null if the element is not present
     */
    public MovieListNode remove(int id) {
    	MovieListNode rem=null;
    	for(int i=0; i<tableSize; i++){
    		rem=table[i].remove(id);
    		if(rem!=null){
    		return rem;
    		}
    	}
    	return null;
    }
    
    public void printt(){
    	for(int i=0; i<tableSize; i++){
    		System.out.println("["+i+"]");
    		MovieListNode search=table[i].getHead();
    		while(search!=null){
    			System.out.println(search.getTitle());
    			search=search.getNext();
    		}
    		System.out.println();
    	}
    }
    
    /**
     * Returns the element with the specified id in the hashTable or null if it not present
     * @param id
     * @return the element
     */
    public MovieListNode get(int id) {
    	for(int i=0; i<tableSize; i++){
    		
    		MovieListNode search=table[i].getHead();
    		while(search!=null){
    			
    			if(search.getId()==id){
    				       return search;
        		}
    			search=search.getNext();
    		}
    	}
    	return null;
    }
    
    
            
    /**
     * Removes all of the elements from this hashTable. The lists in each cell
     * of the table will be empty after this call returns
     */
    public void clear() {
    	for(int i=0; i<tableSize; i++){
    		table[i].clear();
    		nElements=0;
    		
    	}
    }
    
    

    public void setTableSize(){
    	int N=tableSize;
    	for(;;){
    		N++;
    		if(isPrime(N)){tableSize=N; break;}
    	}
    }
    
    
    
    boolean isPrime(int n) {
        for(int i=2;2*i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    
    public int getTableSize() {return tableSize;}
    
    
    
    /**
     * Returns the number of all elements that are inside the hashTable
     * @return nElements
     */
    public int getSize() {return nElements;}
    /**
     * Returns true if this list contains no elements
     * @return true if the number of elements in hashTable equals to 0 and false otherwise
     */
    public boolean isEmpty() {return (nElements==0);}
    
    public boolean movieExists(int movieID){
    	for(int i=0; i<tableSize; i++){
    		MovieListNode search=table[i].getHead();
    		while(search!=null){
    			if(search.getId()==movieID){
    				       return true;
        		}
    			search=search.getNext();
    		}
    	}
    	return false;
    }
    
  public MovieListNode getHead(int i){
	  MovieListNode aa=new MovieListNode();
	  aa=table[i].getHead();
	  return aa;
  }
    
}
