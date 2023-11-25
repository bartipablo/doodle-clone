package com.developerex.server.term;


public class TermMapper {
    public static TermDto mapToDto(Term term) {
        return TermDto.builder()
                .startDateTime(term.getStartDateTime())
                .duration(term.getDuration())
                .build();
    }

    public static Term mapToTerm(TermDto termDto) {
        return Term.builder()
                .startDateTime(termDto.startDateTime())
                .duration(termDto.duration())
                .build();
    }
}
