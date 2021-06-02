package com.example.api;

import com.example.mapper.PersonMapper;
import com.example.model.PersonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@ContextConfiguration("file:src/test/resources/spring-servlet.xml")
class PersonControllerTest {

  private static final String API = "/api/";
  private MockMvc mockMvc;

  @Autowired
  WebApplicationContext webApplicationContext;

  @Autowired
  PersonMapper personMapper;

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testFindAll() throws Exception {
    insertTestData();
    mockMvc.perform(get(API)).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void testFindById() throws Exception {
    insertTestData();
    mockMvc.perform(get(API + "{id}", 111)).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void testDeleteById() throws Exception {
    insertTestData();
    mockMvc.perform(delete(API + "{id}", 111)).andDo(print()).andExpect(status().isAccepted());
  }

  @Test
  void testSaveData() throws Exception {
    mockMvc.perform(
          post(API)
              .content(getPayload())
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  private void insertTestData() {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(personMapper.toPerson(new PersonDto(111, "ABC")));
    transaction.commit();
    session.close();
  }

  private String getPayload() throws JsonProcessingException {
    return objectMapper.writeValueAsString(new PersonDto(111, "ABC"));
  }
}
