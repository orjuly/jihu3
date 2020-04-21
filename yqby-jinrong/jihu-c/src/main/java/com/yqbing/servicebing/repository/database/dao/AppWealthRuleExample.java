package com.yqbing.servicebing.repository.database.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppWealthRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppWealthRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAppPackIsNull() {
            addCriterion("app_pack is null");
            return (Criteria) this;
        }

        public Criteria andAppPackIsNotNull() {
            addCriterion("app_pack is not null");
            return (Criteria) this;
        }

        public Criteria andAppPackEqualTo(String value) {
            addCriterion("app_pack =", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackNotEqualTo(String value) {
            addCriterion("app_pack <>", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackGreaterThan(String value) {
            addCriterion("app_pack >", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackGreaterThanOrEqualTo(String value) {
            addCriterion("app_pack >=", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackLessThan(String value) {
            addCriterion("app_pack <", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackLessThanOrEqualTo(String value) {
            addCriterion("app_pack <=", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackLike(String value) {
            addCriterion("app_pack like", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackNotLike(String value) {
            addCriterion("app_pack not like", value, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackIn(List<String> values) {
            addCriterion("app_pack in", values, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackNotIn(List<String> values) {
            addCriterion("app_pack not in", values, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackBetween(String value1, String value2) {
            addCriterion("app_pack between", value1, value2, "appPack");
            return (Criteria) this;
        }

        public Criteria andAppPackNotBetween(String value1, String value2) {
            addCriterion("app_pack not between", value1, value2, "appPack");
            return (Criteria) this;
        }

        public Criteria andIsOpenIsNull() {
            addCriterion("is_open is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenIsNotNull() {
            addCriterion("is_open is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenEqualTo(String value) {
            addCriterion("is_open =", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotEqualTo(String value) {
            addCriterion("is_open <>", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenGreaterThan(String value) {
            addCriterion("is_open >", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenGreaterThanOrEqualTo(String value) {
            addCriterion("is_open >=", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLessThan(String value) {
            addCriterion("is_open <", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLessThanOrEqualTo(String value) {
            addCriterion("is_open <=", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLike(String value) {
            addCriterion("is_open like", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotLike(String value) {
            addCriterion("is_open not like", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenIn(List<String> values) {
            addCriterion("is_open in", values, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotIn(List<String> values) {
            addCriterion("is_open not in", values, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenBetween(String value1, String value2) {
            addCriterion("is_open between", value1, value2, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotBetween(String value1, String value2) {
            addCriterion("is_open not between", value1, value2, "isOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenIsNull() {
            addCriterion("reward_is_open is null");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenIsNotNull() {
            addCriterion("reward_is_open is not null");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenEqualTo(String value) {
            addCriterion("reward_is_open =", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenNotEqualTo(String value) {
            addCriterion("reward_is_open <>", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenGreaterThan(String value) {
            addCriterion("reward_is_open >", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenGreaterThanOrEqualTo(String value) {
            addCriterion("reward_is_open >=", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenLessThan(String value) {
            addCriterion("reward_is_open <", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenLessThanOrEqualTo(String value) {
            addCriterion("reward_is_open <=", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenLike(String value) {
            addCriterion("reward_is_open like", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenNotLike(String value) {
            addCriterion("reward_is_open not like", value, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenIn(List<String> values) {
            addCriterion("reward_is_open in", values, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenNotIn(List<String> values) {
            addCriterion("reward_is_open not in", values, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenBetween(String value1, String value2) {
            addCriterion("reward_is_open between", value1, value2, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andRewardIsOpenNotBetween(String value1, String value2) {
            addCriterion("reward_is_open not between", value1, value2, "rewardIsOpen");
            return (Criteria) this;
        }

        public Criteria andJihuRateIsNull() {
            addCriterion("jihu_rate is null");
            return (Criteria) this;
        }

        public Criteria andJihuRateIsNotNull() {
            addCriterion("jihu_rate is not null");
            return (Criteria) this;
        }

        public Criteria andJihuRateEqualTo(Integer value) {
            addCriterion("jihu_rate =", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateNotEqualTo(Integer value) {
            addCriterion("jihu_rate <>", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateGreaterThan(Integer value) {
            addCriterion("jihu_rate >", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("jihu_rate >=", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateLessThan(Integer value) {
            addCriterion("jihu_rate <", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateLessThanOrEqualTo(Integer value) {
            addCriterion("jihu_rate <=", value, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateIn(List<Integer> values) {
            addCriterion("jihu_rate in", values, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateNotIn(List<Integer> values) {
            addCriterion("jihu_rate not in", values, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateBetween(Integer value1, Integer value2) {
            addCriterion("jihu_rate between", value1, value2, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andJihuRateNotBetween(Integer value1, Integer value2) {
            addCriterion("jihu_rate not between", value1, value2, "jihuRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateIsNull() {
            addCriterion("channel_rate is null");
            return (Criteria) this;
        }

        public Criteria andChannelRateIsNotNull() {
            addCriterion("channel_rate is not null");
            return (Criteria) this;
        }

        public Criteria andChannelRateEqualTo(Integer value) {
            addCriterion("channel_rate =", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateNotEqualTo(Integer value) {
            addCriterion("channel_rate <>", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateGreaterThan(Integer value) {
            addCriterion("channel_rate >", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_rate >=", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateLessThan(Integer value) {
            addCriterion("channel_rate <", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateLessThanOrEqualTo(Integer value) {
            addCriterion("channel_rate <=", value, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateIn(List<Integer> values) {
            addCriterion("channel_rate in", values, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateNotIn(List<Integer> values) {
            addCriterion("channel_rate not in", values, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateBetween(Integer value1, Integer value2) {
            addCriterion("channel_rate between", value1, value2, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelRateNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_rate not between", value1, value2, "channelRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateIsNull() {
            addCriterion("channel_agent_rate is null");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateIsNotNull() {
            addCriterion("channel_agent_rate is not null");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateEqualTo(Integer value) {
            addCriterion("channel_agent_rate =", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateNotEqualTo(Integer value) {
            addCriterion("channel_agent_rate <>", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateGreaterThan(Integer value) {
            addCriterion("channel_agent_rate >", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_agent_rate >=", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateLessThan(Integer value) {
            addCriterion("channel_agent_rate <", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateLessThanOrEqualTo(Integer value) {
            addCriterion("channel_agent_rate <=", value, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateIn(List<Integer> values) {
            addCriterion("channel_agent_rate in", values, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateNotIn(List<Integer> values) {
            addCriterion("channel_agent_rate not in", values, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateBetween(Integer value1, Integer value2) {
            addCriterion("channel_agent_rate between", value1, value2, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelAgentRateNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_agent_rate not between", value1, value2, "channelAgentRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateIsNull() {
            addCriterion("channel_store_rate is null");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateIsNotNull() {
            addCriterion("channel_store_rate is not null");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateEqualTo(Integer value) {
            addCriterion("channel_store_rate =", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateNotEqualTo(Integer value) {
            addCriterion("channel_store_rate <>", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateGreaterThan(Integer value) {
            addCriterion("channel_store_rate >", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_store_rate >=", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateLessThan(Integer value) {
            addCriterion("channel_store_rate <", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateLessThanOrEqualTo(Integer value) {
            addCriterion("channel_store_rate <=", value, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateIn(List<Integer> values) {
            addCriterion("channel_store_rate in", values, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateNotIn(List<Integer> values) {
            addCriterion("channel_store_rate not in", values, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateBetween(Integer value1, Integer value2) {
            addCriterion("channel_store_rate between", value1, value2, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andChannelStoreRateNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_store_rate not between", value1, value2, "channelStoreRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateIsNull() {
            addCriterion("agent_city_rate is null");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateIsNotNull() {
            addCriterion("agent_city_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateEqualTo(Integer value) {
            addCriterion("agent_city_rate =", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateNotEqualTo(Integer value) {
            addCriterion("agent_city_rate <>", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateGreaterThan(Integer value) {
            addCriterion("agent_city_rate >", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_city_rate >=", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateLessThan(Integer value) {
            addCriterion("agent_city_rate <", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateLessThanOrEqualTo(Integer value) {
            addCriterion("agent_city_rate <=", value, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateIn(List<Integer> values) {
            addCriterion("agent_city_rate in", values, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateNotIn(List<Integer> values) {
            addCriterion("agent_city_rate not in", values, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateBetween(Integer value1, Integer value2) {
            addCriterion("agent_city_rate between", value1, value2, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCityRateNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_city_rate not between", value1, value2, "agentCityRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateIsNull() {
            addCriterion("agent_county_rate is null");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateIsNotNull() {
            addCriterion("agent_county_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateEqualTo(Integer value) {
            addCriterion("agent_county_rate =", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateNotEqualTo(Integer value) {
            addCriterion("agent_county_rate <>", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateGreaterThan(Integer value) {
            addCriterion("agent_county_rate >", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_county_rate >=", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateLessThan(Integer value) {
            addCriterion("agent_county_rate <", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateLessThanOrEqualTo(Integer value) {
            addCriterion("agent_county_rate <=", value, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateIn(List<Integer> values) {
            addCriterion("agent_county_rate in", values, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateNotIn(List<Integer> values) {
            addCriterion("agent_county_rate not in", values, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateBetween(Integer value1, Integer value2) {
            addCriterion("agent_county_rate between", value1, value2, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andAgentCountyRateNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_county_rate not between", value1, value2, "agentCountyRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateIsNull() {
            addCriterion("store_shopkeeper_rate is null");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateIsNotNull() {
            addCriterion("store_shopkeeper_rate is not null");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateEqualTo(Integer value) {
            addCriterion("store_shopkeeper_rate =", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateNotEqualTo(Integer value) {
            addCriterion("store_shopkeeper_rate <>", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateGreaterThan(Integer value) {
            addCriterion("store_shopkeeper_rate >", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_shopkeeper_rate >=", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateLessThan(Integer value) {
            addCriterion("store_shopkeeper_rate <", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateLessThanOrEqualTo(Integer value) {
            addCriterion("store_shopkeeper_rate <=", value, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateIn(List<Integer> values) {
            addCriterion("store_shopkeeper_rate in", values, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateNotIn(List<Integer> values) {
            addCriterion("store_shopkeeper_rate not in", values, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateBetween(Integer value1, Integer value2) {
            addCriterion("store_shopkeeper_rate between", value1, value2, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopkeeperRateNotBetween(Integer value1, Integer value2) {
            addCriterion("store_shopkeeper_rate not between", value1, value2, "storeShopkeeperRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateIsNull() {
            addCriterion("store_shopassistant_rate is null");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateIsNotNull() {
            addCriterion("store_shopassistant_rate is not null");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateEqualTo(Integer value) {
            addCriterion("store_shopassistant_rate =", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateNotEqualTo(Integer value) {
            addCriterion("store_shopassistant_rate <>", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateGreaterThan(Integer value) {
            addCriterion("store_shopassistant_rate >", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_shopassistant_rate >=", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateLessThan(Integer value) {
            addCriterion("store_shopassistant_rate <", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateLessThanOrEqualTo(Integer value) {
            addCriterion("store_shopassistant_rate <=", value, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateIn(List<Integer> values) {
            addCriterion("store_shopassistant_rate in", values, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateNotIn(List<Integer> values) {
            addCriterion("store_shopassistant_rate not in", values, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateBetween(Integer value1, Integer value2) {
            addCriterion("store_shopassistant_rate between", value1, value2, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andStoreShopassistantRateNotBetween(Integer value1, Integer value2) {
            addCriterion("store_shopassistant_rate not between", value1, value2, "storeShopassistantRate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNull() {
            addCriterion("store_type is null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNotNull() {
            addCriterion("store_type is not null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeEqualTo(String value) {
            addCriterion("store_type =", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotEqualTo(String value) {
            addCriterion("store_type <>", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThan(String value) {
            addCriterion("store_type >", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThanOrEqualTo(String value) {
            addCriterion("store_type >=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThan(String value) {
            addCriterion("store_type <", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThanOrEqualTo(String value) {
            addCriterion("store_type <=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLike(String value) {
            addCriterion("store_type like", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotLike(String value) {
            addCriterion("store_type not like", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIn(List<String> values) {
            addCriterion("store_type in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotIn(List<String> values) {
            addCriterion("store_type not in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeBetween(String value1, String value2) {
            addCriterion("store_type between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotBetween(String value1, String value2) {
            addCriterion("store_type not between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeIsNull() {
            addCriterion("store_sub_type is null");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeIsNotNull() {
            addCriterion("store_sub_type is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeEqualTo(String value) {
            addCriterion("store_sub_type =", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeNotEqualTo(String value) {
            addCriterion("store_sub_type <>", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeGreaterThan(String value) {
            addCriterion("store_sub_type >", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeGreaterThanOrEqualTo(String value) {
            addCriterion("store_sub_type >=", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeLessThan(String value) {
            addCriterion("store_sub_type <", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeLessThanOrEqualTo(String value) {
            addCriterion("store_sub_type <=", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeLike(String value) {
            addCriterion("store_sub_type like", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeNotLike(String value) {
            addCriterion("store_sub_type not like", value, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeIn(List<String> values) {
            addCriterion("store_sub_type in", values, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeNotIn(List<String> values) {
            addCriterion("store_sub_type not in", values, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeBetween(String value1, String value2) {
            addCriterion("store_sub_type between", value1, value2, "storeSubType");
            return (Criteria) this;
        }

        public Criteria andStoreSubTypeNotBetween(String value1, String value2) {
            addCriterion("store_sub_type not between", value1, value2, "storeSubType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}