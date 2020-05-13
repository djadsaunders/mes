package com.djad.mes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextDelegate {
    private ApplicationContext applicationContext;

    @Autowired
    public ApplicationContextDelegate(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
