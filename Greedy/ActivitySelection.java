package Greedy;

import java.util.*;

public class ActivitySelection {

    static class Activity {
        String id;
        int start;
        int finish;

        Activity(String id, int start, int finish) {
            this.id = id;
            this.start = start;
            this.finish = finish;
        }
    }

    public static List<Activity> selectMaxActivities(List<Activity> activities) {
        // Step 1: Sort activities by finish time in ascending order
        activities.sort(Comparator.comparingInt(a -> a.finish));

        List<Activity> selected = new ArrayList<>();

        if (activities.isEmpty()) return selected;

        // Step 2: Select the first activity with the earliest finish time
        Activity lastSelected = activities.get(0);
        selected.add(lastSelected);

        // Step 3: Iterate through remaining activities
        for (int i = 1; i < activities.size(); i++) {
            Activity current = activities.get(i);
            // Non-overlapping condition: start time >= finish time of last selected activity
            if (current.start >= lastSelected.finish) {
                selected.add(current);
                lastSelected = current;
            }
        }

        return selected;
    }

    public static void main(String[] args) {
        List<Activity> activities = Arrays.asList(
            new Activity("A1", 1, 4),
            new Activity("A2", 3, 5),
            new Activity("A3", 0, 6),
            new Activity("A4", 5, 7),
            new Activity("A5", 8, 9),
            new Activity("A6", 5, 9),
            new Activity("A7", 6, 10),
            new Activity("A8", 8, 11)
        );

        List<Activity> result = selectMaxActivities(activities);

        System.out.println("Maximum Number of Activities: " + result.size());
        System.out.println("Selected Activities:");
        for (Activity a : result) {
            System.out.printf("%s -> Start: %d, Finish: %d\n", a.id, a.start, a.finish);
        }
    }
}