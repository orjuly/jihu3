package com.yqbing.servicebing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.database.bean.AppBanner;
import com.yqbing.servicebing.repository.database.bean.AppCoupon;
import com.yqbing.servicebing.repository.database.bean.AppCouponCode;
import com.yqbing.servicebing.repository.database.bean.AppModule;
import com.yqbing.servicebing.repository.database.bean.AppRecommend;
import com.yqbing.servicebing.repository.database.bean.StoreHouse;
import com.yqbing.servicebing.repository.database.bean.SysDict;
import com.yqbing.servicebing.repository.database.bean.UserAppTask;
import com.yqbing.servicebing.repository.database.bean.UserInfoBean;
import com.yqbing.servicebing.repository.database.dao.AppBannerExample;
import com.yqbing.servicebing.repository.database.dao.AppBannerMapper;
import com.yqbing.servicebing.repository.database.dao.AppCouponCodeExample;
import com.yqbing.servicebing.repository.database.dao.AppCouponCodeMapper;
import com.yqbing.servicebing.repository.database.dao.AppCouponExample;
import com.yqbing.servicebing.repository.database.dao.AppCouponMapper;
import com.yqbing.servicebing.repository.database.dao.AppModuleExample;
import com.yqbing.servicebing.repository.database.dao.AppModuleMapper;
import com.yqbing.servicebing.repository.database.dao.AppRecommendMapper;
import com.yqbing.servicebing.repository.database.dao.StoreHouseMapper;
import com.yqbing.servicebing.repository.database.dao.SysDictExample;
import com.yqbing.servicebing.repository.database.dao.SysDictMapper;
import com.yqbing.servicebing.repository.database.dao.UserAppTaskExample;
import com.yqbing.servicebing.repository.database.dao.UserAppTaskMapper;
import com.yqbing.servicebing.repository.database.dao.UserInfoBeanMapper;
import com.yqbing.servicebing.repository.database.dao.AppBannerExample.Criteria;
import com.yqbing.servicebing.repository.redis.RStoreHouseData;
import com.yqbing.servicebing.repository.redis.RUserAppTaskData;
import com.yqbing.servicebing.repository.redis.RUserLogBean;
import com.yqbing.servicebing.service.CommonService;
import com.yqbing.servicebing.service.GameService;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.webapp.response.GameAppRes;
import com.yqbing.servicebing.webapp.response.GameType;
import com.yqbing.servicebing.webapp.response.GameTypeRes;
import com.yqbing.servicebing.webapp.response.HomeAppRes;
import com.yqbing.servicebing.webapp.response.HomeModel;
import com.yqbing.servicebing.webapp.response.HomeNewRes;
import com.yqbing.servicebing.webapp.response.homePageRes;


