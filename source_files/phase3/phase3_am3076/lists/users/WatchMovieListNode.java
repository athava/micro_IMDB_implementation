/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.users;

/**
 *
 * @author Giwrgos Hompis
 */
public class WatchMovieListNode {
    private int movieId;
    private WatchMovieListNode next;

    public WatchMovieListNode(){}
    
    public WatchMovieListNode(int movieId) { this.movieId=movieId; }
    
   

    public int getMovieId() {return movieId;}
    
    public WatchMovieListNode getNext() {return next;}
    
    public void setNext(WatchMovieListNode next) {this.next=next;}

    public void printLink1() {
	    System.out.print("        -" + movieId + "\n");
    }
    
}
