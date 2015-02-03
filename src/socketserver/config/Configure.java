package socketserver.config;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Configure 
{
	static String  portConfigPath = "./configure/port.txt";
	static String dbinfoConfigPath = "./configure/dbinfo.txt";
	public static ArrayList<String> getDBInfoConfig()
	{
		ArrayList<String> dbinfoList  = new ArrayList<String>();
		BufferedReader buf = null;
		int index = 0;
		String tmpString = "";
		File f = new File(dbinfoConfigPath);
		try 
		{
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			while( (tmpString = buf.readLine() )!= null )
			{
				String[] strList = tmpString.split("=");
				dbinfoList.add( strList[1]);
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("数据库配置信息文件没有找到");
			System.out.println(e);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("IO异常！");
        	System.out.println(e);
		}
		finally
		{
			if( buf != null)
        	{
        		try 
        		{
					buf.close();
				} 
        		catch (IOException e) 
        		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		}
		
		return dbinfoList;
	}
	public static int getPortConfig()
	{
		String port = "";
		 BufferedReader buf = null;
        File f=new File(portConfigPath);
        try 
        {
			//InputStream in=new FileInputStream(f);
        	 buf = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        	 port = buf.readLine();
		} 
        catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
        	System.out.println("端口配置文件没有找到！");
        	System.out.println(e);
			//e.printStackTrace();
        	return 0;
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO异常！");
        	System.out.println(e);
		}
        finally
        {
        	if( buf != null)
        	{
        		try 
        		{
					buf.close();
				} 
        		catch (IOException e) 
        		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        return Integer.parseInt(port);
	}
	
}
