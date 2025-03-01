package com.bezkoder.spring.datajpa.services.chat2Service;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class SequenceGeneratorService {





    private final Map<String, AtomicInteger> sequences = new ConcurrentHashMap<>();

    public long generateSequence(String seqName) {
        AtomicInteger counter = sequences.computeIfAbsent(seqName, k -> new AtomicInteger(1));
        return counter.getAndIncrement();
    }
}
