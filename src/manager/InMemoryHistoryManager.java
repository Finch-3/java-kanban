package manager;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager<E extends Task> implements HistoryManager {

    private Map<Integer, Node<E>> historyMap;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public InMemoryHistoryManager() {
        this.historyMap = new HashMap<>();
        this.size = 0;
    }



    private List<Task> historyList;


//    public InMemoryHistoryManager() {
//        this.historyList = new ArrayList<>();
//    }


    @Override
    public void remove(int id) {

    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}


