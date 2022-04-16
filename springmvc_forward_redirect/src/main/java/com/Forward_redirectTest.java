package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 转发、重定向
 * 注意：不用 @RestController
 */
@Controller
public class Forward_redirectTest {

    /**
     * 转发
     */
    @RequestMapping(value = "/aaa")
    public String aaa() {
        return "redirect:ccc?type=1-redirect";
    }

    /**
     * 重定向
     */
    @RequestMapping(value = "/bbb")
    public String bbb() {
        return "forward:ccc?type=2-forward";
    }

    /**
     *
     */
    @RequestMapping(value = "/ccc")
    @ResponseBody
    public String ccc(@RequestParam(value = "type", defaultValue = "***") String type) {
        return "测试转发/重定向：" + type;
    }


}
