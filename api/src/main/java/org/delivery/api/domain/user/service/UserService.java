package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/*
* User domain 로직을 처리하는 Service
* */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        //비즈니스 로직에서 entity -> save 로직
        return Optional.ofNullable(userEntity)
                .map(it -> {

                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now()); //이것만 추가적으로 등록해주면 됨.

                    return userRepository.save(userEntity); //UserRepository에 UserEntity로 저장해서 넘김.
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    public UserEntity login(String email, String password){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    //email과 id에 맞는  유저 찾기
    public UserEntity   getUserWithThrow(String email, String password){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUNT)); //없으면 null을 반환 해야 함.
    }

    //오버로딩
    public UserEntity   getUserWithThrow(Long userId){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUNT)); //없으면 null을 반환 해야 함.
    }
}
