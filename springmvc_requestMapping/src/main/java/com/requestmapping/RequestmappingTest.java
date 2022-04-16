package com.requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * 测试：url请求映射规则
 * 说明：这里只测试请求映射的各种情形。至于返回值和返回视图，将单列一个工程
 * <p>
 * 关于注解：
 * 1-@ResponseBody：用于结果处理：返回字符串和json化数据，不可返回视图
 * 1-@RestController = @Controller + @ResponseBody，二者可互换。@ResponseBody 可加在类上和方法上
 * 2-@RequestMapping 建议只配置在方法上，不配置在类上，以便于搜索
 * <p>
 * 请求映射分类
 * 1-标准URL.映射-常用，其他不常用
 * 2-Ant风格.映射
 * 3-rest风格.映射
 * 4-限定请求方法.映射
 * 4-限定请求方法.组合注解.映射
 */
@RestController
public class RequestmappingTest {

    /**
     * 标准URL.映射
     */
    @RequestMapping(value = "/aaa")
    public String aaa() {
        return "标准URL.映射";
    }

    /**
     * Ant风格.映射
     * <p>
     * ?：通配一个字符
     * *：通配0个或者多个字符
     * **：通配0个或者多个路径
     */
    @RequestMapping(value = "/s*/bbb")
    public String bbb() {
        return "Ant风格.映射.*.通配0个或者多个字符";
    }

    @RequestMapping(value = "/ccc/**")
    public String ccc() {
        return "Ant风格.映射.**.通配0个或者多个路径";
    }

    /**
     * rest风格.映射
     */
    @RequestMapping(value = "/ddd/{name}/{id}")
    public String ddd(@PathVariable("name") String nameCustom, @PathVariable("id") Long idCustom) {
        return "rest风格.映射.name=" + idCustom + ",id=" + idCustom;
    }

    /**
     * 限定请求方法.映射
     * <p>
     * POST等请求，需要借助postman模拟
     */
    @RequestMapping(value = "/kkk", method = RequestMethod.GET)
    public String kkk() {
        return "限定请求方法.映射.GET";
    }

    @RequestMapping(value = "/eee", method = RequestMethod.POST)
    public String eee() {
        return "限定请求方法.映射.POST"; // type=Method Not Allowed, status=405
    }

    @RequestMapping(value = "/fff", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String fff() {
        return "限定请求方法.映射.POST/PUT";
    }

    /**
     * 限定请求方法.组合注解.映射
     */
    @GetMapping(value = "/ggg")
    public String ggg() {
        return "限定请求方法.组合注解.@GetMapping";
    }

    @PostMapping(value = "/hhh")
    public String hhh() {
        return "限定请求方法.组合注解.@PostMapping"; // type=Method Not Allowed, status=405
    }

    @PutMapping(value = "/iii")
    public String iii() {
        return "限定请求方法.组合注解.@PutMapping";
    }

    @DeleteMapping(value = "/jjj")
    public String jjj() {
        return "限定请求方法.组合注解.@DeleteMapping";
    }


}
