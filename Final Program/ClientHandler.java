import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    final String name;
    DataOutputStream dosForRecvClient = null;
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, String name)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        this.name = name;
        if(DataOutputStreamForRecvClient.dosForRecvClient!=null)
        {
            this.dosForRecvClient = DataOutputStreamForRecvClient.dosForRecvClient;
        }
    } 
  
    @Override
    public void run()  
    { 
        String received; 
        String toreturn; 
        while (true)  
        { 
            try { 


                if(!name.equals("Client3"))
                {
                    dos.writeUTF("What do you want?[Date | Time]..\n"+ 
                            "Type Exit to terminate connection."); 
                  
                // receive the answer from client 
                received = dis.readUTF(); 
                System.out.println("Received - "+received);
                  
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // creating Date object 
                Date date = new Date(); 
                  
                // write on output stream based on the 
                // answer from the client 
                switch (received) { 
                  
                    case "Date" : 
                        toreturn = fordate.format(date);
                        if(DataOutputStreamForRecvClient.dosForRecvClient!=null)
                        {
                            this.dosForRecvClient = DataOutputStreamForRecvClient.dosForRecvClient;
                            dosForRecvClient.writeUTF(toreturn);
                            dos.writeUTF("Sent to the recv client");
                            DataOutputStreamForRecvClient.times++;
                        }
                        else{
                            dos.writeUTF("There is no Recv Client. Wait for sometime - "+toreturn);
                            DataOutputStreamForRecvClient.times++;
                        }  
                        break; 
                          
                    case "Time" : 
                        toreturn = fortime.format(date); 
                        if(DataOutputStreamForRecvClient.dosForRecvClient!=null)
                        {
                            this.dosForRecvClient = DataOutputStreamForRecvClient.dosForRecvClient;
                            dosForRecvClient.writeUTF(toreturn);
                            dos.writeUTF("Sent to the recv client");
                            DataOutputStreamForRecvClient.times++;
                        }
                        else{
                            dos.writeUTF("There is no Recv Client. Wait for sometime - "+toreturn);
                            DataOutputStreamForRecvClient.times++;
                        }  
                        break; 
                          
                    default: 
                        dos.writeUTF("Invalid input"); 
                        break; 
                } 
            }
                }
                // Ask user what he wants 
                 catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 