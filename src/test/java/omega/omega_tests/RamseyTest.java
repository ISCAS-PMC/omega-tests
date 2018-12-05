package omega.omega_tests;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.ramsey.ComplementRamsey;
import roll.words.Alphabet;

public class RamseyTest {
	
    public static void main(String[] args) {
        
        Alphabet alphabet = new Alphabet();
        alphabet.addLetter('a');
        alphabet.addLetter('b');
        NBA buchi = new NBA(alphabet);
        buchi.addState();
        buchi.addState();
        
        buchi.getState(0).addSuccessor(0, 0);
        buchi.getState(0).addSuccessor(1, 0);
        buchi.getState(0).addSuccessor(1, 1);
        
        buchi.getState(1).addSuccessor(0, 0);
        buchi.getState(1).addSuccessor(0, 1);
        
        buchi.setFinal(1);
        buchi.setInitial(0);
        
        ComplementCommandOptions options = new ComplementCommandOptions();
        ComplementRamsey cr = new ComplementRamsey(options, buchi);
        cr.explore();
//        IBuchi crr = (new Remove(cr)).getResult();
        System.out.println(cr.toDot());
//        System.out.println(crr.toBA());
//        
//        ComplementTuple ct = new ComplementTuple(buchi);
//        new Explore(ct);
//        System.out.println(ct.toDot());
//        System.out.println(ct.toBA());
        
    }


}
