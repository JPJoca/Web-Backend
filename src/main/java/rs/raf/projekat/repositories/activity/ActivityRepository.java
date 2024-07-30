package rs.raf.projekat.repositories.activity;

import rs.raf.projekat.entities.Activity;

import java.util.List;

public interface ActivityRepository {
    public List<Activity> allActivities();

    public Activity findActivityById(int id);

    public Activity addActivity(Activity activity);

    public void deleteActivity(int id);
}