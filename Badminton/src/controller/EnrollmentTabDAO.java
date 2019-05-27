package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import Model.EnrollmentTabVO;
import Model.StudentVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import oracle.net.aso.e;

public class EnrollmentTabDAO {

	// 학생정보
	public EnrollmentTabVO getStudent() throws Exception {
		String sql = "select s.S_code,s.S_name,s.S_year,s.S_ban,s.S_number,a.a_come,a.a_day from student s,attendane a order by s.S_code desc";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EnrollmentTabVO studentinfo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentinfo = new EnrollmentTabVO();
				studentinfo.setS_code(rs.getInt("S_code"));
				studentinfo.setS_name(rs.getString("S_name"));
				studentinfo.setS_year(rs.getInt("S_year"));
				studentinfo.setS_ban(rs.getInt("S_ban"));
				studentinfo.setS_number(rs.getInt("S_number"));
				studentinfo.setA_come(rs.getString("A_come"));
				studentinfo.setA_day(rs.getString("A_day"));

			}
		} catch (SQLException se) {
			System.out.println("오류나는곳");
			se.printStackTrace();

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
		return studentinfo;
	}

	// 데이터베이스에서 학생 테이블의 컬럼갯수
	public ArrayList<String> getColumns_Name() {
		ArrayList<String> columns_Name = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from student");
		// System.out.println(sql);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체선언
		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columns_Name.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.print(se);
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
		return columns_Name;

	}

	// 출석부 등록
	public void setStudentAttendance(EnrollmentTabVO avo) throws Exception {
		String sql = "insert into attendane (a_no,a_come,a_day,s_code) values "
				+ "(attendane_seq.nextval,?,sysdate,?) ";
		// String sql = "update attendane set a_come=? where S_code =?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			System.out.println(avo.getS_code());

			pstmt.setString(1, avo.getA_come());
			pstmt.setInt(2, avo.getS_code());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출석부 등록");
				alert.setHeaderText("등록 성공 완료");
				alert.setContentText("등록 성공");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("출석부 등록");
				alert.setHeaderText("등록 실패 완료");
				alert.setContentText("등록 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("출석부 등록");
			alert.setHeaderText("학생을 선택 하세요");
			alert.setContentText("오류 발생");
			alert.showAndWait();
			// System.err.println("오류발생: " + e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
			}
		}
	}

	// 학생 전체 리스트
	public ArrayList<EnrollmentTabVO> getTotal() {
		ArrayList<EnrollmentTabVO> list = new ArrayList<EnrollmentTabVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select s.S_code, a.A_no ,s.S_name, s.S_year, s.S_ban, s.S_number,a.a_come,a.a_day from student s,attendane a where s.S_code=a.S_code");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EnrollmentTabVO eVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eVo = new EnrollmentTabVO();
				eVo.setS_code(rs.getInt("S_code"));
				eVo.setA_no(rs.getInt("a_no"));
				eVo.setS_name(rs.getString("S_name"));
				eVo.setS_year(rs.getInt("S_year"));
				eVo.setS_ban(rs.getInt("S_ban"));
				eVo.setS_number(rs.getInt("S_number"));
				eVo.setA_come(rs.getString("a_come"));
				eVo.setA_day(rs.getString("a_day"));

				list.add(eVo);

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

	// 학생삭제
	public void getStudentDelete(int no) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from attendane where a_no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean studentDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();

			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 완료");
				alert.setContentText("학생 삭제 성공");
				alert.showAndWait();
				studentDeleteSucess = true;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 삭제");
				alert.setHeaderText("학생 삭제 실패");
				alert.setContentText("학생 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException se) {
			System.out.println("1: " + se);
		} catch (Exception e) {
			System.out.println("2: " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * // 검색 public ArrayList<EnrollmentTabVO> getStudentCheck(String s_name) throws
	 * Exception { ArrayList<EnrollmentTabVO> list = new
	 * ArrayList<EnrollmentTabVO>();
	 * 
	 * String sql = "select * from student s,attendane a where s.S_name like ? ";
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * EnrollmentTabVO eVo = null; try { con = DBUtil.getConnection(); pstmt =
	 * con.prepareStatement(sql); pstmt.setString(1, "%" + s_name + "%");
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { eVo = new EnrollmentTabVO();
	 * eVo.setS_code(rs.getInt("S_code")); eVo.setS_name(rs.getString("S_name"));
	 * eVo.setS_year(rs.getInt("S_year")); eVo.setS_ban(rs.getInt("S_ban"));
	 * eVo.setS_number(rs.getInt("S_number"));
	 * eVo.setA_come(rs.getString("A_come")); eVo.setA_day(rs.getString("A_day"));
	 * 
	 * list.add(eVo); } } catch (SQLException e) { System.out.println(e); } catch
	 * (Exception e) { System.out.println(e); } finally { try { if (rs != null)
	 * rs.close();
	 * 
	 * if (pstmt != null) pstmt.close();
	 * 
	 * if (con != null) con.close(); } catch (SQLException e2) { } } return list; }
	 */

	/*
	 * // 중복확인 public boolean getOverlap(int no) throws Exception {
	 * 
	 * String sql =
	 * "select * from student where s_code in (select s_code from attendane where a_day= a_day)"
	 * ; Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * boolean overlap1 = false; EnrollmentTabVO eVo = null;
	 * 
	 * try { con = DBUtil.getConnection(); pstmt = con.prepareStatement(sql);
	 * pstmt.setInt(1, no); rs = pstmt.executeQuery(); if (rs.next()) { overlap1 =
	 * true; } } catch (SQLException e) { System.out.println("e=[" + e + "]"); }
	 * catch (Exception e) { System.out.println("e=[" + e + "]"); } finally { try {
	 * // 데이터베이스와 연결에 사용되었던 오브젝트를 해제 if (rs != null) rs.close(); if (pstmt != null)
	 * pstmt.close(); if (con != null) con.close(); } catch (SQLException e) { //
	 * TODO: handle exception } } return overlap1; }
	 */

	// 학생 출석 여부 변경 0 -> 1
	public void setStudentAttendanceChange(EnrollmentTabVO evo) {
		String sql = "update student set s_come = 1 where s_code = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			System.out.println(evo.getS_come());

			pstmt.setInt(1, evo.getS_code());

			int i = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 학생 출석 여부 변경 1-> 0
	public void setStudentModification(EnrollmentTabVO evo) {
		System.out.println("초기화");
		String sql = "update student set s_come = 0";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, evo.getS_come());

			// int i = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
