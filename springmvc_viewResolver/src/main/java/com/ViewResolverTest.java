package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 略
 * 无需手动配置视图解析器，因为前缀和后缀，在配置了视图模板引擎 freemarker、thymeleaf后，均无需再配置，见freemarker和thymeleaf
 * 前后缀，见application.properties:
 * #默认前缀
 * spring.mvc.view.prefix=/
 * #后缀
 * spring.mvc.view.suffix=.html
 */
@Controller
public class ViewResolverTest {

    @RequestMapping
    public String aaa() {
        return "sss";
    }
}
