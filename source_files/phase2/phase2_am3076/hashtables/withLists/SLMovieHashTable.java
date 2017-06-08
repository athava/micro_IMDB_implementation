/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withLists;

import java.util.Comparator;

import parser.*;
import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.SortedMovieList;
import lists.movies.UnsortedMovieList;

/**
 *
 * @author Giwrgos Hompis
 */
public class SLMovieHashTable extends LMovieHashTable{
    private Comparator<MovieListNode> listComparator;
    
    /**
     * Creates an new hashTable (initializes the table variable) of size="initialTableSize"
     * with empty SortedMovieList in each cell.
     */
    public SLMovieHashTable(Comparator<MovieListNode> comparator){
    	this.listComparator=comparator;
    	for(int i=0; i<tableSize; i++){
    		table[i]=new SortedMovieList(comparator);
    	}
    }

    
    /**
     *οταν το πληθος των στοιχειων μεσα στο table ειναι μεγαλυτερο απο 2*tableSize, αυτη η μεθοδοσ πρεπει 
     * να καλειται. δημιουργει ενα νεο table απο SortedMovieList με μεγεθος: ενα πρωτο αριθμο
     * μεγαλυτερο απο 2*tableSize και αν ξανα εισαγει ολα τα στοιχεια στο νεο table.
     */
    @Override
    protected void rehash() {
    	oldtableSize=tableSize;
    	oldtable=table;
    	setTableSize();
    	tableSize=getTableSize();
    	table= new MovieList[tableSize];
    	for(int i=0; i<tableSize; i++){
    		table[i]=new SortedMovieList(listComparator);
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
    
    public void printList() {
    for(int i=0; i<tableSize; i++){
    	  MovieListNode currentLink = table[i].getHead();
		    while(currentLink != null) {
			    currentLink.printLink();
			    currentLink = currentLink.getNext();
		    }
	    }
    }
    
}



