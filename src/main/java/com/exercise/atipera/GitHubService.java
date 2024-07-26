package com.exercise.atipera;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Service
public class GitHubService {

    @Value("${github.api.url}")
    private String githubApiUrl;

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Repository[] getUserRepositories(String username) {
        String url = String.format("%s/users/%s/repos", githubApiUrl, username);
        ResponseEntity<Repository[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Repository[].class);
        return response.getBody();
    }

    public Branch[] getRepositoryBranches(String owner, String repo) {
        String url = String.format("%s/repos/%s/%s/branches", githubApiUrl, owner, repo);
        ResponseEntity<Branch[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Branch[].class);
        return response.getBody();
    }
}