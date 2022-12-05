package com.damir.healthcare.controllers;

import com.damir.healthcare.DTO.UserDTO;
import com.damir.healthcare.auth.AuGroupRepository;
import com.damir.healthcare.entities.*;
import com.damir.healthcare.repositories.*;
import com.damir.healthcare.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class adminContoller {
    final
    DiscoveryRepository discoveryRepository;
    final
    Integer perPage=9;
    final
    UserRepository userRepository;
    final
    DoctorRepository doctorRepository;
    final
    PublicServantRepository publicServantRepository;
    final
    AuGroupRepository auGroupRepository;
    final
    RecordRepository recordRepository;
    final
    DiseaseRepository diseaseRepository;
    final
    CountryRepository countryRepository;

    final
    UserService userService;

    public adminContoller(DiscoveryRepository discoveryRepository, UserRepository userRepository, DoctorRepository doctorRepository, PublicServantRepository publicServantRepository, AuGroupRepository auGroupRepository, RecordRepository recordRepository, DiseaseRepository diseaseRepository, CountryRepository countryRepository, UserService userService) {
        this.discoveryRepository = discoveryRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.publicServantRepository = publicServantRepository;
        this.auGroupRepository = auGroupRepository;
        this.recordRepository = recordRepository;
        this.diseaseRepository = diseaseRepository;
        this.countryRepository = countryRepository;
        this.userService = userService;
    }

    @GetMapping("/see/countries/{page}")
    public String seeCountries(Model model,
                               @PathVariable("page") Integer page){
        Page<Country> all = countryRepository.findAllByOrderByIdAsc(PageRequest.of(page, perPage));
        model.addAttribute("Countries", all.get().collect(Collectors.toList()));
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        return "admin/seeCountries";
    }
    @GetMapping("/modify/country/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String modifyCountry(@PathVariable("id")String string, Model model){
        model.addAttribute("Country",countryRepository.findById(string));
        return "admin/modifyCountry";
    }
    @GetMapping("/add/country")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCountry(Model model){
        model.addAttribute("Country",new Country());
        return "admin/addCountry";
    }
    @PostMapping("/save/country")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCountry(@ModelAttribute("Country") Country c, Model model) {
        countryRepository.save(c);
        return "redirect:/see/countries/0";
    }
    @PostMapping("/delete/country/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCountry(@PathVariable("id") String email, Model model) {
        if(countryRepository.findById(email).isPresent()) countryRepository.deleteById(email);
        return "redirect:/see/countries/0";
    }

    @GetMapping("/see/discovered/{page}")
    public String allDiscovered(Model model,
                                @PathVariable("page") Integer page){
        Page<Discover> all = discoveryRepository.findAllByOrderByIdAsc(PageRequest.of(page, perPage));
        model.addAttribute("discovered", all.get().collect(Collectors.toList()));
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        return "discovered";
    }
    @GetMapping("/see/doctors/{page}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allDoctors(Model model,
    @PathVariable("page") Integer page) {
        List<UserDTO> res = userService.getUserDoctor(page,perPage);
        model.addAttribute("Users", res);
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", doctorRepository.findAllDoctorsValues(PageRequest.of(page,perPage)).getTotalPages()>page+1);
        return "admin/seedoctors";
    }
    @GetMapping("/see/publicservants/{page}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allPublicServants(Model model,@PathVariable("page") Integer page){
        List<UserDTO> res = userService.getUserPS(page,perPage);
        model.addAttribute("Users", res);
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", publicServantRepository.findAllPublicServants(PageRequest.of(page,perPage)).getTotalPages()>page+1);

        return "admin/seepublicservants";
    }
    @GetMapping("/see/users/{page}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allUsers(Model model,
                           @PathVariable("page") Integer page){
        Page<User> all = userRepository.findAllByOrderByIdAsc(PageRequest.of(page, perPage));
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        model.addAttribute("Users", all.get().collect(Collectors.toList()));
        return "admin/seeusers";
    }
    @GetMapping("/modify/user/{id}")
    @PreAuthorize("permitAll()")
    public String modifyUser(@PathVariable("id")String string, Model model){
        model.addAttribute("User",userRepository.findById(string).get());
        model.addAttribute("email", userRepository.findById(string).get().getId());
        model.addAttribute("Countries", countryRepository.findAll());
        Optional<Doctor> DoctorByID = doctorRepository.findById(string);
        if (DoctorByID.isPresent()){
            model.addAttribute("Doctor", DoctorByID.get());
            model.addAttribute("Diseases", diseaseRepository.findAllByOrderByDiseaseCode());
            model.addAttribute("returnDis", new Disease());
            return "admin/modifyDoctor";
        }
        if (publicServantRepository.findById(string).isPresent()) {
            model.addAttribute("PublicServant", publicServantRepository.findById(string).get());
            return "admin/modifyPublicServant";
        }
        return "admin/modifyuser";
    }
    @PostMapping("/delete/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") String email, Model model) {
        userService.deleteUser(email);
        return "redirect:/see/users/0";
    }
}
