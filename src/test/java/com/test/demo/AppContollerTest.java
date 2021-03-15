package com.test.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.test.demo.Controllers.AppController;
import com.test.demo.Exceptions.FundNotFoundException;
import com.test.demo.Exceptions.InvalidInputException;
import com.test.demo.Exceptions.InvestorNotFoundException;
import com.test.demo.Services.MarketValueService;
import com.test.demo.dto.ResponseDto;

@RunWith(MockitoJUnitRunner.class)
public class AppContollerTest {
	
	@InjectMocks
	AppController appController;
	
	@Mock
	@Qualifier("fundsMarketValueService")
	MarketValueService fundMarketValueService;

	
	@Test(expected=Exception.class)
	public void toGetMarketValue() throws InvalidInputException, FundNotFoundException, InvestorNotFoundException  {
		ResponseDto dto=new ResponseDto();
		double marketValue=4000;
		dto.setMarketValue(marketValue);
		Mockito.when(fundMarketValueService.fetchMarkedValue(1, null)).thenReturn(marketValue);
		Mockito.doReturn(dto).when(fundMarketValueService.fetchMarkedValue(1, null));
		ResponseEntity<ResponseDto> actualResponse = appController.marketValue(1, null, null);
		assertNotNull(actualResponse);
		assertNotNull(actualResponse.getStatusCode());
		assertEquals(actualResponse.getStatusCode(), HttpStatus.OK.toString());
		assertEquals(dto, actualResponse.getBody());
	}
	
	@Test(expected=InvalidInputException.class)
	public void toGetInvalidInputException() throws InvalidInputException, FundNotFoundException, InvestorNotFoundException {
		ResponseDto dto=new ResponseDto();
		dto.setMarketValue(4000);
		Mockito.when(appController.marketValue(null, null, null)).thenThrow(InvalidInputException.class);
	}
}
