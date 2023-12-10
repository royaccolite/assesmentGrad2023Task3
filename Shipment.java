import java.util.Random;

class Shipping implements Runnable {
    private Random random = new Random();
    private int trips = 0;

    @Override
    public void run() {
        try {
            while (true) {

                performShippingTasks();

                // Check for maintenance
                if (trips >= Global.TRIPS_BEFORE_MAINTENANCE) {
                    System.out.println(Thread.currentThread().getName() + " - Ship is going for maintenance");
                    Thread.sleep(60000);
                    trips = 0;
                }
            }
        } catch (InterruptedException e) {
            // Thread interrupted
        }
    }

    private synchronized void performShippingTasks() throws InterruptedException {

        int cargo = random.nextInt(Global.MAX_SHIP_CARGO - Global.MIN_SHIP_CARGO + 1) + Global.MIN_SHIP_CARGO;
        System.out.println(Thread.currentThread().getName() + " - Cargo Picked Up: " + cargo + " tons");

//        Global.revenue += cargo * 100; // Increment revenue based on cargo weight
        Global.deliveredOrders++;
        trips++;
        System.out.println(Thread.currentThread().getName() + " - Cargo Delivered: " + cargo + " tons");
        System.out.println("Till now revenue: "+Global.revenue);
    }
}
