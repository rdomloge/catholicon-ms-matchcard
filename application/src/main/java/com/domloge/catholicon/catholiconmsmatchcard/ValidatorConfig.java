package com.domloge.catholicon.catholiconmsmatchcard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

@Configuration
public class ValidatorConfig implements InitializingBean {

    @Autowired
    ListableBeanFactory beanFactory;

    @Autowired
    ValidatingRepositoryEventListener validatingRepositoryEventListener;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> events = Arrays.asList("beforeCreate", "afterCreate", "beforeSave", "afterSave", "beforeLinkSave", "afterLinkSave", "beforeDelete", "afterDelete");
        Map<String, Validator> validators = beanFactory.getBeansOfType(Validator.class);
        
        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            events.stream().filter(p -> entry.getKey().startsWith(p)).findFirst()
                    .ifPresent(p -> validatingRepositoryEventListener.addValidator(p, entry.getValue()));
        }
    }
}
