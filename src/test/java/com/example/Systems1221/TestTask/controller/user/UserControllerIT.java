package com.example.Systems1221.TestTask.controller.user;

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
public class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql({"/sql/user/schema.sql"})
    void createUserWithValidUserDTOAndUserIsNotPresent_ReturnsUserEntity() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":"Вася",
                           "email":"telo1@mail.ru",
                           "age":35,
                           "weight":101,
                           "height":101,
                           "target":"MAINTAIN_WEIGHT"
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
                                    "name":"Вася",
                                    "email":"telo1@mail.ru",
                                    "age":35,
                                    "weight":101,
                                    "height":101,
                                    "target":"MAINTAIN_WEIGHT"
                                }
                                """)
                );
    }

    @Test
    @Sql({"/sql/user/schema.sql", "/sql/user/data.sql"})
    void createUserWithValidUserDTOAndUserIsPresent_ThrowsUserIsAlreadyExistException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":"Вася",
                           "email":"telo1@mail.ru",
                           "age":35,
                           "weight":101,
                           "height":101,
                           "target":"MAINTAIN_WEIGHT"
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
                                "Пользователь с таким email уже существует."
                                ]
                                """)
                );
    }

    @Test
    void createUserWithInvalidUserDTO_ThrowsBindException() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name":null,
                           "email":"telo1@mail.ru",
                           "age":35,
                           "weight":101,
                           "height":101,
                           "target":"MAINTAIN_WEIGHT"
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
                                  "укажите имя пользователя"
                                ]
                                """)
                );
    }
}
