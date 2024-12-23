package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age;  // Null을 허용하므로 int가 아닌 Integer

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
