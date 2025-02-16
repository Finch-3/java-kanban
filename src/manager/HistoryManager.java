package manager;

import task.Task;

import java.util.ArrayList;

public interface HistoryManager {
    int MAX_SIZE_HISTORY = 10;

    void addTask(Task task);

    ArrayList<Task> getHistory();
}
