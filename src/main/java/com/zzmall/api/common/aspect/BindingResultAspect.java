package com.zzmall.api.common.aspect;

import com.zzmall.api.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @Author Connor
 * @Date 2019/06/05 10:15
 */
@Aspect
@Component
@Slf4j
public class BindingResultAspect {

    //定义重用切点
    @Pointcut("execution(public * com.zzmall.api.controller.*.*(..))")
    public void doBindingResult() {
    }

    //前置通知
    @Before("doBindingResult()")
    public void doBefore(JoinPoint joinPoint) {

        //获取该方法的参数对象
        Object[] args = joinPoint.getArgs();
        for (Object br : args) {
            if (br instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) br;
                if (bindingResult.hasErrors()) {
                    throw new ApiException(bindingResult.getFieldError().getDefaultMessage());
                }
            }

        }
    }


}
