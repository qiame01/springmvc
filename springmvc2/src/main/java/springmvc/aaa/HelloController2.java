package springmvc.aaa;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 本文件包含了 springMVC 的所有知识的流程，从这里开始复习，顺序往下即可
 * <p>
 * springmvc 入门与优化：见HelloController1===============================================================================
 */

/**
 * springmvc 进阶：请求路径、参数接收等等
 */
@Controller
//@RequestMapping("hello") //这里的公共路径最好不配置，不便于搜索
public class HelloController2 {




    /**
     * 使用jstl标签=====================================================================================================
     */
    @RequestMapping("show27")
    public String test27(Model model) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setAge(17 + i);
            user.setName("张三");
            user.setUserName("saner");
            user.setId(i + 1L);
            userList.add(user);
        }
        model.addAttribute("userList", userList);
        return "users";
    }


    /**
     * json 序列化与反序列化=============================================================================================
     */
    @RequestMapping("show28")
    @ResponseBody
    public List<User> test28() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setAge(17 + i);
            user.setName("李军");
            user.setUserName("lijun");
            user.setId(i + 1l);
            userList.add(user);
        }
        return userList;
    }

    //TODO 需要浏览器带参数：user，不然报错
    @RequestMapping("show29")
    public String test29(@RequestBody() User user, Model model) {
        model.addAttribute("msg", user.toString());
        return "hello";
    }
    //TODO 需要浏览器带参数：user，不然报错
    @RequestMapping("show30")
    public String test30(@RequestBody() String user, Model model) {
        model.addAttribute("msg", user.toString());
        return "hello";
    }

    /**
     * 文件上传解析器fileupload ：单件上传、批量上传======================================================================
     * 注意：
     * 1.文件上传解析器可以在xml里设置文件大小，超过大小，则进方法前就会报错，这个错可以用下面的自定义异常解析器捕获
     * 2.注解里的接收名要与谷歌测试工具里一致
     */
    @RequestMapping("show31")
    public String test31(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        if (file != null) {
            file.transferTo(new File("f:\\uploadTest\\" + file.getOriginalFilename()));
        }
        return "redirect:/success.html";
    }
    @RequestMapping("show311")
    public String test311(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
        if (files != null) {
            for (MultipartFile file : files) {
                file.transferTo(new File("f:\\uploadTest\\" + file.getOriginalFilename()));
            }
        }
        return "redirect:/success.html";
    }



    /**
     * forward、redirect================================================================================================
     */
    @RequestMapping("show32")
    public String test32() {
        return "forward:/hello/show34.do?id=111&type=forward";
    }

    @RequestMapping("show33")
    public String test33() {
        return "redirect:/hello/show34.do?id=222&type=redirect";
    }

    @RequestMapping("show34")
    public String test34(@RequestParam("id") Long id, @RequestParam("type") String type, Model model) {
        model.addAttribute("msg", "forward?redirect?" + type + ",id=" + id);
        return "hello";
    }


    //待清理
    @RequestMapping("show28888888888888888888")
    public List<User> test2888888888888888() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setAge(17 + i);
            user.setName("李红");
            user.setUserName("lihong");
            user.setId(i + 1l);
            userList.add(user);
        }
        return userList;
    }


}
