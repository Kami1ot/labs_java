import java.util.HashSet;
import java.util.Set;

public class Listik1 extends Listik{
    private String strList;
    private Set<Integer> setList;
    public void converter(){
        strList = inputList.toString().substring(1,inputList.toString().length()-1);
        setList = new HashSet<>(inputList);
    }
    public String getStrList(){
        return strList;
    }

    public Set<Integer> getSetList() {
        return setList;
    }
}
