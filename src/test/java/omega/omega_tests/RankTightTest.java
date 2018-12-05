package omega.omega_tests;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.rank.ComplementRankTight;
import roll.words.Alphabet;

public class RankTightTest {
	
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
//        Options.mTightRank = true;
//        Options.mMinusOne = true;
////        Options.mTurnwise = true;
//        Options.mReduceOutdegree = true;
//        Options.mLazyS = true;
        ComplementCommandOptions options = new ComplementCommandOptions();
        options.tight = true;
        ComplementRankTight complement = new ComplementRankTight(options, buchi);
        complement.explore();
        System.out.println(complement.toDot());
        System.out.println(complement.toBA());
//        System.out.println((new Remove(complement)).getResult().getStateSize());
        
//        ComplementNcsb complementNcsb = new ComplementNcsb(buchi);
//        complementNcsb.explore();
//        System.out.println(complementNcsb.toDot());
//        System.out.println(complementNcsb.toBA());
        
    }

}
