package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.RecordRequest;
import com.camster_be.domain.member.dto.response.RankResponse;
import com.camster_be.domain.member.dto.response.RecordResponse;
import com.camster_be.domain.member.entity.Record;

import java.util.List;

public interface RecordService {

    Record createRecord(Record record);

    List<RecordResponse> getRecords(RecordRequest request);

    List<RankResponse> getRank();
}
