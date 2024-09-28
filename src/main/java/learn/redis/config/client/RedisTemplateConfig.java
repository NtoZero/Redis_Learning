package learn.redis.config.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Jackson 직렬화 설정
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // null 값 제외
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // 빈 객체 직렬화 방지
        objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜 및 시간 모듈 등록 (LocalDateTime 등)

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // Key는 StringRedisSerializer로 직렬화
        template.setKeySerializer(new StringRedisSerializer());

        // Value는 Jackson2JsonRedisSerializer로 직렬화
        template.setValueSerializer(jackson2JsonRedisSerializer);

        // Hash Key와 Hash Value도 각각 직렬화
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}