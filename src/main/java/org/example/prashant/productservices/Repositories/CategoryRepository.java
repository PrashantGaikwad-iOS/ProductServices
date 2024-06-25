package org.example.prashant.productservices.Repositories;

import org.example.prashant.productservices.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesByTitle(String title);
    Category save(Category category);
}
