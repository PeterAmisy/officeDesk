package net.wevii.officeDesk.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Office")
@Entity
public class Office{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int numberOfDesk;
    @OneToMany
    private Set<Desk> hasDesk;
}
