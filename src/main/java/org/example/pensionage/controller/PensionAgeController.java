package org.example.pensionage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pensionage.model.Result;
import org.example.pensionage.service.PensionAgeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@RestController
@Validated
@RequestMapping("/pensionAge")
@RequiredArgsConstructor
@Slf4j
public class PensionAgeController {
    private final PensionAgeService pensionAgeService;

    @GetMapping(value = "")
    public ResponseEntity<Result> calculatePensionAge(
            @RequestParam @Min(value = 16, message = "age must be at least 16!") Integer age,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Past(message = "date of start of work must be a date in the past  ") LocalDate startOfWorkDate,
            @RequestParam @Positive(message = "salary must be positive") Integer salary) {
        return ResponseEntity.ok(
                pensionAgeService.calculatePensionAge(age, startOfWorkDate, salary));
    }
}
