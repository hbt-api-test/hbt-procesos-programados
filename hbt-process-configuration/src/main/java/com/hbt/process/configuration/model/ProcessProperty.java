package com.hbt.process.configuration.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PARAMETROPROCESO")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessProperty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParametroProceso;
    private String nombreParametroProceso;
    private String valorParametroProceso;
    private Boolean requerido;


}
