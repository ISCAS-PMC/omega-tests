package omega.omega_tests;

import omega.automata.NBA;
import omega.main.options.CmdLineParser;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.slice.ComplementSliceBreakpoint;
import roll.words.Alphabet;

public class SlicebpTest {
	
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
        String[] arg = { "complement", "a.hoa", "-algo=SLICEBP" };
        CmdLineParser parser = new CmdLineParser(args);
        ComplementCommandOptions options = (ComplementCommandOptions) parser.getCommand();
        ComplementSliceBreakpoint complement = new ComplementSliceBreakpoint(options, buchi);
        complement.explore();
        System.out.println(complement.toDot());
        
        System.out.println(complement.toBA());
        
//        Options.mMergeAdjacentSets = true;
//        Options.mMergeAdjacentColoredSets = true;
        complement = new ComplementSliceBreakpoint(options, buchi);
        complement.explore();
        System.out.println(complement.toDot());
        
        System.out.println(complement.toBA());
        
        buchi = new NBA(alphabet);
        
        buchi.addState();
        buchi.addState();
        
        buchi.getState(0).addSuccessor(1, 0);
        buchi.getState(0).addSuccessor(1, 1);
        
        buchi.getState(1).addSuccessor(1, 0);
        
        buchi.setFinal(1);
        buchi.setInitial(0);
        
        System.out.println(buchi.toDot());
        complement = new ComplementSliceBreakpoint(options, buchi);
        complement.explore();
        System.out.println(complement.toDot());

    }

}
