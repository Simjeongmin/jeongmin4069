package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BadmintonplayVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BadmintonplayDAO {
	//데이터베이스에서 수업 테이블의 컬럼갯수
	public ArrayList<String> getColumName() {
		ArrayList<String> columName=new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from badmintonplay");
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		//resultsetmetData 객체선언
		ResultSetMetaData rsmd=null;
		try {
			con=DBUtil.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			rs =pstmt.executeQuery();
			rsmd= rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for(int i=1; i<=cols; i++) {
				columName.add(rsmd.getColumnName(i));
			}
		}catch(SQLException se) {
			System.out.println(se);
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				
			}
		}
		return columName;
		
	}
	//전체
	public ArrayList<BadmintonplayVO> getbadmintonplayTotal() {
		ArrayList<BadmintonplayVO> list =new ArrayList<BadmintonplayVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select C_code, C_day, C_level, C_time, C_content from badmintonplay");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BadmintonplayVO bdvo =null;
		try {
			con=DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				bdvo = new BadmintonplayVO();
				bdvo.setC_code(rs.getInt("C_code"));
				bdvo.setC_day(rs.getString("C_day"));
				bdvo.setC_level(rs.getString("C_level"));
				bdvo.setC_time(rs.getString("C_time"));
				bdvo.setC_content(rs.getString("C_content"));
				list.add(bdvo);
			}
		}catch(SQLException se) {
			System.out.println(se);
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				
			}
		}
		;
		return list;
	}

	//수업 등록
	public BadmintonplayVO getBadminton(BadmintonplayVO bdvo) throws Exception{
		//데이터 처리 sql문
		StringBuffer sql =  new StringBuffer();
		sql.append("insert into badmintonplay");
		sql.append("(C_code, C_day, C_level, C_time, C_content)");
		sql.append(" values(badmintonplay_seq.nextval, sysdate, ?, ?, ?)");
	
		Connection con =null;
		PreparedStatement pstmt=null;
		BadmintonplayVO retval=null;
		
		
		try {
			con= DBUtil.getConnection();
			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, bdvo.getC_level());
			pstmt.setString(2, bdvo.getC_time());
			pstmt.setString(3, bdvo.getC_content());
		
			//sql문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			
			retval = new BadmintonplayVO();
		}catch(SQLException e) {
			System.out.println("e=["+e+"]");
		}catch(Exception e) {
			System.out.println("e=["+e+"]");
			e.printStackTrace();
		}finally {
			try {
				//6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(SQLException e) {
				
			}
		}
	return retval;
	}
}
