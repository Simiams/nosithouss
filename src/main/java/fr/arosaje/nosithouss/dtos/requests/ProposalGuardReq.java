package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProposalGuardReq {
    private Long postId;
    private String userName;
}
