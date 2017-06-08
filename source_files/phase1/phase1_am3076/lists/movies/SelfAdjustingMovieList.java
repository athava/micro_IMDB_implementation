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
public class SelfAdjustingMovieList extends MovieList {
    
    /**
     * Creates an empty self adjusting list
     */
    public SelfAdjustingMovieList(){}


    
    /**
     * Creates a MovieListNode and appends it the specified element (MovieListNode) in the list.
     * The values for the MovieListNode are contained inside MovieData
     * @param node
     * @return true if node inserted successfully, false otherwise
     */
    @Override
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
     * Returns the element with the specified id in this list or null if it not present. If the
     * element is present, then it moves to the front of the list
     * @param id
     * @return the element
     */
    @Override
    public MovieListNode get(int id){
    	
    	if(head.getId()==id){return head;}
    	MovieListNode a=head;
    	MovieListNode temp=null;
    	for(int i=0; i<size; i++){
    		if(a.getId()==id){
    			if(a==tail){
    				MovieListNode aprev=a.getPrevious();
    				aprev.setNext(null);
    				tail=aprev;
    			}else{
    				a.getPrevious().setNext(a.getNext());
    				a.getNext().setPrevious(a.getPrevious());
    			}
    			a.setNext(head);
    			a.setPrevious(null);
    			head=a;
    			break;
    		}
    		a=a.getNext();
    	}
		return temp;
    }
    
    
}
