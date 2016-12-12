package Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class UserRoleEntity implements Serializable {

    @Id
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "authority", length = 50)
    private String authority;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserEntity user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
