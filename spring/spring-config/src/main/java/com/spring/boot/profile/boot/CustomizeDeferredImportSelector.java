package com.spring.boot.profile.boot;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/***
 * 延迟 特性，  分组特性
 * @author xuweizhi
 */
public class CustomizeDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.spring.boot.profile.boot.Person"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }


    @Override
    public Class<? extends Group> getImportGroup() {
        // 这个返回值决定调用DeferredImportSelector.selectImports  如果null
        // 还是调用Group.selectImports
        return MyGroup.class;
    }

    /**
     * 如果getImportGroup返回自定义Group ， 会调用自定义Group的process方法
     * 如果getImportGroup返回 null,会调用DefaultDeferredImportSelectorGroup的process方法,即调用selectImports
     * 分组利用归类，同一组的bean只影响本组的顺序
     */
    private static class MyGroup
            implements Group {

        AnnotationMetadata metadata;

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            this.metadata = metadata;
        }

        @Override
        public Iterable<Entry> selectImports() {
            List<Entry> list = new ArrayList<>();
            list.add(new Entry(this.metadata, "com.spring.boot.profile.boot.Person"));
            return list;
        }
    }


}
