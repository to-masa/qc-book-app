CREATE TABLE app.book
(
    id                  BIGINT       NOT NULL PRIMARY KEY,
    title   VARCHAR(100)       NOT NULL,
    author_id BIGINT NOT NULL REFERENCES app.author (id),
    created_at       TIMESTAMP    NOT NULL,
    updated_at       TIMESTAMP    NOT NULL
);


-- コメント
COMMENT ON TABLE app.book IS '書籍';

COMMENT ON COLUMN app.book.id IS '書籍ID';
COMMENT ON COLUMN app.book.title IS '書籍タイトル';
COMMENT ON COLUMN app.book.author_id IS '著者ID';
COMMENT ON COLUMN app.book.created_at IS '作成日時';
COMMENT ON COLUMN app.book.updated_at IS '更新日時';
