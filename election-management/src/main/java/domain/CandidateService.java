package domain;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CandidateService {
    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void save(Candidate candidate){
        repository.persist(infrastructure.repositories.entities.Candidate.fromDomain(candidate));
    }

    public List<Candidate> findAll() {
        return repository.listAll().stream().map(infrastructure.repositories.entities.Candidate::toDomain).toList();
    }

    public List<Candidate> find(CandidateQuery query) {
        return repository.find(query);
    }

    public Candidate findById(String id) {
        return repository.findById(id).toDomain();
    }
}
