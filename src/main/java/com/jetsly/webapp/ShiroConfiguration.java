package main.java.com.jetsly.webapp;

import java.util.Map;
import java.util.HashMap;

import main.java.com.jetsly.webapp.shiro.CustomerRealm;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;

import org.apache.shiro.web.filter.authc.AnonymousFilter;
import main.java.com.jetsly.webapp.shiro.JWSAuthenticationFilter;

@Configuration
public class ShiroConfiguration {

  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter() {
      ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
      //shiroFilter.setLoginUrl("/account/auth");
      //shiroFilter.setSuccessUrl("/index");
      //shiroFilter.setUnauthorizedUrl("/test");
      Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
      filterChainDefinitionMapping.put("/account/**", "anon");
      filterChainDefinitionMapping.put("/user", "authcJws");
      //filterChainDefinitionMapping.put("/home", "authc,roles[guest]");
      //filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
      shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
      shiroFilter.setSecurityManager(securityManager());
      Map<String, Filter> filters = new HashMap<String, Filter>();;
      filters.put("anon", new AnonymousFilter());
      filters.put("authcJws", new JWSAuthenticationFilter());
      //filters.put("authcBasic", new BasicHttpAuthenticationFilter());
      //filters.put("noSessionCreation", new NoSessionCreationFilter());
      //filters.put("authc", new FormAuthenticationFilter());
      //filters.put("logout", new LogoutFilter());
      //filters.put("roles", new RolesAuthorizationFilter());
      //filters.put("user", new UserFilter());
      shiroFilter.setFilters(filters);
      //System.out.println("Filter size: "+shiroFilter.getFilters().size());
      return shiroFilter;
  }

  @Bean
  public SecurityManager securityManager() {
      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
      securityManager.setRealm(customerRealm());
      return securityManager;
  }

  @Bean(name = "customerRealm")
  @DependsOn("lifecycleBeanPostProcessor")
  public CustomerRealm customerRealm() {
      CustomerRealm customerRealm = new CustomerRealm();
      customerRealm.init();
      return customerRealm;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
      return new LifecycleBeanPostProcessor();
  }
}
