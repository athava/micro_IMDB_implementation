package heap;

import lists.movies.MovieListNode;
import lists.users.UserListNode;
import parser.MovieData;


public class maxHeapPriorityQueue{
	 
	  private maxHeapPriorityQueueNode[] theHeap;
	  private int size;
	 
	 
	  public maxHeapPriorityQueue(int cap){
	    size=0;
	    theHeap = new maxHeapPriorityQueueNode[cap];
	  }
	 
	
	 
	  public int getSize(){
	    return size;
	  }
	 
	  public boolean insert(MovieData data){
		  maxHeapPriorityQueueNode node=new maxHeapPriorityQueueNode(data.getId(),data.getTitle(),data.getYear(),data.getRating(),data.getVotes(),data.getDuration() ,data.getGenres()); 
		  int index;
		    if((size>=theHeap.length)||(node==null)){
		      return false;
		    }    
		    theHeap[size++]=node;
		    index=shiftUp(size-1);
		    return true;
		    
	  }
	  
	  
	 public void print(){
		 int i;
		    for(i=0;i<size;i++){
		    	System.out.println(( ( ( theHeap[i].getVotes()*theHeap[i].getRating() )+( 25000.0+6.9 ) )/( theHeap[i].getVotes() + 25000.0) ));
		    }
	 }
	 
	 public Object removeMax()
	  {
	    if(size <= 0)
	    {
	      return null;
	    }
	    swap(0,--size);
	    shiftDown(0);
	    return theHeap[size];
	  }
	 
	  private void shiftDown(int index){
	    int lc;
	    if((index>=0)&&(index<(size/2)))
	    {
	      lc=2*index+1;
	      if(((lc+1)<size)&&(((Comparable)( ( ( theHeap[lc].getVotes()*theHeap[lc].getRating() )+( 25000.0+6.9 ) )/( theHeap[lc].getVotes() + 25000.0) )).compareTo(( ( ( theHeap[lc+1].getVotes()*theHeap[lc+1].getRating() )+( 25000.0+6.9 ) )/( theHeap[lc +1].getVotes() + 25000.0) )))<0)
	      {
	        lc++;
	      }
	      if(((Comparable)( ( ( theHeap[index].getVotes()*theHeap[index].getRating() )+( 25000.0+6.9 ) )/( theHeap[index].getVotes() + 25000.0) )).compareTo(( ( ( theHeap[lc].getVotes()*theHeap[lc].getRating() )+( 25000.0+6.9 ) )/( theHeap[lc].getVotes() + 25000.0) ))<0)
	      {
	        swap(index,lc);
	        shiftDown(lc);
	      }
	    }
	  }
	 
	  private int shiftUp(int index){
	    int p;
	    if((index>0)&&(index<size)){
	      p=(index-1)/2;
	        if(((Comparable)( ( ( theHeap[index].getVotes()*theHeap[index].getRating() )+( 25000.0+6.9 ) )/( theHeap[index].getVotes() + 25000.0) )).compareTo(( ( ( theHeap[p].getVotes()*theHeap[p].getRating() )+( 25000.0+6.9 ) )/( theHeap[p].getVotes() + 25000.0) ))>0){
	         swap(p,index);
	         return shiftUp(p);
	      }
	    }
	    return index;
	  }
	 
	  private void swap(int x, int y) {
	    if((x>=0)||(y>=0)||(x<theHeap.length)||(y<theHeap.length)){
	    maxHeapPriorityQueueNode temp = theHeap[x];
	    theHeap[x]=theHeap[y];
	    theHeap[y]=temp;
	    }
	   }
	  
	  public maxHeapPriorityQueueNode get(int id) {
		  maxHeapPriorityQueueNode search=theHeap[0];
		  for(int i=0; i<600; i++){
	    		while(search!=null){
	    			if(search.getId()==id){
	    				       return search;
	        		}
	    			search=theHeap[i];
	    		}
	    	}
	    	return null;
	    }
	  public boolean movieExists(int movieID){
		  maxHeapPriorityQueueNode search=theHeap[0];
		  for(int i=0; i<600; i++){
	    		while(search!=null){
	    			if(search.getId()==movieID){
	    				       return true;
	        		}
	    			search=theHeap[i];
	    		}
	    	}
	    	return false;
	    }
	 
	/*  private void buildHeap(){
	    int i;
	    for(i=(size/2-1);i>=0;i--)
	    {
	      shiftDown(i);
	    }
	  }
	 
	  public void parse(){
	    int i;
	    if(size>0){
	    String ret= " ";
	    for(i=0;i<size;i++){
	      ret+= theHeap[i].toString() + " ";
	      System.out.println(ret);
	    }
	    }
	  }*/
	  
	  public String get(String genrename){
	    	return genrename;
	  }
}






//( ( ( theHeap[index].getVotes()*theHeap[index].getRating() )+( 25000.0+6,9 ) )/( theHeap[index].getVotes + 25000.0) )















