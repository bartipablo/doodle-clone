package com.developerex.server.term;

import com.developerex.server.term.dto.TermDto;
import com.developerex.server.term.mapper.TermMapper;
import com.developerex.server.term.model.Term;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TermService {
    private final TermRepository termRepository;

    @Transactional
    public List<TermDto> getAllTerms() {
        return termRepository.findAll()
                .stream()
                .map(TermMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TermDto addTerm(TermDto termDto) {
        Term term = TermMapper.mapToEntity(termDto);
        termRepository.save(term);
        // set id field in record


        return termDto;
    }
}
