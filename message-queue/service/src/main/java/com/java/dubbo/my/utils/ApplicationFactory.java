package com.java.dubbo.my.utils;


import com.java.dubbo.my.framework.protocol.auto.AutoComponent;
import com.java.dubbo.my.framework.protocol.auto.AutoConfig;
import com.java.dubbo.my.framework.protocol.auto.AutoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author xuweizhi
 * @date 2019/04/09 20:50
 */
@Slf4j
@Data
public class ApplicationFactory {
    /**
     * 操作系统分割符
     */
    public static final String OPERATE_PATH_DELIMITER = "/";

    public final static String INCLUDE_PACKAGE_PATTERN = "&";

    public final static String EXCLUDE_PACKAGE_PATTERN = "#";


    private List<String> exPath;

    public Set<Class<?>> services = new HashSet<>();
    public Set<Class<?>> configs = new HashSet<>();

    private ResourcesUtils resourcesUtils;

    private Map<String, Object> singletons = new ConcurrentHashMap<>();

    public static ApplicationFactory getInstance(String path) {
        ApplicationFactory pathUtils = new ApplicationFactory();
        pathUtils.setResourcesUtils(new ResourcesUtils());
        try {
            pathUtils.packageScan(path + "#" + AutoComponent.class.getPackage().getName(), AutoComponent.class);
            pathUtils.getResourcesUtils().parse("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathUtils;
    }


    /**
     * 获取指定注解的类们
     *
     * @param path            包路径
     * @param annotationClazz 指定注解类型
     * @return value 值
     */
    @SuppressWarnings("all")
    public Set<Class<?>> packageScan(String path, Class<?> annotationClazz) throws IOException {
        // 替换包
        path = path.replaceAll("\\.", OPERATE_PATH_DELIMITER);
        String basePath = "";
        exPath = new ArrayList<>();
        List<String> inPath = new ArrayList<>();
        String remainPath = "";
        if (path.contains(EXCLUDE_PACKAGE_PATTERN)) {
            basePath = getPath(path, EXCLUDE_PACKAGE_PATTERN);
            remainPath = getExcludePath(getRemainPath(path, EXCLUDE_PACKAGE_PATTERN), exPath);
        }
        if (path.contains(INCLUDE_PACKAGE_PATTERN)) {
            if (StringUtils.isEmpty(basePath)) {
                basePath = path.substring(0, path.indexOf(INCLUDE_PACKAGE_PATTERN));
            }
            remainPath = getExcludePath(getRemainPath(path, INCLUDE_PACKAGE_PATTERN), inPath);
        }
        if (StringUtils.isEmpty(remainPath)) {
            log.info("Start scanning package path !");
        }
        if (StringUtils.isEmpty(basePath)) {
            basePath = path;
        }
        inPath.add(basePath);
        Set<Class<?>> result = new HashSet<>();
        for (String packagePath : inPath) {
            result.addAll(getBeanClass(annotationClazz, packagePath));
            log.info(packagePath + " Package scanning completed !");
        }
        return result;
    }

    /**
     * 获取 指定路径下的包们
     *
     * @param component 指定组件
     * @param basePath  扫描的基本包路径
     * @return 指定路径下的classes 们
     * @throws IOException IO 异常
     */
    private Set<Class<?>> getBeanClass(Class<?> component, String basePath) throws IOException {
        ArrayList<URL> list = Collections.list(Thread.currentThread().getContextClassLoader().getResources(basePath));

        List<String> result = new ArrayList<>();

        Set<Class<?>> set = new HashSet<>();

        list.forEach(url -> {
            result.addAll(list(url, basePath).stream().filter(value -> value.endsWith(".class")).collect(Collectors.toList()));
        });

        result.forEach(className -> {
            // 获得全类名
            match(className, component, set);
        });
        return set;
    }

    /**
     * 获取包扫描路径
     */
    public String getPath(String path, String regex) {
        return path.substring(0, path.indexOf(regex));
    }

    /**
     * 递归添加 exPath
     */
    private String getExcludePath(String remainPath, List<String> exPath) {
        // 如果有不扫描的包路径，则添加
        if (remainPath.contains(EXCLUDE_PACKAGE_PATTERN)) {
            exPath.add(getPath(remainPath, EXCLUDE_PACKAGE_PATTERN));
            return getExcludePath(getRemainPath(remainPath, EXCLUDE_PACKAGE_PATTERN), exPath);
        } else {
            // 如果指定了额外的包扫描路径，返回额外包扫描路径的包
            if (remainPath.contains(INCLUDE_PACKAGE_PATTERN)) {
                exPath.add(getPath(remainPath, INCLUDE_PACKAGE_PATTERN));
                return getRemainPath(remainPath, INCLUDE_PACKAGE_PATTERN);
            } else {
                exPath.add(remainPath);
                // 否则返回空串
                return "";
            }
        }
    }

    private String getRemainPath(String remainPath, String regex) {
        return remainPath.substring(remainPath.indexOf(regex) + 1);
    }

    /**
     * 模拟Mybatis 匹配注解,当然Mybatis 真正的包扫描没有那么简单
     */
    public boolean match(Class<?> clazz, Class<?> component) {
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            if (type.equals(component)) {
                return true;
            }
            if (type.isAnnotationPresent(AutoComponent.class)) {
                if (clazz.getAnnotation(AutoService.class) != null) {
                    services.add(clazz);
                }
                if (clazz.getAnnotation(AutoConfig.class) != null) {
                    configs.add(clazz);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 模拟 Mybatis 包扫描
     */
    public void match(String className, Class<?> component, Set<Class<?>> set) {
        // 获取全雷鸣
        String externalName = className.substring(0, className.indexOf('.')).replace('/', '.');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(externalName);
            if (match(clazz, component)) {
                set.add(clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static List<URL> getResources(String path) throws IOException {
        return Collections.list(Thread.currentThread().getContextClassLoader().getResources(path));
    }

    /**
     * 获取指定包下的 URL .class 文件
     */
    public List<String> list(URL url, String path) {
        InputStream is = null;
        List<String> resources = new ArrayList<String>();
        for (String e : exPath) {
            if (url.getPath().contains(e)) {
                log.info(e + " The file under the package path is not scanned and will be ignored!");
                return resources;
            }
        }
        List<String> children = new ArrayList<String>();
        try {
            //获取该路径下的资源
            is = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<String>();
            for (String line; (line = reader.readLine()) != null; ) {
                lines.add(line);
                if (getResources(path + OPERATE_PATH_DELIMITER + line).isEmpty()) {
                    lines.clear();
                    break;
                }
            }
            children.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String prefix = url.toExternalForm();
        if (!prefix.endsWith(OPERATE_PATH_DELIMITER)) {
            prefix = prefix + OPERATE_PATH_DELIMITER;
        }

        // Iterate over immediate children, adding files and recursing into directories
        for (String child : children) {
            String resourcePath = path + OPERATE_PATH_DELIMITER + child;
            resources.add(resourcePath);
            URL childUrl = null;
            try {
                childUrl = new URL(prefix + child);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            resources.addAll(list(childUrl, resourcePath));
        }
        return resources;
    }

    public void addSingleton(String className, Object instance) {
        this.singletons.put(className, instance);
    }

    @SuppressWarnings("all")
    public <T> T getBean(Class<T> clazz){
        return (T) this.singletons.get(clazz.getName());
    }
}
