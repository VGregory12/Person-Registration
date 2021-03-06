package com.agat.test.controller;

import com.agat.test.domain.*;

import com.agat.test.repos.TestRepo;
import com.agat.test.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class GreetingController {

    private static final Logger log = Logger.getLogger(GreetingController.class);

    @Autowired
    private com.agat.test.repos.MessageRepo MessageRepo;

    @Autowired
    private com.agat.test.repos.AgatRepo AgatRepo;

    @Autowired
    private com.agat.test.repos.GenderRepo GenderRepo;

    @Autowired
    private com.agat.test.repos.We We;

    @Autowired
    private com.agat.test.repos.Agat2PersonRepo Agat2PersonRepo;

    @Autowired
    private com.agat.test.repos.Agat2PersonFilter Agat2PersonFilter;

    @Autowired
    private com.agat.test.repos.Agat2IdPersonRepo Agat2IdPersonRepo;

    @Autowired
    private com.agat.test.repos.Agat2Id_PersonFilter Agat2Id_PersonFilter;

    @Autowired
    private com.agat.test.repos.Agat2AddressRepo Agat2AddressRepo;

    @Autowired
    private com.agat.test.repos.Agat2TypeAddressRepo Agat2TypeAddressRepo;

    @Autowired
    private com.agat.test.repos.Agat2DocumentRepo Agat2DocumentRepo;

    @Autowired
    private com.agat.test.repos.Agat2UsersRepo Agat2UsersRepo;

    @Autowired
    private com.agat.test.repos.Agat2SearchFilter Agat2SearchFilter;

    @Autowired
    private com.agat.test.repos.UserRepo UserRepo;

    @Autowired
    private com.agat.test.repos.Agat2HistoryRepo Agat2HistoryRepo;

    @Autowired
    private com.agat.test.repos.Agat2HistoryDocumentRepo Agat2HistoryDocumentRepo;

    @Autowired
    private com.agat.test.repos.Agat2HistoryAddressRepo Agat2HistoryAddressRepo;

    @Autowired
    private com.agat.test.repos.Agat2MetricsRepo Agat2MetricsRepo;

    @Autowired
    private com.agat.test.repos.Agat2TypeMetricsRepo Agat2TypeMetricsRepo;
    @Autowired
    private com.agat.test.repos.TestRepo TestRepo;


    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting.html";
    }

