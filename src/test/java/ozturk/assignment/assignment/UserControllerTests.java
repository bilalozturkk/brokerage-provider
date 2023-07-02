package ozturk.assignment.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ozturk.assignment.assignment.controller.UserController;
import ozturk.assignment.assignment.model.User;
import ozturk.assignment.assignment.repository.RoleRepository;
import ozturk.assignment.assignment.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    User record_1 = new User("bilalozturk", "bilalozturkk@gmail.com", "12345678");
    User record_2 = new User("bilalozturk2", "bilalozturkk3@gmail.com", "12345678");
    User record_3 = new User("bilalozturk3", "bilalozturkk3@gmail.com", "12345678");

    @Test
    public void getUsers_successful(){
        List<User> records = new ArrayList<>(Arrays.asList(record_1, record_2, record_3));

        Mockito.when(userRepository.findAll()).thenReturn(records);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[2].username", is(record_3.getUsername())))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
