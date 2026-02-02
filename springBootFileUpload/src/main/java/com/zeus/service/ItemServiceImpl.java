package com.zeus.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Item;
import com.zeus.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	
	@Transactional
	@Override
	public int create(Item item) throws Exception {
		int count = itemMapper.create(item);
		return count;
	}

	@Override
	public Item read(Item i) throws Exception {
		Item item = itemMapper.read(i);
		return item;
	}

	@Override
	@Transactional
	public int update(Item i) throws Exception {
		int count = itemMapper.update(i);
		return count;
	}

	@Override
	@Transactional
	public int delete(Item item) throws Exception {
		int count = itemMapper.delete(item);
		return count;
	}

	@Override
	public List<Item> list() throws Exception {
		return itemMapper.list();
	}

	@Override
	public String getPicture(Item item) throws Exception {
		 String url = itemMapper.getPicture(item);
		return url;
	}

}
