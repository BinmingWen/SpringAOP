package cn.wyu.aop.controller;



import cn.wyu.aop.myAnnotation.PermissionAnnotations;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @PermissionAnnotations
    public JSONObject checkPermission(@RequestBody JSONObject request) {
        double b = 1/0;
        System.out.println(b);
        return request;
    }
}
