package com.ohgiraffers.jenkinsproject.dto;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CalculatorDTO {

    @NonNull
    private int num1;
//    private final int num1 = 1;       final 방식 권장X
    @NonNull
    private int num2;
    private int sum;
}
