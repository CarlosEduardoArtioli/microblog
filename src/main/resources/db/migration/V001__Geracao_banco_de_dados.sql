CREATE TABLE tag
(
    id   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE news
(
    id      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(100) NOT NULL,
    content VARCHAR(255) NOT NULL,
    date    timestamp    NOT NULL,
    id_user INT          NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comment
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content  VARCHAR(255) NOT NULL,
    date     timestamp    NOT NULL,
    id_user  INT          NOT NULL,
    id_news  INT          NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user (id),
    FOREIGN KEY (id_news) REFERENCES news (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE news_tag
(
    id_news INT NOT NULL,
    id_tag  INT NOT NULL,
    PRIMARY KEY (id_news, id_tag),
    FOREIGN KEY (id_news) REFERENCES news (id),
    FOREIGN KEY (id_tag) REFERENCES tag (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;