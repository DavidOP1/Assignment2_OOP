package api;
import java.util.*;
    public class Pair<X, Y> implements Comparable<Pair> {
        public final X id;
        public final Y weight;
        public Pair(X id, Y weight) {
            this.id = id;
            this.weight = weight;
        }
        @Override
        public int compareTo(Pair o) {// Dont forget to implement
            if(((double)this.weight-(double)o.weight)>0) return 1;
            else if(((double)this.weight-(double)o.weight)<0) return -1;
            else return 0;
        }
    }
