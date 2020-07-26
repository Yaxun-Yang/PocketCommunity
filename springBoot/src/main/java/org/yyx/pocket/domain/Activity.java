package org.yyx.pocket.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String activityId;
    private String text;
    private String picture;
}
