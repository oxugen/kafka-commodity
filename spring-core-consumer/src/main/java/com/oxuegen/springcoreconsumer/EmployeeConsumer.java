package com.oxuegen.springcoreconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxuegen.springcoreconsumer.Entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-employee")
    public void consume(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        LOG.info(
                "employee id : {}, name : {}, date : {}",
                employee.getEmployeeId(), employee.getName(), employee.getBirthDate()
        );
    }
}
