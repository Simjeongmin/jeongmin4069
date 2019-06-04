package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.JoinVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JoinDAO {

	// 관리 선생님 등록
	public boolean getTeacherRegiste(JoinVO jvo) throws Exception {
		// 관리 선생님 등록을 위한 sql문
		String sql = "insert into teacher " + "(t_code, t_id, t_pw, t_name, t_subject)" + " values "
				+ "(teacher_seq.nextval, ?, ?, ?, ?)";
		Connection con = null;// con null로 객체선언
		PreparedStatement pstmt = null; // pstmt null로 객체선언
		boolean joinSucess = false;// joinSucess 비활성화

		try {

			con = DBUtil.getConnection();// DBUtil 클래스를 통해 DB접속
			pstmt = con.prepareStatement(sql);// 연결된 DB에 위에있는 sql문을 뿌린다.
			pstmt.setString(1, jvo.getT_id()); // 아이디
			pstmt.setString(2, jvo.getT_pw()); // 비밀번호
			pstmt.setString(3, jvo.getT_name()); // 담임선생님 이름
			pstmt.setString(4, jvo.getT_subject());// 담당과목

			int i = pstmt.executeUpdate();// 수행결과를 int 타입 값을 반환한다.
			// i가 1이면 등록 성공
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);// 알림 창
				alert.setTitle("관리 선생님 등록");// 알림창의 제목
				alert.setHeaderText(jvo.getT_name() + " 등록.");// 알림창의 상단 텍스트
				alert.setContentText("관리 선생님 등록 성공!!");// 알림창 내용
				alert.showAndWait();//알림창을 보여준다
				joinSucess = true;// 등록 성공
			} else
			// i가 1이 아닐때 등록 실패
			{
				Alert alert = new Alert(AlertType.ERROR);// 에러 알림창
				alert.setTitle("관리 선생님 등록");// 알림창의 제목
				alert.setHeaderText("선생님 등록 실패.");// 알림창의 상단 텍스트
				alert.setContentText("등록 실패!!!");// 알림창 내용
				alert.showAndWait();//알림창을 보여준다
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용 되었던 오브젝트 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return joinSucess;//joinSucess을 리턴함
	}

	// 아이디 중복 체크
	public boolean getIdOverlap(String idOverlap) throws Exception {

		String sql = "select * from teacher where t_id = ?"; // 아이디 중복체크를 위한 sql문
		Connection con = null; // con null로 선언
		PreparedStatement pstmt = null; // pstmt null로 선언
		ResultSet rs = null;// rs null로 선언
		boolean idOverlapResult = false;// 아이디 중복 체크 비활성화

		try {
			con = DBUtil.getConnection();// DBUtil 클래스를 통해 DB접속
			pstmt = con.prepareStatement(sql);// 연결된 DB에 위에있는 sql문을 뿌린다.
			pstmt.setString(1, idOverlap);// index 번호와 id 중복으로 확인한다.
			rs = pstmt.executeQuery();// 수행결과로 result 객체의 값을 변환합니다.
			if (rs.next()) {
				idOverlapResult = true; // 중복된 아이디 있다을때 true
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();//rs 오브젝트 해제
				if (pstmt != null)
					pstmt.close();//pstmt 오브젝트 해제
				if (con != null)
					con.close();// con 오브젝트 해제
			} catch (SQLException e) {
			}
		}
		return idOverlapResult;// idOverlapResult을 리턴함
	}
}
