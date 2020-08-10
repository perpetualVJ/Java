/**
 * Write a description of class TitleAndDepthComparator here.
 * 
 * @author Vishal Jotwani 
 * @version 8/8/2020
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator <QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        String t1 = q1.getInfo();
        String t2 = q2.getInfo();
        double d1 = q1.getDepth();
        double d2 = q2.getDepth();

        if (t1.compareTo(t2) < 0)
        {
            return -1;
        }
        if (t1.compareTo(t2) > 0)
        {
            return 1;
        }
        if (t1.compareTo(t2) == 0)
        {
            if (d1 < d2)
            return -1;
            if (d1 > d2)
            return 1;
        }

        return 0;
    }
}