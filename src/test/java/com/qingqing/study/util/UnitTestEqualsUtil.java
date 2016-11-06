package com.qingqing.study.util;

import com.qingqing.common.util.converter.lang.BigDecimalUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class UnitTestEqualsUtil {

    public final static boolean isEquals(Object obj1, Object obj2, String... ignoreProperties) throws IllegalArgumentException, IllegalAccessException {
        return isEquals(obj1, obj2, new HashSet<String>(Arrays.asList(ignoreProperties)));
    }

    public final static boolean isEquals(Object obj1, Object obj2, Set<String> ignoreProperties) throws IllegalArgumentException, IllegalAccessException {
        if (obj1 == null) {
            return obj2 == null;
        }

        if (obj2 == null) {
            return false;
        }

        if (!obj1.getClass().equals(obj2.getClass())) {
            return false;
        }

        for (Field filed : obj1.getClass().getDeclaredFields()) {
            if (ignoreProperties.contains(filed.getName())) {
                continue;
            }

            if (!filed.isAccessible()) {
                filed.setAccessible(true);
            }
            Object object = filed.get(obj1);
            Object object2 = filed.get(obj2);
            boolean isEqual = object == null ? object2 == null : object.equals(object2);
            if (!isEqual && object != null && object2 != null) {
                if (object instanceof Double && object2 instanceof Double) {
                    isEqual = BigDecimalUtil.equalInDefaultScale((Double) object, (Double) object2);
                } else if (object instanceof Date && object2 instanceof Date) {
                    isEqual = ((Date) object).getTime() / 1000 == ((Date) object2).getTime() / 1000;
                } else if ((object instanceof BigInteger || object instanceof BigDecimal) && (object2 instanceof BigInteger || object2 instanceof BigDecimal)) {
                    isEqual = BigDecimalUtil.equalInDefaultScale(((BigDecimal) object).doubleValue(), ((BigDecimal) object2).doubleValue());
                }
            }

            if (!isEqual) {
                System.err.println("property not equal: fieldName: " + filed.getName() + ",  object1:" + object + ", object2:" + object2);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        ClassA clA1 = new ClassA(1, "a");
        ClassA clA2 = new ClassA(1, "a");
        ClassA clA3 = new ClassA(1, "b");
        ClassB clB = new ClassB(1, "a");

        Set<String> ignoreSet = new HashSet<String>();
        ignoreSet.add("prop2");

        System.out.println(UnitTestEqualsUtil.isEquals(clA1, clA2));
        System.out.println(UnitTestEqualsUtil.isEquals(clA1, clA3));
        System.out.println(UnitTestEqualsUtil.isEquals(clA1, clA3, ignoreSet));
        System.out.println(UnitTestEqualsUtil.isEquals(clA1, clB));
    }

    public static class ClassA {
        private int prop1;

        private String prop2;

        public int getProp1() {
            return prop1;
        }

        public void setProp1(int prop1) {
            this.prop1 = prop1;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }

        public ClassA(int prop1, String prop2) {
            super();
            this.prop1 = prop1;
            this.prop2 = prop2;
        }
    }

    public static class ClassB {
        private int prop1;

        private String prop2;

        public int getProp1() {
            return prop1;
        }

        public void setProp1(int prop1) {
            this.prop1 = prop1;
        }

        public String getProp2() {
            return prop2;
        }

        public void setProp2(String prop2) {
            this.prop2 = prop2;
        }

        public ClassB(int prop1, String prop2) {
            super();
            this.prop1 = prop1;
            this.prop2 = prop2;
        }
    }
}
