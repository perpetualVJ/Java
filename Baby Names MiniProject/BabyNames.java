import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames{
    void totalBirths()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);

        int totalGirls = 0, totalBoys = 0, totalBirth = 0, girls = 0, boys = 0, names = 0;

        for (CSVRecord record : parser)
        {
            if (record.get(1).equals("F"))
            {
                girls += 1;
                totalGirls += Integer.parseInt(record.get(2));
            }    
            else if (record.get(1).equals("M"))
            {
                boys += 1;
                totalBoys += Integer.parseInt(record.get(2));
            }

            totalBirth = totalBoys + totalGirls;
            names = boys + girls;

        }
            System.out.println("Unique Girl Names: " + girls);
            System.out.println("Unique Boy Names " + boys);
            System.out.println("Total unique names " + names);
            System.out.println("Total number of boys " + totalBoys);
            System.out.println("Total number of girls " + totalGirls);
            System.out.println("Total births " + totalBirth);
    }

    int getRank(int year, String name, String gender)
    {
        String f = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(f);
        CSVParser parser= fr.getCSVParser(false);

        int g = 0, m = 0;
        int rank = -1;

        for (CSVRecord record : parser)
        {
            if (record.get(1).equals("F"))
            {
                g += 1;
            }
            if (record.get(1).equals("M"))
            {
                m += 1;
            }
            //System.out.println(g + " " + m);
            if(record.get(0).equals(name))
            {
                //System.out.println(rank);
                if (gender.equals("M") && record.get(1).equals("M"))
                {
                    rank = m;
                    break;
                }
                if (gender.equals("F") && record.get(1).equals("F")) 
                {
                    rank = g;
                    break;
                } 

            }
        }
        return rank;
    }

    String getName(int year, int rank, String gender)
    {
        String f = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(f);
        CSVParser parser= fr.getCSVParser(false);

        String s = "NO NAME";
        int g = 0, m = 0;

        for (CSVRecord record : parser)
        {
            if (record.get(1).equals("F"))
            { 
                g += 1;
            }
            else if (record.get(1).equals("M"))
            {
                m += 1;
            }

            if (rank == g && gender == "F")
            {
                s = record.get(0);
            }
            else if (rank == m && gender == "M")
            {
                s = record.get(0);
            }
        }
        return s;
    }

    void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        //System.out.println(rank);
        if(rank == -1)
        {
            System.out.println("Rank Invalid");
        }
        else{
            String newName = getName(newYear, rank, gender);
            System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
        }
    }

    int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();

        String fileName = "";
        String year;
        int max = -1;
        int y;
        int rank = -1;
        for (File f : dr.selectedFiles())
        {
            fileName = f.getName();
            year = fileName.substring(3, 7);
            y = Integer.parseInt(year);
            if (rank == -1)
            {
                rank = getRank(y, name, gender);
                max = y;
            }
            else
            {
                if (getRank(y, name, gender) < rank)
                {
                    rank = getRank(y, name, gender);
                    max = y;
                }
            }
        }
        return max;
    }

    double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();

        String fileName = "";
        String year;
        int y;
        double rank = -1.0;
        int sum = 0;
        int count = 0;
        for (File f : dr.selectedFiles())
        {
            fileName = f.getName();
            year = fileName.substring(3, 7);
            y = Integer.parseInt(year);
            
            //System.out.println(getRank(y, name, gender));
            if (getRank(y, name, gender) != -1)
            { 
                sum += getRank(y, name, gender);
                count += 1;
            }
            //System.out.println(sum + " " + count);
        }

        if (count != 0)
        {
            rank = (double) sum / count;
        }
        return rank;
    }

    int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        String f = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(f);
        CSVParser parser= fr.getCSVParser(false);

        int g = 0, m = 0;
        int tb = 0;
        for (CSVRecord record : parser)
        {
            if(record.get(1).equals("F") && gender.equals("F"))
            {
                if (record.get(0).equals(name))
                {
                    break;
                }
                tb += Integer.parseInt(record.get(2));
            }
            else if(record.get(1).equals("M") && gender.equals("M"))
            {
                if (record.get(0).equals(name))
                {
                    break;
                }
                tb += Integer.parseInt(record.get(2));
            }

        }
        return tb;
    }
    public static void main(String[] args) {
        BabyNames b = new BabyNames();
        b.totalBirths();
        System.out.println("Rank: "  + b.getRank(1971, "Frank", "M"));
        System.out.println("Name: "  + b.getName(1982, 450, "M"));
        b.whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println("Highest Rank " + b.yearOfHighestRank("Mich", "M"));
        System.out.println("Average rank " + b.getAverageRank("Robert", "M"));
        System.out.println("Total Births Higher " + b.getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}