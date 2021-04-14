FROM node:7
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
#CMD node index.js
#EXPOSE 8000

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait

CMD /wait && node index.js