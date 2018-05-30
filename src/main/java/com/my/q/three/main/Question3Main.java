package com.my.q.three.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.q.three.config.AppConfig;
import com.my.q.three.obj.ClassMethods;
import com.my.q.three.obj.MethodParams;

public class Question3Main {

	public static void main(String[] args) {
		Question3Main main = new Question3Main();

		HashMap<String, ClassMethods> cmMap = main.getClassMap("question3test.txt");

		for (Iterator<String> iterator = cmMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			ClassMethods cm = cmMap.get(key);

			main.runQuestion3(cm);
		}
	}

	/**
	 * invoke the method.
	 * Spring aspect will intercept the method calling and log the time in milliseconds
	 * @param cm
	 */
	public void runQuestion3(ClassMethods cm) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		try {

			BeanDefinition bd = new GenericBeanDefinition();
			bd.setBeanClassName(cm.getClassName());

			context.registerBeanDefinition("theBean", bd);
			Object obj = context.getBean("theBean");

			Class<?> cls = Class.forName(obj.getClass().getName());

			for (MethodParams mp : cm.getMethodParamsList()) {

				try {
					Method method;
					if(mp.getParameters()!=null) {
						Class<?> params[] = new Class[mp.getParameters().length];
						for (int i = 0; i < mp.getParameters().length; i++) {
							if (mp.getParameters()[i] instanceof Integer) {
								params[i] = Integer.TYPE;
							} else if (mp.getParameters()[i] instanceof String) {
								params[i] = String.class;
							}
						}
						method = cls.getDeclaredMethod(mp.getMethodName(), params);
						method.invoke(obj, mp.getParameters());
					}else {
						method = cls.getDeclaredMethod(mp.getMethodName());
						method.invoke(obj);
					}
					
				}catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException |
						SecurityException | InvocationTargetException  e) {
					e.printStackTrace();
				}
			}

		} catch ( ClassNotFoundException e) {
			e.printStackTrace();
		}

		context.close();
	}

	/**
	 * Get list of method to test execution time from resources file
	 * @param fileName
	 * @return
	 */
	public HashMap<String, ClassMethods> getClassMap(String fileName) {

		HashMap<String, ClassMethods> classMap = new HashMap<String, ClassMethods>();
		try {

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
			{
				//line that start with # is comments.
				//only process line that does not start with #
				if(!line.startsWith("#")) {

					try {

						ClassMethods cm;

						String val[] = line.split(";");

						//Check if existing map already contains the class
						//We dont want to register the class in bean multiple times
						if(classMap.containsKey(val[0])) {
							cm = classMap.get(val[0]);
						}else {
							cm = new ClassMethods();
							cm.setClassName(val[0]);
						}

						MethodParams mp = new MethodParams();
						mp.setMethodName(val[1]);

						//check if there is any parameters to pass in
						if(val.length>=3) {
							mp.setParameters(getParameters(val));
						}

						cm.setMethodParams(mp);

						classMap.put(val[0], cm);

					}catch (NullPointerException e) {
						System.err.println("Invalid line " + line);
					}

				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classMap;
	}

	/**
	 * process line in resources folder and convert the parameters to objects
	 * @param val
	 * @return
	 */
	private Object[] getParameters(String[] val) {

		//first 2 item in the line are class name and method name.
		//exclude them.
		Object[] objs = new Object[val.length-2];

		for (int i = 2; i < val.length; i++) {
			String str = val[i];

			if(str.toLowerCase().startsWith("int")) {
				objs[i-2] = Integer.parseInt(val[i].substring(3).trim());
			}else if(str.toLowerCase().startsWith("string")) {
				//we assume first 6 character as setup
				//it is unsafe in real world program.
				objs[i-2] = val[i].substring(6).trim();
			}else {
				//we assume if no match as string for test purpose
				objs[i-2] = val[i];
			}
		}
		return objs;
	}
}