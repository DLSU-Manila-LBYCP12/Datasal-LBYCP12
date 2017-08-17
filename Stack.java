package LOGIC;
/**
 *
 * @author 
 */
import java.util.ArrayList;
public class Stack {

    private ArrayList<Object> list;

    public Stack() {
        list = new ArrayList<>();
    }

    public void push(Object E) {
        list.add(E);
    }

    public Object pop() {
        if (list.size() != 0) {
            return list.remove(list.size() - 1);
        } else {
            return null;
        }
    }

    public Object peek() {
        if (list.size() != 0) {
            return list.get(list.size() - 1);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
    
    public String toString() {
        String str = "";
        str += "------------------\n";
        for(int i = list.size() - 1; i >= 0; i--){
            str += list.get(i).toString();
            str += "\n";
        }
        str += "------------------";
        return str;
    }
}
