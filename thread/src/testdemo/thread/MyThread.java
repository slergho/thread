package testdemo.thread;

public class MyThread extends Thread {

    public void run(){
        try {
            for (int i = 0; i < 500000 ; i++){
                if (this.isInterrupted()){
                    System.out.println("这个线程断了：马上退出！");
                    throw  new InterruptedException();
                }
                System.out.println("i = :" + (i + 1));
            }
            System.out.println("for循环外的，线程继续运行，没有立即中断！");
        }catch (InterruptedException e){
            System.out.println("线程中断之后，如果进入catch会进行异常处理，之后不会继续运行");
            e.printStackTrace();
        }
    }
}
    class Testinterrupted{
        public static void main(String[] args) {
            try {
                MyThread thread = new MyThread();
                thread.start();
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);//中断线程1秒
                Thread.currentThread().interrupt();//中断当前线程操作
                System.out.println("测试对当前线程是否已经停止1："+ Thread.interrupted());
                System.out.println("测试对当前线程是否已经停止2："+ Thread.interrupted());
                thread.interrupt();
                System.out.println("测试对指定的线程是否已经停止1："+ Thread.interrupted());
                System.out.println("测试对指定的线程是否已经停止2："+ Thread.interrupted());
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end!");
        }
        }
