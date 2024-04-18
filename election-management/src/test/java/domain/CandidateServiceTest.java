package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class CandidateServiceTest {
    @Inject
    CandidateService service;
    @InjectMock
    CandidateRepository repository;
//    @Test
//    void save(){
//        Candidate candidate = Instancio.create(Candidate.class);
//
//        service.save(candidate);
//
//        verify(repository).save(infrastructure.repositories.entities.Candidate.fromDomain(candidate));
//        verifyNoMoreInteractions(repository);
//    }

//    @Test
//    void findAll(){
//       List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
//
//        when(repository.findAll()).thenReturn((PanacheQuery<infrastructure.repositories.entities.Candidate>) candidates);
//
//        List<Candidate> result = service.findAll();
//
//        verify(repository).findAll();
//        verifyNoMoreInteractions(repository);
//
//        assertEquals(candidates, result);
//    }

    @Test
    void findById_whenCandidateIsFound_returnsCandidate(){
        Candidate candidate = Instancio.create(Candidate.class);

//        when(repository.findById(candidate.id())).thenReturn(Optional.of(candidate));
        when(repository.findById(candidate.id())).thenReturn(infrastructure.repositories.entities.Candidate.fromDomain(candidate));


        Candidate result = service.findById(candidate.id());

        verify(repository).findById(candidate.id());
        verifyNoMoreInteractions(repository);

        assertEquals(candidate, result);
    }

//    @Test
//    void findById_whenCandidateIsNotFound_throwsException(){
//        Candidate candidate = Instancio.create(Candidate.class);
//
//        when(repository.findById(candidate.id())).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> service.findById(candidate.id()));
//
//        verify(repository).findById(candidate.id());
//        verifyNoMoreInteractions(repository);
//    }
}