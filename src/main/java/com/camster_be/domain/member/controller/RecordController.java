package com.camster_be.domain.member.controller;

import com.camster_be.domain.member.dto.request.RecordRequest;
import com.camster_be.domain.member.dto.response.RankResponse;
import com.camster_be.domain.member.dto.response.RecordResponse;
import com.camster_be.domain.member.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    @GetMapping("")
    public List<RecordResponse> getRecords(@RequestBody RecordRequest request) {
        return recordService.getRecords(request);
    }

    @GetMapping("/rank")
    public List<RankResponse> getRank() {
        return recordService.getRank();
    }
}
