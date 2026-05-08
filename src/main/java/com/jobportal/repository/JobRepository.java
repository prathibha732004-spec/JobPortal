package com.jobportal.repository;

import com.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

        @Query("SELECT j FROM Job j WHERE " +
                        "(:keyword IS NULL OR :keyword = '' OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
                        +
                        "AND (:location IS NULL OR :location = '' OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) "
                        +
                        "AND (:category IS NULL OR :category = '' OR LOWER(j.description) LIKE LOWER(CONCAT('%category: ', :category, '%')))")
        List<Job> searchJobs(@Param("keyword") String keyword,
                        @Param("category") String category,
                        @Param("location") String location);
}
