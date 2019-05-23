package Model;

public class BadmintonplayVO {
	private int c_code ;//수업코드
	private int s_code ;//학생코드
	private int t_code;// 선생님 코드
	private String c_day; // 수업 날짜
	private String c_level;//수업 레벨
	private String c_time; //수업 시간
	private String c_content; //수업 내용
	
	
	
	public BadmintonplayVO() {
		super();
	}

	

	public BadmintonplayVO(String c_level, String c_time, String c_content) {
		super();
		this.c_level = c_level;
		this.c_time = c_time;
		this.c_content = c_content;
	}





	public BadmintonplayVO(int c_code, int s_code, int t_code, String c_day, String c_level, String c_time,
			String c_content) {
		super();
		this.c_code = c_code;
		this.s_code = s_code;
		this.t_code = t_code;
		this.c_day = c_day;
		this.c_level = c_level;
		this.c_time = c_time;
		this.c_content = c_content;
	}


	public BadmintonplayVO(int s_code, int t_code, String c_day, String c_level, String c_time, String c_content) {
		super();
		this.s_code = s_code;
		this.t_code = t_code;
		this.c_day = c_day;
		this.c_level = c_level;
		this.c_time = c_time;
		this.c_content = c_content;
	}

	public BadmintonplayVO(int c_code, String c_day, String c_level, String c_time, String c_content) {
		super();
		this.c_code = c_code;
		this.c_day = c_day;
		this.c_level = c_level;
		this.c_time = c_time;
		this.c_content = c_content;
	}

	
	
	


	//접근자와 설정자

	


	public int getC_code() {
		return c_code;
	}



	public String getC_level() {
		return c_level;
	}




	public void setC_level(String c_level) {
		this.c_level = c_level;
	}




	public void setC_code(int c_code) {
		this.c_code = c_code;
	}



	public int getS_code() {
		return s_code;
	}



	public void setS_code(int s_code) {
		this.s_code = s_code;
	}



	public int getT_code() {
		return t_code;
	}



	public void setT_code(int t_code) {
		this.t_code = t_code;
	}



	public String getC_day() {
		return c_day;
	}



	public void setC_day(String c_day) {
		this.c_day = c_day;
	}



	public String getC_time() {
		return c_time;
	}



	public void setC_time(String c_time) {
		this.c_time = c_time;
	}



	public String getC_content() {
		return c_content;
	}



	public void setC_content(String c_content) {
		this.c_content = c_content;
	}



	
	
}
