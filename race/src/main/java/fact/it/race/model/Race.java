package fact.it.race.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "races")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raceName;
    private Date raceDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RaceLineMember> raceLineMemberList;
}