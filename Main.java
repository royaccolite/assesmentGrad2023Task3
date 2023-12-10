import java.util.Random;

class WayneEnterprise {
//    private static final int MAX_REVENUE = 1000000;
//    private static final int MIN_CARGO_WEIGHT = 10;
//    private static final int MAX_CARGO_WEIGHT = 50;
//    private static final int MIN_ORDER_WEIGHT = 1;
//    private static final int MAX_ORDER_WEIGHT = 10;
//    private static final int MIN_SHIP_CARGO = 50;
//    private static final int MAX_SHIP_CARGO = 300;
//    private static final int TRIPS_BEFORE_MAINTENANCE = 5;
//
//    private static int revenue = 0;
//    private static int deliveredOrders = 0;
//    private static int canceledOrders = 0;

    public static void main(String[] args) {
        Thread[] consumerThreads = new Thread[7];
        Thread[] shippingThreads = new Thread[5];

        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i] = new Thread(new Consumer(), "Consumer-" + i);
            consumerThreads[i].start();
        }

        for (int i = 0; i < shippingThreads.length; i++) {
            shippingThreads[i] = new Thread(new Shipping(), "Shipping-" + i);
            shippingThreads[i].start();
        }

        while (Global.revenue < Global.MAX_REVENUE) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread consumerThread : consumerThreads) {
            consumerThread.interrupt();
        }

        for (Thread shippingThread : shippingThreads) {
            shippingThread.interrupt();
        }

        System.out.println("Total Orders Delivered: " + Global.deliveredOrders);
        System.out.println("Total Orders Canceled: " + Global.canceledOrders);
        System.out.println("Total revenue: " + Global.revenue);
    }

}