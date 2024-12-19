package com.finalproject.repository;
import com.finalproject.model.BookmarkStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerStoreBookmarkRepository extends JpaRepository<BookmarkStore, Integer> {

}
