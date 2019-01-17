package com.thymeleaf.thymeleaf.integration;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.io.IOException;

@Configuration
public class Integration {
    @Value("https://spring.io/blog.atom")//通过@Value注解自动获得https://spring.io/blog.atom的资源
    Resource resource;

    //读取流程
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller(){//使用FluentAPI和Pollers配置默认的轮询方式
        return Pollers.fixedRate(500).get();
    }

    /**
     * FeedEntryMessageSource实际为feed:inbound-channel-adapter,此处即构造feed的入站通道适配器作为数据输入
     * @return
     * @throws IOException
     */
    @Bean
    public FeedEntryMessageSource feedEntryMessageSource()throws IOException{
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(),"news");
        return messageSource;
    }

    @Bean
    public IntegrationFlow myFlow() throws IOException{
        return IntegrationFlows.from(feedEntryMessageSource())//流程从from方法开始
            .<SyndEntry,String>route(plyload ->plyload.getCategories().get(0).getName(),//通过路由方法route来选择路由,消息体(payload)的类型为SyndEntry,作为判断条件的类型为String,判断的值是通过payload获得的分类(Categroy)
            mapping -> mapping.channelMapping("releases","releasesChannel")//通过不同分类的值转向不同的消息通道,若分类为releases,则转向releasesChannel;若分类为engineering,则转向engineeringChannel,若分类为news,则转向newsChannel
            .channelMapping("engineering","engineeringChannel").channelMapping("news",
                            "newsChannel")).get();//通过get方法获得IntegrationFlow实体,配置为Spring的Bean
    }


    //releases流程
    @Bean
    public IntegrationFlow releasesFlow(){
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel",10))//从消息通道releasesChannel开始获取数据
            .<SyndEntry,String>transform(payload -> "<<" +payload.getTitle() + ">>" +payload.getLink()+System.getProperty("line.separator"))//使用transform方法进行数据转换,payload类型为SyndEntry,将其转换为字符串类型,并自定义数据的格式
            .handle(Files.outboundAdapter(new File("G:/integrationFlow_File"))//用handle方法处理file的出战适配器,Files类是由Spring Integration Java DSL提供的Fluent API用来构造文件输出的适配器
            .fileExistsMode(FileExistsMode.APPEND)
            .charset("UTF-8")
            .fileNameGenerator(message -> "releases.txt")
            .get()).get();
    }

    //engineering流程
    @Bean
    public IntegrationFlow engineeringFlow(){
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel",10))
                .<SyndEntry,String>transform(e -> "<<" + e.getTitle() +">>"+e.getLink()+System.getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("G:/integrationFlow_File"))
                .fileExistsMode(FileExistsMode.APPEND)
                .charset("UTF-8")
                .fileNameGenerator(message -> "engineering.txt")
                .get()).get();
    }

    //news流程
    @Bean
    public IntegrationFlow newsFlow(){
        return IntegrationFlows.from(MessageChannels.queue("newsChannel",10))
                .<SyndEntry,String>transform(payload -> "<<"+payload.getTitle()+">>"+payload.getLink()+System.getProperty("line.separator"))
                .enrichHeaders(//通过enrichHeaders来增加消息头的信息
                        Mail.headers()
                        .subject("来自Spring的新闻")
                        .to("baozhuming@yeah.net")
                        .from("zhuming256@163.com")
                ).handle(Mail.outboundAdapter("smtp.163.com")//邮件发送的相关信息通过Spring Integration Java DSL提供的Mail的headers方法来构造
                .port(25)
                .protocol("smtp")
                .credentials("zhuming256@163.com","a05140212+")
                .javaMailProperties(p -> p.put("mail.debug","false")),e ->e.id("smtpOut")).get();
    }
}
