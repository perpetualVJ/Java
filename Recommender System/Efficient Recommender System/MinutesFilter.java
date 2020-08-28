
/**
 * Write a description of class MinutesFilter here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */

public class MinutesFilter implements Filter {
    private int myMin;
    private int myMax;
	
	public MinutesFilter(int min, int max) {
        myMin = min;
        myMax = max;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getMinutes(id) >= myMin && MovieDatabase.getMinutes(id) <= myMax;
	}

}
