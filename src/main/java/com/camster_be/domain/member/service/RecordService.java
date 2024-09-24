package com.camster_be.domain.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.camster_be.domain.member.entity.Record;  // 엔티티 경로에 맞게 수정
import com.camster_be.domain.member.repository.RecordRepository;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Optional<Record> getRecordById(Long id) {
        return recordRepository.findById(id);
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
