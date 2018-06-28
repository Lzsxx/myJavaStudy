package thread.sell_ticket;

 class TicketsRunnable implements Runnable{
    private volatile int ticketCount = 5;

    @Override
    public void run() {
        while (ticketCount > 0) {
            ticketCount --;
            System.out.println(Thread.currentThread() + "  sell one ticket, left :" + ticketCount);
        }
    }

    public static void main(String[] args) {
        TicketsRunnable r1 = new TicketsRunnable();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r1);

        t1.start();
        t2.start();
        t3.start();
    }
}
//Thread[Thread-1,5,main]  sell one ticket, left :3
//Thread[Thread-1,5,main]  sell one ticket, left :2
//Thread[Thread-1,5,main]  sell one ticket, left :1
//Thread[Thread-1,5,main]  sell one ticket, left :0
//Thread[Thread-0,5,main]  sell one ticket, left :0

//Thread[Thread-0,5,main]  sell one ticket, left :4
//Thread[Thread-0,5,main]  sell one ticket, left :3
//Thread[Thread-0,5,main]  sell one ticket, left :2
//Thread[Thread-0,5,main]  sell one ticket, left :1
//Thread[Thread-0,5,main]  sell one ticket, left :0
//Thread[Thread-1,5,main]  sell one ticket, left :3
//Thread[Thread-2,5,main]  sell one ticket, left :2
//Thread[Thread-0,5,main]  sell one ticket, left :3
//Thread[Thread-2,5,main]  sell one ticket, left :0
//Thread[Thread-1,5,main]  sell one ticket, left :1
