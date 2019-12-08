package ru.stc21.internal;

import org.hsqldb.jdbc.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedTemplate;
    private final DataSource dataSource;

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        final List<User> users = new ArrayList<>();
        template.query("select * from users", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setLogin(resultSet.getString(1));
                user.setPassword(resultSet.getString("password"));
                user.setDate(resultSet.getString(3));
            }
        });
        return users;
    }

    public User getUser() {
        return template.execute(new ConnectionCallback<User>() {

            @Override
            public User doInConnection(Connection connection) throws SQLException, DataAccessException {
                PreparedStatement statement = connection.prepareStatement("select top 1 * from users ");
                ResultSet resultSet = statement.executeQuery();
                User user = new User();
                user.setLogin(resultSet.getString(1));
                user.setPassword(resultSet.getString("password"));
                user.setDate(resultSet.getString(3));
                return user;
            }
        });
    }

    public User getNamedUser(String login) {
        return namedTemplate.
                queryForObject("select * from users where login = :login",
                        Collections.singletonMap("login", login), User.class);
    }

}
