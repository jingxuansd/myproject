/**
 * @author jingxuan
 */
public class BlockingQueue<T> {
    T[] arr;
    private final int capacity;
    private int index = 0;

    public BlockingQueue(int size) {
        arr = (T[]) new Object[size];
        capacity = size;
    }

    public void put(T t) throws InterruptedException {
        if (t == null) {
            return;
        }
        synchronized (this) {
            if (index == capacity) {
                wait();
            }
            arr[index++] = t;
            if (index == 0) {
                notifyAll();
            }
        }
    }

    public T take() throws InterruptedException {
        T choose;
        synchronized (this) {
            if (index == 0) {
                wait();
            }
            choose = arr[--index];
            // delete
            arr[index] = null;
            if (index == capacity) {
                notifyAll();
            }
        }
        return choose;
    }
}
