package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "application")
public class ApplicationEntity  implements Serializable {

    @Id
    @Column(name = "id_application")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "comment", length = 1024)
    private String comment;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "change_date")
    private Date changeDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusEntity statusId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationEntity that = (ApplicationEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (applicationDate != null ? !applicationDate.equals(that.applicationDate) : that.applicationDate != null)
            return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (statusId != null ? !statusId.equals(that.statusId) : that.statusId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (applicationDate != null ? applicationDate.hashCode() : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public DepartmentEntity getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(DepartmentEntity departmentByDepartmentId) {
        this.departmentId = departmentByDepartmentId;
    }

    public StatusEntity getStatusId() {
        return statusId;
    }

    public void setStatusId(StatusEntity statusByStatusId) {
        this.statusId = statusByStatusId;
    }

    public EmployeeEntity getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeId = employeeByEmployeeId;
    }

    public ClientEntity getClientId() {
        return clientId;
    }

    public void setClientId(ClientEntity clientByClientId) {
        this.clientId = clientByClientId;
    }
}
