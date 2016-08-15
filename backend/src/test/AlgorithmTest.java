package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.ecme.algorithms.QuadraticAlgorithms;

public class AlgorithmTest {

	@Test
	public void test() {
		int testArr[] = {6,5,4,3,2,1};
	//	int testArr[] = {9,2,8,12,7	};
		System.out.println(Arrays.toString(testArr));
		System.out.println("*** Bubble Sort ****");
		QuadraticAlgorithms qa = new QuadraticAlgorithms(testArr);
		System.out.println(Arrays.toString(qa.sortBubbleSort()));
		System.out.println("*** Selection Sort ****");
		QuadraticAlgorithms qa2 = new QuadraticAlgorithms(testArr);
		System.out.println(Arrays.toString(qa2.sortSelectionSort()));
		assertTrue(true);;
	}

}
