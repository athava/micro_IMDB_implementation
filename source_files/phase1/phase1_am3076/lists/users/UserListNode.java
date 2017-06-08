/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.users;

/**
 *
 * @author Giwrgos Hompis
 */
public class UserListNode {
    private String username;
    private UserListNode next;
    private WatchMovieListNode head2;
    
    public UserListNode(){}
    
    public UserListNode(String username){ this.username=username; }
    
    public String getUsername() {return username;}
    public UserListNode getNext() {return next;}
    public void setNext(UserListNode next) {this.next=next;}
    
    public void insertMovie(int id){
    	WatchMovieListNode node=new WatchMovieListNode(id);
    	if(isMoviesListEmpty()){
    		
    		node.setNext(null);
    		head2=node;
    	}else{
    		node.setNext(head2);
    		head2=node;
    	}
    	
        	
    }
    
    //public int get(int id){}
   
    public void printList() {
    	WatchMovieListNode currentLink = head2;
		System.out.println(username);
	    while(currentLink != null) {
		    currentLink.printLink1();
		    currentLink = currentLink.getNext();
	    }
	    System.out.println("");
    }

    public WatchMovieListNode removeMovie(int id){
    	WatchMovieListNode node1=head2;
    	WatchMovieListNode node2=node1;
    	if(node1.getMovieId()==id){
    		head2=node1.getNext();
    		node1.setNext(null);
    		return node1;
    	}
    	while(node1.getNext() != null){
    		if(node1.getMovieId()==id){break;}
    			node2=node1;
    			node1=node1.getNext();
    	}
    	
    	node2.setNext(node1.getNext());
    	node1.setNext(null);
    	return node2;
    }
   
    
    public boolean containsMovie(int id){
    	WatchMovieListNode is = head2;
    	while(is !=null){
    		if(is.getMovieId()==id){return true;}
    		is=is.getNext();
    	}
    	return false;
    }
   
    public boolean isMoviesListEmpty(){return (head2==null);}
   
   

    
}
