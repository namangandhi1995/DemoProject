package com.test.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.Entities.Funds;

@Repository
public interface FundsRepository extends CrudRepository<Funds, Integer>{

}
