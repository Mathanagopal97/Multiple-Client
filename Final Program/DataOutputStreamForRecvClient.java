import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
class DataOutputStreamForRecvClient{

    static DataOutputStream dosForRecvClient = null;
    static int times = 0;

    public static int getTimes() {
        return times;
    }

    public static void setTimes(int times) {
        DataOutputStreamForRecvClient.times = times;
    }
}