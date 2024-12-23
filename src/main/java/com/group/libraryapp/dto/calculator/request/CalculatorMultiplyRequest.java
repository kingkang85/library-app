package com.group.libraryapp.dto.calculator.request;

// POST 요청으로 들어오는 곱셈 계산을 위한 데이터를 담는 DTO (Data Transfer Object) 클래스
public class CalculatorMultiplyRequest {
    // JSON의 "number1" 필드와 매칭되는 변수
    private int number1;
    // JSON의 "number2" 필드와 매칭되는 변수
    private int number2;

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}

/*
동작 순서:
1. 클라이언트가 POST 요청을 보냄
   예: POST /multiply
   본문: {"number1": 10, "number2": 20}

2. 스프링의 데이터 처리 순서:
   a. CalculatorMultiplyRequest의 빈 객체를 생성
   b. JSON 데이터를 파싱(해석)
   c. 리플렉션을 사용하여 각 필드(number1, number2)에 값을 설정
   d. 완성된 객체를 컨트롤러에 전달

3. 컨트롤러에서 getter 메서드를 통해 값을 읽어 계산 수행
   예: request.getNumber1() * request.getNumber2()

4. 계산 결과가 클라이언트에게 반환됨
*/