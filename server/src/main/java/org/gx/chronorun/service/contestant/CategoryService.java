package org.gx.chronorun.service.contestant;

import org.gx.chronorun.model.Sex;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    List<CategoryInformation> categories = Arrays.asList(
            CategoryInformation.builder().minAge(0).maxAge(6).category("BB").build(),
            CategoryInformation.builder().minAge(7).maxAge(9).category("BE").build(),
            CategoryInformation.builder().minAge(10).maxAge(11).category("PO").build(),
            CategoryInformation.builder().minAge(12).maxAge(13).category("BE").build(),
            CategoryInformation.builder().minAge(14).maxAge(15).category("MA").build(),
            CategoryInformation.builder().minAge(16).maxAge(17).category("CA").build(),
            CategoryInformation.builder().minAge(18).maxAge(19).category("JU").build(),
            CategoryInformation.builder().minAge(20).maxAge(22).category("ES").build(),
            CategoryInformation.builder().minAge(23).maxAge(34).category("SE").build(),
            CategoryInformation.builder().minAge(35).maxAge(39).category("M0").build(),
            CategoryInformation.builder().minAge(40).maxAge(44).category("M1").build(),
            CategoryInformation.builder().minAge(45).maxAge(49).category("M2").build(),
            CategoryInformation.builder().minAge(50).maxAge(54).category("M3").build(),
            CategoryInformation.builder().minAge(55).maxAge(59).category("M4").build(),
            CategoryInformation.builder().minAge(60).maxAge(64).category("M5").build(),
            CategoryInformation.builder().minAge(65).maxAge(69).category("M6").build(),
            CategoryInformation.builder().minAge(70).maxAge(74).category("M7").build(),
            CategoryInformation.builder().minAge(75).maxAge(79).category("M8").build(),
            CategoryInformation.builder().minAge(80).maxAge(84).category("M9").build(),
            CategoryInformation.builder().minAge(85).maxAge(99).category("M10").build()
    );

    public String getContestantCategory(Integer birthYear, Sex sex) {
        Integer age = LocalDateTime.now().getYear() - birthYear;
        return categories.stream().filter(e -> age >= e.getMinAge() && age <= e.getMaxAge()).findFirst()
                .orElse(CategoryInformation.builder().minAge(85).maxAge(99).category("M10").build()).getCategory() + sex.name();
    }

}
