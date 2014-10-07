CREATE TABLE CTPONT (
    CT06POINTER INT NOT NULL ,
    CT06MATNO VARCHAR (10) NOT NULL ,
    CT06SYSCON INT NULL ,
    CT06MARC VARCHAR (2) NULL ,
    CT06TAG VARCHAR (4) NOT NULL ,
    CT06INDI1 CHAR (1) NOT NULL ,
    CT06INDI2 CHAR (1) NOT NULL ,
    CT06STATUS CHAR (1) NULL 
);
ALTER TABLE CTPONT ADD 
    CONSTRAINT PK_CTPONT PRIMARY KEY   
    (
        CT06POINTER,
        CT06MATNO,
        CT06TAG,
    CT06INDI1,
    CT06INDI2
    );
    
insert into ctpont values (1,'0000000001',0,'U','015','0','0','A');
insert into ctpont values (2,'0000000001',0,'U','020','0','0','A');
insert into ctpont values (3,'0000000001',0,'U','050','0','0','A');
insert into ctpont values (4,'0000000001',0,'U','100','0','0','A');
insert into ctpont values (5,'0000000001',0,'U','245','0','0','A');
insert into ctpont values (6,'0000000001',0,'U','260','0','0','A');
insert into ctpont values (7,'0000000001',0,'U','300','0','0','A');
insert into ctpont values (8,'0000000001',0,'U','490','0','0','A');
insert into ctpont values (9,'0000000001',0,'U','504','0','0','A');
insert into ctpont values (10,'0000000001',0,'U','500','0','0','A');
insert into ctpont values (11,'0000000001',0,'U','650','0','0','A');