CREATE TABLE app.author
(
    id                  BIGINT       NOT NULL PRIMARY KEY,
    kana   VARCHAR(100)       NOT NULL,
    name   VARCHAR(100)       NOT NULL,
    created_at       TIMESTAMP    NOT NULL,
    updated_at       TIMESTAMP    NOT NULL
);


-- コメント
COMMENT ON TABLE app.author IS '著者';

COMMENT ON COLUMN app.author.id IS '著者ID';
COMMENT ON COLUMN app.author.kana IS '著者(かな))';
COMMENT ON COLUMN app.author.name IS '著者名';
COMMENT ON COLUMN app.author.created_at IS '作成日時';
COMMENT ON COLUMN app.author.updated_at IS '更新日時';
