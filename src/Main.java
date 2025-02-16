import manager.HistoryManager;
import manager.Manager;
import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Manager.getDefault();

        Task task1 = new Task("Задача 1", "Описание 1");
        taskManager.addTask(task1);
        Task task2 = new Task("Задача 2", "Описание 2");
        taskManager.addTask(task2);
        Task task3 = new Task("Задача 3", "Описание 3");
        taskManager.addTask(task3);

        Epic epic1 = new Epic("Эпик 1", "Описание эпик 1");
        taskManager.addEpic(epic1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпик 2");
        taskManager.addEpic(epic2);
        Epic epic3 = new Epic("Эпик 3", "Описание эпик 3");
        taskManager.addEpic(epic3);

        Subtask subtask1 = new Subtask(epic1.getId(), "Подзадача 1", "Описание подзадачи 1");
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(epic2.getId(), "Подзадача 2", "Описание подзадачи 2");
        taskManager.addSubtask(subtask2);
        Subtask subtask3 = new Subtask(epic3.getId(), "Подзадача 3", "Описание подзадачи 3");
        taskManager.addSubtask(subtask3);

        System.out.println("Задачи:" );
        for (Task value : taskManager.getTasks()) {
            System.out.println(value);
        }

        System.out.println();

        System.out.println("Эпики: ");
        for (Epic value : taskManager.getEpics()) {
            System.out.println(value);
        }

        System.out.println();

        System.out.println("Подзадачи: " );
        for (Subtask value : taskManager.getSubtasks()) {
            System.out.println(value);
        }

        System.out.println();

        System.out.println("Получаем задачу 2: ");
        System.out.println(taskManager.getTaskById(task2.getId()));

        System.out.println();

        System.out.println("Получаем эпик 2: ");
        System.out.println(taskManager.getEpicById(epic2.getId()));

        System.out.println();

        System.out.println("Получаем подзадачу 1: ");
        System.out.println(taskManager.getSubtaskById(subtask1.getId()));

        System.out.println();

        System.out.println("История вызовов задач: ");

        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

    }
}
