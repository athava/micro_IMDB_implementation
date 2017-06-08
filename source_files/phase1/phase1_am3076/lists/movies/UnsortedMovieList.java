/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.movies;


import parser.MovieData;




/**
 *
 * @author Giwrgos Hompis
 */
public class UnsortedMovieList extends MovieList {
	
    /**
     * Creates an empty list
     */
    public UnsortedMovieList(){super();}

    
    
    /**
     * Creates a MovieListNode and appends the specified element (MovieListNode) in the list.
     * The values for the MovieListNode are contained inside MovieData
     * @param node
     * @return true if node inserted successfully, false otherwise
     */
    
    
    public void insert(MovieData data){
       MovieListNode node=new MovieListNode(data.getId(),data.getTitle(),data.getYear(),data.getRating(),data.getVotes(),data.getDuration() ,data.getGenres());
    	if(isEmpty()){
    			tail=node;
    	}else{
    		head.setPrevious(node);
    	}
    	node.setNext(head);
    	head=node;
    	size++;
    }
    
    
    /**
     * Removes the first occurrence of the specified element (id) from this list, if it is
     * present (optional operation). If this list does not contain the element, it is unchanged
     * @param id
     * @return the element which have been deleted or null if the element is not present
     */
    
    public MovieListNode remove(int id){
    	MovieListNode a=head;
    	for(int i=0; i<size; i++){
    		if(a.getId()==id){
    			System.out.println("a title: "+a.getTitle());
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
    
    public MovieListNode getHead(){return head;}
}
