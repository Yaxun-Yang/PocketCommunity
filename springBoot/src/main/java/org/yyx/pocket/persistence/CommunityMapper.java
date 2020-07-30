package org.yyx.pocket.persistence;


import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Activity;
import org.yyx.pocket.domain.Notification;

import java.util.List;

@Repository
public interface CommunityMapper {
    void insertActivity(Activity activity);
    void insertNotification(Notification notification);
    void deleteActivity(String activityId);
    void deleteNotification(String notificationId);
    String getRecentActivity();
    String getRecentNotification();
    List<Activity> getActivityList();
    List<Notification> getNotificationList();
}
