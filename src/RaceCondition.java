/**
 * @author chenchris on 2019/4/28.
 * 3 types of the synchronized
 * 1) lock object
 * 2) lock this
 * 3) synchronized method
 */
public class RaceCondition {

    public static void main(String[] args) {

        MyTestObject testObject = new MyTestObject(0);

        final MyRunnable runnable = new MyRunnable(testObject);

        Thread thread1 = new Thread(runnable, "Thread-1");

        Thread thread2 = new Thread(runnable, "Thread-2");

        thread1.start();
        thread2.start();
    }

    public static class MyRunnable implements Runnable {

        private MyTestObject myTestObject;

        private final Object lock = new Object();

        MyRunnable(MyTestObject testObject) {
            this.myTestObject = testObject;
        }

        private void increase() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            myTestObject.counter++;
            System.out.println(Thread.currentThread().getName() + " " + myTestObject.counter);
        }

        private void decrease() {
            myTestObject.counter--;
            System.out.println(Thread.currentThread().getName() + " " + myTestObject.counter);
        }

        @Override
        public void run() {
            //can use this or lock
            synchronized (lock) {
                increase();
                decrease();
            }
        }
    }

    private static class MyTestObject {
        int counter;

        public MyTestObject(int counter) {
            this.counter = counter;
        }
    }
}
