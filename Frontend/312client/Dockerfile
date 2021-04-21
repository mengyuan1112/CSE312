FROM node:11.10.1
WORKDIR /app
COPY package.json /app
RUN yarn install
COPY . /app
EXPOSE 8081
CMD yarn serve
