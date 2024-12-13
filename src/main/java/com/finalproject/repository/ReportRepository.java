package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

}
