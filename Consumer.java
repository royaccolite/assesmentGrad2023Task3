import java.util.Random;

class Consumer implements Runnable {
    private Random random = new Random();

    @Override
    public void run() {
        try {
            while (true) {
                int cargoWeight = random.nextInt(Global.MAX_CARGO_WEIGHT - Global.MIN_CARGO_WEIGHT + 1) + Global.MIN_CARGO_WEIGHT;
                String destination = random.nextBoolean() ? "Gotham" : "Atlanta";

                // Place order
                placeOrder(cargoWeight, destination);

                Thread.sleep(100); // Wait time for order placement (optimized value)
            }
        } catch (InterruptedException e) {
            // Thread interrupted
        }
    }

    private synchronized void placeOrder(int cargoWeight, String destination) throws InterruptedException {
        long startTime=System.currentTimeMillis();
        if(System.currentTimeMillis()-startTime<60000) {
            long startTime2=System.currentTimeMillis();
            if ((startTime2-startTime) < 60000) {
                Global.canceledOrders++;
                Global.revenue -= 250; // Setback by $250
                System.out.println(Thread.currentThread().getName() + " - Order Canceled");
                return;
            }
            Thread.sleep(1000);
        }

        // Simulate successful order placement
        System.out.println(Thread.currentThread().getName() + " - Order Placed: Cargo Weight = " + cargoWeight +
                " tons, Destination = " + destination);
        Global.revenue += 1000; // Increment revenue by $1000
    }
}
