package com.developerex.server.term;


import org.springframework.stereotype.Service;

@Service
public class TermMapper {
    public static TermDto mapToDto(Term term) {
        return TermDto.builder()
                .startDateTime(term.getStartDateTime())
                .duration(term.getDuration())
                .build();
    }

    public static Term mapToEntity(TermDto termDto) {
        return Term.builder()
                .startDateTime(termDto.startDateTime())
                .duration(termDto.duration())
                .build();
    }
}
