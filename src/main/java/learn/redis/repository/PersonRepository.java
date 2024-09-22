package learn.redis.repository;

import learn.redis.model.vo.PersonVo;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonVo, String> {

}
