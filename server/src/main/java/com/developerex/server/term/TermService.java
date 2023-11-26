package com.developerex.server.term;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TermService {
    private final TermRepository termRepository;

    public List<TermDto> getAllTerms() {
        return termRepository.findAll()
                .stream()
                .map(TermMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void addTerm(TermDto termDto) {
        Term term = TermMapper.mapToEntity(termDto);
        termRepository.save(term);
    }
}
