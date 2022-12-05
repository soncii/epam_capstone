package com.damir.healthcare.controllers;

import com.damir.healthcare.auth.AuGroupRepository;
import com.damir.healthcare.entities.*;
import com.damir.healthcare.repositories.*;
import com.damir.healthcare.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Controller
public class AuthController {
    final Integer perPage=9;
    final
    DiseaseRepository diseaseRepository;
    final
    UserRepository userRepository;
    final
    DoctorRepository doctorRepository;
    final
    PublicServantRepository publicServantRepository;
    final
    AuGroupRepository auGroupRepository;
    final
    CountryRepository countryRepository;

    final
    DiscoveryRepository discoveryRepository;
    final
    RecordRepository recordRepository;

    final
    PasswordEncoder passwordEncoder;
    final
    EntityManager entityManager;
    final
    UserService userService;

    public AuthController(AuGroupRepository auGroupRepository, DiseaseRepository diseaseRepository, UserRepository userRepository, DoctorRepository doctorRepository, PublicServantRepository publicServantRepository, CountryRepository countryRepository, DiscoveryRepository discoveryRepository, RecordRepository recordRepository, PasswordEncoder passwordEncoder, EntityManager entityManager, UserService userService) {
        this.auGroupRepository = auGroupRepository;
        this.diseaseRepository = diseaseRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.publicServantRepository = publicServantRepository;
        this.countryRepository = countryRepository;
        this.discoveryRepository = discoveryRepository;
        this.recordRepository = recordRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logoutsuccess")
    public String logout(){
        return "redirect:login";
    }
    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("email",SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/register/doctor")
    @PreAuthorize("permitAll()")
    public String addDoctor(Model model){

        model.addAttribute("User", new User());
        model.addAttribute("Doctor", new Doctor());
        model.addAttribute("Diseases", diseaseRepository.findAll());
        model.addAttribute( "Countries", countryRepository.findAll());
        model.addAttribute("returnDis", new Disease());
        return "admin/addnewdoctor";
    }
    @GetMapping("/register/publicservant")
    @PreAuthorize("permitAll()")
    public String addS(Model model){
        model.addAttribute("User",new User());
        model.addAttribute("PublicServant", new Publicservant());
        model.addAttribute("Countries", countryRepository.findAll());
        return "admin/addnewpublicservant";
    }
    @GetMapping("/register/user")
    @PreAuthorize("permitAll()")
    public String addUser(Model model){
        model.addAttribute("User",new User());
        model.addAttribute("Countries", countryRepository.findAll());
        return "admin/addnewuser";
    }


    @PostMapping("/save/doctor")
    @PreAuthorize("permitAll()")
    @Transactional
    public String saveDoctor(@ModelAttribute("User") User user,
                             @ModelAttribute("Doctor") Doctor doctor,
                             Model model
    ){
        userService.saveDoctor(user, doctor);
        return "redirect:/";
    }
    @PostMapping("/save/publicservant")
    @PreAuthorize("permitAll()")
    public String saveUser12(@ModelAttribute("User") User user,
                             @ModelAttribute("PublicServant") Publicservant ps){
        userService.savePS(user, ps);
        return "redirect:/";
    }

    @PostMapping("/save/user")
    @Transactional
    @PreAuthorize("permitAll()")
    public String saveUser(@ModelAttribute("User") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
