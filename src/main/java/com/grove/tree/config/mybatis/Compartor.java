package com.grove.tree.config.mybatis;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

public class Compartor {
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
		if( object instanceof String ) return object==null || "".equals(object.toString().trim());
        else if( object instanceof List ) return object==null || ((List)object).isEmpty();
        else if( object instanceof Map ) return object==null || ((Map)object).isEmpty();
        else if( object instanceof Object[] ) return object==null || Array.getLength(object)==0;
        else return object==null;
	}
	
	public static boolean isGreaterThan(String numbers, String compare) {
		return NumberUtils.toInt(numbers, 0) > NumberUtils.toInt(compare, 0);
	}
	
	public static boolean isGreaterThanEqual(String numbers, String compare) {
		return NumberUtils.toInt(numbers, 0) >= NumberUtils.toInt(compare, 0);
	}
	
	public static boolean isLessThan(String numbers, String compare) {
		return NumberUtils.toInt(numbers, 0) < NumberUtils.toInt(compare, 0);
	}
	
	public static boolean isLessThanEqual(String numbers, String compare) {
		return NumberUtils.toInt(numbers, 0) <= NumberUtils.toInt(compare, 0);
	}

}
