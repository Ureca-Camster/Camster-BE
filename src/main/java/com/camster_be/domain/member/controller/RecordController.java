package com.camster_be.domain.member.controller;

import com.camster_be.domain.member.dto.response.RankResponse;
import com.camster_be.domain.member.dto.response.RecordResponse;
import com.camster_be.domain.member.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordController {

    private static final Logger log = LoggerFactory.getLogger(RecordController.class);
    private final RecordService recordService;

    @GetMapping
    public List<RecordResponse> getRecords(@RequestParam int year, @RequestParam int month) {
        log.info("getRecords year={}, month={}", year, month);
        return recordService.getRecords(year, month);
    }

    @GetMapping("/rank")
    public List<RankResponse> getRank() {
        return recordService.getRank();
    }
}
