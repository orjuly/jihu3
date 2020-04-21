package com.yqbing.servicebing.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yqbing.servicebing.repository.database.abstracts.TbStoreAgentExample;
import com.yqbing.servicebing.repository.database.bean.StoreInfoBean;
import com.yqbing.servicebing.repository.database.bean.TUserInfoBean;
import com.yqbing.servicebing.repository.database.bean.TbAgentBusinessLog;
import com.yqbing.servicebing.repository.database.bean.TbStoreAgent;
import com.yqbing.servicebing.repository.database.dao.TbAgentBusinessLogMapper;
import com.yqbing.servicebing.repository.database.dao.TbStoreAgentMapper;

public class commonService {
	@Autowired
	private TbAgentBusinessLogMapper agentBusinessLogMapper = null;
	@Autowired
	private TbStoreAgentMapper storeAgentMapper = null;

	public void dealthAgentBusinessLog(short businessType,int businessPrice,int businessNum,long storeId,long userId,TUserInfoBean userInfo,StoreInfoBean storeInfo){
		//agentBusinessLogService
		if(businessType <=0){
			return;
		}
		if(businessNum <=0){
			return;
		}
		if(storeId <=0){
			return;
		}
		if(userId<=0){
			return;
		}
		TbStoreAgent storeAgent = null;
		TbAgentBusinessLog agentLog = new TbAgentBusinessLog();
		agentLog.setUserid(userId);
		agentLog.setUsername(userInfo.getUserName());
		agentLog.setUserrole(Byte.valueOf(userInfo.getUserRole()+""));
		
		agentLog.setMobile(userInfo.getUserCode());
		agentLog.setStoreid(storeId);
		agentLog.setStorename(storeInfo.getStoreName());
		agentLog.setBusinesstype(businessType);
		if(businessType == 301){
			agentLog.setBusinesstypename("微众银行");
		}else{
			agentLog.setBusinesstypename("花呗分期");
			
		}
		agentLog.setBusinessprice(businessPrice);
		agentLog.setBusinessnum(businessNum);
		agentLog.setProvinceid(storeInfo.getProvinceId().longValue());
		agentLog.setProvincename(storeInfo.getProvinceName());
		agentLog.setCityid(storeInfo.getCityId().longValue());
		agentLog.setCityname(storeInfo.getCityName());
		agentLog.setCountyid(storeInfo.getAreaId().longValue());
		agentLog.setCountyname(storeInfo.getAreaName());
		agentLog.setCreatetime(new Date());
		//查询代理商信息
		Byte s = 1;
		Byte sta = 0;
		
		TbStoreAgentExample agentExample = new TbStoreAgentExample();
		com.yqbing.servicebing.repository.database.abstracts.TbStoreAgentExample.Criteria criteria = agentExample.createCriteria();
		criteria.andCityidEqualTo(storeInfo.getCityId().longValue()).andAgenttypeEqualTo(s);
		criteria.andStatusEqualTo(sta);
		List<TbStoreAgent> list = storeAgentMapper.selectByExample(agentExample);
		if(list != null && list.size() > 0){
			
		
		 storeAgent = list.get(0);
	//	TbStoreAgent storeAgent = storeAgentService.queryStoreAgent(String.valueOf(storeInfo.getCityId()), "1");
		if(storeAgent != null){
			s = 2;
			TbStoreAgentExample agentE = new TbStoreAgentExample();
			com.yqbing.servicebing.repository.database.abstracts.TbStoreAgentExample.Criteria cri  = agentE.createCriteria();
			cri.andAreaidEqualTo(storeInfo.getAreaId().longValue()).andAgenttypeEqualTo(s);
			cri.andStatusEqualTo(sta);
			List<TbStoreAgent> list1 = storeAgentMapper.selectByExample(agentE);
			if(list1 != null && list1.size() > 0){
			TbStoreAgent countyAgent = list1.get(0);
			if(countyAgent != null){
				agentLog.setAgentid(countyAgent.getId());
				agentLog.setAgentpid(storeAgent.getId());
				agentLog.setAgentname(countyAgent.getAgentname());
				agentLog.setAgentmobile(countyAgent.getMobile());
			}
		}else{
			agentLog.setAgentname(storeAgent.getAgentname());
			agentLog.setAgentmobile(storeAgent.getMobile());//代理商手机号
			agentLog.setAgentid(storeAgent.getId());
		}
		}
		}
		agentBusinessLogMapper.insertSelective(agentLog);
    }

}
