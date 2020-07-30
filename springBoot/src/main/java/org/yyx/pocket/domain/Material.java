package org.yyx.pocket.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private String materialId;
    private String materialName;
    private String origin;
    private String destination;
    private String transferStation;
}
