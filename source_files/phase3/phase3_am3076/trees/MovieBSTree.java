/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;


import lists.movies.MovieListNode;
import parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieBSTree {
    
    protected int size;         // The total number of elements in the tree
    protected MovieBSTreeNode root;
    private int i=0;// The root node of the tree

    
    /**
     * Creates an empty BSTree
     */
    public MovieBSTree() {
    	size=0;
    	root=null;
    }
    
    public MovieBSTreeNode getRoot(){return root;}
    public void setRoot(MovieBSTreeNode root){
    	this.root=root;
    }
    
    /**
     * Inserts a BSTreeNode in the tree. Η κατάλληλη θέση των φύλλων του δυαδικού δέντρου 
     * αναζήτησης προσδιορίζεται από το id του MovieData
     * @param data
     */
    public void reTreeing(){}
    
    public void insert(MovieData data) {
    	MovieBSTreeNode node = new MovieBSTreeNode(data.getId(),data.getTitle(),data.getYear(),data.getRating(),data.getVotes(),data.getDuration() ,data.getGenres());
    	MovieBSTreeNode node2 = root,par=null,prev;
    	if (root == null) {
    		root = node;
    		node.setParent(null);
    		node.setlChild(null);
    		node.setrChild(null);
    		size++;
    	} else {
    		while (true) {
    			prev=par;
    			par=node2;
    			if (node.getId() < node2.getId()) {
    				node2=node2.getlChild();
    				if (node2 == null) {
    					par.setlChild(node);
    					par.setParent(prev);
    					size++;
    					return;
    				}
    			} else {
    				node2 = node2.getrChild();
    				if (node2 == null) {
    					par.setrChild(node);
    					par.setParent(prev);
    					size++;
    					return; 
    				}
    			}
    		}
    	}
	}
   
   
    
    public void traverse(MovieBSTreeNode focusNode) {
    	 if (focusNode != null) {
    		traverse(focusNode.getlChild());
		  	System.out.println(focusNode.getTitle());
		  	traverse(focusNode.getrChild());
	       }
    	 
    }
    
 
    
    /**
     * Removes the first occurrence of the specified element (id) from this tree, if it is
     * present (optional operation). If this tree does not contain the element, it is unchanged
     * @param id
     * @return the element which have been deleted or null if the element is not present
     */
    public MovieBSTreeNode remove(int id) {
    	
    		MovieBSTreeNode node2 = root;
    		MovieBSTreeNode par=null;
    		boolean isItALeftChild = true;
    		while (node2!=null && node2.getId() != id) {
    			par = node2;
    			if (id < node2.getId()) {
        			isItALeftChild = true;
    	    		node2 = node2.getlChild();
        		} else {
        			isItALeftChild = false;
        			node2 = node2.getrChild();
        		}
    		}
    		if (node2!=null && (node2.getlChild() == null) && (node2.getrChild()== null)) {
        			if (node2 == root){root = null; }
        			else if (isItALeftChild){par.setlChild(null); }
        			else {par.setrChild(null);
        			}
        		}
        		else if (node2!=null && node2.getrChild() == null) {
        			if (node2 == root){
        					root = node2.getlChild();
        			}
        			else if (isItALeftChild){
        				par.setlChild(node2.getlChild());
        			}
        			else{
        				par.setrChild(node2.getlChild());
        			}
        		}
        		else if (node2!=null && node2.getlChild() == null) {
        			if (node2 == root)
        				root = node2.getrChild();
        			else if (isItALeftChild)
        				par.setlChild(node2.getrChild());
        			else
        				par.setrChild(node2.getrChild()); 
        		}
        		else {
        			MovieBSTreeNode changePar = node2, change = node2;
            		MovieBSTreeNode Node=null;
            		if(node2!=null){Node=node2.getrChild();}
        			while (Node != null) {
        				changePar = change;
        				change = Node;
        				Node = Node.getlChild();
        			}
        			if (change != null && change != node2.getrChild()) {
        				changePar.setlChild(change.getrChild());
        				change.setrChild(change.getrChild());
        			}
        			if (node2 == root){
        				root = change;
        			}
    		    	else if (isItALeftChild){
    		    		par.setlChild(change);
    		    	}
    		    	else{
    		    		par.setrChild(change);
    		    	}
        			if(node2!=null ){
        			change.setlChild(node2.getlChild()); }
        		}
        		if(node2!=null)
        			size--;
        		return node2 ;
    }
    
    
    /**
     * Returns the element with the specified id in the tree or null if it not present
     * @param id
     * @return the element
     */
    public MovieBSTreeNode get(int id) {
    	MovieBSTreeNode node = root;
    	if(node!= null && id==root.getId()){return root;}
    	while (true) {
    		if(node!= null && id==node.getId()){return root;}
			else if (node!= null && id < node.getId()) {
				node=node.getlChild();
				if (node!= null && node.getId()==id) {
					return node;
				}
			} 
			else if (node!= null && id > node.getId()) {
				node = node.getrChild();
				if (node!= null && node.getId()==id) {
					return node; 
				}
			}
			else{return null;}	
    	}
    }
    
    
            
    /**
     * Removes all of the elements from this tree. The tree will be empty
     * after this call returns
     */
    public void clear() {
    	root.setlChild(null);
    	root.setrChild(null);
    	size=0;
    }
    
    
    
    /**
     * Returns the number of all elements that are inside the tree
     * @return nElements
     */
    public int getSize() {return size;}
    
    
    
    /**
     * Returns true if this tree contains no elements
     * @return true if the number of elements in hashTable equals to 0 and false otherwise
     */
    public boolean isEmpty() {return(size==0);}
    
    public boolean movieExists(int id){
    	MovieBSTreeNode temp=get(id);
    		if(temp!=null){
    			return true;
    		}else{return false;}
    }
    
}
