package prototipo;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class Test_IP {
	  public static InetAddress ip;

	public String getIp() {
		return ip.getHostAddress();
	}

	public void setIp() {
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
		
	}
	  

}