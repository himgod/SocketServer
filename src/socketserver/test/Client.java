package socketserver.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import socketserver.server.ReceiveFileThread;
import socketserver.server.SocketServer;
import sun.misc.BASE64Decoder;

public class Client  implements Runnable
{
	String sendFileName = "D://test.txt";
	String serverIP = "11.2.130.51";
	 int i = 0;
	 int length = 0;  
     double sumL = 0 ;  
     byte[] sendBytes = null; 
     byte[] tmpBytes = null;
     byte[] fileBytes = null;
     Socket socket = null;  
     DataOutputStream dos = null; 
     DataInputStream dis = null;
     FileInputStream fis = null;  
     String dbinfo = "";
     
     public Client(int i)
     {
    	 
    	 this.i = i;
     }
	public static byte[] long2Bytes(long num) 
	{  
	    byte[] byteNum = new byte[8];  
	    for (int ix = 0; ix < 8; ++ix) 
	    {  
	        int offset = 64 - (ix + 1) * 8;  
	        byteNum[ix] = (byte) ((num >> offset) & 0xff);  
	    }  
	    return byteNum;  
	}
	
	public void run()
	{
		  boolean bool = false;  
	        try {  
	        	//BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
	       
	            File file = new File(sendFileName); //要传输的文件路径  
	            dbinfo = returnDBInfo(file);
	            System.out.println(dbinfo);
	            int l = (int)file.length(); 
	           // l = l + 1;
	           // System.out.println(intToByte(20000).length);
	            String filename = file.getName();
	            socket = new Socket();    
	            socket.connect(new InetSocketAddress(serverIP, 12345));  
	            dos = new DataOutputStream(socket.getOutputStream());
	            dis = new DataInputStream(socket.getInputStream());
	            fis = new FileInputStream(file); 
	            
	            sendBytes = new byte[1024];  
	            tmpBytes = new byte[1024]; 
	            fileBytes = new byte[1024];
	            
	            tmpBytes =intToByte(l);
	            
	            fileBytes = filename.getBytes();
	            /*
	             * 发送数据
	             */
	            //dos.writeInt(i);
	 
	            dos.write(int2String(dbinfo.length()).getBytes());
	            
	            dos.write(int2String(l).getBytes());
	           // dos.flush();
	            dos.write(dbinfo.getBytes());
	            dos.flush();  
	         
	           while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
	           {  
	                sumL += length;    
	                System.out.println("已传输："+((sumL/l)*100)+"%");  
	                dos.write(sendBytes, 0, length);  
	                dos.flush();  
	            } 
	            
	            //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较  
	            if(sumL==l)
	            {  
	                bool = true; 
	                
	            }
	            System.out.println("wait server....");
	            byte[] tmpResponseCode = new byte[8];
	            dis.read(tmpResponseCode);
	            System.out.println(new String(tmpResponseCode));
	            
	            int responseLen = Integer.parseInt(new String(tmpResponseCode));
	            int perReadLen =1024;
				byte[] dbBuff = new byte[responseLen];
				int readlen, totalReadLen = 0;
				while (responseLen > 0) 
				{
					perReadLen = 1024;
					if (responseLen < 1024) 
					{
						perReadLen = responseLen;
					}
					readlen = dis.read(dbBuff, totalReadLen, perReadLen);
					totalReadLen += readlen;
					responseLen-= readlen;
				}
				
				System.out.println(new String(dbBuff,"gbk"));
	            /*byte[] tmpResponseCode = new byte[4];
	            dis.read(tmpResponseCode);
	            int tmpResponseMsgSize = dis.readInt();
	            
	            byte[] tmpResponseMsg = new byte[tmpResponseMsgSize];
	            dis.read(tmpResponseMsg);
	            System.out.println(new String(tmpResponseCode,"gbk"));
	            System.out.println(tmpResponseMsgSize);
	            System.out.println(new String(tmpResponseMsg,"gbk"));
	            */
	            
	        }
	        catch (Exception e) 
	        {  
	            System.out.println("客户端文件传输异常");  
	            bool = false;  
	            e.printStackTrace();    
	        } 
	        finally
	        {    
	            if (dos != null) 
	            {
	                try {
						dos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	            }
	            if (fis != null) 
	            {
	                try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}     
	            }
	            if (socket != null) 
	            {
	                try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	            }
	        }  
	        
	        System.out.println(bool?"成功":"失败");  
	    }  
	
	
	private static String  md5Check(File file) throws IOException
	{
		MessageDigest md5Digest = null;
		InputStream is = null;
		try 
		{
			md5Digest = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024]; //根据文件大小调整缓冲区大小
			int numRead = 0;
			is = new FileInputStream(file);
			while ( (numRead = is.read(buffer) ) > 0) 
			{	
				md5Digest.update(buffer,0,numRead);
			}
			is.close();
		}
		catch(Exception e)
		{
	//		SocketServer.log.error(s+"校验MD5出错...");
		//	SocketServer.log.error(e);
			throw new IOException("校验MD5出错", e);
			
		}
		finally
		{
			try 
			{
				is.close();
			}
			catch (Exception e)
			{
			//	SocketServer.log.error(s+"校验过程中，关闭文件发生错误");
				//SocketServer.log.error(e);
			}
		}
		byte[] digest = md5Digest.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++)
		{
			String hex = Integer.toHexString(0xff & digest[i]);
			if (hex.length() == 1) 
			{
				hexString.append('0');
			}
			hexString.append(hex);
		}
		System.out.println("MD5="+hexString.toString().toUpperCase());
		return hexString.toString().toUpperCase();
	}
	 public static void main(String[] args) throws IOException 
	 {  
		
		 for(int i = 0;i < 1; i++)
		 {
			//new Thread(new Client(i)).start();
		 }
		 
	 }  
	 public static byte[] intToByte(int i) {
			byte[] bt = new byte[4];
			bt[0] = (byte) (0xff & i);
			bt[1] = (byte) ((0xff00 & i) >> 8);
			bt[2] = (byte) ((0xff0000 & i) >> 16);
			bt[3] = (byte) ((0xff000000 & i) >> 24);
			return bt;
		}  
	 
	 public static String int2String(int length)
	 {
		 String tmpString = String.valueOf(length);
		 while(tmpString.length() < 8)
		 {
			 tmpString = "0"+tmpString;
		 }
		 return tmpString;
	 }
	 
	 public String returnDBInfo(File file) throws IOException
	 {
		 String dbinfo = "27800066933399911ee#500023#1#2011-8-30 8:28#87339835#60";
		 String md5Content = md5Check(file);
		 Random rand = new Random();
		 String taskID = rand.nextDouble()+"";
		 String date = getDate("yyyy-MM-dd hh:mm:ss");
		 String tmpInfo = taskID+"#086032#1#"+date+"#1110000#60#"+md5Content;
		 return tmpInfo;
		 
	 }
	 public static String getDate(String format)
	 {  
		 SimpleDateFormat df = new SimpleDateFormat(format);  
	      return df.format(new Date());  
	 }   
}


