FROM node:11.10.1
WORKDIR /app
COPY package.json /app
RUN yarn install
COPY . /app
CMD yarn serve
EXPOSE 8080
