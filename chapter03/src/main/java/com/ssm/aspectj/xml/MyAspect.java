package com.ssm.aspectj.xml;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
public class MyAspect {
    //前置通知
    public void myBefore(JoinPoint joinPoint){
        System.out.println("前置通知：模拟执行权限检查");
        System.out.println("目标类是：" + joinPoint.getTarget());
        System.out.println("被植入增强处理的目标方法为：" + joinPoint.getSignature().getName());
    }
    //后置通知
    public void myAfterReturnng(JoinPoint joinPoint){
        System.out.println("后置通知：模拟日志记录");
        System.out.println("被植入增强处理的目标方法！"+ joinPoint.getSignature().getName());
    }

    /*
    * 环绕通知
    * Proceeding joinPoint 是JoinPoint 的子接口，表示可执行目标方法
    * 1.必须Object类返回值
    * 2. 必须接受一个参数
    * 3.必须是Throws Trowabale
    * */
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("环绕开始，执行目标方法开始之前，模拟开启事务");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕结束，执行目标方法开始之后，模拟关闭事务");
        return  obj;

    }

    //异常通知
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e){
        System.out.println("异常通知：" + e.getMessage());
    }

    //最终通知
    public void myAfter(){
        System.out.println("模拟方法结束后，释放资源");
    }


}
