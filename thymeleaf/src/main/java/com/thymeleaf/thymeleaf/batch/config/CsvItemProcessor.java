package com.thymeleaf.thymeleaf.batch.config;

import com.thymeleaf.thymeleaf.batch.bean.BatchPerson;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CsvItemProcessor extends ValidatingItemProcessor<BatchPerson> {
    @Override
    public BatchPerson process(BatchPerson item) throws ValidationException {
        super.process(item);//需要执行super.proccess()才会调用自定义校验器
        if (item.getNation().equals("汉族")){//对数据做简单处理
            item.setNation("01");
        }else{
            item.setNation("02");
        }
        return item;
    }
}
