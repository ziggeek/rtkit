package dev.zig.service;


import lombok.Getter;

@Getter
public class StudentService {

    private DataLoader dataLoader;

    public StudentService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
}
