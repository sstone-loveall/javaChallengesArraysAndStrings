package com.machineghost.examples;

import java.util.Arrays;

/***
 * Implement an algorithm to determine if a string has all unique chars.
 *
 */
public class UniqueCharsChecker {
	
	private Boolean isUnique;
	
	public UniqueCharsChecker() { }
	
	public Boolean getIsUnique() {
		return isUnique;
	}
	
	/***
	 * Check if a string is composed of all unique characters.
	 * This approach first sorts the chars. Then it compares each char in order to its neighbor for uniqueness.
	 * This approach is effective and somewhat efficient: O(n log (n))
	 * @param stringToCheck: the string to check for uniqueness
	 * @return true if all chars are unique
	 */
	public Boolean charsAreAllUniqueSortedApproach(String stringToCheck) {
		isUnique = true;
		if (stringToCheck == null || stringToCheck.isEmpty()) {
			return isUnique;
		}
		
		char[] chars = stringToCheck.toCharArray();
		Arrays.sort(chars);
		for (int i = 0; i < chars.length; i++) {
			if (i > 0) {
				if (chars[i] == chars[i-1]) {
					isUnique = false;
					break;
				}
			}
		}
		return isUnique;
	}
	
	/***
	 * Check if a string is composed of all unique characters.
	 * Loop through each char in the string, check if the char exists again.
	 * This approach is effective, but not efficient: O(n2)
	 * @param stringToCheck: the string to check for uniqueness
	 * @return true if all chars are unique
	 */
	public Boolean charsAreAllUniqueNaiveApproach(String stringToCheck) {
		isUnique = true;
		if (stringToCheck == null || stringToCheck.isEmpty()) {
			return isUnique;
		}
		
		char[] chars = stringToCheck.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (stringToCheck.indexOf(chars[i], i + 1) > -1) {
				isUnique = false;
				break;
			}
		}
		return isUnique;
	}
	
	public static void main(String[] args) {
		
		// test 1: null string
		UniqueCharsChecker checker1 = new UniqueCharsChecker();
		checker1.charsAreAllUniqueNaiveApproach(null);
		System.out.println("Test 1.1: null string should return true. Result: " + (checker1.getIsUnique() ? "test passed" : "test failed"));
		
		checker1.charsAreAllUniqueSortedApproach(null);
		System.out.println("Test 1.2: null string should return true. Result: " + (checker1.getIsUnique() ? "test passed" : "test failed"));
		
		// test 2: empty string
		UniqueCharsChecker checker2 = new UniqueCharsChecker();
		checker2.charsAreAllUniqueNaiveApproach("");
		System.out.println("Test 2.1: empty string should return true. Result: " + (checker2.getIsUnique() ? "test passed" : "test failed"));
		
		checker2.charsAreAllUniqueSortedApproach("");
		System.out.println("Test 2.2: empty string should return true. Result: " + (checker2.getIsUnique() ? "test passed" : "test failed"));
		
		// test 3: all unique chars
		UniqueCharsChecker checker3 = new UniqueCharsChecker();
		checker3.charsAreAllUniqueNaiveApproach("abc123def456.!p");
		System.out.println("Test 3.1: all-unique chars should return true. Result: " + (checker3.getIsUnique() ? "test passed" : "test failed"));
		
		checker3.charsAreAllUniqueSortedApproach("abc123def456.!p");
		System.out.println("Test 3.2: all-unique chars should return true. Result: " + (checker3.getIsUnique() ? "test passed" : "test failed"));
		
		// test 4: non-unique chars
		UniqueCharsChecker checker4 = new UniqueCharsChecker();
		checker4.charsAreAllUniqueNaiveApproach("abc123abc");
		System.out.println("Test 4.1: non-unqiue chars should return false. Result: " + (!checker4.getIsUnique() ? "test passed" : "test failed"));
		
		checker4.charsAreAllUniqueSortedApproach("abc123abc");
		System.out.println("Test 4.2: non-unqiue chars should return false. Result: " + (!checker4.getIsUnique() ? "test passed" : "test failed"));
		
		// test 4: non-unique chars at end of string
		UniqueCharsChecker checker5 = new UniqueCharsChecker();
		checker5.charsAreAllUniqueNaiveApproach("abc123dd");
		System.out.println("Test 5.1: non-unqiue chars at end of string should return false. Result: " + (!checker5.getIsUnique() ? "test passed" : "test failed"));
				
		checker4.charsAreAllUniqueSortedApproach("abc123dd");
		System.out.println("Test 5.2: non-unqiue chars at end of string should return false. Result: " + (!checker5.getIsUnique() ? "test passed" : "test failed"));
		
		// test 5: string with only 1 char
		UniqueCharsChecker checker6 = new UniqueCharsChecker();
		checker6.charsAreAllUniqueNaiveApproach("a");
		System.out.println("Test 6.1: single char string should return true. Result: " + (checker6.getIsUnique() ? "test passed" : "test failed"));
		
		checker6.charsAreAllUniqueSortedApproach("a");
		System.out.println("Test 6.2: single char string should return true. Result: " + (checker6.getIsUnique() ? "test passed" : "test failed"));
	}
}
