package com.yqbing.servicebing.repository.database.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalFineFoodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LocalFineFoodExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", "%"+value+"%", "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Short value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Short value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Short value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Short value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Short value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Short> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Short> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Short value1, Short value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Short value1, Short value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andPerPriceIsNull() {
            addCriterion("per_price is null");
            return (Criteria) this;
        }

        public Criteria andPerPriceIsNotNull() {
            addCriterion("per_price is not null");
            return (Criteria) this;
        }

        public Criteria andPerPriceEqualTo(Short value) {
            addCriterion("per_price =", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceNotEqualTo(Short value) {
            addCriterion("per_price <>", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceGreaterThan(Short value) {
            addCriterion("per_price >", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceGreaterThanOrEqualTo(Short value) {
            addCriterion("per_price >=", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceLessThan(Short value) {
            addCriterion("per_price <", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceLessThanOrEqualTo(Short value) {
            addCriterion("per_price <=", value, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceIn(List<Short> values) {
            addCriterion("per_price in", values, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceNotIn(List<Short> values) {
            addCriterion("per_price not in", values, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceBetween(Short value1, Short value2) {
            addCriterion("per_price between", value1, value2, "perPrice");
            return (Criteria) this;
        }

        public Criteria andPerPriceNotBetween(Short value1, Short value2) {
            addCriterion("per_price not between", value1, value2, "perPrice");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursIsNull() {
            addCriterion("business_hours is null");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursIsNotNull() {
            addCriterion("business_hours is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursEqualTo(String value) {
            addCriterion("business_hours =", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursNotEqualTo(String value) {
            addCriterion("business_hours <>", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursGreaterThan(String value) {
            addCriterion("business_hours >", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursGreaterThanOrEqualTo(String value) {
            addCriterion("business_hours >=", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursLessThan(String value) {
            addCriterion("business_hours <", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursLessThanOrEqualTo(String value) {
            addCriterion("business_hours <=", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursLike(String value) {
            addCriterion("business_hours like", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursNotLike(String value) {
            addCriterion("business_hours not like", value, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursIn(List<String> values) {
            addCriterion("business_hours in", values, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursNotIn(List<String> values) {
            addCriterion("business_hours not in", values, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursBetween(String value1, String value2) {
            addCriterion("business_hours between", value1, value2, "businessHours");
            return (Criteria) this;
        }

        public Criteria andBusinessHoursNotBetween(String value1, String value2) {
            addCriterion("business_hours not between", value1, value2, "businessHours");
            return (Criteria) this;
        }

        public Criteria andAdressIsNull() {
            addCriterion("adress is null");
            return (Criteria) this;
        }

        public Criteria andAdressIsNotNull() {
            addCriterion("adress is not null");
            return (Criteria) this;
        }

        public Criteria andAdressEqualTo(String value) {
            addCriterion("adress =", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotEqualTo(String value) {
            addCriterion("adress <>", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressGreaterThan(String value) {
            addCriterion("adress >", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressGreaterThanOrEqualTo(String value) {
            addCriterion("adress >=", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLessThan(String value) {
            addCriterion("adress <", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLessThanOrEqualTo(String value) {
            addCriterion("adress <=", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLike(String value) {
            addCriterion("adress like", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotLike(String value) {
            addCriterion("adress not like", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressIn(List<String> values) {
            addCriterion("adress in", values, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotIn(List<String> values) {
            addCriterion("adress not in", values, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressBetween(String value1, String value2) {
            addCriterion("adress between", value1, value2, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotBetween(String value1, String value2) {
            addCriterion("adress not between", value1, value2, "adress");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesIsNull() {
            addCriterion("special_dishes is null");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesIsNotNull() {
            addCriterion("special_dishes is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesEqualTo(String value) {
            addCriterion("special_dishes =", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesNotEqualTo(String value) {
            addCriterion("special_dishes <>", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesGreaterThan(String value) {
            addCriterion("special_dishes >", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesGreaterThanOrEqualTo(String value) {
            addCriterion("special_dishes >=", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesLessThan(String value) {
            addCriterion("special_dishes <", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesLessThanOrEqualTo(String value) {
            addCriterion("special_dishes <=", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesLike(String value) {
            addCriterion("special_dishes like", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesNotLike(String value) {
            addCriterion("special_dishes not like", value, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesIn(List<String> values) {
            addCriterion("special_dishes in", values, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesNotIn(List<String> values) {
            addCriterion("special_dishes not in", values, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesBetween(String value1, String value2) {
            addCriterion("special_dishes between", value1, value2, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andSpecialDishesNotBetween(String value1, String value2) {
            addCriterion("special_dishes not between", value1, value2, "specialDishes");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
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

        public Criteria andHeadimgIsNull() {
            addCriterion("headimg is null");
            return (Criteria) this;
        }

        public Criteria andHeadimgIsNotNull() {
            addCriterion("headimg is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimgEqualTo(String value) {
            addCriterion("headimg =", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgNotEqualTo(String value) {
            addCriterion("headimg <>", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgGreaterThan(String value) {
            addCriterion("headimg >", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgGreaterThanOrEqualTo(String value) {
            addCriterion("headimg >=", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgLessThan(String value) {
            addCriterion("headimg <", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgLessThanOrEqualTo(String value) {
            addCriterion("headimg <=", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgLike(String value) {
            addCriterion("headimg like", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgNotLike(String value) {
            addCriterion("headimg not like", value, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgIn(List<String> values) {
            addCriterion("headimg in", values, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgNotIn(List<String> values) {
            addCriterion("headimg not in", values, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgBetween(String value1, String value2) {
            addCriterion("headimg between", value1, value2, "headimg");
            return (Criteria) this;
        }

        public Criteria andHeadimgNotBetween(String value1, String value2) {
            addCriterion("headimg not between", value1, value2, "headimg");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIsNull() {
            addCriterion("food_type is null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIsNotNull() {
            addCriterion("food_type is not null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeEqualTo(String value) {
            addCriterion("food_type =", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotEqualTo(String value) {
            addCriterion("food_type <>", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeGreaterThan(String value) {
            addCriterion("food_type >", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeGreaterThanOrEqualTo(String value) {
            addCriterion("food_type >=", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeLessThan(String value) {
            addCriterion("food_type <", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeLessThanOrEqualTo(String value) {
            addCriterion("food_type <=", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeLike(String value) {
            addCriterion("food_type like", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotLike(String value) {
            addCriterion("food_type not like", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIn(List<String> values) {
            addCriterion("food_type in", values, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotIn(List<String> values) {
            addCriterion("food_type not in", values, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeBetween(String value1, String value2) {
            addCriterion("food_type between", value1, value2, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotBetween(String value1, String value2) {
            addCriterion("food_type not between", value1, value2, "foodType");
            return (Criteria) this;
        }

        public Criteria andCookStyleIsNull() {
            addCriterion("cook_style is null");
            return (Criteria) this;
        }

        public Criteria andCookStyleIsNotNull() {
            addCriterion("cook_style is not null");
            return (Criteria) this;
        }

        public Criteria andCookStyleEqualTo(String value) {
            addCriterion("cook_style =", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleNotEqualTo(String value) {
            addCriterion("cook_style <>", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleGreaterThan(String value) {
            addCriterion("cook_style >", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleGreaterThanOrEqualTo(String value) {
            addCriterion("cook_style >=", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleLessThan(String value) {
            addCriterion("cook_style <", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleLessThanOrEqualTo(String value) {
            addCriterion("cook_style <=", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleLike(String value) {
            addCriterion("cook_style like", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleNotLike(String value) {
            addCriterion("cook_style not like", value, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleIn(List<String> values) {
            addCriterion("cook_style in", values, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleNotIn(List<String> values) {
            addCriterion("cook_style not in", values, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleBetween(String value1, String value2) {
            addCriterion("cook_style between", value1, value2, "cookStyle");
            return (Criteria) this;
        }

        public Criteria andCookStyleNotBetween(String value1, String value2) {
            addCriterion("cook_style not between", value1, value2, "cookStyle");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("city_code like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("city_code not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
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