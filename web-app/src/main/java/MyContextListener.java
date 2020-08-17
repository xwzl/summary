import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description: 
 * @projectName testweb
 * @author wukong
 * @date 2020/7/7 19:15
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //应用开启时调用
        String switchUserName = sce.getServletContext()
                .getInitParameter("switch_user_name");

        System.out.println("打开应用的用户：" + switchUserName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //应用关闭时调用
        sce.getServletContext().getRealPath("img");
    }
}
