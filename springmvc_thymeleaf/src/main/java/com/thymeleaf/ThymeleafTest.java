package com.thymeleaf;

/**
 * 测试：视图模板引擎2：thymeleaf
 * 1引入thymeleaf依赖
 * <p>
 * 2若视图类型是.html，则配置文件无需改动，即视图使用默认扩展名 spring.thymeleaf.suffix=.html，return后无需加扩展名
 * 2若视图类型是.ftl，则配置文件里视图使用 spring.thymeleaf.suffix=.ftl，return后无需加扩展名
 * <p>
 * 3若视图放在templates目录下，则配置文件无需改动，即使用默认位置 spring.thymeleaf.prefix=classpath:/templates/
 * 3若视图放在templates/aaa目录下，则配置文件无需改动，但return后的视图名前要加aaa/，return后无需加扩展名
 * 3若视图放在templates/aaa目录下，则配置文件使用spring.thymeleaf.prefix=classpath:/templates/bbb，return后无需加aaa/，return后无需加扩展名
 * 3若视图放在classpath:/ccc目录下，则无效，报错找不到
 *
 * thymeleaf 语法：
 * 1-操作字符串、时间日期格式化、流程控制（if、swith、each及其状态变量）
 * 2-从三大域对象取值
 * 3-url表达式（绝对路径和相对路径）、普通url传参、restful风格url传参、混合url传参：restful风格+普通方式
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ThymeleafTest {

    /**
     * thymeleaf字符串
     */
//    private static final String AAA = "nnn/aaa";
    private static final String AAA = "aaa";
    /**
     * thymeleaf时间日期格式化
     */
    private static final String BBB = "bbb";
    /**
     * thymeleaf流程控制
     */
    private static final String CCC = "ccc";
    /**
     * thymeleaf操作三大域对象
     */
    private static final String DDD = "ddd";
    /**
     * thymeleaf跳转普通url和restful风格url
     */
    private static final String EEE = "eee";


    /**
     * 1操作字符串、时间日期格式化、流程控制（if、swith、each及其状态变量）
     * 2从三大域对象取值
     */
    @RequestMapping("/aaa")
    public String listUsers(Model model, HttpServletRequest request) {

        User didi = new User("didi", "11", 1);
        User sisi = new User("sisi", "22", 0);
        User yiyi = new User("yiyi", "33", 0);

        // 字符串：strings
        model.addAttribute("msg", "你好，春天");
        model.addAttribute("msg1", "你好，春天");
        model.addAttribute("msg2", "");
        model.addAttribute("msg3", null);

        // 时间日期格式化：dates
        model.addAttribute("date", new Date());

        // 流程控制：if、swith、each及其状态变量
        model.addAttribute("aaa", "春天");

        List<User> bbb = Arrays.asList(didi, sisi, yiyi);
        model.addAttribute("users", bbb);

        Map<String, User> map = new HashMap();
        map.put("user1", didi);
        map.put("user2", sisi);
        map.put("user3", yiyi);
        model.addAttribute("userMap", map);

        // 从三大域对象取值
        request.setAttribute("qqq", "request");
        request.getSession().setAttribute("qqq", "session");
        request.getSession().getServletContext().setAttribute("qqq", "servletContext");

        return AAA;
    }

    /**
     * 3.1：url表达式（绝对路径、相对路径）
     * /bbb：用于EEE页面跳转和后续model取值
     */
    @RequestMapping("/bbb")
    public String testUrlObsolete(Model model) {
        // model用于测试下面的传参
        model.addAttribute("id", "234");
        model.addAttribute("name", "ioc");
        return EEE;
    }

    @RequestMapping("/ccc")
    @ResponseBody
    public String testUrlRelative() {
        return "success";
    }

    /**
     * 3.2.1：普通url传参
     * 特点：无需@PathVariable
     */
    @RequestMapping("/ddd")
    @ResponseBody
    public String testDeliverParamsByCommonUrl(String id, String name) {
        return id + "===" + name;
    }

    /**
     * 3.2.2：restful风格url传参
     * 特点：必需@PathVariable
     */
    @RequestMapping("/eee/{id}/{name}")
    @ResponseBody
    public String testDeliverParamsByRestfulUrl(@PathVariable String id, @PathVariable String name) {
        return id + "---" + name;
    }

    /**
     * 3.2.3：混合url传参：restful风格+普通方式
     */
    @RequestMapping("/eee/{id}")
    @ResponseBody
    public String testDeliverParamsByRestfulUrl2(@PathVariable String id, String name) {
        return id + "---" + name;
    }


}
