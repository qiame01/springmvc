package springmvc.exceptionResolver;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 复习从HelloController2开始，去看看，你还会回来的哦
 * 第一步：自定义异常解析器（这里是文件上传解析器）
 * 第二步：在xml文件里配置该自定义异常解析器
 * 只要是处理器（即Controller）里发生的异常，都可在该接口的实现类的方法里捕获——判断——处理。
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        ModelAndView mv = new ModelAndView();

        if (ex instanceof MaxUploadSizeExceededException) {
            mv.setViewName("hello");
            mv.addObject("msg", "您的文件太大了！！！");
        }

        return mv;
    }

}
