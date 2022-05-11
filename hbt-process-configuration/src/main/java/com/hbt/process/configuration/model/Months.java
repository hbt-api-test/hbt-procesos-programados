package com.hbt.process.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Month;

@Entity
@Table(name = "meses")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Months implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mes")
    private Month months;


}
