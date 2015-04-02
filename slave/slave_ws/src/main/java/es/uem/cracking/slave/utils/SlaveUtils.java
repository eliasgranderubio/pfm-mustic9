package es.uem.cracking.slave.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11Protocol;

import es.uem.cracking.name_server.ws.NameServerWS;
import es.uem.cracking.name_server.ws.NameServerWS_Service;
import es.uem.cracking.name_server.ws.RegisterRequest;


/**
 * 
 * Slave utils
 * 
 * @author egrande
 *
 */
public class SlaveUtils {

	/** Public name server endpoint */
	// TODO egrande: move this name server endpoint to a properties file
	public static final String NAME_SERVER_ENDPOINT = "http://masterandnameserver.ddns.net:7001/name_server/Name_Server_WS";
	
	
	// Public methods
	
	/**
	 * Registers this slave in the name server and returns true if was possible
	 */
	public static boolean registerMyselfInTheNameServer() {
		RegisterRequest request = new RegisterRequest();
		request.setIp(getIp());
		request.setPort(getTomcat7Port());
		request.setProcessorName(getProcessorName());
		if(request.getIp()!=null && request.getPort()>0 && request.getProcessorName()!=null) {
			try {
				NameServerWS_Service nsws_service = new NameServerWS_Service(new URL(NAME_SERVER_ENDPOINT));
				NameServerWS ns = nsws_service.getNameServerWSPort();
				return ns.register(request).isRegistered();
			} catch(Exception ex) {
				ex.printStackTrace(System.err);
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * Gets my listen port in Tomcat 7. Returns zero if not possible
	 */
	public static int getTomcat7Port() {
		try {
			MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
			ObjectName name = new ObjectName("Catalina", "type", "Server");
			Server server = (Server) mBeanServer.getAttribute(name, "managedResource");
	        Service[] services = server.findServices();
	        for (Service service : services) {
	            for (Connector connector : service.findConnectors()) {
	                ProtocolHandler protocolHandler = connector.getProtocolHandler();
	                if (protocolHandler instanceof Http11Protocol) {            
	                    return connector.getPort();
	                }
	            }
	        }
	        return 0;
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			return 0;
		}
	}
	
	
	/**
	 * Gets my IP as String. Returns null if not possible
	 */
	public static String getIp() {
		try {
			List<String> ips = new ArrayList<String>();
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()){
			    NetworkInterface current = interfaces.nextElement();
			    if (current.isUp() && !current.isLoopback() && !current.isVirtual()) {
			    	Enumeration<InetAddress> addresses = current.getInetAddresses();
				    while (addresses.hasMoreElements()){
				        InetAddress current_addr = addresses.nextElement();
				        if (!current_addr.isLoopbackAddress() && current_addr instanceof Inet4Address) {
				        	ips.add(current_addr.getHostAddress());
				        }		     
				    }
			    }
			}
			// TODO egrande: review this ip resolution
			return ips.get(0);
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			return null;
		}
	}
	
	
	/**
	 * Gets my processor name as String. Returns null if not possible
	 */
	public static String getProcessorName() {
		try {
			if(System.getProperty("file.separator").equals("/")) {
				if(System.getProperty("os.name").equals("Linux")) {
					// Linux
					Runtime rt = Runtime.getRuntime();
		            String[] cmd = { "/bin/sh", "-c", "cat /proc/cpuinfo | grep 'model name' | uniq | awk -F':' '{print $2}' | sed 's/^.//' " };
		            Process proc = rt.exec(cmd);
		            proc.waitFor();
		            BufferedReader is = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		            String line = is.readLine();
		            return line;		       
				}
				else {
					// MAC
					Runtime rt = Runtime.getRuntime();
		            String[] cmd = { "/bin/sh", "-c", "sysctl -n machdep.cpu.brand_string" };
		            Process proc = rt.exec(cmd);
		            proc.waitFor();
		            BufferedReader is = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		            String line = is.readLine();
		            return line;
				}
			}
			else {
				// Windows
				// TODO egrande: get processor name for Windows
				return null;
			}
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			return null;
		}
	}
	
}
