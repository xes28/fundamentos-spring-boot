package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private final Log logger = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        logger.info("Accediendo al método printWithDependency");
        logger.debug("El número enviado como parametro a la dependencia myOperation es : " + 5);
        System.out.println(myOperation.sum(5));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
