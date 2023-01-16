package com.racing.controller;


import java.util.List;

import com.racing.api.RaceFacilityOpenApiManager;
import com.racing.model.service.RaceFacilityService;
import com.racing.model.vo.RaceFacility;


public class RaceFacilityController {
	private RaceFacilityService RaceFacilityService = new RaceFacilityService();
	
	public void initRaceFacility() {
//		Calendar cal = new GregorianCalendar(2022, 0, 3);
//		Calendar today = Calendar.getInstance();
		
		while(true) {
//			System.out.println(cal.getTime());
//			if(cal.after(today) == true) {
//				break;
//			}
			List<RaceFacility> list = RaceFacilityOpenApiManager.callRaceFacilityListByXML();
//			cal.add(Calendar.DATE, 7);
			
			if(list == null || list.isEmpty()) {
				continue;
			}
			
			for(RaceFacility box : list) {
				System.out.println(box);
				RaceFacilityService.insert(box);
			}
		}
	}
	
//	public List<RaceFacility> getAllMvList(){
//		return RaceFacilityService.selectAll();
//	}
	
	public List<RaceFacility> selectBySearchFltTitle(String facTitle) {
		return RaceFacilityService.selectBySearchFltTitle(facTitle);
	}
	
	public List<RaceFacility> selectByBuyTicket() {
		return RaceFacilityService.selectByBuyTicket();
	}
	
	public List<RaceFacility> selectByFood() {
		return RaceFacilityService.selectByFood();
	}
	
	public List<RaceFacility> selectBySee() {
		return RaceFacilityService.selectBySee();
	}
	
	public List<RaceFacility> selectByFun() {
		return RaceFacilityService.selectByFun();
	}
	
	public List<RaceFacility> selectByFac() {
		return RaceFacilityService.selectByFac();
	}
	
	public List<RaceFacility> selectByMemOnly() {
		return RaceFacilityService.selectByMemOnly();
	}
	
	public List<RaceFacility> selectByEtc() {
		return RaceFacilityService.selectByEtc();
	}
	
	
	
	public static void main(String[] args) {
		RaceFacilityController controller = new RaceFacilityController();
		controller.initRaceFacility();
	}
}
