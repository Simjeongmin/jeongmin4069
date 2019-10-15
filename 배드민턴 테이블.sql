create table attendane( --�⼮��
a_no NUMBER(10), --no
a_come VARCHAR2(15)  not null, --�⼮����
a_day VARCHAR2(20) DEFAULT SYSDATE , --�⼮��¥
s_code number, --�����ڵ�
foreign key(s_code) references student(s_code),
primary key(a_no)
);

create table student( --�л�
S_code number(2),--�л��ڵ�
S_name VARCHAR2(16) not null, --�л��̸�
S_year number(2) not null, --�г�
S_ban number(2) not null, --��
S_number number(2) not null,--�⼮��ȣ
S_gender VARCHAR2(16) not null, --����
S_phone VARCHAR2(16) not null, --�����޴���
S_emergency VARCHAR2(16) not null, --��󿬶���
S_costfree VARCHAR2(16) not null, --���ṫ��
S_time number(1) not null, --�����ð�
S_experience VARCHAR2(10) not null, --���ΰ���
S_level VARCHAR2(10) not null, --����
S_startdate VARCHAR2(10) not null, --������
S_enddate VARCHAR2(30) not null, --������
S_email VARCHAR2(30) not null, --�θ���̸���
S_image VARCHAR2(100) ,--�̹���
s_come NUMBER(2), --�л� �⼮ ����
primary key(s_code)
);

create table teacher( --������
t_code NUMBER(2), --������ �ڵ�
t_name VARCHAR2(7) not null, --�������̸�
t_subject varchar2(10) not null, --������
t_id varchar2(6) not null,
t_pw varchar2(8) not null,
primary key(t_code)
);


create table badmintonplay( --����
c_code NUMBER(2), --���� �ڵ�
s_code NUMBER(2), --�л� �ڵ�
t_code NUMBER(2), --������ �ڵ�
c_day varchar2(20) DEFAULT sysdate, --��������
c_level varchar2(20) not null,--���� ����
c_time varchar2(30) not null,--���� �ð�
c_content varchar2(30) not null, --��������
foreign key(s_code) references student(s_code),
foreign key(t_code) references teacher(t_code),
primary key(c_code)
);

--������
create sequence badmintonplay_seq --����
start with 1
increment by 1;
create sequence attendane_seq --�⼮��
start with 1
increment by 1;
create sequence student_seq --�л�
start with 1
increment by 1;
