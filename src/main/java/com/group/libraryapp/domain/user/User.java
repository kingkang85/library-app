package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity  // 스프링이 User 객체와 user 테이블을 같은 것으로 바라봄
public class User {
    @Id  // 이 필드를 primary key로 간주함
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // primary key는 자동 생성되는 값
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {}  // JPA를 사용하기 위해서는 기본 생성자가 필요

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            // IllegalArgumentException : 잘못된 값이 메서드에 전달되었을 때 사용하는 예외 클래스
            // String.format (문자열 포맷팅) : %s에 name 값이 들어감
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    // User와 UserLoanHistory가 협력하게 바꿈
    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
