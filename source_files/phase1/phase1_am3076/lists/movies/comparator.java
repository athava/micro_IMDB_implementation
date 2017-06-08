package lists.movies;

import java.util.Comparator;

public class comparator implements Comparator<MovieListNode>{

	public comparator(){}
	public int compare(MovieListNode a , MovieListNode b ){
		return (a.getYear() -b.getYear());
		
	}
}
