package ru.clevertec.hosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.hosting.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
