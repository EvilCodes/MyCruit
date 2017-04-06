package com.oucre.service;

import java.util.Map;

import com.oucre.core.mode.json.AjaxJson;
import com.oucre.core.mode.search.EasyUiPager;
import com.oucre.pojo.Announcement;

public interface AnnouncementManagerService {

	public AjaxJson addAnnouncementManager(Announcement entity);

	public AjaxJson updAnnouncementManager(Announcement entity);

	public Announcement findAnnouncement(Integer id);

	public AjaxJson delAnnouncementManager(String id);

	public Map<String, Object> findAnnouncementSearch(Map<String, Object> map, EasyUiPager easyUiPager);

}
