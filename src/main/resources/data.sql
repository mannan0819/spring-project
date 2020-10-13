INSERT INTO User (ID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD) VALUES (30,'Mannan','Arain','mannan','password');
INSERT INTO User (ID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD) VALUES (9,'Joe','Random','joe','password');


INSERT INTO COMMENT (ID, BODY, CREATED, EDITED, USERNAME, USRID, TICKETID) VALUES (1,'This sis the body','2018-02-02','2018-02-03','mannan', 1,1);

INSERT INTO Ticket (ID, TITLE, AUTHOR, CREATED, EDITED, STATUS, PRIORITY, DESCRIPTION, GROUPID, FILENAME, ISIMG) VALUES 
(1,'Ticket 1', 'Mannan','2018-02-02','2018-02-03','1','2','This is a ticket. and its a description',2,'name',True);
INSERT INTO Ticket (ID, TITLE, AUTHOR, CREATED, EDITED, STATUS, PRIORITY, DESCRIPTION, GROUPID, FILENAME, ISIMG) VALUES 
(2,'Ticket 2', 'Mannan','2018-02-02','2018-02-03','1','2','This is a ticket. and its a description',2,'name',True);
INSERT INTO Ticket (ID, TITLE, AUTHOR, CREATED, EDITED, STATUS, PRIORITY, DESCRIPTION, GROUPID, FILENAME, ISIMG) VALUES 
(3,'Ticket 3', 'Mannan','2018-02-02','2018-02-03','1','2','This is a ticket. and its a description',2,'name',True);
INSERT INTO Ticket (ID, TITLE, AUTHOR, CREATED, EDITED, STATUS, PRIORITY, DESCRIPTION, GROUPID, FILENAME, ISIMG) VALUES 
(4,'Ticket 4', 'Mannan','2018-02-02','2018-02-03','Open','High','This is a ticket. and its a description',2,'name',True);

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(1,'Group 1', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(2,'Group 2', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(3,'Group 3', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(4,'Group 4', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(5,'Group 5', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(6,'Group 6', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(7,'Group 7', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(8,'Group 8', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(9,'Group 9', 'Mannan','2018-02-02','This group is for Systems.');

INSERT INTO TICKET_GROUP (ID, NAME, AUTHOR, CREATED, DESCRIPTION) VALUES 
(10,'Group 10', 'Mannan','2018-02-02','This group is for Systems.');