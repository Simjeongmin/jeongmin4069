package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.JoinVO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class JoinController implements Initializable {

	@FXML
	private TextField txtId; // 아이디
	@FXML
	private PasswordField txtPw; // 비밀번호
	@FXML
	private PasswordField txtPwRepeat; // 비밀번호 확인
	@FXML
	private TextField txtName; // 담임 선생님
	@FXML
	private TextField txtSubject; // 담임 담당과목
	@FXML
	private Button btnCancel; // 닫기
	@FXML
	private Button btnJoin; // 등록
	@FXML
	private Button btnOverlap; // 아이디 중복 확인

	JoinVO joinVO = new JoinVO();// joinVO라는 객체 생성

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);// 아이이 등록 버튼 비활성화
		txtPw.setEditable(false);// 텍스트 패스워드 수정불가로 만듬
		txtPwRepeat.setEditable(false);// 텍스트 패스워드 확인 수정불가로 만듬
		txtName.setEditable(false);
		txtSubject.setEditable(false);

		btnOverlap.setOnAction(event -> handlerBtnOverlapActoion(event)); // 아이디 중복 확인
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event)); // 아이디 등록
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 닫기 버튼
	}

	// 아이디 중복 확인(액션 이벤트)
	public void handlerBtnOverlapActoion(ActionEvent event) {
		btnJoin.setDisable(false);// 아이디 등록버튼 활성화
		btnOverlap.setDisable(true);// 아이디 중복버튼 비활성화

		JoinDAO jDao = null; // null값으로 jdao의 변수선언

		String searchId = "";// searchId ""선언;
		boolean searchResult = true; // searchResult를 true로 선언;

		try {
			searchId = txtId.getText().trim();// txtId에 공백이있으면 공백을없애고 searchId를 넣는다.
			jDao = new JoinDAO();// jDao를 인스턴스화 시킨다.
			searchResult = (boolean) jDao.getIdOverlap(searchId);// jDao에 얻어온 아이디를 중복되는지 안되는지 boolean으로 판단한다.

			if (!searchResult && !searchId.equals("")) {
				txtId.setDisable(true);// 텍스트ID 비활성화
				Alert alert = new Alert(AlertType.INFORMATION);// 알림
				alert.setTitle("아이디 중복 검사");// 알림 제목
				alert.setHeaderText(searchId + " 를 사용할 수 있습니다.");// 알림 주요 내용
				alert.setContentText("패스워드 입력하세요.");// 알림 서브 내용
				alert.showAndWait();// 알림창을 보여준다.

				btnJoin.setDisable(false);// 버튼 등록 활성화
				btnOverlap.setDisable(true);// 버튼 아이디중복 비활성화
				txtPw.setEditable(true);// 텍스트 패스워드 수정 가능
				txtPwRepeat.setEditable(true); // 텍스트 패스워드 중복 수정가능
				txtPw.requestFocus();// 텍스트 패스워드 발생한뒤에도 포커스를 계속 그대로 남길수있음.
				txtName.setEditable(true);
				txtName.requestFocus();
				txtSubject.setEditable(true);
				txtSubject.requestFocus();
				
				
			} else if (searchId.equals("")) {
				btnJoin.setDisable(true); // 버튼 등록 비활성화
				btnOverlap.setDisable(false);// 버튼 아이디중복 활성화
				Alert alert = new Alert(AlertType.WARNING);// WARNING 알림 창
				alert.setTitle("아이디 중복 검색");// 알림 제목
				alert.setHeaderText("아이디 입력 하시오.");// 알림 주요 내용
				alert.setContentText("등록할 아이디를 입력하세요!");// 알림 서브 내용
				alert.showAndWait();// 알림창을 보여준다.
			} else {
				btnJoin.setDisable(true);// 버튼 등록 비활성화
				btnOverlap.setDisable(false);// 버튼 아이디중복 활성화
				txtId.clear();// 텍스트 id의 값을 지워버림

				Alert alert = new Alert(AlertType.INFORMATION);// 알림
				alert.setTitle("아이디 중복 검사");// 알림 제목
				alert.setHeaderText(searchId + "사용 불가입니다.");// 알림 주요 내용
				alert.setContentText("패스워드 입력 하세요.");// 알림 서브 내용
				alert.showAndWait();// 알림창을 보여준다.

				txtId.requestFocus();// 텍스트 id 발생한뒤에도 포커스를 계속 그대로 남길수있음.
			}
		}
		// 실패할때
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);// ERROR 알림 창
			alert.setTitle("아이디 중복 검사 오류");// 알림 제목
			alert.setHeaderText("아이디 중복 검사 오류 발생.");// 알림 주요내용
			alert.setContentText("다시하세요");// 알림 서브 내용
			alert.showAndWait();// 알림창을 보여줌
		}
	}

	// 취소 버튼 이벤트 (액션 이벤트)
	public void handlerBtnCancelActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginView.fxml"));// 취소를 누르면 loginView로 이동한다.																					
			Parent mainView = (Parent) loader.load(); // mainView 
			Scene scane = new Scene(mainView);//scane 객체 생성
			Stage mainMtage = new Stage();// mainMtage 객체 생성
			mainMtage.setTitle("관리 선생님 로그인");
			mainMtage.setScene(scane);//mainmtage를 보여준다.
			Stage oldStage = (Stage) btnJoin.getScene().getWindow();//등록버튼을 누르면 스테이지 이동	
			oldStage.close();//oldStage를 닫음
			mainMtage.show();//mainMtage를 보여줌
		}
		// 실패할때
		catch (IOException e) {
			System.err.println("오류 " + e);// 오류를 보여줌
		}
	}

	// 선생님 등록버튼(액션이벤트)
	public void handlerBtnJoinActoion(ActionEvent event) {

		JoinVO jvo = null; // jvo null로 선언
		JoinDAO jdao = null;// jdao null로 선언

		boolean joinSucess = false;// joinSucess를 false로 선언

		// 패스워드 확인
		if (txtPw.getText().trim().equals(txtPwRepeat.getText().trim()) && !txtName.getText().trim().equals("")) {
			jvo = new JoinVO(txtId.getText().trim(), txtPw.getText().trim(), txtName.getText().trim(),
					txtSubject.getText().trim());//jvo의 인스턴스화
			jdao = new JoinDAO();//jdao 인스터화
			try {
				joinSucess = jdao.getTeacherRegiste(jvo);//JDAO에서 선생님등록 데이터로 JOINSucces 한다.
				if (joinSucess) {
					handlerBtnCancelActoion(event);//취소이벤트 버튼가 발동
				}
			} catch (Exception e1) {
				e1.printStackTrace();//오류 발생하면 발생한곳을 찾아줌.
			}
		} else {
			txtPw.clear();// 텍스트 패스워드 초기화
			txtPwRepeat.clear();// 텍스트 패스워드 확인 초기화
			Alert alert = new Alert(AlertType.ERROR);// 알림 ERROR
			alert.setTitle("패스워드 확인");// 알림 제목
			alert.setHeaderText("패스워드 확인 검사에 오류 발생.");// 알림 주요 내용
			alert.setContentText("패스워드를 다시 입력하세요.");// 알림 서브 내용
			alert.showAndWait();// 알림창을 보여준다.
		}
	}
}
