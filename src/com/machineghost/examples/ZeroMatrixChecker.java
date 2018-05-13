package com.machineghost.examples;

import java.util.ArrayList;
import java.util.List;

/***
 * Write an algorithm such that if an element is in an MxN matrix is 0,
 * its entire row and column are set to 0.
 *
 */
public class ZeroMatrixChecker {

	/***
	 * Transform containing row and column elements to zero in the given matrix where zeros are found.
	 * @param matrix: the matrix to perform
	 */
	public void transformMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		
		List<Integer> rows = new ArrayList<Integer>(matrix.length);
		List<Integer> columns = new ArrayList<Integer>(matrix[0].length);
		
		// track where we find any zeroes
		// for every row, loop through every column element
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					rows.add(i);
					columns.add(j);
				}
			}
		}
		
		// replace the elements with zeros where appropriate
		for (int row : rows) {
			zeroOutRow(matrix, row);
		}
		for (int col : columns) {
			zeroOutColumn(matrix, col);
		}
	}
	
	/***
	 * Transform all elements in a matrix for a given row
	 * @param matrix: the matrix to transform
	 * @param col: the row to target for transforms to zero
	 */
	public void zeroOutRow(int[][] matrix, int row) {
		for (int i = 0; i < matrix[0].length; i++) {
			matrix[row][i] = 0;
		}
	}
	
	/***
	 * Transform all elements in a matrix for a given column
	 * @param matrix: the matrix to transform
	 * @param col: the column to target for transforms to zero
	 */
	public void zeroOutColumn(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}
	
	/***
	 * Determine if two matrices have the same values
	 * @param firstMatrix: the first matrix
	 * @param secondMatrix: the matrix to compare to
	 * @return true if the matrices have the same values
	 */
	public Boolean matricesAreEqual(int[][] firstMatrix, int[][] secondMatrix) {
		// if both matrices are null, return true
		if (firstMatrix == null && secondMatrix == null) {
			return true;
		} 
		else if (secondMatrix == null) {
			return false;
		}
		
		// they are both empty, return true
		if (firstMatrix.length == 0 && secondMatrix.length == 0) {
			return true;
		}
		
		// if the matrices are different row or column lengths, they can't be the same
		if (firstMatrix.length != secondMatrix.length
				|| firstMatrix[0].length != secondMatrix[0].length) {
			return false;
		}
		
		// compare each element in the two matrices
		for (int row = 0; row < firstMatrix.length; row++) {
			for (int col = 0; col < firstMatrix[0].length; col++) {
				if (firstMatrix[row][col] != secondMatrix[row][col]) {
					// they don't match
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// test 1: null and empty matrix
		int[][] inputMatrix1 = new int[][]{};
		int[][] testMatrix1 = new int[][]{};
		ZeroMatrixChecker checker1 = new ZeroMatrixChecker();
		checker1.transformMatrix(inputMatrix1);
		System.out.println("Test 1: empty matrix should not be transformed. Result: " + (checker1.matricesAreEqual(inputMatrix1, testMatrix1) ? "test passed" : "test failed"));
		
		// test 2: matrix with no zeros
		int[][] inputMatrix2 = new int[][]{
			  { 1, 2, 3 },
			  { 3, 4, 5 },
			  { 5, 6, 7 }
		};
		int[][] testMatrix2 = new int[][]{
			{ 1, 2, 3 },
			{ 3, 4, 5 },
			{ 5, 6, 7 }
		};
		ZeroMatrixChecker checker2 = new ZeroMatrixChecker();
		checker2.transformMatrix(inputMatrix2);
		System.out.println("Test 2: matrix without zeros should not be transformed. Result: " + (checker2.matricesAreEqual(inputMatrix2, testMatrix2) ? "test passed" : "test failed"));
		
		// test 3: matrix with zeros
		int[][] inputMatrix3 = new int[][]{
			  { 1, 2, 0 },
			  { 3, 0, 4 },
			  { 5, 6, 7 }
		};
		int[][] testMatrix3 = new int[][]{
			  { 0, 0, 0 },
			  { 0, 0, 0 },
			  { 5, 0, 0 }
		};
		ZeroMatrixChecker checker3 = new ZeroMatrixChecker();
		checker3.transformMatrix(inputMatrix3);
		System.out.println("Test 3: matrix should match transformed. Result: " + (checker3.matricesAreEqual(inputMatrix3, testMatrix3) ? "test passed" : "test failed"));
	
		int[][] inputMatrix4 = new int[][]{
			  { 1, 2, 0 },
			  { 3, 0, 4 },
			  { 5, 6, 7 }
		};
		int[][] testMatrix4 = new int[][]{
			  { 1, 2, 0 },
			  { 3, 0, 4 },
			  { 5, 6, 7 }
		};
		ZeroMatrixChecker checker4 = new ZeroMatrixChecker();
		checker4.transformMatrix(inputMatrix4);
		System.out.println("Test 4: matrix should not match transformed. Result: " + (!checker4.matricesAreEqual(inputMatrix4, testMatrix4) ? "test passed" : "test failed"));
	
	}
}
