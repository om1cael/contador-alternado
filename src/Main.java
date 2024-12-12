public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();
        Thread thread = new Thread(() -> sharedCounter.countThread());
        Thread thread2 = new Thread(() -> sharedCounter.countThreadTwo());

        thread.start();
        thread2.start();
    }
}

class SharedCounter {
    int counter = 0;

    public synchronized void countThread()  {
        while (this.counter < 9) {
            if((this.counter + 1) % 2 == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            this.counter++;
            this.notify();
            System.out.println(this.counter + "a");
        }
    }

    public synchronized void countThreadTwo() {
        while(this.counter < 9) {
            if((this.counter + 1) % 2 != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            this.counter++;
            this.notify();
            System.out.println(this.counter + "b");
        }
    }
}