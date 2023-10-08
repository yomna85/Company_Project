package com.example.BackendTask.utils;

import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class HibernateUtils {
    public static boolean isConvertible(Set<?> set) {
        if (set == null)
            return false;
        return Hibernate.isInitialized(set);
    }

    public static boolean isConvertible(List<?> list) {
        return Hibernate.isInitialized(list);
    }

    public static boolean isConvertible(Object obj) {
        return Hibernate.isInitialized(obj);
    }
}

