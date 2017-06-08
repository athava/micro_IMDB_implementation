/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withTrees;

import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.UnsortedMovieList;
import parser.MovieData;
import trees.MovieBSTree;
import trees.MovieBSTreeNode;

/**
 *
 * @author Giwrgos Hompis
 */
public class BSTMovieHashTable {
    
    
    private int nElements;     // The total number of elements inside the hash table
    private int tableSize; 	// The current table size
    private int position;
    private MovieBSTree[] table;    // The table with trees

    
    
    
    /**
     * Constructor: Creates a new movie hash table with binary search trees. The size of the table
     * is given as a parameter
     * @param tableSize
     */
    public BSTMovieHashTable(int tableSize) {
    	nElements=0;
    	position=-1;
    	this.tableSize=tableSize;
    	table= new MovieBSTree[tableSize];
    	for(int i=0; i<tableSize; i++){
    		table[i]=new MovieBSTree();
    	}
    }
    
    
    
    
    /**
     * This is the hash function of the hash table. It calculates the index of the table
     * where the element should be.
     * A good hash function should distribute elements uniformly in the table
     */
    private int getHashIndex(int movieId){
    	movieId^=(movieId>>>20)^(movieId>>>12);
		return ((movieId^(movieId>>>7)^(movieId>>>4))%(tableSize));
    }
    
    
    
    
    /**
     * Inserts a BSTreeNode inside the hash table. The appropriate cell of the hash
     * table is determined by the hash function and the MovieData is inserted in the 
     * corresponding tree
     * @param data
     */
    public void insert(MovieData data) {
    	
    	position=getHashIndex(data.getId());
    	table[position].insert(data);
    	nElements++;
    }
    
   
    public void traverse1(){
    	for(int i=0; i<tableSize; i++){
    		traverse(table[i].getRoot());
    	}
    }
    
    public void traverse(MovieBSTreeNode focusNode) {
    	if (focusNode != null) {
    		traverse(focusNode.getlChild());
    		System.out.println(focusNode.getTitle());
    		traverse(focusNode.getrChild());
    	}
    }
    /**
     * Removes the first occurrence of the specified element (id) from this table, if it is
     * present (optional operation). If this table does not contain the element, it is unchanged
     * @param id
     * @return the element which have been deleted or null if the element is not present
     */
    public MovieBSTreeNode remove(int id) {
    	MovieBSTreeNode temp=null;
    	for(int i=0; i<tableSize; i++){
    		temp=table[i].remove(id);
    		if(temp!=null){
    			nElements--;
    			return temp;
    		}
    	}
    	return null;
    }
    
    /**
     * Returns the element with the specified id in the hashTable or null if it not present
     * @param id
     * @return the element
     * */
    public MovieBSTreeNode get(int id) {
    	MovieBSTreeNode temp=null;
    	for(int i=0; i<tableSize; i++){
    		temp=table[i].get(id);
    		if(temp!=null){
    			return temp;
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
    		if(table[i].getRoot() != null){
    			table[i].clear();
    		}
    		nElements=0;
    	}
    }
    
    
    /**
     * Returns the number of all elements that are inside the hashTable
     * @return nElements
     */
    public int getSize() {return nElements;}
    
    
    /**
     * Returns true if this list contains no elements
     * @return true if the number of elements in hashTable equals to 0 and false otherwise
     */
    public boolean isEmpty() {return(nElements==0);}
    
    public boolean movieExists(int id){
    	MovieBSTreeNode temp=null;
    	for(int i=0; i<tableSize; i++){
    		temp=table[i].get(id);
    		if(temp!=null){
    			return true;
    		}
    	}
    	return false;
    }
    
    public MovieBSTreeNode getRoot(int cell){
    	return table[cell].getRoot();
    }

}
