package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void createUser(UserCreateRequest request) {
        userJdbcRepository.createUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        // 사용자가 없으면 예외를 발생시킴
        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUser(name);
    }
}
