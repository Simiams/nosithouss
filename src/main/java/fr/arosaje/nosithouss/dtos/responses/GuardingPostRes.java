package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.GuardingPost;
import lombok.Getter;
import lombok.Setter;

import java.awt.Point;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GuardingPostRes extends PostRes {

    private Date guardingAt;
    private Date endGuardingAt;
    private List<String> img;
    private float coordinateX;
    private float coordinateY;

    public GuardingPostRes(GuardingPost post) {
        super(post);
        this.guardingAt = post.getGuardingAt();
        this.endGuardingAt = post.getEndGuardingAt();
        this.img = post.getImages();
        this.coordinateX = post.getCoordinateX();
        this.coordinateY = post.getCoordinateY();
    }
}
