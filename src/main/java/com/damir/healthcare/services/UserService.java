package com.damir.healthcare.services;

import com.damir.healthcare.DTO.UserDTO;
import com.damir.healthcare.auth.AuGroup;
import com.damir.healthcare.auth.AuGroupRepository;
import com.damir.healthcare.entities.*;
import com.damir.healthcare.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    final
    DoctorRepository doctorRepository;
    final
    PublicServantRepository publicServantRepository;
    final
    AuGroupRepository auGroupRepository;
    final
    PasswordEncoder passwordEncoder;
    final
    DiseaseRepository diseaseRepository;
    final
    EntityManager entityManager;
    final
    ReviewingRepository reviewingRepository;
    final
    RecordRepository recordRepository;

    public UserService(UserRepository userRepository, DoctorRepository doctorRepository,
                       PublicServantRepository publicServantRepository, AuGroupRepository auGroupRepository,
                       PasswordEncoder passwordEncoder, DiseaseRepository diseaseRepository,
                       EntityManager entityManager,
                       ReviewingRepository reviewingRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.publicServantRepository = publicServantRepository;
        this.auGroupRepository = auGroupRepository;
        this.passwordEncoder = passwordEncoder;
        this.diseaseRepository = diseaseRepository;
        this.entityManager = entityManager;
        this.reviewingRepository = reviewingRepository;
        this.recordRepository = recordRepository;
    }

    public List<UserDTO> getUserDoctor(Integer page, Integer perPage) {
        Pageable of = PageRequest.of(page,perPage);
        Page<User> all = userRepository.findAllDoctors(of);
        Page<Doctor> allValues = doctorRepository.findAllDoctorsValues(of);
        List<User> users = all.get().collect(Collectors.toList());
        List<Doctor> docs = allValues.get().collect(Collectors.toList());
        List<UserDTO> res= new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            res.add(new UserDTO(users.get(i),docs.get(i).getDegree()));
        }
        return res;
    }
    public List<UserDTO> getUserPS(Integer page, Integer perPage) {
        Pageable of = PageRequest.of(page,perPage);
        Page<User> all = userRepository.findAllStatisticians(of);
        Page<Publicservant> allValues =publicServantRepository.findAllPublicServants(of);
        List<User> users = all.get().collect(Collectors.toList());
        List<Publicservant> ps = allValues.get().collect(Collectors.toList());
        List<UserDTO> res= new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            res.add(new UserDTO(users.get(i),ps.get(i).getDepartment()));
        }
        return res;
    }
    @Transactional
    public void deleteUser(String email){
        if (doctorRepository.findById(email).isPresent()) doctorRepository.deleteById(email);
        if (publicServantRepository.findById(email).isPresent()) publicServantRepository.deleteById(email);
        if (auGroupRepository.findFirstByEmail(email).isPresent()) auGroupRepository.deleteByEmail(email);
        if (userRepository.findById(email).isPresent()) userRepository.deleteById(email);
    }

    public void savePS(User user, Publicservant ps) {
        ps.setEmail(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        AuGroup augroup = new AuGroup(user.getId(), "PUBLICSERVANT");
        if (!auGroupRepository.native_findGroup(user.getId(),"PUBLICSERVANT").isPresent()) auGroupRepository.save(augroup);
        publicServantRepository.save(ps);
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        AuGroup augroup = new AuGroup(user.getId(), "USER");
        entityManager.persist(augroup);
        auGroupRepository.save(augroup);
    }

    public void saveDoctor(User user, Doctor doctor) {
        doctor.setEmail(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        doctorRepository.save(doctor);
        if (!auGroupRepository.native_findGroup(user.getId(),"DOCTOR").isPresent()) auGroupRepository.save(new AuGroup(user.getId(),"DOCTOR"));

    }


}
