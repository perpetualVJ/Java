/**
 * Write a description of class TitleLastAndMagnitudeComparator here.
 * 
 * @author Vishal Jotwani 
 * @version 8/8/2020
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator <QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        String[] t  = q1.getInfo().split("\\W");
        String t1 = t[t.length - 1];
        t = q2.getInfo().split("\\W");
        String t2 = t[t.length - 1];
        double m1 = q1.getMagnitude();
        double m2 = q2.getMagnitude();

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
            if (m1 < m2)
            return -1;
            if (m1 > m2)
            return 1;
        }

        return 0;
    }
}