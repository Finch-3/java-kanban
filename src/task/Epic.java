package task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends task.Task {
    private List<Subtask> subtaskList;

    public Epic(String name, String description) { //создание эпика
        super(name, description);
        this.subtaskList = new ArrayList<>();
    }

    public Epic(int id, String name, String description) { //обновление эпика
        super(id, name, description, null);
        this.subtaskList = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtaskList.add(subtask);
    }

    public void setSubtaskList(List<Subtask> subtaskList) {
        this.subtaskList = subtaskList;
    }

    public List<Subtask> getSubtaskList() {
        return subtaskList;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", subtaskList=" + getSubtaskList() +
                ", status=" + getStatus() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
