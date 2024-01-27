package oss.akrzelj.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Recipe")
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @Column(name = "instructions")
    private String[] instructions;

    @ElementCollection
    @Column(name = "ingredients")
    private String[] ingredients;

    @ElementCollection
    @Column(name = "amounts")
    private String[] amounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}

