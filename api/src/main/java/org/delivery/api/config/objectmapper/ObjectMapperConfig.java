package org.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Json 형식이나 날짜 출력 형식등을 공통적으로 맞춰주기 위해서 설정.
public class ObjectMapperConfig {

    //custom한 설정을 spring boot가 start할 때 이 파일을 참고해서 정의. (없다면 spring boot가 디폴트로 하나 만들어서 사용)

    @Bean //해당 객체를 등록, 해당 빈의 이름은 메소드 이름으로.
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); //custom해서 사용하기 위해 registerModule 등록, Jdk 8버전 이후 클래스들 파싱하거나 serialize,deserialize

        objectMapper.registerModule(new JavaTimeModule()); // <<- local date

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //모르는 json field에 대해서는 무시.

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); //비어있는 빈을 만들 때 어떻게 설정할 지

        //날짜 관련 직렬화
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //snake case 사용
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}
