package com.vaultcode.springbatchsection6.controller;

import com.vaultcode.springbatchsection6.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/job")
@RestController
public class JobController {
    private final JobService jobService;

    @GetMapping("/start/{jobName}")
    public String jobStared(@PathVariable String jobName) {

        jobService.startJob(jobName);
        return "%s Job started!".formatted(jobName);
    }
}
