package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";//오라클 드라이버
	static final String url = "jdbc:oracle:thin:@192.168.0.106:1521:orcl";//오라클 연결

	public static Connection getConnection() throws Exception {
		Class.forName(driver);//JDBC 드라이버를 적재
		Connection con = DriverManager.getConnection(url, "scott", "tiger");//데이터베이스 연결
		System.out.println("DB 접속 성공");
		return con;
	}
}
