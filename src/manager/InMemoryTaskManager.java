package manager;

import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Subtask> subtasks;
    private int id;
    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.id = 1;
        this.historyManager = new InMemoryHistoryManager();
    }

    private int generateId() { //генератор ключей
        return id++;
    }

    @Override
    public void addTask(Task task) { //добавить задачу
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(Task task) { //обновление задачи
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void clearTask() { //удаление всех задач
        tasks.clear();
    }

    @Override
    public Task getTaskById(int taskId) { //возвращает задачу по идентификатору
        historyManager.addTask(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public void deleteTaskById(int taskId) { //удаление задачи по идентификатору
        tasks.remove(taskId);
    }

    @Override
    public void addEpic(Epic epic) { //добавить эпик
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateEpic(Epic epic) { //обновление эпика с очисткой хэшмап от его подзадач
        if (epics.containsKey(epic.getId())) {
            ArrayList<Subtask> subtasksList = epics.get(epic.getId()).getSubtaskList();
            if (!subtasksList.isEmpty()) {
                for (Subtask subtask : subtasksList) {
                    subtasks.remove(subtask.getId());
                }
            }
            epic.setStatus(updateStatus(epic));
            epics.put(epic.getId(), epic);
        }
    }

    @Override
    public void clearEpics() { // удаление всех эпиков и подзадач
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Epic getEpicById(int epicId) { //возвращает эпик по идентификатору
        historyManager.addTask(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Subtask getSubtaskById(int subtaskId) { //возвращает подзадачу по идентификатору
        historyManager.addTask(subtasks.get(subtaskId));
        return subtasks.get(subtaskId);
    }

    @Override
    public void clearSubtask() { // удаление всех подзадач
        for (Epic value : epics.values()) {
            value.getSubtaskList().clear();
        }
        subtasks.clear();
    }

    @Override
    public void addSubtask(Subtask subtask) { //добавить подзадачу в эпик
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.setId(generateId());
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtask(subtask);
            epic.setStatus(updateStatus(epic));
            subtasks.put(subtask.getId(), subtask);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) { //обновление подзадачи
        if (subtasks.containsKey(subtask.getId())) {
            subtask.setEpicId(subtasks.get(subtask.getId()).getEpicId());
            deleteSubtaskList(subtask);
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

    @Override
    public void deleteSubtaskById(int subtaskId) { //удаление подзадачи по идентификатору
        if (subtasks.containsKey(subtaskId)) {
            Subtask subtask = subtasks.get(subtaskId);
            Epic epic = epics.get(subtask.getEpicId());
            epic.getSubtaskList().remove(subtask);
            subtasks.remove(subtaskId);
        }
    }

    @Override
    public void deleteEpicById(int epicId) { //удаление эпика по идентификатору
        if (epics.containsKey(epicId)) {
            ArrayList<Subtask> subtasksList = epics.get(epicId).getSubtaskList();
            if (!subtasksList.isEmpty()) {
                for (Subtask subtask : subtasksList) {
                    subtasks.remove(subtask.getId());
                }
            }
            epics.remove(epicId);
        }
    }

    @Override
    public ArrayList<Subtask> getSubtaskList(int epicId) { //возвращает список подзадач эпика по id
        return epics.get(epicId).getSubtaskList();
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

    @Override
    public ArrayList<Task> getHistory() {
        return historyManager.getHistoryList();
    }

    @Override
    public ArrayList<Task> getTasks() { //вернуть список задач
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() { //вернуть список эпиков
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() { //вернуть список подзадач
        return new ArrayList<>(subtasks.values());
    }
}
