/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author Giwrgos Hompis
 */
public class EventData {
    
    private String operation;
    private String username;
    private int movieId;
  

    public EventData(){}
    
    public EventData(String operation, String username, int movieId) {
        this.operation = operation;
        this.username = username;
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getOperation() {
        return operation;
    }

    public String getUsername() {
        return username;
    }

    
    @Override
    public String toString() {
    	
        String out = "";
        out += "[" + this.operation + "] - ";
        out += "[" + this.username + "] - ";
        out += "[" + this.movieId + "]";
        return out;
    }
    
    
}
