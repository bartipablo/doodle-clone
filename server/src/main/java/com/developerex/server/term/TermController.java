package com.developerex.server.term;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/terms")
public class TermController {
    private final TermService termService;
    private final TermRepository termRepository;

    @GetMapping
    public String getAllTerms() {
        return termService.getTerms();
    }

    @PostMapping
    public String addTerm(TermDto termDto) {
        if (termService.addTerm(termDto)){
            return "Term added";
        }
        return "Term not added";
    }
}
