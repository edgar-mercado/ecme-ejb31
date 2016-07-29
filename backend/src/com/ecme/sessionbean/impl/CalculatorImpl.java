package com.ecme.sessionbean.impl;

import com.ecme.sessionbean.CalculatorLocal;
import com.ecme.sessionbean.CalculatorRemote;

public class CalculatorImpl implements CalculatorLocal, CalculatorRemote {

	  public int sum(int add1, int add2) {
	        return add1 + add2;
	    }

	    public int multiply(int mul1, int mul2) {
	        return mul1 * mul2;
	    }

}
