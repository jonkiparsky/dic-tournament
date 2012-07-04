package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A combination is a set of elements from a group of elements where the order
 * of the elements in the set does not matter. There can be replacement (Meaning
 * every time you select an element, you put it back before choosing the next
 * element). This is known as repetition, because values will repeat within each
 * combination.
 * 
 * <p>
 * The formula for determining the number of combinations without repetition is:
 * <br>
 * Let n = size of the initial set <br>
 * Let k = size of the subsets to generate <br>
 * <code>n! / k!(n-k)!</code>
 * 
 * <p>
 * The formula for determining the number of combinations with repetition is: <br>
 * Let n = size of the initial set <br>
 * Let k = size of the subsets to generate <br>
 * <code>(n + k - 1)! / k!(n-1)!</code>
 */
public class Combinations {
	static long factorial(int x) {
		long result = 1;
		for (int i = 2; i <= x; i++) {
			result *= i;
		}
		return result;
	}

	/**
	 * Calculates the number of k-length combinations that any given set of n
	 * length. Change n to (n + k - 1) for combinations with repetition.
	 */
	static long numberOfCombinations(int n, int k) {
		// Could potentially be optimized by first factoring
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	// Used for testing. Change the original list, k, or whether or not you want
	// repetition, the rest falls in place.
	public static void main(String[] args) {
		ArrayList<Object> messages = new ArrayList<Object>();

		messages.add("1");
		messages.add("2");
		messages.add("3");
		messages.add("4");
		messages.add("5");

		int n = messages.size();
		int k = 3;
		boolean repetition = false;

		List<List<Object>> combos = generateCombinations(messages, k,
				repetition);

		System.out.println(combos);

		int realN = repetition ? (n + k - 1) : n;

		if (numberOfCombinations(realN, k) == combos.size()) {
			System.out.println("This is the correct number of combinations.");
		}
	}

	/**
	 * Generates all combinations of k-size subsets of <code>array</code>. (In
	 * combinations, order does not matter)
	 * 
	 * @param array
	 *            The original set from which to generate combinations.
	 * @param k
	 *            The size of the subsets the caller needs to generate.
	 * @param repetition
	 *            Whether or not elements are placed back after one is chosen.
	 *            In other words, whether an element can appear more than once
	 *            in a single combination.
	 * @return All combinations of k-size subsets of <code>array</code>.
	 */
	public static <T> List<List<T>> generateCombinations(List<T> array, int k,
			boolean repetition) {
		return generateCombinations_recursive(array, k, repetition, false);
	}

	private static <T> List<List<T>> generateCombinations_recursive(
			List<T> array, int k, boolean repetition, boolean recursiveCall) {

		ArrayList<List<T>> combinations = new ArrayList<List<T>>();
		final int n = array.size();

		// You're asking for combination sizes larger than the original list
		if (n < k) {
			// however, we need this in recursive calls because the final
			// elements don't have much to choose from.
			if (!recursiveCall) {
				throw new IllegalArgumentException();
			}
		}

		// base case
		if (k == 1) {
			for (T element : array) {
				ArrayList<T> singleElement = new ArrayList<T>();
				singleElement.add(element);
				combinations.add(singleElement);
			}
			return combinations;
		}

		// recurse
		for (int i = 0; i < n; i++) {
			ArrayList<T> subset = new ArrayList<T>();
			final int include = repetition ? 0 : 1;
			for (int j = i + include; j < n; j++) {
				subset.add(array.get(j));
			}

			List<List<T>> subsetCombinations = null;

			subsetCombinations = generateCombinations_recursive(subset, k - 1,
					repetition, true);

			for (List<T> subsetCombo : subsetCombinations) {
				ArrayList<T> combo = new ArrayList<T>();
				combo.add(array.get(i));
				combo.addAll(subsetCombo);
				combinations.add(combo);
			}
		}

		return combinations;
	}
}