package springmvc.aaa;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 复习从HelloController2开始，去看看，你还会回来的哦
 * 本文件是springmvc的入门和优化，重点：web.xml、springmvc-servlet.xml、HelloController1
 * 配置里每一个配置项的用途，以及这里为何要实现Controller接口，需要见视频
 * 为何处理具体逻辑处理的handler，名字却叫controller呢？在整体架构中，为handler；在具体实现中，为Controller
 */

public class HelloController1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO：模拟处理完成

        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "这是第一个springmvc程序！实现Controller接口");
        return mv;
    }

}


