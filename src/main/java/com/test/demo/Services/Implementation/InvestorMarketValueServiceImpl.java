package com.test.demo.Services.Implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.demo.Entities.Funds;
import com.test.demo.Entities.Investors;
import com.test.demo.Repository.InvestorRepository;
import com.test.demo.Services.AppService;
import com.test.demo.Services.MarketValueService;

@Service("investorMarketValueService")
public class InvestorMarketValueServiceImpl implements MarketValueService {

	@Autowired
	InvestorRepository investorRepository;

	@Autowired
	AppService appService;

	@Override
	public double fetchMarkedValue(Integer id, List<Integer> exclusionHoldingList) {
		double marketValue = 0.0;
		// TODO Auto-generated method stub
		Investors investors = getNodeById(id);
		Set<Funds> funds = investors.getFunds();
		if (funds != null && funds.size() != 0) {
			for (Funds fund : funds) {
				marketValue += appService.calculateMarketValue(fund,exclusionHoldingList);
			}
		}
		return marketValue;
	}

	@Override
	public Investors getNodeById(int id) {
		// TODO Auto-generated method stub
		Optional<Investors> investors = investorRepository.findById(id);
		if (investors.isPresent())
			return investors.get();
		return null;
	}

}
