package testdemo.thread;

class ThreadDaemon extends Thread {
    private int i = 0;
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println(this.getName() + "-->" + i);
                Thread.sleep(1000);
                /*设置为守护线程后，线程sleep后会自动停止，这样可以当作一种线程停止的手段*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    class TestDaemon {
    public static void main(String[] args) {
        try {
            ThreadDaemon td = new ThreadDaemon();
            td.setDaemon(true);
            td.start();
            Thread.sleep(1000);
            System.out.println("线程td先设置为守护线程，运行后sleep后即停止了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}