package com.yqbing.servicebing.repository.database.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppCouponExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNull() {
            addCriterion("link_url is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("link_url is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("link_url =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("link_url <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("link_url >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("link_url >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("link_url <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("link_url <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("link_url like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("link_url not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("link_url in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("link_url not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("link_url between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("link_url not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeIsNull() {
            addCriterion("is_display_qrcode is null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeIsNotNull() {
            addCriterion("is_display_qrcode is not null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeEqualTo(Byte value) {
            addCriterion("is_display_qrcode =", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeNotEqualTo(Byte value) {
            addCriterion("is_display_qrcode <>", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeGreaterThan(Byte value) {
            addCriterion("is_display_qrcode >", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_display_qrcode >=", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeLessThan(Byte value) {
            addCriterion("is_display_qrcode <", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeLessThanOrEqualTo(Byte value) {
            addCriterion("is_display_qrcode <=", value, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeIn(List<Byte> values) {
            addCriterion("is_display_qrcode in", values, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeNotIn(List<Byte> values) {
            addCriterion("is_display_qrcode not in", values, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeBetween(Byte value1, Byte value2) {
            addCriterion("is_display_qrcode between", value1, value2, "isDisplayQrcode");
            return (Criteria) this;
        }

        public Criteria andIsDisplayQrcodeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_display_qrcode not between", value1, value2, "isDisplayQrcode");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNull() {
            addCriterion("publishtime is null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNotNull() {
            addCriterion("publishtime is not null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeEqualTo(Date value) {
            addCriterion("publishtime =", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotEqualTo(Date value) {
            addCriterion("publishtime <>", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThan(Date value) {
            addCriterion("publishtime >", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publishtime >=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThan(Date value) {
            addCriterion("publishtime <", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThanOrEqualTo(Date value) {
            addCriterion("publishtime <=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIn(List<Date> values) {
            addCriterion("publishtime in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotIn(List<Date> values) {
            addCriterion("publishtime not in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeBetween(Date value1, Date value2) {
            addCriterion("publishtime between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotBetween(Date value1, Date value2) {
            addCriterion("publishtime not between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("category_id like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("category_id not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNull() {
            addCriterion("total_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNotNull() {
            addCriterion("total_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberEqualTo(Integer value) {
            addCriterion("total_number =", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotEqualTo(Integer value) {
            addCriterion("total_number <>", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThan(Integer value) {
            addCriterion("total_number >", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_number >=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThan(Integer value) {
            addCriterion("total_number <", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_number <=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIn(List<Integer> values) {
            addCriterion("total_number in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotIn(List<Integer> values) {
            addCriterion("total_number not in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_number between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_number not between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeIsNull() {
            addCriterion("qrcode_type is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeIsNotNull() {
            addCriterion("qrcode_type is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeEqualTo(String value) {
            addCriterion("qrcode_type =", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeNotEqualTo(String value) {
            addCriterion("qrcode_type <>", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeGreaterThan(String value) {
            addCriterion("qrcode_type >", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("qrcode_type >=", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeLessThan(String value) {
            addCriterion("qrcode_type <", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeLessThanOrEqualTo(String value) {
            addCriterion("qrcode_type <=", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeLike(String value) {
            addCriterion("qrcode_type like", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeNotLike(String value) {
            addCriterion("qrcode_type not like", value, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeIn(List<String> values) {
            addCriterion("qrcode_type in", values, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeNotIn(List<String> values) {
            addCriterion("qrcode_type not in", values, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeBetween(String value1, String value2) {
            addCriterion("qrcode_type between", value1, value2, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andQrcodeTypeNotBetween(String value1, String value2) {
            addCriterion("qrcode_type not between", value1, value2, "qrcodeType");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andIsShowIosIsNull() {
            addCriterion("is_show_ios is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIosIsNotNull() {
            addCriterion("is_show_ios is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowIosEqualTo(String value) {
            addCriterion("is_show_ios =", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosNotEqualTo(String value) {
            addCriterion("is_show_ios <>", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosGreaterThan(String value) {
            addCriterion("is_show_ios >", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosGreaterThanOrEqualTo(String value) {
            addCriterion("is_show_ios >=", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosLessThan(String value) {
            addCriterion("is_show_ios <", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosLessThanOrEqualTo(String value) {
            addCriterion("is_show_ios <=", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosLike(String value) {
            addCriterion("is_show_ios like", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosNotLike(String value) {
            addCriterion("is_show_ios not like", value, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosIn(List<String> values) {
            addCriterion("is_show_ios in", values, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosNotIn(List<String> values) {
            addCriterion("is_show_ios not in", values, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosBetween(String value1, String value2) {
            addCriterion("is_show_ios between", value1, value2, "isShowIos");
            return (Criteria) this;
        }

        public Criteria andIsShowIosNotBetween(String value1, String value2) {
            addCriterion("is_show_ios not between", value1, value2, "isShowIos");
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

        public Criteria andIsApplyUrlIsNull() {
            addCriterion("is_apply_url is null");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlIsNotNull() {
            addCriterion("is_apply_url is not null");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlEqualTo(String value) {
            addCriterion("is_apply_url =", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlNotEqualTo(String value) {
            addCriterion("is_apply_url <>", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlGreaterThan(String value) {
            addCriterion("is_apply_url >", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("is_apply_url >=", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlLessThan(String value) {
            addCriterion("is_apply_url <", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlLessThanOrEqualTo(String value) {
            addCriterion("is_apply_url <=", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlLike(String value) {
            addCriterion("is_apply_url like", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlNotLike(String value) {
            addCriterion("is_apply_url not like", value, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlIn(List<String> values) {
            addCriterion("is_apply_url in", values, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlNotIn(List<String> values) {
            addCriterion("is_apply_url not in", values, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlBetween(String value1, String value2) {
            addCriterion("is_apply_url between", value1, value2, "isApplyUrl");
            return (Criteria) this;
        }

        public Criteria andIsApplyUrlNotBetween(String value1, String value2) {
            addCriterion("is_apply_url not between", value1, value2, "isApplyUrl");
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