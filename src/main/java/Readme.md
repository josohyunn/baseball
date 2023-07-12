# 데이터베이스 쿼리문

CREATE database baseball;
USE baseball;

## stadium_tb

CREATE TABLE `stadium_tb` (
`id` int primary key auto_increment not null ,
`name` varchar(10) DEFAULT NULL,
created_at timestamp not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

select * from stadium_tb;
INSERT INTO stadium_tb VALUES (null,'사직야구장',now());
INSERT INTO stadium_tb VALUES (null,'잠실야구장',now());
INSERT INTO stadium_tb VALUES (null,'광주챔피언스필드',now());

select * from stadium_tb;

## team_tb

CREATE TABLE `team_tb` (
`id` int primary key auto_increment not null,
`stadium_tb` int,
`name` varchar(10) DEFAULT NULL,
created_at timestamp not null,
FOREIGN KEY (`stadium_tb`)
REFERENCES `stadium_tb`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `team_tb` VALUES (null, 1,'롯데', now());
INSERT INTO `team_tb` VALUES (null, 2,'LG', now());
INSERT INTO `team_tb` VALUES (null, 3,'기아', now());

select * from team_tb;

## player_tb

CREATE TABLE `player_tb` (
`id` int primary key auto_increment not null ,
`team_id` int DEFAULT NULL,
`name` varchar(10) DEFAULT NULL,
`position` varchar(10),
`created_at` timestamp not null,
FOREIGN KEY (`team_id`)
REFERENCES `team_tb`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- truncate table player_tb;
-- select * from player_tb;

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
`id` int primary key auto_increment not null ,
`player_id` int,
`reason` varchar(100) DEFAULT NULL,
created_at timestamp not null,
FOREIGN KEY (`player_id`)
REFERENCES `player_tb`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- select * from out_player_tb;
