import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Subtask> subtasks;
    private int id;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.id = 10000;
    }

    private int generateId() { //генератор ключей
        return id++;
    }

    public void addTask(Task task) { //добавить задачу
        task.setId(generateId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) { //обновление задачи
        if (tasks.containsKey(task.getId())) {
            task.setStatus(Status.IN_PROGRESS);
            tasks.put(task.getId(), task);
        }
    }

    public void clearTask() { //удаление всех задач
        if (!tasks.isEmpty()) {
            tasks.clear();
        }
    }

    public Task getTaskById(int taskId) { //возвращает задачу по идентификатору
        if (tasks.containsKey(taskId)) {
            for (Integer id : tasks.keySet()) {
                if (id.equals(taskId)) {
                    return tasks.get(taskId);
                }
            }
        }
        return null;
    }

    public void deleteTaskById(int taskId) { //удаление задачи по идентификатору
        if (tasks.containsKey(taskId)) {
            for (Integer id : tasks.keySet()) {
                if (id.equals(taskId)) {
                    tasks.remove(taskId);
                    break;
                }
            }
        }
    }

    public void addEpic(Epic epic) { //добавить эпик
        epic.setId(generateId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
    }

    public void clearEpics() { // удаление всех эпиков и подзадач
        if (!epics.isEmpty()) {
            epics.clear();
            if (!subtasks.isEmpty()) {
                subtasks.clear();
            }
        }
    }

    public Epic getEpicById(int epicId) { //возвращает эпик по идентификатору
        if (epics.containsKey(epicId)) {
            for (Integer id : epics.keySet()) {
                if (id.equals(epicId)) {
                    return epics.get(epicId);
                }
            }
        }
        return null;
    }

    public Subtask getSubtaskById(int subtaskId) { //возвращает подзадачу по идентификатору
        if (subtasks.containsKey(subtaskId)) {
            for (Integer id : subtasks.keySet()) {
                if (id.equals(subtaskId)) {
                    return subtasks.get(subtaskId);
                }
            }
        }
        return null;
    }

    public void clearSubtask() { // удаление всех подзадач
        if (!subtasks.isEmpty()) {
            subtasks.clear();
        }
    }

    public void addSubtask(Subtask subtask) { //добавить подзадачу в эпик
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.setId(generateId());
            subtask.setStatus(Status.NEW);
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtask(subtask);
            epic.setStatus(updateStatus(epic));
            subtasks.put(subtask.getId(), subtask);
        }
    }

    public void updateSubtask(Subtask subtask) { //обновление подзадачи
        if (subtasks.containsKey(subtask.getId())) {
            deleteSubtaskList(subtask);
            subtask.setStatus(Status.IN_PROGRESS);
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            epic.setStatus(updateStatus(epic));
        }
    }

    public void deleteSubtaskList(Subtask subtask) { //обновляет задачу в списке эпика
        Subtask deleteSubtask = subtasks.get(subtask.getId());
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtaskList().remove(deleteSubtask);
        epic.addSubtask(subtask);
    }

    public void deleteSubtaskById(int subtaskId) { //удаление подзадачи по идентификатору
        if (subtasks.containsKey(subtaskId)) {
            for (Integer id : subtasks.keySet()) {
                if (id.equals(subtaskId)) {
                    subtasks.remove(subtaskId);
                    break;
                }
            }
        }
    }

    public void updateEpic(Epic epic) { //обновление эпика с очисткой хэшмап от его подзадач
        if (epics.containsKey(epic.getId())) {
            ArrayList<Subtask> subtasksList = epics.get(epic.getId()).getSubtaskList();
            if (!subtasksList.isEmpty()) {
                for (Subtask subtask : subtasksList) {
                    subtasks.remove(subtask.getId());
                }
            }
            epic.setStatus(Status.NEW);
            epics.put(epic.getId(), epic);
        }
    }

    public void deleteEpicById(int epicId) { //удаление эпика по идентификатору
        if (epics.containsKey(epicId)) {
            ArrayList<Subtask> subtasksList = epics.get(epicId).getSubtaskList();
            if (!subtasksList.isEmpty()) {
                for (Subtask subtask : subtasksList) {
                    subtasks.remove(subtask.getId());
                }
            }
            for (Integer id : epics.keySet()) {
                if (id.equals(epicId)) {
                    epics.remove(epicId);
                    break;
                }
            }
        }
    }

    public ArrayList<Subtask> getSubtaskList(int epicId) { //возвращает список подзадач эпика по id
        if (!epics.get(epicId).getSubtaskList().isEmpty()) {
            return epics.get(epicId).getSubtaskList();
        }
        return null;
    }

    public Status updateStatus(Epic epic) { //обновление статуса эпика
        boolean flag = true;
        ArrayList<Subtask> subtaskList = epic.getSubtaskList();
        if (!subtaskList.isEmpty()) {
            for (Subtask subtask : subtaskList) {
                if (subtask.getStatus().equals(Status.IN_PROGRESS)) {
                    return Status.IN_PROGRESS;
                } else if (subtask.getStatus().equals(Status.NEW)) {
                    flag = false;
                }
            }
            if (flag) {
                return Status.DONE;
            }
        }
        return Status.NEW;
    }

    public HashMap<Integer, Task> getTasks() { //вернуть список задач
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() { //вернуть список эпиков
        return epics;
    }

    public HashMap<Integer, Subtask> getSubtasks() { //вернуть список подзадач
        return subtasks;
    }
}
