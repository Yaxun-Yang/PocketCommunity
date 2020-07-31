package org.yyx.pocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyx.pocket.domain.Activity;
import org.yyx.pocket.domain.Notification;
import org.yyx.pocket.persistence.CommunityMapper;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Transactional
    public String insertActivity(Activity activity)
    {
        communityMapper.insertActivity(activity);
        return communityMapper.getRecentActivity();
    }

    public String insertNotification(Notification notification)
    {
        communityMapper.insertNotification(notification);
        return communityMapper.getRecentNotification();
    }

    public void deleteActivity(String activityId)
    {
        communityMapper.deleteActivity(activityId);
    }

    public void deleteNotification(String notificationId)
    {
        communityMapper.deleteNotification(notificationId);
    }

    public void updatePicture(String activityId, String picture)
    {
        communityMapper.updatePicture(activityId, picture);
    }

    public List<Activity> getActivityList()
    {
        return communityMapper.getActivityList();
    }

    public List<Notification> getNotificationList()
    {
        return communityMapper.getNotificationList();
    }
}
