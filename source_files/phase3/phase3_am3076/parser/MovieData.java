/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;

import lists.movies.MovieListNode;
import lists.movies.UnsortedMovieList;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieData {
    public  enum genre_t {
        Short, Drama, Comedy, Documentary, Adult, Action, Romance,
        Thriller, Animation, Family, Crime, Horror, Music, Adventure, Fantasy,
        Sci_Fi, Mystery, Biography, Sport, History, Musical, Western, War, Reality_TV,
        News, Talk_Show, Game_Show, Film_Noir, Lifestyle, Experimental, Commercial 
    };
    
    private  int id;
    private  String title;
    private  int year;
    private  double rating;
    private  int votes;
    private  int duration;
    private  ArrayList<genre_t> genres;
    
    public MovieData(int id, String title, int year, double rating, int votes, int duration, ArrayList<genre_t> genres) {
        this.id = id ;
        this.title =title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.duration = duration;
        this.genres = genres;
    }

    public  int getDuration() {
        return duration;
    }

    public  ArrayList<genre_t> getGenres() {
        return genres;
    }

    public  int getId() {
    	return id;
    }

    public  double getRating() {
        return rating;
    }

    public  String getTitle() {
        return title;
    }

    public  int getVotes() {
        return votes;
    }

    public  int getYear() {
        return year;
    }
    
    public String toString(){
        String out = "";
        out += "---------------------- Movie ----------------------\n";
        out += "ID:       " + this.id + "\n";
        out += "Title:    " + this.title + "\n";
        out += "Year:     " + this.year + "\n";
        out += "Rating:   " + this.rating + "\n";
        out += "Votes:    " + this.votes + "\n";
        out += "Duration: " + this.duration + " min\n";
        out += "Genres:   ";
        for( genre_t g : this.genres )
            out += g + ", ";
        out = out.substring(0, out.length()-2); // remove last comma
        out += "\n"; 
        return out;
    }
    
}
