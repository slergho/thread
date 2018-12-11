package testdemo.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderAndWriter<K,V> {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Map<K, V> map;


    public ReaderAndWriter(Map<K, V> map) {
        this.map = map;
    }

    public V put(K key, V value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReaderAndWriter<String, Integer> rw = new ReaderAndWriter<>(new HashMap<>());
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new TestRunnable(rw) );
        }
        exec.shutdown();
    }
    static class TestRunnable implements Runnable{
        private final ReaderAndWriter<String, Integer> rw;
        private final String KEY = "x";

        TestRunnable(ReaderAndWriter<String, Integer> rw) {
            this.rw = rw;
        }

        @Override
        public void run() {
            Random ran = new Random();
            int r = ran.nextInt(100);
            if (r<30){
                rw.put(KEY,r);
            }else {
                System.out.println(rw.map.get(KEY));
            }
        }
    }
}

