package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class LowTabController implements Initializable {

	@FXML
	private Button btnday1;
	@FXML
	private Button btnday2;
	@FXML
	private Button btnday3;
	@FXML
	private Button btnday4;
	@FXML
	private Button btnday5;
	@FXML
	private Button btnday6;
	@FXML
	private Button btnday7;
	@FXML
	private Button btnday8;
	@FXML
	private Button btnday9;
	@FXML
	private Button btnday10;
	@FXML
	private Button btnday11;
	@FXML
	private Button btnday12;
	@FXML
	private Button btnday13;
	@FXML
	private Button btnday14;
	@FXML
	private Button btnday15;
	@FXML
	private Button btnday16;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//버튼 1~16개 이벤트 설정
		btnday1.setOnAction(event->handlerbtnday1Action(event));
		btnday2.setOnAction(event->handlerbtnday2Action(event));
		btnday3.setOnAction(event->handlerbtnday3Action(event));
		btnday4.setOnAction(event->handlerbtnday4Action(event));
		btnday5.setOnAction(event->handlerbtnday5Action(event));
		btnday6.setOnAction(event->handlerbtnday6Action(event));
		btnday7.setOnAction(event->handlerbtnday7Action(event));
		btnday8.setOnAction(event->handlerbtnday8Action(event));
		btnday9.setOnAction(event->handlerbtnday9Action(event));
		btnday10.setOnAction(event->handlerbtnday10Action(event));
		btnday11.setOnAction(event->handlerbtnday11Action(event));
		btnday12.setOnAction(event->handlerbtnday12Action(event));
		btnday13.setOnAction(event->handlerbtnday13Action(event));
		btnday14.setOnAction(event->handlerbtnday14Action(event));
		btnday15.setOnAction(event->handlerbtnday15Action(event));
		btnday16.setOnAction(event->handlerbtnday16Action(event));

	}

 
	//버튼 이벤트 활성화 1회차~16회차
	
	public void handlerbtnday16Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 16회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("반코트로 배웠던 기술들 응용");
		alert.showAndWait();
	}



	public void handlerbtnday15Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 15회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("강사가 학생들의 자세 와 기술들을 수정해줌");
		alert.showAndWait();
	}



	public void handlerbtnday14Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 14회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("풋워크와 하이클리어 연결해서 응용하기");
		alert.showAndWait();
	}



	public void handlerbtnday13Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 13회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("높이에 따라서 스윙하는법 배우기");
		alert.showAndWait();
	}



	public void handlerbtnday12Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 12회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("하이클리어 스윙동작 배우기");
		alert.showAndWait();
	}



	public void handlerbtnday11Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 11회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("리시브 리턴동작 익히기");
		alert.showAndWait();
	}



	public void handlerbtnday10Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 10회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("셔틀곡에 대한 학습하기");
		alert.showAndWait();
	}
	
	public void handlerbtnday9Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 9회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("타격 집중력 기르기");
		alert.showAndWait();
	}

	public void handlerbtnday8Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 8회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("전위 풋워크&& 풋위크 응용하기");
		alert.showAndWait();
	}

	public void handlerbtnday7Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 7회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("후위 풋위크 배우기");
		alert.showAndWait();
	}

	public void handlerbtnday6Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 6회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("전위 풋워크 배우기");
		alert.showAndWait();
	}

	public void handlerbtnday5Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 5회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("타격 지점 찾기");
		alert.showAndWait();
	}

	public void handlerbtnday4Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 4회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("라켓에 공 맞추기");
		alert.showAndWait();
	}

	private void handlerbtnday3Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 3회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("오버헤드 스윙동작 배우기");
		alert.showAndWait();
		
	}

	public void handlerbtnday2Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 2회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("라켓 그립잡기 자세");
		alert.showAndWait();
		
	}

	public void handlerbtnday1Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 초급 1회차");
		alert.setHeaderText("미래 중학교 배드민턴 초급");
		alert.setContentText("배드민턴 기본 이론 && 유래");
		alert.showAndWait();
	}

}
