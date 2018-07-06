/**
 * Created by jingxuan on 2018/7/6.
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
        if (t == null) return;
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
            arr[index] = null; //删除
            if (index == capacity) {
                notifyAll();
            }
        }
        return choose;
    }
}
