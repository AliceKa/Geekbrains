package serverside.server;

import serverside.AuthService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BaseAuthService implements AuthService {

    private List<Entry> entryList;

    private Statement statement;
    private Connection conn;
    public BaseAuthService() {
        this.statement = null;
        this.conn = null;

        try {
            statement = Singleton.getConnection().createStatement();
            conn = Singleton.initConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        System.out.println("AuthService start");
    }

    @Override
    public void stop() {
        System.out.println("AuthService stop");

    }
    @Override
    public String getNickByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException {

        ResultSet set = statement.executeQuery("SELECT * FROM chatserver");
        while (set.next()) {
            User user = new User().userBuilder(set);
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) return user.getNick();
        }
        return null;
    }

    public void changeNick(String newNick, String oldNick) {
        try {
            statement.executeUpdate("UPDATE chatserver SET nick = '" + newNick + "' WHERE nick = '"+ oldNick + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


        /*
        for (Entry e: entryList) {
            if (e.login.equals(login) && e.password.equals(password)) {
                return e.nickname;
            }
        }
        return null;
    }

         */

    private class Entry {
        private String login;
        private String password;
        private String nickname;

        public Entry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }
}
