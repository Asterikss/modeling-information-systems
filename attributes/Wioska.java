import java.io.Serializable;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Wioska {
    private static final String FILENAME = "data.ser";

    public static void saveDataToFile(List<Smerf> smerfList, List<Stork> storkList, int storkStaticId) {
        AllDataContainer container = new AllDataContainer(smerfList, storkList, storkStaticId);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(container);
            System.out.println("Data has been stored to a file\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static AllDataContainer loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (AllDataContainer) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initializeClasses() {
        if (!Files.exists(Paths.get(FILENAME))) {
            System.out.println("File to read from not found");
        } else {
            AllDataContainer container = loadDataFromFile();
            if (container != null) {
                Smerf.setSmurfList(container.getSmerfList());
                Stork.setStorkList(container.getStorkList());
                Stork.setStaticId(container.getStorkStaticId());
                System.out.println("Classes have been initialized");
            } else {
                System.out.println("No data to be read / Invalid data");
            }

        }
    }

    public static void listSmerfs() {
        System.out.println("Smerfs in the village");
        System.out.println("------------");
        for (Smerf smerf : Smerf.getSmerfList()) {
            System.out.println(smerf);
        }
        System.out.println("------------");

    }

}
