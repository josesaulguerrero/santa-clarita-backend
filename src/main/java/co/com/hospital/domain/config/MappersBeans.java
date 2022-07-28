package co.com.hospital.domain.config;

import co.com.hospital.persistence.mapper.AppointmentMapper;
import co.com.hospital.persistence.mapper.PatientMapper;
import co.com.hospital.persistence.mapper.SpecialistMapper;
import co.com.hospital.persistence.mapper.SpecialtyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersBeans {
    @Bean
    public AppointmentMapper getAppointmentMapperBean() {
        return new AppointmentMapper();
    }

    @Bean
    public PatientMapper getPatientMapperBean() {
        return new PatientMapper();
    }

    @Bean
    public SpecialistMapper getSpecialistMapperBean() {
        return new SpecialistMapper();
    }

    @Bean
    public SpecialtyMapper getSpecialtyMapperBean() {
        return new SpecialtyMapper();
    }
}
