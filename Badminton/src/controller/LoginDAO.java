package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	// 아이디 비밀번호 체크
	public boolean getLogin(String loginId, String loginPassword) throws Exception {
		// 아이디 비밀번호 체크를 위한 sql문
		String sql = "select * from teacher where t_id = ? and t_pw = ?";
		Connection con = null;// con null선언
		PreparedStatement pstmt = null; // pstmt null 선언
		ResultSet rs = null;// rs null선언
		boolean loginResult = false;// loginResult false로 선언

		try {
			con = DBUtil.getConnection();// DBUtil 클래스를 통해 DB접속
			pstmt = con.prepareStatement(sql);// 연결된 DB에 위에있는 sql문을 뿌린다.
			pstmt.setString(1, loginId);// 아이디
			pstmt.setString(2, loginPassword);// 패스워드
			rs = pstmt.executeQuery();// 결과물을 rs객체를 통해서 반환한다.
			// 아이디 비밀번호가 선생님등록한 값이 맞을때
			if (rs.next()) {
				loginResult = true; // 아이디 패스워드 일치
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터 베이스 연결에 사용 되었던 오브젝트 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginResult;
	}

	// 선생님이름(메인에 라벨로 표시)
	public String getLoginName(String loginId) throws Exception {
		// 선생님이름을 가져오는 sql문
		String sql = "select t_name from teacher where t_id = ?";
		Connection con = null;// con null선언
		PreparedStatement pstmt = null;// pstmt null 선언
		ResultSet rs = null;// rs null선언
		String loginName = null; // loginName null선언

		try {
			con = DBUtil.getConnection();// DBUtil 클래스를 통해 DB접속
			pstmt = con.prepareStatement(sql);// 연결된 DB에 위에있는 sql문을 뿌린다.
			pstmt.setString(1, loginId);// 로그인 아이디

			rs = pstmt.executeQuery();// 결과물을 rs객체를 통해서 반환한다.
			if (rs.next()) {
				loginName = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginName;
	}

	// 담당과목 이름(메인에 라벨로 표시)
	public String getLoginName1(String loginId1) throws Exception {
		// 선생님이름 표시 sql문
		String sql = "select t_subject from teacher where t_id = ?";
		Connection con = null;// con null선언
		PreparedStatement pstmt = null;// pstmt null선언
		ResultSet rs = null;// rs null선언
		String loginName1 = null;// loginName1 null선언

		try {
			con = DBUtil.getConnection();// DBUtil 클래스를 통해 DB접속
			pstmt = con.prepareStatement(sql);// 연결된 DB에 위에있는 sql문을 뿌린다.
			pstmt.setString(1, loginId1);// 로그인 아이디1

			rs = pstmt.executeQuery();// 결과물을 rs객체를 통해서 반환한다.
			if (rs.next()) {
				loginName1 = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginName1;
	}

}
