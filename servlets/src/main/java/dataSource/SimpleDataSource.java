package dataSource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {

    private final String URL;
    private final String JDBC_DRIVER;
    private final String USERNAME;
    private final String PASSWORD;

    public SimpleDataSource(SimpleDataSourceConfig config) {
        this.URL = config.getUrl();
        this.JDBC_DRIVER = config.getDriver();
        this.USERNAME = config.getUsername();
        this.PASSWORD = config.getPassword();
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new IllegalStateException("ignore");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new IllegalStateException("ignore");
    }
}
