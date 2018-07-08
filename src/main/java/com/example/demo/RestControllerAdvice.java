package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = {Controller.class,RestController.class})
public class RestControllerAdvice {


//    /**
//     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
//     * @param binder
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {}
//
//    /**
//     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
//     * @param model
//     */
//    @ModelAttribute
//    public void addAttributes(Model model) {
//        model.addAttribute("author", "Magical Sam");
//    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public Object errorHandler(Throwable throwable) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", throwable.getMessage());
        return map;
    }


}