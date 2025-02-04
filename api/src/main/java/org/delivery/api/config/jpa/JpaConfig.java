package org.delivery.api.config.jpa;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//멀티모듈이기 때문에 모든 폴더에 있는 빈들을 등록하도록 설정해줘야함. 원래는 하위폴더에 존재하는 빈들만 등록.
@Configuration
@EntityScan(basePackages = "org.delivery.db") // "org.delivery.db"의 하위에 있는 Entity 들을 사용하겠다..
@EnableJpaRepositories(basePackages = "org.delivery.db") //"org.delivery.db"의 하위에 있는 Repository 들을 사용하겠다.
public class JpaConfig {
}
