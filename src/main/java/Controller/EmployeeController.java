package Controller;

import DTO.AppByEmplDTO;
import Entity.ApplicationEntity;
import Entity.EmployeeEntity;
import Entity.StatusEntity;
import Service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final int SYS = 1;
    private static final int WEB = 2;
    private static final int SAP = 3;
    private static final int QA = 4;

    private static final String NAME_WEB = "Web";
    private static final String NAME_SYS = "Sys";
    private static final String NAME_SAP = "SAP";
    private static final String NAME_QA = "QA";

    private static final String DIR_NAME_SYS = "SystemDevelopment";
    private static final String DIR_NAME_WEB = "WebDevelopment";
    private static final String DIR_NAME_SAP = "SAPConsulting";
    private static final String DIR_NAME_QA = "QualityAssurance";

    private static final int THAT_EMPL = 1;
    private static final int NO_EMPL = 0;
    private static final int NOT_THAT_EMPL = 2;

    private static final int SET_STATUS_WORK = 2;
    private static final int SET_STATUS_COMPLETE = 3;

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

    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @RequestMapping(value = "/{nameDep}", method = RequestMethod.GET)
    public String QAPage(@PathVariable String nameDep, Principal user, ModelMap model) {

        setModelAttr(user, model, checkDep(nameDep));
        String strRedirect = "AuthPages/Department/" + checkDepForRedirect(nameDep) + "/" + nameDep + "Page";
        return strRedirect;
    }

    @RequestMapping(value = "/{nameDep}/viewapp", method = RequestMethod.GET)
    public String employeeView(@RequestParam("appId") int appId,
                               @RequestParam("emplId") int emplId,
                               @PathVariable String nameDep, Principal user, ModelMap model) {

        setModelUser(user, model);

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }

        model.addAttribute("application", app);

        if(app.getEmployeeId() != null) {
            if (app.getEmployeeId().getId() == emplId) {
                model.addAttribute("isThatEmpl", THAT_EMPL);
            }else
                model.addAttribute("isThatEmpl", NOT_THAT_EMPL);
        }else
            model.addAttribute("isThatEmpl", NO_EMPL);

        String strRedirect = "AuthPages/Department/" + checkDepForRedirect(nameDep) + "/viewApp" + nameDep;

        return strRedirect;
    }

    @RequestMapping(value = "/{nameDep}/viewapp/editempl", method = RequestMethod.GET)
    public String employeeViewEditEmpl(@RequestParam("appId") int appId,
                               @RequestParam("emplId") int emplId,
                               @PathVariable String nameDep, Principal user, ModelMap model) {

        setModelUser(user, model);

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }

        List<EmployeeEntity> listEmplAll = serviceEmployee.getEmployees();
        EmployeeEntity empl = new EmployeeEntity();

        for(EmployeeEntity e: listEmplAll){
            if(e.getId() == emplId)
                empl = e;
        }

        List<StatusEntity> statusListAll = serviceStatus.getStatuses();
        StatusEntity statusEntity = new StatusEntity();
        for(StatusEntity s: statusListAll){
            if(s.getId() == SET_STATUS_WORK)
                statusEntity = s;
        }

        if(app.getEmployeeId() == null) {
            app.setEmployeeId(empl);
            app.setChangeDate(new Date());
            app.setStatusId(statusEntity);
            serviceApp.updateApplication(app);

            model.addAttribute("isThatEmpl", THAT_EMPL);
        } else
            model.addAttribute("isThatEmpl", NO_EMPL);

        model.addAttribute("application", app);

        String strRedirect = "AuthPages/Department/" + checkDepForRedirect(nameDep) + "/viewApp" + nameDep;

        return strRedirect;
    }

    @RequestMapping(value = "/{nameDep}/viewapp/editapp", method = RequestMethod.POST)
    public String employeeViewEditApp(@PathVariable String nameDep,
                                      @ModelAttribute("appEdit") AppByEmplDTO appEdit,
                                      Principal user, ModelMap model) {

        setModelUser(user, model);

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appEdit.getId())
                app = application;
        }

        List<StatusEntity> statusListAll = serviceStatus.getStatuses();
        StatusEntity statusEntity = new StatusEntity();
        for(StatusEntity s: statusListAll){
            if(s.getId() == Integer.parseInt(appEdit.getStatus()))
                statusEntity = s;
        }

        app.setComment(appEdit.getComment());
        app.setChangeDate(new Date());
        app.setStatusId(statusEntity);
        serviceApp.updateApplication(app);

        model.addAttribute("isThatEmpl", THAT_EMPL);
        model.addAttribute("application", app);

        String strRedirect = "AuthPages/Department/" + checkDepForRedirect(nameDep) + "/viewApp" + nameDep;

        return strRedirect;
    }

    public void setModelAttr(Principal user, ModelMap model, int numDep){

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        List<ApplicationEntity> listAppDep = new ArrayList<ApplicationEntity>();

        for(ApplicationEntity app: listAppAll){
            if(app.getDepartmentId() != null) {
                if (app.getDepartmentId().getId() == numDep)
                    listAppDep.add(app);
            }
        }

        model.addAttribute("applicationList", listAppDep);
        setModelUser(user, model);
    }

    public void setModelUser(Principal user, ModelMap model){
        String login = user.getName();
        List<EmployeeEntity> emplListAll = serviceEmployee.getEmployees();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        for(EmployeeEntity e: emplListAll){
            if(e.getLogin().equals(login))
                employeeEntity = e;
        }
        model.addAttribute("employee", employeeEntity);
    }

    public int checkDep(String name){
        if(name.equals(NAME_QA)) return QA;
        if(name.equals(NAME_SAP)) return SAP;
        if(name.equals(NAME_SYS)) return SYS;
        if(name.equals(NAME_WEB)) return WEB;
        return 0;
    }

    public String checkDepForRedirect(String name){
        if(name.equals(NAME_QA)) return DIR_NAME_QA;
        if(name.equals(NAME_SAP)) return DIR_NAME_SAP;
        if(name.equals(NAME_SYS)) return DIR_NAME_SYS;
        if(name.equals(NAME_WEB)) return DIR_NAME_WEB;
        return "";
    }
}
