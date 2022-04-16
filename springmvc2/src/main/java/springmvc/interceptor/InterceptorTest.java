package springmvc.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试：拦截器
 */
@Controller
public class InterceptorTest {

    // 这个方法是Hello2中复制来的，需要重写，简单点
    @RequestMapping("show34")
    public String test34(@RequestParam("id") Long id, @RequestParam("type") String type, Model model) {
        model.addAttribute("msg", "forward?redirect?" + type + ",id=" + id);
        return "hello";
    }


}
