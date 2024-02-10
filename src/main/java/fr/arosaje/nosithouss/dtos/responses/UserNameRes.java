package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserNameRes {

    private String userName;
}
