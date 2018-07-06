import jdk.nashorn.internal.parser.JSONParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jingxuan on 2018/7/5.
 */
public class OrderMain {
    private static BlockingQueue<Integer> queue = new BlockingQueue<Integer>(10);
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread() {
            @Override
            public void run() {
                for( int j = 0 ; j < 100 ; j++ )
                    try {
                        queue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        a.start();
        Thread b = new Thread() {
            @Override
            public void run() {
                for( int j = 0 ; j < 100 ; j++ )
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        b.start();
        a.join();
        b.join();
        System.out.println();
    }
}
