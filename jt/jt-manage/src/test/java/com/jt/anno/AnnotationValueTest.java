package com.jt.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.controller.ItemCatController;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class AnnotationValueTest {

	@Test
	public void testAnnotation() throws Exception, Exception {
		
	Class<ItemCatController> clz = ItemCatController.class;
		
		ItemCatController instance = clz.newInstance();
		
		Method[] methods = clz.getMethods();
		for (Method method : methods) {
			System.out.println(methods);
		}
		
		Method method = clz.getMethod("findItemCatAll", Long.class);
		
		 Annotation[] annotations = method.getAnnotations();
		 
		 for (Annotation annotation : annotations) {
			
			 System.out.println(annotation);
		}
	}
	
}
