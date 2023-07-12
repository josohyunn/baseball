# 데이터베이스 쿼리문

CREATE database baseball;
USE baseball;

## stadium_tb

CREATE TABLE `stadium_tb` (
`stadium_id` int primary key auto_increment not null ,
`stadium_name` varchar(10) DEFAULT NULL,
`stadium_created_at` date not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO stadium_tb VALUES (null,"사직야구장","1985-10-14");
INSERT INTO stadium_tb VALUES (null,"잠실야구장","1980-04-17");
INSERT INTO stadium_tb VALUES (null,"광주챔피언스필드","2014-03-08");

drop table stadium_tb;
drop table team_tb;
drop table player_tb;
drop table out_player_tb;

select * from stadium_tb;

## team_tb

CREATE TABLE `team_tb` (
`team_id` int primary key auto_increment not null,
`stadium_id` int,
`team_name`  varchar(10) DEFAULT NULL,
`team_created_at` date not null,
FOREIGN KEY (`stadium_id`)
REFERENCES `stadium_tb`(`stadium_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

select * from team_tb;

INSERT INTO `team_tb` VALUES (null, 1,"롯데", "1975-05-06");
INSERT INTO `team_tb` VALUES (null, 2,"LG", "1982-01-26" );
INSERT INTO `team_tb` VALUES (null, 3,"기아", "1982-01-30");


## player_tb


CREATE TABLE `player_tb` (
`player_id` int primary key auto_increment not null ,
`team_id` int DEFAULT NULL,
`player_name` varchar(10) DEFAULT NULL,
`player_position` varchar(10),
`player_created_at` timestamp not null,
FOREIGN KEY (`team_id`)
REFERENCES `team_tb`(`team_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

select * from player_tb;
delete from player_tb where player_id=28;


INSERT INTO player_tb VALUES (null, 1,'김민석', '중견수',now());
INSERT INTO player_tb VALUES (null, 1,'윤동희', '우익수',now());
INSERT INTO player_tb VALUES (null, 1,'전준우', '1루수',now());
INSERT INTO player_tb VALUES (null, 1,'안치홍', '2루수',now());
INSERT INTO player_tb VALUES (null, 1,'유강남', '지명타자',now());
INSERT INTO player_tb VALUES (null, 1,'노진혁', '유격수',now());
INSERT INTO player_tb VALUES (null, 1,'한동희', '3루수',now());
INSERT INTO player_tb VALUES (null, 1,'손성빈', '포수',now());
INSERT INTO player_tb VALUES (null, 1,'황성빈', '좌익수',now());

INSERT INTO player_tb VALUES (null, 2,'홍창기', '우익수',now());
INSERT INTO player_tb VALUES (null, 2,'문성주', '좌익수',now());
INSERT INTO player_tb VALUES (null, 2,'김현수', '지명타자',now());
INSERT INTO player_tb VALUES (null, 2,'오스틴', '1루수',now());
INSERT INTO player_tb VALUES (null, 2,'오지환', '유격수',now());
INSERT INTO player_tb VALUES (null, 2,'박동원', '포수',now());
INSERT INTO player_tb VALUES (null, 2,'문보경', '3루수',now());
INSERT INTO player_tb VALUES (null, 2,'박해민', '중견수',now());
INSERT INTO player_tb VALUES (null, 2,'신민재', '2루수',now());

INSERT INTO player_tb VALUES (null, 3,'최원준', '1루수',now());
INSERT INTO player_tb VALUES (null, 3,'김도영', '3루수',now());
INSERT INTO player_tb VALUES (null, 3,'나성범', '우익수',now());
INSERT INTO player_tb VALUES (null, 3,'최형우', '좌익수',now());
INSERT INTO player_tb VALUES (null, 3,'소크라테스', '중견수',now());
INSERT INTO player_tb VALUES (null, 3,'고종욱', '지명타자',now());
INSERT INTO player_tb VALUES (null, 3,'김선빈', '2루수',now());
INSERT INTO player_tb VALUES (null, 3,'김태군', '포수',now());
INSERT INTO player_tb VALUES (null, 3,'박찬호', '유격수',now());


## out_player_tb

CREATE TABLE `out_player_tb` (
`out_player_id` int primary key auto_increment not null ,
`player_id` int,
`out_player_reason` varchar(100) DEFAULT NULL,
`out_player_created_at` timestamp not null,
FOREIGN KEY (`player_id`)
REFERENCES `player_tb`(`player_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

select * from out_player_tb;