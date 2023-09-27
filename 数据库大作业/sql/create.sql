--实体
create table course(
	courseno char(10) primary key not null,
	coursename char(20) not null,
	coursestumax int not null,
	coursetime char(20)not null,
	coursespot char(20)not null
);

create table major(
	majorno char(10) primary key not null,
	majorname char(20) not null,
	majorstunum int not null
);

create table student(
	stuno char(10) primary key not null,
	stuname char(20) not null,
	stumajor char(10) references major(majorno) not null,
	stuage int,
	stusex char(5)
);

create table college(
    collegeno char(10) primary key not null,
	collegename char(20) not null,
	college_major char(10) references major(majorno) not null,
);

create table teacher(
    teacherno char(10)primary key not null,
	teachername char(10)not null,
	teacher_sex char(5)not null,
	teacher_age int,
	teacher_college char(10) references college(collegeno)not null
)

--联系
create table coursechoice(
	stu char(10) references student(stuno) not null,
	course char(10) references course(courseno) not null,
	score int,
	primary key(stu,course)
);

create table teach(
    teacher char(10) references teacher(teacherno) not null,
	teach_class char(10) references course(courseno) not null,
	primary key(teacher,teach_class)
)
