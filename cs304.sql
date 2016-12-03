drop table customer cascade constraints;
drop table book_ticket;
drop table passenger;
drop table routes;
drop table member;
drop table has_a_list_of;
drop table airline cascade constraints;
drop table HasAvailableSeats;
drop table OperateFlights cascade constraints;
drop view availableflights;


CREATE TABLE Customer(
cfname varchar(30),
clname varchar(30),
cred_num varchar(16),
phone_num varchar(12),
email varchar(50) primary key);

INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Alice', 'Alex', '5415902488556731', '16047838412', 'alicealex@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Bob', 'Bony', '231583920128345', '16044839212', 'bobbony@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Cicilia', 'Cathy', '5839204391923856', '16047382341', 'ciciliacathy@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Doug', 'Danny', '5948302349504023', '16044269483', 'dougdanny@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Emily', 'Elizabeth', '4257392813048563', '17783201938', 'emilyelizabeth@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Fisher', 'Fiber', '4257392814048563', '17783241938', 'fisherfiber@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Giggs', 'Gason', '4257395813048563', '16043201938', 'giggsgason@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Hallen', 'Hammer', '4257392813066563', '17782201938', 'hallenhammer@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Iris', 'Ilizabeth', '4257392817748563', '17783991938', 'irisilizabeth@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('Jason', 'Jeffery', '4257392812248563', '17783201008', 'jasonjeffery@gmail.com');
INSERT INTO Customer (cfname, clname, cred_num, phone_num, email)
VALUES ('King', 'Kingsman', '4257999813048563', '17783000938', 'kingkingsman@gmail.com');


CREATE TABLE routes(
dep_airport varchar(3),
arr_airport varchar(3),
dep_date date,
Constraint PK_Routes primary key (dep_airport, arr_airport, dep_date));

INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('YVR', 'PVG', to_date('20161201', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('PVG', 'YVR', to_date('20161202', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('YVR', 'HKG', to_date('20161203', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('HKG', 'YVR', to_date('20161204', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('YVR', 'JFK', to_date('20161205', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('JFK', 'YVR', to_date('20161206', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('PVG', 'JFK', to_date('20161207', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('JFK', 'PVG', to_date('20161208', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('JFK', 'HKG', to_date('20161209', 'YYYYMMDD'));
INSERT INTO routes(dep_airport, arr_airport, dep_date)
VALUES ('HKG', 'JKF', to_date('20161210', 'YYYYMMDD'));


CREATE TABLE Airline(
code varchar(3) primary key,
name varchar(20));

INSERT INTO Airline(code, name)
VALUES ('AC', 'Air Canada');
INSERT INTO Airline(code, name)
VALUES ('CA', 'Air China');
INSERT INTO Airline(code, name)
VALUES ('CX', 'Cathay Pacific');
INSERT INTO Airline(code, name)
VALUES ('AF', 'Air France');
INSERT INTO Airline(code, name)
VALUES ('NH', 'All Nippon Airways');
INSERT INTO Airline(code, name)
VALUES ('PH', 'Philippine Airlines');
INSERT INTO Airline(code, name)
VALUES ('NZ', 'Air New Zealand');
INSERT INTO Airline(code, name)
VALUES ('AS', 'Alaska Airlines');
INSERT INTO Airline(code, name)
VALUES ('EY', 'Etihad Airways');
INSERT INTO Airline(code, name)
VALUES ('FI', 'Icelandair');

CREATE TABLE Member(
email varchar(50) primary key references Customer(email),
point integer);

INSERT INTO Member(email, point)
VALUES ('alicealex@gmail.com', 1000);
INSERT INTO Member(email, point)
VALUES ('bobbony@gmail.com', 2000);
INSERT INTO Member(email, point) 
VALUES ('ciciliacathy@gmail.com', 3000);
INSERT INTO Member(email, point)
VALUES ('dougdanny@gmail.com', 4000);
INSERT INTO Member(email, point)
VALUES ('emilyelizabeth@gmail.com', 5000);
INSERT INTO Member(email, point)
VALUES ('fisherfiber@gmail.com', 6000);
INSERT INTO Member(email, point)
VALUES ('giggsgason@gmail.com', 7000);
INSERT INTO Member(email, point)
VALUES ('hallenhammer@gmail.com', 8000);
INSERT INTO Member(email, point)
VALUES ('irisilizabeth@gmail.com', 9000);
INSERT INTO Member(email, point)
VALUES ('kingkingsman@gmail.com', 10000);

CREATE TABLE OperateFlights(
code varchar(2),
flight_num varchar(4),
dep_date date,
dep_time varchar(5),
arr_date date,
arr_time varchar(5),
dep_airport varchar(3),
arr_airport varchar(3),
CONSTRAINT PK_operateFilights primary key (code, flight_num),
Constraint fk_operateflights foreign key (code) references Airline(code)
ON DELETE CASCADE);

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AC', 'AC12', to_date('20161201', 'YYYYMMDD'), '23:25', to_date('20161202', 'YYYYMMDD'), '07:30', 'YVR', 'HKG');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AC', 'AC23', to_date('20161202', 'YYYYMMDD'), '22:25', to_date('20161202', 'YYYYMMDD'), '19:30', 'YVR', 'HKG');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AC', 'AC34', to_date('20161203', 'YYYYMMDD'), '23:36', to_date('20161203', 'YYYYMMDD'), '20:30', 'MNL', 'YVR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AC', 'AC45', to_date('20161204', 'YYYYMMDD'), '19:25', to_date('20161205', 'YYYYMMDD'), '20:30', 'MNL', 'HKG');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AC', 'AC56', to_date('20161205', 'YYYYMMDD'), '10:25', to_date('20161205', 'YYYYMMDD'), '20:05', 'HKG', 'MNL');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CA', 'CA24', to_date('20161101', 'YYYYMMDD'), '11:25', to_date('20161102', 'YYYYMMDD'), '12:30', 'YYZ', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CA', 'CA52', to_date('20161102', 'YYYYMMDD'), '13:25', to_date('20161103', 'YYYYMMDD'), '14:30', 'LHX', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CA', 'CA12', to_date('20161103', 'YYYYMMDD'), '15:36', to_date('20161104', 'YYYYMMDD'), '17:30', 'MNL', 'PEK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CA', 'CA23', to_date('20161104', 'YYYYMMDD'), '01:25', to_date('20161105', 'YYYYMMDD'), '02:30', 'SFO', 'YVR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CA', 'CA48', to_date('20161105', 'YYYYMMDD'), '04:25', to_date('20161106', 'YYYYMMDD'), '05:05', 'YVR', 'LAX');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CX', 'CX01', to_date('20161001', 'YYYYMMDD'), '10:25', to_date('20161002', 'YYYYMMDD'), '11:30', 'FRA', 'YVR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CX', 'CX25', to_date('20161002', 'YYYYMMDD'), '14:25', to_date('20161002', 'YYYYMMDD'), '17:30', 'JFK', 'SIN');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CX', 'CX37', to_date('20161003', 'YYYYMMDD'), '22:11', to_date('20161003', 'YYYYMMDD'), '23:30', 'HKG', 'SIN');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CX', 'CX59', to_date('20161004', 'YYYYMMDD'), '09:25', to_date('20161005', 'YYYYMMDD'), '10:30', 'ICN', 'KUL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('CX', 'CX76', to_date('20161005', 'YYYYMMDD'), '05:25', to_date('20161005', 'YYYYMMDD'), '06:05', 'ICN', 'HND');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AF', 'AF95', to_date('20160901', 'YYYYMMDD'), '20:25', to_date('20160902', 'YYYYMMDD'), '21:30', 'LAD', 'MIA');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AF', 'AF38', to_date('20160902', 'YYYYMMDD'), '08:25', to_date('20160903', 'YYYYMMDD'), '09:30', 'YVR', 'MIA');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AF', 'AF10', to_date('20160903', 'YYYYMMDD'), '11:36', to_date('20160904', 'YYYYMMDD'), '02:30', 'HKG', 'KUL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AF', 'AF48', to_date('20160904', 'YYYYMMDD'), '20:25', to_date('20160905', 'YYYYMMDD'), '23:30', 'ICN', 'BKK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AF', 'AF60', to_date('20160905', 'YYYYMMDD'), '03:25', to_date('20160906', 'YYYYMMDD'), '08:05', 'BKK', 'MNL');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NH', 'NH23', to_date('20160801', 'YYYYMMDD'), '10:25', to_date('20160802', 'YYYYMMDD'), '01:30', 'YVR', 'LGW');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NH', 'NH74', to_date('20160802', 'YYYYMMDD'), '03:25', to_date('20160803', 'YYYYMMDD'), '09:30', 'TPE', 'MNL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NH', 'NH73', to_date('20160803', 'YYYYMMDD'), '15:36', to_date('20160804', 'YYYYMMDD'), '16:30', 'ICN', 'TPE');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NH', 'NH27', to_date('20160804', 'YYYYMMDD'), '18:25', to_date('20160805', 'YYYYMMDD'), '19:30', 'NRT', 'MNL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NH', 'NH98', to_date('20160805', 'YYYYMMDD'), '04:25', to_date('20160806', 'YYYYMMDD'), '10:05', 'SHA', 'NRT');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('PH', 'PH11', to_date('20160801', 'YYYYMMDD'), '11:25', to_date('20160802', 'YYYYMMDD'), '11:30', 'LAD', 'LGW');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('PH', 'PH22', to_date('20160802', 'YYYYMMDD'), '13:25', to_date('20160803', 'YYYYMMDD'), '19:30', 'JFK', 'MNL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('PH', 'PH33', to_date('20160803', 'YYYYMMDD'), '05:36', to_date('20160804', 'YYYYMMDD'), '06:30', 'ICN', 'TPE');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('PH', 'PH44', to_date('20160804', 'YYYYMMDD'), '08:25', to_date('20160805', 'YYYYMMDD'), '09:30', 'HKG', 'MNL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('PH', 'PH55', to_date('20160805', 'YYYYMMDD'), '14:25', to_date('20160806', 'YYYYMMDD'), '20:05', 'SFO', 'NRT');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NZ', 'NZ12', to_date('20160801', 'YYYYMMDD'), '10:21', to_date('20160802', 'YYYYMMDD'), '01:35', 'YVR', 'LAD');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NZ', 'NZ66', to_date('20160802', 'YYYYMMDD'), '03:27', to_date('20160803', 'YYYYMMDD'), '09:35', 'TPE', 'JFK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NZ', 'NZ77', to_date('20160803', 'YYYYMMDD'), '15:39', to_date('20160804', 'YYYYMMDD'), '16:35', 'ICN', 'NRT');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NZ', 'NZ88', to_date('20160804', 'YYYYMMDD'), '18:15', to_date('20160805', 'YYYYMMDD'), '19:35', 'NRT' , 'KUL');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('NZ', 'NZ99', to_date('20160805', 'YYYYMMDD'), '04:44', to_date('20160806', 'YYYYMMDD'), '10:55', 'SHA', 'SIN');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AS', 'AS10', to_date('20161101', 'YYYYMMDD'), '12:25', to_date('20161102', 'YYYYMMDD'), '12:30', 'SHA', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AS', 'AS20', to_date('20161102', 'YYYYMMDD'), '14:25', to_date('20161103', 'YYYYMMDD'), '14:30', 'NRT', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AS', 'AS30', to_date('20161103', 'YYYYMMDD'), '17:36', to_date('20161104', 'YYYYMMDD'), '18:30', 'ICN', 'PEK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AS', 'AS40', to_date('20161104', 'YYYYMMDD'), '11:25', to_date('20161105', 'YYYYMMDD'), '12:30', 'TPE', 'YVR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('AS', 'AS50', to_date('20161105', 'YYYYMMDD'), '14:25', to_date('20161106', 'YYYYMMDD'), '15:05', 'ICN', 'LAX');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('EY', 'EY00', to_date('20160801', 'YYYYMMDD'), '15:25', to_date('20160802', 'YYYYMMDD'), '11:30', 'YVR', 'LAX');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('EY', 'EY01', to_date('20160802', 'YYYYMMDD'), '13:25', to_date('20160803', 'YYYYMMDD'), '19:30', 'TPE', 'YVR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('EY', 'EY03', to_date('20160803', 'YYYYMMDD'), '18:36', to_date('20160804', 'YYYYMMDD'), '16:35', 'ICN', 'PEK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('EY', 'EY05', to_date('20160804', 'YYYYMMDD'), '10:25', to_date('20160805', 'YYYYMMDD'), '19:35', 'NRT', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('EY', 'EY07', to_date('20160805', 'YYYYMMDD'), '14:25', to_date('20160806', 'YYYYMMDD'), '10:34', 'SHA', 'KUL');

INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('FI', 'FI91', to_date('20161001', 'YYYYMMDD'), '14:25', to_date('20161002', 'YYYYMMDD'), '11:40', 'FRA', 'LAX');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('FI', 'FI95', to_date('20161002', 'YYYYMMDD'), '11:25', to_date('20161002', 'YYYYMMDD'), '17:30', 'JFK', 'PEK');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('FI', 'FI97', to_date('20161003', 'YYYYMMDD'), '15:11', to_date('20161003', 'YYYYMMDD'), '23:20', 'HKG', 'LHR');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('FI', 'FI99', to_date('20161004', 'YYYYMMDD'), '19:25', to_date('20161005', 'YYYYMMDD'), '10:10', 'ICN', 'LAD');
INSERT INTO OperateFlights(code, flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport)
VALUES ('FI', 'FI96', to_date('20161005', 'YYYYMMDD'), '15:25', to_date('20161005', 'YYYYMMDD'), '16:05', 'ICN', 'MNL');



CREATE TABLE HasAvailableSeats(
code varchar(2),
flight_num varchar(4),
class_name varchar(15),
price integer,
total_num integer,
Constraint pk_hasavailableseats primary key (flight_num, code, class_name),
Constraint fk_hasavailableseats foreign key (flight_num, code) references OperateFlights(flight_num, code)
ON DELETE CASCADE);

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('AC', 'AC12', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AC', 'AC23', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('AC', 'AC34', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AC', 'AC45', 'Economy', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AC', 'AC56', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('CA', 'CA24', 'First', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CA', 'CA52', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('CA', 'CA12', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CA', 'CA23', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CA', 'CA48', 'Economy', '1500', '300');


INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('CX', 'CX01', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CX', 'CX25', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('CX', 'CX37', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CX', 'CX59', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('CX', 'CX76', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('AF', 'AF95', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AF', 'AF38', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('AF', 'AF10', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AF', 'AF48', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AF', 'AF60', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('NH', 'NH23', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NH', 'NH74', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('NH', 'NH73', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NH', 'NH27', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NH', 'NH98', 'Economy', '1500', '300');


INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('PH', 'PH11', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('PH', 'PH22', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('PH', 'PH33', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('PH', 'PH44', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('PH', 'PH55', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('NZ', 'NZ12', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NZ', 'NZ66', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('NZ', 'NZ77', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NZ', 'NZ88', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('NZ', 'NZ99', 'Economy', '1500', '300');


INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('AS', 'AS10', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AS', 'AS20', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('AS', 'AS30', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AS', 'AS40', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('AS', 'AS50', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('EY', 'EY00', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('EY', 'EY01', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('EY', 'EY03', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('EY', 'EY05', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('EY', 'EY07', 'Economy', '1500', '300');

INSERT INTO HasAvailableSeats(code, flight_num, class_name, price, total_num)
VALUES ('FI', 'FI91', 'Business', '2000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('FI', 'FI95', 'Economy', '1000', '300');
INSERT INTO HasAvailableSeats(code, flight_num,class_name, price, total_num)
VALUES ('FI', 'FI97', 'First', '3000', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('FI', 'FI99', 'Business', '1500', '300');
INSERT INTO HasAvailableSeats(code,flight_num,class_name, price, total_num)
VALUES ('FI', 'FI96', 'Economy', '1500', '300');


CREATE TABLE Book_Ticket(
email varchar(50),
conf_num varchar(15),
flight_num varchar(4),
class_name varchar(15),
code varchar(2),
constraint PK_Book_Ticket primary key (email, conf_num),
CONSTRAINT fk_Book_Ticket foreign key (email) references Customer(email),
CONSTRAINT fk_book_ticket2 foreign key (code, flight_num) references OperateFlights (code, flight_num),
CONSTRAINT fk_book_ticket3 foreign key (code, flight_num, class_name) references HasAvailableSeats (code, flight_num, class_name)
ON DELETE CASCADE);

INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('alicealex@gmail.com', '0000000001', 'AC12', 'Business', 'AC');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('bobbony@gmail.com', '0000000002', 'CA24', 'First', 'CA');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('ciciliacathy@gmail.com', '0000000003', 'CX01', 'Business','CX');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('dougdanny@gmail.com', '0000000004', 'AF10', 'First', 'AF');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('emilyelizabeth@gmail.com', '0000000005', 'NH23', 'Business', 'NH');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('fisherfiber@gmail.com', '0000000006', 'PH22', 'Economy', 'PH');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('giggsgason@gmail.com', '0000000007', 'NZ12', 'Business', 'NZ');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('hallenhammer@gmail.com', '0000000008', 'AS50', 'Economy', 'AS');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('irisilizabeth@gmail.com', '0000000009', 'EY07', 'Economy', 'EY');
INSERT INTO Book_Ticket (email, conf_num, flight_num, class_name, code)
VALUES ('kingkingsman@gmail.com', '0000000010', 'FI99', 'Business', 'FI');



CREATE TABLE Passenger(
pass_num varchar(15) primary key,
gender varchar(1),
pfname varchar(30) NOT NULL, 
plname varchar(30),
code varchar(2),
flight_num varchar(4),
conf_num varchar(15),
CONSTRAINT fk_passenger foreign key (code, flight_num) references OperateFlights (code, flight_num)
ON DELETE CASCADE);

INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000001', 'F', 'Alice', 'Alex', 'AC', 'AC12', '0000000001');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000002', 'M', 'Bob', 'Bony', 'CA', 'CA24', '0000000002');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000003', 'F', 'Cicilia', 'Cathy', 'CX', 'CX01', '0000000003');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000004', 'M', 'Doug', 'Danny', 'AF', 'AF10', '0000000004');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000005', 'F', 'Emily', 'Elizabeth', 'NH', 'NH23', '0000000005');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000006', 'M', 'Fisher', 'Fiber', 'PH', 'PH22', '0000000006');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000007', 'M', 'Giggs', 'Gason', 'NZ', 'NZ12', '0000000007');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000008', 'F', 'Hallen', 'Hammer', 'AS', 'AS50', '0000000008');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000009', 'F', 'Iris', 'Ilizabeth', 'EY', 'EY07', '0000000009');
INSERT INTO Passenger (pass_num, gender, pfname, plname, code, flight_num, conf_num)
VALUES ('E00000010', 'M', 'King', 'Kingsman', 'FI', 'FI99', '0000000010');

CREATE TABLE has_a_list_of(
pass_num varchar(15),
flight_num varchar(4),
code varchar(2),
Constraint pk_hasalistof primary key (pass_num, flight_num, code),
Constraint fk_hasalistof foreign key (flight_num, code) references OperateFlights(flight_num, code)
ON DELETE CASCADE);

INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000001', 'AC12','AC');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000002', 'CA24','CA');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000003', 'CX01','CX');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000004', 'AF10','AF');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000005', 'NH23','NH');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000006', 'PH22','PH');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000007', 'NZ12','NZ');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000008', 'AS50','AS');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E00000009', 'EY07','EY');
INSERT INTO has_a_list_of (pass_num, flight_num, code)
VALUES ('E000000010', 'FI99','FI');

create view Availableflights as 
  select o.code, o.flight_num, dep_date, dep_time, arr_date, arr_time, dep_airport, arr_airport, class_name, price
  from operateflights o inner join hasavailableseats h 
  on o.code= h.code and o.flight_num = h.flight_num and h.total_num > 0;
