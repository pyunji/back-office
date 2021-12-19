package com.mycompany.webapp.dto.member;

import java.util.List;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.vo.member.EventVo;

import lombok.Data;

@Data
public class EventResult {
	List<EventVo> eventList;
	Pager pager;
}
