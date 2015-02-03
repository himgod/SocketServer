package socketserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import socketserver.config.Configure;

public class SocketServer
{
	public static Logger log = Logger.getLogger("socketserver");

	public static void main(String[] args) throws IOException
	{
		PropertyConfigurator.configure( "./configure/log4j.properties " );
		ServerSocket server = new ServerSocket(Configure.getPortConfig());
		
		SocketServer.log.info("SocketServer is starting..."+server);
		while(true)
		{
			Socket s;
			try 
			{
				s = server.accept();
				new Thread(new ReceiveFileThread(s)).start();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				SocketServer.log.error("服务器发生异常，请确认连接");
				SocketServer.log.error(e);
				//e.printStackTrace();
			}
		}
	}
}
