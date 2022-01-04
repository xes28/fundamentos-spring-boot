package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyOperatorBeanImplement implements MyOperatorBean {

    private final Log logger = LogFactory.getLog(MyOperatorBeanImplement.class);

    @Override
    public int sum(int a, int b) {
        logger.info("Accediendo al método sum");
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        logger.info("Accediendo al método subtract");
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        logger.info("Accediendo al método multiply");
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        logger.info("Accediendo al método divide");
        try{
            logger.debug("El valor de a es " + a + " y el valor de b es " + b);
            return a / b;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }
}
