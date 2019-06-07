package com.mex.SpringBootProject.controllers;

import com.mex.SpringBootProject.configuration.MessageException;
import static com.mex.SpringBootProject.configuration.Constants.*;
import com.mex.SpringBootProject.services.FormService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    Logger log = LogManager.getLogger(FormController.class);

    @Autowired
    private FormService service;

    @RequestMapping("/updateParticipants")
    public int updateParticipants(@RequestParam String fromDate, @RequestParam String toDate) throws MessageException, IOException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(PATTERN_DATE);
        Date from = Date.valueOf(LocalDate.parse(fromDate, df));
        Date to = Date.valueOf(LocalDate.parse(toDate, df));
        if (to.before(from)) {
            throw new MessageException("From date should be before to date.");
        }
        System.out.println("From date: " + from + "To date: " + to);
        int msgResult = service.updateInfo(from, to);
        log.info("From date: " + from + "To date: " + to);
        return msgResult;
    }
}
