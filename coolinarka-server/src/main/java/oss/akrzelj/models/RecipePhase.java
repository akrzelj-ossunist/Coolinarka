package oss.akrzelj.models;

import io.micrometer.core.instrument.util.StringUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recipe_Phase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipePhase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
    private Recipe recipe;

    @Column(name = "phase_number")
    private int index;

    @Column
    private String description;
}
