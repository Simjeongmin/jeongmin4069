package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.AdPrintVO;
import Model.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oracle.net.aso.a;

public class OutputTabController implements Initializable {
	@FXML
	private Button btnExcel; // 엑셀
	@FXML
	private Button btnSaveFileDir;// 저장폴더
	@FXML
	private TextField txtSaveFileDir;// 파일경로
	@FXML
	private TableView<AdPrintVO> tableView = new TableView<AdPrintVO>();// 출석부 테이불 뷰

	ObservableList<AdPrintVO> data = FXCollections.observableArrayList();
	private Stage primaryStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnExcel.setOnAction(event -> handlerBtnExcelFileAction(event));// 엑셀파일생성
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirAction(event));// 파일 저장 폴더

		tableView.setEditable(false);// 테이블뷰 수정금지

		// 테이블 뷰 컬럼이름
		TableColumn cols_Code = new TableColumn("NO");
		cols_Code.setMaxWidth(40);
		cols_Code.setCellValueFactory(new PropertyValueFactory<>("S_code"));

		TableColumn cols_Name = new TableColumn("이름");
		cols_Name.setMaxWidth(40);
		cols_Name.setCellValueFactory(new PropertyValueFactory<>("S_name"));

		TableColumn cols_Year = new TableColumn("학년");
		cols_Year.setMaxWidth(50);
		cols_Year.setCellValueFactory(new PropertyValueFactory<>("S_year"));

		TableColumn cols_Ban = new TableColumn("반");
		cols_Ban.setMaxWidth(50);
		cols_Ban.setCellValueFactory(new PropertyValueFactory<>("S_ban"));

		TableColumn cols_Number = new TableColumn("출석번호");
		cols_Number.setMaxWidth(80);
		cols_Number.setCellValueFactory(new PropertyValueFactory<>("S_number"));
		
//		TableColumn cola_Come = new TableColumn("출석여부");
//		cola_Come.setMaxWidth(80);
//		cola_Come.setCellValueFactory(new PropertyValueFactory<>("a_come"));
//		
//		TableColumn cola_Day = new TableColumn("출석날짜");
//		cola_Day.setMaxWidth(80);
//		cola_Day.setCellValueFactory(new PropertyValueFactory<>("a_day"));
		tableView.getColumns().addAll(cols_Code, cols_Name, cols_Year, cols_Ban, cols_Number);

		totalList();
		tableView.setItems(data);
		;
	}

	// 파일 저장 폴더
	public void handlerBtnSaveFileDirAction(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(primaryStage);

		if (selectedDirectory != null) {
			txtSaveFileDir.setText(selectedDirectory.getAbsolutePath());
			btnExcel.setDisable(false);

		}

	}

	// 엑셀 파일 생성
	public void handlerBtnExcelFileAction(ActionEvent event) {
		StudentDAO sDao = new StudentDAO();
		boolean saveSuccess;

		ArrayList<StudentVO> list;
		list = sDao.getStudentTotal();
		StudentExcel excelWriter = new StudentExcel();

		// xlsx 파일 쓰기
		saveSuccess = excelWriter.xlsxWiter(list, txtSaveFileDir.getText());
		if (saveSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("엑셀 파일 생성");
			alert.setHeaderText("학생 목록 엑셀 파일 생성 성공");
			alert.setContentText("학생 목록 엑셀 파일");
			alert.showAndWait();
		}
		btnExcel.setDisable(true);
	}

	// 학셍 전체 리스트
	public void totalList() {
		Object[][] totalData;
		AdprintDAO aDao = new AdprintDAO();
		AdPrintVO aVo = null;
		ArrayList<String> title;
		ArrayList<AdPrintVO> list;
		title = aDao.getColumns_Name();
		int columnCount = title.size();
		list = aDao.getStudent();
		int rowCount = list.size();
		totalData = new Object[rowCount][columnCount];
		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			data.add(aVo);
		}
	}

}
