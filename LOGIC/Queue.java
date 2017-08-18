package LOGIC;
/**
 *
 * @author
 */
import java.util.ArrayList;
public class Queue {
    
    private ArrayList<Object> list;
    
    public Queue(){
        this.list = new ArrayList<>();
    }
    
    public void enqueue(Object o){
        list.add(o);
    }
    
    public Object dequeue(){
        Object o = null;
        if(list.isEmpty())
            return null;
        else{
            o = list.get(0);
            list.remove(o);
            return o;
        }
    }
    
    public Object peek(){
        Object o = null;
        if(list.isEmpty())
            return null;
        else{
            o = list.get(list.size() - 1);
            return o;
        }
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public int size(){
        return list.size();
    }
    
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < list.size(); i++){
            str += list.get(i).toString() + " ";
        }
        return str;
    }
}
