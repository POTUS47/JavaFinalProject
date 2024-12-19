package com.finalproject.repository;
import com.finalproject.model.BookmarkProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerProductBookmarkRepository extends JpaRepository<BookmarkProduct, Integer> {

}
