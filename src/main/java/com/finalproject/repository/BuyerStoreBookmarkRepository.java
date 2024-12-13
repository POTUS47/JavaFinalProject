package com.finalproject.repository;
import com.finalproject.model.BuyerStoreBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerStoreBookmarkRepository extends JpaRepository<BuyerStoreBookmark, Integer> {

}
