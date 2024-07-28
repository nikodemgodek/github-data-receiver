package com.exercise.atipera;
import com.exercise.atipera.GitHubController;
import com.exercise.atipera.GitHubService;
import com.exercise.atipera.Repository;
import com.exercise.atipera.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GitHubControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GitHubService gitHubService;

    @InjectMocks
    private GitHubController gitHubController;

    private Repository[] repositories;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gitHubController).build();

        // Set up test data
        Repository repo1 = new Repository();
        repo1.setName("Repo1");
        repo1.setFork(false);

        Repository repo2 = new Repository();
        repo2.setName("Repo2");
        repo2.setFork(true);

        repositories = new Repository[]{repo1, repo2};
    }

    @Test
    public void testGetUserRepos_success() throws Exception {
        when(gitHubService.getUserRepositories("nikodemgodek")).thenReturn(repositories);

        mockMvc.perform(get("/repos/nikodemgodek")
                        .header("Accept", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserRepos_userNotFound() throws Exception {
        when(gitHubService.getUserRepositories("invaliduserasda319sd")).thenThrow(new UserNotFoundException("Not found"));

        mockMvc.perform(get("/repos/invaliduserasda319sd")
                        .header("Accept", "application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUserRepos_unsupportedAcceptHeader() throws Exception {
        mockMvc.perform(get("/repos/casd231User")
                        .header("Accept", "text/plain"))
                .andExpect(status().isBadRequest());
    }
}