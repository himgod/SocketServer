package socketserver.db.util;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * jdbc������
 */
public class JDBCUtil {
	/**
	 * ��ȡ����
	 * @param driver
	 * @param url
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public static Connection getConnection(String driver, String url, String loginName, String loginPassword){
		Connection conn = null;
		try {
			Class driverClass = Class.forName(driver);
			conn = DriverManager.getConnection(url,loginName,loginPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ�����
	 * @param connection
	 */
	public static void freeConnection(Connection connection){
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
