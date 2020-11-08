package com.masivian.casino.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Edwin Steven Perez Molina
 */

@Entity
@Table(name = "users", catalog = "db_casino", schema = "public")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    @Getter
    @Setter
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Basic(optional = false)
    @Column(name = "money")
    @Getter
    @Setter
    private double money;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private Set<Bet> betsPlaced;

}
