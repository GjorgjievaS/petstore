package javatask.petstore;

import com.google.gson.Gson;
import javatask.petstore.model.User;
import javatask.petstore.repository.UserRepository;
import javatask.petstore.service.UserService;
import javatask.petstore.webapi.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
     UserService userService = new UserService(userRepository);


    @InjectMocks
    UserController userController = new UserController(userService);

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testInsertUser() throws Exception {

        User user = new User(UUID.randomUUID(),"user","user","user@user",12.0);

        userController.addUser(user);

        mockMvc.perform(post("/api/users")
                .accept(MediaType.APPLICATION_JSON).content(new Gson().toJson(user)))
                .andExpect(status().isOk());

        verify(userService, times(1)).addUser(user);
    }
}
