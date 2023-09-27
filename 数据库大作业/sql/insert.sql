--ͨ������stuno,stuname,stumajor����������Ϣ
--����stuno�Ѿ������ڵ�ǰ��stu���stuno�У���֤���������������ܲ��룬����ع�
--����stuno�������ڵ�ǰ��stu���stuno�У���stumajorҲ�������ڵ�ǰ��major���majorno�У��������רҵ���󣬲��ܲ��룬����ع�
--�����stuno�������ڵ�ǰ��stu���stuno�У���stumajor�����ڵ�ǰ��major���majorno��
--���������룬��������Ӧרҵ��רҵ��������ά������majorstunum+1

create trigger insert_student on student
instead of insert
as
begin transaction insert_stu
save transaction insert_stu

	declare @stuno_insert char(10)
	declare @stuname_insert char(20)
	declare @stumajor_insert char(10)
	set @stuno_insert=(select stuno from inserted)
	set @stuname_insert=(select stuname from inserted)
	set @stumajor_insert=(select stumajor from inserted)

	if(@stuno_insert in (select stuno from student))
		--������ѧ���Ѵ�����student����
		begin
			print 'student exist'
			rollback transaction insert_stu
		end
	else
		--������ѧ��δ������student����
		if(@stumajor_insert not in(select majorno from major))
			--������רҵδ������major����
			begin
				print 'majorno not exist'
				rollback transaction insert_stu
			end
		else
			--������רҵ������major����
			begin
				--��student���в��������Ϣ
				insert into student
				values(@stuno_insert,@stuname_insert,@stumajor_insert)

				--ά��major���е�majorstunum��Ϣ
				update major
				set major.majorstunum=major.majorstunum+1
				where major.majorno=@stumajor_insert

			end
if @@ERROR<>0
	begin
		rollback transaction insert_stu
	end
else 
	begin
		commit transaction insert_stu
	end