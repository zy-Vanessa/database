--通过输入courseno删除course表中的课程信息
--若该课程不在course表中，不能删除，事务回滚
--若该课程在course表中，同时也在coursechoice表中，则现将coursechoice表中相关信息删除，再删除course表中相关信息
--通过这一操作，说明创建的触发器是在发挥作用的，因为如果没有这个触发器，由于外键约束，删除操作将无法进行
--若该课程在course表中，而不在coursechoice表中，则直接删除
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
		--若被删除的课程现正有学生选课，不能删除，事务回滚
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