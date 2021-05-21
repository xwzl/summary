import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 
 * @projectName testweb
 * @author wukong
 * @date 2020/7/7 19:11
 */
public class Myfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //过滤器初始化
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println("请求地址"+req.getRequestURI());
        //让过滤器放行
        //过滤器相互之间是不清楚它的上一个或下一个是谁，所有的调用都是服务器在处理
        //Filter执行顺序可以由<filter_mapping>的顺序决定
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        //过滤器销毁
    }
}