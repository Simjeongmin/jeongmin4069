package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.EnrollmentTabVO;
import Model.StudentVO;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oracle.net.aso.e;
import oracle.net.aso.s;

public class EnrollmentTabController implements Initializable {
	@FXML
	private Button btnReset;// 초기화
	@FXML
	private Button btnCheck;// 등록 버튼
	@FXML
	private Button btnExit;// 메인창이동
	@FXML
	private Label lblTeacherName;// 담당선생님
	@FXML
	private DatePicker dpDate;// 날짜
	@FXML
	private TableView<StudentVO> tableView = new TableView<StudentVO>();
	@FXML
	private ToggleGroup choiceGroup;// 출석부
	@FXML
	private RadioButton rbAttendance;// 출석
	@FXML
	private RadioButton rbAbsent;// 결석
	@FXML
	private Label lblCount;// 총인원

	ObservableList<StudentVO> data = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent;// 테이블에서 선택한 정보 저장

	int selectedindex;// 테이블에서 선택한 학생정보 인덱스 저장
	int no;// 삭제시 테이블에서 선택한 학생 번호 저장
	String Studentloginid;// 로그인아이디

	ObservableList<EnrollmentTabVO> list = FXCollections.observableArrayList();

	@Override

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("출석부 컨트롤");
		dpDate.setValue(LocalDate.now());// 오늘날짜 설정
		choiceGroup.selectToggle(null);
		// 키이벤트
		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // 메인창이동
		btnCheck.setOnAction(event -> handlerBtnCheckActoion(event));// 등록
		btnReset.setOnAction(event -> handlerBtnResetActoion(event));// 학생 선택 초기화

		tableView.setOnMouseClicked(event -> handlerBtnPieChartAction(event));// 테이블에 선택한 정보 저장

		lblTeacherName.setText(LoginController.teacherName);// 담당 선생님 불러오기

		// 학생선택
		selectedindex = tableView.getSelectionModel().getSelectedIndex();

		// 테이블 뷰 수정금지!
		tableView.setEditable(false);
		// 테이블 뷰 컬럼이름
		TableColumn cols_Code = new TableColumn("NO");
		cols_Code.setMaxWidth(40);
		cols_Code.setCellValueFactory(new PropertyValueFactory<>("S_code"));

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

		TableColumn cols_Come = new TableColumn("출석여부");
		cols_Come.setMaxWidth(80);
		cols_Come.setCellValueFactory(new PropertyValueFactory<>("S_come"));

		tableView.getColumns().addAll(cols_Code, cols_Name, cols_Year, cols_Ban, cols_Number, cols_Come);
		System.out.println("출석부 테이블 뷰 :학생 전체 리스트");
		totalList();
		tableView.setItems(data);
	}

	// 마우스이벤트 핸들러
	public void handlerBtnPieChartAction(MouseEvent event) {

		// 마우스 왼쪽 더블 클릭 카운트이면 2을 반환
		// 마우스 왼쪽을 클릭이면 수정 삭제
		try {
			if (event.getClickCount() != 2) {
				selectStudent = tableView.getSelectionModel().getSelectedItems();
				no = selectStudent.get(0).getS_code();
				System.out.println(no);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학생 선택");
			alert.setHeaderText("선택한 학생이 없습니다");
			alert.setContentText("학생을 추가한후 선택하세요");

		}
		return;

	}

	// 초기화
	public void handlerBtnResetActoion(ActionEvent event) {
		try {
			EnrollmentTabVO evo = null;
			EnrollmentTabDAO edao = null;
			edao = new EnrollmentTabDAO();
			edao.setStudentReset(); // 학생 출석 여부 변경 1-> 0
			//학생테이블 재표시
			data.removeAll(data);
			totalList();
			tableView.setItems(data);
		} catch (Exception e) {
		}

	}

	// 등록 버튼
	public void handlerBtnCheckActoion(ActionEvent event) {

		try {

			EnrollmentTabVO evo = null;
			EnrollmentTabDAO edao = null;
			no = selectStudent.get(0).getS_code();
			//출석여부 라디오 버튼  데이터 등록 선택한 학생 코드로 구분한다(no)
			evo = new EnrollmentTabVO(choiceGroup.getSelectedToggle().getUserData().toString(), no);
			edao = new EnrollmentTabDAO();
			edao.setStudentAttendance(evo); // 학생 출석 등록
			edao.setStudentAttendanceChange(evo); // 학생 출석 여부 컬럼 변경 0 -> 1
			if (edao != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("출석부 등록");
				alert.setHeaderText("등록 성공");
				alert.setContentText("등록 완료");
				alert.showAndWait();
				data.removeAll(data);
				totalList();
				tableView.setItems(data);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("출석부");
			alert.setHeaderText(" 등록 실패");
			alert.setContentText("학생,출석여부를 선택하세요 ");
			alert.showAndWait();
		}
	}

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

	// 학생 전체리스트
	public void totalList() {
		Object[][] totalData;
		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;
		title = sDao.getColumnName();
		int columnCount = title.size();
		list = sDao.getStudentTotal();//학생 테이블 메인 용
		int rowCount = list.size();

		lblCount.setText("총원: " + rowCount + " 명");//총원 출력

		totalData = new Object[rowCount][columnCount];
		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			data.add(sVo);
		}

	}

}
