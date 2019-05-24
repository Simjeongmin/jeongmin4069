package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.StudentVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class MainController implements Initializable {
	@FXML
	private Label lblTeacherName;// 선생님이름 라벨로 표시
	@FXML
	private Label lblSubjectName;// 과목이름 라벨로 표시
	@FXML
	private TableView<StudentVO> tableView = new TableView<>();// 테이블뷰로 저장된 데이터 표시
	@FXML
	private TextField txtName;// 학생이름
	@FXML
	private TextField txtYear;// 학생 학년
	@FXML
	private TextField txtBan;// 학생 반
	@FXML
	private TextField txtNumber;// 출석번호
	@FXML
	private ToggleGroup genderGroup;// 성별 선택
	@FXML
	private RadioButton rbMale;// 남성
	@FXML
	private RadioButton rbFemale;// 여성
	@FXML
	private TextField txtPhone;// 학생 휴대폰
	@FXML
	private TextField txtEmergency;// 비상연락망
	@FXML
	private ToggleGroup CostFreeGroup;// 수업료 유료 무료 선택
	@FXML
	private RadioButton rbFree;// 무료 버튼
	@FXML
	private RadioButton rbCost;// 유료 버튼
	@FXML
	private TextField txtTime;// 수업 시간
	@FXML
	private TextField txtExperience;// 본인경험
	@FXML
	private ToggleGroup LevelGroup;// 등급별 수강료
	@FXML
	private RadioButton rbCostno;// 무료
	@FXML
	private RadioButton rbLow;// 초급
	@FXML
	private RadioButton rbMid;// 중급
	@FXML
	private RadioButton rbHigh;// 고급
	@FXML
	private TextField txtStartdate;// 시작일
	@FXML
	private TextField txtEnddate;// 끝난일
	@FXML
	private TextField txtEmail;// 부모님이메일
	@FXML
	private Button btnInit;// 초기화
	@FXML
	private Button btnRegister;// 등록
	@FXML
	private Button btnEdit;// 수정
	@FXML
	private Button btnDelete;// 삭제
	@FXML
	private Button btnExit; // 종료
	@FXML
	private Button btnbadmintonplay;// 수업
	@FXML
	private Button btnEmail; // 이메일 창 전환
	@FXML
	Button btnAttendance;// 출석부
	@FXML
	private HBox imageBox;// 이미지
	@FXML
	private Button btnImageFile;// 이미지 등록 버튼
	@FXML
	private ImageView imageView;// 이미지뷰

	StudentVO student = new StudentVO();
	ObservableList<StudentVO> data = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent;// 테이블에서 선택한 정보 저장

	boolean editDelete = false; // 확인 버튼 상태설정
	int selectedIndex;// 테이블에서 선택한 학생 정보 인덱스 저장

	private Stage primaryStage;
	String selectFileName = "";// 이미지 파일 명
	String localUrl = "";// 이미지파일경로
	Image localimage;//

	int s_Code;// 삭제시 테이블에서 선택한 학생의 번호저장
	File selectedFile = null;

	// 이미지 저장할 폴더를 매게변수로 파일 객체 선언
	private File dirSave = new File("D:/images");
	// 이미지 불러올 파일 객체 선언
	private File file = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnInit.setDisable(false);// 초기화버튼 활성
		btnRegister.setDisable(false); // 등록 활성
		btnEdit.setDisable(false);// 수정 활성
		btnDelete.setDisable(false);// 삭제 활성
		btnImageFile.setDisable(false);// 이미지 등록 버튼 사용 활성

		// 학년 반 출석번호에는 숫자만 입력
		DecimalFormat format = new DecimalFormat("###");
		// 학년
		txtYear.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}
		}));
		// 반
		txtBan.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}
		}));
		// 출석번호
		txtNumber.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;

			}
		}));

		// 테이블 뷰 수정금지!
		tableView.setEditable(false);

		// 테이블 뷰 컬럼이름 설정
		TableColumn cols_Code = new TableColumn("NO");
		cols_Code.setMaxWidth(40);
		cols_Code.setCellValueFactory(new PropertyValueFactory<>("S_code"));

		TableColumn cols_Name = new TableColumn("이름");
		cols_Name.setMaxWidth(50);
		cols_Name.setCellValueFactory(new PropertyValueFactory<>("S_name"));

		TableColumn cols_Year = new TableColumn("학년");
		cols_Year.setMaxWidth(40);
		cols_Year.setCellValueFactory(new PropertyValueFactory<>("S_year"));

		TableColumn cols_Ban = new TableColumn("반");
		cols_Ban.setMaxWidth(40);
		cols_Ban.setCellValueFactory(new PropertyValueFactory<>("S_ban"));

		TableColumn cols_Number = new TableColumn("출석번호");
		cols_Number.setMaxWidth(40);
		cols_Number.setCellValueFactory(new PropertyValueFactory<>("S_number"));

		TableColumn cols_Gender = new TableColumn("성별");
		cols_Gender.setMaxWidth(40);
		cols_Gender.setCellValueFactory(new PropertyValueFactory<>("S_gender"));

		TableColumn cols_Phone = new TableColumn("핸드폰");
		cols_Phone.setMaxWidth(120);
		cols_Phone.setCellValueFactory(new PropertyValueFactory<>("S_phone"));

		TableColumn cols_Emergency = new TableColumn("비상연락");
		cols_Emergency.setMaxWidth(120);
		cols_Emergency.setCellValueFactory(new PropertyValueFactory<>("S_emergency"));

		TableColumn colc_Costfree = new TableColumn("수업료");
		colc_Costfree.setMaxWidth(60);
		colc_Costfree.setCellValueFactory(new PropertyValueFactory<>("S_costfree"));

		TableColumn colc_Time = new TableColumn("수업시간");
		colc_Time.setMaxWidth(60);
		colc_Time.setCellValueFactory(new PropertyValueFactory<>("S_time"));

		TableColumn cols_Experience = new TableColumn("본인경험");
		cols_Experience.setMaxWidth(180);
		cols_Experience.setCellValueFactory(new PropertyValueFactory<>("S_experience"));

		TableColumn colc_Level = new TableColumn("등급별 가격");
		colc_Level.setMaxWidth(100);
		colc_Level.setCellValueFactory(new PropertyValueFactory<>("S_level"));

		TableColumn colc_Startdate = new TableColumn("시작날짜");
		colc_Startdate.setMaxWidth(140);
		colc_Startdate.setCellValueFactory(new PropertyValueFactory<>("S_startdate"));

		TableColumn colc_Enddate = new TableColumn("끝나는날짜");
		colc_Enddate.setMaxWidth(140);
		colc_Enddate.setCellValueFactory(new PropertyValueFactory<>("S_enddate"));

		TableColumn cols_Email = new TableColumn("부모님 이메일");
		cols_Email.setMaxWidth(200);
		cols_Email.setCellValueFactory(new PropertyValueFactory<>("S_email"));

		TableColumn cols_Image = new TableColumn("이미지");
		cols_Image.setMaxWidth(300);
		cols_Image.setCellValueFactory(new PropertyValueFactory<>("S_image"));

		tableView.getColumns().addAll(cols_Code, cols_Name, cols_Year, cols_Ban, cols_Number, cols_Gender, cols_Phone,
				cols_Emergency, colc_Costfree, colc_Time, cols_Experience, colc_Level, colc_Startdate, colc_Enddate,
				cols_Email, cols_Image);

		// 학생전체 정보
		totalList();
		tableView.setItems(data);

		// 기본 이미지
		localUrl = "/image/default.png";
		localimage = new Image(localUrl, false);
		imageView.setImage(localimage);

		// 학생정보저장
		btnRegister.setOnAction(event -> {
			try {

				data.removeAll(data);
				StudentVO svo = null;
				StudentDAO sdao = new StudentDAO();
				File dirMake = new File(dirSave.getAbsolutePath());
				// 이미지 저장 폴더 생성
				if (!dirMake.exists()) {
					dirMake.mkdir();
				}
				// 이미지 파일 저장
				String s_image = imageSave(selectedFile);
				if (event.getSource().equals(btnRegister)) {
					svo = new StudentVO(txtName.getText(), Integer.parseInt(txtYear.getText().trim()),
							Integer.parseInt(txtBan.getText().trim()), Integer.parseInt(txtNumber.getText().trim()),
							genderGroup.getSelectedToggle().getUserData().toString(), txtPhone.getText(),
							txtEmergency.getText(), CostFreeGroup.getSelectedToggle().getUserData().toString(),
							Integer.parseInt(txtTime.getText().trim()), txtExperience.getText(),
							LevelGroup.getSelectedToggle().getUserData().toString(), txtStartdate.getText(),
							txtEnddate.getText(), txtEmail.getText(), s_image);

					sdao = new StudentDAO();
					sdao.getStudentregiste(svo);
					//s_Code = selectStudent.get(0).getS_code();
					if (sdao != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("점수 입력");
						alert.setHeaderText("등록 완료!");
						alert.setContentText(txtName.getText() + "학생의 점수가 성공적으로 추가되었습니다.");
						alert.showAndWait();

						btnImageFile.setDisable(false);

						// 기본 이미지
						localUrl = "/image/default.png";
						localimage = new Image(localUrl, false);
						imageView.setImage(localimage);

						txtName.setEditable(true);
						txtYear.setEditable(true);
						txtBan.setEditable(true);
						txtNumber.setEditable(true);
						txtPhone.setEditable(true);
						txtEmergency.setEditable(true);
						txtTime.setEditable(true);
						txtExperience.setEditable(true);
						txtStartdate.setEditable(true);
						txtEnddate.setEditable(true);
						handlerBtnInitAction(event);
					}
				}

			} catch (Exception e) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("학생 정보 입력");
				alert.setHeaderText("주의합시다");
				alert.setContentText("주의하라고요!");
				alert.showAndWait();

				txtName.setEditable(true);
				txtYear.setEditable(true);
				txtBan.setEditable(true);
				txtNumber.setEditable(true);
				txtPhone.setEditable(true);
				txtEmergency.setEditable(true);
				txtTime.setEditable(true);
				txtExperience.setEditable(true);
				txtStartdate.setEditable(true);
				txtEnddate.setEditable(true);
				totalList();
			}
		});

		// 키 이벤트 등록
		txtName.setOnKeyPressed(event -> handlerTxtNameKeyPressed(event));
		txtYear.setOnKeyPressed(event -> handlerTxtYearKeyPressed(event));
		txtBan.setOnKeyPressed(event -> handlerTxtBanKeyPressed(event));
		txtNumber.setOnKeyPressed(event -> handlerTxtNumberKeyPressed(event));
		rbMale.setOnKeyPressed(event -> handlerRbMalePressed(event));
		rbFemale.setOnKeyPressed(event -> handlerRbFemalePressed(event));
		txtPhone.setOnKeyPressed(event -> handlerTxtPhoneKeyPressed(event));
		txtEmergency.setOnKeyPressed(event -> handlerTxtEmergencyKeyPressed(event));
		rbFree.setOnKeyPressed(event -> handlerRbFreePressed(event));
		rbCost.setOnKeyPressed(event -> handlerRbCostPressed(event));
		txtTime.setOnKeyPressed(event -> handlerTxtTimeKeyPressed(event));
		txtExperience.setOnKeyPressed(event -> handlerTxtExperienceKeyPressed(event));
		rbLow.setOnKeyPressed(event -> handlerRbLowPressed(event));
		rbMid.setOnKeyPressed(event -> handlerRbMidPressed(event));
		rbHigh.setOnKeyPressed(event -> handlerRbHighPressed(event));
		txtStartdate.setOnKeyPressed(event -> handlerTxtStartdate(event));
		txtEnddate.setOnKeyPressed(event -> handlerTxtEnddate(event));
		txtEmail.setOnKeyPressed(event -> handlerTxtKeyEmail(event));

		// 버튼이벤트
		btnExit.setOnAction(event -> handlerBtnExitlActoion(event));// 메인창 종료
		btnEmail.setOnAction(event -> handlerBtnEmalActoion(event)); // 메일창 이동
		btnAttendance.setOnAction(event -> handlerBtnAttendanceActoion(event)); // 출석부 이동
		btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event)); // 이미지 선택 창
		btnbadmintonplay.setOnAction(event -> handlerBtnbadmintonplay(event));// 배드민턴 수업창으로 이동
		btnInit.setOnAction(event -> handlerBtnInitAction(event));// 초기화
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));// 수정
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));// 삭제
		lblTeacherName.setText(LoginController.teacherName);// 담당 선생님
		lblSubjectName.setText(LoginController.subjectName);// 담당 과목
		tableView.setOnMouseClicked(event -> handlerBtnPieChartAction(event));// 테이블의 학생선택
	}

	public void handlerBtnPieChartAction(MouseEvent event) {
		// 마우스 왼쪽 더블 클릭이면 2를 반환
		// 마우스 왼쪽 클릭이면 수정 삭제
		if (event.getClickCount() != 2) {
			try {
				selectStudent = tableView.getSelectionModel().getSelectedItems();
				s_Code = selectStudent.get(0).getS_code();
				selectFileName = selectStudent.get(0).getS_image();
				localUrl = "file:/D:/images/" + selectFileName;
				localimage = new Image(localUrl, false);

				imageView.setImage(localimage);
				imageView.setFitHeight(250);
				imageView.setFitWidth(230);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("학생 선택");
				alert.setHeaderText("선택한 학생이없습니다");
				alert.setContentText("학생을 추가한뒤에 선택해주세요");

			}
		}
		return;
	}

