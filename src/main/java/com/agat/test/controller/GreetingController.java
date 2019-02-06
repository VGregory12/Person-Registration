package com.agat.test.controller;

import com.agat.test.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class GreetingController {

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

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting.html";
    }

//    @GetMapping("/templates/img/arrow.png")
//    public String templates(Map<String, Object> model) {
//        return "/main2";
//    }



    @GetMapping("/main2")
    public String main2(Map<String, Object> model) {
        return "main2";
    }

    @GetMapping("/opa")
    public String opa(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "opa.html";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = MessageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Message message = new Message(text, tag, user);
        MessageRepo.save(message);
        Iterable<Message> messages = MessageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/agat")
    public String agat(Map<String, Object> model) {
        Iterable<Agat> personAdd = AgatRepo.findAll();
        model.put("personAdd", personAdd);
        return "agat";
    }



    @PostMapping("/agat")
    public String add(@RequestParam Integer ID, @RequestParam String NAME,  @RequestParam String SURNAME,
                     @RequestParam Integer PHONE_NUMBER, @RequestParam Integer WORK_ID, @RequestParam Integer GENDER_ID,
                     Map<String, Object> model){
        Optional<Gender> gender = GenderRepo.findById(GENDER_ID);
       Agat agat = new Agat(ID, NAME, SURNAME, PHONE_NUMBER, WORK_ID, gender.get());
       AgatRepo.save(agat);
        Iterable<Agat> personAdd = AgatRepo.findAll();
        model.put("personAdd", personAdd);
        return "agat";
    }

    @GetMapping("/delete_person/{id}")
    public String delete(@PathVariable int id){
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
    public String filter(@RequestParam String filter, Map<String, Object> model){

        Iterable<Agat> personAdd;
        if(filter != null && !filter.isEmpty()){
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
    public String agat2Person (Map<String, Object> model) {
        Iterable<Agat2Person> agat2PersonTable = Agat2PersonRepo.findAll();
        model.put("agat2PersonTable", agat2PersonTable);
        return "agat2Person";
    }

    @PostMapping("/agat2Person")
    public String add(@RequestParam Integer ID, @RequestParam Integer PID, @RequestParam String SURNAME, @RequestParam String NAME, @RequestParam String PATRONYMIC,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date BIRTHDAY, @RequestParam  String CAUSE_DEATH, @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date DATE_DEATH,
                      @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date DATE_ENTER,
                      @RequestParam Integer USER_ID, @RequestParam Integer IDENTIFIER,
                      Map<String, Object> model){
//        Optional<Gender> gender = GenderRepo.findById(GENDER_ID);
//
        Agat2Person Agat2Person = new Agat2Person(ID, PID, SURNAME, NAME, PATRONYMIC, BIRTHDAY, CAUSE_DEATH, DATE_DEATH, DATE_ENTER, USER_ID, IDENTIFIER);
        Agat2PersonRepo.save(Agat2Person);
        Iterable<Agat2Person> Agat2PersonTable = Agat2PersonRepo.findAll();
        model.put("Agat2PersonTable", Agat2PersonTable);
        return "redirect:/agat2Person";
    }

    @GetMapping("/delete_agat2Person/{id}")
    public String deletePerson(@PathVariable Integer id){
        Agat2PersonRepo.deleteById(id);
        return "redirect:/agat2Person";
    }

    @PostMapping("filterAgat2Person")
    public String filterAgat2Person(@RequestParam String filterAgat2PersonFilter, Map<String, Object> model){

        Iterable<Agat2Person> agat2PersonTable;
        if(filterAgat2PersonFilter != null && !filterAgat2PersonFilter.isEmpty()){
            agat2PersonTable = Agat2PersonFilter.findBySurname(filterAgat2PersonFilter);
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
    public String add(@RequestParam Integer PID, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ENTRY_DATE,
                      @RequestParam Integer USER_ID,
                      Map<String, Object> model){
//        Optional<Agat2Person> agat2Person = Agat2PersonRepo.findById(PID);
        Agat2IdPerson Agat2IdPerson = new Agat2IdPerson(PID, ENTRY_DATE, USER_ID
//                , agat2Person.get()
        );
        Agat2IdPersonRepo.save(Agat2IdPerson);
        Iterable<Agat2IdPerson> agat2IdPersonTable = Agat2IdPersonRepo.findAll();
        model.put("agat2IdPersonTable", agat2IdPersonTable);
        return "agat2IdPerson";
    }

    @GetMapping("/delete_agat2IdPerson/{pid}")
    public String deleteAgat2IdPerson(@PathVariable int pid){
        Agat2IdPersonRepo.deleteById(pid);
        return "redirect:/agat2IdPerson";
    }


    @PostMapping("filterAgat2IdPerson")
    public String filterAgat2IdPerson(@RequestParam Integer filter, Map<String, Object> model){

        Iterable<Agat2IdPerson> agat2IdPersonTable;
        if(filter != null){
            agat2IdPersonTable = Agat2Id_PersonFilter.findByPid(filter);
            model.put("agat2IdPersonTable", agat2IdPersonTable);
            return "agat2IdPerson";
        } else {
            agat2IdPersonTable = Agat2IdPersonRepo.findAll();
            model.put("agat2IdPersonTable", agat2IdPersonTable);
            return "redirect:/agat2IdPerson";
        }
    }

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
                      Map<String, Object> model){
        Optional<Agat2TypeAddress> agat2TypeAddress = Agat2TypeAddressRepo.findById(TYPE_ADDRESS_ID);
        Agat2Address agat2Address = new Agat2Address(ID, PID, TYPE_ADDRESS_ID, LOCALITY, STREET, HOUSE, BODY, APARTMENT, agat2TypeAddress.get());
        Agat2AddressRepo.save(agat2Address);
        Iterable<Agat2Address> agat2AddressTable = Agat2AddressRepo.findAll();
        model.put("agat2AddressTable", agat2AddressTable);
        return "agat2Address";
    }

    @GetMapping("/delete_agat2Address/{id}")
    public String deleteAgat2Address(@PathVariable int id){
        Agat2AddressRepo.deleteById(id);
        return "redirect:/agat2Address";
    }

    @PostMapping("filterAgat2Address")
    public String filterAgat2Address(@RequestParam Integer filterAgat2AddressFilter, Map<String, Object> model){

        Iterable<Agat2Address> agat2AddressTable;
        if(filterAgat2AddressFilter != null){
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



    @PostMapping("/agat2Document")
    public String add(@RequestParam Integer ID,
                      @RequestParam Integer PID,
                      @RequestParam Integer DOC_NUMBER,
                      @RequestParam String DOC_TYPE,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date DATE_RECEIVING,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ENTRY_DATE,
                      @RequestParam Integer USER_ID,
                      Map<String, Object> model){

        Agat2Document agat2Document = new Agat2Document(ID, PID, DOC_NUMBER, DOC_TYPE, DATE_RECEIVING, ENTRY_DATE, USER_ID);
        Agat2DocumentRepo.save(agat2Document);
        Iterable<Agat2Document> agat2DocumentTable = Agat2DocumentRepo.findAll();
        model.put("agat2DocumentTable", agat2DocumentTable);
        return "agat2Document";
    }

    @GetMapping("/delete_agat2Document/{id}")
    public String deleteAgat2Document(@PathVariable int id){
        Agat2DocumentRepo.deleteById(id);
        return "redirect:/agat2Document";
    }

    @PostMapping("filterAgat2Document")
    public String filterAgat2Document(@RequestParam Integer filterAgat2DocumentFilter, Map<String, Object> model){

        Iterable<Agat2Document> agat2DocumentTable;
        if(filterAgat2DocumentFilter != null){
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
    public String agat2Users(@RequestParam(required = false, defaultValue = "") String filterAgat2UsersFilter, Model model) {
        Iterable<Agat2Users> agat2UsersTable = Agat2UsersRepo.findAll();

        if(filterAgat2UsersFilter != null && !filterAgat2UsersFilter.isEmpty()){
            agat2UsersTable = Agat2UsersRepo.findByUsername(filterAgat2UsersFilter);

        } else {
            agat2UsersTable = Agat2UsersRepo.findAll();

        }
        model.addAttribute("agat2UsersTable", agat2UsersTable);
        model.addAttribute("filterAgat2UsersFilter", filterAgat2UsersFilter);

        return "agat2Users.html";
    }



    @PostMapping("/agat2Users")
    public String add(@RequestParam String USER_NAME,
                      @RequestParam Integer USER_ID,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ENTRY_DATE,
                      Map<String, Object> model){

        Agat2Users agat2Users = new Agat2Users(USER_NAME, USER_ID, ENTRY_DATE);
        Agat2UsersRepo.save(agat2Users);
        Iterable<Agat2Users> agat2UsersTable = Agat2UsersRepo.findAll();
        model.put("agat2UsersTable", agat2UsersTable);
        return "agat2Users.html";
    }

    @GetMapping("/delete_agat2Users/{user_name}")
    public String deleteAgat2Users(@PathVariable String user_name){
        Agat2UsersRepo.deleteById(user_name);
        return "redirect:/agat2Users";
    }

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

}
