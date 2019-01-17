package com.thymeleaf.thymeleaf.batch.config;

import com.thymeleaf.thymeleaf.batch.bean.BatchPerson;
import com.thymeleaf.thymeleaf.batch.job.CsvJobListener;
import com.thymeleaf.thymeleaf.batch.validator.CsvBeanValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration  开启的话是自动触发，
//@EnableBatchProcessing//开启批处理的支持
public class CsvBatchConfig {
//    @Bean
//    public ItemReader<BatchPerson> reader()throws Exception{
//        //使用FlatFileItemReader读取文件
//        FlatFileItemReader<BatchPerson> reader = new FlatFileItemReader<BatchPerson>();
//        //使用FlatFileItemReader的setResource方法设置csv文件的路径
//        reader.setResource(new ClassPathResource("people.csv"));
//        //在此处对cvs文件的数据和领域模型类型做对应映射
//        reader.setLineMapper(new DefaultLineMapper<BatchPerson>(){{
//            setLineTokenizer(new DelimitedLineTokenizer(){{
//                setNames(new String[]{"name","age","nation","address"});
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<BatchPerson>(){{
//                setTargetType(BatchPerson.class);
//            }});
//        }});
//        return reader;
//    }
//    @Bean
//    public ItemProcessor<BatchPerson,BatchPerson> processor(){
//        //使用自定义的ItemProcessor的实现CsvItemProcessor
//        CsvItemProcessor processor = new CsvItemProcessor();
//        //为processor指定校验器为CsvBeanValidator
//        processor.setValidator( csvBeanValidator());
//        return processor;
//    }
//    @Bean
//    public ItemWriter<BatchPerson> writer(DataSource dataSource){//Spring能让容器中已有的Bean以参数的形式注入，Spring Boot已经定义了DataSource
//        //使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
//        JdbcBatchItemWriter<BatchPerson> writer = new JdbcBatchItemWriter<BatchPerson>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BatchPerson>());
//        StringBuilder sqlB = new StringBuilder();
//        sqlB.append("Insert into batch_person ")
//                .append("(id,name,age,nation,address) ")
//                .append("values(hibernate_sequence.nextval,:name,:age,:nation,:address)");
//        //设置要执行批处理的SQL语句
//        writer.setSql(sqlB.toString());
//        writer.setDataSource(dataSource);
//        return writer;
//    }
//    @Bean
//    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception{
//        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setDataSource(dataSource);
//        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
//        jobRepositoryFactoryBean.setDatabaseType("oracle");
//        return jobRepositoryFactoryBean.getObject();
//    }
//    @Bean
//    public SimpleJobLauncher jobLauncher(DataSource dataSource,PlatformTransactionManager transactionManager) throws  Exception{
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
//        return jobLauncher;
//    }
//    @Bean
//    public Job importJob(JobBuilderFactory jobs, Step s1){
//        return jobs.get("importJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(s1)//为Job指定Step
//                .end()
//                .listener(csvJobListener())//绑定监听器csvJobListener
//                .build();
//    }
//    @Bean
//    public Step step1(StepBuilderFactory stepBuilderFactory,ItemReader<BatchPerson> reader,ItemWriter<BatchPerson> writer,
//                      ItemProcessor<BatchPerson,BatchPerson> processor){
//        return stepBuilderFactory
//                .get("step1")
//                .<BatchPerson,BatchPerson>chunk(65000)//批处理每次提交65000条数据
//                .reader(reader)//给step绑定reader
//                .processor(processor)//给step绑定processor
//                .writer(writer)//给step绑定writer
//                .build();
//    }
//    @Bean
//    public CsvJobListener csvJobListener(){
//        return new CsvJobListener();
//    }
//    @Bean
//    public Validator<BatchPerson> csvBeanValidator(){
//        return new CsvBeanValidator<BatchPerson>();
//    }
}
