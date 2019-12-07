package java_net.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProcessor extends Thread{
	private Socket sock;
	
	public ClientProcessor( Socket sock )
	{
		this.sock = sock;
	}
	
	@Override
	public void run() {
		System.out.println( " Lancement du traitement de la connexion cliente" );
		Scanner in = null;
		PrintWriter out = null;

		while ( true )
		{
			
			try
			{
				 in = new Scanner( sock.getInputStream() );
				 out = new PrintWriter( sock.getOutputStream() , true );

				 while ( in.hasNextLine() )
				{
					String msg =  in.nextLine().toUpperCase() ;
					out.println( msg );
				}
				
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			} 
			finally 
			{
				in.close();
				out.close();
				
			}
		}
	}

}
