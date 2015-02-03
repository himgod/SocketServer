package socketserver.db;
import socketserver.config.Configure;
import socketserver.db.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FaxInsertDB 
{
	private static String driver = ""; //oracle.jdbc.driver.OracleDriver
	//private static String Url = "jdbc:oracle:thin:@192.168.118.192:1521:ora10g";
	private static String Url = "";
	private static String loginName = "";
	private  static String loginPassword ="";
	public static int insertDB(FaxDBRecord record)
	{
		int nRecord = -1;
		Connection con = null;
		Statement stmt = null;
		//DataBaseInfo dbInfo = BankStarter.getBankConfig().getDbInfo();
		ArrayList<String> dbInfo = Configure.getDBInfoConfig();
		driver = dbInfo.get(0);
		Url = dbInfo.get(1);
		loginName = dbInfo.get(2);
		loginPassword = dbInfo.get(3);
		con = JDBCUtil.getConnection(driver,Url,loginName,loginPassword);
		if( con == null )
		{
			System.out.println("�������ݿ����Ӵ�����鿴��ص�����˵��");
			return 0;
		}
		String insertSQL = "";
		String querySQL = "";
		try 
		{
			//TODO
			stmt = con.createStatement();
			querySQL = "select * from TDBI_FAXOUT where TASK_ID = '"+record.getTaskID()+"';";
			insertSQL = "insert into  TDBI_FAXOUT (TASK_ID,MOULDINGNAME,FAXID,FAXTASKTYPE,FAXCREATEDATETIME," +
					"			FAXNUMBER,FAXRETRYINTERVAL) values('"
							+record.getTaskID()+"','"+record.getModuleName()+"','"+record.getFaxID()+"','"
							+record.getFaxTaskType()+"','"+record.getFaxCreateDateTime()+"','"+record.getFaxNumber()+"','"
							+record.getFaxRetryInterval()+"');";
			//ResultSet rs = stmt.executeQuery(querySQL);
			//System.out.println(rs.next());
			//if(rs.next())
			{
				nRecord = stmt.executeUpdate(insertSQL);
			}
			if( nRecord <= 0)
			{
				System.out.println("�������ݼ�¼ʱ����������");
				return 0;
			}
			else
			{
				System.out.println("�ɹ�����һ�������¼");
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				stmt.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			JDBCUtil.freeConnection(con);
		}
		return nRecord;
	}
}

