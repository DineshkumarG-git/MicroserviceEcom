package org.dinesh.userservicetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.dinesh.controllers.UserController;
import org.dinesh.dto.UserRequestDto;
import org.dinesh.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@RequiredArgsConstructor
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    UserService userService;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
     this.mockMvc =  MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void registerUserTestCase() throws Exception {
      //  assertTrue(true);
       // Mockito.when(userService.registerUser( UserRequestDto())).thenReturn("test");
        var userRequestDto = UserRequestDto.builder().userName("dinesh").state("TamilNadu").email("dinesh@google.com")
                .address("good address").street("4th street").country("India").passWord("GoodPassword").phoneNo("2123445").build();
      //  Mockito.when(userService.registerUser( userRequestDto)).thenReturn(userService.registerUser( userRequestDto));

        var payLoad =  objectMapper.writeValueAsString(userRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/Register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(payLoad)).andExpect(status().isOk());
    }


}
