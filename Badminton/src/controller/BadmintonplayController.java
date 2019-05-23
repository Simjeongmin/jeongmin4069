package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class BadmintonplayController implements Initializable {
	@FXML
	private TabPane mainPane;
	@FXML
	private Tab check;
	@FXML
	private Tab low;
	@FXML
	private Tab mid;
	@FXML
	private Tab high;
	
	//메뉴	
	@FXML
	private MenuItem menulow;
	@FXML
	private MenuItem menumid;
	@FXML
	private MenuItem menuhigh;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//메뉴 이벤트 등록
		menulow.setOnAction(event->handlermenulowAction(event));
		menumid.setOnAction(event->handlermenumidAction(event));
		menuhigh.setOnAction(event->handlermenuhighAction(event));
		

	}


	//메뉴 고급(이벤트 핸들러)
	public void handlermenuhighAction(ActionEvent event) {
		Alert alert;
		alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("배드민턴 레슨(고급)");
		alert.setHeaderText("미래 중학교 방과후 배드민턴 레슨(고급)");
		alert.setContentText("단식 복식 게임 "+"배드민턴 기술 심화");
		alert.setResizable(false);
		alert.showAndWait();
	}


	//메뉴 중급(이벤트 핸들러)
	public void handlermenumidAction(ActionEvent event) {
		Alert alert;
		alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("배드민턴 레슨(중급)");
		alert.setHeaderText("미래 중학교 방과후 배드민턴 레슨(중급)");
		alert.setContentText("경기하는법"+"배드민턴의 기술");
		alert.setResizable(false);
		alert.showAndWait();
	}


	//메뉴 초급(이벤트 핸들러)
	public void handlermenulowAction(ActionEvent event) {
		Alert alert;
		alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("배드민턴 레슨(초급)");
		alert.setHeaderText("미래 중학교 방과후 배드민턴 레슨(초급)");
		alert.setContentText("라켓 잡는법"+" 셔틀콕 잡는법" +" 기본 자세");
		alert.setResizable(false);
		alert.showAndWait();
	}

}
