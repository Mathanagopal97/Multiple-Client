import java.io.*;  
import java.util.*; 
import java.net.*; 
  
// Server class 
public class Server  
{ 
    public static void main(String[] args) throws IOException  
    { 
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(5056);
        ArrayList<ListOFClients> listOfClients = new ArrayList<ListOFClients>(); 
          
        // running infinite loop for getting 
        // client request 
        while (true)  
        { 
            Socket s = null; 
              
            try 
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 
                  
                System.out.println("A new client is connected : " + s); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

                String nameOfClient = dis.readUTF();

                if(nameOfClient.equals("Client3"))
                {
                    System.out.println("Came inside the if condition");
                    DataOutputStreamForRecvClient.dosForRecvClient = dos;
                    System.out.println("DataOutputStreamForRecvClient.dosForRecvClient = "+DataOutputStreamForRecvClient.dosForRecvClient);
                    dis=null;
                }
                  
                System.out.println("Assigning new thread for this client named - "+nameOfClient); 
  
                // create a new thread object 
                ListOFClients lOf = new ListOFClients();
                ClientHandler clientHandler = new ClientHandler(s, dis, dos, nameOfClient);
                lOf.clientHandler = clientHandler;
                lOf.name = nameOfClient;
                Thread t = clientHandler ;

                listOfClients.add(lOf);

                // clientHandler.setDosForClientThree(lOf);
                //For this to work the client3 must connect first
  
                // Invoking the start() method 
                t.start(); 
                  
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace();
                ss.close(); 
            } 
        } 
    } 
}  
