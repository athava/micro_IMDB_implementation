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
public abstract class MovieList {
    protected int size=0;
    protected MovieListNode head=null;
    protected MovieListNode tail=null;

    
     /**
      * Creates an empty sorted list, with size=0 and null head/tail
     * @param comparator is an object that implements compare method 
     *        which defines how objects should be compared
     */
   public MovieList(){
	   this.size=0;
	   this.head=null;
	   	this.tail=null;
   }
 
     
     /*
      * These 3 methods should not be implemented here. They are implementation
      * specific and should be implemented inside unsortedList, sortedList, selfAdjustingList*/
     abstract public void insert(MovieData data);
     abstract public MovieListNode remove(int id);
     abstract public MovieListNode get(int id); 
     abstract public MovieListNode getHead();
     abstract public boolean movieExists(int movieID);
     
     
     /**
      * Removes all of the elements from this list. The list will be empty after this call returns
      */
     public void clear(){head=null;}
     
     
     /**
      * Returns the number of elements in this list
      * @return the size of the list
      */
     public int getSize(){return size;}
     
     /**
      * Returns true if this list contains no elements
      * @return true if the size of the list equals to 0 and false otherwise
      */
     public boolean isEmpty(){return (size<1);}
    
    
}
