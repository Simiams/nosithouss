package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProposalGuardReq {
    private Long postId;
    private String userName;
}
