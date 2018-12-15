package serverApp;





import serverApp.Server;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
 
public class Main {
        
    public static final ArrayList<String>CONNECTIONS_LIST= new ArrayList<>();
    public static String filePathAndName;
    public static int PORT ;
    public static int activeThreadss=0;
    
    
    final static String EXIT = ">exit";
    final static String GET_THREADS = ">threads number";
    final static String HELP =">help";
    final static String SAVE =">save";
    

	public static final Scanner systemInput = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
                CONNECTIONS_LIST.add("ss");
                //Config PORT
                System.out.println("-Enter port");
                PORT = Integer.parseInt(systemInput.nextLine());
                ServerSocket serverSocket = new ServerSocket(PORT);

                //save file path
                String fileMetaData = new Date().toString().replace(':', '_');
                System.out.println("-Enter File Path");
                String path = systemInput.nextLine();
                File fCheck = new File(path);
                while(!fCheck.exists()){
                    System.out.println("file path dos not excist");
                      path = systemInput.nextLine();
                      fCheck = new File(path);
                }
                
	        filePathAndName = path +"/"+fileMetaData+"/";
                System.out.println("SERVER STARTED");
                //--------------------------------------
                
                
       
           
           
           
           Thread saveData = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PrintStream out = new PrintStream(new File(filePathAndName+"log.txt"));
                            for (String str : CONNECTIONS_LIST) {
                                out.println(str); out.flush();
                            }
                            out.close();
                            System.out.println("------LOGS ARE SAVED-----");
                        } catch (FileNotFoundException ex) {
                            System.out.println("! Can`t save file !");
                        }   }
                }); //save data thread is call when you type 'save' in cmd
         
         
         
         
         new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // COMAND THREAD
                        while(true){
                            String comand = systemInput.nextLine();
                            switch(comand){
                                case EXIT : System.out.println("SYSTEM SHUTDOWN");System.exit(0);
                                break;
                                case GET_THREADS : System.out.println("Active Threads - "+activeThreadss);
                                break;
                                case HELP : System.out.println(GET_THREADS + " - use to show active threads");
                                System.out.println(EXIT + " - use to shotdown he server");
                                break;
                                case SAVE : saveData.start();
                                break;
                                default:System.out.println("Invalid command use 'help' ");
                                break;
                            }
                        }      }
                }).start();
         
          
         
         
         
         
	  while(true) {	 //CONNECTION THREDS
             Socket s1;
             if( (s1 = serverSocket.accept())!= null) {
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       Main.activeThreadss++;
                       Server s = new Server(s1);
                   }
               }).start();	       
             }
	  }
          
          
          
	}
        
        
 
}
