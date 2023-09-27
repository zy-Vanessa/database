create view 学生专业情况 as
	select student.stuno as 学生编号,
		student.stuname as 学生姓名,
		major.majorno as 专业编号,
		major.majorname as 专业名称,
		major.majorstunum as 专业人数
	from student,major
	where  student.stumajor=major.majorno

go