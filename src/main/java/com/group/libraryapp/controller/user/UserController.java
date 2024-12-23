package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    // User 타입의 객체들만 저장할 수 있는 유저 목록 리스트
    // private final List<User> users = new ArrayList<>();

//    @Autowired
    private UserServiceV2 userService;

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    // POST 요청 처리 : 유저 추가
    @PostMapping("/user")  // POST /user
    public void createUser(@RequestBody UserCreateRequest request) {
        // User 객체를 만들어 리스트에 추가
        // users.add(new User(request.getName(), request.getAge()));

        userService.createUser(request);
    }

    // GET 요청 처리 : 모든 유저 정보 반환
    @GetMapping("/user")  // GET /user
    public List<UserResponse> getUsers() {
//        List<UserResponse> responses = new ArrayList<>();
//        // users를 순회하며 UserResponse 객체로 변환하여 리스트에 추가
//        for (int i = 0; i < users.size(); i++) {
//            responses.add(new UserResponse(i + 1, users.get(i)));
//        }
//        return responses;

        return userService.getUsers();
    }

    // PUT 요청 처리 : 유저 정보 수정
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    // DELETE 요청 처리 : 유저 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}