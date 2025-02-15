package task;

public class Subtask extends task.Task {
    private int epicId;

    public Subtask(int epicId, String name, String description) { //создание подзадачи
        super(name, description);
        this.epicId = epicId;
    }

    public Subtask(int id, String name, String description, Status status) { //обновление подзадачи
        super(id, name, description, status);
        this.epicId = 0;
    }

    public void setEpicId(int epicId) {
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
