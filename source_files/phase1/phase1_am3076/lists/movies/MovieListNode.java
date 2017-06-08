/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.movies;


import parser.MovieData.genre_t;

import java.util.ArrayList;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieListNode {
    
    private int id;
    private String title;
    private int year;
    private double rating;
    private int votes;
    private int duration;
    private ArrayList<genre_t> genres;
    
    private MovieListNode previous;
    private MovieListNode next;
    
    public MovieListNode(){}
    
    public MovieListNode(int id, String title, int year, double rating, int votes, int duration, ArrayList<genre_t> genres) {
    	this.id=id;
    	this.title=title;
    	this.year=year;
    	this.rating=rating;
    	this.votes=votes;
    	this.duration=duration;
    	this.genres=genres;
    }
   
    public int getId() {return id;}
    public String getTitle() {return title;}
    public int getYear() {return year;}
    public double getRating() {return rating;}
    public int getVotes() {return votes;}
    public int getDuration() {return duration;}
    public ArrayList<genre_t> getGenres() {return genres;}
    public MovieListNode getPrevious() {return previous;}
    public MovieListNode getNext() {return next;}
    
    public void setNext(MovieListNode next) {this.next=next;}
    public void setPrevious(MovieListNode previous) {this.previous=previous;}
    
}
