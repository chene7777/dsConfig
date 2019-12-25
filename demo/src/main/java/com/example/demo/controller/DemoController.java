package com.example.demo.controller;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.dto.Demo;
import com.example.demo.model.dto.DemoCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@EnableScheduling   // 开启定时任务
public class DemoController {
    @Autowired(required = false)
    DemoMapper demoMapper;

    @RequestMapping("/hello")
    public String Hello(String name) {
        return "Hello World";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Demo> getList() {
        //创建一个Demo类的搜索条件类,利用example可以添加搜索条件
        DemoCriteria example = new DemoCriteria();
        //selectByExample可表示使用搜索条件做搜索，得出一个List结果
        List<Demo> demos = this.demoMapper.selectByExample(example);
        System.out.println("demos = " + demos);
        return demos;
    }

    @RequestMapping(value = "/getOne")
    public Demo getOne(Integer id) {
        //selectByPrimaryKey表示按主键选择该条数据
        Demo demo = this.demoMapper.selectByPrimaryKey(id);
        System.out.println("demo = " + demo);
        return demo;
    }

    @RequestMapping(value = "/delectById")
    public Boolean delectById(Integer id) {
        //deleteByPrimaryKey按主键删除该条数据
        //成功删除1条则返回1，删除失败则返回0
        if (this.demoMapper.deleteByPrimaryKey(id) == 1){
            System.out.println("true");
            return true;
        }else {
            System.out.println("false");
            return false;
        }
    }

    @RequestMapping(value = "/insert")
    public Boolean insert(String name,String code) {
        Demo demo = new Demo();
        demo.setName(name);
        demo.setCode(code);
        //insertSelective按照现已设定参数的参做insert，而不是整个类做insert
        if (this.demoMapper.insertSelective(demo) == 1){
            System.out.println("true");
            return true;
        }else {
            System.out.println("false");
            return false;
        }
    }

    @RequestMapping(value = "/update")
    public Boolean update(Integer id,String name,String code) {
        //按id找到该条数据
        Demo demo = this.demoMapper.selectByPrimaryKey(id);
        //如该条数据不在库，则需返回错误或者业务处理
        if (demo == null){
            return false;
        }
        //设定参数
        demo.setName(name);
        demo.setCode(code);
        //updateByPrimaryKeySelective按照现已设定参数的参做update，而不是整个类做update
        if (this.demoMapper.updateByPrimaryKeySelective(demo) == 1){
            System.out.println("true");
            return true;
        }else {
            System.out.println("false");
            return false;
        }
    }


    @GetMapping("/thymeleaf/{msg}")
    public void learn(@PathVariable String msg) throws IOException {
        //创建模版加载器
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");  //模板文件的所在目录
        resolver.setSuffix(".ttf");       //模板文件后缀
        //创建模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        //将加载器放入模板引擎
        templateEngine.setTemplateResolver(resolver);
        //创建字符输出流并且自定义输出文件的位置和文件名
        FileWriter writer = new FileWriter("D:/workFile/index111.ttf");
        //创建Context对象(存放Model)
        Context context = new Context();
        //放入数据
        context.setVariable("hello",msg);
        //创建静态文件,"text"是模板html名字
        templateEngine.process("text",context,writer);
    }

}
