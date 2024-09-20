package com.camster_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camster_be.entity.StudyMember;
import com.camster_be.repository.StudyMemberRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StudyMemberService {

    @Autowired
    private StudyMemberRepository studyMemberRepository;

    public StudyMember createStudyMember(StudyMember studyMember) {
        return studyMemberRepository.save(studyMember);
    }

    public List<StudyMember> getAllStudyMembers() {
        return studyMemberRepository.findAll();
    }

    public Optional<StudyMember> getStudyMemberById(Long id) {
        return studyMemberRepository.findById(id);
    }

    public void deleteStudyMember(Long id) {
        studyMemberRepository.deleteById(id);
    }
}
