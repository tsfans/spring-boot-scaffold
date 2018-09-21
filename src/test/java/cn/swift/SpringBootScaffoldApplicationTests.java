package cn.swift;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpringBootScaffoldApplicationTests {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void ping() throws Exception {
    RequestBuilder req = MockMvcRequestBuilders.get("/ping");
    MvcResult result = mockMvc.perform(req).andReturn();
    int httpStatus = result.getResponse().getStatus();
    String content = result.getResponse().getContentAsString();
    System.out.println("Response: HttpStatus="+httpStatus+",Content="+content);
    Assert.assertTrue("success", httpStatus==HttpStatus.OK.value());
  }

}
