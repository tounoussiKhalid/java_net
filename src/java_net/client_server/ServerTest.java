package java_net.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest extends Thread{
	
	ServerSocket ss = null ;
	Socket cs = null;

	
	public ServerTest( int port)
	{
		try {
			ss = new ServerSocket( port );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		ss.close();
		super.finalize();
		
	}

	
	public static void main(String[] args)
	{
		ServerTest st = new ServerTest( 4000 );
		st.start();
	}
	
	@Override
	public void run()
	{
		
		while ( true )
		{
			try 
			{
				System.out.println( " server is waiting ..");
				cs = ss.accept();
				
				 new ClientProcessor( cs )  .start();
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			} 	
		}
	}

}
