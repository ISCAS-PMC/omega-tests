package omega.omega_tests;

import omega.util.UtilNumbers;
import omega.util.sets.UtilUnmodifiedISet;
import roll.util.sets.ISet;
import roll.util.sets.UtilISet;

public class UnmodifiedISet {
	
	public static void main(String[] args) {
		ISet set = UtilISet.newISet();
		for(int i = 0; i < 100; i ++) {
			if(UtilNumbers.isEven(i)) {
				set.set(i);
			}
		}
		set.and(UtilISet.newISet());
		set.or(UtilISet.newISet());
		set.set(1);
		System.out.println(set);
		ISet unmodified = UtilUnmodifiedISet.getUnmodifiedISet(set);
//		unmodified.and(UtilISet.newISet());
//		unmodified.or(UtilISet.newISet());
//		unmodified.set(3);
//		unmodified.clear(3);
//		unmodified.clear();
//		unmodified.andNot(UtilISet.newISet());
	}

}
