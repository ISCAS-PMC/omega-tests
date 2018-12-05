package omega.omega_tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import omega.automata.NBA;
import omega.automata.State;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.rank.ComplementRankKV;
import roll.words.Alphabet;

public class SliceKVTest {
	
    public static void main(String[] args) {
        
        Alphabet alphabet = new Alphabet();
        alphabet.addLetter('a');
        alphabet.addLetter('b');
        NBA buchi = new NBA(alphabet);
        State aState = buchi.addState();
        State bState = buchi.addState();
        
        aState.addSuccessor(0, aState.getId()); 
        aState.addSuccessor(1, aState.getId());     
        aState.addSuccessor(1, bState.getId());     
        bState.addSuccessor(1, bState.getId());
        
        buchi.setFinal(bState.getId());
        buchi.setInitial(aState);
        
        System.out.println(buchi.toDot());
        ComplementCommandOptions options = new ComplementCommandOptions();

        ComplementRankKV complement = new ComplementRankKV(options, buchi);
//        new Explore(complement);
//        System.out.println(complement.toDot());
//        Remove rm = new Remove(complement);
//        System.out.println(rm.getResult().toDot());
//        System.out.println(rm.getResult().toBA());
        
        complement = new ComplementRankKV(options, buchi);
//        Options.mLazyS = true;
//        Options.mMinusOne = true;
        complement.explore();
        System.out.println(complement.toDot());
        System.out.println(complement.toBA());
        
        // now we use the complement as input
        buchi = new NBA(alphabet);
        aState = buchi.addState();
        bState = buchi.addState();
        
        aState.addSuccessor(1, aState.getId()); 
        aState.addSuccessor(0, bState.getId());     
        bState.addSuccessor(0, bState.getId());     
        bState.addSuccessor(1, aState.getId());
        
        buchi.setFinal(bState.getId());
        buchi.setInitial(aState);
        
        complement = new ComplementRankKV(options, buchi);
//        Options.mLazyS = true;
        complement.explore();
        System.out.println(complement.toDot());
        System.out.println(complement.toBA());
        
//        complement = new ComplementNBA(buchi);
//        Options.mLazyS = false;
//        new Explore(complement);
//        System.out.println(complement.toDot());
//        System.out.println(complement.toBA());
        buchi = getBuchi(5);
        PrintStream print;
        try {
            print = new PrintStream(new FileOutputStream("/home/liyong/Downloads/op.ba"));
            print.print(buchi.toBA());
            print.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        complement = new ComplementRankKV(options, buchi);
//        Options.mLazyS = true;
//        Options.mMinusOne = true;
        complement.explore();
        
        try {
            print = new PrintStream(new FileOutputStream("/home/liyong/Downloads/op2.ba"));
            print.print(complement.toBA());
            print.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        complement = new ComplementRankKV(options, buchi);
//        Options.mLazyS = false;
//        Options.mMinusOne = true;
        complement.explore();
        try {
            print = new PrintStream(new FileOutputStream("/home/liyong/Downloads/op1.ba"));
            print.print(complement.toBA());
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    private static NBA getBuchi(int n) {
        Alphabet alphabet = new Alphabet();
        alphabet.addLetter('a');
        alphabet.addLetter('b');
        NBA buchi = new NBA(alphabet);
        State[] states = new State[n + 1];
        for(int i = 1; i <= n; i ++) {
            states[i] = buchi.addState();
        }
        final int[] ap = new int[2];
        ap[0] = 0;
        ap[1] = 1;
        
        buchi.setInitial(states[1]);
        // accepting
        for(int i = 2; i <= n; i += 2) {
            buchi.setFinal(states[i].getId());
        }
        
        // transition
        for(int j = 1; j <= n - 2; j ++) {
            states[j].addSuccessor(ap[0], states[j+1].getId());
            states[j].addSuccessor(ap[1], states[j+1].getId());
        }
        states[1].addSuccessor(ap[0], states[1].getId());
        states[1].addSuccessor(ap[1], states[1].getId());
        states[n].addSuccessor(ap[0], states[n].getId());
        states[n].addSuccessor(ap[1], states[n].getId());
        states[n-1].addSuccessor(ap[1], states[n-1].getId());
        states[n-1].addSuccessor(ap[0], states[n].getId());
        
        return buchi;
        

    }


}
