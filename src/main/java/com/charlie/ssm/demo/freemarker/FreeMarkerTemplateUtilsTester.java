package com.charlie.ssm.demo.freemarker;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlw on 2019/05/23  10:35.
 */
public class FreeMarkerTemplateUtilsTester {

    public static void main(String[]args){
        test1();
    }

    public static void test1(){
        String sql = "select * from corp where corpId = ${corpId} and corpName = ${corpName!'默认成员单位名称'}";
        Map<String,Object> params = new HashMap<>();
        params.put("corpId","成员单位ID");

        try{
            System.out.println("\nSQL："+sql);
            System.out.println("\n处理输出："+resolveFreemarkerSql(sql,params));
            System.out.println();
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }

    }

    /**
     * 解析Freemarker语法的SQL语句
     *
     * @param freemarkerSql
     * @param params
     * @return
     */
    public static String resolveFreemarkerSql(String freemarkerSql,Map<String,Object> params) throws TemplateException,IOException{
        String sql;

        //1、加载String模板串
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("freemarkerSql",freemarkerSql);

        //2、创建模板对象
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(stringLoader);
        try {
            //3、获取模板对象
            Template template = cfg.getTemplate("freemarkerSql","utf-8");
            //4、解析模板获得字符串
            sql = FreeMarkerTemplateUtils.processTemplateIntoString(template,params);
        } catch (TemplateException te){
            //模板解析错误
            throw new TemplateException("解析SQL语句失败，请检查SQL语句是否正确："+te.getMessage(),te.getEnvironment());
        } catch (IOException e) {
            //IO流错误
            throw e;
        }
        return sql;
    }


}
