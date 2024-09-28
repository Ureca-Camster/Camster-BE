package com.camster_be.domain.member.dto.response;

import com.camster_be.domain.member.entity.Record;

import java.time.LocalDate;

public record RecordResponse(
    LocalDate date,
    Integer time
) {
    public static RecordResponse of(Record record) {
        return new RecordResponse(
                record.getRecordDate(),
                record.getStudyTime()
        );
    }
}
