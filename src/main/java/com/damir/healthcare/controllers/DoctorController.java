package com.damir.healthcare.controllers;

import com.damir.healthcare.auth.AuGroupRepository;
import com.damir.healthcare.entities.*;
import com.damir.healthcare.repositories.*;
import com.damir.healthcare.services.DiseaseService;
import com.damir.healthcare.services.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Controller
public class DoctorController {
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
    UserService userService;
    final
    DiscoveryRepository discoveryRepository;
    final
    RecordRepository recordRepository;
    final
    EntityManager entityManager;
    final
    ReviewingRepository reviewingRepository;
    final DiseaseService diseaseService;

    public DoctorController(AuGroupRepository auGroupRepository, DiseaseRepository diseaseRepository, UserRepository userRepository, DoctorRepository doctorRepository, PublicServantRepository publicServantRepository, CountryRepository countryRepository, UserService userService, DiscoveryRepository discoveryRepository, RecordRepository recordRepository, EntityManager entityManager, ReviewingRepository reviewingRepository, DiseaseService diseaseService) {
        this.auGroupRepository = auGroupRepository;
        this.diseaseRepository = diseaseRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.publicServantRepository = publicServantRepository;
        this.countryRepository = countryRepository;
        this.userService = userService;
        this.discoveryRepository = discoveryRepository;
        this.recordRepository = recordRepository;
        this.entityManager = entityManager;
        this.reviewingRepository = reviewingRepository;
        this.diseaseService = diseaseService;
    }


    @GetMapping("/diseases/{page}")
    public String getDisease(Model model,
                             @PathVariable("page")Integer page) {
        Page<Disease> all = diseaseRepository.findAll(PageRequest.of(page, perPage));
        model.addAttribute("Diseases", all);
        model.addAttribute("isReviewing", reviewingRepository.findAll(PageRequest.of(page,perPage)).getTotalElements()>0);
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        return "doctor/diseases";
    }
    @GetMapping("/reviewing/{page}")
    public String getReviewing(Model model,
                             @PathVariable("page")Integer page) {
        Page<Reviewing> all = reviewingRepository.findAll(PageRequest.of(page, perPage));
        model.addAttribute("Diseases", all);
        System.out.println( SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("curuser", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        return "doctor/reviewing";
    }
    @GetMapping("/see/disease/history/{page}")
    public String getHistory(Model model,
                               @PathVariable("page")Integer page) {
        Page<Disease> all = diseaseRepository.findAll(PageRequest.of(page, perPage));
        model.addAttribute("Diseases", all);
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        return "doctor/history";
    }
    @GetMapping("/see/modified/{id}")
    public String getMod(Model model,
                         @PathVariable("id") Integer id) {
        Reviewing newDis = reviewingRepository.findById(id).get();
        System.out.println(newDis);
        System.out.println(diseaseRepository.findById(newDis.getOriginalId()).get());
        model.addAttribute("d",diseaseRepository.findById(newDis.getOriginalId()).get());
        model.addAttribute("n", newDis);
        model.addAttribute("curuser",SecurityContextHolder.getContext().getAuthentication().getName());
        return "doctor/seemodified";
    }
    @GetMapping("/add/disease")
    public String addDisease(Model model) {
        model.addAttribute("Disease", new Reviewing());
        return "doctor/addDisease";
    }

    @GetMapping("/modify/disease/{id}")
    public String updateDisease(@PathVariable("id") Integer id, Model model) {
        Disease disease = diseaseRepository.findById(id).get();
        model.addAttribute("Disease", disease);
        return "doctor/modifyDisease";
    }

    @PostMapping("/save/disease")
    @Transactional
    public String saveDisease(@ModelAttribute("Disease") Disease dis) {
        diseaseService.saveModifiedDisease(dis);
        return "redirect:/diseases/0";
    }
    @PostMapping("/save/new/disease")
    @Transactional
    public String saveNewDisease(@ModelAttribute("Disease") Reviewing dis) {
        diseaseService.saveNewDisease(dis);
        return "redirect:/diseases/0";
    }

    @PostMapping("/delete/disease/{id}")
    public String deleteDisease(@PathVariable("id") Integer id, Model model) {
        diseaseService.deleteDisease(id);
        return "redirect:/diseases/0";
    }

    @PostMapping("/delete/reviewing/{id}")
    public String deleteReviewing(@PathVariable("id") Integer id, Model model) {
        reviewingRepository.deleteById(id);
        return "redirect:/reviewing/0";
    }

    @PostMapping("/approve/{id}")
    public String approveRev(@PathVariable("id") Integer id) {
        diseaseService.approve(reviewingRepository.findById(id).get());
        return "redirect:/reviewing/0";
    }

    @GetMapping("/add/discovered")
    public String addDiscovered(Model model) {
        model.addAttribute("Discover", new Discover());
        model.addAttribute("Disease", diseaseRepository.findAll());
        model.addAttribute("Countries", countryRepository.findAll());
        return "adddiscovered";
    }

    @PostMapping("/save/discovered")
    public String saveDiscovered(@ModelAttribute Discover discover) {
        discover.setFirstEncDate(LocalDate.parse(discover.date));
        discoveryRepository.save(discover);
        return "redirect:/see/discovered/0";
    }
    @GetMapping("/modify/discovered/{cname}/{discode}")
    public String saveDiscovered(@PathVariable("cname") String cname,
                                 @PathVariable("discode") String disCode,
                                 Model model) {
        Discover discover = discoveryRepository.findById(new DiscoverID(cname, disCode)).get();
        model.addAttribute("Discover",discover);
        model.addAttribute("Disease", diseaseRepository.findAll());
        model.addAttribute("Countries", countryRepository.findAll());
        return "modifydiscovered";
    }
    @PostMapping("/delete/discovered/{cname}/{discode}")
    public String deleteDisc(@PathVariable("cname") String cname,
                                 @PathVariable("discode") String disCode,
                                 Model model) {
        discoveryRepository.deleteById(new DiscoverID(cname,disCode));
        return "redirect:/see/discovered/0";
    }
    //ATTENTION!!!
    @PostMapping("/delete/discovered/{id}")
    public String deleteType(@PathVariable("id") Integer id) {
//        discoveryRepository.deleteById(id);
        return "redirect:/see/discovered/0";
    }

}
