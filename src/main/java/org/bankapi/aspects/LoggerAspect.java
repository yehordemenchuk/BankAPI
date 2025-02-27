package org.bankapi.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* org.bankapi..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(String.format("%s starts execution", joinPoint.getSignature().toShortString()));

        Instant start = Instant.now();

        Object proceedingRes = joinPoint.proceed();

        Instant end = Instant.now();

        long duration = Duration.between(start, end).toMillis();

        logger.info(String.format("duration: %s ms", duration));

        logger.info(String.format("%s ends execution", joinPoint.getSignature().toShortString()));

        return proceedingRes;
    }

    @AfterThrowing(value = "execution(* org.bankapi..*.*(..))", throwing = "ex")
    public void log(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE, joinPoint.getSignature().toShortString() +
                " occured an exception: " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* org.bankapi..*.*(..))", returning = "retVal")
    public void log(JoinPoint joinPoint, Object retVal) {
        logger.log(Level.INFO, String.format("%s returning %s",
                joinPoint.getSignature().toShortString(), retVal));
    }
}