//    @GetMapping("/templates/img/arrow.png")
//    public String templates(Map<String, Object> model) {
//        return "/main2";
//    }


    @GetMapping("/main2")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String main2(Map<String, Object> model) {
        return "main2";
    }

    //    /////////// MAIN3//


    @GetMapping("/main3")
    public String main3(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", user);
        model.addAttribute("length", user.getRoles().size() - 1);
        model.addAttribute("isAdmin", user.isAdmin());
        return "main3";
    }

    @GetMapping("/main4")
    public String main4(Map<String, Object> model) {
        Iterable<Test> TestTable = TestRepo.findAll();
        model.put("TestTable", TestTable);
        return "main4";
    }

    @PostMapping("/main4")
    public String main4add(@RequestParam Integer ID,
                           @RequestParam String NAME,
                           Map<String, Object> model) {
        Test test = new Test(ID, NAME);
        TestRepo.save(test);
        Iterable<Test> TestTable = TestRepo.findAll();
        model.put("TestTable", TestTable);
        return "main4";
    }

    @GetMapping("/opa")
    public String opa(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "opa.html";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user, Map<String, Object> model) {
        Iterable<Message> messages = MessageRepo.findAll();
        model.put("messages", messages);
        return "main.html";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
        MessageRepo.save(message);
        Iterable<Message> messages = MessageRepo.findAll();
        model.put("messages", messages);
        return "main.html";
    }

    @GetMapping("/agat")
    public String agat(Map<String, Object> model) {
        Iterable<Agat> personAdd = AgatRepo.findAll();
        model.put("personAdd", personAdd);
        return "agat";
    }


    @PostMapping("/agat")
    public String add(@RequestParam Integer ID, @RequestParam String NAME, @RequestParam String SURNAME,
                      @RequestParam Integer PHONE_NUMBER, @RequestParam Integer WORK_ID, @RequestParam Integer GENDER_ID,
                      Map<String, Object> model) {
        Optional<Gender> gender = GenderRepo.findById(GENDER_ID);
        Agat agat = new Agat(ID, NAME, SURNAME, PHONE_NUMBER, WORK_ID, gender.get());
        AgatRepo.save(agat);
        Iterable<Agat> personAdd = AgatRepo.findAll();
        model.put("personAdd", personAdd);
        return "agat";
    }

    @GetMapping("/delete_person/{id}")
    public String delete(@PathVariable int id) {
        AgatRepo.deleteById(id);
        return "redirect:/agat";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model){
//        List<Message> messages = MessageRepo.findByTag(filter);
//        model.put("messages", messages);
//        return "main";
//    }

//    @PostMapping("/agat/filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model){
//        List<Agat> personAdd = We.findByName(filter);
//
//        model.put("personAdd", personAdd);
//        return "agat/filter";
//    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<Agat> personAdd;
        if (filter != null && !filter.isEmpty()) {
            personAdd = We.findBySurnameOrName(filter, filter);
            model.put("personAdd", personAdd);
            return "agat";
        } else {
            personAdd = AgatRepo.findAll();
            model.put("personAdd", personAdd);
            return "redirect:/agat";
        }
    }

    /////////////////PERSON////////////////
    @GetMapping("/agat2Person")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String agat2Person(Map<String, Object> model) {
        Iterable<Agat2Person> agat2PersonTable = Agat2PersonRepo.findAll();
        model.put("agat2PersonTable", agat2PersonTable);
        return "agat2Person";
    }

    @PostMapping("/agat2Person")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String add( @RequestParam Integer PID, @RequestParam String SURNAME, @RequestParam String NAME, @RequestParam String PATRONYMIC,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date BIRTHDAY, @RequestParam String CAUSE_DEATH, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date DATE_DEATH,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date DATE_ENTER,
                      @RequestParam Integer USER_ID, @RequestParam String IDENTIFIER,
                      @RequestParam Integer TYPE_METRICS_ID,
                      Map<String, Object> model) {
//        Optional<Gender> gender = GenderRepo.findById(GENDER_ID);
//

        Agat2Person Agat2Person = new Agat2Person(PID, SURNAME, NAME, PATRONYMIC, BIRTHDAY, CAUSE_DEATH, DATE_DEATH, DATE_ENTER, USER_ID, IDENTIFIER);
        Agat2PersonRepo.save(Agat2Person);
        Iterable<Agat2Person> Agat2PersonTable = Agat2PersonRepo.findAll();
        model.put("Agat2PersonTable", Agat2PersonTable);
        return "redirect:/agat2Person";
    }

    @GetMapping("/delete_agat2Person/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePerson(@PathVariable Integer id) {
        Agat2PersonRepo.deleteById(id);
        return "redirect:/agat2Person";
    }

    @PostMapping("filterAgat2Person")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String filterAgat2Person(@RequestParam String filterAgat2PersonFilter, Map<String, Object> model) {

        Iterable<Agat2Person> agat2PersonTable;
        if (filterAgat2PersonFilter != null && !filterAgat2PersonFilter.isEmpty()) {
            agat2PersonTable = Agat2PersonFilter.findByNameLike(filterAgat2PersonFilter + "%");
            model.put("agat2PersonTable", agat2PersonTable);
            return "agat2Person";
        } else {
            agat2PersonTable = Agat2PersonFilter.findAll();
            model.put("agat2PersonTable", agat2PersonTable);
            return "redirect:/agat2Person";
        }
    }

    ////////////////AGAT2 ID_PERSON//////////////////


    @GetMapping("/agat2IdPerson")
    public String agat2IdPerson(Map<String, Object> model) {
        Iterable<Agat2IdPerson> agat2IdPersonTable = Agat2IdPersonRepo.findAll();
        model.put("agat2IdPersonTable", agat2IdPersonTable);
        return "agat2IdPerson";
    }


    @PostMapping("/agat2IdPerson")
    public String add(@RequestParam Integer PID, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ENTRY_DATE,
                      @RequestParam Integer USER_ID,
                      Map<String, Object> model) {
//        Optional<Agat2Person> agat2Person = Agat2PersonRepo.findById(PID);
        Agat2IdPerson Agat2IdPerson = new Agat2IdPerson(ENTRY_DATE, USER_ID
//                , agat2Person.get()
        );
        Agat2IdPersonRepo.save(Agat2IdPerson);
        Iterable<Agat2IdPerson> agat2IdPersonTable = Agat2IdPersonRepo.findAll();
        model.put("agat2IdPersonTable", agat2IdPersonTable);
        return "agat2IdPerson";
    }

    @GetMapping("/delete_agat2IdPerson/{pid}")
    public String deleteAgat2IdPerson(@PathVariable int pid) {
        Agat2IdPersonRepo.deleteById(pid);
        return "redirect:/agat2IdPerson";
    }


