# 芋づる検索

- DB のデータを芋づる式に検索していく Web アプリ.
- Servlet 上で動作する.
- Java 8 以上.
- war ファイルをデプロイすることで動作させられる.
- コネクションプールは用いない.
    - コネクションを張っていると、データベースの作り直しの邪魔になりそうなので.
- 検索方法を画面で定義できる.
- 検索方法はテーブル単位で定義でき、組み合わせて１つの芋づる検索を定義できる.
- 定義情報は独自のデータベースに保存する.
    - 組み込み利用可能なデータベースを使用し、初期化などはアプリが自動で行う.
- 画面はモダンブラウザを前提.

順序