package com.developerex.server.term;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TermService {
    private final TermRepository termRepository;

    public String getTerms() {
        return termRepository.findAll().stream().map(TermMapper::mapToTermDto).toString();
    }

    public boolean addTerm(TermDto termDto) {
        Term term = TermMapper.mapToTerm(termDto);
        termRepository.save(term);
        return true;
    }
}
