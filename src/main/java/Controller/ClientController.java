package Controller;

import DTO.ApplicationDTO;
import Entity.ApplicationEntity;
import Entity.ClientEntity;
import Entity.EmployeeEntity;
import Entity.StatusEntity;
import Service.ApplicationService;
import Service.ClientService;
import Service.StatusService;
import Validator.ApplicationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ApplicationService serviceApp;
    @Autowired
    private ClientService serviceClient;
    @Autowired
    private StatusService serviceStatus;

    @Autowired
    private ApplicationValidator appValidator;

    private static final Logger LOGGER = Logger.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String clientMainPage(Principal user, ModelMap model) {

        String login = user.getName();
        List<ClientEntity> clientListAll = serviceClient.getClients();
        ClientEntity clientEntity = new ClientEntity();
        for(ClientEntity c: clientListAll){
            if(c.getLogin().equals(login))
                clientEntity = c;
        }
        int id = clientEntity.getId();

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        List<ApplicationEntity> listAppClient = new ArrayList<ApplicationEntity>();

        for(ApplicationEntity app: listAppAll){
            if(app.getClientId().getId() == id)
                listAppClient.add(app);
        }
        model.addAttribute("applicationList", listAppClient);
        model.addAttribute("client", clientEntity);

        return "AuthPages/Client/showApplications";
    }

    @RequestMapping(value = "/viewapp", method = RequestMethod.GET)
    public String clientView(@RequestParam("appId") int appId, ModelMap model, Principal user) {

        List<ApplicationEntity> listAppAll = serviceApp.getApplications();
        ApplicationEntity app = new ApplicationEntity();

        for(ApplicationEntity application: listAppAll){
            if(application.getId() == appId)
                app = application;
        }
        model.addAttribute("application", app);

        String login = user.getName();
        List<ClientEntity> clientListAll = serviceClient.getClients();
        ClientEntity clientEntity = new ClientEntity();
        for(ClientEntity e: clientListAll){
            if(e.getLogin().equals(login))
                clientEntity = e;
        }
        model.addAttribute("client", clientEntity);

        return "AuthPages/Client/viewApplication";
    }

    @RequestMapping(value = "/addapp", method = RequestMethod.GET)
    public String addApp(Principal user, ModelMap model) {

        String login = user.getName();
        List<ClientEntity> clientListAll = serviceClient.getClients();
        ClientEntity clientEntity = new ClientEntity();
        for(ClientEntity e: clientListAll){
            if(e.getLogin().equals(login))
                clientEntity = e;
        }
        model.addAttribute("client", clientEntity);
        model.addAttribute("appNew", new ApplicationDTO());

        return "AuthPages/Client/addAplication";
    }

    @RequestMapping(value = "/addapp", method = RequestMethod.POST)
    public String clientAddApp(@ModelAttribute("appNew") ApplicationDTO app, BindingResult br,
                                ModelMap model, Principal u) {

        appValidator.validate(app, br);
        model.addAttribute("appNew", app);

        String login = u.getName();

        List<ClientEntity> clientListAll = serviceClient.getClients();
        ClientEntity clientEntity = new ClientEntity();
        for(ClientEntity e: clientListAll){
            if(e.getLogin().equals(login))
                clientEntity = e;
        }

        List<StatusEntity> statusListAll = serviceStatus.getStatuses();
        StatusEntity statusEntity = new StatusEntity();
        for(StatusEntity s: statusListAll){
            if(s.getId() == 1)
                statusEntity = s;
        }

        if(br.hasErrors()) {
            return "AuthPages/Client/addAplication";
        }
        else {
            ApplicationEntity newApp = new ApplicationEntity();
            newApp.setName(app.getName());
            newApp.setDescription(app.getDescription());
            newApp.setApplicationDate(new Date());
            newApp.setStatusId(statusEntity);
            newApp.setClientId(clientEntity);
            serviceApp.createApplication(newApp);
            return "redirect:/client";
        }
    }
}
