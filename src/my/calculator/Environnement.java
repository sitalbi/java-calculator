package my.calculator;

import java.util.HashMap;

/**
 *
 * @author Simon Talbi
 */
public class Environnement {
    public HashMap<String, Integer> table = new HashMap<>();
    
    public Environnement() {
    }
    
    public Environnement(HashMap<String, Integer> table) {
        this.table = table;
    }
    
    public HashMap<String, Integer> getTable() {
        return table;
    } 
    
    public void affect(String name, int value) {
        table.put(name, value);
    }
    
    public int value(String name) {
        return table.get(name);
    }
    
}
