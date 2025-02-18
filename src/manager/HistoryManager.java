package manager;

import task.Task;

import java.util.List;

public interface HistoryManager {
    int MAX_SIZE_HISTORY = 10;

    void addTask(Task task);

    List<Task> getHistory();
}
