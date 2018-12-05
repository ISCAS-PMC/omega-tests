package omega.omega_tests;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.rank.LevelRanking;
import omega.operations.complement.rank.LevelRankingConstraint;
import omega.operations.complement.rank.LevelRankingGenerator;
import omega.operations.complement.rank.UtilRank;
import roll.words.Alphabet;

public class RankGenerator {
	
    public static void main(String[] args) {
        
        Alphabet alphabet = new Alphabet();
        alphabet.addLetter('a');
        alphabet.addLetter('b');
        NBA buchi = new NBA(alphabet);
        
        buchi.addState();
        buchi.addState();
        buchi.addState();
        
        buchi.setInitial(0);
        buchi.setFinal(1);
        
        ComplementCommandOptions options = new ComplementCommandOptions();
        
        LevelRankingGenerator generator = new LevelRankingGenerator(options, buchi);
        
        LevelRankingConstraint constraint = new LevelRankingConstraint(options.turnwise);
        constraint.addConstraint(0, 5, false, false);
        constraint.addConstraint(1, 5, true, false);
        constraint.addConstraint(2, 5, false, false);
        
        Collection<LevelRanking> hhh = generator.generateLevelRankings(constraint);
        System.out.println(hhh);
        Set<LevelRanking> set2 = generator.generateLevelRankings(constraint, false);
        System.out.println(generator.generateLevelRankings(constraint, false));
        System.out.println(set2.size() == hhh.size());
        for(LevelRanking s : hhh) {
            if(!set2.contains(s)) System.out.println("false");
        }
        
        final int n = 3;
        int[] array = new int[n];
        for(int i = 0; i < n; i ++) {
            array[i] = i;
        }
        LinkedList<int[]> perm = UtilRank.permute(array);
        for(int[] arr : perm) {
            System.out.print("[ ");
            for(int i = 0; i < arr.length; i ++) {
                System.out.print(arr[i] + ",");
            }
            System.out.println("] ");
            
        }
       
    }

}
