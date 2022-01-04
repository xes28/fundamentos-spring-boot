package com.fundamentosplatzi.springboot.fundamentos.component;

import com.fundamentosplatzi.springboot.fundamentos.FundamentosApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency{
    private final Log logger = LogFactory.getLog(ComponentTwoImplement.class);
    @Override
    public void saludar() {
        logger.info("Accediendo al método saludar");
        System.out.println("Hello World desde mi componente dos");
    }
}
