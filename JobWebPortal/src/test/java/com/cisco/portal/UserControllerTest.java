package com.cisco.portal;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cisco.models.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class UserControllerTest {
  private MockMvc mockMvc;

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  MockHttpSession session;

  @Autowired
  MockHttpServletRequest request;

  @Autowired
  private WebApplicationContext webApplicationContext;

  // Map which holds all the data against which our tests verify the returned
  // data
  private Map<String, Object> verifiableData = new HashMap<>();

  @Before
  public void setUp() {
    // We have to reset our mock between tests because the mock objects
    // are managed by the Spring container. If we would not reset them,
    // stubbing and verified behavior would "leak" from one test to another.

    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnQualificationList() throws Exception {
    MvcResult result = this.mockMvc
        .perform(get("/user/getQualificationList").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    JsonResponse returnedData = mapper.readValue(result.getResponse().getContentAsString(),
        JsonResponse.class);
    assertTrue(returnedData.getStatus().equals(TestSuiteUtils.SUCCESS));
    assertTrue(returnedData.getResponseCode() == TestSuiteUtils.RESPONSE_200);
  }
}
