create view 学生选课情况 as
	select student.stuno as 学生编号,
		student.stuname as 学生姓名,
		major.majorname as 专业名称,
		major.majorstunum as 专业人数,
		course.courseno as 课程编码,
		course.coursename as 课程名称,
		coursechoice.score as 课程成绩
	from student,course,major,coursechoice
	where student.stuno=coursechoice.stu
		and student.stumajor=major.majorno
		and coursechoice.course = course.courseno
go