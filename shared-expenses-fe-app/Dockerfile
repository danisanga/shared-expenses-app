# syntax=docker/dockerfile:1.4

FROM --platform=$BUILDPLATFORM node:20.11.1-bullseye as builder
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN npm install -g @angular/cli
RUN npm install
CMD ["ng", "serve", "--host", "0.0.0.0"]