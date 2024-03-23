# qc-book-app
## 概要
書籍の情報を管理するアプリケーション

## 機能
- 書籍
  - 全ての書籍の取得
    - method: GET
    - endpoint: /books
    - response body(例): 
      ```json
      [
        {
          "id": 1,
          "title": "タイトル",
          "authorId": 1,
          "createdAt": "2024-01-01T00:00:00",
           "updatedAt": "2024-01-01T00:00:00"
        },
        {
          "id": 2,
          "title": "タイトル2",
          "authorId": 2,
            "createdAt": "2024-01-01T00:00:00",
             "updatedAt": "2024-01-01T00:00:00"
        }
      ]
      ```
  - 特定の書籍の取得
    - method: GET
    - endpoint: /books/{id}
    - response body(例): 
      ```json
      {
        "id": 1,
        "title": "タイトル",
        "authorId": 1,
        "createdAt": "2024-01-01T00:00",
         "updatedAt": "2024-01-01T00:00:00"
      }
      ```
  - 著者に紐づく書籍の取得
    - method: GET
    - endpoint: /authors/{id}/books
    - response body(例): 
      ```json
      {
        "author": {
          "id": 1,
          "name": "著者 太郎",
          "kana": "ちょしゃ たろう",
            "createdAt": "2024-01-01T00:00",
             "updatedAt": "2024-01-01T00:00"
        },
        "books": [
          {
            "id": 1,
            "title": "タイトル",
            "authorId": 1,
            "createdAt": "2024-01-01T00:00",
             "updatedAt": "2024-01-01T00:00"
          },
          {
            "id": 2,
            "title": "タイトル2",
            "authorId": 1,
            "createdAt": "2024-01-01T00:00",
             "updatedAt": "2024-01-01T00:00"
          }
        ]
      }
      ```
  - 書籍の登録
    - method: POST
    - endpoint: /books
    - request body(例): 
      ```json
      {
        "title": "タイトル",
        "authorId": 1
      }
      ```
    - response body(例): 
      ```json
      {
        "id": 1,
        "title": "タイトル",
        "authorId": 1,
        "createdAt": "2024-01-01T00:00",
         "updatedAt": "2024-01-01T00:00"
      }
      ```
  - 書籍の更新
    - method: PUT
    - endpoint: /books/{id}
    - request body(例): 
      ```json
      {
        "title": "タイトル",
        "authorId": 1
      }
      ```
    - response body(例): 
      ```json
      {
        "id": 1,
        "title": "タイトル",
        "authorId": 1,
        "createdAt": "2024-01-01T00:00",
         "updatedAt": "2024-01-01T00:00"
      }
      ```
- 著者 
  - 全ての著者の取得
    - method: GET
    - endpoint: /authors
    - response body(例): 
      ```json
      [
        {
          "id": 1,
          "name": "著者 太郎",
          "kana": "ちょしゃ たろう",
          "createdAt": "2024-01-01T00:00",
          "updatedAt": "2024-01-01T00:00"
        },
        {
          "id": 2,
          "name": "著者 二郎",
          "kana": "ちょしゃ じろう",
          "createdAt": "2024-01-01T00:00",
          "updatedAt": "2024-01-01T00:00"
        }
      ]
      ```
  - 特定の著者の取得
    - method: GET
    - endpoint: /authors/{id}
    - response body(例): 
      ```json
      {
        "id": 1,
        "name": "著者 太郎",
        "kana": "ちょしゃ たろう",
        "createdAt": "2024-01-01T00:00",
        "updatedAt": "2024-01-01T00:00"
      }
      ```
  - 著者の登録
    - method: POST
    - endpoint: /authors
    - request body(例): 
      ```json
      {
        "name": "著者 太郎",
        "kana": "ちょしゃ たろう"
      }
      ```
    - response body(例): 
      ```json
      {
        "id": 1,
        "name": "著者 太郎",
        "kana": "ちょしゃ たろう",
        "createdAt": "2024-01-01T00:00",
        "updatedAt": "2024-01-01T00:00"
      }
      ```
  - 著者の更新
    - method: PUT
    - endpoint: /authors/{id}
    - request body(例): 
      ```json
      {
        "name": "著者 太郎",
        "kana": "ちょしゃ たろう"
      }
      ```
    - response body(例): 
      ```json
      {
        "id": 1,
        "name": "著者 太郎",
        "kana": "ちょしゃ たろう",
        "createdAt": "2024-01-01T00:00",
        "updatedAt": "2024-01-01T00:00"
      }
      ```
## 環境構築
### Dockerコンテナの起動方法
```bash
$ docker compose up --detach
```
※初回の起動の場合
```bash
$ docker compose up --build --detach
```