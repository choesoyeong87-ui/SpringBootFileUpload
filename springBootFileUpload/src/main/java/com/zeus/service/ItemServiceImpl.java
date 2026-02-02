package com.zeus.service;

import java.util.List;

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
	public int update(Item item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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
