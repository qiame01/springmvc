package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 步骤：
 * 1 一个或多个拦截器：实现接口的三个方法
 * 2 WebMvcConfigurer：注册拦截器
 * 3 发起请求
 * <p>
 * 拦截器执行顺序
 * Interceptor1.preHandle
 * Interceptor2.preHandle
 * InterceptorTest.aaa
 * Interceptor2.postHandle
 * Interceptor1.postHandle
 * Interceptor2.afterCompletion
 * Interceptor1.afterCompletion
 * <p>
 * preHandle调用Handler之前执行，称为前置方法
 * 返回值：true表示放行，后续业务逻辑继续执行  false表示被拦截，后续业务逻辑不再执行，但之前返回true的拦截器的完成方法会倒序执行
 * postHandle调用Handler之后执行，称为后置方法
 * afterCompletion视图渲染完成之后执行
 */

@RestController
public class InterceptorTest {

    @RequestMapping("/aaa")
    public String aaa(String rrr) {
        System.out.println("InterceptorTest.aaa");
        return "aaa";
    }
}
