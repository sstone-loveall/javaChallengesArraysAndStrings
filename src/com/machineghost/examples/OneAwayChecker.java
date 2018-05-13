package com.machineghost.examples;

/***
 * There are three types of edits that can be performed on strings: 
 * insert a char, remove a char, or replace a char. Given two strings,
 * write a function to check if they are one or zero edits away.
 * 
 * Assumptions: Input strings will be ASCII encoded, all lower case.
 *
 */
public class OneAwayChecker {
	
	/***
	 * Determine if one string is one char away from another
	 * @param firstString: the first string
	 * @param secondString: the string to compare to
	 * @return True if the string is one away by means of insertion, deletion, or replacement of one or zero chars.
	 */
	public Boolean isOneAway(String firstString, String secondString) {
		
		// if both are null, return true
		if (firstString == null && secondString == null) {
			return true;
		}
		else if (firstString == null || secondString == null) {
			return false;
		}
		
		// if strings are identical, return true
		if (firstString.equals(secondString)) {
			return true;
		}
		
		// if one string is empty and the other isn't, but it is one char in length, return true
		if ((firstString.isEmpty() && secondString.length() == 1)
				|| (secondString.isEmpty() && firstString.length() == 1)) {
			return true;
		}
		
		// if the strings are one-away, return true
		if (insertions(firstString, secondString)) {
			return true;
		}
		else if (deletions(firstString, secondString)) {
			return true;
		}
		else if (replacements(firstString, secondString)) {
			return true;
		}
		else {
			// all else, return false
			return false;
		}
	}
	
	/***
	 * Determine if two strings are one away if the second string has one more char than the first
	 * @param firstString: the first string
	 * @param secondString: the string to compare to
	 * @return True if one-away
	 */
	public Boolean insertions(String firstString, String secondString) {
		// if the second string is one more char than the first
		// and all of the first string's chars are found in the second
		// then we have a match
		Boolean isOneAway = false;
		if (secondString.length() - firstString.length() == 1) {
			if (secondString.contains(firstString)) {
				isOneAway = true;
			}
		}
		return isOneAway;
	}
	
	/***
	 * Determine if two strings are one away if the second string has one less char than the first
	 * @param firstString: the first string
	 * @param secondString: the string to compare to
	 * @return True if one-away
	 */
	public Boolean deletions(String firstString, String secondString) {
		// if the first string is one more char than the second
		// and all the second string's chars are found in the first
		// then we have a match
		Boolean isOneAway = false;
		if (firstString.length() - secondString.length() == 1) {
			if (firstString.contains(secondString)) {
				isOneAway = true;
			}
		}
		return isOneAway;
	}
	
	/***
	 * Determine if two strings are one away if the first and second strings has just one char different in each
	 * @param firstString: the first string
	 * @param secondString: the string to compare to
	 * @return True if one-away
	 */
	public Boolean replacements(String firstString, String secondString) {
		// if the first and second strings are the same length
		// and they have all the same letters in place except for one
		// then we have a match
		Boolean isOneAway = false;
		if (firstString.length() == secondString.length()) {
			int countNoMatch = 0;
			for (int i = 0; i < firstString.length(); i++) {
				if (firstString.charAt(i) != secondString.charAt(i)) {
					countNoMatch++;
					if (countNoMatch > 1) {
						isOneAway = false;
						break;
					}
				}
				else {
					isOneAway = true;
				}
			}
		}
		return isOneAway;
	}
	
	public static void main(String[] args) {
		Boolean isOneAway = false;
		
		// test 1: null and empty inputs
		OneAwayChecker checker1 = new OneAwayChecker();
		isOneAway = checker1.isOneAway(null, null);
		System.out.println("Test 1.1: null strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker1.isOneAway("", "");
		System.out.println("Test 1.2: empty strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker1.isOneAway("notnull", null);
		System.out.println("Test 1.3: one null and one non-null string should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker1.isOneAway(null, "notnull");
		System.out.println("Test 1.4: one null and one non-null string should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		// test 2: identical inputs
		OneAwayChecker checker2 = new OneAwayChecker();
		isOneAway = checker2.isOneAway("abcdef1", "abcdef1");
		System.out.println("Test 2.1: identical strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		// test 3: non-matching inputs
		OneAwayChecker checker3 = new OneAwayChecker();
		isOneAway = checker3.isOneAway("12345", "abcde");
		System.out.println("Test 3.1: non-matching strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker3.isOneAway("12cde", "abcde");
		System.out.println("Test 3.2: non-matching strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));

		isOneAway = checker3.isOneAway("", null);
		System.out.println("Test 3.3: non-matching strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		// test 4: one-away inputs
		OneAwayChecker checker4 = new OneAwayChecker();
		isOneAway = checker4.isOneAway("abc", "abc9");
		System.out.println("Test 4.1: one-away strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker4.isOneAway("abc", "ab");
		System.out.println("Test 4.2: one-away strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker4.isOneAway("abcr", "abc9");
		System.out.println("Test 4.3: one-away strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker4.isOneAway("", "a");
		System.out.println("Test 4.4: one-away strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker4.isOneAway("a", "");
		System.out.println("Test 4.5: one-away strings should return true. Result: " + (isOneAway ? "test passed" : "test failed"));
		
		// test 5: two-away inputs
		OneAwayChecker checker5 = new OneAwayChecker();
		isOneAway = checker5.isOneAway("ab12c", "abc");
		System.out.println("Test 5.1: two-away strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker5.isOneAway("abcd", "ab");
		System.out.println("Test 5.2: two-away strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker5.isOneAway("abc3r", "abc9h");
		System.out.println("Test 5.3: two-away strings should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		// test 6: permutations
		OneAwayChecker checker6 = new OneAwayChecker();
		isOneAway = checker6.isOneAway("taco", "ocat");
		System.out.println("Test 6.1: permutations should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
		
		isOneAway = checker6.isOneAway("palem", "lapem");
		System.out.println("Test 6.2: permutations should return false. Result: " + (!isOneAway ? "test passed" : "test failed"));
	}
}
