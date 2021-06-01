package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Xiaoping Yu
 * @date 2021/6/1 - 11:15
 */
public class ServerGenerator {

    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";


    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath + "Test.java");
    }
}
