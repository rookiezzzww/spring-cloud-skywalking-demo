package com.bite.alarm.controller;

import com.bite.alarm.entity.AlarmMessage;
import com.bite.alarm.utils.MailUtils;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    private MailUtils mailUtils;

    @RequestMapping("/handler")
    public String handler(@RequestBody List<AlarmMessage> alarmMessages) throws MessagingException, UnsupportedEncodingException {
        log.info("alarm message: {}", alarmMessages);
        mailUtils.send("2960633784@qq.com", "sw报警信息", alarmMessages.toString());
        return "接收到报警信息";
    }
}
