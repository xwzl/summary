package com.common.starter.logstash;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


/**
 * ConditionalOnClass 最好使用 name 属性，找不到也不会报错
 * <p>
 * com.common.starter.logstash.config.LogstashAspect 本 jar 中存在，因此会一直触发
 */
@Slf4j
@Aspect
@Component
@ConditionalOnClass(name = "com.common.starter.logstash.config.LogstashAspect")
@SuppressWarnings("all")
public class SystemLogAutoConfig {

    public SystemLogAutoConfig() {
        log.info("system log config init");
    }

    @Pointcut("@within(com.common.starter.logstash.config.LogstashAspect) || @annotation(com.common.starter.logstash.config.LogstashAspect)")
    public void pointCut() {
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Exception {

        long startTime = System.currentTimeMillis();

        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req = attributes.getRequest();
            // 记录下请求内容
            log.debug("[HTTP <<<] ");
            log.info("[HTTP_URL] : {}", req.getRequestURL().toString());
            log.info("[HTTP_METHOD] : {}", req.getMethod());
            log.info("[HTTP_ACTION] : {}", pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            Object[] args = getMethodArg(pjp.getArgs());
            // log.info("[HTTP_PARAMS] : {}", JSON.toJSONString(args[0]));
            log.info("[REMOTE_IP] : {}", req.getRemoteAddr());

            //运行方法
            Object o = pjp.proceed();
            log.info("[HTTP_RESPONSE]: {}", JSON.toJSONString(o));
            return o;
        } catch (Throwable throwable) {
            throw new Exception(throwable.getMessage(), throwable);
        } finally {
            long etime = System.currentTimeMillis();
            log.info("[HTTP_TIME] : {}", (etime - startTime) + "ms");
        }
    }


    private Object[] getMethodArg(Object[] args) {
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        return arguments;
    }

}
