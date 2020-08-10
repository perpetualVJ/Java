import edu.duke.*;
import org.apache.commons.csv.*;


public class parsingdata{

    String countryInfo(CSVParser parser, String country){
        String info = "NOT FOUND";
        int flag = 0;
        for (CSVRecord record : parser){
            if (record.get("Country").equals(country))
            {
                info = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                flag = 1;
                break;
            }
        }
        if (flag == 0)
        {
            info = "NOT FOUND";
        }

        return info;
    }

    void listExportersTwoProducts(CSVParser parser, String exportItem1 , String exportItem2)
    {
        String export;
        for (CSVRecord record: parser){
            export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }

    int numberOfExporters(CSVParser parser, String exportItem)
    {
        String export;
        int count = 0;
        for (CSVRecord record: parser){
            export = record.get("Exports");
            if (export.contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }

    void bigExporters(CSVParser parser, String value)
    {
        String valuee;
        double two;
        double one;
        value = value.replace("$", "");
        value = value.replaceAll(",", "");
        two  = Double.parseDouble(value);
        for (CSVRecord record: parser){
            valuee = record.get("Value (dollars)");
            valuee = valuee.replace("$", "");
            valuee = valuee.replaceAll(",", "");
            one = Double.parseDouble(valuee);
            //System.out.println(one);

            if (one > two)
            {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    void tester(){
        FileResource fr = new FileResource();
        CSVParser parser= fr.getCSVParser();

        System.out.println(countryInfo(parser, "Germany"));
        System.out.println(countryInfo(parser, "India"));

        parser= fr.getCSVParser();
        System.out.println("Countries exporting two products ");
        listExportersTwoProducts(parser, "cotton", "flowers");

        parser = fr.getCSVParser();
        System.out.println("No. of countries exporting cocoa");
        System.out.println(numberOfExporters(parser, "cocoa"));

        parser = fr.getCSVParser();
        System.out.println("Big Exporters");
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        parsingdata p = new parsingdata();
        p.tester();
    }

}