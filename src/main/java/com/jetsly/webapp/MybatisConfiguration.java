package main.java.com.jetsly.webapp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="main.java.com.jetsly.webapp.persistence")
public class MybatisConfiguration {
	    @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
					sqlSessionFactoryBean.setDataSource(dataSource);

					PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

					sqlSessionFactoryBean.setMapperLocations(resolver
							.getResources("classpath:/mapper/*.xml"));

	        return sqlSessionFactoryBean.getObject();
	    }
}
