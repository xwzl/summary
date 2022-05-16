package com.java.interview.java.base.core.application;

import com.java.interview.java.base.core.extension.CarExtension;
import com.java.interview.java.base.core.param.CarParam;
import com.java.interview.java.base.core.Context;
import com.java.interview.java.base.core.param.Param;
import lombok.Data;

/**
 * @author xuweizhi
 * @since 2022/05/16 21:19
 */
@Data
public class CarApplication extends DefaultApplication<String> {
    @Override
    public Context createContext(Param param) {
        CarApplication carContext = new CarApplication();
        CarParam carParam = (CarParam) param;
        CarExtension carExtension = new CarExtension();
        // 差异化处理
        carContext.setExtension(carExtension);
        System.out.println("car 创建上下文");
        return carContext;
    }


    @Override
    public String handle(Context context) {
        System.out.println("car execute");
        return "11";
    }
}
