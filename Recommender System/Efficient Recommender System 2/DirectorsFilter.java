
/**
 * Write a description of class DirectorsFilter here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


public class DirectorsFilter implements Filter {
    private String[] myDirectors;
	
	public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) {
        
        for (String s : myDirectors)
        {
            if (MovieDatabase.getDirector(id).contains(s))
                return true;
        }

        return false;
	}

}
