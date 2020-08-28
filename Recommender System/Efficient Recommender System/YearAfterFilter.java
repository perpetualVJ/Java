
/**
 * Write a description of class YearAfterFilter here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
