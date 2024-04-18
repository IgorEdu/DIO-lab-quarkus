package infrastructure.repositories;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SQLCandidateRepository extends CandidateRepository {
//    private final EntityManager entityManager;
//
//    public SQLCandidateRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    @Override
//    @Transactional
//    public void save(List<Candidate> candidates) {
////        candidates.stream()
////                .map(infrastructure.repositories.entities.Candidate::fromDomain)
//        for(Candidate candidate : candidates){
//            persist(infrastructure.repositories.entities.Candidate.fromDomain(candidate));
//        }
//    }
//
//    @Override
//    public List<Candidate> find(CandidateQuery query) {
//        var cb = entityManager.getCriteriaBuilder();
//        var cq = cb.createQuery(infrastructure.repositories.entities.Candidate.class);
//        var root = cq.from(infrastructure.repositories.entities.Candidate.class);
//
//        cq.select(root).where(conditions(query,cb,root));
//
//        return entityManager.createQuery(cq)
//                .getResultStream()
//                .map(infrastructure.repositories.entities.Candidate::toDomain)
//                .toList();
//    }
    
    @Override
    public List<Candidate> find(CandidateQuery query) {
        return find("id in (:ids) or (givenName like :name or familyName like :name)",
                Parameters.with("ids", query.ids().orElse(Collections.emptySet()))
                        .and("name", query.name().orElse("") + "%"))
                .stream()
                .map(infrastructure.repositories.entities.Candidate::toDomain)
                .toList();
    }


//    private Predicate[] conditions(CandidateQuery query,
//                                   CriteriaBuilder cb,
//                                   Root<infrastructure.repositories.entities.Candidate> root){
//        return Stream.of(query.ids().map(id -> cb.in(root.get("id")).value(id)),
//                query.name().map(name -> cb.or(cb.like(cb.lower(root.get("familyName")), name.toLowerCase() + "%"),
//                        cb.like(cb.lower(root.get("givenName")), name.toLowerCase() + "%"))))
//                .flatMap(Optional::stream)
//                .toArray(Predicate[]::new);
//    }
}
