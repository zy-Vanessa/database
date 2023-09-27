--当一名学生申请转专业时，若申请的目标专业与当前专业相同，则转专业失败。
--否则该生转专业成功，维护对应专业的人数变化
--存储过程的两个参数为申请学生的学号stuno以及申请转入的专业的编号majorno
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
			--若转出专业与转入专业不同，则本次转专业操作有效
			--更新该生的专业信息
			update student
			set student.stumajor=(select majorno
									from major
									where major.majorno=@newmajor)
			where student.stuno=@applicantid

			--更新转出班级及转入班级的人数
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