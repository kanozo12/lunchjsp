package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Not Found Dirver");
			return null;
		}

		try {
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/I4Jg66y62Y", "I4Jg66y62Y",
					"Ld6west95X");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 접속 오류");
			return null;
		}
	}

	public static void close(Connection value) {
		if (value != null) { try { value.close(); } catch (Exception e) { } }
	}

	public static void close(PreparedStatement value) {
		if(value != null) { try { value.close(); } catch(Exception e) { } }
	}
		
	public static void close(ResultSet value) {
		if(value != null) { try { value.close(); } catch(Exception e) {} }
	}
}