// 학생 삭제
	public void handlerBtnDeleteAction(ActionEvent event) {
		StudentDAO sDao = null;
		sDao = new StudentDAO();
		try {
			sDao.getStudentDelete(s_Code);
			data.removeAll(data);
			// 학생 전체 정보
			totalList();
			handlerBtnInitAction(event);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

//수정버튼 이벤트 핸들러
	public void handlerBtnEditAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/formedit.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnRegister.getScene().getWindow());
			dialog.setTitle("수정");

			Parent parentEdit = (Parent) loader.load();
			StudentVO studentEdit = tableView.getSelectionModel().getSelectedItem();
			selectedIndex = tableView.getSelectionModel().getSelectedIndex();

			TextField editno = (TextField) parentEdit.lookup("#txts_code");
			TextField editname = (TextField) parentEdit.lookup("#txts_name");
			TextField edityear = (TextField) parentEdit.lookup("#txts_year");
			TextField editban = (TextField) parentEdit.lookup("#txts_ban");
			TextField editnumber = (TextField) parentEdit.lookup("#txts_number");
			TextField editgender = (TextField) parentEdit.lookup("#txts_gender");
			TextField editphone = (TextField) parentEdit.lookup("#txts_phone");
			TextField editemergency = (TextField) parentEdit.lookup("#txts_emergency");
			TextField editcostfree = (TextField) parentEdit.lookup("#txts_costfree");
			TextField edittime = (TextField) parentEdit.lookup("#txts_time");
			TextField editexperience = (TextField) parentEdit.lookup("#txts_experience");
			TextField editlevel = (TextField) parentEdit.lookup("#txts_level");
			TextField editstartdate = (TextField) parentEdit.lookup("#txts_startdate");
			TextField editenddate = (TextField) parentEdit.lookup("#txts_enddate");
			TextField editemail = (TextField) parentEdit.lookup("#txts_email");

			// 수정금지
			editno.setDisable(true);
			editgender.setDisable(true);
			editemergency.setDisable(true);
			editcostfree.setDisable(true);
			edittime.setDisable(true);
			editexperience.setDisable(true);
			editlevel.setDisable(true);
			editstartdate.setDisable(true);
			editenddate.setDisable(true);
			editemail.setDisable(true);

			editno.setText(studentEdit.getS_code() + "");
			editname.setText(studentEdit.getS_name());
			edityear.setText(studentEdit.getS_year() + "");
			editban.setText(studentEdit.getS_ban() + "");
			editnumber.setText(studentEdit.getS_number() + "");
			editgender.setText(studentEdit.getS_gender());
			editphone.setText(studentEdit.getS_phone());
			editemergency.setText(studentEdit.getS_emergency());
			editcostfree.setText(studentEdit.getS_costfree());
			edittime.setText(studentEdit.getS_time() + "");
			editexperience.setText(studentEdit.getS_experience());
			editlevel.setText(studentEdit.getS_level());
			editstartdate.setText(studentEdit.getS_startdate());
			editenddate.setText(studentEdit.getS_enddate());
			editemail.setText(studentEdit.getS_email());

			Button btnFormAdd = (Button) parentEdit.lookup("#btnFormAdd");
			Button btnFormCancel = (Button) parentEdit.lookup("#btnFormCancel");

			btnFormAdd.setOnAction(e -> {
				StudentVO sVo = null;
				StudentDAO sDao = null;
				TextField txts_code = (TextField) parentEdit.lookup("#txts_code");
				TextField txts_name = (TextField) parentEdit.lookup("#txts_name");
				TextField txts_year = (TextField) parentEdit.lookup("#txts_year");
				TextField txts_ban = (TextField) parentEdit.lookup("#txts_ban");
				TextField txts_number = (TextField) parentEdit.lookup("#txts_number");
				TextField txts_gender = (TextField) parentEdit.lookup("#txts_gender");
				TextField txts_phone = (TextField) parentEdit.lookup("#txts_phone");
				TextField txts_emergency = (TextField) parentEdit.lookup("#txts_emergency");
				TextField txts_costfree = (TextField) parentEdit.lookup("#txts_costfree");
				TextField txts_time = (TextField) parentEdit.lookup("#txts_time");
				TextField txts_experience = (TextField) parentEdit.lookup("#txts_experience");
				TextField txts_level = (TextField) parentEdit.lookup("#txts_level");
				TextField txts_startdate = (TextField) parentEdit.lookup("#txts_startdate");
				TextField txts_enddate = (TextField) parentEdit.lookup("#txts_enddate");
				TextField txts_email = (TextField) parentEdit.lookup("#txts_email");

				data.remove(selectedIndex);

				try {
					sVo = new StudentVO(Integer.parseInt(txts_code.getText()), txts_name.getText(),
							Integer.parseInt(txts_year.getText().trim()), Integer.parseInt(txts_ban.getText().trim()),
							Integer.parseInt(txts_number.getText().trim()), txts_gender.getText(), txts_phone.getText(),
							txts_emergency.getText(), txts_costfree.getText(),
							Integer.parseInt(txts_time.getText().trim()), txts_experience.getText(),
							txts_level.getText(), txts_startdate.getText(), txts_enddate.getText(),
							txts_email.getText());

					sDao = new StudentDAO();
					sDao.getStudentUpdate(sVo, sVo.getS_code());
					data.removeAll(data);
					totalList();
					dialog.close();
				} catch (Exception e1) {
					e1.printStackTrace();

				}
				btnDelete.setDisable(true);
				btnEdit.setDisable(true);
				handlerBtnInitAction(event);

				// 기본이미지
				localUrl = "/image/default.png";
				localimage = new Image(localUrl, false);
				imageView.setImage(localimage);

			});
			// 수정창의 버튼 취소
			btnFormCancel.setOnAction(e -> {
				btnDelete.setDisable(true);
				btnEdit.setDisable(false);
				dialog.close();
				handlerBtnInitAction(event);

				// 기본 이미지
				localUrl = "/image/default.png";
				localimage = new Image(localUrl, false);
				imageView.setImage(localimage);
			});
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			handlerBtnInitAction(event);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

//이미지 삭제 메소드
	public boolean imageDelete(String s_image) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getAbsolutePath() + "\\" + s_image);// 삭제 이미지 파일

			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();

				// 기본 이미지
				localUrl = "/image/default.png";
				localimage = new Image(localUrl, false);
				imageView.setImage(localimage);
			}
		} catch (Exception ie) {
			System.out.println("ie= [" + ie.getMessage() + "]");
			result = false;
		}
		return result;
	}

