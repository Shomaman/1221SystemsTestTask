insert into users (name, email, age, weight, height, target, basic_calorie_requirements)
values ('Вася','test@mail.ru', 35, 101, 101, 'MAINTAIN_WEIGHT', 1500);

insert into dishes (name, calories_per_serving, proteins,fats,carbohydrates)
values ('tom yam',100, 11,11,11);

insert into meals (user_Email, name, meal_time)
values ('test@mail.ru','Завтрак','2025-03-25 11:00:00');

insert into meals_dishes (meals_id, dishes_id)
values ('1','1');