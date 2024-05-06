package oasis.model.domain;

public class User {
    private String idUser;
    private String password;
    private boolean  logged = false;
    public User(String idUser, String password) {
        this.idUser = idUser;
        this.password = password;
        this.logged = false;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", password='" + password + '\'' +
                ", logged=" + logged +
                '}';
    }
}
