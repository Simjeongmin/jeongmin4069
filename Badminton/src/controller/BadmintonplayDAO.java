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
		sql.append("select * from badmintonplay"); // 수업테이블 불러오는 sql문
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		//resultsetmetData 객체선언
		ResultSetMetaData rsmd=null;
		try {
			con=DBUtil.getConnection();//DButil 연결
			pstmt=con.prepareStatement(sql.toString());
			rs =pstmt.executeQuery();//결과물을 rs객체를 통해서 변환한다.
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
		//테이블에 표시할 sql문
		sql.append("select C_code, C_day, C_level, C_time, C_content from badmintonplay");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BadmintonplayVO bdvo =null;
		try {
			con=DBUtil.getConnection();//DButil에 연결시킨다.
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();//결과물을 rs객체를 통해서 변환한다.
			while(rs.next()) {
				bdvo = new BadmintonplayVO();//bdvo 인스턴스화
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
		//정보를 테이블에 삽입하는 sql문
		sql.append("insert into badmintonplay");
		sql.append("(C_code, C_day, C_level, C_time, C_content)");
		sql.append(" values(badmintonplay_seq.nextval, sysdate, ?, ?, ?)");
	
		Connection con =null;
		PreparedStatement pstmt=null;
		BadmintonplayVO retval=null;
		
		
		try {
			con= DBUtil.getConnection();//DButil연결
			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, bdvo.getC_level());
			pstmt.setString(2, bdvo.getC_time());
			pstmt.setString(3, bdvo.getC_content());
		
			//sql문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			
			retval = new BadmintonplayVO();
		}catch(SQLException e) {
			System.out.println("e1=["+e+"]");
			
			
		}catch(Exception e) {
			System.out.println("e2=["+e+"]");
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
	
	//삭제
	public void getBadmintonDelete(int c_code) throws Exception{
		// 데이터 처리를 위한 sql문
		StringBuffer sql= new StringBuffer();
		//삭제 sql문
		sql.append("delete from badmintonplay where c_code = ?");
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			//DButil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con=DBUtil.getConnection();
			
			//sql문을 수행한후 처리 결과를 얻어옴
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1,c_code);
			
			int i=pstmt.executeUpdate();//행의 수를 반환한다.
			
			if(i==1) {
				Alert alert =new Alert(AlertType.INFORMATION);
				alert.setTitle("수업 삭제");
				alert.setHeaderText("수업 삭제완료.");
				alert.setContentText("학생 삭제 성공!!");
				alert.showAndWait();
			}else {
				Alert alert =new Alert(AlertType.WARNING);
				alert.setTitle("수업 삭제");
				alert.setHeaderText("수업 삭제 실패.");
				alert.setContentText("학생 삭제 실패!!");
				alert.showAndWait();
			}
		}catch(SQLException e) {
			System.out.println("e=["+e+"]");
		}catch(Exception e) {
			System.out.println("e=["+e+"]");
		}finally {
			try {
				//데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch (SQLException e) {
			
			}
		}
		
	}
}
