package org.example.pensionage.service;

import org.example.pensionage.model.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PensionAgeService {

    public Result calculatePensionAge(Integer age, LocalDate startOfWorkDate, Integer salary) {

        int actualYear = LocalDate.now().getYear();
        int actualYearsOfWork =  actualYear - startOfWorkDate.getYear();
        if (age - actualYearsOfWork < 16) {
            throw new IllegalArgumentException("age must be equal or greater than year of work + 16");
        }

        int birthYear = actualYear - age;
        Integer pensionAge = birthYear <= 1958 ? 66 : 67;
        Integer startOfWorkAge = startOfWorkDate.getYear() - birthYear;
        int yearsOfWork = pensionAge - startOfWorkAge;

        return new Result(((float) (salary / 1000)) * yearsOfWork);
    }
}
