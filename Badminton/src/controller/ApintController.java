package controller;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.StudentVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ApintController implements Initializable {
	@FXML
	private TableView<StudentVO> tableView = new TableView<StudentVO>();
	@FXML
	private Button btnExcel; // 엑셀
	@FXML
	private Button btnSaveFileDir;// 저장폴더
	@FXML
	private TextField txtSaveFileDir;// 파일경로
	

	private Stage primaryStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			// 테이블 뷰 컬럼 이름
			TableColumn colNo = new TableColumn("NO.");
			colNo.setPrefWidth(50);
			colNo.setStyle("-fx-allignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colName = new TableColumn("이름");
			colName.setPrefWidth(50);
			colName.setStyle("-fx-allignment:CENTER");
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));

			TableColumn colYear = new TableColumn("학년");
			colYear.setPrefWidth(50);
			colYear.setStyle("-fx-allignment:CENTER");
			colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

			TableColumn colBan = new TableColumn("반");
			colBan.setPrefWidth(50);
			colBan.setStyle("-fx-allignment:CENTER");
			colBan.setCellValueFactory(new PropertyValueFactory<>("ban"));

			TableColumn colNumber = new TableColumn("출석번호");
			colNumber.setPrefWidth(60);
			colNumber.setStyle("-fx-allignment:CENTER");
			colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

			TableColumn colImage = new TableColumn("이미지 파일 이름");
			colImage.setPrefWidth(100);
			colImage.setStyle("-fx-allignment:CENTER");
			colImage.setCellValueFactory(new PropertyValueFactory<>("image"));

			tableView.getColumns().addAll(colNo, colName, colYear, colBan, colNumber, colImage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//btnExcel.setOnAction(event -> handlerBtnExcelFileAction(event));// 엑셀파일생성
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirAction(event));// 파일 저장 폴더

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

//	// 엑셀 파일 생성
//	public void handlerBtnExcelFileAction(ActionEvent event) {
//	StudentDAO sDao = new StudentDAO();
//	boolean saveSuccess;
//
//	ArrayList<StudentVO> list;list=sDao.getStudentTotal();
//	StudentExcel excelWriter = new StudentExcel();
//
//	// xlsx 파일 쓰기 
//	saveSuccess = excelWriter.xlsxWiter(list,
//	txtSaveFileDir.getText());
//	if(saveSuccess)
//	{
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("엑셀 파일 생성");
//		alert.setHeaderText("학생 목록 엑셀 파일 생성 성공");
//		alert.setContentText("학생 목록 엑셀 파일");
//		alert.showAndWait();
//	}btnExcel.setDisable(true);
//}
}
