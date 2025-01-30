FROM mcr.microsoft.com/devcontainers/java:1-17-bookworm

# 必要なパッケージをインストール
RUN apt-get update && apt-get install -y \
    default-mysql-client \
    maven \
  && rm -rf /var/lib/apt/lists/*

# 環境変数を設定
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# 環境変数を永続化
RUN echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> /root/.bashrc && \
    echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /root/.bashrc

# デフォルトのコマンド（コンテナを継続的に動作させるため）
CMD [ "sleep", "infinity" ]
