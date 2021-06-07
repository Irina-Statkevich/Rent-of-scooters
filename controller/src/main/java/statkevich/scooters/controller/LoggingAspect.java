package statkevich.scooters.controller;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    final static Logger logger=Logger.getLogger(LoggingAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointСut() {
    }

    @AfterThrowing(pointcut = "controllerPointСut()",throwing ="ex")
    public void doAfterThrowingTask(Exception ex){
        logger.error("Exception  : "+ex.getMessage()+"  "+ex.getStackTrace());
    }
}
