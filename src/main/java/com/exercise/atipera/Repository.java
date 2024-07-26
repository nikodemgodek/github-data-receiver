package com.exercise.atipera;

public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;

    // Getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public boolean isFork() {
        return fork;
    }
}