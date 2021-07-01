package com.d3sage.spectre;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.d3sage.spectre");

        noClasses()
            .that()
            .resideInAnyPackage("com.d3sage.spectre.service..")
            .or()
            .resideInAnyPackage("com.d3sage.spectre.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.d3sage.spectre.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
