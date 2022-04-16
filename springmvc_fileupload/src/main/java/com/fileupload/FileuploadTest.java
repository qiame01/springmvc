package com.fileupload;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 1 本地模拟.单个文件.上传
 * <p>
 * 实际开发中，上传、查看包含以下两步，而下载则是根据文件资源的url去访问文件服务器：
 * 1) 文件资源上传到文件服务器
 * 2) 文件名和跳转链接保存到数据库
 * <p>
 * 2 本地模拟.单个文件.查看
 * <p>
 * 3 本地模拟.单个文件.下载
 * <p>
 * 4 todo 本地模拟.批量文件.上传、查看、下载
 * 5 todo 使用.文件服务器.和数据库.进行文件资源上传下载和文件名保存
 * <p>
 * 注意：要想访问templates下文件，必须引入视图模板引擎fm或tl，而static下则直接用文件名访问
 */
@Controller
public class FileuploadTest {

    public static final String UPLOAD_DIR = "F:\\end";


    /**
     * 本地模拟.单个文件.上传
     * 浏览器访问：http://localhost:8080/upload.html
     */
    @RequestMapping("/fileupload")
    public String nnn(MultipartFile pic) throws IOException {
        if (pic.isEmpty()) {
            return "文件为空";
        }

        String filename = pic.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        File dest = new File(UPLOAD_DIR, UUID.randomUUID().toString() + suffix);

        // 检测目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 检测文件是否已经存在
        if (dest.exists()) {
            return "文件已存在";
        }

        // 存储到数据库，这里简便起见，直接保存到本地
        pic.transferTo(dest);
        return "redirect:listFile";
    }

    /**
     * 本地模拟.单个文件.查看
     */
    @RequestMapping("/listFile")
    public String fff(ModelMap model) throws IOException {
        File file = new File(UPLOAD_DIR);
        File[] files = file.listFiles();
        model.addAttribute("files", files);
        return "file_list";
    }

    /**
     * 本地模拟.单个文件.下载
     * 浏览器访问：http://localhost:8080/download?filename=aaa.jpg
     * D://end下有个文件aaa.jpg，后续可以从查看列表中点击后进入本方法
     */
    @RequestMapping("/download")
    public ResponseEntity ddd(String filename) throws IOException {
        File dest = new File(UPLOAD_DIR, filename);
        // attachement：告诉浏览器，内容不要直接打开，显示下载框
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .body(new FileSystemResource(dest));
    }

    // 本地模拟.单个文件.上传2-待完善和测试
    @RequestMapping("/download2")
    public String download2(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        File file = new File("" + "/" + fileName);
        if (!file.exists()) {
            return "sssss";
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 方式1：原生方式：1024数组，略

        // 方式2
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        return "success";
    }


    /**
     * 本地模拟.批量上传
     */
    @RequestMapping("/aaa")
    public String aaa(@RequestParam("files") MultipartFile[] files) throws IOException {

        return "success";
    }



}
