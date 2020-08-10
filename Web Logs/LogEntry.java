import edu.duke.*;
import java.util.Date;

public class LogEntry{

    private String ipAddress;
    private Date accessTime;
    private String Request;
    private int statusCode;
    private int bytesReturned;
    
    public LogEntry(String ip, Date at, String request, int code, int bytes){
        ipAddress = ip;
        accessTime = at;
        Request = request;
        statusCode = code;
        bytesReturned = bytes;
    }

    String getIPAddress(){
        return ipAddress;
    }

    Date getAccessTime(){
        return accessTime;
    }

    String getRequest(){
        return Request;
    }

    int getStatusCode(){
        return statusCode;
    }

    int getBytesReturned(){
        return bytesReturned;
    }

    public String toString(){
        return ipAddress + " " + accessTime + " " + Request + " " + statusCode + " " + bytesReturned;
    }
}