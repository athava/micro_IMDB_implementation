package heap;

import java.util.ArrayList;

import parser.MovieData;
import parser.MovieData.genre_t;

public class maxHeapPriorityQueueNode {

	 private int id;
	    private String title;
	    private int year;
	    private double rating;
	    private int votes;
	    private int duration;
	    private ArrayList<MovieData.genre_t> genres;
	    
	    public maxHeapPriorityQueueNode(int id, String title, int year, double rating, int votes, int duration, ArrayList<genre_t> genres) {
	        this.id = id;
	        this.title = title;
	        this.year = year;
	        this.rating = rating;
	        this.votes = votes;
	        this.duration = duration;
	        this.genres = genres;
	    }
	    
	    public int getDuration() {
	        return duration;
	    }

	    public ArrayList<genre_t> getGenres() {
	        return genres;
	    }

	    public int getId() {
	        return id;
	    }
	    
	    public double getRating() {
	        return rating;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public int getVotes() {
	        return votes;
	    }

	    public int getYear() {
	        return year;
	    }
	    
	    
	    public String Marika(){
	    	
	        String out = "";
	        out += "---------------------- Movie ----------------------\n";
	        out += "ID:       " + this.id + "\n";
	        out += "Title:    " + this.title + "\n";
	        out += "Year:     " + this.year + "\n";
	        out += "Rating:   " + this.rating + "\n";
	        out += "Votes:    " + this.votes + "\n";
	        out += "Duration: " + this.duration + " min\n";
	        out += "Genres:   ";
	        for( MovieData.genre_t g : this.genres )
	            out += g + ", ";
	        out = out.substring(0, out.length()-2); // remove last comma
	        out += "\n";
	        return out;
	    }
}
