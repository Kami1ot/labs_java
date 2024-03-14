public class MyThread extends Thread {

    int time = 0;
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.print("\rВремя выполнения кода равно: " + this.time + " секунд.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.time++;
        }
    }
}

