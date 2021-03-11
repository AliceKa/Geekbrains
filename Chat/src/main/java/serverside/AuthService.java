package serverside;

import java.sql.SQLException;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException;
    void changeNick(String newNick, String oldNick);
}