@Service("gameService")
@SuppressWarnings("all")
public class GameServiceImpl extends CommonService implements GameService{
    
	
	 @Resource
	 private AppBannerMapper appBannerMapper= null;
	 @Resource
	 private AppCouponMapper appCouponMapper= null;
     @Resource
     private SysDictMapper sysDictMapper= null;
     @Resource
	 private StoreHouseMapper storeHouseMapper= null;
     @Resource
	 private AppRecommendMapper appRecommendMapper= null;
     @Resource
	 private RStoreHouseData rStoreHouseData = null;
     @Resource
	 private RUserAppTaskData rUserAppTaskData = null;
     @Resource
	 private UserAppTaskMapper userAppTaskMapper = null;
     @Resource
	 private UserInfoBeanMapper userInfoBeanMapper = null;
     @Resource
     private AppCouponCodeMapper appCouponCodeMapper = null;
     @Resource
	 private RUserLogBean rUserLogBean = null;
     @Resource
     private AppModuleMapper appModuleMapper = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
	@Override
	public String gamePage(String token,String type) {
		UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		Byte s = 0;
		GameTypeRes gtr = new GameTypeRes();
		if(StringUtils.isBlank(type)){
		
		
			
			SysDictExample example2 = new SysDictExample();
			example2.createCriteria().andParentKeyEqualTo("game_type");
			List<SysDict> list = sysDictMapper.selectByExample(example2);
			List<GameType> types = new ArrayList<GameType>();
			for (SysDict sd : list) {
				GameType gt = new GameType();
				gt.setName(sd.getName());
				gt.setFidkey(sd.getFldkey());
				types.add(gt);
			}
			gtr.setTypes(types);
		//	if(type.equals("game_hot_tag")){//热门图片
				AppBannerExample example = new AppBannerExample();
				Criteria criteria = example.createCriteria();
				example.setOrderByClause(" sort");
				criteria.andAppCategoryIdEqualTo("hot_banner").andStatusEqualTo(s);
				List<AppBanner> pic = appBannerMapper.selectByExample(example);
				gtr.setPic(pic);
		//	}
		}else{
			SysDictExample example3 = new SysDictExample();
			example3.createCriteria().andParentKeyEqualTo(type);
			List<SysDict> list4 = sysDictMapper.selectByExample(example3);
			List<GameAppRes> list3 = new ArrayList<GameAppRes>();
			for (SysDict sd1 : list4) {
				List<AppRecommend> ares =  appRecommendMapper.queryTagId(sd1.getFldkey(),infoBean.getId(),0,10);
				for (AppRecommend a : ares) {
					GameAppRes appRes = new GameAppRes();
					StoreHouse sh = rStoreHouseData.getAppPack(a.getAppId());
					if(sh ==  null){
						sh = storeHouseMapper.getByAppPack(a.getAppId());
					}
					
					if(sh ==  null){
						continue;
						//	return NotifyUtil.error(ErrorCodeEnum.NULLOBJECT, "app信息不存在");
					}
					//在我的下载列表 存在不在展示
				//	if(getIsTrue( infoBean, sh)){
				//		continue;
				//	}
					appRes.setSort(a.getSort());
					appRes.setAppName(sh.getAppName());
					appRes.setAppPake(sh.getAppPack());
					appRes.setImg(sh.getAppUrlImg());
					appRes.setUrl(sh.getAppUrl());
					appRes.setId(sh.getId());
					appRes.setTAGID(a.getTagId());
					appRes.setTagIdName(sd1.getName());
					appRes.setAppType(getAppType(sh.getAppType()));
					appRes.setType(sh.getIsSelf());
					appRes.setIsHot(a.getIsHot());
				//	if(getLocalApp(infoBean.getId(), sh.getAppPack())){
				//		appRes.setLocalstatus(1);
				//		continue;
				 //   }else{
				    	appRes.setLocalstatus(0);
				  //  }
					appRes.setModule(type);
					list3.add(appRes);
				}
	        /*   if(list3 != null && list3.size() > 6){
					
	        	   List<GameAppRes> subList = list3.subList(0,6);
	        	   gtr.setApps(subList);
				}else{*/
					
					gtr.setApps(list3);
			//	}
			}
		}
	
		
		
		 return NotifyUtil.success(gtr);
		
	}
	
	
	
	@Override
	public String moreGamePage(String token, String page, String size,
			String fidkey) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String gamePeck(String token, int page, int size) {
		// TODO Auto-generated method stub
		Byte s = 0;
		UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		} 
	     
