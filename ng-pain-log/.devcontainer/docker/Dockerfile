FROM node:18
WORKDIR /angular/pain-log
COPY pain-log/package.json pain-log/package-lock.json ./
RUN npm install
RUN npm install -g @angular/cli
EXPOSE 4200
CMD ["npm", "start"]
