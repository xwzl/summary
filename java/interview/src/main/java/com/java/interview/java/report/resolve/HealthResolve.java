package com.java.interview.java.report.resolve;

import com.java.interview.java.report.domain.FieldMapping;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2022/05/30 17:33
 */
@Data
public class HealthResolve implements Resolve {

    List<FieldMapping> fields = new ArrayList<>();

    @Override
    public void resolvePattern(Map<String, Object> target, Object t) {
        Map<String, Object> data = (Map<String, Object>) t;
        for (FieldMapping field : fields) {
            Object o = data.get(field.getSource());
            target.put(field.getTarget(), o);
        }
    }


}
