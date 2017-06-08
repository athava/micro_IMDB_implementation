/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import parser.EventData;
import lists.movies.MovieListNode;
import parser.MovieData.genre_t;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Giwrgos Hompis
 */
public class FileParsers {
    private static FileReader db_fr=null, events_fr=null;
    private static BufferedReader db_br=null, events_br=null;
    
    
    
    public static void initializeParsers(String dbFilePath, String eventsFilePath) throws FileNotFoundException{
         db_fr = new FileReader(dbFilePath);
         db_br = new BufferedReader(db_fr);
         events_fr = new FileReader(eventsFilePath);
         events_br = new BufferedReader(events_fr);
    }
    
    public static void finalizeParsers() throws IOException{
        db_br.close();
        db_fr.close();
        events_br.close();
        events_fr.close();
    }
    
    public static MovieData getNextMovie() throws IOException {
        String dataLine = db_br.readLine();
        String genresLine = db_br.readLine();
       // if(has)
        if( dataLine==null || genresLine==null )
            return null;
        // Extract movie data
        String[] data = dataLine.split("\t");
        for(int i=0; i<data.length; i++) // remove "[" and "]" chars
            data[i] = data[i].replace("[","").replace("]","");
        int movieId     = Integer.parseInt( data[0] );
        String title    = data[1];
        int year        = Integer.parseInt( data[2] );
        double rating   = Double.parseDouble( data[3] );
        int votes       = Integer.parseInt( data[4] );
        int duration    = Integer.parseInt( data[5] );
        // Extract genres
        String[] genres = genresLine.split("\t");
        ArrayList<genre_t> genresList = new ArrayList<>();
        for(String s : genres)
            genresList.add( genre_t.valueOf(s) );
        // Create node and return
        return new MovieData(movieId, title, year, rating, votes, duration, genresList);
    }
    
    public static boolean hasNextMovie() throws IOException{
        return ( db_br.ready() )?  true:false;
    }
    
    
    public static EventData getNextEvent() throws IOException {
        String line = events_br.readLine();
        if( line==null )
            return null;
        // Extract movie data
        String[] data = line.split("\t");
        for(int i=0; i<data.length; i++) // remove "[" and "]" chars
            data[i] = data[i].replace("[","").replace("]","");
        String operation = data[0];
        String user      = data[1];
        int movieId      = Integer.parseInt( data[2] );
        // Create node and return
        return new EventData(operation, user, movieId);
    }
    
    public static boolean hasNextEvent() throws IOException{
        return ( events_br.ready() )?  true:false;
    }
}
