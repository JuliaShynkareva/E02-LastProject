package DTO;

public class ClientDTO {

    private String name;

    private String lastname;

    private String surname;

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientDTO() {
    }

    public ClientDTO(String name, String lastname, String surname, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.lastname = login;
        this.surname = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
