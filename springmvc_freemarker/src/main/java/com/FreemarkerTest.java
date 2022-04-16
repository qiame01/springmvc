package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.freemarker.User;

import java.util.Arrays;
import java.util.List;

/**
 * 测试：视图模板引擎1：freemarker
 * <p>
 * 1引入freemarker依赖
 * <p>
 * 2若视图类型是ftlh，则配置文件无需改动，即视图使用默认扩展名(spring.freemarker.suffix=.ftlh)，controller方法return后无需加扩展名。ccc
 * 2若视图类型是ftl或html，则配置文件里视图使用spring.freemarker.suffix=.ftl或.html，return后无需加扩展名。aaa、bbb
 * <p>
 * 注意配置文件里下面几个目录配置后的斜杠加目录名如/fff，跟return语句视图名前的目录名加斜杠如fff/，二者其中一处加了即可，若都缺少，则找不到页面
 * 注意视图名与controller的url不能相同，不能都叫aaa。但thymeleaf可以
 * 3若视图放在templates/下，则配置文件无需改动，即位置使用默认值(spring.freemarker.template-loader-path=classpath:/templates)，return后无需加扩展名
 * 3若视图放在templates/fff目录下，则使用spring.freemarker.template-loader-path=classpath:/templates/fff，return后无需加扩展名，也无需重复加fff/
 * 3若视图放在templates/views目录下，则使用spring.freemarker.template-loader-path=classpath:/templates/views，return后无需加扩展名，也无需重复加/views
 * 3若视图放在classpath:/aaa目录下即与templates平级，则使用spring.freemarker.template-loader-path=classpath:/aaa，return后无需加扩展名，也无需重复加aaa/
 * <p>
 * todo 更多 freemarker 语法，待整理
 */
@Controller
public class FreemarkerTest {

    private static final String AAA = "aaa"; // templates 目录和templates/fff 目录.通用，fff 目录配置见配置文件

    /**
     * 注意视图名与controller的url不能相同，不能都叫aaa。但thymeleaf可以
     */
    @RequestMapping("/qqq")
    public String qqq(Model model) {
        System.out.println("------888555");
        List<User> bbb = Arrays.asList(
                new User("lili", "11", 0),
                new User("bobo", "22", 1),
                new User("dada", "33", 0));
        model.addAttribute("users", bbb);
        return AAA;
    }
}
