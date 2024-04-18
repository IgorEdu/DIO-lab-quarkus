package api.dto.out;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ElectionCandidate {
    public String electionId;
    public String candidateId;
    public String candidatePhoto;
    public String candidateGivenName;
    public String candidateFamilyName;
    public String candidateEmail;
    public String candidatePhone;
    public String candidateJobTitle;
    public int votes;

    public ElectionCandidate(@ProjectedFieldName("election_candidate.election_id") String electionId,
                             @ProjectedFieldName("election_candidate.candidate_id") String candidateId,
                             @ProjectedFieldName("candidate.photo") String candidatePhoto,
                             @ProjectedFieldName("candidate.given_name") String candidateGivenName,
                             @ProjectedFieldName("candidate.given_name") String candidateFamilyName,
                             @ProjectedFieldName("candidate.given_name") String candidateEmail,
                             @ProjectedFieldName("candidate.given_name") String candidatePhone,
                             @ProjectedFieldName("candidate.given_name") String candidateJobTitle,
                             Integer votes) {
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.candidatePhoto = candidatePhoto;
        this.candidateGivenName = candidateGivenName;
        this.candidateFamilyName = candidateFamilyName;
        this.candidateEmail = candidateEmail;
        this.candidatePhone = candidatePhone;
        this.candidateJobTitle = candidateJobTitle;
        this.votes = votes;
    }
}
