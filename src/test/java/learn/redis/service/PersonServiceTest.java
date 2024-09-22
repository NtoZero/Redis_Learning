package learn.redis.service;

import learn.redis.model.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @DisplayName("Person 객체를 해시 타입으로 레디스에 저장할 수 있다.")
    @Test
    void hashTypeSave() {
        for(int i=0; i<10000; i++) {
            // given
            String personId = String.valueOf(i);
            String dummyName = "kim" + (int)(Math.random() * 10000 + 1);
            int dummyAge = (int) (Math.random() * 80 + 1);
            PersonVo personVo = PersonVo.builder()
                    .id(personId)
                    .name(dummyName)
                    .age(dummyAge)
                    .build();

            // when
            personService.savePerson(personVo);

            // then
            log.info(personVo.toString());
        }
    }

    @DisplayName("해시 타입 Person 객체를 레디스에서 조회할 수 있다.")
    @Test
    void getPersonById() {
        for(int i=0; i<10000; i++) {
            //given
            String personId = String.valueOf(i);

            //when
            PersonVo person = personService.getPerson(personId);
            log.info(person.toString());

            //then
            Assertions.assertThat(person).isNotNull();
            Assertions.assertThat(person.getId()).isEqualTo(personId);
        }
    }

}