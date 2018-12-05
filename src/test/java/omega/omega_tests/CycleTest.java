package omega.omega_tests;

import java.util.List;

import omega.automata.NBA;
import omega.util.cycles.ElementaryCycles;
import omega.util.parser.gff.GFFFileParser;

public class CycleTest {
	
	public static void main(String[] args) {
		GFFFileParser parser = new GFFFileParser();
        parser.parse("/home/liyong/git/omega-tests/examples/A3.gff");
        NBA buchi = parser.get();
        ElementaryCycles cycles = new ElementaryCycles(buchi);
        cycles.findElementaryCycles();
        for(List<Integer> list : cycles.getElementaryCycles()) {
        	System.out.println(list);
        }
        System.out.println(buchi.toDot());
        
        NBA nba = new NBA(buchi.getAlphabet());
        nba.addState();
        nba.addState();
        nba.addState();
        
        nba.getState(0).addSuccessor(0, 1);
        nba.getState(0).addSuccessor(0, 2);
        nba.getState(1).addSuccessor(0, 0);
        nba.getState(2).addSuccessor(0, 0);
        nba.getState(2).addSuccessor(0, 1);
        
        cycles = new ElementaryCycles(nba);
        cycles.findElementaryCycles();
        /**
         * 0 1
			0 2
			1 0
			2 0
			2 1
         * */
        for(List<Integer> list : cycles.getElementaryCycles()) {
        	System.out.println(list);
        }
        System.out.println(nba.toDot());
	}

}
