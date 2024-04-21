DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    isbn       varchar(13)  NOT NULL,
    title      varchar(100) NOT NULL,
    author     varchar(100) NOT NULL,
    publisher  varchar(100) NOT NULL,
    price      integer      NOT NULL,
    created_at timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (isbn)
);

INSERT INTO book
VALUES ('4065223962',
        'ちいかわ なんか小さくてかわいいやつ(1) (ワイドKC) ',
        'ナガノ',
        'tanaka',
        4000,
        '2024-04-01 00:00:00',
        '2024-04-01 00:00:00');
