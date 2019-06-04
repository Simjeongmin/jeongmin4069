package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.StudentVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StudentDAO {
	// 1 신규 학생 등록 테이블
	public StudentVO getStudentregiste(StudentVO sVo) throws Exception {
		// 2 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		// 정보를 삽입하기위한 sql문
		sql.append("insert into student  ");
		sql.append("(S_code, S_name, S_year, S_ban, " + "S_number, S_gender, S_phone , "
				+ "S_emergency, S_costfree, S_time, " + "S_experience, S_level, S_startdate,"
				+ "S_enddate, S_email, S_image, S_come) ");
		sql.append(" values (student_seq.nextval, ?" + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,0)");
		Connection con = null;
		PreparedStatement pstmt = null;
		StudentVO retval = null;

		try {

			// 3.DBUtil.getConnection()의 메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4.입력받은 정보를 처리하기 위해 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(1, sVo.getS_name());
			pstmt.setInt(2, sVo.getS_year());
			pstmt.setInt(3, sVo.getS_ban());
			pstmt.setInt(4, sVo.getS_number());
			pstmt.setString(5, sVo.getS_gender());
			pstmt.setString(6, sVo.getS_phone());
			pstmt.setString(7, sVo.getS_emergency());
			pstmt.setString(8, sVo.getS_costfree());
			pstmt.setInt(9, sVo.getS_time());
			pstmt.setString(10, sVo.getS_experience());
			pstmt.setString(11, sVo.getS_level());
			pstmt.setString(12, sVo.getS_startdate());
			pstmt.setString(13, sVo.getS_enddate());
			pstmt.setString(14, sVo.getS_email());
			pstmt.setString(15, sVo.getS_image());

			// 5.sql문을 수행후 처리결과를 얻어옴
			int i = pstmt.executeUpdate();

			retval = new StudentVO();

		} catch (SQLException e) {
			System.out.println("1e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("2e=[" + e + "]");
		} finally {
			try {
				// 6데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {

			}
		}
		return retval;
	}

	// 출석부 학생 전체 리스트
	public ArrayList<StudentVO> getStudentTotal() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		StringBuffer sql = new StringBuffer();
		// 출석부 학생 리스트를 표시하기위한 sql문
		sql.append("select S_code, S_name, S_year, S_ban, S_number, S_gender, ");
		sql.append("S_phone, S_emergency, S_costfree, S_time, S_experience, S_level, ");
		sql.append(
				"S_startdate, S_enddate, S_email, S_image, S_come from student where S_come = 0 order by S_code desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;
		try {
			con = DBUtil.getConnection();// DBUtil 연결
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// 결과물을 rs객체를 통해서 반환
			while (rs.next()) {
				sVo = new StudentVO();// sVo 인스턴스화
				sVo.setS_code(rs.getInt("S_code"));
				sVo.setS_name(rs.getString("S_name"));
				sVo.setS_year(rs.getInt("S_year"));
				sVo.setS_ban(rs.getInt("S_ban"));
				sVo.setS_number(rs.getInt("S_number"));
				sVo.setS_gender(rs.getString("S_gender"));
				sVo.setS_phone(rs.getString("S_phone"));
				sVo.setS_emergency(rs.getString("S_emergency"));
				sVo.setS_costfree(rs.getString("S_costfree"));
				sVo.setS_time(rs.getInt("S_time"));
				sVo.setS_experience(rs.getString("S_experience"));
				sVo.setS_level(rs.getString("S_level"));
				sVo.setS_startdate(rs.getString("S_startdate"));
				sVo.setS_enddate(rs.getString("S_enddate"));
				sVo.setS_email(rs.getString("S_email"));
				sVo.setS_image(rs.getString("S_image"));
				sVo.setS_come(rs.getInt("S_come"));

				list.add(sVo);

			}
		} catch (SQLException se) {
			System.out.println(se);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;

	}

	// 메인창 학생 전체 리스트
	public ArrayList<StudentVO> getStudentTotal1() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		StringBuffer sql = new StringBuffer();
		// 테이블에 표시할 sql문
		sql.append("select S_code, S_name, S_year, S_ban, S_number, S_gender, ");
		sql.append("S_phone, S_emergency, S_costfree, S_time, S_experience, S_level, ");
		sql.append("S_startdate, S_enddate, S_email, S_image, S_come from student order by S_code desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO sVo = null;
		try {
			con = DBUtil.getConnection();// DButil 연결
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// 결과물을 rs객체를 통해서 반환
			while (rs.next()) {
				sVo = new StudentVO();// sVo 인스턴스화
				sVo.setS_code(rs.getInt("S_code"));
				sVo.setS_name(rs.getString("S_name"));
				sVo.setS_year(rs.getInt("S_year"));
				sVo.setS_ban(rs.getInt("S_ban"));
				sVo.setS_number(rs.getInt("S_number"));
				sVo.setS_gender(rs.getString("S_gender"));
				sVo.setS_phone(rs.getString("S_phone"));
				sVo.setS_emergency(rs.getString("S_emergency"));
				sVo.setS_costfree(rs.getString("S_costfree"));
				sVo.setS_time(rs.getInt("S_time"));
				sVo.setS_experience(rs.getString("S_experience"));
				sVo.setS_level(rs.getString("S_level"));
				sVo.setS_startdate(rs.getString("S_startdate"));
				sVo.setS_enddate(rs.getString("S_enddate"));
				sVo.setS_email(rs.getString("S_email"));
				sVo.setS_image(rs.getString("S_image"));

				list.add(sVo);

			}
		} catch (SQLException se) {
			System.out.println(se);

		} catch (Exception e) {
			System.out.println(e);
		}
		// 연결을 해제한다
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;

	}

	// 데이터베이스에서 학생 테이블의 컬럼갯수
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		// 학생의 테이블 컬럼갯수를 표시하는 SQL문
		sql.append("select * from student");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체선언
		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();// DButil 연결
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.print(se);
		} catch (Exception e) {
			System.out.println(e);
		}
		// 연결을 해제한다
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return columnName;

	}

	// 선택한 한생의 점수 수정
	public StudentVO getStudentUpdate(StudentVO sVo, int s_code) throws Exception {
		// 2 데이터처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		//수정을 위한 sql문
		sql.append("update student set");
		sql.append(" S_name=?, S_year=?, S_ban=?, S_number=?, S_phone=? ");
		sql.append(" where s_code = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		StudentVO retval = null;
		try {
			// 3. DBUtil 이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();
			// 4. 수정한 학생 정보를 수정하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sVo.getS_name());
			pstmt.setInt(2, sVo.getS_year());
			pstmt.setInt(3, sVo.getS_ban());
			pstmt.setInt(4, sVo.getS_number());
			pstmt.setString(5, sVo.getS_phone());
			pstmt.setInt(6, sVo.getS_code());

			// 5 sql문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i==1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 정보 수정");
				alert.setHeaderText("학생 정보 수정 완료!");
				alert.setContentText("학생 정보 수정 완료!!");
				alert.showAndWait();
				//retval = new StudentVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 정보 수정");
				alert.setHeaderText("학생 정보 수정 실패!");
				alert.setContentText("학생 정보 수정 실패!!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 데이터베이스와 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return retval;
	}

//학생삭제
	public void getStudentDelete(int s_Code) throws Exception {
		// 2 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		//학생삭제를 위한 sql문 
		sql.append("delete from student where s_code = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 3 DButil 이라는 클래스의 getConnection()메서드로 데이터베이스 연결
			con = DBUtil.getConnection();

			// sql문 수행후 처리결과를 얻어옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, s_Code);
			// sql문을 수행후 처리결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 완료");
				alert.setContentText("학생 삭제 완료!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 실패");
				alert.setContentText("학생 삭제 실패!!");
				alert.showAndWait();

			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("학생 삭제를 하실라면 먼저 출석부에서 삭제를 하세요");
			alert.setContentText("학생 삭제 오류");
			alert.showAndWait();

		} catch (Exception e) {
			System.out.println("4e=[" + e + "]");

		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}

	}

}