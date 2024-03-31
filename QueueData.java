import java.util.*;

public class QueueData<E> {
    private Queue<E> queueList;

    public QueueData() {
        queueList = new LinkedList<E>();
    }

    public void masukTugas(E object) {
        queueList.add(object);
        System.out.println("Menambahkan " + object + " Ke Dalam List Tugas\n");
    }

    public E hapusTugas() throws NoSuchElementException {
        System.out.println("Menghapus " + queueList.peek() + " Dari Daftar\n");
        return queueList.remove();

    }

    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    public void printQueue() {
        Iterator<E> iterator = queueList.iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            System.out.print("(" + value + ") ");
        }
    }
}