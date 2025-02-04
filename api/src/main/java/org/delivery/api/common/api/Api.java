package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//최상위 담당
//공통적으로 사용하는 status code 같은 상태 정보를 담는 api가 필요
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){ //기본적으로 사용할 응답 정의
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }
}

/*
{ -> Api Class
    "result" : {
        "result_code" : 200,
        "result_message" : "OK",
        "result_description" : "성공",
    },
    "body" : { <T>
        ,,,
        ,,,
    }
}
*/
