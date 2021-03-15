package com.test.demo.Services;

import java.util.List;

import com.test.demo.Entities.Node;

public interface MarketValueService {
	
	double fetchMarkedValue(Integer id, List<Integer> exclusionHoldingList);
	Node getNodeById(int id);

}
