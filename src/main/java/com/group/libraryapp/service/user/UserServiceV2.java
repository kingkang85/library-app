package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;을 해줌 (트랜잭션 시작!)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 문제가 있다면 rollback
    @Transactional
    public void createUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));
//        u.getId();
    }

    // 조회만 하므로 성능 향상을 위해 readOnly = true 설정
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?;
        // Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        // 영속성 컨텍스트가 변경을 감지하여 자동 save
//        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);

//        if (!userRepository.existsByName(name)) {
//            throw new IllegalArgumentException();
//        }
//
//        User user = userRepository.findByName(name);
//        userRepository.delete(user);
    }
}
