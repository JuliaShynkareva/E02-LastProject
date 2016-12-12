package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "department")
public class DepartmentEntity implements Serializable {

    @Id
    @Column(name = "id_department")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 40)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departmentId")
    private List<ApplicationEntity> applicationsById;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departmentId")
    private List<EmployeeEntity> employeesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplicationEntity> getApplicationsById() {
        return applicationsById;
    }

    public void setApplicationsById(List<ApplicationEntity> applicationsById) {
        this.applicationsById = applicationsById;
    }

    public List<EmployeeEntity> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(List<EmployeeEntity> employeesById) {
        this.employeesById = employeesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
