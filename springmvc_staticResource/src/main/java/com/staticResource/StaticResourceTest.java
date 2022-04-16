package com.staticResource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试：静态资源的目录规则和访问
 * 这里的配置文件采用application.properties，不用application.yaml，因为后者换行太多了
 * 访问方式：1-浏览器直接输入文件名 2-Controller跳转到静态资源文件，如.html、.jpg、.js
 * <p>
 * 一 静态资源访问原理
 * 浏览器输入aaa.png后，当请求进来时，aaa.png会被当做url，先去Controller里找与aaa.png对应的动态资源(即方法)
 * 如果找不到对应的url，就会将请求交给静态资源处理器，然后去下面四个静态目录找对应名称的静态资源。静态资源若还找不到，则404
 * springmvc提供了四个静态资源目录，优先级高低排序：/META-INF/resources,resources,static(默认),public
 * <p>
 * 二 静态资源.访问方式
 * 方式一：浏览器输入文件名
 * 1-把静态资源如img、html、js等文件，放在上述四个目录下(这里只演示static目录，自定义目录见下面四)
 * 2-启动项目
 * 3-浏览器输入 localhost:8080/bbb.png
 * 4-浏览器访问static目录下index文件，输入localhost:8080即可，可以不加上index.html，无论pom里是否引入了thymeleaf
 * 5-浏览器访问static目录下非index文件，输入localhost:8080/page1.html即可，必须加扩展名，无论pom里是否引入了thymeleaf
 *
 * <p>
 * 方式二：Controller跳转
 * 1-见下面：111223 所在方法及其三个return，尤其是：若访问 templates动态资源目录，则必须引入 thymeleaf 依赖
 * 1-return "/page1.html"; // page1.html前面可以不加/
 * 2-若未引入thymeleaf，则controller里return "index.html"或return "page1.html"必须加扩展名，不加的话浏览器会报404
 * 3-若引入了thymeleaf(另见单独工程)，则controller里return 语句无论加不加扩展名，都会去templates下寻找资源，找不到则浏览器报404。
 * 所以，若想用controller方法访问自定义目录和static目录下html或img，则1-不能引入thymeleaf，2-return 后必须加扩展名，3-配置文件中需指定该自定义目录
 * 4-若配置文件里自定义了浏览器访问前缀ccc(见下面的三-自定义访问前缀)，则Controller无法成跳转到资源 todo---待探究
 *
 * <p>
 * 三 自定义静态资源浏览器访问前缀
 * 1-默认：/**
 * 2-方法：在配置文件里配置：spring.mvc.static-path-pattern=/ccc/**
 * 3-只能浏览器直接输入文件名访问，无法做controller跳转(见上面方式二-3)：http://localhost:8080/ccc/aaa.png
 *
 * <p>
 * 四 自定义静态资源的存放目录
 * 方法：
 * 1-先在类路径下自定义一个目录，再在配置文件里配置：spring.web.resources.static-locations=classpath:/bbb，bbb后面不加/，下同
 * 2-自定义多个目录，用逗号隔开：spring.web.resources.static-locations=classpath:/bbb,classpath:/ddd
 * 3-自定义目录后，默认的四个静态资源目录会失效，需要单独再次添加(这里只演示static目录)：classpath:/bbb,classpath:/static
 * 4-浏览器直接输入文件名访问(也可以用上面方式二：Controller跳转访问)：http://localhost:8080/bbb.png
 */

@Controller
public class StaticResourceTest {

    /**
     * 属于上面：方式二
     */
    @RequestMapping("/ddd")
    public String ddd() {
        System.out.println("-----111223");
//        return "/aaa.jpg"; // 可访问：static静态资源目录
//        return "/bbb.jpg"; // 可访问：自定义静态资源目录 bbb
        return "/page1.html"; // 可访问：templates动态资源目录，但必须引入 thymeleaf 依赖
    }

    /**
     * 下面两个演示：添加 @ResponseBody 后，可以返回String和map
     */
    @RequestMapping("/www")
    @ResponseBody
    public String www() {
        return "返回String.二娘子家书";
    }

    @RequestMapping("/www")
    @ResponseBody
    public Map fff() {
        Map map = new HashMap<>();
        map.put("aaa", "多肉");
        return map;
    }

}
