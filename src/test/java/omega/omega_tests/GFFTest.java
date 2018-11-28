package omega.omega_tests;

import omega.automata.NBA;
import omega.util.parser.gff.GFFFileParser;

public class GFFTest {
    
    public static void main(String[] args) {
        GFFFileParser parser = new GFFFileParser();
        parser.parse("/home/liyong/tools/GOAL-20151018/new-s-20-r-1.00-f-0.10--1-of-100.gff");
        NBA buchi = parser.get();
        System.out.println(buchi.toDot());
        System.out.println(buchi.toGFF());
        parser.print(buchi, System.out);
    }

}
