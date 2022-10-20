/*Crear una base de datos*/
create database WebUsers

/*Usar la base de datos*/
use WebUsers

/*Crear tabla users*/
create table users 
(
	Id int,
	Name varchar(50),
	LastName varchar(50),
	Phone int,    
	Email varchar(50),
	Address varchar(100)
)

/*Crear un user*/
insert into users (Id, Name, LastName, Phone, Email, Address) values (1, 'John', 'Doe', 123, 'john.doe@gmail.com', 'Miami, FL')

insert into users (Id, Name, LastName, Phone, Email, Address)
values (2, 'Will', 'Smith', 124, 'will.smith@gmail.com', 'Tampa, FL')

insert into users (Id, Name, LastName, Phone, Email, Address) 
values (4, 'Sthepen', 'Berg', 125, 'step.berg@gmail.com', 'Scottdale, AR')

/*Obtener los user*/
select * from users

/*Borrar un registro*/
delete from users where Id = 3 and Name = 'Sthepen'

/*Update un user*/
update users set Name = 'Jason' where Id = 3 and Name = 'Sthepen'
