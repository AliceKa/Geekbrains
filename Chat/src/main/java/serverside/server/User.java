package serverside.server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String login;
    private String password;
    private String nick;
    private String fileName;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getFileName() { return fileName; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public User userBuilder(ResultSet set) {
        try {
            this.id = set.getInt(1);
            this.login = set.getString(2);
            this.password = set.getString(3);
            this.nick = set.getString(4);
            this.fileName = set.getString(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this;
    }
}
