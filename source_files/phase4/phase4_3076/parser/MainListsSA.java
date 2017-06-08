package parser;

/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import dbGenerator.Movie;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

import parser.MovieData.genre_t;
import lists.movies.MovieListNode;
import lists.movies.SelfAdjustingMovieList;
import lists.movies.SortedMovieList;
import lists.movies.UnsortedMovieList;
import lists.users.UserList;
import lists.users.UserListNode;
import lists.users.WatchMovieListNode;

/**
 *
 * @author Giwrgos Hompis
 */
public class MainListsSA {
    /* Define here the file that contains information about the movies and the events*/
    private static final String moviesInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\movies_full.txt";
    private static final String eventsInputFilePath = "C:\\Users\\dona\\Desktop\\java\\project\\events_2.txt";
    private static int i=0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
       
        SelfAdjustingMovieList selfAdjustingList = new SelfAdjustingMovieList();
       
        
        
        
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
        long currTime = System.currentTimeMillis();
        while( FileParsers.hasNextMovie() ){
            MovieData movie = FileParsers.getNextMovie();
            System.out.println( movie );
            selfAdjustingList.insert(movie);
            	
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time passed: "+ (endTime-currTime)+" ms");

        /* Get all events from file*/
        
        UserList users=new UserList();
        while( FileParsers.hasNextEvent() ){
            EventData event = FileParsers.getNextEvent();
            System.out.println("\n\n\n\n\n"+i++ +") "+event );
            if(event.getOperation().equals("I")){ // operation
				System.out.print("Searching title..."); 
            	if(selfAdjustingList.movieExists(event.getMovieId())){  //h tainia yparxei
            		System.out.println("Success ["+selfAdjustingList.get(event.getMovieId()).getTitle()+"]"); 
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
				if(selfAdjustingList.movieExists(event.getMovieId())){  //h tainia yparxei
            		System.out.println("Success ["+selfAdjustingList.get(event.getMovieId()).getTitle()+"]");
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
  
        FileParsers.finalizeParsers();
          //menu
    boolean aaaaa=true;
    MovieListNode ln=new MovieListNode();
    Scanner keyboard=new Scanner(System.in);
    String word=null;
    int fun=0;
    
    while(aaaaa){
    	System.out.println("\n______________________Menu______________________");
    	System.out.println("choose 1,2,3,4,5,6,7,8 or 93\n1.print all movie list\n2.print all users and the movies they choosed\n3.choose a user to check\n4.max_ranked\n5.movies btw XXXX and YYYY year\n6.movies that belong to X and Y genre\n7.totall movies duration of a user\n8.the user with more movies of genre X\n9.10 top ranked movies ");
    	fun=keyboard.nextInt();
    	if(fun==1){
    		System.out.println("\n___________________Movies List__________________");
    		selfAdjustingList.printList();
    	}else if(fun==2){
    		System.out.println("\n____________________User List___________________\n");
        	users.printList1();
    	}else if(fun==3){
    		System.out.println("please gine a user name");
        	word=keyboard.next();
        	users.get(word).printList();
    	}else if(fun==4){
    		String temp=null;
            ln=selfAdjustingList.getHead();
            double rank=( ( ( ln.getVotes()*ln.getRating() )+( 25000.0+6.9 ) )/( ln.getVotes() + 25000.0) );
            double max=rank;
            long currTime1 = System.currentTimeMillis();
             while(ln.getNext()!=null){
            	if(rank>max){max=rank; temp=ln.getTitle();}
            	ln=ln.getNext();
            	rank=( ( ( ln.getVotes()*ln.getRating() )+( 25000.0+6.9 ) )/( ln.getVotes() + 25000.0) );
            }
             long endTime1 = System.currentTimeMillis();
             System.out.println("time passed max rank: "+ (endTime-currTime)+" ms");
             System.out.println("max ranked: "+ temp);
    	}else if(fun==5){
    		int XXXX=0,YYYY=0,aa=0;
    		System.out.println("give XXXX");
    		XXXX=keyboard.nextInt();
    		System.out.println("give YYYY");
    		YYYY=keyboard.nextInt();
            ln=selfAdjustingList.getHead();
            long currTime1 = System.currentTimeMillis();
             while(ln.getNext()!=null){
            	if(ln.getYear()>=XXXX && ln.getYear()<=YYYY){ aa++;}
            	ln=ln.getNext();
            }
             long endTime1 = System.currentTimeMillis();
             System.out.println("time passed movies btwen xxxx yyyy: "+ (endTime-currTime)+" ms");
             System.out.println("3 betwen "+XXXX+" and "+YYYY+" are "+ aa);
        }else if(fun==6){
        	String a1=null, a2=null;
    		System.out.println("give first genre");
    		a1=keyboard.next();
    		System.out.println("give second genre");
    		a2=keyboard.next();
        	ln=selfAdjustingList.getHead();
            genre_t x=genre_t.valueOf(a1);
            genre_t y=genre_t.valueOf(a2);
            System.out.println("The movies than belong in "+ a1+" and "+a2+" are:");
            long currTime1 = System.currentTimeMillis();
            while(ln.getNext()!=null){
            	if(ln.getGenres().contains(x) && ln.getGenres().contains(y)){
            		System.out.println("["+ln.getTitle()+"]");
            	}
            	ln=ln.getNext();
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("time passed movies x y genre: "+ (endTime-currTime)+" ms");
        }else if(fun==7){
        	System.out.println("please gine a user name");
        	word=keyboard.next();
        	UserListNode x=users.get(word);
            int dur=0;
            WatchMovieListNode node=x.getWatch();
            long currTime1 = System.currentTimeMillis();
             while(node !=null){
            	dur+=selfAdjustingList.get(node.getMovieId()).getDuration();
            	node=node.getNext();	
            }
            System.out.println("totall movie duration of "+word+" is "+dur+ " mins");
            long endTime1 = System.currentTimeMillis();
            System.out.println("time passed total time of movies: "+ (endTime-currTime)+" ms");
        
        }else if(fun==8){
        	System.out.println("please gine a genre name");
        	word=keyboard.next();
        	genre_t eidos=genre_t.valueOf(word);
            UserListNode user1= users.getHead();
            UserListNode user2=null;
            WatchMovieListNode movie;
            int counter=0 ,max2=0;
            long currTime1 = System.currentTimeMillis();
             while(user1 != null){
            	movie=user1.getWatch();
            	while(movie !=null){
            		if(selfAdjustingList.get(movie.getMovieId()).getGenres().contains(eidos)){
            			counter++;
            		}
            		movie=movie.getNext();
            	}
            	if(counter>max2){
            		max2=counter;
            		user2=user1;
            	}
            	user1=user1.getNext();
            }
             long endTime1 = System.currentTimeMillis();
             System.out.println("time passed movies x y genre: "+ (endTime-currTime)+" ms");
             if(user2!=null){System.out.println("the user with more "+word+" movies is "+user2.getUsername());}
            else{System.out.println("no such genre found");}
        }else if(fun==9){
        	System.out.println("please gine a genre name");
        	word=keyboard.next();
        	genre_t eidos=genre_t.valueOf(word);
             MovieListNode ak= new MovieListNode();
             SortedMovieList biggestrank = new SortedMovieList( 
                     new Comparator<MovieListNode>(){
                         @Override
                         public int compare(MovieListNode m1, MovieListNode m2) {
                        	 if(( ( ( ( m1.getVotes()*m1.getRating() )+( 25000.0+6.9 ) )/( m1.getVotes() + 25000.0) ) ) - ( ( ( ( m2.getVotes()*m2.getRating() )+( 25000.0+6.9 ) )/( m2.getVotes() + 25000.0) )) > 0 ){
                        		 return 1;
                        	 }else {return -1;}
                         }
                     }
                 );
            
             double rank=0.0;
             int mov_count=0 ;
             ak=selfAdjustingList.getHead();
             MovieListNode min = ak;
             double min_rank=( ( ( ak.getVotes()*ak.getRating() )+( 25000.0+6.9 ) )/( ak.getVotes() + 25000.0) );
             for(int i=0 ; i<selfAdjustingList.getSize() ;i++){
             	if(ak.getGenres().contains(eidos)){
             		if(mov_count<10){
             			rank=( ( ( ak.getVotes()*ak.getRating() )+( 25000.0+6.9 ) )/( ak.getVotes() + 25000.0) );
             			if(min_rank>rank){
             				min_rank=rank;
             				min=ak;
             			}
             			MovieData boh9= new MovieData(ak.getId() , ak.getTitle() ,ak.getYear() ,ak.getRating() ,ak.getVotes() ,ak.getDuration(),ak.getGenres());
             			biggestrank.insert(boh9);
             			mov_count++;
             		}else {
             			rank=( ( ( ak.getVotes()*ak.getRating() )+( 25000.0+6.9 ) )/( ak.getVotes() + 25000.0) );
             			if(rank<min_rank){
             				biggestrank.remove(min.getId());
             				MovieData boh22= new MovieData(ak.getId() , ak.getTitle() ,ak.getYear() ,ak.getRating() ,ak.getVotes() ,ak.getDuration(),ak.getGenres());
             				biggestrank.insert(boh22);
             			}
             		}
             	}
             	ak=ak.getNext();
             }
         	System.out.println("\n______________________Max Ranked______________________");
             biggestrank.printList();
             
        }
    	
    }
    }
    
}
