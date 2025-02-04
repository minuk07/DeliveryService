package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
User의 경우는 1000번대 에러 코드 사용
*/

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUNT(400, 1404, "사용자를 찾을 수 없음."),

    ;

    private final Integer httpStatusCode; //이에 상응하는 http Status Code

    private final Integer errorCode; //Internal Error Code

    private final String description;

}
