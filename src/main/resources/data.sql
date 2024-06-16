-- Package 테이블에 초기 데이터 삽입
INSERT INTO package (tracking_no)
values ('111122223331'),
       ('111122223332'),
       ('987654321098'),
       ('456789012345'),
       ('654321098765');

INSERT INTO package_image (package_id, filename, type)
values (1, 'image1.jpg', 'jpg'),
       (1, 'image2.jpg', 'jpg'),
       (2, 'image3.jpg', 'jpg'),
       (2, 'image4.jpg', 'jpg'),
       (3, 'image5.jpg', 'jpg'),
       (3, 'image6.jpg', 'jpg'),
       (4, 'image7.jpg', 'jpg'),
       (4, 'image8.jpg', 'jpg');
