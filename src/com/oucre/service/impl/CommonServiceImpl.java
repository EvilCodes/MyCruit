package com.oucre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oucre.dao.AnnouncementDao;
import com.oucre.pojo.Announcement;
import com.oucre.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private AnnouncementDao announcementDao;

	@Override
	public Announcement findAnnouncementTopOne() {
		return announcementDao.findAnnouncementTopOne();
	}

}
