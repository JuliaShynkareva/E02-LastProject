package Controller;

import DTO.EmployeeDTO;
import Entity.*;
import Service.*;
import Validator.EmployeeValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final int ENABLED = 1;
    private static final int SET_STATUS = 2;

    @Autowired
    private ApplicationService serviceApp;
    @Autowired
    private DepartmentService serviceDepartment;
    @Autowired
    private EmployeeService serviceEmployee;
    @Autowired
    private UserService serviceUser;
    @Autowired
    private StatusService serviceStatus;

    @Autowired
    private EmployeeValidator emplValidator;

    private static final Logger LOGGER = Logger.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String adminMainPage(ModelMap model) {

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        model.addAttribute("applicationList", listAppAll);

        return "AuthPages/Admin/adminPage";
    }

    @RequestMapping(value = "/viewapp", method = RequestMethod.GET)
    public String adminView(@RequestParam("appId") int appId, ModelMap model) {

        List<EmployeeEntity> listEmplAll = serviceEmployee.getEmployees();
        List<EmployeeEntity> listEmpl = new ArrayList<EmployeeEntity>();
        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }

        DepartmentEntity depApp = app.getDepartmentId();
        //LOGGER.info("департамент " + depApp.getName());
        if(depApp != null){
            for(EmployeeEntity employee: listEmplAll){
                if(employee.getDepartmentId().getId() == depApp.getId())
                    listEmpl.add(employee);
            }
        }

        model.addAttribute("application", app);
        model.addAttribute("emplList", listEmpl);

        return "AuthPages/Admin/adminApplication";
    }

    @RequestMapping(value = "/viewapp/editempl", method = RequestMethod.GET)
    public String adminEditAppEmpl(@RequestParam("selEmpl") String emplId, @RequestParam("appId") int appId,
                               ModelMap model) {

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }

        List<EmployeeEntity> employeeListAll = serviceEmployee.getEmployees();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        for(EmployeeEntity e: employeeListAll){
            if(e.getId() == Integer.parseInt(emplId))
                employeeEntity = e;
        }

        List<StatusEntity> statusListAll = serviceStatus.getStatuses();
        StatusEntity statusEntity = new StatusEntity();
        for(StatusEntity s: statusListAll){
            if(s.getId() == SET_STATUS)
                statusEntity = s;
        }

        app.setChangeDate(new Date());
        app.setStatusId(statusEntity);
        app.setEmployeeId(employeeEntity);
        serviceApp.updateApplication(app);

        model.addAttribute("application", app);

        return "AuthPages/Admin/adminApplication";
    }

    @RequestMapping(value = "/viewapp/editdep", method = RequestMethod.GET)
    public String adminEditAppDep(@RequestParam("selDep") String depId, @RequestParam("appId") int appId,
                               ModelMap model) {

        List<EmployeeEntity> listEmplAll = serviceEmployee.getEmployees();
        List<EmployeeEntity> listEmpl = new ArrayList<EmployeeEntity>();
        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }

        List<DepartmentEntity> departmentListAll = serviceDepartment.getDepartments();
        DepartmentEntity departmentEntity = new DepartmentEntity();
        for(DepartmentEntity d: departmentListAll){
            if(d.getId() == Integer.parseInt(depId))
                departmentEntity = d;
        }

        app.setDepartmentId(departmentEntity);
        serviceApp.updateApplication(app);


        for(EmployeeEntity employee: listEmplAll){
            if(employee.getDepartmentId().getId() == departmentEntity.getId())
                listEmpl.add(employee);
        }

        model.addAttribute("application", app);
        model.addAttribute("emplList", listEmpl);

        return "AuthPages/Admin/adminApplication";
    }

    @RequestMapping(value = "/addempl", method = RequestMethod.GET)
    public String addEmpl(ModelMap model) {

        model.addAttribute("employeeNew", new EmployeeDTO());

        return "AuthPages/Admin/addEmployee";
    }

    @RequestMapping(value = "/addempl", method = RequestMethod.POST)
    public String adminAddEmpl(@ModelAttribute("employeeNew") EmployeeDTO empl, BindingResult br,
                               ModelMap model) {

        emplValidator.validate(empl, br);
        model.addAttribute("employeeNew", empl);

        List<DepartmentEntity> departmentListAll = serviceDepartment.getDepartments();
        DepartmentEntity departmentEntity = new DepartmentEntity();
        for(DepartmentEntity d: departmentListAll){
            if(d.getId() == Integer.parseInt(empl.getDepartment()))
                departmentEntity = d;
        }

        if(br.hasErrors()) {
            return "AuthPages/Admin/addEmployee";
        }
        else {
            EmployeeEntity newEmpl = new EmployeeEntity();
            newEmpl.setName(empl.getName());
            newEmpl.setLastname(empl.getLastname());
            newEmpl.setSurname(empl.getSurname());
            newEmpl.setLogin(empl.getLogin());
            newEmpl.setDepartmentId(departmentEntity);

            UserEntity newUser = new UserEntity();
            newUser.setEnabled(ENABLED);
            newUser.setUsername(empl.getLogin());
            newUser.setPassword(passwordEncoder(empl.getPassword()));
            serviceUser.createUser(newUser);
            serviceEmployee.createEmployee(newEmpl);

            return "redirect:/admin";
        }
    }

    public String passwordEncoder(String pass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pass);
        return hashedPassword;
    }
}
