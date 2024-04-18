package infrastructure.repositories;

import domain.Candidate;
import domain.Election;
import domain.ElectionRepository;
import domain.annotations.Principal;
import infrastructure.repositories.entities.ElectionCandidate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Principal
@ApplicationScoped
public class SQLElectionRepository extends ElectionRepository {
    private final EntityManager entityManager;

    public SQLElectionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void submit(Election election) {
//        infrastructure.repositories.entities.Election entity = infrastructure.repositories.entities.Election.fromDomain(election);

        persist(election);

//        entityManager.merge(entity);

//        election.votes()
//                .entrySet()
//                .stream()
//                .map(entry -> ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue()))
//                .forEach(ElectionCandidate::persist);

        for (Map.Entry<Candidate, Integer> entry : election.votes()
                .entrySet()) {
            ElectionCandidate electionCandidate = ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue());
            electionCandidate.persist();
        }
    }

    public List<Election> findAllByCandidate() {
//        List<Election> elections = new ArrayList<>();
//
//        // Consulta usando PanacheQuery
//        PanacheQuery<api.dto.out.ElectionCandidate> query = ElectionCandidate.find(
//                "SELECT e.id AS election_id, c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, ec.votes FROM election_candidate AS ec INNER JOIN elections AS ec ON e.id = ec.election_id  INNER JOIN candidates AS c ON ec.candidate_id = c.id"
//        ).project(api.dto.out.ElectionCandidate.class);
//
//        Map<String, List<api.dto.out.ElectionCandidate>> map = query.stream().collect(groupingBy(o -> o.electionId));
//
//        return map.entrySet()
//                .stream()
//                .map(entry -> {
//                    Map.Entry<Candidate, Integer>[] candidates = entry.getValue()
//                            .stream()
//                            .map(result -> Map.entry(new Candidate(
//                                    result.candidateId,
//                                    Optional.ofNullable(result.candidatePhoto),
//                                    result.candidateGivenName,
//                                    result.candidateFamilyName,
//                                    result.candidateEmail,
//                                    Optional.ofNullable(result.candidatePhone),
//                                    Optional.ofNullable(result.candidateJobTitle)),
//                                    result.votes))
//                            .toArray(Map.Entry[]::new);
//
//                    return new Election(entry.getKey(), Map.ofEntries(candidates));
//                })
//                .toList();

//        List<api.dto.out.ElectionCandidate> resultList = query.list();
//        Map<String, List<Candidate>> candidateMap = new HashMap<>();
//        Map<Candidate, Integer> votesMap = new HashMap<>();
//        for (api.dto.out.ElectionCandidate result : resultList) {
//            String electionId = result.electionId;
//            Candidate candidate = new Candidate(
//                    result.candidateId,
//                    Optional.ofNullable(result.candidatePhoto),
//                    result.candidateGivenName,
//                    result.candidateFamilyName,
//                    result.candidateEmail,
//                    Optional.ofNullable(result.candidatePhone),
//                    Optional.ofNullable(result.candidateJobTitle)
//            );
//            int votes = result.votes;
//
//            // Adiciona o candidato à lista correspondente à eleição
//            candidateMap.computeIfAbsent(electionId, k -> new ArrayList<>()).add(candidate);
//        }
//
//        candidateMap.entrySet()
//
//        // Cria objetos Election a partir do mapa de candidatos
//        for (Map.Entry<String, List<Candidate>> entry : candidateMap.entrySet()) {
//            elections.add(new Election(entry.getKey(), candidateMap));
//        }
//
//        return elections;



        Stream<Object[]> stream = entityManager.createNativeQuery("SELECT e.id AS election_id, c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, ec.votes FROM elections AS e INNER JOIN election_candidate AS ec ON ec.election_id = e.id INNER JOIN candidates AS c ON ec.candidate_id = c.id")
                .getResultStream();

        Map<String, List<Object[]>> map = stream.collect(Collectors.groupingBy(o -> (String) o[0]));

        return map.entrySet()
                .stream()
                .map(entry -> {
                    Map.Entry<Candidate, Integer>[] candidates = entry.getValue()
                            .stream()
                            .map(row -> Map.entry(new Candidate((String)row[1],
                                            Optional.ofNullable((String)row[2]),
                                            (String)row[3],
                                            (String)row[4],
                                            (String)row[5],
                                            Optional.ofNullable((String)row[6]),
                                            Optional.ofNullable((String)row[7])),
                                    (Integer) row[8]))
                            .toArray(Map.Entry[]::new);

                    return new Election(entry.getKey(), Map.ofEntries(candidates));
                })
                .toList();
    }

    @Transactional
    public void sync(Election election) {
        for (Map.Entry<Candidate, Integer> entry : election.votes()
                .entrySet()) {
            ElectionCandidate electionCandidate = ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue());
            electionCandidate.persist();
        }

    //    election.votes()
    //            .entrySet()
    //            .stream()
    //            .map(entry -> ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue()))
    //            .forEach(entityManager::merge);
    }
}