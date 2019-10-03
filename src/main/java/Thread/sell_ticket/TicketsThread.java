package Thread.sell_ticket;

 class TicketsThread extends Thread{
    private String name;
    private int ticketCount = 5;

    public TicketsThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketCount > 0) {
            ticketCount --;
            System.out.println(name + "  sell one ticket, left :" + ticketCount);
        }
    }

    public static void main(String[] args) {
        TicketsThread t1 = new TicketsThread("1");
        TicketsThread t2 = new TicketsThread("2");
        TicketsThread t3 = new TicketsThread("3");

        t1.start();
        t2.start();
        t3.start();

    }

}

//        1  sell one ticket, left :4
//        1  sell one ticket, left :3
//        1  sell one ticket, left :2
//        1  sell one ticket, left :1
//        1  sell one ticket, left :0
//        2  sell one ticket, left :4
//        2  sell one ticket, left :3
//        3  sell one ticket, left :4
//        2  sell one ticket, left :2
//        3  sell one ticket, left :3
//        2  sell one ticket, left :1
//        3  sell one ticket, left :2
//        2  sell one ticket, left :0
//        3  sell one ticket, left :1
//        3  sell one ticket, left :0