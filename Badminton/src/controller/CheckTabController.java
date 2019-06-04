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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckTabController implements Initializable {
	@FXML
	private DatePicker dpDate;// 날짜
	@FXML
	private TextField txtLevel;// 레벨
	@FXML
	private TextField txtTime;// 시간
	@FXML
	private TextField txtContent;// 수업 내용
	@FXML
	private Button btnCheck;// 저장(확인)
	@FXML
	private Button btnDelete;// 삭제
	@FXML
	private Button btnExit;// 종료
	@FXML
	private TableView<BadmintonplayVO> tableView = new TableView<BadmintonplayVO>();

	BadmintonplayVO badmintonplay = new BadmintonplayVO();
	ObservableList<BadmintonplayVO> data = FXCollections.observableArrayList();
	ObservableList<BadmintonplayVO> selectbadmintonplay;// 테이블에서 선택한 정보 저장

	int selectedindex;// 테이블에서 선택한 학생 정보 인덱스 저장
	int c_code;// 삭제시 테이블에서 선택한 수업의 번호 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dpDate.setValue(LocalDate.now());// 오늘날짜 설정

		btnCheck.setDisable(false);//등록 버튼 활성화
		btnDelete.setDisable(false);//삭제 버튼 활성화

		//키 이벤트
		txtLevel.setOnKeyPressed(event->handlerTxtLevelKeyPressed(event));
		txtTime.setOnKeyPressed(event->handlerTxtTimeKeyPressed(event));
		txtContent.setOnKeyPressed(event->handlerTxtContentKeyPressed(event));
		
		
		
		// 버튼 이벤트

		btnExit.setOnAction(event -> handlerBtnExitActoion(event)); // 종료->메인창이동
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제
		tableView.setOnMouseClicked(event -> handlerBtnPieChartAction(event));// 테이블 학생의 선택
		// 수업선택
		selectedindex = tableView.getSelectionModel().getSelectedIndex();

		// 테이블 뷰 수정금지!
		tableView.setEditable(false);

		// 테이블 뷰 컬럼이름
		TableColumn colc_code = new TableColumn("No");
		colc_code.setMaxWidth(40);
		colc_code.setCellValueFactory(new PropertyValueFactory<>("c_code"));

		TableColumn colc_day = new TableColumn("수업날짜");
		colc_day.setMaxWidth(200);
		colc_day.setCellValueFactory(new PropertyValueFactory<>("c_day"));

		TableColumn colc_level = new TableColumn("수업레벨");
		colc_level.setMaxWidth(70);
		colc_level.setCellValueFactory(new PropertyValueFactory<>("c_level"));

		TableColumn colc_time = new TableColumn("수업시간");
		colc_time.setMaxWidth(120);
		colc_time.setCellValueFactory(new PropertyValueFactory<>("c_time"));

		TableColumn colc_content = new TableColumn("수업내용");
		colc_content.setMaxWidth(200);
		colc_content.setCellValueFactory(new PropertyValueFactory<>("c_content"));

		tableView.getColumns().addAll(colc_code, colc_day, colc_level, colc_time, colc_content);

		// 수업 전체
		totalList();
		tableView.setItems(data);
		//버튼 등록 이벤트
		btnCheck.setOnAction(event -> {
			try {
				data.removeAll(data);
				BadmintonplayVO bdvo = null;
				BadmintonplayDAO bddao = new BadmintonplayDAO();
				//입력이 안되었을때 오류
				if(txtLevel.getText().trim().equals("")||txtTime.getText().trim().equals("")||
						txtContent.getText().trim().equals("")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("입력 실패");
					alert.setHeaderText("수업 등록 실패");
					alert.setContentText("수업 등록 자세히 보세요!!");
					alert.showAndWait();
					data.removeAll(data);
					// 전체정보
					totalList();
				}else {
					
					//회원정보 등록
				if (event.getSource().equals(btnCheck)) {
					bdvo = new BadmintonplayVO(txtLevel.getText().trim(), txtTime.getText().trim(), txtContent.getText().trim());
					bddao = new BadmintonplayDAO();
					bddao.getBadminton(bdvo);
				
					if (bdvo != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("입력");
						alert.setHeaderText("수업 등록 성공");
						alert.setContentText("수업 등록 성공!!");
						alert.showAndWait();
						
						txtLevel.clear();
						txtTime.clear();
						txtContent.clear();
						
						
						txtLevel.setEditable(true);
						txtTime.setEditable(true);
						txtContent.setEditable(true);
						data.removeAll(data);
						// 전체정보
						totalList();
						
					}	
					}
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("수업 입력");
				alert.setHeaderText("수업 등록 실패");
				alert.setContentText("수업 등록 실패!!");
				alert.showAndWait();
				
				
				txtLevel.setEditable(true);
				txtTime.setEditable(true);
				txtContent.setEditable(true);
				data.removeAll(data);
				// 전체정보
				totalList();
			}
		});

	}
	//텍스트키 이벤트
	public void handlerTxtContentKeyPressed(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			btnCheck.requestFocus();
		}
	}

	public void handlerTxtTimeKeyPressed(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			txtContent.requestFocus();
		}
	}

	public void handlerTxtLevelKeyPressed(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			txtTime.requestFocus();
		}
	}

	// 테이블 뷰 마우스 이벤트 핸들러
	public void handlerBtnPieChartAction(MouseEvent event) {
		// 마우스 왼쪽 더블 클릭 카운트면 2를 반환
		// 마우스 왼쪽 클릭이면 수정 삭제
		if (event.getClickCount() != 2) {
			try {
				selectbadmintonplay = tableView.getSelectionModel().getSelectedItems();//tableView에있는 여러 데이터들중에 하나를 선택함.
				c_code = selectbadmintonplay.get(0).getC_code();

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("수업 선택");
				alert.setHeaderText("수업 선택이 없습니다.");
				alert.setContentText("수업 선택을 먼저 해주세요!");
				alert.showAndWait();
			}
			return;
		}
	}

//삭제
	public void handlerBtnDeleteAction(ActionEvent event) {
		BadmintonplayDAO bdao = null;
		bdao = new BadmintonplayDAO();
		try {
			bdao.getBadmintonDelete(c_code);
			data.removeAll(data);
			// 전체정보
			totalList();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//수업 저장 전체
	public void totalList() {
		Object[][] totalData;// totalData를 2차배열로 선언
		BadmintonplayDAO bddao = new BadmintonplayDAO();
		BadmintonplayVO bdvo = null;
		ArrayList<String> title; //title 배열선언
		ArrayList<BadmintonplayVO> list; //list 배열선언
		title = bddao.getColumName();
		int columCount = title.size();
		list = bddao.getbadmintonplayTotal();
		int rowCount = list.size();
		totalData = new Object[rowCount][columCount];//2차배열 인스턴스화
		for (int index = 0; index < rowCount; index++) {
			bdvo = list.get(index);
			data.add(bdvo);
		}

	}

	// 종료버튼 액션(누르면->메인창으로 이동)
	public void handlerBtnExitActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
			//메인뷰(학생등록창으로 이동한다)
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
