package com.exercise.atipera;

import java.util.List;

public class RepositoryResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchResponse> branches;

    // Constructor, getters, and setters

    public RepositoryResponse(String repositoryName, String ownerLogin, List<BranchResponse> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<BranchResponse> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchResponse> branches) {
        this.branches = branches;
    }
}