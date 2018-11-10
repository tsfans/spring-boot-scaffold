package cn.swift;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
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
    assertTrue(httpStatus==HttpStatus.OK.value());
  }

}
