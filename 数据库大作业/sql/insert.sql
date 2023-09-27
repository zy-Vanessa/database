--通过输入stuno,stuname,stumajor插入新生信息
--若该stuno已经存在于当前的stu表的stuno中，则证明不是新生，不能插入，事务回滚
--若该stuno不存在于当前的stu表的stuno中，但stumajor也不存在于当前的major表的majorno中，则该新生专业错误，不能插入，事务回滚
--如果该stuno不存在于当前的stu表的stuno中，且stumajor存在于当前的major表的majorno中
--则正常插入，并对所对应专业的专业人数进行维护，即majorstunum+1

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
		--若该生学号已存在于student表中
		begin
			print 'student exist'
			rollback transaction insert_stu
		end
	else
		--若该生学号未存在于student表中
		if(@stumajor_insert not in(select majorno from major))
			--若该生专业未存在于major表中
			begin
				print 'majorno not exist'
				rollback transaction insert_stu
			end
		else
			--若该生专业存在于major表中
			begin
				--在student表中插入该生信息
				insert into student
				values(@stuno_insert,@stuname_insert,@stumajor_insert)

				--维护major表中的majorstunum信息
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