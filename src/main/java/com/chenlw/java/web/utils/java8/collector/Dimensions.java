package com.chenlw.java.web.utils.java8.collector;

import java.util.Arrays;

/**
 * Created by chenlw on 2019/05/16  15:26.
 */
public class Dimensions {

    public String[] dimensions;

    public Dimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }


    @Override
    public String toString() {
        return "Dimensions{" +
                "dimensions=" + Arrays.toString(dimensions) +
                '}';
    }

    /**
     * 重写equals()方法是为了本类实例对象作为Map散列结构的key时，判断key是否已经存在
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dimensions that = (Dimensions) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(dimensions, that.dimensions);
    }

    /**
     * 重写equals()方法是为了本类实例对象作为Map散列结构的key时，判断key是否已经存在
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(dimensions);
    }

}
