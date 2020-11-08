package com.masivian.casino.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Edwin Steven Perez Molina
 */

@Entity
@Table(name = "roulettes", catalog = "db_casino", schema = "public")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Roulette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_roulette")
    @Getter
    @Setter
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer idRoulette;

    @Basic(optional = false)
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Basic(optional = false)
    @Column(name = "open")
    @Getter
    @Setter
    private boolean open;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roulette")
    @Getter
    @Setter
    private Set<Bet> betsPlaced;

}
