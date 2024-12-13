package com.finalproject.repository;
import com.finalproject.model.BuyerProductBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerProductBookmarkRepository extends JpaRepository<BuyerProductBookmark, Integer> {

}
