import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
	private PrintWriter mOut;
	 private BufferedReader mIn; 
		PrintStream printStream;
	HashMap<String, DataOutputStream> clients;
	HashMap<String, String> clients2;
	int a;
private ServerSocket ServerSocket = null;
private ServerSocket ServerSocket2 = null;
String temp2=null;
Queue que ;
int otpA;
int otpB;
int otpC;
int otpD;
public static void main(String[] args){
	new OTP();
	new Server().start();
}
public Server() {
	que= new LinkedList();
	clients = new HashMap<String, DataOutputStream>();
	clients2 = new HashMap<String, String>();
	Collections.synchronizedMap(clients);
}
private void start() {
	int port =9999; 

	Socket socket =null;

	try 
	{
		ServerSocket = new ServerSocket(port);
	
		System.out.println("접속대기중(아두이노)");
	while(true) {
			socket = ServerSocket.accept();
			InetAddress ip = socket.getInetAddress();
			System.out.println(ip + " connected");
			new MultiThread(socket).start();
			}
		
	}catch(IOException e) {
		System.out.println(e);
		
			
		}
	}
	

class MultiThread extends Thread {
	Socket socket = null ;
	
	String mac = null; 
	String msg = null;
	
	DataInputStream input;
	DataOutputStream output;
	
	public MultiThread(Socket socket) {
		this.socket = socket;
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			   mIn = new BufferedReader(
	                    new InputStreamReader(input));
			 mOut = new PrintWriter(socket.getOutputStream());
		}catch(IOException e ) {
			
		}
	}
	
	public void run() {
		
				try {
					
				
					mac = mIn.readLine();
					System.out.println("접속 " +mac);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
		while(mac != null) {
		
			String temp;
			try {
				temp = null;
				System.out.println(mac);
				if(Integer.parseInt(mac)==9) {
					mOut.print(8);
					
		            mOut.flush();
		            System.out.println("전송");
					String  com =null;
					String  com1 =null;
					com=mIn.readLine();
					com1=mIn.readLine();
					System.out.println(com);
					System.out.println(com1);
			
		
					 otpA= new OTP().OTP(com,1);
					 otpB = new OTP().OTP(com,0);
					 otpC= new OTP().OTP(com,-1);
					 otpD= new OTP().OTP(com,-2);
					
					System.out.println("1분뒤 : "+otpA);
					System.out.println("0분뒤 : "+otpB);
					System.out.println("1분전 : "+otpC);
					System.out.println("2분전 : "+otpD);
					
					if(otpA==Integer.parseInt(com1) || otpB==Integer.parseInt(com1) || otpC==Integer.parseInt(com1) ||otpD==Integer.parseInt(com1)) {
						if(Integer.parseInt(com1)==otpA) {
							System.out.println("서버가 1분 느림");
							OTP.y=1;
						}
						else if(Integer.parseInt(com1)==otpB){
							System.out.println("서버 시간 동일");
							OTP.y=0;
						}
						else if(Integer.parseInt(com1)==otpC){
							System.out.println("서버 1분 빠름");
							OTP.y=-1;
							
						}
						else if(Integer.parseInt(com1)==otpD){
							System.out.println("서버 2분 빠름");
							OTP.y=-1;
							
						}
						mOut.print(7);
						 mOut.flush();
						System.out.println("일치");
					}
					
					break;
				}
//			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	}
		
		


		}
		
	}
	




