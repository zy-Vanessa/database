create view ѧ��ѡ����� as
	select student.stuno as ѧ�����,
		student.stuname as ѧ������,
		major.majorname as רҵ����,
		major.majorstunum as רҵ����,
		course.courseno as �γ̱���,
		course.coursename as �γ�����,
		coursechoice.score as �γ̳ɼ�
	from student,course,major,coursechoice
	where student.stuno=coursechoice.stu
		and student.stumajor=major.majorno
		and coursechoice.course = course.courseno
go