//    @PostMapping("filterAgat2IdPerson")
//    public String filterAgat2IdPerson(@RequestParam Integer filter, Map<String, Object> model) {
//
//        Iterable<Agat2IdPerson> agat2IdPersonTable;
//        if (filter != null) {
//            agat2IdPersonTable = Agat2Id_PersonFilter.findByPid(filter);
//            model.put("agat2IdPersonTable", agat2IdPersonTable);
//            return "agat2IdPerson";
//        } else {
//            agat2IdPersonTable = Agat2IdPersonRepo.findAll();
//            model.put("agat2IdPersonTable", agat2IdPersonTable);
//            return "redirect:/agat2IdPerson";
//        }
//    }

    ////////////AGAT2ADDRESS///////////////

    @GetMapping("/agat2Address")
    public String agat2Address(Map<String, Object> model) {
        Iterable<Agat2Address> agat2AddressTable = Agat2AddressRepo.findAll();
        model.put("agat2AddressTable", agat2AddressTable);
        return "agat2Address";
    }


    @PostMapping("/agat2Address")
    public String add(@RequestParam Integer ID, @RequestParam Integer PID,
                      @RequestParam String LOCALITY, @RequestParam String STREET, @RequestParam Integer HOUSE,
                      @RequestParam Integer BODY, @RequestParam Integer APARTMENT, @RequestParam Integer TYPE_ADDRESS_ID,
                      Map<String, Object> model) {
        Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID);
        Agat2Address agat2Address = new Agat2Address(PID, TYPE_ADDRESS_ID, LOCALITY, STREET, HOUSE, BODY, APARTMENT, agat2TypeAddress.get());
        Agat2AddressRepo.save(agat2Address);
        Iterable<Agat2Address> agat2AddressTable = Agat2AddressRepo.findAll();
        model.put("agat2AddressTable", agat2AddressTable);
        return "agat2Address";
    }

    @GetMapping("/delete_agat2Address/{id}")
    public String deleteAgat2Address(@PathVariable int id) {
        Agat2AddressRepo.deleteById(id);
        return "redirect:/agat2Address";
    }

    @PostMapping("filterAgat2Address")
    public String filterAgat2Address(@RequestParam Integer filterAgat2AddressFilter, Map<String, Object> model) {

        Iterable<Agat2Address> agat2AddressTable;
        if (filterAgat2AddressFilter != null) {
            agat2AddressTable = Agat2AddressRepo.findByPid(filterAgat2AddressFilter);
            model.put("agat2AddressTable", agat2AddressTable);
            return "agat2Address";
        } else {
            agat2AddressTable = Agat2AddressRepo.findAll();
            model.put("agat2AddressTable", agat2AddressTable);
            return "redirect:/agat2Address";
        }
    }


    ////////////Document///////////////////


    @GetMapping("/agat2Document")
    public String agat2Document(Map<String, Object> model) {
        Iterable<Agat2Document> agat2DocumentTable = Agat2DocumentRepo.findAll();
        model.put("agat2DocumentTable", agat2DocumentTable);
        return "agat2Document";
    }


