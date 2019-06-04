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
				+ "(attendane_seq.nextval,?,sysdate,?) ";// 출석여부는 라디오 버튼 등록:학생 코드로 구분 해서 등록한다
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, avo.getA_come());// 출석 여부 불러오기
			pstmt.setInt(2, avo.getS_code()); // 등록할 학생 코드 불러오기

			int i = pstmt.executeUpdate();

			if (i == 1) {
				// 참인경우
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출석부 등록");
				alert.setHeaderText("등록 성공 완료");
				alert.setContentText("등록 성공");
				alert.showAndWait();
			} else {
				// 거짓인경우
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("출석부 등록");
				alert.setHeaderText("등록 실패 완료");
				alert.setContentText("등록 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			// 출석부 학생 데이터 제거 안한경우
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("출석부 등록");
			alert.setHeaderText("학생을 선택 하세요");
			alert.setContentText("오류 발생");
			alert.showAndWait();
		} catch (Exception e) {
			// 그밖에
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
		// 학생 리스트를 불러오기 조건으로 학생테이블 코드와 출석부 학생 코드 가 동일한것만
		sql.append(
				"select s.S_code, a.A_no ,s.S_name, s.S_year, s.S_ban, s.S_number,a.a_come,a.a_day from student s,attendane a where s.S_code=a.S_code order by a.a_day desc");

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

	// 출석부학생삭제
	public void getStudentDelete(int no) throws Exception {

		// 출석부 테이블에 학생을 선택해서 삭제한다
		StringBuffer sql = new StringBuffer();
		sql.append("delete from attendane where a_no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean studentDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no); // 출석부 코드 불러오기

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

	// 학생 출석 여부 변경 0 -> 1
	public void setStudentAttendanceChange(EnrollmentTabVO evo) {
		// 업데이트 선택한 학생 코드로 구분
		String sql = "update student set s_come = 1 where s_code = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, evo.getS_code());// 학생 코드

			int i = pstmt.executeUpdate();

		} catch (Exception e) {
		}

	}

	// 학생 출석 여부 변경 1-> 0
	public void setStudentReset() {
		// 전체 학생 학생출석여부 0으로 변환
		String sql = "update student set s_come = 0";
		System.out.println(1111);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출석부  학생 초기화");
				alert.setHeaderText("학생 초기화 실패");
				alert.setContentText("학생 초기화 실패");
				alert.showAndWait();

			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("출석부  학생 초기화");
			alert.setHeaderText("학생 초기화 완료");
			alert.setContentText("학생 초기화 성공");
			alert.showAndWait();

		} catch (Exception e) {
		}

	}

	// 검색
	public EnrollmentTabVO getStudentCheck(String S_name) throws Exception {

		String dml = "select * from student,attendane where S_name like ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EnrollmentTabVO eVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, "%" + S_name + "%"); // 학생 이름
			rs = pstmt.executeQuery();

			if (rs.next()) {
				eVo = new EnrollmentTabVO();
				eVo.setS_code(rs.getInt("S_code"));
				eVo.setS_name(rs.getString("S_name"));
				eVo.setS_year(rs.getInt("S_year"));
				eVo.setS_ban(rs.getInt("S_ban"));
				eVo.setS_number(rs.getInt("S_number"));
				eVo.setA_come(rs.getString("A_come"));
				eVo.setA_day(rs.getString("A_day"));

			}
		} catch (SQLException e) {
			System.out.println("1 " + e);
		} catch (Exception e) {
			System.out.println("2" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
			}
		}
		return eVo;

	}

}
