package com.developerex.server.auth;

import com.developerex.server.attendee.AttendeeRepository;
import com.developerex.server.attendee.model.Attendee;
import com.developerex.server.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final AttendeeRepository attendeeRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void signup(RegisterRequest registerRequest) {
        Attendee attendee = new Attendee();
        attendee.setUsername(registerRequest.getUsername());
        attendee.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        attendee.setEmail(registerRequest.getEmail());
        attendee.setCreated(Instant.now());
        attendee.setEnabled(false);

        attendeeRepository.save(attendee);

        String token = generateVerificationToken(attendee);
        mailService.send(attendee.getEmail(), "[ Doodle] Activate your account", "Thank you for signing up to Doodle, click on the below url to activate your account : " + "http://localhost:8080/api/auth/accountVerification/" + token);

    }

    private String generateVerificationToken(Attendee attendee) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setAttendee(attendee);

        String token = UUID.randomUUID().toString();
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional
    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new IllegalStateException("Invalid token"));
        String username = verificationToken.getAttendee().getUsername();
        Attendee attendee = attendeeRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("Attendee not found with name - " + username));
        attendee.setEnabled(true);
        attendeeRepository.save(attendee);
    }

}
