package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.AdPrintVO;
import Model.StudentVO;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class EnrollmentTabController implements Initializable {
	@FXML
	private Button btnCheck;// 등록 버튼
	@FXML
	private Button btnExit;// 메인창이동
	@FXML
	private Label lblTeacherName;// 담당선생님
	@FXML
	private Label lblS_Number;// 출석번호
	@FXML
	private DatePicker dpDate;// 날짜
	
	@FXML
	private TextField txtS_name;
	@FXML
	private TextField txtS_year;
	@FXML
	private TextField txtS_ban;
	@FXML
	private TextField txtS_number;
	@FXML
	private ToggleGroup genderGroup;//
	@FXML
	private RadioButton rbMale;
	@FXML
	private RadioButton rbFemale;
	
	
	

	ObservableList<AdPrintVO> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		dpDate.setValue(LocalDate.now());// 오늘날짜 설정
		genderGroup.selectToggle(null);
		


		// 키이벤트
		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // 메인창이동
	//	btnCheck.setOnAction(event -> handlerBtnCheckActoion(event));// 등록

		lblTeacherName.setText(LoginController.teacherName);// 담당 선생님

	}

//	// 등록 버튼
//	public void handlerBtnCheckActoion(ActionEvent event) {
//		
//		try {
//			list.removeAll(list);
//			
//			AdPrintVO avo = null;
//			AdprintDAO adao = new AdprintDAO();
//			
//			avo = new AdPrintVO(genderGroup.getSelectedToggle().getUserData().toString(), txtS_name.setText(value),txtS_year.setText(value), txtS_ban.setText(), txtS_number.setText());
//			//, dpDate.getValue(), , 
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	// 메인창이동
	public void handlerBtnExitActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}
	}
	

}
