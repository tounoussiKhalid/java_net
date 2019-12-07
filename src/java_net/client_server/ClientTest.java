package java_net.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {
	
	public static void main(String[] args) {
		
		try {
			Socket cs = new Socket( "localhost", 4000);
						
			PrintWriter out = new PrintWriter( cs.getOutputStream(), true  );
			
			Scanner in = new Scanner ( cs.getInputStream() );
			
			Scanner scanner = new Scanner ( System.in );
			
			while ( scanner.hasNextLine() )
			{
				String msg = scanner.nextLine();
				out.println( msg );
				System.out.println( in.nextLine() );
			}
			
			
			scanner.close();
			out.close();
			in.close();
			
			cs.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}