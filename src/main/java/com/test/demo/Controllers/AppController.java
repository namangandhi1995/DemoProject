package com.test.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.Exceptions.FundNotFoundException;
import com.test.demo.Exceptions.InvalidInputException;
import com.test.demo.Exceptions.InvestorNotFoundException;
import com.test.demo.Services.AppService;
import com.test.demo.Services.MarketValueService;
import com.test.demo.dto.NodeRequestDto;
import com.test.demo.dto.ResponseDto;

@RestController
@RequestMapping(name = "/api/v1")
public class AppController {

	@Autowired
	AppService appService;

	@Autowired
	@Qualifier("fundsMarketValueService")
	MarketValueService fundMarketValueService;

	@Autowired
	@Qualifier("investorMarketValueService")
	MarketValueService investorMarketValueService;

	@PostMapping(name = "/node", consumes = "application/json")
	public <T> ResponseEntity<T> addNode(@RequestBody NodeRequestDto nodeRequestDto) throws InvalidInputException {
		if (nodeRequestDto == null || nodeRequestDto.getLevel() >= 3)
			throw new InvalidInputException("Invalid Input");
		appService.addNode(nodeRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@SuppressWarnings("deprecation")
	@GetMapping(name = "/marketValue")
	public ResponseEntity<ResponseDto> marketValue(@RequestParam(name="fundsId",required = false) Integer fundsId,
			@RequestParam(name ="investorsId", required = false) Integer investorsId , @RequestParam(name="exclusionHoldingList",required = false) List<Integer> exclusionHoldingList )
			throws InvalidInputException, FundNotFoundException, InvestorNotFoundException {
		ResponseDto responseDto = null;
		if (!StringUtils.isEmpty(fundsId)) {
			if (fundMarketValueService.getNodeById(fundsId) == null)
				throw new FundNotFoundException("No Fund found with id " + fundsId);
			responseDto = new ResponseDto();
			responseDto.setMarketValue(fundMarketValueService.fetchMarkedValue(fundsId,exclusionHoldingList));
		} else if (!StringUtils.isEmpty(investorsId)) {
			if (investorMarketValueService.getNodeById(investorsId) == null)
				throw new InvestorNotFoundException("No Investor found with id " + investorsId);
			responseDto = new ResponseDto();
			responseDto.setMarketValue(investorMarketValueService.fetchMarkedValue(investorsId,exclusionHoldingList));
		} else {
			throw new InvalidInputException("Invalid Input");
		}
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

}
