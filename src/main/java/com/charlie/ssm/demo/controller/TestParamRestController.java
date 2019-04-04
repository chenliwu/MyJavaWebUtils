package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.entity.ParamsEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/params")
public class TestParamRestController {

    /**
     * @param paramName
     * @return
     * @RequestParam注解获取参数（GET请求方式）
     */
    @GetMapping("/testGet1")
    public String testGet1(@RequestParam("paramName") String paramName) {
        System.out.println("paramName:" + paramName);
        return paramName;
    }

    /**
     * @param paramName
     * @return
     * @RequestParam注解获取参数（POST请求方式）
     */
    @PostMapping("/testPost1")
    public String testPost1(@RequestParam("paramName") String paramName) {
        System.out.println("paramName:" + paramName);
        return paramName;
    }


    /**
     * @param paramName
     * @return
     * @PathVariable注解的作用就是从URL里面读取参数值（GET请求方式）
     */
    @GetMapping("/testGet2/{paramName}")
    public String testGet2(@PathVariable String paramName) {
        System.out.println("paramName:" + paramName);
        return paramName;
    }

    /**
     * @param param1 占位符{}添加了正则表达式，限定5位数值，如果传递过来的参数不合要求则不会执行方法的代码。
     * @param param2
     * @return
     * @PathVariable注解的作用就是从URL里面读取参数值（GET请求方式）
     * @PathVariable注解一般用于只传递一个参数的场景，当然也可以传递多个参数。
     */
    @GetMapping("/testGet2_1/{param1:[0-9]{5}}/{param2}")
    public String testGet2_1(@PathVariable String param1, @PathVariable String param2) {
        System.out.println("param1:" + param1);
        System.out.println("param2:" + param2);
        return param1 + "," + param2;
    }


    /**
     * @param paramName
     * @return
     * @PathVariable注解的作用就是从URL里面读取参数值（POST请求方式）
     */
    @PostMapping("/testPost2/{paramName}")
    public String testPost2(@PathVariable String paramName) {
        System.out.println("paramName:" + paramName);
        return paramName;
    }


    /**
     * springMVC的自动匹配参数（GET请求方式）
     * <p>
     * 形参paramName会自动匹配请求中key为paramName的参数值。
     * <p>
     * 可以接收AJAX封装的请求参数
     *
     * @param paramName
     */
    @GetMapping("/testGet3")
    public String testGet3(String paramName, String paramName1, Model model) {
        System.out.println("paramName:" + paramName);
        System.out.println("paramName1:" + paramName1);
        model.addAttribute("paramName", paramName);
        return paramName;
    }

    /**
     * springMVC的自动匹配参数（POST请求方式）
     * <p>
     * 形参paramName会自动匹配请求中key为paramName的参数值。
     * <p>
     * 可以接收AJAX封装的请求参数
     *
     * @param paramName
     */
    @PostMapping("/testPost3")
    public String testPost3(String paramName, String paramName1, Model model) {
        System.out.println("paramName:" + paramName);
        System.out.println("paramName1:" + paramName1);
        model.addAttribute("paramName", paramName);
        return paramName;
    }


    /**
     * SpringMVC的自动装箱（GET请求方式）
     * <p>
     * SpringMVC会把请求参数装箱到实体类的属性当中，属性名称就是参数名称
     *
     * @param paramsEntity
     * @return
     */
    @GetMapping("/testGet4")
    public String testGet4(ParamsEntity paramsEntity) {
        System.out.println("params:" + paramsEntity.toString());
        return paramsEntity.toString();
    }

    /**
     * SpringMVC的自动装箱（POST请求方式）
     * <p>
     * SpringMVC会把请求参数装箱到实体类的属性当中，属性名称就是参数名称
     *
     * @param paramsEntity
     * @return
     */
    @PostMapping("/testPost4")
    public String testPost4(ParamsEntity paramsEntity) {
        System.out.println("params:" + paramsEntity.toString());
        return paramsEntity.toString();
    }

}
