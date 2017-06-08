package parser;

/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import dbGenerator.Movie;



import java.io.FileNotFoundException;
import java.io.IOException;
import parser.MovieData.genre_t;
import lists.movies.MovieListNode;
import lists.movies.SelfAdjustingMovieList;
import lists.movies.SortedMovieList;
import lists.movies.UnsortedMovieList;
import lists.users.UserList;

/**
 *
 * @author Giwrgos Hompis
 */
public class Main {
    /* Define here the file that contains information about the movies and the events*/
    private static final String moviesInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\movies_medium.txt";
    private static final String eventsInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\events_2.txt";
    private static int i=0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        UnsortedMovieList unsortedList = new UnsortedMovieList();
        SortedMovieList sortedList = new SortedMovieList(); 
        SelfAdjustingMovieList selfAdjustingList = new SelfAdjustingMovieList();
       
        
        
        
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
        while( FileParsers.hasNextMovie() ){
            MovieData movie = FileParsers.getNextMovie();
            	unsortedList.insert(movie);
            	sortedList.insert(movie);
            	selfAdjustingList.insert(movie);
            	
        }

        /* Get all events from file*/
        
        UserList users=new UserList();
        while( FileParsers.hasNextEvent() ){
            EventData event = FileParsers.getNextEvent();
            System.out.println("\n\n\n\n\n"+i++ +") "+event );
            if(event.getOperation().equals("I")){ // operation
				System.out.print("Searching title..."); 
            	if(unsortedList.movieExists(event.getMovieId())){  //h tainia yparxei
            		System.out.println("Success ["+unsortedList.get(event.getMovieId()).getTitle()+"]"); 
            		if(users.userexists(event.getUsername())){    //o xrhsths yparxei
            			System.out.print("Action: User Exists.");
            			if(!users.get(event.getUsername()).containsMovie(event.getMovieId())){ //h tainia den yparxei sth lista toy xrhsth
            				System.out.println(" Movie not in list. Adding movie...");
            				users.get(event.getUsername()).insertMovie(event.getMovieId());    
            				System.out.println("-----------------user list-----------------");
            				users.printList1(); 
            				System.out.println("-------------------------------------------");
            			}
            			else{ // h tainia yparxei idi sth lista
            				System.out.println("\nmovie allready exists");
            				System.out.println("-----------------user list-----------------");
            				users.printList1(); 
            				System.out.println("-------------------------------------------");
            			}
            		}
            		else{ // o xrhsths den uparxei
            			users.insert(event.getUsername()); 
                    	users.get(event.getUsername()).insertMovie(event.getMovieId());	
                    	System.out.println("-----------------user list-----------------");
                    	users.printList1(); 
                    	System.out.println("-------------------------------------------");
                	}
            	}
            	else{	// h tainia den yparxei
            		System.out.println("title not found");
            		System.out.print("Action: Movie "+event.getMovieId()+" doesn't exists in database. Ignoring event ...");
            	}
            }
            else if(event.getOperation().equals("D")){
				System.out.print("Searching title...");
				if(unsortedList.movieExists(event.getMovieId())){  //h tainia yparxei
            		System.out.println("Success ["+unsortedList.get(event.getMovieId()).getTitle()+"]");
            		if(users.userexists(event.getUsername())){ // o xrhsths yparxei
						System.out.print("Action: User Exists.");
						if(users.get(event.getUsername()).containsMovie(event.getMovieId())){ //h tainia yparxei sth lista
							System.out.print(" Movie is in list. Removing movie from list...");
							users.get(event.getUsername()).removeMovie(event.getMovieId()); //diagrafh tainias
							if(users.get(event.getUsername()).isMoviesListEmpty()){	//diagrafh xrhsth an h lsita einai adia
								System.out.print(" User dosen't have any more movies.Removing user...");
								users.remove(event.getUsername());
							}
							System.out.println("\n-----------------user list-----------------");
							users.printList1(); 
							System.out.println("-------------------------------------------");
						}
						else{// h tainia den yparxei sth lista
							System.out.println("movie not in list. Do nothing...");
            				System.out.println("\n-----------------user list-----------------");
            				users.printList1();
            				System.out.println("-------------------------------------------");
						}
					}
					else{ // o xrhsths den yparxei
						System.out.print("Action: User doesn't exists. Do nothing...");
					}
				}
				else{// h tainia den yparxei
					System.out.println("title not found");
            		System.out.print("Action: Movie "+event.getMovieId()+" doesn't exists in database. Ignoring event ...");
				}
            }
        }
        
        //max rank
        MovieListNode ln=new MovieListNode();
        String temp=null;
        ln=unsortedList.getHead();
        double rank=(((ln.getVotes()*(ln.getVotes()+25000))*ln.getRating())+(25000/(ln.getVotes()+25000)*6.9));
        double max=rank;
        while(ln.getNext()!=null){
        	if(rank>max){max=rank; temp=ln.getTitle();}
        	ln=ln.getNext();
        	rank=(((ln.getVotes()*(ln.getVotes()+25000))*ln.getRating())+(25000/(ln.getVotes()+25000)*6.9));
        }
        System.out.println("1)max ranked: "+ temp);
        
        //XXXX-YYYY
        ln=sortedList.getHead();
        int a=2000, b=2005, aa=0;
        while(ln.getNext()!=null){
        	if(ln.getYear()>=a && ln.getYear()<=b){ aa++;}
        	ln=ln.getNext();
        }
        System.out.println("2)movies betwen "+a+" and "+b+" are "+ aa);
        
        
      //same genre 
        ln=unsortedList.getHead();
        String a1="Action";
        String a2="Adventure";
        genre_t x=genre_t.valueOf(a1);
        genre_t y=genre_t.valueOf(a2);
        System.out.println("3)The movies than belong in "+ a1+" and "+a2+" are:");
        while(ln.getNext()!=null){
        	if(ln.getGenres().contains(x) && ln.getGenres().contains(y)){
        		System.out.println("["+ln.getTitle()+"]");
        	}
        	ln=ln.getNext();
        }
        
   
        FileParsers.finalizeParsers();
    }
    
   
    
}
