FROM node:18

# 作業ディレクトリを Angular プロジェクトのルートに設定
WORKDIR /angular/pain-log

# package.json/package-lock.json をコピーして依存関係をインストール
COPY pain-log/package.json pain-log/package-lock.json ./
RUN npm install

# Angular CLI をグローバルインストール
RUN npm install -g @angular/cli

# ポート 4200 を公開
EXPOSE 4200

# コンテナ起動時に ng serve を実行
CMD ["npm", "start"]
