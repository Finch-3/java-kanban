package task;

public class Subtask extends task.Task {
    private final int epicId;

    public Subtask(String name, String description, int epicId) { //создание подзадачи
        super(name, description);
        this.epicId = epicId;
    }

    public Subtask(String name, String description, int epicId, int id, Status status) { //обновление подзадачи
        super(name, description, id, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }


    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", epicId=" + getEpicId() +
                ", status=" + getStatus() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
