package org.example.unittestingusingmockito.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

        import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.unittestingusingmockito.dto.request.UserRequestDto;
import org.example.unittestingusingmockito.service.UserService;
import org.example.unittestingusingmockito.util.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveUser() throws Exception {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        // Set properties for userRequestDto as required

        Response response = new Response();
        response.setMessage("Success");
        response.setResponse(null); // or set a meaningful response object

        when(userService.saveUser(any(UserRequestDto.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Success")))
                .andExpect(jsonPath("$.response", is(nullValue()))); // Adjust as per the actual response structure
    }
}
