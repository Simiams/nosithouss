package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "posts")
@DiscriminatorValue("post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String title;
    @OneToOne
    @JoinColumn(name = "last_version_id")
    private Post lastVersion;
    @Column(nullable = false)
    private int nbLike;
    @Column(nullable = false)
    private int nbDislike;
}
