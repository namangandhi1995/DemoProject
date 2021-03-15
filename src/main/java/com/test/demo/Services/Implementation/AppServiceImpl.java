package com.test.demo.Services.Implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.Entities.Funds;
import com.test.demo.Entities.FundsHoldings;
import com.test.demo.Entities.Holdings;
import com.test.demo.Entities.Investors;
import com.test.demo.Repository.AppDao;
import com.test.demo.Repository.FundsRepository;
import com.test.demo.Repository.InvestorRepository;
import com.test.demo.Services.AppService;
import com.test.demo.dto.NodeRequestDto;

@Service
public class AppServiceImpl<T> implements AppService {

	@Autowired
	AppDao appDao;

	@Autowired
	FundsRepository fundsRepository;

	@Autowired
	InvestorRepository investorsRepository;

	@Override
	public void addNode(NodeRequestDto nodeRequestDto) {
		// TODO Auto-generated method stub
		if (nodeRequestDto.getLevel() == 0) {
			Investors investor = (Investors) appDao.getNodebyId(nodeRequestDto.getParent().getName(), Investors.class);
			Funds fund = (Funds) appDao.getNodebyId(nodeRequestDto.getChild().getName(), Funds.class);
			Set<Funds> funds = null;
			if (fund == null) {
				fund = new Funds();
				fund.setId(nodeRequestDto.getChild().getId());
			}
			if (investor == null) {
				investor = new Investors();
				investor.setId(nodeRequestDto.getParent().getId());
			}
			if (investor.getFunds() == null)
				funds = new HashSet<Funds>();
			else
				funds = investor.getFunds();
			funds.add(fund);
			investor.setFunds(funds);
			appDao.saveOrUpdateObject(investor);
		} else if (nodeRequestDto.getLevel() == 1) {
			Funds fund = (Funds) appDao.getNodebyId(nodeRequestDto.getParent().getName(), Funds.class);
			Holdings holdings = (Holdings) appDao.getNodebyId(nodeRequestDto.getChild().getName(), Holdings.class);
			Set<FundsHoldings> fundsholdingSet = null;
			FundsHoldings fundsHoldings = new FundsHoldings();
			if (fund == null) {
				fund = new Funds();
				fund.setId(nodeRequestDto.getParent().getId());
			}
			if (holdings == null) {
				holdings = new Holdings();
				holdings.setId(nodeRequestDto.getChild().getId());
			}
			if (fund.getFundsHoldings() == null)
				fundsholdingSet = new HashSet<FundsHoldings>();
			else
				fundsholdingSet = fund.getFundsHoldings();

			fundsHoldings.setFund(fund);
			fundsHoldings.setQuantity(nodeRequestDto.getEdge());
			fundsHoldings.setHolding(holdings);
			fundsholdingSet.add(fundsHoldings);
			fund.setFundsHoldings(fundsholdingSet);
			appDao.saveOrUpdateObject(fund);
		} else if (nodeRequestDto.getLevel() == 2) {
			Funds fund = (Funds) appDao.getNodebyId(nodeRequestDto.getParent().getName(), Funds.class);
			Holdings holdings = (Holdings) appDao.getNodebyId(nodeRequestDto.getChild().getName(), Holdings.class);
			Set<FundsHoldings> fundsholdingSet = null;
			FundsHoldings fundsHoldings = new FundsHoldings();
			if (fund == null) {
				fund = new Funds();
				fund.setId(nodeRequestDto.getParent().getId());
			}
			if (holdings == null) {
				holdings = new Holdings();
				holdings.setId(nodeRequestDto.getChild().getId());
			}
			if (fund.getFundsHoldings() == null)
				fundsholdingSet = new HashSet<FundsHoldings>();
			else
				fundsholdingSet = fund.getFundsHoldings();

			fundsHoldings.setFund(fund);
			fundsHoldings.setQuantity(nodeRequestDto.getEdge());
			fundsHoldings.setHolding(holdings);
			fundsholdingSet.add(fundsHoldings);
			fund.setFundsHoldings(fundsholdingSet);
			appDao.saveOrUpdateObject(fund);
		}

	}

	@Override
	public double calculateMarketValue(Funds fund, List<Integer> exclusionHoldingList) {
		double marketValueforFund = 0.0;
		Set<FundsHoldings> fundsHoldings = fund.getFundsHoldings();
		if (fundsHoldings != null && fundsHoldings.size() != 0) {
			for (FundsHoldings fundsHolding : fundsHoldings) {
				if (!exclusionHoldingList.contains(fundsHolding.getId())) {
					int quantity = fundsHolding.getQuantity();
					double holdingValue = fundsHolding.getHolding() != null
							? fundsHolding.getHolding().getHoldingValue()
							: 0.0;
					marketValueforFund += quantity * holdingValue;
				}
			}
		}
		return marketValueforFund;
	}

}
