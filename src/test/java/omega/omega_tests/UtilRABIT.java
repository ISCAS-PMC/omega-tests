package omega.omega_tests;

import automata.FAState;
import automata.FiniteAutomaton;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import mainfiles.RABIT;
import omega.automata.NBA;
import omega.automata.State;
import omega.util.PairXX;
import roll.words.Alphabet;
import roll.words.Word;

public class UtilRABIT {
	private static FAState getOrAddState(FiniteAutomaton fa, TIntObjectMap<FAState> map, int s) {
		if (map.containsKey(s)) {
			return map.get(s);
		}
		FAState st = fa.createState();
		map.put(s, st);
		return st;
	}

	public static FiniteAutomaton toRABITNBA(NBA nba) {
		FiniteAutomaton rabitAut = new FiniteAutomaton();
		TIntObjectMap<FAState> map = new TIntObjectHashMap<>();
		Alphabet alphabet = nba.getAlphabet();
		for (int s = 0; s < nba.getStateSize(); s++) {
			FAState faState = getOrAddState(rabitAut, map, s);
			State state = nba.getState(s);
			for(int letter : state.getEnabledLetters()) {
				for (int succ : state.getSuccessors(letter)) {
					FAState faSucc = getOrAddState(rabitAut, map, succ);
					rabitAut.addTransition(faState, faSucc, alphabet.getLetter(letter) + "");
				}
			}
			if (nba.isFinal(s)) {
				rabitAut.F.add(faState);
			}
		}
		FAState init = map.get(nba.getInitialState());
		rabitAut.setInitialState(init);
		return rabitAut;
	}

	public static PairXX<Word> isIncluded(Alphabet alphabet, FiniteAutomaton A, FiniteAutomaton B) {
		boolean inclusion = RABIT.isIncluded(A, B);
		if (inclusion)
			return null;
		String prefixStr = RABIT.getPrefix();
		String suffixStr = RABIT.getSuffix();
		Word prefix = alphabet.getWordFromString(prefixStr);
		Word suffix = null;
		if (!suffixStr.equals("")) {
			suffix = alphabet.getWordFromString(suffixStr);
		} else {
			suffix = alphabet.getEmptyWord();
		}
		return new PairXX<>(prefix, suffix);
	}
}
