package com.machineghost.examples;

import java.util.Arrays;

public class PermutationChecker {
	
	private Boolean isPermutation;
	
	public PermutationChecker() { }
	
	public Boolean getIsPermutation() {
		return isPermutation;
	}
	
	/***
	 * Given two strings, decide if one is a permutation of the other.
	 * This approach mimics a hash table by mapping each char in the strings to its count/frequency.
	 * This is not as readable, but is effective and more efficient: close to O(1)
	 * Assumption: ASCII chars only, case and whitespace sensitive
	 * @param firstString: the first ASCII string to compare
	 * @param secondString: the second ASCII string to compare
	 * @return true if the strings are permutations of each other
	 */
	public Boolean stringIsPermutationOfAnotherOptimized(String firstString, String secondString) {
		isPermutation = true;
		
		if (!eligibleForPermutations(firstString, secondString)) {
			isPermutation = false;
			return isPermutation;
		}
		
		/*** 
		 * Algorithm:
		 * Create an array of ints that stores the count of each char in the first string.
		 * Then decrement the count based on how the second string maps to the same array of ints.
		 * If there are any negative numbers, then we can infer there is a "mismatch" between the char frequency in the two strings.
		 */
		
		int[] chars = new int[128];
		for (int i = 0; i < firstString.length(); i++) {
			// increment the frequency count for the current char
			chars[firstString.charAt(i)]++;
		}
		
		for (int j = 0; j < secondString.length(); j++) {
			// decrement the frequency count for the current char
			chars[secondString.charAt(j)]--;
			
			int currentCharCount = chars[secondString.charAt(j)];
			if (currentCharCount < 0) {
				// if we find a negative number it indicates a unique char in the second string
				isPermutation = false;
				break;
			}
		}
		
		return isPermutation;
	}
	
	/***
	 * Given two strings, decide if one is a permutation of the other.
	 * This approach sorts both strings' char arrays then compares.
	 * This is effective and fairly efficient. Sort is O(n log n)
	 * Assumption: case and whitespace sensitive 
	 * @param firstString: the first string to compare
	 * @param secondString: the second string to compare as a permutation of the first
	 * @return true if the strings are permutations of each other
	 */
	public Boolean stringIsPermutationOfAnother(String firstString, String secondString) {
		isPermutation = true;
		
		if (!eligibleForPermutations(firstString, secondString)) {
			isPermutation = false;
			return isPermutation;
		}
		
		char[] firstChars = firstString.toCharArray();
		Arrays.sort(firstChars);
		
		char[] secondChars = secondString.toCharArray();
		Arrays.sort(secondChars);
		
		isPermutation = (Arrays.equals(firstChars, secondChars));
		
		return isPermutation;
	}
	
	/***
	 * Check if the input strings are valid for permutation evaluation.
	 * 
	 * @param firstString: the first string to compare
	 * @param secondString: the second string to compare
	 * @return true if the strings could be possible permutations
	 */
	public Boolean eligibleForPermutations(String firstString, String secondString) {
		if (firstString == null || secondString == null) {
			// if either string is null, there is nothing to compare
			return false;
		}
		
		if (firstString.length() != secondString.length()) {
			// if they aren't the same length, they can't be permutations
			return false;
		}
		
		if (firstString.equals(secondString)) {
			// if the strings are identical, they aren't really permutations are they?
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		// test 1: null inputs
		PermutationChecker checker1 = new PermutationChecker();
		checker1.stringIsPermutationOfAnother("something", null);
		System.out.println("Test 1.1: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		checker1.stringIsPermutationOfAnother(null, "something");
		System.out.println("Test 1.2: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		checker1.stringIsPermutationOfAnother(null, null);
		System.out.println("Test 1.3: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		checker1.stringIsPermutationOfAnotherOptimized("something", null);
		System.out.println("Test 1.4: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		checker1.stringIsPermutationOfAnotherOptimized(null, "something");
		System.out.println("Test 1.5: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		checker1.stringIsPermutationOfAnotherOptimized(null, null);
		System.out.println("Test 1.6: null string should return false. Result: " + (!checker1.getIsPermutation() ? "test passed" : "test failed"));
		
		// test 2: strings are not permutations
		PermutationChecker checker2 = new PermutationChecker();
		checker2.stringIsPermutationOfAnother("something", "something");
		System.out.println("Test 2.1: identical strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnother("", "");
		System.out.println("Test 2.2: identical strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnother("dog", "cat");
		System.out.println("Test 2.3: non-permutation strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnother("dog", "d og");
		System.out.println("Test 2.4: non-permutation strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnotherOptimized("something", "something");
		System.out.println("Test 2.5: identical strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnotherOptimized("", "");
		System.out.println("Test 2.6: identical strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnotherOptimized("dog", "cat");
		System.out.println("Test 2.7: non-permutation strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		checker2.stringIsPermutationOfAnotherOptimized("dog", "d og");
		System.out.println("Test 2.8: non-permutation strings should return false. Result: " + (!checker2.getIsPermutation() ? "test passed" : "test failed"));
		
		// test 3: strings are permutations
		PermutationChecker checker3 = new PermutationChecker();
		checker3.stringIsPermutationOfAnother("dog", "god");
		System.out.println("Test 3.1: permutation strings should return true. Result: " + (checker3.getIsPermutation() ? "test passed" : "test failed"));
		
		checker3.stringIsPermutationOfAnother("dog12", "g1o2d");
		System.out.println("Test 3.2: permutation strings should return true. Result: " + (checker3.getIsPermutation() ? "test passed" : "test failed"));
	
		checker3.stringIsPermutationOfAnotherOptimized("dog", "god");
		System.out.println("Test 3.3: permutation strings should return true. Result: " + (checker3.getIsPermutation() ? "test passed" : "test failed"));
		
		checker3.stringIsPermutationOfAnotherOptimized("dog12", "g1o2d");
		System.out.println("Test 3.4: permutation strings should return true. Result: " + (checker3.getIsPermutation() ? "test passed" : "test failed"));
	
		// test 4: string is substring of the other
		PermutationChecker checker4 = new PermutationChecker();
		checker4.stringIsPermutationOfAnother("abcdef", "abc");
		System.out.println("Test 4.1: substrings should return false. Result: " + (!checker4.getIsPermutation() ? "test passed" : "test failed"));
		
		checker4.stringIsPermutationOfAnotherOptimized("abcdef", "abc");
		System.out.println("Test 4.2: substrings should return false. Result: " + (!checker4.getIsPermutation() ? "test passed" : "test failed"));	
	}
}
