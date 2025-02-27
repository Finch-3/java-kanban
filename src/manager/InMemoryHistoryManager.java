package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> historyList;

    public InMemoryHistoryManager() {
        this.historyList = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        if (historyList.size() >= MAX_SIZE_HISTORY) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
