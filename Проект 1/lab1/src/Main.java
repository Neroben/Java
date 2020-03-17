import Shop.Shop;

public class Main {
    public static void main(String[] args) {
        Shop a = Shop.openShop("Конфетка", 3);
        for(int i = 1; i <= 10; i++)
            a.newBuyer("Александр Бот #" + i);
        a.closeShop();
    }
}
