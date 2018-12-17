package omega.test.nbagen;

import java.util.Random;

import omega.automata.NBA;
import omega.automata.State;
import omega.main.OmegaRuntimeException;
import roll.words.Alphabet;

public class NBAGen {
	
    public static NBA getRandomNBA(int numState, int numLetter) {

        if (numLetter > 5) {
            throw new OmegaRuntimeException("only allow a,b,c,d,e letters in generated NBA");
        }
        Alphabet alphabet = new Alphabet();
        char[] letters = { 'a', 'b', 'c', 'd', 'e' };

        for (int i = 0; i < numLetter && i < letters.length; i++) {
            alphabet.addLetter(letters[i]);
        }

        NBA result = new NBA(alphabet);
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < numState; i++) {
            result.addState();
        }

        result.setInitial(0);

        // final states
        int numF = r.nextInt(numState - 1);
        numF = numF > 0 ? numF : 1;
        boolean acc = false;
        for (int n = 0; n < numF; n++) {
            int f = r.nextInt(numF);
            if (f != 0) {
                acc = true;
                result.setFinal(f);
            }
        }

        if (!acc) {
            result.setFinal(numF);
        }

        int numTrans = r.nextInt(numState * numLetter);

        // transitions
        for (int k = 0; k < numLetter && k < 5; k++) {
            for (int n = 0; n < numTrans; n++) {
                int i = r.nextInt(numState);
                int j = r.nextInt(numState);
                result.getState(i).addSuccessor(k, j);
            }
        }

        return result;
    }
        
    public static NBA getRandomLDBA(
            final int numState,
            final int numDetState,
            final int numLetter, 
            final int numFinals, 
            final double density) {

        if (numLetter > 5) {
            throw new UnsupportedOperationException("only allow a,b,c,d,e letters in generated LDBA");
        }
        
        Alphabet alphabet = new Alphabet();
        char[] letters = { 'a', 'b', 'c', 'd', 'e' };

        for (int i = 0; i < numLetter && i < letters.length; i++) {
            alphabet.addLetter(letters[i]);
        }

        NBA result = new NBA(alphabet);
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < numState; i++) {
            result.addState();
        }

        final int numNondetState = numState - numDetState;
        final int numTrans = (int) (numState * density);

        result.setInitial(0);
        
        // the deterministic states are all in the range 
        // [ numNondetState, numState)
        // so the accepting states should be all in this range 
        for (int i = 0; i < numFinals; i++) {
            result.setFinal(numNondetState + r.nextInt(numDetState));
        }
        
        for (int i = 0; i < numTrans; i++) {
            final State source = result.getState(r.nextInt(numState));
            final int action = r.nextInt(numLetter);
            if (source.getId() >= numNondetState) {
                // source is a deterministic state, so it can't enable again the same letter 
                if (source.getEnabledLetters().contains(action)) {
                    // the transition is not created, so we have to try again
                    i--;
                } else {
                    // the deterministic states are all in the range 
                    // [ numNondetState, numState)
                    // so the target state should be all in this range 
                    source.addSuccessor(action, numNondetState + r.nextInt(numDetState));
                }
            } else {
                source.addSuccessor(action, r.nextInt(numState));
            }
        }
    
        return result;
    }

}
