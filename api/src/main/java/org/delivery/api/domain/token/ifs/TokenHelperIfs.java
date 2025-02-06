package org.delivery.api.domain.token.ifs;

import org.delivery.api.domain.token.model.TokenDto;

import java.util.Map;

//외부라이브러리를 가져다 쓰는 인터페이스
public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> data);

    TokenDto issueRefreshToken(Map<String, Object> data);

    //validation
    Map<String, Object> validationTokenWithThrow(String token);
}
