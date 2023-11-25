package com.developerex.server.term;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/terms")
public class TermController {
    private final TermService termService;

    @GetMapping
    public ResponseEntity<List<TermDto>> getAllTerms() {
        return ResponseEntity.ok(termService.getAllTerms());
    }

    @PostMapping
    public ResponseEntity<TermDto> addTerm(TermDto termDto) {
//        if (termService.addTerm(termDto)){
//            return ResponseEntity.ok(termDto);
//        }
//        return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(termDto);
    }
}
