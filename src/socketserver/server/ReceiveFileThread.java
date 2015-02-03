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
 * socket�ֽ����Ĺ���
 * ǰ8���ֽ�Ϊ���ݿ������Ϣ�Ĵ�С��ʾ
 * ����8���ֽ�Ϊ�ļ���С�ı�ʾ
 * Ȼ�����ݿ���Ϣ��
 * ����ļ�������
 */

/*
 * @Description�����ļ������߳�
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
	String MD5 = ""; //�ļ�У������
	String FaxID ="";//���ݿ��ж�ȡ�����ģ���־�ļ�������������Ϊ�ļ�����һ����
	String TaskID = "";
	
	//�����ʽ
	String ENCODE_CHARSETNAME = "gbk";
	public ReceiveFileThread(Socket s) throws IOException
	{
		SocketServer.log.info("�µĿͻ���"+s+"���������������һ���µ��ļ������߳�....");
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
//			SocketServer.log.info(s+"���ڽ����µ��ļ��У������ľ���...");
//		 //  	int tmp = dis.readInt(); //just for test		
//			
//			//�������ݿ��¼���ݵĴ�С
//		   	SocketServer.log.info(s+"ͷ����Ϣ��ǰ�˸��ֽ�Ϊ���ݿ���Ϣ�Ĵ�С�����£�");
//		   	byte tmpDBLen[] = new byte[8];
//			int dbLen = 0;
//			long headStartTime = System.currentTimeMillis();
//		   	dis.read(tmpDBLen);
//		   	//System.out.println(new String(tmpDBLen));
//		   	dbLen = ReceiveFileThread.StringToInt(new String(tmpDBLen,ENCODE_CHARSETNAME));
//		   	SocketServer.log.info(s+"���յ������ݼ�¼���ַ���ϢΪ��"+new String(tmpDBLen,ENCODE_CHARSETNAME));
//		 	SocketServer.log.info(s+"ת�����Int���ʹ�СΪ��  "+dbLen);
//		 	
//			//�ļ���С
//			SocketServer.log.info(s+"ͷ����Ϣ�ĺ�˸��ֽ������ļ��Ĵ�С�����£�");
//			int fileSize = 0;
//			byte tmpFileSize[] = new byte[8];
//			dis.read(tmpFileSize);
//			fileSize = ReceiveFileThread.StringToInt(new String(tmpFileSize,ENCODE_CHARSETNAME));
//			SocketServer.log.info(s+"���յ����ļ���С���ַ���ϢΪ��"+new String(tmpFileSize,ENCODE_CHARSETNAME));
//		 	SocketServer.log.info(s+"ת�����Int���ʹ�СΪ��  "+fileSize);
//		 	long headEndTime = System.currentTimeMillis();
//		 	SocketServer.log.info(s+"ͷ����Ϣ��ʱ��"+(headEndTime-headStartTime)+"����");
//			/*
//			 * ��ʼ�������ݿ�����
//			 */
//			SocketServer.log.info(s+"���ڽ������ݿ���Ϣ��....");
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
//			SocketServer.log.info(s+"���ݿ��������£�");
//			SocketServer.log.info(s+"dbBuff="+new String(dbBuff,ENCODE_CHARSETNAME));
//			SocketServer.log.info(s+"���ݿ���Ϣ�������....��");
//			long dbEndTime = System.currentTimeMillis();
//			SocketServer.log.info(s+"���ݿ���Ϣ��ʱ��"+(dbEndTime-dbStartTime)+"����");
//			/*
//			 * �������ݿ�����
//			 */
//			SocketServer.log.info(s+"��ʼ�������ݿ���Ϣ������FaxDBRecord����....");
//			FaxDBRecord record = parseDBInfo(new String(dbBuff,ENCODE_CHARSETNAME));	
//			if(record != null)
//			{
//				isParse = true;
//				this.TaskID = record.getTaskID();
//				SocketServer.log.info(s+"TaskID="+record.getTaskID());
//				SocketServer.log.info(s+"MD5���ݣ�"+MD5);
//			}
//			//System.out.println(MD5);
//			/*
//			 * ��ʼ�����ļ�
//			 */
//			//savePath = tmp+".doc";
//			long fileStartTime = System.currentTimeMillis();
//			if(isParse)
//			{
//					SocketServer.log.info(s+"�������ݿ���ֶ�TaskID��FaxTaskType�ֶζ��ļ������ɹ���....");
//					savePath = generateFileName(record.getTaskID(),record.getFaxTaskType());
//					SocketServer.log.info(s+"�ļ�·��savePath="+savePath);
//					if( savePath != "")
//					{
//						isRightPath = true;
//						fos = new FileOutputStream(new File(savePath));
//						int perReadSize = 0;
//						byte[] fileBuffer = new byte[BUF_SIZE];
//						int readSize,totalReadSize = 0;
//						SocketServer.log.info(s+"��ʼ�����ļ���Ϣ....");
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
//						SocketServer.log.info(s+"�����ļ���Ϣ��ɣ�");
//						
//					}
//					else
//					{
//						isRightPath = false;
//						SocketServer.log.info(s+"�����ļ�·������");
//					}	
//			}
//			long fileEndTime = System.currentTimeMillis();
//			SocketServer.log.info(s+"�ļ����պ�ʱ��"+(fileEndTime-fileStartTime)+"����");
//			/*while ((length = dis.read(inputByte, 0, inputByte.length)) > 0)
//			{  
//                fos.write(inputByte, 0, length);  
//                fos.flush();      
//            }*/
//          
//			/*
//			 * MD5�ļ�У��
//			 */
//			boolean isCheck = false;
//			if(isRightPath)
//			{
//				 SocketServer.log.info(s+"��ʼУ���ļ�����....");
//				 isCheck = md5Check(new File(savePath),MD5);
//				 SocketServer.log.info(s+"�ļ�MD5У����ΪisCheck="+isCheck);
//			}
//			 /*
//			  * ��¼д�����ݿ�
//			  */ 
//			 long dbOperStartTime = System.currentTimeMillis();
//			 int nResult = 0;//Ĭ�ϲ������ݼ�¼Ϊ0��
//			 boolean isWriteDB = false;
//			 if(isCheck && isRightPath)
//			 {
//					//TODO
//				 SocketServer.log.info(s+"У���ļ���ȷ����һ���������ݿ����...");
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
//					 SocketServer.log.info(s+"���ݿ�ɹ�������"+nResult+"����¼");
//				 }
//				 else
//				 {
//					 isWriteDB = false;
//					 SocketServer.log.info(s+"���ݿ�����¼ʧ�ܣ����Ϊ��"+nResult);
//				 }
//			 }
//			 long dbOperEndTime = System.currentTimeMillis();
//			 SocketServer.log.info(s+"���ݿ������ʱ��"+(dbOperEndTime-dbOperStartTime)+"����");
//			/*
//			 * ��Ӧ��Ϣ
//			 */
//			 if( isWriteDB && isCheck && isRightPath && isParse)
//			 {
//				  //�ļ�У��ɹ��������ݿ����ɹ�������Ӧ�ɹ�
//				    SocketServer.log.info(s+"0000:���ճɹ��������ݿ��������ɹ�����ͻ�����Ӧ�ɹ���־0000....");
//				    //System.out.println(responseSuccessCode.length());
//				    //System.out.println(responseSuccessCode.getBytes().length);
//					
//				    //dos.write(responseSuccessCode.getBytes());
//					String tmpString = "0000:���ճɹ����뷢�����кŵ��忨!";
//					String responseString = mergeString(ResponseCode.responseSuccessCode,this.TaskID,tmpString);
//					SocketServer.log.info(s+"��Ӧ��Ϣͷ����"+int2String2(stringToByte(responseString).length));
//					SocketServer.log.info(s+"��Ӧ��ϢΪ��"+responseString);
//					dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(responseString.getBytes());
//					dos.flush();
//					//TODO
//			 }
//			  if(!isWriteDB && isRightPath && isCheck && isParse)
//			 {
//				 //�ļ�У��ʧ�ܣ��������д���
//				   SocketServer.log.error(s+"�����ļ��������ݿ�����з�������");
//				  
//				 //  dos.write(this.responseJiaoYanErrorCode.getBytes());
//				   //SocketServer.log.error(s+"0003:δ֪���󣬿����Ǵ�������г������⣬�������жϻ��߿ͻ��˳����նˣ������������ݲ�������");
//					//dos.write(this.responseUnknowErrorCode.getBytes());
//					String tmpString = "0005:���ݿ���ִ��д�������ʱ�򣬷�������!";
//					String responseString = mergeString(ResponseCode.responseWriteDBErrorCode,this.TaskID,tmpString);
//					 SocketServer.log.error(s+"��Ӧ��ϢΪ��"+responseString);		
//					 dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//					//dos.write(int2String(stringToByte(tmpString).length).getBytes());
//					//dos.write(stringToByte(tmpString));
//				   //TODO
//			 }
//			 if(!isWriteDB && !isCheck && isRightPath && isParse) //����У�����
//			 {
//				    SocketServer.log.error(s+"0002���ļ�У������з��������ļ�������������ݷ�������");
//				    String tmpString = "0002:У��MD5����,���ļ����ݴ�����չ����г��ִ���";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				    String responseString = mergeString(ResponseCode.responseJiaoYanErrorCode,this.TaskID,tmpString);
//				    SocketServer.log.error(s+"��Ӧ��ϢΪ��"+responseString);
//				    dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 if(!isWriteDB && !isCheck && !isRightPath && isParse) //·������
//			 {
//				     SocketServer.log.error(s+"0006:�ļ��洢�Ĺ��̷���������·�������ɹ���");
//				    String tmpString = "0006:�ļ��洢�Ĺ��̷���������·�������ɹ���";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				     String responseString = mergeString(ResponseCode.responseSaveFileErrorCode,this.TaskID,tmpString);
//				     SocketServer.log.error(s+"��Ӧ��ϢΪ��"+responseString);
//				     dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 if(!isWriteDB && !isCheck && !isRightPath && !isParse)
//			 {
//				    SocketServer.log.error(s+"0004�����ݿ���Ϣ��������");
//				    String tmpString = "0004:���ݿ���Ϣ���������г���";
//				   //dos.write(this.responseJiaoYanErrorCode.getBytes());
//				    String responseString = mergeString(ResponseCode.responseParseErrorCode,this.TaskID,tmpString);
//				    SocketServer.log.error(s+"��Ӧ��ϢΪ��"+responseString);
//				    dos.write((int2String2(stringToByte(responseString).length)+responseString).getBytes());
//					//dos.write(int2String2(stringToByte(responseString).length).getBytes());
//					//dos.write(stringToByte(responseString));
//			 }
//			 long totalEndTime = System.currentTimeMillis();
//			 SocketServer.log.info(s+"�����ļ��������:"+(totalEndTime-totalStartTime)+"����");
//		}
//		catch(IOException e)
//		{
//			try 
//			{
//				SocketServer.log.error(s+"0007:������̴��󣬿����Ǵ�������г������⣬�������жϻ��߿ͻ��˳����ն˵�");
//				
//				//dos.write(this.responseUnknowErrorCode.getBytes());
//				String tmpString = "0007:��������д��󣬿����Ǵ�������г������⣬�������жϻ��߿ͻ��˳����ն˵�";
//				String responseString = mergeString(ResponseCode.responseTransferErrorCode,this.TaskID,tmpString);
//				 SocketServer.log.error(s+"��Ӧ��ϢΪ��"+responseString);
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
//				SocketServer.log.error(s+"��Ӧ���̷������󣬿��ܿͻ��˶Ͽ�����");
//				SocketServer.log.error(s+":"+e1);
//				//System.out.println("��Ӧ���̷�������");
//				//e1.printStackTrace();
//			}
//			SocketServer.log.error(s+"�ļ����չ����п��ܳ��ִ���....");
//			SocketServer.log.error("ERROR:"+e);
//			//System.out.println("�ļ����չ����з����쳣��"+e);
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
//	            SocketServer.log.info("*************************************************�˳�***********************************************");
//	            //SocketServer.log.info("");
//			}
//			catch(IOException e1)
//			{
//				SocketServer.log.error(s+"�ر�socketʱ�����쳣");
//				SocketServer.log.error(s+":"+e1);
//				//System.out.println("�ر�socketʱ�����쳣");
//				//e1.printStackTrace();
//			}
//		}
	}
	
	 public String mergeString(String errorCode,String taskID,String errorMsg)
	 {
		 return errorCode+"#"+taskID+"#"+errorMsg;
	 }
	/*
	 * @Description ʱ���ʽת��
	 * @param
	 * @return yyyyMMdd_HHmmss��ʽ��ʱ��
	 */
	 public static String getDate(String format)
	 {  
		 SimpleDateFormat df = new SimpleDateFormat(format);  
	      return df.format(new Date());  
	 }   
	 /*
	  * @Description�������ݿ��¼��Ϣ
	  * @param dbInfo ���յ����ݿ������Ϣ������MD5��
	  * @return �������ݿ��¼�Ķ���
	  */
	 public FaxDBRecord parseDBInfo(String dbInfo)
	 {
		 if(dbInfo == null)
		 {
			 System.out.println("��ϢΪ�գ���ȷ�Ͻ��յ����ݿ���Ϣ");
			 return null;
		 }
		String[] infoList = dbInfo.split("#");
		if(infoList.length != 7)
		{
			System.out.println("��������ݿ���Ϣ����ȱ�ٻ��߶����ֶ�");
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
		 * @Description MD5У��
		 * @param file ҪУ����ļ�
		 * @param md5String ��ȷ��MD5�ο�
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
				byte[] buffer = new byte[1024]; //�����ļ���С������������С
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
				SocketServer.log.error(s+"У��MD5����...");
				SocketServer.log.error(e);
				throw new IOException("У��MD5����", e);
				
			}
			finally
			{
				try 
				{
					is.close();
				}
				catch (Exception e)
				{
					SocketServer.log.error(s+"У������У��ر��ļ���������");
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
				SocketServer.log.error(s+"��֧�ֵı����ʽ������Ĭ�ϵı����ʽ");
				SocketServer.log.error(s+"ERROR:"+e);
				//System.out.println("��֧�ֵı����ʽ������Ĭ�ϵı����ʽ");
				//e.printStackTrace();
			}
			return charSetName.getBytes();//ϵͳĬ�ϵı����ʽ
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

		//int ת��Ϊ8�ֽڵ��ַ�
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

