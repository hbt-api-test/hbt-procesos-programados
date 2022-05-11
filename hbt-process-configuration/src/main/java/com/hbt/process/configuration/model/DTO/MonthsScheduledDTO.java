package com.hbt.process.configuration.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthsScheduledDTO {

    private Long id;
    private String name;


}
