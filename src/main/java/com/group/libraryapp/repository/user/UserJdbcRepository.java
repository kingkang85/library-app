package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// SQL을 사용해 실제 DB와 통신 담당
public class UserJdbcRepository {
    // 자바 코드와 데이터베이스 사이의 통신을 담당하는 JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 유저 생성
    public void createUser(String name, Integer age) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // update : 데이터베이스를 변경할 때 사용
        jdbcTemplate.update(sql, name, age);
    }

    // 유저 조회
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";

        // query : 데이터베이스를 조회할 때 사용
        // RowMapper : 데이터베이스의 한 행을 자바 객체로 변환하는 방법을 정의
        // 3. 최종적으로 jdbcTemplate.query()는 UserResponse 객체들을 List에 담아서 반환
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // 1. rs에서 각 컬럼의 값을 가져와서
            long id = rs.getLong("id");  // "id" 컬럼의 값을 long 타입으로
            String name = rs.getString("name");  // "name" 컬럼의 값을 String 타입으로
            int age = rs.getInt("age");  // "age" 컬럼의 값을 int 타입으로

            // 2. 이 값들로 자바 객체를 만들어서 반환
            return new UserResponse(id, name, age);
        });
    }

    // 해당 id의 사용자가 존재하는지 확인
    public boolean isUserNotExist(long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    // 유저의 이름을 변경
    public void updateUserName(String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    // 해당 이름의 사용자가 존재하는지 확인
    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    // 유저 삭제
    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
