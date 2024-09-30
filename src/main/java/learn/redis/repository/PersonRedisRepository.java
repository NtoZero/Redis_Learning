package learn.redis.repository;

import learn.redis.model.vo.PersonVo;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<PersonVo, String> {
    PersonVo findByUuid(String uuid);
}
