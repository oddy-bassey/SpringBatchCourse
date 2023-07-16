package com.vaultcode.springbatchsection6.controller;

import com.vaultcode.springbatchsection6.dto.JobParamsRequest;
import com.vaultcode.springbatchsection6.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/job")
@RestController
public class JobController {
    private final JobService jobService;

    @GetMapping("/start/{jobName}")
    public String jobStared(@PathVariable String jobName,
                            @RequestBody List<JobParamsRequest> paramsRequests) {

        jobService.startJob(jobName, paramsRequests);
        return "%s Job started!".formatted(jobName);
    }
}
