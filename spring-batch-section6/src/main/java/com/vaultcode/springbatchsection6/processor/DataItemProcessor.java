package com.vaultcode.springbatchsection6.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataItemProcessor implements ItemProcessor<Integer, Long> {

    @Override
    public Long process(Integer item) throws Exception {
        log.info("*** Inside the item processor!");
        return (long) (item + 20);
    }
}
