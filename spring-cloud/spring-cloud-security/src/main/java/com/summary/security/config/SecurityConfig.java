package com.summary.security.config;

import com.summary.security.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 创建 WebSecurityConfig配置类，继承 WebSecurityConfigurerAdapter抽象类，实现 Spring Security在 Web 场景下的自定义配置。
 * <p>
 * 是否需要添加@EnableWebSecurity注解？
 * <p>
 * springboot 项目中如果引入的是 spring-boot-starter-security 依赖不需要添加 @EnableWebSecurity，可以参考自动配置类
 *
 * @author xuweizhi
 * @since 2022/01/11 19:19
 */
@Configuration
@SuppressWarnings("all")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IUserService userService;

    @Resource
    private DataSource dataSource;

    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // String password = passwordEncoder().encode("123456");
        // auth.inMemoryAuthentication()
        //         .withUser("username")
        //         .password(password)
        //         .roles("admin")
        //         .and()
        //         .withUser("admin")
        //         .password(password).roles("admin");
        // 设置具体实现类
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单相关
        this.formLogin(http);
        // session 管理
        this.sessionManagement(http);
        // rememberMe
        this.rememberMe(http);
        // 退出登录
        this.loginOut(http);
    }

    public void loginOut(HttpSecurity http) throws Exception {

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .addLogoutHandler((request, response, authentication) -> {
                    new SecurityContextLogoutHandler().logout(request, response, authentication);
                });
    }

    /**
     * 记住我
     *
     * @param http //
     * @throws Exception //
     */
    public void rememberMe(HttpSecurity http) throws Exception {
        // 多端登录上限和此处有冲突，同一浏览器，每次返回重新登录会产生一个新的 session
        // 当 Session Id 数量到达上限后，会抛出异常，提示登录失败
        http.rememberMe()
                .tokenRepository(persistentTokenRepository()) // 设置持久化仓库
                .tokenValiditySeconds(3600) // 超时时间,单位s默认两周
                .userDetailsService(userService); // 设置自定义登录逻辑
    }

    private PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * 机制描述
     * always      如果session不存在总是需要创建
     * ifRequired  如果需要就创建一个session（默认）登录时
     * neverSpring Security 将不会创建session，但是如果应用中其他地方创建了session，那么Spring Security将会使用它
     * stateless   Spring Security将绝对不会创建session，也不使用session。并且它会暗示不使用cookie，所以每个请求都需要重新进行身份验证。这种无状态架构适用于REST API及其无状态认证机制。
     * <p>
     * 默认情况下，Spring Security会为每个登录成功的用户会新建一个Session，就是ifRequired 。在执行认证过程之前，
     * spring security将运行SecurityContextPersistenceFilter过滤器负责存储安全请求上下文，上下文根据策略进行
     * 存储，默认为HttpSessionSecurityContextRepository ，其使用http session作为存储器。
     *
     * @param httpSecurity //
     */
    public void sessionManagement(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement()
                .invalidSessionUrl("/session/invalid")
                // 用户在这个手机登录后，他又在另一个手机登录相同账户，对于之前登录的账户是否需要被挤兑，或者说在第二次登录时限制它登录，
                // 更或者像腾讯视频VIP账号一样，最多只能五个人同时登录，第六个人限制登录。
                // maximumSessions：最大会话数量，设置为1表示一个用户只能有一个会话
                // expiredSessionStrategy：会话过期策略
                .maximumSessions(1)
                .expiredSessionStrategy(new MyExpiredSessionStrategy());
                // sessionManagement 也可以配置 maxSessionsPreventsLogin：boolean 值，当达到 maximumSessions 设置的最大会话个数时阻止登录。
                // 默认登出没有删除 sesson ,session 的计数依然有问题
                // .maxSessionsPreventsLogin(true);
    }

    public void formLogin(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin() // 表单提交
                .loginPage("/login.html") //自定义登录页面
                .loginProcessingUrl("/user/login") //登录访问路径，必须和表单提交接口一样
                // .defaultSuccessUrl("/admin/index")// 认证成功之后跳转的路径
                // .successForwardUrl("/index") // 认证成功之后转发的路径,必须是Post请求
                // .failureForwardUrl("/errors") // 认证失败之后转发的路径,必须是Post请求
                .successHandler(new MyAuthenticationSuccessHandler("/index.html"))
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))
                .and().authorizeRequests()//设置哪些路径可以直接访问，不需要认证
                .antMatchers("/user/login", "/error", "/login.html", "/error.html").permitAll()
                .anyRequest().authenticated()//需要认证
                .and().csrf().disable();//关闭csrf防护
    }
}
