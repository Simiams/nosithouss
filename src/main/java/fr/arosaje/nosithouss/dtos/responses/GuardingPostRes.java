package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Image;
import lombok.Getter;
import lombok.Setter;

import java.awt.Point;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GuardingPostRes extends PostRes {

    private Point coordinates;
    private Date guardingAt;
    private Date endGuardingAt;
    private List<String> img;

    public GuardingPostRes(GuardingPost post) {
        super(post);
        this.coordinates = new Point(post.getCoordinateX(), post.getCoordinateY());
        this.guardingAt = post.getGuardingAt();
        this.endGuardingAt = post.getEndGuardingAt();
        this.img = post.getImages();
    }

}
