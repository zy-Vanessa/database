--ͨ������coursenoɾ��course���еĿγ���Ϣ
--���ÿγ̲���course���У�����ɾ��������ع�
--���ÿγ���course���У�ͬʱҲ��coursechoice���У����ֽ�coursechoice���������Ϣɾ������ɾ��course���������Ϣ
--ͨ����һ������˵�������Ĵ��������ڷ������õģ���Ϊ���û��������������������Լ����ɾ���������޷�����
--���ÿγ���course���У�������coursechoice���У���ֱ��ɾ��
create trigger delete_course on course
instead of delete
as

begin transaction delete_cour
save transaction delete_cour

	declare @courseno_deleted char(10)
	declare @coursename_deleted char(20)
	declare @coursestumax_deleted int
	declare @coursetime_deleted char(20)

	set @courseno_deleted=(select courseno from deleted)
	set @coursename_deleted=(select coursename from deleted)
	set @coursestumax_deleted=(select coursestumax from deleted)
	set @coursetime_deleted=(select coursetime from deleted)

	if(@courseno_deleted in (select course from coursechoice))
		--����ɾ���Ŀγ�������ѧ��ѡ�Σ�����ɾ��������ع�
		begin
			delete from coursechoice
			where coursechoice.course=@courseno_deleted

			delete from teach
			where  teach.teach_class=@courseno_deleted

			delete from course
			where course.courseno=@courseno_deleted
		end
	else
		begin
		    delete from teach
			where teach.teach_class=@courseno_deleted

			delete from course
			where course.courseno=@courseno_deleted
		end
if @@ERROR<>0
	begin
		rollback transaction delete_cour
	end
else 
	begin
		commit transaction delete_cour
	end