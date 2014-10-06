package rmilab.client;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
	      Scanner in = new Scanner(System.in);
	      String request;
	      System.out.println("Enter your request");
	      System.out.println("<object-name> <registry-host> <registry-port> <service-name>");
	      request = in.nextLine();
	      String[] parts = request.split(" ");
	      String initialClassName = parts[0];
	      String registryHost = parts[1]; 
	      String registryPort = parts[2];
	      String serviceName = parts[3];
	      // get registry
	      // do lookup
	      // localise ror
	      // print return value
	}	
}
