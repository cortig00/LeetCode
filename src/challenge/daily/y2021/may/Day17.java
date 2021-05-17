package challenge.daily.y2021.may;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * UNSOLVED - :(
 * 
 * Given a list of words, each word consists of English lowercase letters. Let's
 * say word1 is a predecessor of word2 if and only if we can add exactly one
 * letter anywhere in word1 to make it equal to word2. For example, "abc" is a
 * predecessor of "abac". A word chain is a sequence of words [word_1, word_2,
 * ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is
 * a predecessor of word_3, and so on. Return the longest possible length of a
 * word chain with words chosen from the given list of words.
 * 
 * Example 1:
 * 
 * Input: words = ["a","b","ba","bca","bda","bdca"] Output: 4 Explanation: One
 * of the longest word chain is "a","ba","bda","bdca".
 * 
 * Example 2:
 * 
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"] Output: 5
 * 
 * 
 * @author cesar
 *
 */
public class Day17 {

	public static int longestStrChain(String[] words) {
		int solution = 0;
		int counter = 0;

		if (words.length == 1) {
			return 1;
		}

		List<String> list = Arrays.asList(words);

		Collections.sort(list, new Comparator<String>() {	//Ordered in desc order (from great to lower)

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.length()-o1.length();
			}
		});



		for (String word1 : list) {
			for (String word2 : list) {
				if (isPredecessor(word1, word2)) {
					counter++;
					break;
				}
			}
		}





		return solution;
	}

	private static boolean isPredecessor(String word1, String word2) {

		if (word1.length() == word2.length()+1) {
			
		}

		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"a","b","ba","bca","bda","bdca"};
		System.out.println(longestStrChain(words));
	}

}
