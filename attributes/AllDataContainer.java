import java.io.Serializable;
import java.util.List;

public class AllDataContainer implements Serializable {
    private List<Smerf> smerfList;
    private List<Stork> storkList;
    private int storkStaticId;
        
    public AllDataContainer(List<Smerf> smerfList, List<Stork> storkList, int storkStaticId) {
        this.smerfList = smerfList;
        this.storkList = storkList;
        this.storkStaticId = storkStaticId;
    }

    public List<Smerf> getSmerfList() {
        return smerfList;
    }

    public List<Stork> getStorkList() {
        return storkList;
    }

    public int getStorkStaticId() {
        return storkStaticId;
    }
}
