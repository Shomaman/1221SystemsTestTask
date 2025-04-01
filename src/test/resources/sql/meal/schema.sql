CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   age INTEGER NOT NULL,
   weight DOUBLE PRECISION NOT NULL,
   height DOUBLE PRECISION NOT NULL,
   target VARCHAR NOT NULL,
   basic_calorie_requirements DOUBLE PRECISION
);

CREATE TABLE dishes (
  id SERIAL PRIMARY KEY,
   name VARCHAR NOT NULL,
   calories_per_serving DOUBLE PRECISION NOT NULL,
   proteins DOUBLE PRECISION NOT NULL,
   fats DOUBLE PRECISION NOT NULL,
   carbohydrates DOUBLE PRECISION NOT NULL
);

CREATE TABLE meals (
   id SERIAL PRIMARY KEY,
   user_email VARCHAR NOT NULL,
   name VARCHAR NOT NULL,
   meal_time TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE meals_dishes (
    meals_id integer REFERENCES meals,
    dishes_id integer REFERENCES dishes,
    PRIMARY KEY (meals_id, dishes_id)
);