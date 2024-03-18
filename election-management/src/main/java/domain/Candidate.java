package domain;

import java.util.Optional;

public record Candidate(String id,
                        Optional<String> photo,
                        String givenName,
                        String familyName,
                        String email,
                        Optional<String> Phone,
                        Optional<String> jobTitle) {
}
