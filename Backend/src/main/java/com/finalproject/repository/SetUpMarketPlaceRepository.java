package com.finalproject.repository;
import com.finalproject.model.SetUpMarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetUpMarketPlaceRepository extends JpaRepository<SetUpMarketPlace, Integer>{

}
