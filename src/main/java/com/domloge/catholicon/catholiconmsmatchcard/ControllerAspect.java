package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;

import com.domloge.catholicon.ms.common.ScraperException;

@Aspect
@Configuration
public class ControllerAspect {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	private ScheduledExecutorService exec;
	
	@Autowired
	private SyncSchedulingAndPersistence sync;
	
	@PostConstruct
	public void setup() {
		exec = Executors.newSingleThreadScheduledExecutor();
	}
	
	@PreDestroy
	public void shutdown() {
		exec.shutdownNow();
	}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}


    @Pointcut("within(*..*Controller)")
    public void allControllerMethods() { }
    
    //http://localhost:8085/matchcards/search/findByFixtureId?fixtureId=2033

    @Around("publicMethod() && allControllerMethods()")
    public Object controllerMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
    	LOGGER.info("Controller '{}' called", joinPoint.getSignature());
    	Object[] args = joinPoint.getArgs();
    	if(null != args && args.length > 0 && joinPoint.getSignature().getName().contains("executeSearch")) {
    		@SuppressWarnings("unchecked")
			LinkedMultiValueMap<String, String> map = 
        			(LinkedMultiValueMap<String, String>) joinPoint.getArgs()[1];
        	List<String> list = map.get("fixtureId");
        	String fixtureIdStr = list.get(0);
	    	exec.submit(() -> {
				try {
					sync.sync(Integer.parseInt(fixtureIdStr));
				} catch (ScraperException e) {
					LOGGER.error("Could not scrape", e);
				}
				catch(Exception e) {
					LOGGER.error("Background task is bailing due to unexpected error", e);
				}
			});
    	}
    	else {
    		LOGGER.debug("No args passed to the controller method ("
    				+joinPoint.getSignature().getName()
    				+") - can't sync in background (maybe a call to findAll()?) ");
    	}
        Object result = joinPoint.proceed();
        LOGGER.debug("Returning {}", result);
        return result;
    }
}