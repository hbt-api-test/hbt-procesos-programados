package com.hbt.process.configuration.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PARAMETROPROCESO")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessProperty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParametroProceso;
    private String nombreParametroProceso;
    private String valorParametroProceso;
    private Boolean requerido;


}
