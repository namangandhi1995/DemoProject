package com.test.demo.Services.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.Entities.Funds;
import com.test.demo.Repository.FundsRepository;
import com.test.demo.Services.AppService;
import com.test.demo.Services.MarketValueService;

@Service("fundsMarketValueService")
public class FundsMarketValueServiceImpl implements MarketValueService {

	@Autowired
	FundsRepository fundsRepository;

	@Autowired
	AppService appService;

	@Override
	public double fetchMarkedValue(Integer id, List<Integer> exclusionHoldingList) {
		// TODO Auto-generated method stub
		double marketValue = 0.0;
		Funds fund = getNodeById(id);
		marketValue = appService.calculateMarketValue(fund,exclusionHoldingList);
		return marketValue;
	}

	@Override
	public Funds getNodeById(int id) {
		// TODO Auto-generated method stub
		Optional<Funds> funds = fundsRepository.findById(id);
		if (funds.isPresent())
			return funds.get();
		return null;
	}

}
