package org.yyx.pocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String messageId;
    private String userId;
    private String text;
    private Timestamp messageTimestamp;
}
