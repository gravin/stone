package chap11;
import java.util.HashMap;

public class Symbols {
    public static class Location {
        public int nest, index;
        public Location(int nest, int index) {
            this.nest = nest;
            this.index = index;
        }
    }
    protected Symbols outer;
    protected HashMap<String,Integer> table;
    public Symbols() { this(null); }
    public Symbols(Symbols outer) {
        this.outer = outer;
        this.table = new HashMap<String,Integer>();
    }
    public int size() { return table.size(); }
    public void append(Symbols s) { table.putAll(s.table); }
    public Integer find(String key) { return table.get(key); }
    public Location get(String key) { return get(key, 0); }
    public Location get(String key, int nest) {
        Integer index = table.get(key);
        if (index == null)
            if (outer == null)
                return null;
            else
                return outer.get(key, nest + 1);
        else
            return new Location(nest, index.intValue());
    }
    public int putNew(String key) {
        Integer i = find(key);
        if (i == null)
            return add(key);
        else
            return i;
    }
    public Location put(String key) {
        Location loc = get(key, 0);
        if (loc == null)
            return new Location(0, add(key));
        else
            return loc;
    }
    protected int add(String key) {
        int i = table.size();
        table.put(key, i);
        return i;
    }
}
