package com.yqbing.servicebing.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yqbing.servicebing.exception.BingException;
import com.yqbing.servicebing.repository.database.abstracts.HotCityBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.OfferInfoBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.OfferInfoBeanExample.Criteria;
import com.yqbing.servicebing.repository.database.abstracts.OfferOpenBeanExample;
import com.yqbing.servicebing.repository.database.bean.HotCityBean;
import com.yqbing.servicebing.repository.database.bean.OfferInfoBean;
import com.yqbing.servicebing.repository.database.bean.OfferOpenBean;
import com.yqbing.servicebing.repository.database.bean.WeBankBean;
import com.yqbing.servicebing.repository.database.dao.HotCityBeanMapper;
import com.yqbing.servicebing.repository.database.dao.OfferInfoBeanMapper;
import com.yqbing.servicebing.repository.database.dao.OfferOpenBeanMapper;
import com.yqbing.servicebing.service.OfferService;
import com.yqbing.servicebing.utils.StrUtils;


@Service("offerService")
public class OfferServiceImpl implements OfferService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfferServiceImpl.class);
	
	@Autowired
	private HotCityBeanMapper hotCityBeanMapper = null;
	@Autowired
	private OfferInfoBeanMapper offerInfoBeanMapper = null;
	@Autowired
	private OfferOpenBeanMapper offerOpenBeanMapper = null;
	
	
	
	@Override
	public int addoffer(OfferInfoBean offerInfoBean) throws BingException {
		offerInfoBean.setCreatetime(new Date());
		int i = offerInfoBeanMapper.insertSelective(offerInfoBean);
		return i;
	}



	@Override
	public int editffer(OfferInfoBean infoBean)throws BingException {
		// TODO Auto-generated method stub.
		byte s = 0;
		if(null ==infoBean.getStatus()){
		infoBean.setAddress(infoBean.getAddress());
		infoBean.setHotcityname(infoBean.getHotcityname());
		infoBean.setHotid(infoBean.getHotid());
		infoBean.setHotcityname(infoBean.getHotcityname());
		infoBean.setPhone(infoBean.getPhone());
		infoBean.setShopname(infoBean.getShopname());
		infoBean.setShoppicture(infoBean.getShoppicture());
		infoBean.setOffertitle(infoBean.getOffertitle());
		infoBean.setQuotation(infoBean.getQuotation());
		}else if(infoBean.getStatus() == 3){
			 infoBean = offerInfoBeanMapper.selectByPrimaryKey(infoBean.getId());
			
			infoBean.setStatus(s);
		}
		int i = offerInfoBeanMapper.updateByPrimaryKeySelective(infoBean);
		return i;
	}


    
	@Override
	public PageInfo<OfferInfoBean> queryoffer(String shopname, Long hotid,
			Byte offertype)throws BingException {
		// TODO Auto-generated method stub
		byte s = 1;
		OfferInfoBeanExample offerInfoBeanExample = new OfferInfoBeanExample();
		PageInfo<OfferInfoBean> info = null;
		Criteria createCriteria = offerInfoBeanExample.createCriteria();
		if(offertype != null){
			
			createCriteria.andOffertypeEqualTo(offertype);
		}
		if(null != hotid ){
			createCriteria.andHotidEqualTo(hotid);
		}
		if(!StrUtils.isNullOrBlank(shopname)){
			createCriteria.andShopnameLike(shopname+"%");
		}
		createCriteria.andStatusEqualTo(s);
		offerInfoBeanExample.setOrderByClause("createTime desc");
		List<OfferInfoBean> list = offerInfoBeanMapper.selectByExample(offerInfoBeanExample);
		LOGGER.info("-----------------------------------------报价信息记录"+new Gson().toJson(list));
		info = new PageInfo<OfferInfoBean>(list);
		return info;
	}



	@Override
	public List<HotCityBean> queryHotcity() throws BingException {
		// TODO Auto-generated method stub
		Byte s = 1;
		HotCityBeanExample hotCityBeanExample = new HotCityBeanExample();
		hotCityBeanExample.createCriteria().andStatusEqualTo(s);
		hotCityBeanExample.setOrderByClause("sort");
		List<HotCityBean> selectByExample = hotCityBeanMapper.selectByExample(hotCityBeanExample);
		
		return selectByExample;
	}



	@Override
	public PageInfo<HotCityBean> queryhotcity(String city)throws BingException {
		PageInfo<HotCityBean> info = null;
		HotCityBeanExample example = new HotCityBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.HotCityBeanExample.Criteria createCriteria = example.createCriteria();
		if(!StrUtils.isNullOrBlank(city)){
			createCriteria.andHotcitynameLike(city+"%");
		}
		byte s = 1;
		createCriteria.andStatusEqualTo(s);
		example.setOrderByClause("createTime desc");
		// TODO Auto-generated method stub
		List<HotCityBean> list = hotCityBeanMapper.selectByExample(example);
		info = new PageInfo<HotCityBean>(list);
		return info;
	}



	@Override
	public int addhotcity(HotCityBean hotCityBean) throws BingException{
		// TODO Auto-generated method stub
		hotCityBean.setCreatetime(new Date());
		
		int selecthotcity = selecthotcity(hotCityBean.getHotcityname());
		if(selecthotcity >0){
			return 2;
		}
		
		if(hotCityBean.getCitytype() == 1){
			int selecthotcity5 = selecthotcity5();
			if(selecthotcity5 >=5){
				return 2;
			}
		}
		int i = hotCityBeanMapper.insertSelective(hotCityBean);
		
		return i;
	}



	@Override
	public int edithotcity(HotCityBean hotCityBean)throws BingException {
		
		byte s = 0;
		if(null == hotCityBean.getStatus()){
			int selecthotcity = selecthotcity(hotCityBean.getHotcityname());
			if(selecthotcity >0){
				return 2;
			}
			if(hotCityBean.getCitytype() == 1){
			int selecthotcity5 = selecthotcity5();
			if(selecthotcity5 >=5){
				return 2;
			}}
		} else if(hotCityBean.getStatus() == 3){
			hotCityBean = hotCityBeanMapper.selectByPrimaryKey(hotCityBean.getId());
				
			hotCityBean.setStatus(s);
		}
		int i = hotCityBeanMapper.updateByPrimaryKeySelective(hotCityBean);
		return i;
	}
	public int selecthotcity(String city)throws BingException {
		// TODO Auto-generated method stub
		byte s = 1;
		HotCityBeanExample example = new HotCityBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.HotCityBeanExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andHotcitynameEqualTo(city);
			createCriteria.andStatusEqualTo(s);
		// TODO Auto-generated method stub
		List<HotCityBean> list = hotCityBeanMapper.selectByExample(example);
		return list.size();
	}
	public int selecthotcity5()throws BingException {
		// TODO Auto-generated method stub
		byte s = 1;
		HotCityBeanExample example = new HotCityBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.HotCityBeanExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCitytypeEqualTo(s);
		createCriteria.andStatusEqualTo(s);
		// TODO Auto-generated method stub
		List<HotCityBean> list = hotCityBeanMapper.selectByExample(example);
		
		return list.size();
	}



	@Override
	public int offerisopen(String off)throws BingException  {
		// TODO Auto-generated method stub
		byte s = 1;
		byte t = 0;
		OfferOpenBeanExample offerOpenBeanExample = new OfferOpenBeanExample();
		offerOpenBeanExample.createCriteria().andStatusEqualTo(s);
		List<OfferOpenBean> list = offerOpenBeanMapper.selectByExample(offerOpenBeanExample);
		OfferOpenBean offerOpenBean = list.get(0);
		
		offerOpenBean.setOfferisopen(off.equals("true")?s:t);
		int i = offerOpenBeanMapper.updateByPrimaryKeySelective(offerOpenBean);
		
		return i;
	}



	@Override
	public OfferOpenBean queryWeBankopen() {
		// TODO Auto-generated method stub
		byte s = 1;
		OfferOpenBeanExample offerOpenBeanExample = new OfferOpenBeanExample();
		offerOpenBeanExample.createCriteria().andStatusEqualTo(s);
		List<OfferOpenBean> list = offerOpenBeanMapper.selectByExample(offerOpenBeanExample);
		OfferOpenBean offerOpenBean = list.get(0);
		return offerOpenBean;
	}
}
