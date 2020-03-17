package Shop;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Shop{
    private ArrayList<Seller> workSeller;

    private Shop(){
        workSeller = new ArrayList<Seller>();
    }

    public static Shop openShop(String name, int valueSeller){
        Shop a = new Shop();
        for(int i = 1; i <= valueSeller; i++) {
            a.newSeller("Бот #" + i);
        }
        System.out.println("Магазин " + name + " открылся!");
        return a;
    }

    public void closeShop(){
        for (Seller a:workSeller) {
            a.endWork();
            while (a.getWorking()) {
                //System.out.println("Жду!");
            }
        }

        System.out.println("Магазин закрылся!");
    }

    public void endSeller(){
        if(workSeller.isEmpty())
            return;
        workSeller.get(0).endWork();
        workSeller.remove(0);
    }

    public void newSeller(String name){
        workSeller.add(Seller.createAndStart(name));
    }

    public void newBuyer(String name){
        Seller nice = workSeller.get(0); //минимальная очередь
        for (Seller a:workSeller) {
            if(nice.getLength() > a.getLength())
                nice = a;
        }
        nice.setFifo(new Buyer(name));
    }
}
