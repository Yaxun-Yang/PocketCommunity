package org.yyx.pocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyx.pocket.domain.Users;
import org.yyx.pocket.persistence.UsersMapper;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    public String insertUser(Users users)
    {
        usersMapper.insertUser(users);
        return usersMapper.getRecentUserId();
    }

    public void deleteUser(String userId)
    {
        usersMapper.deleteUser(userId);
    }

    public void updateAvatar(String userId, String avatar)
    {
        Users users = usersMapper.getUser(userId);
        users.setAvatar(avatar);
        usersMapper.updateUser(users);
    }

    public void updateUser(Users users)
    {
        usersMapper.updateUser(users);
    }

    public Users getUsersByUsernameAndPassword(String username, String password)
    {
        return usersMapper.getUserByUsernameAndPassword(username, password);
    }


    public List<Users> getUserList()
    {
        return usersMapper.getUserList();
    }
}