//    @PostMapping("/agat2Document")
//    public String add(
//                      @RequestParam Integer PID,
//                      @RequestParam Integer DOC_NUMBER,
//                      @RequestParam String DOC_TYPE,
//                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date DATE_RECEIVING,
//                      @AuthenticationPrincipal User user,
//                      Map<String, Object> model) {
//
//        Agat2Document agat2Document = new Agat2Document(PID, DOC_NUMBER, DOC_TYPE, DATE_RECEIVING, new Date(System.currentTimeMillis()), user.getId());
//        Agat2DocumentRepo.save(agat2Document);
//        Iterable<Agat2Document> agat2DocumentTable = Agat2DocumentRepo.findAll();
//        model.put("agat2DocumentTable", agat2DocumentTable);
//        return "agat2Document";
//    }

    ///////
    @GetMapping("/agat2DocumentAdd/{pid}")
    public String agat2DocumentAdd(Map<String, Object> model, @PathVariable Integer pid) {
        Iterable<Agat2Document> agat2DocumentTable = Agat2DocumentRepo.findAll();
        model.put("agat2DocumentTable", agat2DocumentTable);
        return "agat2DocumentAdd";
    }


//    @PostMapping("/agat2DocumentAdd/{pid}")
//    public String add(@PathVariable Integer pid,
//                      @RequestParam Integer DOC_NUMBER,
//                      @RequestParam String DOC_TYPE,
//                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date DATE_RECEIVING,
//                      @AuthenticationPrincipal User user,
//                      Map<String, Object> model) {
//
//        Agat2Document agat2Document = new Agat2Document(pid, DOC_NUMBER, DOC_TYPE, DATE_RECEIVING, new Date(System.currentTimeMillis()), user.getId());
//        Agat2DocumentRepo.save(agat2Document);
//        Iterable<Agat2Document> agat2DocumentTable = Agat2DocumentRepo.findAll();
//        model.put("agat2DocumentTable", agat2DocumentTable);
//        return "agat2Document";
//    }


//    @GetMapping("/delete_agat2Document/{id}")
//    public String deleteAgat2Document(@PathVariable int id) {
//        Agat2DocumentRepo.deleteById(id);
//        return "redirect:/agat2Document";
//    }

    @PostMapping("filterAgat2Document")
    public String filterAgat2Document(@RequestParam Integer filterAgat2DocumentFilter, Map<String, Object> model) {

        Iterable<Agat2Document> agat2DocumentTable;
        if (filterAgat2DocumentFilter != null) {
            agat2DocumentTable = Agat2DocumentRepo.findByPid(filterAgat2DocumentFilter);
            model.put("agat2DocumentTable", agat2DocumentTable);
            return "agat2Document";
        } else {
            agat2DocumentTable = Agat2DocumentRepo.findAll();
            model.put("agat2DocumentTable", agat2DocumentTable);
            return "redirect:/agat2Document";
        }
    }

    //////////USERS////////////


    @GetMapping("/agat2Users")
    public String User(@RequestParam(required = false, defaultValue = "") String filterAgat2UsersFilter, Model model) {
        Iterable<User> UserTable = UserRepo.findAll();
//
//        if(filterAgat2UsersFilter != null && !filterAgat2UsersFilter.isEmpty()){
//            UserTable = UserRepo.findByUsername(filterAgat2UsersFilter);
//
//        } else {
//            UserTable = UserRepo.findAll();
//
//        }
        model.addAttribute("UserTable", UserTable);
//        model.addAttribute("filterAgat2UsersFilter", filterAgat2UsersFilter);

        return "agat2Users.html";
    }


