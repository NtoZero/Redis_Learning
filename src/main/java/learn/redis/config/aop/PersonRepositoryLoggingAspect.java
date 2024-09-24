package learn.redis.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersonRepositoryLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(PersonRepositoryLoggingAspect.class);

    // save() 메서드 호출 감지
    @Before("execution(* learn.redis.repository.PersonRedisRepository.save(..))")
    public void logBeforeSave(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            Object person = args[0];
            log.info("{}.{} - Saving person: {}",className, methodName, person);
        }
    }

    // findById() 메서드 호출 감지
    @Before("execution(* learn.redis.repository.PersonRedisRepository.findById(..))")
    public void logBeforeFindById(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            String id = (String) args[0];
            log.info("Finding person with ID: {}", id);
        }
    }

    // delete() 메서드 호출 감지
    @Before("execution(* learn.redis.repository.PersonRedisRepository.delete(..))")
    public void logBeforeDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            Object person = args[0];
            log.info("Deleting person: {}", person);
        }
    }

    // 메서드가 성공적으로 실행된 후 로그 남김
    @AfterReturning(pointcut = "execution(* learn.redis.repository.PersonRedisRepository.save(..))", returning = "result")
    public void logAfterSave(JoinPoint joinPoint, Object result) {
        log.info("Person saved successfully: {}", result);
    }
}
