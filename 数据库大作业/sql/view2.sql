create view ѧ��רҵ��� as
	select student.stuno as ѧ�����,
		student.stuname as ѧ������,
		major.majorno as רҵ���,
		major.majorname as רҵ����,
		major.majorstunum as רҵ����
	from student,major
	where  student.stumajor=major.majorno

go