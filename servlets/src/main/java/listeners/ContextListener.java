package listeners;

import dataSource.SimpleDataSource;
import dataSource.SimpleDataSourceConfig;
import repositories.UserRepositoryJdbcImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        SimpleDataSourceConfig simpleConfig = new SimpleDataSourceConfig();

        simpleConfig.setUrl("jdbc:mysql://localhost:3306/mashina_ru?serverTimezone=UTC");
        simpleConfig.setDriver("com.mysql.jdbc.Driver");
        simpleConfig.setUsername("root");
        simpleConfig.setPassword("Кщще");

        SimpleDataSource dataSource = new SimpleDataSource(simpleConfig);

        servletContextEvent.getServletContext().setAttribute("datasource", dataSource);

        UserRepositoryJdbcImpl userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserServiceImpl(userRepository);
        servletContextEvent.getServletContext().setAttribute("userService", userService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("datasource", null);
    }

}

