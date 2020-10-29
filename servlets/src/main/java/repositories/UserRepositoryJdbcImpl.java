package repositories;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String SQL_INSERT_IMAGE = "update user set profilePhoto = ? where id = ?;";
    private DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM user";

    //language=SQL
    private final String SQL_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id= ";

    //language=SQL
    private final String SQL_INSERT = "INSERT user (userName, email, password, dop_inf) VALUES (?,?,?,?)";

    private RowMapper<User> userRowMapper = row -> User.builder()
            .userName(row.getString("userName"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .dop_inf(row.getString("dop_inf"))
            .build();

    @Override
    public User findByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            statement.setString(1, email);

            List<User> result = new ArrayList<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(userRowMapper.mapRow(resultSet));
            }

            return !result.isEmpty() ? result.get(0) : null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    @Override
    public void saveImage(String filename, Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT_IMAGE);
            statement.setString(1, filename);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);

            List<User> result = new ArrayList<>();
            resultSet = statement.executeQuery();
            result.add(userRowMapper.mapRow(resultSet));

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    @Override
    public User findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_BY_ID + id);

            List<User> result = new ArrayList<>();
            resultSet = statement.executeQuery();
            result.add(userRowMapper.mapRow(resultSet));

            return !result.isEmpty() ? result.get(0) : null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getDop_inf());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
