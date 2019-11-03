package com.example.countries.test;

import com.example.countries.controllers.Controller;
import com.example.countries.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    public void testInvalidCountryCode() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/MON").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());
        String expected = "INVALID_COUNTRY_CODE";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getErrorMessage(), false);
        JSONAssert.assertEquals(String.valueOf(500), String.valueOf(result.getResponse().getStatus()), false);
    }

    @Test
    public void testDatabaseConnection() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/EGY").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getErrorMessage());
        String expected = "INTERNAL_ERROR";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getErrorMessage(), false);
        JSONAssert.assertEquals(String.valueOf(500), String.valueOf(result.getResponse().getStatus()), false);
    }
}
