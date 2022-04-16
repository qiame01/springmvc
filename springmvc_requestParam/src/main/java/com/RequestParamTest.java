package com;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import com.requestParam.User;
import com.requestParam.UserList;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 测试：接收两类请求参数
 * 1-内置对象参数
 * 2-可用注解辅助获取的参数
 */
@RestController
public class RequestParamTest {

    private static final String PAGE = "ttt";

    /**
     * 1-内置对象参数
     */
    @RequestMapping(value = "/aaa")
    public String aaa(
            MultipartRequest multipartRequest,
            HttpServletRequest request,
            HttpSession httpSession,
            Model model,
            ModelMap modelMap,
            HttpHeaders headers,
            TimeZone timeZone,
            Locale locale,
            HttpMethod httpMethod,
            Reader reader,
            InputStream inputStream,
            ServletRequest servletRequest,
            WebRequest webRequest,
            HttpServletResponse response
    ) {
        // 放入视图
        request.setAttribute("aaa", "request");

        // request->cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    System.out.println(cookie.getName());
                    return "cookie";
                }
            }
        }
        return PAGE;
    }

    /**
     * 2-可用注解辅助获取的参数
     * <p>
     * A、处理requet URI 上路径变量的注解：@PathVariable，如占位符中的路径变量：@RequestMapping(value = "/ccc/{aaa}/{bbb}")
     * B、处理request header部分的注解：@RequestHeader(可获取浏览器Request Headers下参数), @CookieValue
     * C、处理request body部分的注解：@RequestParam,  @RequestBody
     * <p>
     *
     * @PathVariable 接收.请求路径中的占位符。不加也行，但url上的参数名要与方法形参一致
     * <p>
     * @RequestParam 接收.get请求参数。不加也行，但前端参数名要与方法形参一致
     * 1-value：参数名 注意只有value时，可省略value=
     * 2-required：是否必须，默认为true，标示请求中必须包含该value参数，如果不包含则抛出异常（见ssm综合练习笔记10.3）
     * 3-defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false。这时，如果请求中不包含该参数则使用默认值
     * <p>
     * MultipartRequest：获取文件
     *
     * @ResponseBody：将结果中的对象序列化成字符串，返回前端
     * @RequestBody：将请求中的字符串，反序列化成对象，进行接收，并将各个属性保存到数据库
     */
    @RequestMapping(value = "/ccc/{aaa}/{bbb}")
    @ResponseBody
    public String ccc(
            User user,
            @RequestBody User user1,
            @RequestBody String userJsonString,

            @PathVariable(value = "hhh", name = "ddd", required = true) String ooo,

            @RequestHeader("bbb") String bbb,

            @RequestBody String ccc,

            @ModelAttribute("eee") User eee,

            @MatrixVariable("fff") String fff,

            @CookieValue(value = "JSESSIONID", required = false) String JSESSIONID,
            @CookieValue(value = "ggg", required = false) String ggg,

            @RequestParam(value = "hhh", required = true, defaultValue = "0") String hhh,
            @RequestParam("jjj") UserList jjj,
            @RequestParam("iii") String[] iii,
            @RequestParam("kkk") List<String> kkk,
            @RequestParam("lll") User lll,
            @RequestParam("mmm") String mmm,
            @RequestParam("file") MultipartFile file,
            @RequestParam("files") MultipartFile[] files,
            String nnn
    ) {
        return null;
    }

}
