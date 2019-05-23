package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AdPrintVO;
import Model.StudentVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdprintDAO {

	// 학생정보
	public ArrayList<AdPrintVO> getStudent() {
		ArrayList<AdPrintVO> list = new ArrayList<AdPrintVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select S_code,S_name,S_year,S_ban,S_number ");
		sql.append(" from student  order by S_code desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdPrintVO aVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVo = new AdPrintVO();
				aVo.setS_code(rs.getInt("S_code"));
				aVo.setS_name(rs.getString("S_name"));
				aVo.setS_year(rs.getInt("S_year"));
				aVo.setS_ban(rs.getInt("S_ban"));
				aVo.setS_number(rs.getInt("S_number"));
//				aVo.setA_come(rs.getString("a_come"));
//				aVo.setA_day(rs.getString("a_day"));

				list.add(aVo);

			}
		} catch (SQLException se) {
			System.out.println("오류나는곳");
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

	// 데이터베이스에서 학생 테이블의 컬럼갯수
	public ArrayList<String> getColumns_Name() {
		ArrayList<String> columns_Name = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from student");
		System.out.println(sql);
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

	// 출석부
	public void getStudentName(AdPrintVO avo) throws Exception {
		String sql = "insert into attendane (a_no,a_come,a_day) vlues (attendane_seq.netval,?,sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, avo.getA_come());

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
			System.err.println("오류발생: " + e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}

}
