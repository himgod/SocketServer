package socketserver.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import socketserver.db.FaxDBRecord;
import socketserver.db.FaxInsertDB;
import socketserver.response.ResponseCode;

/*
 * socket字节流的规则：
 * 前8个字节为数据库相关信息的大小表示
 * 接着8个字节为文件大小的表示
 * 然后数据库信息流
 * 最后文件数据流
 */

/*
 * @Description接收文件处理线程
 * @Author lgx
 * @Date 2014-10-22
 * @update 2014-10-24
 */
public class ReceiveFileThread  implements Runnable 
{
	Socket s = null;
	boolean isParse = false;
	boolean isRightPath = false ;
	int BUF_SIZE = 4096;
	DataInputStream dis = null; 
	DataOutputStream dos = null;
    FileOutputStream fos = null;
    String rootDirectory = "D:"+File.separator+"FaxServer"+File.separator+"data"+File.separator+"bjpostbank";
	String savePath = "";//"E:/"+getDate()+".txt";
	String MD5 = ""; //文件校验数据
	String FaxID ="";//数据库中读取进来的，标志文件，这里用来作为文件名称一部分
	String TaskID = "";
	
	//编码格式
	String ENCODE_CHARSETNAME = "gbk";
	public ReceiveFileThread(Socket s) throws IOException
	{
		SocketServer.log.info("新的客户端"+s+"请求进来，开启了一个新的文件接收线程....");
		//SocketServer.log.info(s);
		//System.out.println(rootDirectory);
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream()); 
		System.out.println(s+"ReceiveFileThread().out.....");
	}
	@Override
	public void run() 
	{
		byte tmpByte[] = new byte[256];
		try 
		{
			dis.read(tmpByte);
			System.out.println(new String(tmpByte));
			System.out.println(new String(tmpByte).split("#")[1]);
			if(new String(tmpByte).split("#")[1].equals("LOGIN"))
			{
				dos.write("OK".getBytes());
			}
			else
			{
				String msg = "hello, SendFax() function .I have heard from you!";
				int len = msg.length();
				String buff = len + "#" + msg;
				dos.write(buff.getBytes());
				dos.flush();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				dis.close();
				s.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
//		try
//		{
//			long totalStartTime = System.currentTimeMillis();
//			SocketServer.log.info(s+"正在接收新的文件中，请耐心静候...");
//		 //  	int tmp = dis.readInt(); //just for test		
//			
//			//接收数据库记录数据的大小
//		   	SocketServer.log.info(s+"头部信息的前八个字节为数据库信息的大小，如下：");
//		   	byte tmpDBLen[] = new byte[8];
//			int dbLen = 0;
//			long headStartTime = System.currentTimeMillis();
//		   	dis.read(tmpDBLen);
//		   	//System.out.println(new String(tmpDBLen));
//		   	dbLen = ReceiveFileThread.StringToInt(new String(tmpDBLen,ENCODE_CHARSETNAME));
//		   	SocketServer.log.info(s+"接收到的数据记录的字符信息为："+new String(tmpDBLen,ENCODE_CHARSETNAME));
//		 	SocketServer.log.info(s+"转换后的Int类型大小为：  "+dbLen);
//		 	
//			//文件大小
//			SocketServer.log.info(s+"头部信息的后八个字节描述文件的大小，如下：");
//			int fileSize = 0;
//			byte tmpFileSize[] = new byte[8];
//			dis.read(tmpFileSize);
//			fileSize = ReceiveFileThread.StringToInt(new String(tmpFileSize,ENCODE_CHARSETNAME));
//			SocketServer.log.info(s+"接收到的文件大小的字符信息为："+new String(tmpFileSize,ENCODE_CHARSETNAME));
//		 	SocketServer.log.info(s+"转换后的Int类型大小为：  "+fileSize);
//		 	long headEndTime = System.currentTimeMillis();
//		 	SocketServer.log.info(s+"头部信息耗时："+(headEndTime-headStartTime)+"毫秒");
//			/*
//			 * 开始接收数据库数据
//			 */
//			SocketServer.log.info(s+"正在接收数据库信息中....");
//			long dbStartTime = System.currentTimeMillis();
//			int perReadLen = BUF_SIZE;
//			byte[] dbBuff = new byte[dbLen];
//			int readlen, totalReadLen = 0;
//			while (dbLen > 0) 
//			{
//				perReadLen = BUF_SIZE;
//				if (dbLen < BUF_SIZE) 
//				{
//					perReadLen = dbLen;
//				}
//				readlen = dis.read(dbBuff, totalReadLen, perReadLen);
//				totalReadLen += readlen;
//				dbLen -= readlen;
//			}
//			SocketServer.log.info(s+"数据库内容如下：");
//			SocketServer.log.info(s+"dbBuff="+new String(dbBuff,ENCODE_CHARSETNAME));
//			SocketServer.log.info(s+"数据库信息接收完成....！");
//			long dbEndTime = System.currentTimeMillis();
//			SocketServer.log.info(s+"数据库信息耗时："+(dbEndTime-dbStartTime)+"毫秒");
//			/*
//			 * 解析数据库数据
//			 */
//			SocketServer.log.info(s+"开始解析数据库信息，生成FaxDBRecord对象....");
//			FaxDBRecord record = parseDBInfo(new String(dbBuff,ENCODE_CHARSETNAME));	
//			if(record != null)
//			{
//				isParse = true;
//				this.TaskID = record.getTaskID();
//				SocketServer.log.info(s+"TaskID="+record.getTaskID());
//				SocketServer.log.info(s+"MD5内容："+MD5);
//			}
//			//System.out.println(MD5);
//			/*
//			 * 开始接收文件
//			 */
//			//savePath = tmp+".doc";
//			long fileStartTime = System.currentTimeMillis();
//			if(isParse)
//			{
//					SocketServer.log.info(s+"根据数据库的字段TaskID和FaxTaskType字段对文件名生成过程....");
//					savePath = generateFileName(record.getTaskID(),record.getFaxTaskType());
//					SocketServer.log.info(s+"文件路径savePath="+savePath);
//					if( savePath != "")
//					{
//						isRightPath = true;
//						fos = new FileOutputStream(new File(savePath));
//						int perReadSize = 0;
//						byte[] fileBuffer = new byte[BUF_SIZE];
//						int readSize,totalReadSize = 0;
//						SocketServer.log.info(s+"开始接受文件信息....");
//						double sumFile = fileSize;
//						while( fileSize > 0)
//						{
//							perReadSize = BUF_SIZE;
//							if( fileSize < BUF_SIZE )
//							{
//								perReadSize = fileSize;
//							}
//							SocketServer.log.info("fileSize="+fileSize);
//							readSize = dis.read(fileBuffer, 0, perReadSize);
//							SocketServer.log.info("perReadSize="+perReadSize);
//							SocketServer.log.info("ReadSize="+readSize);
//							fos.write(fileBuffer, 0, readSize);
//							//fos.flush();
//							totalReadSize += readSize;
//							fileSize -= readSize;
//							SocketServer.log.info(s+"["+(totalReadSize/sumFile)*100+"%]");
//						}
//						fos.flush();
//						fos.close();
//						SocketServer.log.info(s+"接收文件信息完成！");
//						
//					}
//					else
//					{
//						isRightPath = false;
//						SocketServer.log.info(s+"生成文件路径出错");
//					}	
//			}
//			long fileEndTime = System.currentTimeMillis();
//			SocketServer.log.info(s+"文件接收耗时："+(fileEndTime-fileStartTime)+"毫秒");
//			/*while ((length = dis.read(inputByte, 0, inputByte.length)) > 0)
//			{  
//                fos.write(inputByte, 0, length);  
//                fos.flush();      
//            }*/
//          
//			/*
//			 * MD5文件校验
//			 */
//			boolean isCheck = false;
//			if(isRightPath)
//			{
//				 SocketServer.log.info(s+"开始校验文件内容....");
//				 isCheck = md5Check(new File(savePath),MD5);
//				 SocketServer.log.info(s+"文件MD5校验结果为isCheck="+isCheck);
//			}
//			 /*
//			  * 记录写入数据库
//			  */ 
//			 long dbOperStartTime = System.currentTimeMillis();
//			 int nResult = 0;//默认插入数据记录为0条
//			 boolean isWriteDB = false;
//			 if(isCheck && isRightPath)
//			 {
//					//TODO
//				 SocketServer.log.info(s+"校验文件正确，下一步插入数据库操作...");
//				 String tmpFaxType = record.getFaxTaskType();
//				 
//				 FaxID = new File(savePath).getName();
//				 FaxID = tmpFaxType+"/" + getDate("yyyyMMdd")+"/"+FaxID;
//				 System.out.println(FaxID);
//				 record.setFaxID(FaxID);
//				 //System.out.println(record.getFaxID());
//				 nResult = FaxInsertDB.insertDB(record);
//				 if(nResult == 1)
//				 {
//					 isWriteDB = true;
//					 SocketServer.log.info(s+"数据库成功插入了"+nResult+"条记录");
//				 }
//				 else
//				 {
//					 isWriteDB = false;
//					 SocketServer.log.info(s+"数据库插入记录失败，结果为："+nResult);
//				 }
//			 }
//			 long dbOperEndTime = System.currentTimeMillis();
//			 SocketServer.log.info(s+"数据库操作耗时："+(dbOperEndTime-dbOperStartTime)+"毫秒");
//			/*
//			 * 响应消息
//			 */
//			 if( isWriteDB && isCheck && isRightPath && isParse)
//			 {
//				  //文件校验成功而且数据库插入成功，才响应成功
//				    SocketServer.log.info(s+"0000:接收成功而且数据库插入操作成功，向客户端响应成功标志0000....");
//				    //System.out.println(responseSuccessCode.length());
//				    //System.out.println(responseSuccessCode.getBytes().length);
//					
//				    //dos.write(responseSuccessCode.getBytes());
//					String tmpString = "0000:接收成功，请发送主叫号到板卡!";
//					String responseString = mergeString(ResponseCode.responseSuccessCode,this.TaskID,tmpString);
//					SocketServer.log.info(s+"响应消息头部："+int2String2(stringToByte(responseString).length));
//					SocketServer.log.info(s+"响应信息为："+responseString);
//					dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(responseString.getBytes());
//					dos.flush();
//					//TODO
//			 }
//			  if(!isWriteDB && isRightPath && isCheck && isParse)
//			 {
//				 //文件校验失败，即内容有错误
//				   SocketServer.log.error(s+"数据文件插入数据库过程中发生错误");
//				  
//				 //  dos.write(this.responseJiaoYanErrorCode.getBytes());
//				   //SocketServer.log.error(s+"0003:未知错误，可能是传输过程中出现问题，如网络中断或者客户端程序终端，服务器端数据插入错误等");
//					//dos.write(this.responseUnknowErrorCode.getBytes());
//					String tmpString = "0005:数据库在执行写入操作的时候，发生错误!";
//					String responseString = mergeString(ResponseCode.responseWriteDBErrorCode,this.TaskID,tmpString);
//					 SocketServer.log.error(s+"响应信息为："+responseString);		
//					 dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//					//dos.write(int2String(stringToByte(tmpString).length).getBytes());
//					//dos.write(stringToByte(tmpString));
//				   //TODO
//			 }
//			 if(!isWriteDB && !isCheck && isRightPath && isParse) //内容校验错误
//			 {
//				    SocketServer.log.error(s+"0002：文件校验过程中发生错误，文件传输过程中内容发生错误");
//				    String tmpString = "0002:校验MD5出错,即文件内容传输接收过程中出现错误。";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				    String responseString = mergeString(ResponseCode.responseJiaoYanErrorCode,this.TaskID,tmpString);
//				    SocketServer.log.error(s+"响应信息为："+responseString);
//				    dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 if(!isWriteDB && !isCheck && !isRightPath && isParse) //路径错误
//			 {
//				     SocketServer.log.error(s+"0006:文件存储的过程发生错误，如路径的生成过程");
//				    String tmpString = "0006:文件存储的过程发生错误，如路径的生成过程";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				     String responseString = mergeString(ResponseCode.responseSaveFileErrorCode,this.TaskID,tmpString);
//				     SocketServer.log.error(s+"响应信息为："+responseString);
//				     dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 if(!isWriteDB && !isCheck && !isRightPath && !isParse)
//			 {
//				    SocketServer.log.error(s+"0004：数据库信息解析出错");
//				    String tmpString = "0004:数据库信息解析过程中出错";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				    String responseString = mergeString(ResponseCode.responseParseErrorCode,this.TaskID,tmpString);
//				    SocketServer.log.error(s+"响应信息为："+responseString);
//				    dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 long totalEndTime = System.currentTimeMillis();
//			 SocketServer.log.info(s+"整个文件处理过程:"+(totalEndTime-totalStartTime)+"毫秒");
//		}
//		catch(IOException e)
//		{
//			try 
//			{
//				SocketServer.log.error(s+"0007:传输过程错误，可能是传输过程中出现问题，如网络中断或者客户端程序终端等");
//				
//				//dos.write(this.responseUnknowErrorCode.getBytes());
//				String tmpString = "0007:传输过程中错误，可能是传输过程中出现问题，如网络中断或者客户端程序终端等";
//				String responseString = mergeString(ResponseCode.responseTransferErrorCode,this.TaskID,tmpString);
//				 SocketServer.log.error(s+"响应信息为："+responseString);
//				 dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//				//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//				//dos.write(stringToByte(responseString));
//				//byte tmpUnknown[] = new byte[4];
//				//dos.write(int2String(tmpString.length()).getBytes());
//				//dos.write(stringToByte(tmpString));
//				//TODO
//			} 
//			catch (IOException e1) 
//			{
//				// TODO Auto-generated catch block
//				SocketServer.log.error(s+"响应过程发生错误，可能客户端断开连接");
//				SocketServer.log.error(s+":"+e1);
//				//System.out.println("响应过程发生错误");
//				//e1.printStackTrace();
//			}
//			SocketServer.log.error(s+"文件接收过程中可能出现错误....");
//			SocketServer.log.error("ERROR:"+e);
//			//System.out.println("文件接收过程中发生异常："+e);
//			//e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if (dis != null) 
//				{
//	                dis.close();  
//				}
//	            if (dos != null)  
//	            {
//	                dos.close();
//	            }
//	            if (s != null) 
//	            {
//	                s.close();
//	            }
//	            SocketServer.log.info("*************************************************退出***********************************************");
//	            //SocketServer.log.info("");
//			}
//			catch(IOException e1)
//			{
//				SocketServer.log.error(s+"关闭socket时发生异常");
//				SocketServer.log.error(s+":"+e1);
//				//System.out.println("关闭socket时发生异常");
//				//e1.printStackTrace();
//			}
//		}
	}
	
	 public String mergeString(String errorCode,String taskID,String errorMsg)
	 {
		 return errorCode+"#"+taskID+"#"+errorMsg;
	 }
	/*
	 * @Description 时间格式转换
	 * @param
	 * @return yyyyMMdd_HHmmss格式的时间
	 */
	 public static String getDate(String format)
	 {  
		 SimpleDateFormat df = new SimpleDateFormat(format);  
	      return df.format(new Date());  
	 }   
	 /*
	  * @Description解析数据库记录信息
	  * @param dbInfo 接收的数据库相关信息（包含MD5）
	  * @return 传真数据库记录的对象
	  */
	 public FaxDBRecord parseDBInfo(String dbInfo)
	 {
		 if(dbInfo == null)
		 {
			 System.out.println("信息为空，请确认接收的数据库信息");
			 return null;
		 }
		String[] infoList = dbInfo.split("#");
		if(infoList.length != 7)
		{
			System.out.println("传输的数据库信息错误，缺少或者多余字段");
			return null;
		}
		FaxDBRecord record = new FaxDBRecord();
		if(infoList[0] != "")
		{
			record.setTaskID(infoList[0]);
		}
		record.setModuleName(infoList[1]);
		record.setFaxTaskType("1");//infoList[2]);
		record.setFaxCreateDateTime(infoList[3]);
		record.setFaxNumber(infoList[4]);
		record.setFaxRetryInterval(infoList[5]);
		MD5 = infoList[6];
		
		return record;
	 }
	 
	 
	 /**
		 * 
		 * @Description MD5校验
		 * @param file 要校验的文件
		 * @param md5String 正确的MD5参考
		 * @return
		 * @throws IOException
		 *
		 */
		private boolean  md5Check(File file,String md5String) throws IOException
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
				SocketServer.log.error(s+"校验MD5出错...");
				SocketServer.log.error(e);
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
					SocketServer.log.error(s+"校验过程中，关闭文件发生错误");
					SocketServer.log.error(e);
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
			return hexString.toString().equalsIgnoreCase(md5String);
		}
		
		public String generateFileName(String faxID,String faxTaskType)
		{
			String tmpDirectory = rootDirectory+File.separator+faxTaskType+File.separator+getDate("yyyyMMdd");
			File newDirectory = new File(tmpDirectory);
			if( !newDirectory.exists() )
			{
				 newDirectory.mkdirs();
			}	
			return tmpDirectory+File.separator+faxID+"_"+getDate("yyyyMMddhhmmss")+".fax.data";
		}
		
		public byte[] stringToByte(String charSetName)
		{
			try 
			{
				return charSetName.getBytes(ENCODE_CHARSETNAME);
			}
			catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				SocketServer.log.error(s+"不支持的编码格式，返回默认的编码格式");
				SocketServer.log.error(s+"ERROR:"+e);
				//System.out.println("不支持的编码格式，返回默认的编码格式");
				//e.printStackTrace();
			}
			return charSetName.getBytes();//系统默认的编码格式
		}
		
		public static  int StringToInt(String tmp)
		{
			/*char[] tmpArray = tmp.toCharArray();
			int index = 0;
			for(int i=0;i < tmpArray.length; i++)
			{
				 if(tmpArray[i] != '0')
				 {
					 break;
				 }
			}
			int value = Integer.parseInt(tmp.substring(index));
			*/
			
			return Integer.parseInt(tmp);
		}
		
		public static String int2String(int value)
		{
			 String tmpString = String.valueOf(value);
			 while(tmpString.length() < 4)
			 {
				 tmpString = "0"+tmpString;
			 }
			 return tmpString;
		}

		//int 转换为8字节的字符
		public static String int2String2(int value)
		{
			 String tmpString = String.valueOf(value);
			 while(tmpString.length() < 8)
			 {
				 tmpString = "0"+tmpString;
			 }
			 return tmpString;
		}

		
}

