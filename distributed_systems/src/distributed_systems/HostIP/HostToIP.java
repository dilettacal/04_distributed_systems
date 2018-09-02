package distributed_systems.HostIP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostToIP {

	public static void main(String[] args) {
		if (args.length != 0) {
			try {
				String input = args[0];
				System.out.print("--------------------------" + System.getProperty("line.separator"));
				InetAddress inetAddress = InetAddress.getByName(input);
				System.out.println("IP-Adresse: " + inetAddress);
				System.out.print("--------------------------" + System.getProperty("line.separator"));
				System.out.println("Host-Name: " + inetAddress.getHostName());
				System.out.print("--------------------------" + System.getProperty("line.separator"));
			} catch (UnknownHostException e) {
				System.out.println("Host nicht gefunden: " + e.getMessage());
			}

		} else {
			System.out.println("Please provide a valid name!");
		}
	}
}
