package com.oucre.core.util;

/**
 * 全局公共常量表
 */
public interface Pub {
	/** 我自己转化 **/
	String CONVER_BY_MYSELF = "1";
	/** 任意人可以转化 **/
	String CONVER_BY_ANYONE = "0";
	/** 转化剩余日期 **/
	int CONVER_DAYS = 30;
	
	
	/** --------------------------------------学生状态------------------------------------ **/
	/** 学生录入状态，提交申请 **/
	String STATUS_APPLY_ENTRING = "1";
	/** 学生信息审核通过 **/
	String STATUS_VARIFY_INFO_SUCCESS = "2";
	/** 学生信息审核不通过 **/
	String STATUS_VERIFY_INFO_FAILD = "3";
	/** 转化审核申请 **/
	String STATUS_APPLY_COVER = "4";
	/** 转化审核通过 **/
	String STATUS_VERIFY_COVER_SUCCESS = "5";
	/** 转化审核失败 **/
	String STATUS_VERIFY_COVER_FAILD = "6";
	/** 转化失败 **/
	String STATUS_COVER_FAILD = "7";
	
	/** --------------------------------------系统角色ID------------------------------------ **/
	/** 介绍人 **/
	Integer ROLE_ID_USER_INTRODUCER = 22;
	
	/** 审核人 **/
	Integer ROLE_ID_USER_VERIFYER = 24;
}
