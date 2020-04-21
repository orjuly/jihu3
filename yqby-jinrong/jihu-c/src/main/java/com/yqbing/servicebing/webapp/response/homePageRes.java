package com.yqbing.servicebing.webapp.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yqbing.servicebing.repository.database.bean.AppBanner;
@ApiModel(value = "首页")
public class homePageRes  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772448200310705267L;
	@ApiModelProperty(value = "首页大图", name = "pic")
	private List<AppBanner> pic;//首页大图
	@ApiModelProperty(value = "所有应用", name = "apps")
	private Map<String,List<HomeAppRes>> apps;//
	public List<AppBanner> getPic() {
		return pic;
	}
	public void setPic(List<AppBanner> pic) {
		this.pic = pic;
	}
	public Map<String, List<HomeAppRes>> getApps() {
		return apps;
	}
	public void setApps(Map<String, List<HomeAppRes>> apps) {
		this.apps = apps;
	}
	
	
}
