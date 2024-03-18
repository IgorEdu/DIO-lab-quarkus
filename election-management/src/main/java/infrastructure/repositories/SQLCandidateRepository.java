package infrastructure.repositories;

import domain.Candidate;
import domain.CandidateRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SQLCadndidateRepository implements CandidateRepository {
    @Override
    public void save(List<Candidate> candidates) {

    }

    @Override
    public List<Candidate> findAll() {
        return List.of();
    }

    @Override
    public Optional<Candidate> findById(String id) {
        return Optional.empty();
    }
}
