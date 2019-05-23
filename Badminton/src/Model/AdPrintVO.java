package Model;

public class AdPrintVO {
	private int a_no;// 출석 코드
	private String a_come;// 출석여부
	private String a_day;// 출석날짜
	private int s_code;// 학생코드
	private String s_name; // 학생이름
	private int s_year; // 학년
	private int s_ban; // 반
	private int s_number; // 출석번호
	private int c_code; // 수업코드

	public AdPrintVO() {
		super();
	}
	

	public AdPrintVO(int s_code, String s_name, int s_year, int s_ban, int s_number, int c_code) {
		super();
		this.s_code = s_code;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.c_code = c_code;
	}


	// 출석코드 없는것
	public AdPrintVO(String a_come, String a_day, int s_code, String s_name, int s_year, int s_ban, int s_number,
			int c_code) {
		super();
		this.a_come = a_come;
		this.a_day = a_day;
		this.s_code = s_code;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.c_code = c_code;
	}

	// 출석코드,수업 코드 없는것
	public AdPrintVO(String a_come, String a_day, int s_code, String s_name, int s_year, int s_ban, int s_number) {
		super();
		this.a_come = a_come;
		this.a_day = a_day;
		this.s_code = s_code;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
	}

	// 다있는것
	public AdPrintVO(int a_no, String a_come, String a_day, int s_code, String s_name, int s_year, int s_ban,
			int s_number, int c_code) {
		super();
		this.a_no = a_no;
		this.a_come = a_come;
		this.a_day = a_day;
		this.s_code = s_code;
		this.s_name = s_name;
		this.s_year = s_year;
		this.s_ban = s_ban;
		this.s_number = s_number;
		this.c_code = c_code;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getA_come() {
		return a_come;
	}

	public void setA_come(String a_come) {
		this.a_come = a_come;
	}

	public String getA_day() {
		return a_day;
	}

	public void setA_day(String a_day) {
		this.a_day = a_day;
	}

	public int getS_code() {
		return s_code;
	}

	public void setS_code(int s_code) {
		this.s_code = s_code;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public int getS_year() {
		return s_year;
	}

	public void setS_year(int s_year) {
		this.s_year = s_year;
	}

	public int getS_ban() {
		return s_ban;
	}

	public void setS_ban(int s_ban) {
		this.s_ban = s_ban;
	}

	public int getS_number() {
		return s_number;
	}

	public void setS_number(int s_number) {
		this.s_number = s_number;
	}

	public int getC_code() {
		return c_code;
	}

	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	
	

}
