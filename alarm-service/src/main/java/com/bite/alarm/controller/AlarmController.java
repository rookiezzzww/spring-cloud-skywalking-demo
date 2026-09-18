package com.bite.alarm.controller;

import com.bite.alarm.entity.AlarmMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @RequestMapping("/handler")
    public String handler(@RequestBody List<AlarmMessage> alarmMessages) {
        log.info("alarm message: {}", alarmMessages);
        return "接收到报警信息";
    }
}
