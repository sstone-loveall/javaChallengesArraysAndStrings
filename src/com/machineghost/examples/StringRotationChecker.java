package com.machineghost.examples;

/***
 * Given two strings, check if the second string is a rotation of the first.
 * Assumption: case-insensitive
 *
 */
public class StringRotationChecker {

	/***
	 * Determine if the second string is a rotation of the first
	 * @param firstString: the base string
	 * @param secondString: the rotated string
	 * @return true if the second string is a rotation of the first
	 */
	public Boolean isRotation(String firstString, String secondString) {
		// check for null
		if (firstString == null || secondString == null) {
			return false;
		}
		
		// check for empty
		if (firstString.isEmpty() || secondString.isEmpty()) {
			return false;
		}
		
		// check if strings are not the same length
		if (firstString.length() != secondString.length()) {
			return false;
		}
		
		// compare the possible rotations
		int maxIndex = firstString.length() -1;
		for (int i = 0; i < maxIndex; i++) {
			String substring1 = firstString.substring(i + 1, maxIndex + 1);
			String substring2 = firstString.substring(0, i + 1);
			String rotation = substring1.concat(substring2);
			if (rotation.equalsIgnoreCase(secondString)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Boolean isRotation = false;
		
		// test 1: null and empty strings
		StringRotationChecker checker1 = new StringRotationChecker();
		isRotation = checker1.isRotation(null, null);
		System.out.println("Test 1.1: null strings should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker1.isRotation("", "");
		System.out.println("Test 1.2: empty strings should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker1.isRotation("not null", null);
		System.out.println("Test 1.3: null strings should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		// test 2: strings are not the same length
		StringRotationChecker checker2 = new StringRotationChecker();
		isRotation = checker2.isRotation("taco", "tacocat");
		System.out.println("Test 2.1: strings of different length should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker2.isRotation("t", "ta");
		System.out.println("Test 2.2: strings of different length should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker2.isRotation("", "t");
		System.out.println("Test 2.3: strings of different length should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker2.isRotation("ta", "t");
		System.out.println("Test 2.4: strings of different length should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		// test 3: strings are not rotations
		StringRotationChecker checker3 = new StringRotationChecker();
		isRotation = checker3.isRotation("cat", "dog");
		System.out.println("Test 3.1: strings that are not rotations should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		isRotation = checker3.isRotation("teacup", "eatcup");
		System.out.println("Test 3.2: strings that are not rotations should return false. Result: " + (!isRotation ? "test passed" : "test failed"));
		
		// test 4: strings are rotations
		StringRotationChecker checker4 = new StringRotationChecker();
		isRotation = checker4.isRotation("teacup", "cuptea");
		System.out.println("Test 4.1: strings that are rotations should return true. Result: " + (isRotation ? "test passed" : "test failed"));
		
		isRotation = checker4.isRotation("teacup", "upteac");
		System.out.println("Test 4.2: strings that are rotations should return true. Result: " + (isRotation ? "test passed" : "test failed"));
	}
}
