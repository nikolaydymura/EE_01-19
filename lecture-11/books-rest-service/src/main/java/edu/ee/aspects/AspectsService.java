package edu.ee.aspects;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@EnableAspectJAutoProxy
@Configuration
@Aspect
public class AspectsService {

  private static final Logger logger = LoggerFactory.getLogger(AspectsService.class);

  @Pointcut("execution(public * doSomething*(..)) && args(int)")
  public void doSomething4PointCat() {
  }

  @Pointcut("execution(public * doSomething2(..)) && args(long)")
  public void doSomething2PointCat() {
  }

  @Before(value = "doSomething4PointCat()")
  public void printStartMethod(JoinPoint joinPoint) throws InvalidArgumentException {
    logger.info("========method started=========");
    Object[] args = joinPoint.getArgs();
    logger.info("========argument " + args[0]);
    int id = (Integer) args[0];
    if (id < 0) {
      throw new InvalidArgumentException(new String[]{"Less that 0"});
    }
  }

  @AfterThrowing(value = "doSomething4PointCat()", throwing = "ex")
  public void printFailMethod(JoinPoint joinPoint, Exception ex) {
    logger.info("========method failed=========");
    logger.info("========" + Arrays.toString(joinPoint.getArgs()));
    logger.error("=======" + ex.getClass().getSimpleName(), ex);
  }

  @AfterReturning(value = "doSomething4PointCat()", returning = "val")
  public void printEndMethod(JoinPoint joinPoint, int val) {
    logger.info("========method ended=========");
    logger.info("========" + Arrays.toString(joinPoint.getArgs()));
    logger.info("========" + val);
  }

  @Around(value = "doSomething2PointCat()")
  public Object printAround(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.info("========method around start =========");
    double start = System.nanoTime();
    Object[] args = joinPoint.getArgs();
    Long value;
    if ((Long) args[0] < 0) {
      value = (Long) joinPoint.proceed(new Object[]{0});
    } else {
      value = (Long) joinPoint.proceed();
    }

    double end = System.nanoTime();
    logger.info("========method around value " + value);
    logger.info("========method around time " + (end - start) / 1_000_000);
    return value;
  }

}
