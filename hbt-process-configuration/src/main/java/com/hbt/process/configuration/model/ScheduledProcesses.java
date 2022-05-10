package com.hbt.process.configuration.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PROCESOPROGRAMADO")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledProcesses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesoProgramado;
    @Column(unique = true, nullable = false)
    private String nombreProcesoProgramado;
    private Integer numeroReintentos;
    @Enumerated(EnumType.ORDINAL)
    private StatusScheduledProcess statusScheduledProcess;
    private String clases;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProcessProperty> processProperty = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = true,
            orphanRemoval = true)
    private ScheduledConfiguration scheduledConfiguration;




}
