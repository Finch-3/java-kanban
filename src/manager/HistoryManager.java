package manager;

import task.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void addTask(Task task);

    ArrayList<Task> getHistoryList();
}
