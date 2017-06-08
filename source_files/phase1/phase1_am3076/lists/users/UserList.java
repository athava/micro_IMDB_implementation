/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.users;




/**
 *
 * @author Giwrgos Hompis
 */

public class UserList {
    private UserListNode head;
    private int size=0;
    
    public UserList(){}
  
    public void insert(String username){
    	UserListNode node= new UserListNode(username);
    	if(isEmpty()){
    		head=node;
    		head.setNext(null);
    		size++;
    	}else{
    		node.setNext(head);
    		head=node;
    		size++;
    	}
    	
    }
  
    
    public boolean userexists(String username){
    	UserListNode exists=head;
    	for(int i=0; i<size; i++){
    		if(exists.getUsername().equals(username)){
    			return true;
    		}
    		exists=exists.getNext();
    	}
    	return false;
    }
    
    
    public void printList1(){
    	UserListNode exists=head;
    	for(int i=0; i<size; i++){
    		exists.printList();
    		exists=exists.getNext();
    	}
    }
    
    public UserListNode remove(String username){
    	UserListNode node1=head;
    	UserListNode node2=node1;
    	if(node1.getUsername().equals(head)){
    		head=node1.getNext();
    		node1.setNext(null);
    		size--;
    		return node1;
    	}
    	while(node1.getNext() != null){
    		if(node1.getUsername().equals(username)){break;}
    			node2=node1;
    			node1=node1.getNext();
    	}
    	
    	node2.setNext(node1.getNext());
    	node1.setNext(null);
    	size--;
    	return node2;
    }
    
    
    public UserListNode get(String username){
    	UserListNode search=head;
    	for(int i=0; i<size; i++){
    		if(search.getUsername().equals(username)){
    			break;
    		}
    		search=search.getNext();
    	}
    	return search;
    }
   
    
    
    
    public boolean isEmpty(){return (size == 0);}
   
    
    
    
}
