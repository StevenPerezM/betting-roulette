package com.masivian.casino.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Edwin Steven Perez Molina
 */

@Entity
@Table(name = "bets", catalog = "db_casino", schema = "public")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bet")
    @Getter
    @Setter
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer idBet;

    @Column(name = "number")
    @Getter
    @Setter
    private Short number;

    @Column(name = "color")
    @Getter
    @Setter
    private String color;

    @Basic(optional = false)
    @Column(name = "money_bet")
    @Getter
    @Setter
    private double moneyBet;

    @JoinColumn(name = "id_roulette", referencedColumnName = "id_roulette")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    @JsonIgnore
    private Roulette roulette;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    @JsonIgnoreProperties({"betsPlaced"})
    private User user;

}