//이미지 저장 메소드
	public String imageSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String s_image = null;
		try {
			// 이미지 파일 생성
			s_image = "student" + System.currentTimeMillis() + "_" + file.getName();

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + s_image));

			// 선택한 이미지 파일 inputstream의 마지막에 이르렀을 경우에 -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return s_image;
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
		list = sDao.getStudentTotal1();
		int rowCount = list.size();
		totalData = new Object[rowCount][columnCount];
		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			data.add(sVo);
		}

	}

//텍스트 이벤트
	public void handlerTxtKeyEmail(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btnRegister.requestFocus();
		}

	}

	public void handlerTxtEnddate(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEmail.requestFocus();
		}

	}

	public void handlerTxtStartdate(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEnddate.requestFocus();
		}

	}

	public void handlerRbHighPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}

	}

	public void handlerRbMidPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}

	}

	public void handlerRbLowPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtStartdate.requestFocus();
		}
	}

	public void handlerTxtExperienceKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbLow.requestFocus();
		}
	}

	public void handlerTxtTimeKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtExperience.requestFocus();
		}

	}

	public void handlerRbCostPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtTime.requestFocus();
		}

	}

	public void handlerRbFreePressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtTime.requestFocus();
		}
	}

	public void handlerTxtEmergencyKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbFree.requestFocus();
		}
	}

	public void handlerTxtPhoneKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtEmergency.requestFocus();
		}

	}

	public void handlerRbFemalePressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {
			txtPhone.requestFocus();
		}

	}

	public void handlerRbMalePressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtPhone.requestFocus();
		}

	}

	public void handlerTxtNumberKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			rbMale.requestFocus();
		}

	}

	public void handlerTxtBanKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtNumber.requestFocus();
		}

	}

	public void handlerTxtYearKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtBan.requestFocus();
		}
	}

	public void handlerTxtNameKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtYear.requestFocus();
		}
	}

