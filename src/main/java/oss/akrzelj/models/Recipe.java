package oss.akrzelj.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Recipe")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "ingredients", columnDefinition = "JSONB[]")
    private String[] ingredients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}

