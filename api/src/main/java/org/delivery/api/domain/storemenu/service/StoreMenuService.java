package org.delivery.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    //메뉴를 가져오는 메소드
    public StoreMenuEntity getStoreMenuWithThrow(Long id){ //id를 넘겨주면 해당 가게에 있는 스토어를 찾아 리턴
        var entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //스토어 아이디로 메뉴 가져오기
    public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }

    //메뉴 등록
    public StoreMenuEntity register(
            StoreMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(storeMenuEntity)
                .map(it ->{
                    it.setStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
