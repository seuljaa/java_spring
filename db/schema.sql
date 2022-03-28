DROP DATABASE demo;
CREATE DATABASE demo;
USE demo;

CREATE TABLE article(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title CHAR(100) NOT NULL,
`body` TEXT NOT NULL 
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

CREATE TABLE `member`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
loginId CHAR(20) NOT NULL,
loginPw CHAR(60) NOT NULL,
`authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨(3=일반, 7=관리자)',
`name` CHAR(20) NOT NULL,
`nickname` CHAR(20) NOT  NULL,
cellphoneNo CHAR(20) NOT NULL,
email CHAR(50) NOT NULL,
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전, 1=탈퇴)',
delDate DATETIME COMMENT '탈퇴날짜'
);

CREATE TABLE `board`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
`code` CHAR(50) NOT NULL UNIQUE COMMENT 'noice(공지사항), free1(자유게시판1), free2(자유게시판2)',
`name` CHAR(50) NOT NULL UNIQUE COMMENT '게시판 이름',
delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전, 1=탈퇴)',
delDate DATETIME COMMENT '탈퇴날짜'
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`authLevel` = 7,
`name` = '관리자',
`nickname` = '관리자',
cellphoneNo = '01011112222',
email = 'seuljaa940503@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '사용자1',
`nickname` = '사용자1',
cellphoneNo = '01011112222',
email = 'seuljaa940503@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
`nickname` = '사용자2',
cellphoneNo = '01011112222',
email = 'seuljaa940503@gmail.com';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER `updateDate`;
DESC article;

UPDATE article
SET memberId = 2
WHERE memberId = 0;

SELECT A.*,
M.nickname AS extra__writerName
FROM article AS A
LEFT JOIN MEMBER AS M
ON A.memberId = M.id
ORDER BY A.id DESC

SELECT *
FROM board;

SELECT *
FROM article;

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free1',
`name` = '자유게시판';

ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberId;
DESC article;

UPDATE article
SET boardId = 1
WHERE Id IN (1, 2);

UPDATE article
SET boardId = 2
WHERE Id IN (3);
