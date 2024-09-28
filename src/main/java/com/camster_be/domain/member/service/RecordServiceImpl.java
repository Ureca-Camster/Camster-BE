package com.camster_be.domain.member.service;

import com.camster_be.domain.member.dto.request.RecordRequest;
import com.camster_be.domain.member.dto.response.RankResponse;
import com.camster_be.domain.member.dto.response.RecordResponse;
import com.camster_be.domain.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.camster_be.domain.member.entity.Record;  // 엔티티 경로에 맞게 수정
import com.camster_be.domain.member.repository.RecordRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public List<RecordResponse> getRecords(RecordRequest request) {
        Long memberId = SecurityUtils.getMemberId();  // 현재 로그인된 사용자의 ID를 가져옴
        Integer year = request.year();  // 요청에서 연도를 가져옴
        Integer month = request.month();  // 요청에서 월을 가져옴

        // 해당 연월의 첫 번째 날과 마지막 날을 계산
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // 해당 범위의 레코드를 조회
        List<Record> records = recordRepository.findByMemberIdAndRecordDateBetween(memberId, startDate, endDate);

        // 조회된 Record들을 RecordResponse로 변환하여 반환
        return records.stream()
                .map(RecordResponse::of)  // Record를 RecordResponse로 변환
                .collect(Collectors.toList()); // 리스트로
    }

    @Override
    public List<RankResponse> getRank() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        // 어제 날짜에 해당하는 상위 3명의 기록
        List<Record> topRecords = recordRepository.findTop3ByRecordDateOrderByStudyTimeDescIdAsc(yesterday);

        return topRecords.stream()
                .map(record -> new RankResponse(
                        record.getMember().getNickname(),  // Member에서 nickname 가져옴
                        record.getStudyTime()  // Record에서 studyTime 가져옴
                ))
                .collect(Collectors.toList());  // 리스트로
    }
}
