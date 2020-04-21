package com.yqbing.servicebing.service;

import com.yqbing.servicebing.repository.jihu.bean.TUserInfoBean;
import com.yqbing.servicebing.webapp.request.AddressReq;

public interface LuckdrawService {



	String signed(TUserInfoBean validToken)throws Exception ;

	String homePage(TUserInfoBean validToken)throws Exception;

	String awardrec(TUserInfoBean validToken,Integer page,Integer size)throws Exception;

	String action(TUserInfoBean validToken, Long id)throws Exception;

	String myaward(TUserInfoBean validToken, String type, String page, String size);


	String exchange(AddressReq req, TUserInfoBean validToken);

	String getdis();

}
