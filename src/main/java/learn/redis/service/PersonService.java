package learn.redis.service;

import learn.redis.model.vo.PersonVo;
import learn.redis.repository.PersonRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRedisRepository personRedisRepository;

    public void savePerson(PersonVo personVo) {
        personRedisRepository.save(personVo);
    }

    public PersonVo getPerson(String id) {
        return personRedisRepository.findById(id).orElse(null);
    }
}
