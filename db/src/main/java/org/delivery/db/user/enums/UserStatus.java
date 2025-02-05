package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    //오타 방지와 지정된 데이터가 들어갈 수 있도록 enum 사용.
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
