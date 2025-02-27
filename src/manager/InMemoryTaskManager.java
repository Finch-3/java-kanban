package manager;

import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, Subtask> subtasks;
    private HistoryManager historyManager;
    private int id;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.historyManager = Manager.getDefaultHistory();
        this.id = 1;
    }

    private int generateId() { //генератор ключей
        return id++;
    }

    @Override
    public int addTask(Task task) { //добавить задачу
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int updateTask(Task task) { //обновление задачи
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task.getId();
        }
        return -1;
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
    public int addEpic(Epic epic) { //добавить эпик
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
    public int updateEpic(Epic epic) { //обновление эпика
        if (epics.containsKey(epic.getId())) {
            List<Subtask> subtaskList = epics.get(epic.getId()).getSubtaskList();
            epic.setSubtaskList(subtaskList);
            epic.setStatus(updateStatus(epic));
            epics.put(epic.getId(), epic);
            return epic.getId();
        }
        return -1;
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
    public int addSubtask(Subtask subtask) { //добавить подзадачу в эпик
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.setId(generateId());
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtask(subtask);
            epic.setStatus(updateStatus(epic));
            subtasks.put(subtask.getId(), subtask);
            return subtask.getId();
        }
        return -1;
    }

    @Override
    public int updateSubtask(Subtask subtask) { //обновление подзадачи
        if (subtasks.containsKey(subtask.getId())) {
            subtask.setEpicId(subtasks.get(subtask.getId()).getEpicId()); //перезаписывает ид эпика родителя
            updateSubtaskList(subtask);
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            epic.setStatus(updateStatus(epic));
            return subtask.getId();
        }
        return -1;
    }

    public void updateSubtaskList(Subtask subtask) { //обновляет задачу в списке эпика
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
            List<Subtask> subtasksList = epics.get(epicId).getSubtaskList();
            if (!subtasksList.isEmpty()) {
                for (Subtask subtask : subtasksList) {
                    subtasks.remove(subtask.getId());
                }
            }
            epics.remove(epicId);
        }
    }

    @Override
    public List<Subtask> getSubtaskList(int epicId) { //возвращает список подзадач эпика по id
        return epics.get(epicId).getSubtaskList();
    }

    public Status updateStatus(Epic epic) { //обновление статуса эпика
        boolean flag = true;
        List<Subtask> subtaskList = epic.getSubtaskList();
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
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public List<Task> getTasks() { //вернуть список задач
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpics() { //вернуть список эпиков
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getSubtasks() { //вернуть список подзадач
        return new ArrayList<>(subtasks.values());
    }
}
