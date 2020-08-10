import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class ParsingWeather{
    CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldest = null;

        double temp1, temp2;
        for (CSVRecord record : parser)
        {
            if (!record.get("TemperatureF").equals("-9999"))
            {
                if (coldest == null)
                {
                    coldest = record;
                }
                else
                {
                    temp1 = Double.parseDouble(record.get("TemperatureF"));
                    temp2 = Double.parseDouble(coldest.get("TemperatureF"));

                    if (temp1 < temp2)
                    {
                        coldest = record;
                    }
                }
            }
        }
        return coldest;
    }

    void testColdestHourInFile(){
        System.out.println("Coldest Hour in File");

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println(coldestRecord.get("TemperatureF"));
    }

    String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecord = null;
        CSVRecord record = null;
        String coldestFile = "";
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            if (coldestRecord == null)
            {
                coldestRecord = coldestHourInFile(fr.getCSVParser());
                coldestFile = f.getName();
            }
            else{
                record = coldestHourInFile(fr.getCSVParser());

                if(Double.parseDouble(coldestRecord.get("TemperatureF")) > Double.parseDouble(record.get("TemperatureF"))) 
                {
                    coldestRecord = record;
                    coldestFile  = f.getName();
                }           
            }
        }
        return coldestFile;
    }

    void testFileWithColdestTemperature(){
        System.out.println("Coldest Day " + fileWithColdestTemperature());
    }

    CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord humidity = null;

        double h1, h2;
        for (CSVRecord record : parser)
        {
            if (!record.get("Humidity").equals("N/A"))
            {
                if (humidity == null)
                {
                    humidity = record;
                }
                else
                {
                    h1 = Double.parseDouble(record.get("Humidity"));
                    h2 = Double.parseDouble(humidity.get("Humidity"));

                    if (h1 < h2)
                    {
                        humidity = record;
                    }
                }
            }
        }
        return humidity;
    }
    void testLowestHumidityInFile(){
        System.out.println("Lowest Humidity  in File");

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord humidityRecord = lowestHumidityInFile(parser);
        System.out.println(humidityRecord.get("Humidity") + " " + humidityRecord.get("DateUTC"));
    }

    CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord humidityRecord = null;
        CSVRecord record = null;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            if (humidityRecord == null)
            {
                humidityRecord =  lowestHumidityInFile(fr.getCSVParser());
            }
            else{
                record = lowestHumidityInFile(fr.getCSVParser());

                if(Double.parseDouble(humidityRecord.get("Humidity")) > Double.parseDouble(record.get("Humidity"))) 
                {
                    humidityRecord = record;
                }           
            }
        }
        return humidityRecord;
    }

    void testLowestHumidityInManyFiles(){
        System.out.println("Lowest Humidity in many files " + lowestHumidityInManyFiles().get("DateUTC"));
    }

    double averageTempInFile(CSVParser parser){
        int count = 0;
        double sum = 0;
        for (CSVRecord record : parser)
        {
            if (!record.get("TemperatureF").equals("-9999"))
            {
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
                count += 1;
            }
        }

        return sum / count;
    }

    void testAverageTempInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average Temp in File is " + averageTempInFile(parser));
    }

    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        int count = 0;
        double sum = 0;
        for (CSVRecord record : parser)
        {
            if (!record.get("TemperatureF").equals("-9999") && !record.get("Humidity").equals("N/A"))
            {
                if (Double.parseDouble(record.get("Humidity")) >= value)
                {
                    sum = sum + Double.parseDouble(record.get("TemperatureF"));
                    count += 1;
                }
            }

        }
        if (count == 0)
        return -1.0;
        return sum / count;
    }

    void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double d = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (d == -1.0)
        System.out.println("No temperature with that humidity");
        else
        System.out.println("Average Temp in File Greater than 80 is " + d);
    }
    public static void main(String[] args) {
        ParsingWeather p = new ParsingWeather();
        p.testColdestHourInFile();
        p.testFileWithColdestTemperature();
        p.testLowestHumidityInFile();
        p.testLowestHumidityInManyFiles();
        p.testAverageTempInFile();
        p.testAverageTemperatureWithHighHumidityInFile();
    
    }
}