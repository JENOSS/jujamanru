

-- team (팀) --

insert into team(name) values ('SSG'); -- id 1
insert into team(name) values ('기아'); -- id 2
insert into team(name) values ('두산'); -- id 3
insert into team(name) values ('롯데'); -- id 4
insert into team(name) values ('삼성'); -- id 5
insert into team(name) values ('엔씨'); -- id 6
insert into team(name) values ('엘지'); -- id 7
insert into team(name) values ('KT'); -- id 8
insert into team(name) values ('키움'); -- id 9
insert into team(name) values ('한화'); -- id 10

-- post (게시글) --

insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('공지사항입니다.', null, true, true, '공지사항 입니다. \n 잘부탁드립니다.', 121, 'zeno', '2024-03-19 00:00:00', null); -- id 1
insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('오 첫글 작성 완료.', 1, false, false, '신기하다', 88,  'user1', '2024-03-19 00:01:00', null); -- id 2
insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('하도 뭐라 그래서 제목 수정함', 1, false, false, 'ㅇㅇ', 21, 'user2', '2024-03-19 00:02:00', '2024-03-19 00:03:00'); -- id 3
insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('SSG 올해 몇위 각이냐', 1, false, false, '내가 보기엔 이것저것 하면 가을야구는 갈듯 ㅋㅋ', 3,  'user3', '2024-03-19 00:02:00', null); -- id 4
insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('기아 첫글은 나의것', 2, false, false, '기아의 안치홍 안치홍', 324, 'user4', '2024-03-19 00:03:00', null); -- id 5
insert into post(title, team_id, is_notice, must_read, text, view_count, created_by, created_datetime, modified_datetime)
values ('기아 우승 예상하면 댓글 ㄱㄱ', 2, false, false, '이게 맞다', 111, 'user5', '2024-03-19 00:04:00', null); -- id 6

-- reply (댓글) --

insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('방가워요', 1, 'user1', '2024-03-19 00:00:01', null); -- id 1 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('방가워요22', 1, 'user2', '2024-03-19 00:00:02', null); -- id 2 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('잘부탁드려요', 1, 'user3', '2024-03-19 00:00:03', null); -- id 3 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('드디어 야구 커뮤앱이!!', 1, 'user3', '2024-03-20 00:00:04', null); -- id 4 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('진짜다', 1, 'user4', '2024-03-20 00:00:05', null); -- id 5 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('이것은 공지 댓글', 1, 'user5', '2024-03-20 00:00:06', null); -- id 6 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('이거 앱이 개행이 잘 안 되는거 같은데\n 개행 잘되나요?', 1, 'user7', '2024-03-21 00:00:07', null); -- id 7 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('제발 우리팀아 가을야구만 가자', 1, 'user8', '2024-03-24 00:00:08', null); -- id 8 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('ㄴㄴ 우리가 갈거', 1, 'user9', now(), null); -- id 8 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('과연 한화는..?', 1, 'user10', now(), null); -- id 9 (post_id 1)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)

values ('반가워 친구', 2, 'user1', '2024-03-19 00:00:11', null); -- id 11 (post_id 2)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('방가워요', 2, 'user2', '2024-03-19 00:00:11', null); -- id 12 (post_id 2)

insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('ㅋㅋ', 3, 'user2', '2024-03-19 00:00:12', null); -- id 13 (post_id 3)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('나도 댓글 수정함 ㄱ-', 3, 'user3', '2024-03-19 00:00:13', '2024-03-19 00:01:00'); -- id 14 (post_id 3)

insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('당연 1위지 이친구야', 4, 'user3', '2024-03-19 00:10:04', null); -- id 15 (post_id 4)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('제발.. 가을야구만이라도', 4, 'user5', '2024-03-19 00:12:05', null); -- id 16 (post_id 4)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('최정만 믿는다', 4, 'user5', '2024-03-19 00:14:05', null); -- id 17 (post_id 4)

insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('1위각임', 5, 'user6', '2024-03-19 00:17:06', null); -- id 18 (post_id 5)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('바람의 손자좀 데려와', 5, 'user7', '2024-03-19 00:18:07', null); -- id 19 (post_id 5)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('바람의 손자좀 데려와222', 5, 'user8', '2024-03-19 00:23:07', null); -- id 20 (post_id 5)

insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('가자가자 우승 가자~', 6, 'user8', '2024-03-19 00:20:08', null); -- id 21 (post_id 6)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('최강기아 우승기아', 6, 'user9', '2024-03-20 00:21:09', null); -- id 22 (post_id 6)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('ㄱㄱㄱㄱ', 6, 'user10', '2024-03-21 00:00:10', null); -- id 23 (post_id 6)
insert into reply(text, post_id, created_by, created_datetime, modified_datetime)
values ('이정후 못데려오냐', 6, 'user11', '2024-03-21 00:10:10', null); -- id 24 (post_id 6)



