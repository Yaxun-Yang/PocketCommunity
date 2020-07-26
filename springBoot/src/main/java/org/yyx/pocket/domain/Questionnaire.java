package org.yyx.pocket.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questionnaire {
    private String questionnaireId;
    private String url;
}
