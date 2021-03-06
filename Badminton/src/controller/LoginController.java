package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class LoginController implements Initializable {

	@FXML
	private TextField txtId; // 아이디
	@FXML
	private PasswordField txtPw; // 비밀번호
	@FXML
	private Button btnExit; // 닫기
	@FXML
	private Button btnLogin; // 로그인
	@FXML
	private Button btnTeacher; // 선생님등록

	public static String teacherName; // 담당선생님
	public static String subjectName; // 담당 과목
	public static String dayName; // 수업요일

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtId.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // 아이디 입력에서 enter 키 이벤트 적용
		txtPw.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // 패스원드 입력 에서 enter 키 이벤트
		btnTeacher.setOnAction(event -> handerBtnJoinAction(event)); // 선생님 등록창 으로 전환
		btnLogin.setOnAction(event -> handlerBtnLoginActoion(event)); // 로그인
		btnExit.setOnAction(event -> handlerBtnCancelActoion(event)); // 로그인창 닫기
	}

	// 아이디 입력에서 Enter키 이벤트 적용
	public void handerTxtIdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtPw.requestFocus();
		}
	}

	// 패스워드 입력에서 Enter키 이벤트
	public void handerTxtPasswordKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	// 선생님 등록창 전환
	public void handerBtnJoinAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/joinView.fxml"));//선생님 등록창으로 이동
			Parent mainView = (Parent) loader.load();//mainView
			Scene scane = new Scene(mainView);//scane 객체 생성
			Stage mainMtage = new Stage();//mainMtage 객체 생성
			mainMtage.setTitle("관리 선생님 등록");//제목이 관리선생님인 등록 창으로 감
			mainMtage.setScene(scane);//mainMtage를 보여준다.
			Stage oldStage = (Stage) btnLogin.getScene().getWindow();//로그인버튼을 누르면 스테이지 이동
			oldStage.close();//oldStage를 닫음
			mainMtage.show();//mainMtage를 보여줌
		} 
		//실패 할때
		catch (IOException e) {
			
			System.err.println("오류" + e);
		}

	}

	// 로그인 창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		Platform.exit();
	}

	// 로그인
	public void handlerBtnLoginActoion(ActionEvent event) {
		login();
	}

	// 로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO();// login 객체 생성

		boolean sucess = false; //sucess false로 선언

		try {
			teacherName = loginName(); // 담당선생님
			subjectName = loginName1(); // 담당과목명
			// teacherName=txtId.getText();
			sucess = login.getLogin(txtId.getText().trim(), txtPw.getText().trim());//텍스트 id 값과 텍스트 pw값을 얻어와서 로그인을 성공시킨다.
			//실패할때 오류발생
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//알림창 로그인에 실패할때
		Alert alert;
		if (txtId.getText().equals("") || txtPw.getText().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디 비밀번호 미입력");
			alert.setContentText("아이디 비밀번호중 미입력 이있습니다." + "\n 다시 입력 하세요.");
			alert.setResizable(false);
			alert.showAndWait();
		}

		// 로그인 성공시 메인 페이지 이동
		if (sucess) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/mainView.fxml"));//로그인이 성공되면 메인(학생등록창으로) 이동
				Parent mainView = (Parent) loader.load();// mainView
				Scene scane = new Scene(mainView); //scane 객체 생성
				Stage mainMtage = new Stage();//mainMtage 객체 생성
				mainMtage.setTitle("미래 방과후 중학생 관리"); 
				mainMtage.setResizable(false);
				mainMtage.setScene(scane);
				Stage oldStage = (Stage) btnLogin.getScene().getWindow();//로그인 버튼 누르면 스테이지 이동
				oldStage.close();//oldStage를 닫음
				mainMtage.show();//mainMtage를 보여줌
			} catch (IOException e) {
				System.err.println("오류 " + e);
			}
		} else {
			// 아이디 패스워드 확인 창
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디 비밀번호 불일치");
			alert.setContentText("아이디 비밀번호 불일치." + "\n 다시 입력 하세요.");
			alert.setResizable(false);
			alert.showAndWait();

			txtId.clear();//텍스트 id 값 초기화
			txtPw.clear();//텍스트 pw 값 초기화
		}
	}

	// 선생님이름 불러오기
	public String loginName() {
		LoginDAO ldao = new LoginDAO();//ldao 객체 생성

		String name = null; //이름 null값으로 선언

		try {
			name = ldao.getLoginName(txtId.getText()); //LoginDAO에서 텍스트 아이디를 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	// 선생님과목 불러오기 이름 변경할것
	public String loginName1() {
		LoginDAO ldao = new LoginDAO(); //ldao 객체 생성

		String name = null; //이름 null값으로 선언

		try {
			name = ldao.getLoginName1(txtId.getText());//LoginDAO에서 텍스트 아이디를 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	
}
