package com.example.demo.testAggregate;

import com.example.demo.annotations.Policy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    @GetMapping("")
    @Policy("test")
    public String findTest(){
        return "test success";
    }
}
