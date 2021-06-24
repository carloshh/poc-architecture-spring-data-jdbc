package com.github.carloshh.poc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.carloshh.poc.api.dto.UserRequest;
import com.github.carloshh.poc.repository.details.UserDetailsRepository;
import com.github.carloshh.poc.repository.user.UserRepository;
import com.github.carloshh.poc.test.IntegrationTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerIT extends IntegrationTestContext {
    private static final String USER_USERNAME_TEST_DATA = "test-user";
    private static final String USER_USERNAME = "carloshh";
    private static final String USER_EMAIL = "carloshh@test.com";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Test
    @DisplayName("A user from a script to the correct database should be loaded")
    @Sql(scripts = "classpath:/com/github/carloshh/poc/api/UserControllerIT-user-load.sql", config= @SqlConfig(dataSource = "userDataSource"))
    public void userLoad() {
        assertThat(userRepository.count()).isEqualTo(1);

        assertThat(userRepository.findUserByUsername(USER_USERNAME_TEST_DATA)).isPresent();
    }

    @Test
    @DisplayName("A new user and persist it in two databases should be created")
    public void createUser() throws Exception {
        var json = objectMapper.writeValueAsString(new UserRequest(USER_USERNAME, USER_EMAIL));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("username").value(USER_USERNAME))
                .andExpect(status().isOk());

        var user = userRepository.findUserByUsername(USER_USERNAME).orElseThrow();
        var userDetails = userDetailsRepository.findByUserId(user.id()).orElseThrow();

        assertThat(userRepository.count()).isEqualTo(1);
        assertThat(user.username()).isEqualTo(USER_USERNAME);
        assertThat(userDetails.email()).isEqualTo(USER_EMAIL);
    }

}