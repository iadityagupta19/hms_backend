package com.hospitalmanagement.HMS.service;

import com.hospitalmanagement.HMS.model.Appointment;
import com.hospitalmanagement.HMS.model.Patient;
import com.hospitalmanagement.HMS.model.Doctor;
import com.hospitalmanagement.HMS.repository.AppointmentRepository;
import com.hospitalmanagement.HMS.repository.PatientRepository;
import com.hospitalmanagement.HMS.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public Appointment addAppointment(Appointment appointment) {
        Patient patient = patientRepository.findById(appointment.getPatient().getId()).orElse(null);
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId()).orElse(null);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
