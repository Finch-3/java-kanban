import manager.InMemoryTaskManager;
import manager.TaskManager;
import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager manager = new InMemoryTaskManager();
        Task task = new Task("111", "222");
        Task task1 = new Task("111", "222");
        Task task2 = new Task("111", "222");
        Task task3 = new Task("111", "222");
        Epic epic = new Epic("555", "888");
        Epic epic1 = new Epic("555", "888");
        Epic epic2 = new Epic("555", "888");
        manager.addTask(task);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        manager.addEpic(epic);
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getTaskById(3);
        manager.getTaskById(1);
        manager.getEpicById(5);
        System.out.println(manager.getHistory());

    }
}
