package com.revature.test.junit;

import com.revature.model.CurriculumJSON;

public class DemoTestApplication {

	public static void main(String[] args) {

		CurriculumJSON js = new CurriculumJSON();
		
		//js.setCount(1);
		js.setId(5);
		//js.setName("cat");
		
		System.out.println(js.compareTo(js));
		
		
		CurriculumJSON js1 = new CurriculumJSON();
		//js1.setCount(5);
		js1.setId(6);
		//js1.setName("demo2");
		
		System.out.println(js.compareTo(js1));
		
		System.out.println(js1.compareTo(js));
		
		
		
	}

}
