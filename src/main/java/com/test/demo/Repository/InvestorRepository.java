package com.test.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.Entities.Investors;

@Repository
public interface InvestorRepository extends CrudRepository<Investors, Integer> {

}
