public class InventoryManager {
    private static InventoryManager instance;


    private InventoryManager(){

    }


    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }
}
