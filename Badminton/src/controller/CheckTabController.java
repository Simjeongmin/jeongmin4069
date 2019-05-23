package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.BadmintonplayVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckTabController implements Initializable {
	@FXML
	private DatePicker dpDate;//날짜
	@FXML
	private TextField txtLevel;//레벨
	@FXML
	private TextField txtTime;//시간
	@FXML
	private TextField txtContent;//수업 내용
	@FXML
	private Button btnCheck;//저장(확인)
	@FXML
	private Button btnExit;//종료
	@FXML
	private TableView<BadmintonplayVO> tableView = new TableView<BadmintonplayVO>();
	
	BadmintonplayVO badmintonplay = new BadmintonplayVO();
	ObservableList<BadmintonplayVO> data =FXCollections.observableArrayList();
	ObservableList<BadmintonplayVO> selectbadmintonplay;//테이블에서 선택한 정보 저장
	
	int selectedindex;//테이블에서 선택한 학생 정보 인덱스 저장
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dpDate.setValue(LocalDate.now());// 오늘날짜 설정
		
		btnCheck.setDisable(false);
		
		//버튼 이벤트
		
		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // 종료->메인창이동
		
		//수업선택
		selectedindex = tableView.getSelectionModel().getSelectedIndex();
		
		
		//테이블 뷰 수정금지!
		tableView.setEditable(false);
		
		//테이블 뷰 컬럼이름
		TableColumn colc_code= new TableColumn("No");
		colc_code.setMaxWidth(40);
		colc_code.setCellValueFactory(new PropertyValueFactory<>("c_code"));
		
		TableColumn colc_day= new TableColumn("수업날짜");
		colc_day.setMaxWidth(200);
		colc_day.setCellValueFactory(new PropertyValueFactory<>("c_day"));
	
		TableColumn colc_level= new TableColumn("수업레벨");
		colc_level.setMaxWidth(70);
		colc_level.setCellValueFactory(new PropertyValueFactory<>("c_level"));
		
		TableColumn colc_time= new TableColumn("수업날짜");
		colc_time.setMaxWidth(70);
		colc_time.setCellValueFactory(new PropertyValueFactory<>("c_time"));
		
		TableColumn colc_content= new TableColumn("수업내용");
		colc_content.setMaxWidth(100);
		colc_content.setCellValueFactory(new PropertyValueFactory<>("c_content"));
		
		tableView.getColumns().addAll(colc_code, colc_day, colc_level, colc_time, colc_content);
		
		// 수업 전체 
		totalList();
		tableView.setItems(data);
		
		
		btnCheck.setOnAction(event->{
			try {
				data.removeAll(data);
				BadmintonplayVO bdvo=null;
				BadmintonplayDAO bddao=new BadmintonplayDAO();
				
				if(event.getSource().equals(btnCheck)) {
					bdvo= new BadmintonplayVO(txtLevel.getText(),txtTime.getText(),txtContent.getText());
					bddao = new BadmintonplayDAO();
					bddao.getBadminton(bdvo);
					
					if(bdvo!=null) {
						totalList();
						Alert alert =new Alert(AlertType.INFORMATION);
						alert.setTitle("입력");
						alert.setHeaderText("성공");
						alert.setContentText("성공!!");
						alert.showAndWait();
					}
				}
			}catch(Exception e) {
				Alert alert =new Alert(AlertType.WARNING);
				alert.setTitle("입력");
				alert.setHeaderText("실패");
				alert.setContentText("실패!!");
				alert.showAndWait();
			}
		});
		
		
		
		
		
		
		
	}

	
	
	
	
	

//수업 저장 전체
public void totalList() {
		Object[][] totalData;
		BadmintonplayDAO bddao = new BadmintonplayDAO();
		BadmintonplayVO bdvo=null;
		ArrayList<String> title;
		ArrayList<BadmintonplayVO> list;
		title= bddao.getColumName();
		int columCount= title.size();
		list = bddao.getbadmintonplayTotal();
		int rowCount = list.size();
		totalData = new Object[rowCount] [columCount];
		for (int index=0; index<rowCount; index++) {
			bdvo = list.get(index);
			data.add(bdvo);
		}
		
		
	}





	//종료버튼 액션(누르면->메인창으로 이동)
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
