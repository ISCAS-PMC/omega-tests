package omega.omega_tests;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.ncsb.ComplementNcsb;
import roll.words.Alphabet;

public class NcsbTest {
	
	   public static void main(String[] args) {
	        Alphabet alphabet = new Alphabet();
	        alphabet.addLetter('a');
	        alphabet.addLetter('b');
	        NBA buchi = new NBA(alphabet);
	        
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        
	        buchi.getState(0).addSuccessor(0, 0);
	        buchi.getState(0).addSuccessor(1, 0);
	        buchi.getState(0).addSuccessor(0, 1);
	        buchi.getState(0).addSuccessor(1, 1);
	        
	        buchi.getState(1).addSuccessor(0, 2);
	        buchi.getState(1).addSuccessor(1, 1);
	        
	        buchi.getState(2).addSuccessor(0, 2);
	        buchi.getState(2).addSuccessor(1, 2);
	        
	        buchi.setFinal(1);
	        buchi.setInitial(0);
	        
	        System.out.println(buchi.toDot());
//	        System.out.println(buchi.getDetStatesAfterFinals());
//	        Options.mEnhancedSliceGuess = true;
	        ComplementCommandOptions options = new ComplementCommandOptions();
	        ComplementNcsb complement = new ComplementNcsb(options, buchi);
	        complement.explore();;
	        System.out.println(complement.toDot());
	        
	        System.out.println(complement.toBA());
	        
//	        IBuchi result = (new Remove(complement)).getResult();
//	        
//	        System.out.println(result.toDot());
//	        
//	        System.out.println(result.toBA());
	        
	        
	        buchi = new NBA(alphabet);
	        
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        
	        buchi.getState(0).addSuccessor(0, 1);
	        buchi.getState(0).addSuccessor(1, 2);
	        buchi.getState(1).addSuccessor(0, 1);
	        buchi.getState(1).addSuccessor(1, 1);
	        
	        buchi.getState(2).addSuccessor(0, 2);
	        buchi.getState(2).addSuccessor(1, 2);
	        
	        buchi.setFinal(1);
	        buchi.setInitial(0);
	        
	        complement = new ComplementNcsb(options, buchi);
	        complement.explore();
	        System.out.println(complement.toDot());
	        
	        buchi = new NBA(alphabet);
	        
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        buchi.addState();
	        
	        int q0 = 0;
	        int q0a = 1;
	        int q0b = 2;
	        
	        int q1 = 3;
	        int q1a = 4;
	        int q1b = 5;
	        
	        buchi.getState(q0).addSuccessor(0, q0a);
	        buchi.getState(q0).addSuccessor(1, q0b);
	        buchi.getState(q0).addSuccessor(1, q1);
	        
	        buchi.getState(q1).addSuccessor(0, q1a);
	        buchi.getState(q1).addSuccessor(1, q1b);
	        buchi.getState(q1).addSuccessor(0, q0);
	        
	        buchi.getState(q0a).addSuccessor(0, q0a);
	        buchi.getState(q0a).addSuccessor(1, q0a);
	        
	        buchi.getState(q0b).addSuccessor(0, q0b);
	        buchi.getState(q0b).addSuccessor(1, q0b);
	        
	        buchi.getState(q1a).addSuccessor(0, q1a);
	        buchi.getState(q1a).addSuccessor(1, q1a);
	        
	        buchi.getState(q1b).addSuccessor(0, q1b);
	        buchi.getState(q1b).addSuccessor(1, q1b);
	        
	        
	        
	        buchi.setInitial(q0);
	        buchi.setFinal(q0a);
	        buchi.setFinal(q1b);
	        
	        System.out.println(buchi.toDot());
	        
	        complement = new ComplementNcsb(options, buchi);
	        complement.setDetStates(q0b);
	        complement.setDetStates(q1a);
	        
	        complement.explore();
	        System.out.println(complement.toDot());
	        
//	        ComplementNsbc complementNsbc = new ComplementNsbc(buchi);
//	        new Explore(complementNsbc);
//	        System.out.println(complementNsbc.toDot());
//	        
//	        System.out.println(complement.toBA());
//	        
//	        System.out.println(complementNsbc.toBA());

	    }


}
