import manager.InMemoryTaskManager;
import manager.TaskManager;
import task.Epic;
import task.Status;
import task.Subtask;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager manager = new InMemoryTaskManager();
        Epic epic = new Epic("111", "222");
        manager.addEpic(epic);
        System.out.println(manager.getEpics());
        Subtask subtask = new Subtask(epic.getId(), "222", "555");
        manager.addSubtask(subtask);
        System.out.println(manager.getEpics());
        Subtask updateSub = new Subtask(subtask.getId(), "999", "999", Status.IN_PROGRESS);
        manager.updateSubtask(updateSub);
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtaskList(1));

    }
}
