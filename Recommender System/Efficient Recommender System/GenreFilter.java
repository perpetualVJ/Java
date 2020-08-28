
/**
 * Write a description of class GenreFilter here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


public class GenreFilter implements Filter {
	private String myGenre;
	
	public GenreFilter(String genre) {
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myGenre);
	}

}
