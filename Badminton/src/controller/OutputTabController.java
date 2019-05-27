package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.EnrollmentTabVO;
import Model.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oracle.net.aso.a;

public class OutputTabController implements Initializable {
	@FXML
	private Button btnSearch;// 검색
	@FXML
	private Button btnList;// 새로고침
	@FXML
	private Button btnDelete;// 삭제
	@FXML
	private Button btnExcel; // 엑셀
	@FXML
	private Button btnSaveFileDir;// 저장폴더
	@FXML
	private TextField txtSaveFileDir;// 파일경로
	@FXML
	private TextField txtSearch;// 검색이름

	@FXML
	private TableView<EnrollmentTabVO> tableView = new TableView<EnrollmentTabVO>();// 출석부 테이불 뷰

	ObservableList<EnrollmentTabVO> data = FXCollections.observableArrayList(); // 데이터
	private Stage primaryStage;
	ObservableList<EnrollmentTabVO> selectStudent;// 테이블에서 선택한 정보 저장

	int no;// 삭제시 테이블에서 선택한 학생 번호 저장
	int selectedindex;// 테이블에서 선텍한 학생정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 키이벤트
		btnList.setOnAction(event -> handlerBtnListAction(event));// 새로고침;
		btnExcel.setOnAction(event -> handlerBtnExcelFileAction(event));// 엑셀파일생성
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirAction(event));// 파일 저장 폴더
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));// 삭제버튼
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));// 검색

		tableView.setEditable(false);// 테이블뷰 수정금지
		tableView.setOnMouseClicked(event -> handlerBtnPieChartAction(event));// 테이블에 선택한 정보 저장
		// 학생선택
		selectedindex = tableView.getSelectionModel().getSelectedIndex();
		// 테이블 뷰 컬럼이름
		TableColumn colS_code = new TableColumn("NO");
		colS_code.setMaxWidth(40);
		colS_code.setCellValueFactory(new PropertyValueFactory<>("S_code"));

		TableColumn cols_Name = new TableColumn("이름");
		cols_Name.setMaxWidth(70);
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

		TableColumn cola_Come = new TableColumn("출석여부");
		cola_Come.setMaxWidth(80);
		cola_Come.setCellValueFactory(new PropertyValueFactory<>("a_come"));

		TableColumn cola_Day = new TableColumn("출석날짜");
		cola_Day.setMaxWidth(170);
		cola_Day.setCellValueFactory(new PropertyValueFactory<>("A_day"));
		tableView.getColumns().addAll(colS_code, cols_Name, cols_Year, cols_Ban, cols_Number, cola_Come, cola_Day);

		// 학생 전체 정보
		totalList();
		tableView.setItems(data);

	}

	// 검색
	public void handlerBtnSearchAction(ActionEvent event) {
		EnrollmentTabVO eVo = new EnrollmentTabVO();
		EnrollmentTabDAO eDao = null;

		Object[][] totalData = null;
		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txtSearch.getText().trim();// 데이터 집어 넣기
			eDao = new EnrollmentTabDAO();
			eVo = eDao.getStudentCheck(searchName);// 입력한데이터 dao 에서 확인

			// 검색칸 공백 확인
			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("학생 정보 검색");
				alert.setHeaderText("학생 이름을 입력 하시오");
				alert.setContentText("다음에 주의 하세요");
				alert.showAndWait();
			}
			// 검색칸 공백아닌경우
			if (!searchName.equals("") && (eVo != null)) {
				ArrayList<String> title;
				ArrayList<EnrollmentTabVO> list;
				title = eDao.getColumns_Name();// 학생 테이블 갯수 넣기
				int columnCount = title.size();// 데이터 넣기

				list = eDao.getTotal();//학생 전체 데이터 넣기
				int rowCount = list.size();//학생 갯수 넣기

				totalData = new Object[rowCount][columnCount]; //배열사용

				if (eVo.getS_name().equals(searchName)) {
					txtSearch.clear();
					data.removeAll(data);
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						eVo = list.get(index);
						if (eVo.getS_name().equals(searchName)) {
							data.add(eVo);
							searchResult = true;
						}
					}
				}
			}
			if (!searchResult) {
				txtSearch.clear();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("학생 검색");
				alert.setHeaderText(searchName + " 학생이 없습니다");
				alert.setContentText("다시 검색 하세요");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("학생 검색 오류");
			alert.setHeaderText("학생 검색 중 오류 발생 ");
			alert.setContentText("다시 검색 하세요");
			alert.showAndWait();
		}
	}

	// 새로고침
	public void handlerBtnListAction(ActionEvent event) {

		try {
			data.removeAll(data);
			totalList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 마우스이벤트 핸들러
	public void handlerBtnPieChartAction(MouseEvent event) {
		// 마우스 왼쪽 더블 클릭 카운트이면 2을 반환
		// 마우스 왼쪽을 클릭이면 수정 삭제
		try {
			if (event.getClickCount() != 2) {
				selectStudent = tableView.getSelectionModel().getSelectedItems();
				no = selectStudent.get(0).getA_no();
				System.out.println(no);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("학생 선택");
			alert.setHeaderText("선택한 학생이 없습니다");
			alert.setContentText("학생을 추가한후 선택하세요");

		}
		return;

	}

	// 삭제 버튼
	public void handlerBtnDeleteAction(ActionEvent event) {
		EnrollmentTabDAO eDao = null;
		eDao = new EnrollmentTabDAO();
		try {

			eDao.getStudentDelete(no);
			data.removeAll(data);
			// 학생 전체 정보
			totalList();
			// handlerBtnInitAction(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		EnrollmentTabDAO sDao = new EnrollmentTabDAO();
		boolean saveSuccess;

		ArrayList<EnrollmentTabVO> list;
		list = sDao.getTotal();
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
	}

	// 학셍 전체 리스트
	public void totalList() {
		Object[][] totalData;
		EnrollmentTabDAO eDao = new EnrollmentTabDAO();
		EnrollmentTabVO eVo = null;
		ArrayList<String> title;
		ArrayList<EnrollmentTabVO> list;
		title = eDao.getColumns_Name();
		int columnCount = title.size();
		list = eDao.getTotal();// 학생등록 출석부 등록된 학생만 검색 해서 출력
		int rowCount = list.size();
		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			eVo = list.get(index);
			data.add(eVo);
		}
	}

}
