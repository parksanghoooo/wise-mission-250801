CREATE DATABASE IF NOT EXISTS wiseSaying;
USE wiseSaying;

-- 기존 테이블 제거
DROP TABLE IF EXISTS wiseSayings;

create table if not exists wiseSayings
(
    id              bigint              auto_increment  primary key,
    content         varchar(255)        not null,
    author          varchar(255)        not null
);