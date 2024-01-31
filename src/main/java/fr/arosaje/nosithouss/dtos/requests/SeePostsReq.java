package fr.arosaje.nosithouss.dtos.requests;

import lombok.Getter;

import java.util.Date;

@Getter
public class SeePostsReq {
    private int number;
    private Date createdAt;
}
