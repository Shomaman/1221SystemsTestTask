package com.example.Systems1221.TestTask.controller.dish;

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
public class DishControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql({"/sql/dish/schema.sql"})
    void createDishWithValidDishDTOAndDishIsNotPresent_ReturnsDishEntity() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/dishes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":"том ям",
                           "caloriesPerServing":100,
                           "proteins":11,
                           "fats":11,
                           "carbohydrates":11
                        }
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
                                    "id":1,
                                    "name":"том ям",
                                    "caloriesPerServing":100,
                                    "proteins":11,
                                    "fats":11,
                                    "carbohydrates":11
                                }
                                """)
                );
    }

    @Test
    @Sql({"/sql/dish/schema.sql", "/sql/dish/data.sql"})
    void createDishWithValidDishDTOAndDishIsPresent_ThrowsDishIsAlreadyExistException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/dishes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":"tom yam",
                           "caloriesPerServing":100,
                           "proteins":11,
                           "fats":11,
                           "carbohydrates":11
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
                                "Блюдо с таким названием уже существует."
                                ]
                                """)
                );
    }

    @Test
    void createDishWithInvalidDishDTO_ThrowsBindException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/dishes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":null,
                          "caloriesPerServing":100,
                           "proteins":11,
                           "fats":11,
                           "carbohydrates":11
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
                                  "Укажите название блюда"
                                 ]
                                """)
                );
    }
}
