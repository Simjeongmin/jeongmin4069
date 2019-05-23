package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class MidTabController implements Initializable {
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

		// 버튼 1~16개 이벤트 설정
		btnday1.setOnAction(event -> handlerbtnday1Action(event));
		btnday2.setOnAction(event -> handlerbtnday2Action(event));
		btnday3.setOnAction(event -> handlerbtnday3Action(event));
		btnday4.setOnAction(event -> handlerbtnday4Action(event));
		btnday5.setOnAction(event -> handlerbtnday5Action(event));
		btnday6.setOnAction(event -> handlerbtnday6Action(event));
		btnday7.setOnAction(event -> handlerbtnday7Action(event));
		btnday8.setOnAction(event -> handlerbtnday8Action(event));
		btnday9.setOnAction(event -> handlerbtnday9Action(event));
		btnday10.setOnAction(event -> handlerbtnday10Action(event));
		btnday11.setOnAction(event -> handlerbtnday11Action(event));
		btnday12.setOnAction(event -> handlerbtnday12Action(event));
		btnday13.setOnAction(event -> handlerbtnday13Action(event));
		btnday14.setOnAction(event -> handlerbtnday14Action(event));
		btnday15.setOnAction(event -> handlerbtnday15Action(event));
		btnday16.setOnAction(event -> handlerbtnday16Action(event));

	}

	// 버튼 이벤트 활성화 1일~16일

	public void handlerbtnday16Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("토너먼트 해보기");
		alert.showAndWait();
	}

	public void handlerbtnday15Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("강사가 학생들의 자세 와 기술들을 수정해줌");
		alert.showAndWait();
	}

	public void handlerbtnday14Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("2명에서 1조를 만들어서 서로 기술들 응용해서 자유롭게 치기");
		alert.showAndWait();
	}

	public void handlerbtnday13Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("시합 해보기");
		alert.showAndWait();
	}

	public void handlerbtnday12Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("연습 시합 해보기");
		alert.showAndWait();
	}

	public void handlerbtnday11Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("배드민턴 시합 영상 보기");
		alert.showAndWait();
	}

	public void handlerbtnday10Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("시합 규칙&& 과정 익히기");
		alert.showAndWait();
	}

	public void handlerbtnday9Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("스매시 배우기");
		alert.showAndWait();
	}

	public void handlerbtnday8Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("백핸드와 포핸드 익히기");
		alert.showAndWait();
	}

	public void handlerbtnday7Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("헤어핀 배우기");
		alert.showAndWait();
	}

	public void handlerbtnday6Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("드롭샷 백핸드 드롭샷 배우기");
		alert.showAndWait();
	}

	public void handlerbtnday5Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("드라이브(좌측)배우기");
		alert.showAndWait();
	}

	public void handlerbtnday4Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("드라이브(우측)배우기");
		alert.showAndWait();
	}

	private void handlerbtnday3Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("포핸드언더(좌측)배우기");
		alert.showAndWait();

	}

	public void handlerbtnday2Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("포핸드언더(우측)배우기");
		alert.showAndWait();

	}

	public void handlerbtnday1Action(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("레슨 중급");
		alert.setHeaderText("미래 중학교 배드민턴 중급");
		alert.setContentText("배드민턴 기술들 영상으로 보기");
		alert.showAndWait();
	}

}
