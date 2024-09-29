package learn.redis.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@ToString
@Getter
@Setter
@Builder
@RedisHash("Person")
public class PersonVo {
    @Id
    private String id;
    @Indexed
    private String uuid;
    private String name;
    private int age;
    private String hobby;
}
