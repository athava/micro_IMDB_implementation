/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.movies;



import java.util.Comparator;

import parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
public class SortedMovieList extends MovieList {
    
    /*
     * Comparator<MovieListNode> is an object that implements the int compare(MovieListNode o1, MovieListNode o2) method.
     * This method compares two MovieListNodes and returns:
     *     A positive number when the first MovieListNode is bigger than the second element
     *     Zero when the first MovieListNode is equal to the second element
     *     A negative number when the first MovieListNode is smaller than the second element
     * To compare two MovieListNode objects call "comparator.compare(m1, m2);" method.
     */
    private Comparator<MovieListNode> comparator;
    
    /**
     * Creates an empty sorted list
     * @param comparator2 is an object that implements compare method 
     *        which defines how objects should be compared
     */
    public SortedMovieList(Comparator<MovieListNode> comparator){ this.comparator=comparator;}



	/**
     * Creates a MovieListNode and appends the specified element (MovieListNode) in the sorted list in such
     * way that list should remain sorted. The comparator defines the way 2 nodes should be compared.
     * The values for the MovieListNode are contained inside MovieData
     * @param node
     * @return true if node inserted successfully, false otherwise
     */
    @Override
    public void insert(MovieData data){
    	if(isEmpty()){
    		MovieListNode node = new MovieListNode(data.getId(),data.getTitle(),data.getYear(),data.getRating(),data.getVotes(),data.getDuration(),data.getGenres());
        	tail=node;
			node.setNext(null);
			node.setPrevious(null);
	    	head=node;
	    	size++;
    	}else{add(data);}	
    	
    }
    
    
    private void add(MovieData data) {
    	MovieListNode node = new MovieListNode(data.getId(),data.getTitle(),data.getYear(),data.getRating(),data.getVotes(),data.getDuration(),data.getGenres());
    	MovieListNode a=head;
		for(int i=0; i<size; i++){
			if(comparator.compare(a, node)<0 && a==head ){
    			node.setNext(a);
    			node.setPrevious(null);
    			a.setPrevious(node);
    			head=node;
    			size++;
    			break;
    		}
    		else if(comparator.compare(a, node)<0 && a!=head){
    			node.setPrevious(a.getPrevious());
				MovieListNode aprev=a.getPrevious();
				node.setNext(aprev.getNext());
				a.setPrevious(node);
				aprev.setNext(node);
				size++;
				break;
    		}
    		else if(comparator.compare(a, node)>0 && a==tail){
    			node.setNext(null);
    			node.setPrevious(a);
    			a.setNext(node);
    			tail=node;
    			size++;
    		}
		
    		a=a.getNext();
		}		
	}


    /**
     * Removes the first occurrence of the specified element (id) from this list, if it is
     * present (optional operation). If this list does not contain the element, it is unchanged
     * @param id
     * @return the element which have been deleted or null if the element is not present
     */
    @Override
    public MovieListNode remove(int id){
    	MovieListNode a=head;
    	for(int i=0; i<size; i++){
    		if(a.getId()==id){
    			if(a==head){
    				MovieListNode anext=a.getNext();
    				anext.setPrevious(null);
    				head=anext;
    			}
    			else if(a==tail){
    				MovieListNode aprev=a.getPrevious();
    				aprev.setNext(null);
    				tail=aprev;
    			}else{
    				a.getPrevious().setNext(a.getNext());
    				a.getNext().setPrevious(a.getPrevious());
    			}
    			a.setNext(null);
    			a.setPrevious(null);
    			size--;
    			break;
    		}
    		a=a.getNext();
    	}
    	return a;
    }
    
    
    
    /**
     * Returns the element with the specified id in this list or null if it not present
     * @param id
     * @return the element
     */
    @Override
    public MovieListNode get(int id){
    	MovieListNode search=head.getNext();
    	for(int i=0; i<size; i++){
    		if(search.getId()==id){
    			break;
    		}
    		search=search.getNext();
    	}
    	
    	return search;
    }


    public MovieListNode getHead(){return head;}
    
    public void printList() {
	    MovieListNode currentLink = head;
	    while(currentLink != null) {
		    currentLink.printLink();
		    currentLink = currentLink.getNext();
	    }
    }
    
    public boolean movieExists(int movieID){
    	MovieListNode exists=head;
    	for(int i=0; i<size; i++){
    		if(exists.getId()== movieID){
    			return true;
    		}
    		exists=exists.getNext();
    	}
    	return false;
    }










    
}
