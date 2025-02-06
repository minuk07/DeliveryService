package org.delivery.api.domain.user.business;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Business //Service와 동일. 비즈니스로직 역할을 명시 (복잡한 로직을 처리하므로 여러 군데가 붙을 수 있음)
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;

    //사용자에 대한 가입처리 로직
    /*
    1.request -> entity
    2.entity -> save
    3.save Entity -> response
    4.response return
    * */

    public UserResponse register(@Valid UserRegisterRequest request) {
        //request -> Entity 로 바꿔서 저장. => Converter에서 변환해야 함.
        var entity = userConverter.toEntity(request);
        //Service에서 entity -> save 수행
        var newEntity = userService.register(entity);
        //entity -> response
        var response = userConverter.toResponse(newEntity);

        return response;
        /*return Optional.ofNullable(request)
                .map(userConverter::toEntity) //reuqest -> entity
                .map(userService::register) //entity -> save
                .map(userConverter::toResponse)//save entity -> response
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));*/
    }

    /*
    * 1. email, password를 가지고 사용자 체크
    * 2. user entity 로그인 확인
    * 3. token 생성
    * 4. token response
    * */

    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        //사용자가 없으면 throw

        var tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }

    public UserResponse me(User user) {

        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
