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
	
	int handleMathematicalOperation( int a , char operand, int b )
	{
		int res = 0 ;
		
		System.out.println( "a ="+ a + " operand :"+operand +" b ="+b);
		
		try {
			switch ( operand )
			{
				case '+' : res =  a + b; break;
				case '-' : res =  a - b; break;
				case '*' : res =  a * b; break;
				case '/' : res =  a / b; break;
			}
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
	
	int extractingParts ( String msg )
	{

		String without = msg.trim().replaceAll("\\s+", " ");
		System.out.println(" beg " +  msg  + " end " + without);
		
		String[] parts = without.split(" "); 
		
		int a = Integer.valueOf( parts[0].trim() );
		
		int b = Integer.valueOf( parts[2].trim() );
		
		char operand = parts[1].trim().charAt( 0 );
		
		int res = handleMathematicalOperation( a, operand , b );
		
		System.out.println( "res " + res );
		
		return res; 
	}
	
	@Override
	public void run() {
		Scanner in = null;
		PrintWriter out = null;

		while ( ! sock.isClosed() )
		{
			
			try
			{
				 in = new Scanner( sock.getInputStream() );
				 out = new PrintWriter( sock.getOutputStream() , true );

				 while ( in.hasNextLine() )
				{
					int msg =  extractingParts( in.nextLine() ) ;
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
