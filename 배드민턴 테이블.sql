create table attendane( --출석부
a_no NUMBER(10), --no
a_come VARCHAR2(15)  not null, --출석여부
a_day VARCHAR2(20) DEFAULT SYSDATE , --출석날짜
s_code number, --수업코드
foreign key(s_code) references student(s_code),
primary key(a_no)
);

create table student( --학생
S_code number(2),--학생코드
S_name VARCHAR2(16) not null, --학생이름
S_year number(2) not null, --학년
S_ban number(2) not null, --반
S_number number(2) not null,--출석번호
S_gender VARCHAR2(16) not null, --성별
S_phone VARCHAR2(16) not null, --본인휴대폰
S_emergency VARCHAR2(16) not null, --비상연락망
S_costfree VARCHAR2(16) not null, --유료무료
S_time number(1) not null, --수업시간
S_experience VARCHAR2(10) not null, --본인경험
S_level VARCHAR2(10) not null, --레벨
S_startdate VARCHAR2(10) not null, --시작일
S_enddate VARCHAR2(30) not null, --종료일
S_email VARCHAR2(30) not null, --부모님이메일
S_image VARCHAR2(100) ,--이미지
s_come NUMBER(2), --학생 출석 여부
primary key(s_code)
);

create table teacher( --선생님
t_code NUMBER(2), --선생님 코드
t_name VARCHAR2(7) not null, --선생님이름
t_subject varchar2(10) not null, --담당과목
t_id varchar2(6) not null,
t_pw varchar2(8) not null,
primary key(t_code)
);


create table badmintonplay( --수업
c_code NUMBER(2), --수업 코드
s_code NUMBER(2), --학생 코드
t_code NUMBER(2), --선생님 코드
c_day varchar2(20) DEFAULT sysdate, --수업요일
c_level varchar2(20) not null,--수업 레벨
c_time varchar2(30) not null,--수업 시간
c_content varchar2(30) not null, --수업내용
foreign key(s_code) references student(s_code),
foreign key(t_code) references teacher(t_code),
primary key(c_code)
);

--시퀀스
create sequence badmintonplay_seq --수업
start with 1
increment by 1;
create sequence attendane_seq --출석부
start with 1
increment by 1;
create sequence student_seq --학생
start with 1
increment by 1;
