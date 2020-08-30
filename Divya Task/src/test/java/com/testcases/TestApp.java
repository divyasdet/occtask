package com.testcases;

import org.junit.Test;

import com.util.EmployeeUtil;

public class TestApp {
	
	@Test
	public void runTest() {
		
		//Generate Unique Values From JSON and CSV
			EmployeeUtil.GenerateUniqueValuesFromJsonCsv();
	}

}
