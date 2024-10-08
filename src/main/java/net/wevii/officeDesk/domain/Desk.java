package net.wevii.officeDesk.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "DESK")
@Entity
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean isScreen;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "office_id",nullable = false)
    private Office office;
}
