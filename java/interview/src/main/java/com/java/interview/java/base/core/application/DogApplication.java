package com.java.interview.java.base.core.application;

import com.java.interview.java.base.core.Context;
import lombok.Data;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:19
 */
@Data
public class DogApplication extends DefaultApplication<Integer> {

    @Override
    public Integer handle(Context context) {
        System.out.println("car execute");
        return 12;
    }
}
