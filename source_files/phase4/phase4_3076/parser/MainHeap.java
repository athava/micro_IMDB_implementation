package parser;

import heap.maxHeapPriorityQueue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

import lists.movies.MovieListNode;
import lists.movies.SelfAdjustingMovieList;
import lists.movies.SortedMovieList;
import lists.movies.UnsortedMovieList;
import lists.users.UserList;
import lists.users.UserListNode;
import lists.users.WatchMovieListNode;
import parser.MovieData.genre_t;


public class MainHeap {

	private static final String moviesInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\movies_full.txt";
    private static final String eventsInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\events_2.txt";
	
    private static int i=0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
    	maxHeapPriorityQueue[] array=new maxHeapPriorityQueue[31];
    	for(int k=0; k<31; k++){
    		array[k]=new maxHeapPriorityQueue(600);
    		System.out.println(k);
    	}
    	
    	   FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
    	   
    	   
    	   long currTime = System.currentTimeMillis();
           while( FileParsers.hasNextMovie() ){
               MovieData movie = FileParsers.getNextMovie();
               	System.out.println(( ( ( movie.getVotes()*movie.getRating() )+( 25000.0+6.9 ) )/( movie.getVotes() + 25000.0) ));
               	System.out.println(movie.getGenres());
              
               	
               	
               	if(movie.getGenres().contains(genre_t.valueOf("Short"))){
            	  array[0].insert(movie);
               }
               	if(movie.getGenres().contains(genre_t.valueOf("Drama"))){
              	  array[1].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Comedy"))){
              	  array[2].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Documentary"))){
              	  array[3].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Adult"))){
              	  array[4].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Romance"))){
              	  array[5].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Thriller"))){
              	  array[6].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Animation"))){
              	  array[7].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Family"))){
              	  array[8].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Crime"))){
              	  array[9].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Horror"))){
              	  array[10].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Sci_Fi"))){
              	  array[11].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Mystery"))){
              	  array[12].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Biography"))){
              	  array[13].insert(movie);
                 }
               		if(movie.getGenres().contains(genre_t.valueOf("Sport"))){
              	  array[14].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("History"))){
              	  array[15].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("News"))){
              	  array[16].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Talk_Show"))){
              	  array[17].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Game_Show"))){
              	  array[18].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Film_Noir"))){
              	  array[19].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Lifestyle"))){
              	  array[20].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Music"))){
              	  array[21].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Adventure"))){
              	  array[22].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Fantasy"))){
              	  array[23].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Musical"))){
              	  array[24].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Western"))){
              	  array[25].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("War"))){
              	  array[26].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Reality_TV"))){
              	  array[27].insert(movie);
                 }
               	if(movie.getGenres().contains(genre_t.valueOf("Experimental"))){
                	  array[28].insert(movie);
                   }
               	if(movie.getGenres().contains(genre_t.valueOf("Commercial"))){
                	  array[29].insert(movie);
                   }
               	if(movie.getGenres().contains(genre_t.valueOf("Action"))){
              	  array[30].insert(movie);
                 }
           }
               	
               	long endTime = System.currentTimeMillis();
                System.out.println("time passed: "+ (endTime-currTime)+" ms");
           
           UserList users=new UserList();
           while( FileParsers.hasNextEvent() ){
               EventData event = FileParsers.getNextEvent();
               System.out.println("\n\n"+i++ +") "+event );
               if(event.getOperation().equals("I")){ 
   				System.out.print("Searching title..."); 
   				for(int i=0; i<31; i++){
   					if(array[i].movieExists(event.getMovieId())){  
   	               		System.out.println("Success ["+array[i].get(event.getMovieId()).getTitle()+"]"); 
   	               		if(users.userexists(event.getUsername())){   
   	               			System.out.print("Action: User Exists.");
   	               			if(!users.get(event.getUsername()).containsMovie(event.getMovieId())){ 
   	               				System.out.println(" Movie not in list. Adding movie...");
   	               				users.get(event.getUsername()).insertMovie(event.getMovieId());    
   	               				System.out.println("______________user list______________");
   	               				users.printList1(); 
   	               				System.out.println("-------------------------------------------");
   	               			}
   	               			else{ 
   	               				System.out.println("\nmovie allready exists");
   	               				System.out.println("______________user list______________");
   	               				users.printList1(); 
   	               				System.out.println("-------------------------------------------");
   	               			}
   	               		}
   	               		else{ 
   	               			users.insert(event.getUsername()); 
   	                       	users.get(event.getUsername()).insertMovie(event.getMovieId());	
   	           				System.out.println("______________user list______________");
   	                       	users.printList1(); 
   	                       	System.out.println("-------------------------------------------");
   	                   	}
               	}
               	else{	
               		System.out.println("title not found");
               		System.out.print("Action: Movie "+event.getMovieId()+" doesn't exists in database. Ignoring event ...");
               	}
               }
               }
               else if(event.getOperation().equals("D")){
   				System.out.print("Searching title...");
   				for(int i=0; i<31; i++){
   					if(array[i].movieExists(event.getMovieId())){  
   	               		System.out.println("Success ["+array[i].get(event.getMovieId()).getTitle()+"]");
   	               		if(users.userexists(event.getUsername())){ 
   	   						System.out.print("Action: User Exists.");
   	   						if(users.get(event.getUsername()).containsMovie(event.getMovieId())){ 
   	   							System.out.print(" Movie is in list. Removing movie from list...");
   	   							users.get(event.getUsername()).removeMovie(event.getMovieId()); 
   	   							if(users.get(event.getUsername()).isMoviesListEmpty()){	
   	   								System.out.print(" User dosen't have any more movies.Removing user...");
   	   								users.remove(event.getUsername());
   	   							}
   	               				System.out.println("\n______________user list______________");
   	   							users.printList1(); 
   	   							System.out.println("-------------------------------------------");
   	   						}
   	   						else{
   	   							System.out.println("movie not in list. Do nothing...");
   	               				System.out.println("\n______________user list______________");
   	               				users.printList1();
   	               				System.out.println("-------------------------------------------");
   	   						}
   	   					}
   	   					else{ 
   	   						System.out.print("Action: User doesn't exists. Do nothing...");
   	   					}
   	   				}
   	   				
   	   				else{
   	   					System.out.println("title not found");
   	               		System.out.print("Action: Movie "+event.getMovieId()+" doesn't exists in database. Ignoring event ...");
   	   				}
   				}
               }
               
            
           }
           FileParsers.finalizeParsers();
          
    }
}
