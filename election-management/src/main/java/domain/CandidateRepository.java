package domain;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.transaction.Transactional;
import java.util.List;

public class CandidateRepository implements PanacheRepositoryBase<infrastructure.repositories.entities.Candidate, String>{

    @Transactional
    public void save(List<Candidate> candidates){
        for(Candidate candidate : candidates){
            persist(infrastructure.repositories.entities.Candidate.fromDomain(candidate));
        }
    }

//    public void save(Candidate candidate){
//        save(List.of(candidate));
//    }

    public List<Candidate> find(CandidateQuery query) {
        return null;
    }


//    default List<Candidate> findAll(){
//        return find(new CandidateQuery.Builder().build());
//    }
//
//    public infrastructure.repositories.entities.Candidate findById(String id){
//        CandidateQuery query = new CandidateQuery.Builder().ids(Set.of(id)).build();
//        return find(query).stream().findFirst();
//    }
}
