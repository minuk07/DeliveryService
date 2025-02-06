package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
User의 경우는 2000번대 에러 코드 사용
*/

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰."),

    EXPIRED_TOKEN(400, 2001, "만료된 토큰."),

    TOKEN_EXCEPTION(400, 20002, "토큰 알 수 없는 에러"),
    ;

    private final Integer httpStatusCode; //이에 상응하는 http Status Code

    private final Integer errorCode; //Internal Error Code

    private final String description;

}
