package com.damir.healthcare.controllers;

import com.damir.healthcare.entities.Record;
import com.damir.healthcare.entities.RecordID;
import com.damir.healthcare.repositories.CountryRepository;
import com.damir.healthcare.repositories.DiseaseRepository;
import com.damir.healthcare.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAnyRole('ROLE_PUBLICSERVANT','ROLE_ADMIN')")
public class  PublicServantController {
    final int perPage=9;
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    DiseaseRepository diseaseRepository;
    @Autowired
    CountryRepository countryRepository;
    @GetMapping("/add/record")
    public String addRecord(Model model){
        Record record = new Record();
        model.addAttribute("Record", record );
        model.addAttribute("diseases",diseaseRepository.findAll());
        model.addAttribute("countries",countryRepository.findAllByOrderByIdAsc());
        return "ps/addnewrecord";
    }
    @PostMapping("/save/record")
    public String saveRecord(@ModelAttribute("Record") Record record) {
        record.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        recordRepository.save(record);
        return "redirect:/records/0 ";
    }

    @GetMapping("/records/{page}")
    @PreAuthorize("permitAll()")
    public String getRecord(Model model,
                            @PathVariable("page") Integer page){
        Page<Record> all = recordRepository.findAll(PageRequest.of(page, perPage));
        model.addAttribute("records", all.get().collect(Collectors.toList()));
        model.addAttribute("NextPage", page+1);
        model.addAttribute("PrevPage",page-1);
        model.addAttribute("hasNextPage", all.getTotalPages()>page+1);
        model.addAttribute("id",SecurityContextHolder.getContext().getAuthentication().getName());

        return "ps/publicservant_main_page";
    }

    @PostMapping("/delete/record/{email}/{cname}/{diseaseCode}")
    public String deleteRecord(@PathVariable("email") String email,
                               @PathVariable("cname") String cname,
                               @PathVariable("diseaseCode") String diseaseCode,
                               Model model) {
        recordRepository.deleteById(new RecordID(email, cname, diseaseCode));
        return "redirect:/records/0";
    }
    @GetMapping("/modify/record/{email}/{cname}/{diseaseCode}")
    @PreAuthorize("hasRole('PUBLICSERVANT')")
    public String modifyRecord(@PathVariable("email") String email,
                               @PathVariable("cname") String cname,
                               @PathVariable("diseaseCode") String diseaseCode,
                               Model model) {
        model.addAttribute("Record", recordRepository.findById(new RecordID(email, cname, diseaseCode)).get());
        return "ps/modifyrecord";
    }

}