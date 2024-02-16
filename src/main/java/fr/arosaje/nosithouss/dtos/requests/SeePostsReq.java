package fr.arosaje.nosithouss.dtos.requests;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class SeePostsReq {
    private int number;
    private Timestamp createdAt;
}
