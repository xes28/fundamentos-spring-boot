package com.fundamentosplatzi.springboot.fundamentos.bean;

import com.fundamentosplatzi.springboot.fundamentos.component.ComponentTwoImplement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyMathFuncPrintBeanImplement implements MyMathFuncPrintBean{

    private final Log logger = LogFactory.getLog(MyMathFuncPrintBeanImplement.class);
    private MyOperatorBean myOperatorBean;

    public MyMathFuncPrintBeanImplement(MyOperatorBean myOperatorBean) {
        this.myOperatorBean = myOperatorBean;
    }

    @Override
    public void printMathFunc(int a, int b) {
        logger.info("Accediendo al método printMathFunc");
        System.out.println("The sum of " + a + " and " + b +" is:" + myOperatorBean.sum(a,b));
        System.out.println("The subtract of " + a + " and " + b +" is:" + myOperatorBean.subtract(a,b));
        System.out.println("The multiplication of " + a + " and " + b +" is:" + myOperatorBean.multiply(a,b));
        System.out.println("The division of " + a + " and " + b +" is:" + myOperatorBean.divide(a,b));
    }
}
