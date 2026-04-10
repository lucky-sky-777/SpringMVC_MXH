package com.oosd.springmvc_mxh.util;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class ResultSetMapper {

	public static <T> List<T> map(ResultSet resultSet, Class<T> clazz) {
		List<T> list = new ArrayList<>();

		try {
			while (resultSet.next()) {
				T object = clazz.getDeclaredConstructor().newInstance();

				for (Field field : clazz.getDeclaredFields()) {
					field.setAccessible(true);
					field.set(object, resultSet.getObject(field.getName()));
				}

				list.add(object);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public static <T> List<T> map2(ResultSet resultSet, Class<T> clazz) {
		List<T> list = new ArrayList<>();

		try {
			// 🔥 CHANGED: lấy constructor của record
			Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];

			// 🔥 CHANGED: lấy danh sách field của record
			RecordComponent[] components = clazz.getRecordComponents();

			while (resultSet.next()) {

				// 🔥 CHANGED: tạo args thay vì newInstance()
				Object[] args = new Object[components.length];

				for (int i = 0; i < components.length; i++) {
					String fieldName = components[i].getName();
					args[i] = resultSet.getObject(fieldName);
				}

				// 🔥 CHANGED: gọi constructor có tham số
				T object = constructor.newInstance(args);

				list.add(object);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return list;
	}

}