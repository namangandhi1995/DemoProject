package com.test.demo.Services;

import java.util.List;

import com.test.demo.Entities.Funds;
import com.test.demo.dto.NodeRequestDto;

public interface AppService {
	
	void addNode(NodeRequestDto nodeRequestDto);
	
	double calculateMarketValue(Funds fund, List<Integer> exclusionHoldingList);

}
