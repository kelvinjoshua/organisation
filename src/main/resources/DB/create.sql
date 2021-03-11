CREATE DATABASE   department_news;

\c department_news

CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR,description VARCHAR);

CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR,  role VARCHAR);

CREATE TABLE news (id SERIAL PRIMARY KEY, name VARCHAR,description VARCHAR,content VARCHAR);

CREATE TABLE departments_users(id SERIAL PRIMARY KEY, departments_id INTEGER, users_id INTEGER);




