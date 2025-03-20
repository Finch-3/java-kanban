package manager;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Map<Integer, Node> historyMap;
    private Node head;
    private Node tail;
    private int size = 0;

    public InMemoryHistoryManager() {
        this.historyMap = new HashMap<>();
    }

    @Override
    public void addTask(Task task) {
        if (historyMap.containsKey(task.getId())) {
            remove(task.getId());
        }
        linkLast(task);
    }

    public void linkLast(Task task) {
        Node oldTail = tail;
        Node newNode = new Node(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
        historyMap.put(task.getId(), newNode);
    }

    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        if (head != null) {
            Node value = head;
            while (value.next != null) {
                list.add(value.data);
                value = value.next;
            }
            list.add(tail.data);
        }
        return list;
    }

    @Override
    public void remove(int id) {
        Node element = historyMap.get(id);
        Node prev = element.prev;
        Node next = element.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            element.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            element.next = null;
        }
        size--;
        historyMap.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private static class Node {
        private Task data;
        private Node next;
        private Node prev;

        public Node(Node prev, Task data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}


