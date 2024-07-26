package com.exercise.atipera;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/repos/{username}")
    public List<RepositoryResponse> getUserRepos(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (!"application/json".equalsIgnoreCase(acceptHeader)) {
            throw new IllegalArgumentException("Unsupported Accept header");
        }

        Repository[] repositories = gitHubService.getUserRepositories(username);
        return Arrays.stream(repositories)
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    Branch[] branches = gitHubService.getRepositoryBranches(repo.getOwner().getLogin(), repo.getName());
                    List<BranchResponse> branchResponses = Arrays.stream(branches)
                            .map(branch -> new BranchResponse(branch.getName(), branch.getCommit().getSha()))
                            .collect(Collectors.toList());

                    return new RepositoryResponse(repo.getName(), repo.getOwner().getLogin(), branchResponses);
                })
                .collect(Collectors.toList());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}