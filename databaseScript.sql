desc heal.patients;
desc heal.doctors;
desc heal.admin;
desc heal.login;
desc heal.review;
desc heal.doctors_schedule;

alter table doctors_schedule
	add column seats int not null default 50;

alter table doctors_schedule
	modify column fees int not null default 0;

alter table doctors_schedule
	modify column davailable varchar(30) not null default "Y";

select * from patients;
select * from login;
select * from admin;
select * from doctors;
select * from review;


Alter table heal.appointment
Add Column paymentId varchar(30);


Alter table heal.appointment
Add Constraint fk_appointment_paymentid
foreign key(paymentid)
References heal.payment(paymentid)

-- Insert Trigger

delimiter #
create trigger PatientsTrigger after insert on Patients
	for each row
    begin
		insert into login(username,password,usertype) values (new.pemail,new.ppassword,"patient");
    end#
delimiter ;

delimiter #
create trigger DoctorsTrigger after insert on doctors
	for each row
    begin
		insert into login(username,password,usertype) values (new.demail,new.dpassword,"doctor");
    end#
delimiter ;

delimiter #
create trigger AdminTrigger after insert on Admin
	for each row
    begin
		insert into login(username,password,usertype) values (new.aemail,new.apassword,"admin");
    end#
delimiter ;

delimiter #
create trigger ReviewTrigger after insert on Review
	for each row
    begin
		update doctors d join (select did, avg(rating) as avgrating from review r group by did) r on d.did = r.did set d.drating = r.avgrating;
    end#
delimiter ;

-- Update Trigger
delimiter #
create trigger AdminTriggerUpdate after update on Admin
	for each row
    begin
		update login set username=new.aemail,password=new.apassword where username=old.aemail;
    end#
delimiter ;

delimiter #
create trigger DoctorsTriggerUpdate after update on doctors
	for each row
    begin
		update login set username=new.demail,password=new.dpassword where username=old.demail;
    end#
delimiter ;

delimiter #
create trigger PatientsTriggerUpdate after update on Patients
	for each row
    begin
		update login set username=new.pemail,password=new.ppassword where username=old.pemail;
    end#
delimiter ;

-- Delete Trigger
delimiter #
create trigger AdminTriggerDelete after delete on Admin
	for each row
    begin
		delete from login where username=old.aemail;
    end#
delimiter ;

delimiter #
create trigger DoctorsTriggerDelete after delete on doctors
	for each row
    begin
		delete from login where username=old.demail;
    end#
delimiter ;

delimiter #
create trigger PatientsTriggerDelete after delete on Patients
	for each row
    begin
		delete from login where username=old.pemail;
    end#
delimiter ;


SET SQL_SAFE_UPDATES=0;
delete from heal.login;
SET SQL_SAFE_UPDATES=1;
select * from heal.login;

SET SQL_SAFE_UPDATES=0;
delete from heal.admin;
SET SQL_SAFE_UPDATES=1;
select * from heal.admin;

SET SQL_SAFE_UPDATES=0;
delete from heal.doctors;
SET SQL_SAFE_UPDATES=1;
select * from heal.doctors;

SET SQL_SAFE_UPDATES=0;
delete from heal.patients;
SET SQL_SAFE_UPDATES=1;
select * from heal.patients;