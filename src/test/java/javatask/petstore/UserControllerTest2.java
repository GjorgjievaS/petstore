package javatask.petstore;

import javatask.petstore.model.User;
import javatask.petstore.repository.UserRepository;
import javatask.petstore.webapi.UserController;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest2 {


    @Autowired
     MockMvc mockMvc;

    @MockBean
     UserRepository userRepository;

    public User user1(){
        User user = new User(UUID.randomUUID(), "name", "secondName","name@test",12.0);
        return user;
    }

    public User user2(){
        User user2 = new User(UUID.randomUUID(), "name2", "secondName2","name2@test",22.0);
        return user2;
    }

    @BeforeEach
    void setup() {
        given(this.userRepository.selectAllUsers())
                .willReturn(Lists.newArrayList(user1(),user2()));
    }

    @Test
    public void testStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")).andExpect(status().isOk());
    }
}
