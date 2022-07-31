package co.com.hospital.domain.config;

import co.com.hospital.web.mapper.AppointmentMapper;
import co.com.hospital.web.mapper.PatientMapper;
import co.com.hospital.web.mapper.SpecialistMapper;
import co.com.hospital.web.mapper.SpecialtyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MappersBeans {
    @Bean
    @Order(-1)
    public AppointmentMapper getAppointmentMapperBean() {
        return new AppointmentMapper();
    }

    @Bean
    @Order(-1)
    public SpecialistMapper getSpecialistMapperBean() {
        return new SpecialistMapper();
    }

    @Bean
    @Autowired
    public PatientMapper getPatientMapperBean(AppointmentMapper appointmentMapper) {
        return new PatientMapper(appointmentMapper);
    }

    @Bean
    @Autowired
    public SpecialtyMapper getSpecialtyMapperBean(SpecialistMapper specialistMapper) {
        return new SpecialtyMapper(specialistMapper);
    }
}