// 이미지 선택 창
	public void handlerBtnImageFileAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// 이미지 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		localimage = new Image(localUrl, false);

		imageView.setImage(localimage);
		imageView.setFitHeight(250);
		imageView.setFitWidth(230);

		btnRegister.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}

	// 출석부창 이동
	public void handlerBtnAttendanceActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adprintView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("출석부");
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.out.println("출석부 기능");
			System.err.println("오류 " + e);
		}

	}

	// 이메일창이동
	public void handlerBtnEmalActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emailView.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("이메일");
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류 " + e);
		}
	}

	// 수업창으로 이동
	public void handlerBtnbadmintonplay(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/badmintonplay.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("수업");
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainMtage.setScene(scane);
			mainMtage.show();
		} catch (IOException e) {
			System.err.print("오류" + e);
		}
	}

	// 초기화
	public void handlerBtnInitAction(ActionEvent event) {
		txtName.clear();
		txtName.setEditable(true);
		txtYear.clear();
		txtYear.setEditable(true);
		txtBan.clear();
		txtBan.setEditable(true);
		txtNumber.clear();
		txtNumber.setEditable(true);
		txtPhone.clear();
		txtEmergency.clear();
		txtTime.clear();
		txtTime.setEditable(true);
		txtExperience.clear();
		txtStartdate.clear();
		txtEnddate.clear();
		txtEmail.clear();

		// 기본 이미지
		localUrl = "/image/default.png";
		localimage = new Image(localUrl, false);
		imageView.setImage(localimage);

	}

	// 메인창 종료
	public void handlerBtnExitlActoion(ActionEvent event) {
		Platform.exit();
	}

}
