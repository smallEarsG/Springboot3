package org.example.h5.config;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Aspect
@Order(1)
public class ApiAop {
    @Value("${server.name}")
    private  String serverName;

//   设置切入点  controller..*.*(..) 表示controller下的所有包
    @Pointcut("execution(* org.example.h5.controller..*.*(..))")
    public void  pointCut(){  // 连接点
        // 这个方法不会执行任何代码，只是一个标识切点的地方
    }

    /**
     * 请求方法前打印内容
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }
    /**
     * 环绕通知
     * */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        log.info(serverName+"===============================请求开始=============================");
        log.info(serverName+"[请求IP]:{}", request.getRemoteAddr());
        log.info(serverName+"[请求URL]:{}, [请求方式]:{}", request.getRequestURL(), request.getMethod());
        log.info(serverName+"[请求类名]:{},[请求方法名]:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info(serverName+"[请求参数]:{}", JSON.toJSONString(getParameter(method, joinPoint.getArgs())));

        Object retVal;
        Object[] args = joinPoint.getArgs();
        try {
            retVal = joinPoint.proceed(args);
        } catch (Exception e) {
            log.info(serverName+"===============================请求异常=============================");
            log.error(e.getMessage(), e);
            long endTime = System.currentTimeMillis();
            log.info(serverName+"[请求耗时]:{}毫秒", endTime - startTime);
            log.info(serverName+"===============================请求结束=============================");
            throw e;
        }
        long endTime = System.currentTimeMillis();
        log.info(serverName+"[请求耗时]:{}毫秒", endTime - startTime);
        return retVal;
    }
    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);

            if (requestBody == null && requestParam == null) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String arg = mapper.writeValueAsString(args[i]);
                    argList.add(arg);
                } catch (Exception e) {

                }
            } else {
                if (requestBody != null) {
                    argList.add(args[i]);
                }

                if (requestParam != null) {
                    Map<String, Object> map = new HashMap<>();
                    String key = parameters[i].getName();
                    if (!StringUtils.isEmpty(requestParam.value())) {
                        key = requestParam.value();
                    }
                    map.put(key, args[i]);
                    argList.add(map);
                }
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
    /**
     * 在方法执行后打印返回内容
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "pointCut()")
    public void doAfterReturning(Object obj) throws Throwable {
        // 处理完请求，返回内容
        log.info(serverName+"===============================返回内容=============================");
        log.info(serverName+"result:{}",JSON.toJSONString(obj));
        log.info(serverName+"===============================请求结束=============================");
    }

}