		 List<AppCoupon> list = appCouponMapper.queryByGame();
	//	 List<AppCoupon> list2 = new ArrayList<AppCoupon>();
		 for (AppCoupon appCoupon : list) {
			 
			 AppCouponCodeExample example = new AppCouponCodeExample();
				
				example.createCriteria().andCouponIdEqualTo(appCoupon.getId()).andUserIdEqualTo(infoBean.getId());
				List<AppCouponCode> list1 = appCouponCodeMapper.selectByExample(example);
				if(list1.size() > 0){
					byte ss = -1;
					appCoupon.setStatus(ss);
				//	list2.a
				}
				
		}
		 return NotifyUtil.success(list);
	}



	@Override
	public String getGameCode(String token, String device,Integer id) {
		Byte s = 0;
		Byte t = -1;
		UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		AppCouponCodeExample example = new AppCouponCodeExample();
		
		example.createCriteria().andCouponIdEqualTo(id).andUserIdEqualTo(infoBean.getId());
		List<AppCouponCode> list1 = appCouponCodeMapper.selectByExample(example);
		if(list1.size() > 0){
			return NotifyUtil.error(ErrorCodeEnum.NOTGAMECODE, "老弟,已经领取过了");
		}
		
		AppCouponCodeExample example1 = new AppCouponCodeExample();
		example1.createCriteria().andCouponIdEqualTo(id).andStatusEqualTo(s);
		List<AppCouponCode> list = appCouponCodeMapper.selectByExample(example1);
		if(list == null || list.size() == 0){
			return NotifyUtil.error(ErrorCodeEnum.NOTGAMECODE, "老弟,下次早点来");
		}
		AppCouponCode couponCode = list.get(0);
		String code = couponCode.getCode();
		couponCode.setStatus(t);//已经被领取了
		couponCode.setUserId(infoBean.getId());
		couponCode.setCreateTime(new Date());
		couponCode.setDevice(device);
		couponCode.setUserId(infoBean.getId());
		appCouponCodeMapper.updateByPrimaryKey(couponCode);
		return NotifyUtil.success(code);
	}



	@Override
	public String GameCodeOrder(String token) {
		 List<AppCoupon> list = new ArrayList<AppCoupon>();
        UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		AppCouponCodeExample example = new AppCouponCodeExample();
		example.createCriteria().andUserIdEqualTo(infoBean.getId());
		example.setOrderByClause(" create_time desc");
		List<AppCouponCode> list1 = appCouponCodeMapper.selectByExample(example);
		if( list1 == null || list1.size() == 0){
			return NotifyUtil.error(ErrorCodeEnum.NOTGAMECODE, "没有领取过");
		}
		
			for (AppCouponCode a : list1) {
				AppCoupon coupon = appCouponMapper.selectByPrimaryKey(a.getCouponId());
				if(coupon != null){
					coupon.setCode(a.getCode());
					list.add(coupon);
				}
			}
			
		return NotifyUtil.success(list);
	}



	@Override
	public String gameNew(String token, String type) {
		
		UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		Byte s = 0;
		HomeNewRes res = null;	        	 
		try {
			res = new HomeNewRes();
        	 
        	AppBannerExample picex = new AppBannerExample();
     		Criteria criteria = picex.createCriteria();
     		criteria.andAppCategoryIdEqualTo("hot_banner").andStatusEqualTo(s);
     		picex.setOrderByClause(" sort");
     		List<AppBanner> pic = appBannerMapper.selectByExample(picex);
     		res.setPic(pic);//图片
        	 
        	 AppModuleExample example = new AppModuleExample();
        	 //example.createCriteria().and
        	 AppModule am =  appModuleMapper.selectByCode("game_module");
        	 example.createCriteria().andParentIdEqualTo(am.getId()).andStatusEqualTo(0);
        	 example.setOrderByClause(" sort");
        	 List<AppModule> list = appModuleMapper.selectByExample(example);
        	 List<HomeModel> models = new ArrayList<HomeModel>();
        	 for (AppModule ad : list) {
        		 
        		 HomeModel model = new HomeModel();
        		 model.setName(ad.getName());
        		 model.setPid(ad.getId());
        		 model.setStyle(ad.getStyle());
        		 model.setIsMore(ad.getIsMore());
        		 model.setIsMark(ad.getIsMark());
        		 List<AppRecommend> ares =  appRecommendMapper.queryTagId(ad.getId()+"",infoBean.getId(),0,10);
        		 List<HomeAppRes> apps = new ArrayList<HomeAppRes>();
        		 for (AppRecommend a : ares) {
        			 
        			 HomeAppRes appRes = new HomeAppRes();
        			 StoreHouse	 sh = storeHouseMapper.getByAppPack(a.getAppId());
        			 if(sh == null){
        				continue;
        			 }
        						appRes.setSort(a.getSort());
        						LOGGER.info("-----------------------------------首页信息"+new Gson().toJson(sh));
        						appRes.setAppName(sh.getAppName());
        						appRes.setAppPake(sh.getAppPack());
        						appRes.setAppType(getAppType(sh.getAppType()));
        						appRes.setImg(sh.getAppUrlImg());
        						appRes.setUrl(sh.getAppUrl());
        						appRes.setId(sh.getId());
        						appRes.setTAGID(a.getTagId());
        						appRes.setType(sh.getIsSelf());
        						appRes.setIsHot(a.getIsHot());
        						appRes.setLocalstatus(0);
        						appRes.setAppSize(sh.getAppSize());
        						apps.add(appRes);
        						model.setApps(apps);
				}
        		 models.add(model);
			 }
        	 res.setModels(models);
        	 
        	 
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException();
		}
		
		
		return NotifyUtil.success(res);
		
	}



	@Override
	public String gamePeckDatail(String token, String appPack) {
		Byte s = 0;
		UserInfoBean infoBean = queryTokenByUser(token);
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		} 
		/*AppCouponCodeExample example = new AppCouponCodeExample();
		example.createCriteria().and*/
		 AppCoupon list = appCouponMapper.selectByAppPack(appPack);
		 
		 AppCouponCodeExample example = new AppCouponCodeExample();
		 example.createCriteria().andCouponIdEqualTo(list.getId()).andStatusEqualTo(s);
		 List<AppCouponCode> list2 = appCouponCodeMapper.selectByExample(example);
		 list.setTotalNumber(list2.size());
	//	 List<AppCoupon> list2 = new ArrayList<AppCoupon>();
		 /*for (AppCoupon appCoupon : list) {
			 
			 AppCouponCodeExample example = new AppCouponCodeExample();
				
				example.createCriteria().andCouponIdEqualTo(appCoupon.getId()).andUserIdEqualTo(infoBean.getId());
				List<AppCouponCode> list1 = appCouponCodeMapper.selectByExample(example);
				if(list1.size() > 0){
					byte ss = -1;
					appCoupon.setStatus(ss);
				//	list2.a
				}
				
		}*/
		 return NotifyUtil.success(list);
	}

}
