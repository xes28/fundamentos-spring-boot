package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyMathFuncPrintBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyMathFuncPrintBeanImplement;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperatorBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperatorBeanImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBeanOperators {
    @Bean
    public MyOperatorBean myOperatorBean(){
        return new MyOperatorBeanImplement();
    }

    @Bean
    public MyMathFuncPrintBean myMathFuncPrintBean(MyOperatorBean myOperatorBean){
        return new MyMathFuncPrintBeanImplement(myOperatorBean);
    }

}
