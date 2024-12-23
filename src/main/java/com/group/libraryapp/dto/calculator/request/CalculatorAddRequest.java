package com.group.libraryapp.dto.calculator.request;

// GET 요청으로 들어오는 덧셈 계산을 위한 DTO(Data Transfer Object) 클래스
public class CalculatorAddRequest {
    // 클라이언트가 보내는 요청의 파라미터 이름과 일치해야함
    private final int number1;
    private final int number2;

    // URL의 쿼리 파라미터로 받은 값들을 객체의 필드에 설정하는 생성자
    public CalculatorAddRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}

/*
동작 순서:
1. 클라이언트가 GET 요청을 보냄
  예: GET /add?number1=10&number2=20

2. 스프링의 데이터 처리 순서:
  a. URL에서 쿼리 파라미터 추출 (number1=10, number2=20)
  b. 추출한 값을 사용하여 CalculatorAddRequest 생성자 호출
  c. 생성된 객체를 컨트롤러에 전달

3. 컨트롤러에서 getter 메서드를 통해 값을 읽어 계산 수행
  예: request.getNumber1() + request.getNumber2()

4. 계산 결과가 클라이언트에게 반환됨

특징:
- final 키워드 사용: 객체 생성 후 값 변경 불가능
- 생성자를 통한 초기화: 모든 필수 값을 생성 시점에 받음
- 불변 객체: 한번 생성된 후 내부 상태가 변경되지 않음
*/