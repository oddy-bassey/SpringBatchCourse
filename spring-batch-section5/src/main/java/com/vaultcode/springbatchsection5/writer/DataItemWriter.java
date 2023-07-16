package com.vaultcode.springbatchsection5.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataItemWriter implements ItemWriter<Integer> {
    @Override
    public void write(Chunk<? extends Integer> chunk) throws Exception {
        log.info("*** Inside item writer!");
        chunk.getItems().forEach(data -> log.info(String.valueOf(data)));
    }
}