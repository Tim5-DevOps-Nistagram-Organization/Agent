FROM node:13.12.0-alpine

ARG PROTOCOL="  protocol: 'http',"
ARG DOMAIN="  domain: 'localhost',"
ARG PORT="  port: '8080',"
ARG API="  api: '/api/server'"

# set working directory
WORKDIR /usr/src

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install

# add app
COPY . ./
RUN npm run build --prod