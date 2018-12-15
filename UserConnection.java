package serverApp;


/*
     .....................................No comment..................................
*/
import serverApp.Main;
import serverApp.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;


public class UserConnection {
    protected Scanner recognizer ;
    protected Socket socket;
    protected FileInputStream in;
    protected FileOutputStream out;
	
	protected UserConnection(Socket s) {
		  
	   try {
		   socket = s;
			   
		   
		   System.out.println("------SERVER CONNECTED------");
		   if(socket.isConnected()) {
			   recognizer = new Scanner(socket.getInputStream());
                           String singal = recognizer.nextLine();
                            if(singal.equals("1")){
                                getData();
                            }else{
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
                in = (FileInputStream) socket.getInputStream();
		out = new FileOutputStream(new File(Main.filePathAndName));
		int count;
		byte[] buffer = new byte[1024]; // or 4096, or more
		while ((count = in.read(buffer)) > 0) {		
		  out.write(buffer, 0, count);
		  out.flush();
		}
		
		
		socket.close();
		in.close();
		out.close();
		 
	  } catch (IOException e) {
	      e.printStackTrace();             
	  }
		
	 
	     Main.activeThreadss--;
	}
	
	
        private void sendData(){
            
             Main.activeThreadss--;
        }
        
        
}
