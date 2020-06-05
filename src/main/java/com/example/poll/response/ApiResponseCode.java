package com.example.poll.response;

import com.example.poll.util.EnumModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements EnumModel {

    OK("요청이 성공하였습니다."), BAD_PARAMETER("요청 파라미터가 잘못되었습니다."), NOT_FOUND("리소스를 찾지 못했습니다."),
    UNAUTHORIZED("해당 리소스에 접근하기 위한 권한이 없습니다."), ACCESS_DENIED("보유한 권한으로 접근할수 없는 리소스 입니다."),
    EMAIL_SIGNIN_FAILED("계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다."), SERVER_ERROR("서버 에러입니다.");

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
