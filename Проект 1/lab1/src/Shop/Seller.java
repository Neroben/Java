package Shop;

import java.util.LinkedList;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;

public class Seller implements Runnable {
    Thread thrd;
    private LinkedList<Buyer> fifo;
    private String name;
    boolean work;
    boolean working;

    boolean getWorking(){
        return working;
    }

    Seller(String name){
        this.name = name;
        work = true;
        working = true;
        fifo = new LinkedList<Buyer>();
        thrd = new Thread(this);
    }

    public String getName(){
        return name;
    }

    public void endWork(){
        work = false;
    }

    public void setFifo(Buyer a){
        fifo.addLast(a);
    }

    public int getLength(){
        return fifo.size();
    }

    public static Seller createAndStart (String name) {
        Seller myThrd = new Seller(name);
        myThrd.thrd.start();
        return myThrd;
    }



    @Override
    public void run() {
        while (work || (!fifo.isEmpty())) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(!fifo.isEmpty());
            if (!fifo.isEmpty()) {
                Buyer path = fifo.getFirst();
                fifo.removeFirst();
                int time = ((int) (random() * 10) * 1000);
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mySinh.printconsole(path, this, time/1000);
            }
        }
        working = false;
    }
}

class mySinh{
    synchronized static public void printconsole(Buyer a, Seller b, int time){
        System.out.println("-----------------------------------------");
        System.out.println("Покупатель: " + a.getName());
        System.out.println("Продавец: " + b.getName());

        System.out.println("Информация о чеке");
        System.out.println("Время обслуживания покупателя " + time + " секунд");
        System.out.println("-----------------------------------------");
    }
}