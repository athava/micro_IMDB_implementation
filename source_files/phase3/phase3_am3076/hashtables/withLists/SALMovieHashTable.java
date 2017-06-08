/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withLists;

import parser.MovieData;
import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.SelfAdjustingMovieList;
import lists.movies.UnsortedMovieList;

/**
 *
 * @author Giwrgos Hompis
 */
public class SALMovieHashTable extends LMovieHashTable{
    
    /**
     * Creates an new hashTable (initializes the table variable) of size="initialTableSize"
     * with empty SelfAdjustingMovieList in each cell.
     */
    public SALMovieHashTable() {
    	for(int i=0; i<tableSize; i++){
    		table[i]=new SelfAdjustingMovieList();
    	}
    }
    
    
    /**
     * When the number of elements in the table is over 2*tableSize, this method should
     * be called. It creates a new table of SelfAdjustingMovieList with size: a prime
     * number over 2*tableSize and re-inserts all elements to the new table.
     */
    @Override
    protected void rehash() {
    	oldtableSize=tableSize;
    	oldtable=table;
    	setTableSize();
    	tableSize=getTableSize();
    	table= new MovieList[tableSize];
    	for(int i=0; i<tableSize; i++){
    		table[i]=new SelfAdjustingMovieList();
    	}
    	for(int i=0; i<oldtableSize; i++){
    		MovieListNode el = oldtable[i].getHead();
    		while(el!=null){
    			MovieData save=new MovieData(el.getId(),el.getTitle(),el.getYear(),el.getRating(),el.getVotes(),el.getDuration(),el.getGenres());
    			int hash=getHashIndex(el.getId());
    			table[hash].insert(save);
    			el=el.getNext();
        		
    		}
    	}
    }
}