//    @PostMapping("/agat2Users")
//    public String add(@RequestParam String USER_NAME,
//                      @RequestParam Integer USER_ID,
//                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ENTRY_DATE,
//                      Map<String, Object> model){
//
//        Agat2Users agat2Users = new Agat2Users(USER_NAME, USER_ID, ENTRY_DATE);
//        Agat2UsersRepo.save(agat2Users);
//        Iterable<Agat2Users> agat2UsersTable = Agat2UsersRepo.findAll();
//        model.put("agat2UsersTable", agat2UsersTable);
//        return "agat2Users.html";
//    }

//    @GetMapping("/delete_agat2Users/{user_name}")
//    public String deleteAgat2Users(@PathVariable String user_name){
//        Agat2UsersRepo.deleteById(user_name);
//        return "redirect:/agat2Users";
//    }

//    @PostMapping("filterAgat2Users")
//    public String filterAgat2Users(@RequestParam String filterAgat2UsersFilter, Map<String, Object> model){
//
//        Iterable<Agat2Users> agat2UsersTable;
//        if(filterAgat2UsersFilter != null && !filterAgat2UsersFilter.isEmpty()){
//            agat2UsersTable = Agat2UsersRepo.findByUsername(filterAgat2UsersFilter);
//            model.put("agat2UsersTable", agat2UsersTable);
//            return "agat2Users.html";
//        } else {
//            agat2UsersTable = Agat2UsersRepo.findAll();
//            model.put("agat2UsersTable", agat2UsersTable);
//            return "redirect:/agat2Users";
//        }
//    }


    ///// Person registration
    @GetMapping("/agat2Registration")
    public String agat2Registration(Map<String, Object> model) {
        Iterable<Agat2Users> agat2UsersTable = Agat2UsersRepo.findAll();
        Iterable<Agat2Person> agat2PersonTable = Agat2PersonRepo.findAll();
        model.put("agat2PersonTable", agat2PersonTable);
        model.put("agat2UsersTable", agat2UsersTable);
        model.put("x", getRandomIntegerBetweenRange(5000, 1000000));
        return "agat2Registration";
    }


    @PostMapping("/agat2Registration")
    public String add2(
            @RequestParam Integer PID,
            @RequestParam String SURNAME,
            @RequestParam String NAME,
            @RequestParam String PATRONYMIC,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date BIRTHDAY,
            @RequestParam String IDENTIFIER,
            @RequestParam List<Integer> DOC_NUMBER,
            @RequestParam List<String> DOC_TYPE,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> DATE_RECEIVING,
            @RequestParam List<Integer> TYPE_ADDRESS_ID,
            @RequestParam List<String> LOCALITY,
            @RequestParam List<String> STREET,
            @RequestParam List<Integer> HOUSE,
            @RequestParam List<Integer> BODY,
            @RequestParam List<Integer> APARTMENT,
            @RequestParam List<Integer> TYPE_METRICS_ID,
            @AuthenticationPrincipal User user,
            Map<String, Object> model) {

        Agat2Person ier = Agat2PersonFilter.findByIdentifier(IDENTIFIER);
        if( ier != null){
            model.put("message", "НЕВЕРНО ВВЕДЕН ИДЕНТИФИКАТОР!");
            return "agat2Registration.html";
        }

//        Agat2HistoryRepo.setCustomerName();
        Agat2IdPerson agat2IdPerson = new Agat2IdPerson(PID, new Date(System.currentTimeMillis()), user.getId());
        Agat2IdPersonRepo.save(agat2IdPerson);

        for (int i = 0; i < TYPE_METRICS_ID.size(); i++) {
            Optional<Agat2TypeMetrics> agat2TypeMetrics = Agat2TypeMetricsRepo.findById(TYPE_METRICS_ID.get(i));
            Agat2Metrics agat2Metrics = new Agat2Metrics(agat2IdPerson.getPid(), TYPE_METRICS_ID.get(i), agat2TypeMetrics.get());
            Agat2MetricsRepo.save(agat2Metrics);
        }

        Agat2Person agat2Person = new Agat2Person(agat2IdPerson.getPid(), SURNAME, NAME, PATRONYMIC, BIRTHDAY, new Date(System.currentTimeMillis()), user.getId(), IDENTIFIER);
        Agat2PersonRepo.save(agat2Person);
        for (int i = 0; i < DOC_NUMBER.size(); i++) {
            Agat2Document agat2Document = new Agat2Document(agat2IdPerson.getPid(), DOC_NUMBER.get(i), DOC_TYPE.get(i), DATE_RECEIVING.get(i), new Date(System.currentTimeMillis()), user.getId());
            Agat2DocumentRepo.save(agat2Document);
        }

        for (int i = 0; i < BODY.size(); i++) {
            Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID.get(i));
            Agat2Address agat2Address = new Agat2Address(
                    agat2IdPerson.getPid(),
                    TYPE_ADDRESS_ID.get(i),
                    LOCALITY.get(i),
                    STREET.get(i),
                    HOUSE.get(i),
                    BODY.get(i),
                    APARTMENT.get(i),
                    agat2TypeAddress.get());
            Agat2AddressRepo.save(agat2Address);
        }

        Agat2History agat2History = new Agat2History(
                PID,
                SURNAME,
                NAME,
                PATRONYMIC,
                BIRTHDAY,
                IDENTIFIER,
                new Date(System.currentTimeMillis()),
                user.getId(),
                true);
        Agat2HistoryRepo.save(agat2History);

        for (int i = 0; i < DOC_NUMBER.size(); i++) {
            Agat2HistoryDocument agat2HistoryDocument = new Agat2HistoryDocument(
                    agat2History.getId_key(),
                    agat2IdPerson.getPid(),
                    DOC_NUMBER.get(i),
                    DOC_TYPE.get(i),
                    DATE_RECEIVING.get(i));
            Agat2HistoryDocumentRepo.save(agat2HistoryDocument);
        }

        for (int i = 0; i < BODY.size(); i++) {
            Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID.get(i));
            Agat2HistoryAddress agat2HistoryAddress = new Agat2HistoryAddress(
                    agat2History.getId_key(),
                    agat2IdPerson.getPid(),
                    TYPE_ADDRESS_ID.get(i),
                    LOCALITY.get(i),
                    STREET.get(i),
                    HOUSE.get(i),
                    BODY.get(i),
                    APARTMENT.get(i),
                    agat2TypeAddress.get());
            Agat2HistoryAddressRepo.save(agat2HistoryAddress);
        }

        log.info("Регистрация нового лица ПОЛЬЗОВАТЕЛЕМ "+ user.getSurname() + " " + user.getName() );
        log.info("добавление лица " + SURNAME + " " + NAME + " " + PATRONYMIC + " " + IDENTIFIER);

        return "agat2Registration.html";
    }

    ////////EDIT////////////

    @GetMapping("/agat2Edit/{pid}")
    public String agat2Edit(Map<String, Object> model, @PathVariable Integer pid) {
        Iterable<Agat2IdPerson> idPerson = Agat2IdPersonRepo.findByPid(pid);
        model.put("agat2IdPerson", idPerson);
        return "agat2Edit";
    }


    public static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }
