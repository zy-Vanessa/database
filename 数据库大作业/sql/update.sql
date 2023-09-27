--��һ��ѧ������תרҵʱ���������Ŀ��רҵ�뵱ǰרҵ��ͬ����תרҵʧ�ܡ�
--�������תרҵ�ɹ���ά����Ӧרҵ�������仯
--�洢���̵���������Ϊ����ѧ����ѧ��stuno�Լ�����ת���רҵ�ı��majorno
create procedure change_major_proc
	@applicantid char(10) output,@goalmajor char(10) output
as
begin
	declare @oldmajor char(10)
	declare @newmajor char(10)
	set @oldmajor=(select major.majorno
					from student,major
					where student.stumajor=major.majorno
						and student.stuno=@applicantid)
	set @newmajor=@goalmajor

	if(@oldmajor<>@newmajor)
		begin
			--��ת��רҵ��ת��רҵ��ͬ���򱾴�תרҵ������Ч
			--���¸�����רҵ��Ϣ
			update student
			set student.stumajor=(select majorno
									from major
									where major.majorno=@newmajor)
			where student.stuno=@applicantid

			--����ת���༶��ת��༶������
			update major
			set major.majorstunum=major.majorstunum-1
			where major.majorno=(select majorno
									from major
									where major.majorno=@oldmajor)

			update major
			set major.majorstunum=major.majorstunum+1
			where major.majorno=(select majorno
									from major
									where major.majorno=@newmajor)
		end
end