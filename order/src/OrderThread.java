/**
 * Created by jingxuan on 2018/7/5.
 */
public class OrderThread extends Thread {
    public void run() {
        System.out.println(Order.maxOrderId++);
    }
}
