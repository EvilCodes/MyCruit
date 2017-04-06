package com.oucre.dao;

import java.util.Map;

import com.oucre.core.dao.BaseDao;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Announcement;

public interface AnnouncementDao extends BaseDao<Announcement> {
	public Map<String, Object> findAnnouncementSearch(Map<String, Object> map, EasyUiPager easyUiPager);

	public Announcement findAnnouncementTopOne();
}
