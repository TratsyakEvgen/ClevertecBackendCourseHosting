package ru.clevertec.hosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.hosting.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
