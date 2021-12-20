package vut.cz.bpcbdsproject3.data;

import vut.cz.bpcbdsproject3.App;
import vut.cz.bpcbdsproject3.Postgre.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.configuration.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private static final Logger logger = LoggerFactory.getLogger(LoginRepository.class);

    public LoginView getLoginView(String email) {
        try (Connection connection = DataSourceConfig.getConnection(); // opravdu je ten Vas user user a ne person?
             PreparedStatement prpstmt = connection.prepareStatement("SELECT id, email, password FROM project.\"user\" WHERE email = ?;")) {
            prpstmt.setString(1, email);
            try (ResultSet rs = prpstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToLoginView(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(String.format("Couldn't get login view!\nMeassage: %s", e.getMessage()));
        }
        return null;
    }

    private LoginView mapToLoginView(ResultSet rs) throws SQLException {
        LoginView loginView = new LoginView();
        loginView.setEmail(rs.getString("email"));
        loginView.setHashedPwd(rs.getString("password"));
        App.userId = rs.getInt("id");
        return loginView;
    }

}
