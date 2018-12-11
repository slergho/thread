package testdemo.thread;


public class Test {


    public static void main(String[] args) {
        method();
    }

    public static void method (){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
              //  Thread.yield();
                System.out.println("t1");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
              //  try {
                t1.setPriority(9);
                //   } catch (InterruptedException e) {
                  //  e.printStackTrace();
            //    }
                System.out.println("t2");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
              //  try {
                t2.setPriority(8);
              //  Thread.yield();
             //   } catch (InterruptedException e) {
                //    e.printStackTrace();
             //   }
                System.out.println("t3");
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                t3.setPriority(1);
                System.out.println("t4");
            }
        });
        t4.start();
        t2.start();
        t1.start();
        t3.start();

    }
}
