package org.yyx.pocket.domain;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseTemplate {

    private  int status;
    private String statusText;
    private Object data;
}
