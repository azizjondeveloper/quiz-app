package com.example.quizapp.repository;

import com.example.quizapp.entity.Science;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
    public interface ScienceRepository extends JpaRepository<Science, Integer> {
}
