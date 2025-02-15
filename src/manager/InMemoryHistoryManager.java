package manager;

import task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> historyList;

    public InMemoryHistoryManager() {
        this.historyList = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        if (historyList.size() == 10) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return historyList;
    }
}
