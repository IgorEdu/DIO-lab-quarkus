package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.instancio.Select.field;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository repository();

    @Test
    @Transactional
    void save(){
        Candidate candidate = Instancio.create(Candidate.class);
        repository().save(Collections.singletonList(candidate));

        Optional<Candidate> result = Optional.ofNullable(repository().findById(candidate.id()).toDomain());

        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }

    @Test
    void findAll(){
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        repository().save(candidates);

        List<infrastructure.repositories.entities.Candidate> result = repository().listAll().stream().toList();

        assertEquals(result.size(), candidates.size());
    }

    @Test
    @Transactional
    void findByName(){
        Candidate candidate1 = Instancio.create(Candidate.class);
        Candidate candidate2 = Instancio.of(Candidate.class)
                .set(field("givenName"), "Igor").create();

        CandidateQuery query = new CandidateQuery.Builder().name("ig").build();

        repository().save(List.of(candidate1,candidate2));


        List<Candidate> result = repository().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate2, result.get(0));
    }

}