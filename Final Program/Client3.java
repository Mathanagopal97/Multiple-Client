import java.io.*; 
import java.net.*; 
  
// Client class 
public class Client3  
{ 
    public static void main(String[] args) throws IOException  
    { 
        try
        {             
            // getting localhost ip 
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 5056 
            Socket s = new Socket(ip, 5056); 
      
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Client3");
            String tosend = "";
            dos = null;
      
            // the following loop performs the exchange of 
            // information between client and client handler 
            while (true)  
            { 
                System.out.println(dis.readUTF());
                System.out.println("Total Times = "+DataOutputStreamForRecvClient.times);  
                //dos.writeUTF(tosend);
                //dos.close(); 
                  
                // If client sends exit,close this connection  
                // and then break from the while loop 
                if(tosend.equals("Exit")) 
                { 
                    System.out.println("Closing this connection : " + s); 
                    s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // printing date or time as requested by client 
                String received = dis.readUTF(); 
                System.out.println(received); 
            } 
            
              
            // closing resources 
            // scn.close(); 
            // dis.close(); 
            // dos.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 