//    public static int generateRandomIntIntRange(int min, int max) {
//        Random r = new Random();
//        return r.nextInt((max - min) + 1) + min;
//    }


    @PostMapping("/agat2Edit/{pid}")
    public String addEdit(
            @PathVariable Integer pid,
            @RequestParam String SURNAME,
            @RequestParam String NAME,
            @RequestParam String PATRONYMIC,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date BIRTHDAY,
            @RequestParam String IDENTIFIER,
            @RequestParam List<Integer> DOC_NUMBER,
            @RequestParam List<String> DOC_TYPE,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> DATE_RECEIVING,
            @RequestParam List<Integer> TYPE_ADDRESS_ID,
            @RequestParam List<String> LOCALITY,
            @RequestParam List<String> STREET,
            @RequestParam List<Integer> HOUSE,
            @RequestParam List<Integer> BODY,
            @RequestParam List<Integer> APARTMENT,
            @RequestParam List<Integer> TYPE_METRICS_ID,
            @AuthenticationPrincipal User user,
            Map<String, Object> model) {

        Agat2IdPersonRepo.deleteByPid(pid);
        Agat2HistoryRepo.updateHistory(pid);


        Agat2IdPerson agat2IdPerson = new Agat2IdPerson(pid, new Date(System.currentTimeMillis()), user.getId());
        Agat2IdPersonRepo.save(agat2IdPerson);

        Agat2Person agat2Person = new Agat2Person(agat2IdPerson.getPid(), SURNAME, NAME, PATRONYMIC, BIRTHDAY, new Date(System.currentTimeMillis()), user.getId(), IDENTIFIER);
        Agat2PersonRepo.save(agat2Person);
        for (int i = 0; i < DOC_NUMBER.size(); i++) {
            Agat2Document agat2Document = new Agat2Document(agat2IdPerson.getPid(), DOC_NUMBER.get(i), DOC_TYPE.get(i), DATE_RECEIVING.get(i), new Date(System.currentTimeMillis()), user.getId());
            Agat2DocumentRepo.save(agat2Document);
        }

        for (int i = 0; i < BODY.size(); i++) {
            Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID.get(i));
            Agat2Address agat2Address = new Agat2Address(agat2IdPerson.getPid(), TYPE_ADDRESS_ID.get(i), LOCALITY.get(i), STREET.get(i), HOUSE.get(i), BODY.get(i), APARTMENT.get(i), agat2TypeAddress.get());
            Agat2AddressRepo.save(agat2Address);
        }
        for (int i = 0; i < TYPE_METRICS_ID.size(); i++) {
            Optional<Agat2TypeMetrics> agat2TypeMetrics = Agat2TypeMetricsRepo.findById(TYPE_METRICS_ID.get(i));
            Agat2Metrics agat2Metrics = new Agat2Metrics(agat2IdPerson.getPid(), TYPE_METRICS_ID.get(i), agat2TypeMetrics.get());
            Agat2MetricsRepo.save(agat2Metrics);
        }

        Agat2History agat2History = new Agat2History(agat2IdPerson.getPid(), agat2Person.getSurname(), agat2Person.getName(),
                agat2Person.getPatronymic(), agat2Person.getBirthday(), agat2Person.getIdentifier(),
                new Date(System.currentTimeMillis()), user.getId(), true);
        Agat2HistoryRepo.save(agat2History);
        for (int i = 0; i < DOC_NUMBER.size(); i++) {
            Agat2HistoryDocument agat2HistoryDocument = new Agat2HistoryDocument(
                    agat2History.getId_key(),
                    agat2IdPerson.getPid(),
                    DOC_NUMBER.get(i),
                    DOC_TYPE.get(i),
                    DATE_RECEIVING.get(i));
            Agat2HistoryDocumentRepo.save(agat2HistoryDocument);
        }
        for (int i = 0; i < BODY.size(); i++) {
            Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID.get(i));
            Agat2HistoryAddress agat2HistoryAddress = new Agat2HistoryAddress(
                    agat2History.getId_key(),
                    agat2IdPerson.getPid(),
                    TYPE_ADDRESS_ID.get(i),
                    LOCALITY.get(i),
                    STREET.get(i),
                    HOUSE.get(i),
                    BODY.get(i),
                    APARTMENT.get(i),
                    agat2TypeAddress.get());
            Agat2HistoryAddressRepo.save(agat2HistoryAddress);
        }
        return "redirect:/agat2Search.html";
    }


    //// SEARCH////////


    @GetMapping("/agat2Search")
    public String agat2Search(Map<String, Object> model) {
        Iterable<Agat2IdPerson> agat2SearchTable = Agat2IdPersonRepo.findAll();
        model.put("agat2SearchTable", agat2SearchTable);
        return "agat2Search";
    }

