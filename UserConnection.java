package serverApp;


/*
     .....................................No comment..................................
*/
import com.callrecorder.myapplication.DataObject.DataObject;
import serverApp.Main;
import serverApp.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
 
 

public class UserConnection {
  
   private DataObject dataobj ;
   
    protected Socket socket;
    protected FileInputStream in;
    protected FileOutputStream out;
    protected ObjectOutputStream objOut;
    protected ObjectInputStream objIn;
	
	protected UserConnection(Socket s) {		  
	   try {
		   socket = s;			   		   
		   System.out.println("------SERVER CONNECTED------");		  
                   if(socket.isConnected()) {
		      objIn = new ObjectInputStream(socket.getInputStream());
                      dataobj = (DataObject) objIn.readObject();
                      if(dataobj.isSendingData() == true){
                          getData();
                      }
                      else{
                          sendData();
                      }
                            
		   }
		                 
		String c = new Date().toString()+"/INTERNET ADDRESS: "+socket.getInetAddress().toString()+" LOCAL ADDRESS: "+socket.getLocalAddress();
                System.out.println(c);
		Main.CONNECTIONS_LIST.add(c);
	   }
	   catch(Exception e) {
		   System.out.println(e.toString());
	   }
	 
	}

  
	
	
	
	
	private void getData(){
	   try {		
               
                String fileMetaData = new Date().toString().replace(':', '_');
		out = new FileOutputStream(new File(Main.filePath+"/"+fileMetaData+".mp3"));
		ArrayList<Byte> dataFromClient = dataobj.getData();
                for (Byte b : dataFromClient) {
                  out.write(b);
                  out.flush();
                }
                objIn.close();
                out.close();
                socket.close();
		            		 
	   } catch (IOException e) {
	      e.printStackTrace();             	  
           }			 
	     Main.activeThreadss--;
	}
	
	
        private void sendData(){
            System.out.println("user2");
             Main.activeThreadss--;
        }
        
        
}
