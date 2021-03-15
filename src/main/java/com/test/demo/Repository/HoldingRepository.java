package com.test.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.Entities.Holdings;

@Repository
public interface HoldingRepository extends CrudRepository<Holdings, Integer> {

}
