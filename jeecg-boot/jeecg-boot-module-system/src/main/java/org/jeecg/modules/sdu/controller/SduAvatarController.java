package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
@RestController
@RequestMapping("/sdu/avatar")
@Slf4j
public class SduAvatarController {
    @PostMapping(value = "/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Long userId,String status){
        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
            Path path = Paths.get("C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\" + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;

            System.out.println(fileName+"\n");
            return fileName;//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static class UpPhotoNameUtils {
        @NotNull
        public static String getPhotoName(String name, String imageFileName){
            String fileName = name;
            Calendar cal=Calendar.getInstance();
            //如果年的目录不存在，创建年的目录
            int year=cal.get(Calendar.YEAR);
            fileName=fileName + "_" + year;
            //如果月份不存在，创建月份的目录
            int month=cal.get(Calendar.MONTH)+1;
            fileName=fileName+"_";
            if(month<10)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+month;
            //生成文件名的日部分
            int day=cal.get(Calendar.DAY_OF_MONTH);
            fileName=fileName+"_";
            if(day<10)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+day;
            //生成文件名的小时部分
            int hour=cal.get(Calendar.HOUR_OF_DAY);
            if(hour<10)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+hour;
            //生成文件名的分钟部分
            int minute=cal.get(Calendar.MINUTE);
            if(minute<10)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+minute;
            //生成文件名的秒部分
            int second=cal.get(Calendar.SECOND);
            if(second<10)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+second;
            //生成文件名的毫秒部分
            int millisecond=cal.get(Calendar.MILLISECOND);
            if(millisecond<10)
            {
                fileName=fileName+"0";
            }
            if(millisecond<100)
            {
                fileName=fileName+"0";
            }
            fileName=fileName+millisecond;
            //生成文件的扩展名部分,只截取最后单位
            String endName = imageFileName.substring(imageFileName.lastIndexOf("."));//截取之后的值
            fileName=fileName+ endName;
            return fileName;
        }
    }

}
