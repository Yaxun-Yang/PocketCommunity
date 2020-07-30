package org.yyx.pocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String userId;
    private String username;
    private String password;
    private String avatar;
    private String idCartNum;
    private String phoneNumber;
    private String community;
    private String address;
    private String admin;
}
