CREATE TABLE IF NOT EXISTS "user_info"
(
    id         SERIAL       NOT NULL,
    username   VARCHAR(100) NOT NULL,
    password   VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT unique_user_info
        UNIQUE (username),
        UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS "video_info"
(
    id          SERIAL       NOT NULL,
    preview_url VARCHAR(100) NOT NULL,
    video_url   VARCHAR(100) NOT NULL,
    tag         VARCHAR(100) NOT NULL,
    title       varchar(100) NOT NULL,
    description TEXT         NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "tag_info"
(
    id         SERIAL NOT NULL ,
    tag_name   VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS "user_video"
(
    id         SERIAL NOT NULL ,
    user_id    INT NOT NULL,
    video_id   INT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


