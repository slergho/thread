package testdemo.thread;

class ThreadA extends Thread {
    private int count = 0;
    public int getCount(){
        return count;
    }
    public void run() {
        try {
            for (int i = 0; i < 1000000; i++) {
                count = i;
                System.out.println(this.getName()+"-------->"+count);
                if (count >= 5000) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " 线程已经执行完毕!");
        }
    }
}

class ThreadB extends Thread {
    private int count = 0;
    public int getCount(){
        return count;
    }
    public void run() {
        try {
            for (int i = 0; i < 1000000; i++) {
                count = i;
                System.out.println(this.getName()+"-->"+count);
                if (count >= 5000) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " 线程已经执行完毕!");
        }
    }
}

class MyThread2 extends Thread {

    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 10000000; i++) {
            // Thread.yield();
            // 放弃当前的CPU资源的执行权，将它让给其他的任务去占用CPU的执行时间，时间不确定，可能又马上获取到了执行权继续执行；
            count = count + (i + 1) * 2 / 2;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(this.getName() + "--"
                + Thread.currentThread().getPriority());
        System.out.println(this.getPriority());
        System.out.println(endTime - beginTime);
    }
}

    class TestYield {
    public static void main(String[] args) {
        // MyThread2 m = new MyThread2();
        // m.start();
        // System.out.println(Thread.currentThread().getName());
        // System.out.println(Thread.currentThread().getPriority());

        try {
            ThreadA a = new ThreadA();
            ThreadB b = new ThreadB();
            a.setPriority(Thread.MAX_PRIORITY);
            b.setPriority(Thread.NORM_PRIORITY - 2);
            a.start();
            b.start();
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}