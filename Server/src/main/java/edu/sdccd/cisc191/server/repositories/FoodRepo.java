package edu.sdccd.cisc191.server.repositories;

import edu.sdccd.cisc191.common.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<FoodItem, Long> {

}