//    @GetMapping("/delete_agat2Search/{pid}")
//    public String deleteAgat2Search(@PathVariable Integer pid) {
//
//        return "redirect:/agat2Search";
//    }

    @PostMapping("filterAgat2Search")
    public String filterAgat2Search(@RequestParam String filterAgat2SearchFilter, Map<String, Object> model) {

        Iterable<Agat2IdPerson> agat2SearchTable;
        if (filterAgat2SearchFilter != null && !filterAgat2SearchFilter.isEmpty()) {
            agat2SearchTable = Agat2SearchFilter.findByAgat2PersonSurnameLikeOrAgat2PersonNameLike(filterAgat2SearchFilter + "%", filterAgat2SearchFilter + "%");
            model.put("agat2SearchTable", agat2SearchTable);
            return "agat2Search";
        } else {
            agat2SearchTable = Agat2SearchFilter.findAll();
            model.put("agat2SearchTable", agat2SearchTable);
            return "redirect:/agat2Search";
        }
    }


             //SHOWMETRICS///

    @GetMapping("/agat2ShowMetrics/{pid}")
    public String agat2ShowMetrics(Map<String, Object> model, @PathVariable Integer pid) {
        Iterable<Agat2IdPerson> agat2ShowMetricsTable = Agat2IdPersonRepo.findByPid(pid);
        model.put("agat2ShowMetricsTable", agat2ShowMetricsTable);
        return "agat2ShowMetrics.html";
    }

    //// HISTORY ////////
    @GetMapping("/agat2History")
    public String agat2History(Map<String, Object> model) {
        Iterable<Agat2History> agat2HistoryTable = Agat2HistoryRepo.findAll();
        model.put("agat2HistoryTable", agat2HistoryTable);

        return "agat2History";
    }


