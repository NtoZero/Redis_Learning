package learn.redis.service;

import learn.redis.model.vo.PersonVo;
import learn.redis.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public void savePerson(PersonVo personVo) {
        personRepository.save(personVo);
    }

    public PersonVo getPerson(String id) {
        return personRepository.findById(id).orElse(null);
    }
}
