package com.example.Systems1221.TestTask.controller.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MealControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql({"/sql/meal/schema.sql", "/sql/meal/data.sql"})
    void createMealWithValidMealDTOAndMealIsNotPresent_ReturnsMealEntity() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "userEmail":"test@mail.ru",
                              "name":"завтрак",
                              "listOfDishes":[{ 
                                                "id":1,
                                                "name":"tom yam",
                                                "caloriesPerServing":100,
                                                "proteins":11,
                                                "fats":11,
                                                "carbohydrates":11}],
                              "mealTime":"2025-03-25 11:00:56"                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "id":2,
                                    "userEmail":"test@mail.ru",
                                    "name":"завтрак",
                                    "listOfDishes":[
                                                      {
                                                      "id":1,
                                                      "name":"tom yam",
                                                      "caloriesPerServing":100.0,
                                                      "proteins":11.0,
                                                      "fats":11.0,
                                                      "carbohydrates":11.0
                                                      }
                                                      ],
                                    "mealTime":"2025-03-25T11:00:56"
                                }
                               \s""")
                );
    }

    @Test
    @Sql({"/sql/meal/schema.sql", "/sql/meal/data.sql"})
    void createMealWithValidMealDTOAndMealIsPresent_ThrowsMealIsAlreadyExistException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "userEmail":"test@mail.ru",
                              "name":"завтрак",
                              "listOfDishes":[{ 
                                                "id":1,
                                                "name":"tom yam",
                                                "caloriesPerServing":100,
                                                "proteins":11,
                                                "fats":11,
                                                "carbohydrates":11}],
                              "mealTime":"2025-03-25 11:00:00"
                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                "Прием пищи в такое время уже был создан"
                                ]
                                """)
                );
    }

    @Test
    void createMealWithInvalidMealDTO_ThrowsBindException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "userEmail":"test@mail.ru",
                              "name":null,
                              "listOfDishes":[{ 
                                                "id":1,
                                                "name":"tom yam",
                                                "caloriesPerServing":100,
                                                "proteins":11,
                                                "fats":11,
                                                "carbohydrates":11}],
                              "mealTime":"2025-03-25 11:00:00"
                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                  "укажите название приема пищи"
                                 ]
                                """)
                );
    }

    @Test
    @Sql({"/sql/meal/schema.sql", "/sql/meal/data.sql"})
    void createMealWithValidMealDTOAndUserIsNotPresent_ThrowUserNotFoundException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "userEmail":"test1@mail.ru",
                              "name":"Завтрак",
                              "listOfDishes":[{ 
                                                "id":1,
                                                "name":"tom yam",
                                                "caloriesPerServing":100,
                                                "proteins":11,
                                                "fats":11,
                                                "carbohydrates":11}],
                              "mealTime":"2025-03-25 11:00:00"
                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                  "Пользователь не найден"
                                 ]
                                """)
                );
    }

    @Test
    @Sql({"/sql/meal/schema.sql", "/sql/meal/data.sql"})
    void createMealWithValidMealDTOAndDishIsNotPresent_ThrowDishNotFoundException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "userEmail":"test@mail.ru",
                              "name":"Завтрак",
                              "listOfDishes":[{ 
                                                "id":2,
                                                "name":"карбонара",
                                                "caloriesPerServing":100,
                                                "proteins":11,
                                                "fats":11,
                                                "carbohydrates":11}],
                              "mealTime":"2025-03-25 12:00:00"
                        }
                        """);
        //when
        mockMvc.perform(request)
                //then
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                  "Такое блюдо не найдено"
                                 ]
                                """)
                );
    }
}