//
//    @PostMapping("/agat2History")
//    public String agat2HistoryAdd(
//            @RequestParam String SURNAME,
//            @RequestParam String NAME,
//            @RequestParam String PATRONYMIC,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date BIRTHDAY,
//            @RequestParam String IDENTIFIER,
//            @RequestParam Integer DOC_NUMBER,
//            @RequestParam String DOC_TYPE,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date DATE_RECEIVING,
//            @RequestParam Integer TYPE_ADDRESS_ID,
//            @RequestParam String LOCALITY,
//            @RequestParam String STREET,
//            @RequestParam Integer HOUSE,
//            @RequestParam Integer BODY,
//            @RequestParam Integer APARTMENT,
//            @AuthenticationPrincipal User user,
//            @AuthenticationPrincipal Agat2IdPerson agat2IdPerson,
//            @AuthenticationPrincipal Agat2Person agat2Person,
//            Map<String, Object> model) {
//
//        Agat2History agat2History = new Agat2History(agat2IdPerson.getPid(), agat2Person.getSurname());
//        Agat2HistoryRepo.save(agat2History);
//
////        Agat2IdPerson agat2IdPerson = new Agat2IdPerson(new Date(System.currentTimeMillis()), user.getId());
////        Agat2IdPersonRepo.save(agat2IdPerson);
////        Agat2Person agat2Person = new Agat2Person(agat2IdPerson.getPid(), SURNAME, NAME, PATRONYMIC, BIRTHDAY, new Date(System.currentTimeMillis()), user.getId(), IDENTIFIER);
////        Agat2PersonRepo.save(agat2Person);
////        Agat2Document agat2Document = new Agat2Document(agat2IdPerson.getPid(), DOC_NUMBER, DOC_TYPE, DATE_RECEIVING, new Date(System.currentTimeMillis()), user.getId());
////        Agat2DocumentRepo.save(agat2Document);
////        Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID);
////        Agat2Address agat2Address = new Agat2Address(agat2IdPerson.getPid(), TYPE_ADDRESS_ID, LOCALITY, STREET, HOUSE, BODY, APARTMENT, agat2TypeAddress.get());
////        Agat2AddressRepo.save(agat2Address);
//
//        return "agat2History.html";
//    }


}
