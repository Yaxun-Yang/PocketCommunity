package org.yyx.pocket.persistence;



import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Users;

import java.util.List;

@Repository
public interface UsersMapper {
    void insertUser(Users user);
    void deleteUser(String userId);
    void updateUser(Users user);
    String getRecentUserId();
    Users getUserByUsernameAndPassword(String username, String password);
    Users getUser(String userId);
    List<Users> getUserList();
}
