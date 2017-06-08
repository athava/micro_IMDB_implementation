/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withLists;

import parser.MovieData;
import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.UnsortedMovieList;

/**
 *
 * @author Giwrgos Hompis
 */
public class ULMovieHashTable extends LMovieHashTable{

    /**
     * Creates an new hashTable (initializes the table variable) of size="initialTableSize"
     * with empty UnsortedMovieList in each cell.
     */
    public ULMovieHashTable() {
    	for(int i=0; i<tableSize; i++){
    		table[i]=new UnsortedMovieList();
    	}
    }
    
    /**
      *οταν το πληθος των στοιχειων μεσα στο table ειναι μεγαλυτερο απο 2*tableSize, αυτη η μεθοδοσ πρεπει 
     * να καλειται. δημιουργει ενα νεο table απο SortedMovieList με μεγεθος: ενα πρωτο αριθμο
     * μεγαλυτερο απο 2*tableSize και να ξανα εισαγει ολα τα στοιχεια στο νεο table.
     */
    
    @Override
    protected void rehash() {
    	oldtableSize=tableSize;
    	oldtable=table;
    	setTableSize();
    	tableSize=getTableSize();
    	table= new MovieList[tableSize];
    	for(int i=0; i<tableSize; i++){
    		table[i]=new UnsortedMovieList();
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
