package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	RedisTemplate<String, List<Category>> redisTemplate;
	  
	
	public List<Category> list(){
		List<Category> lt = redisTemplate.opsForValue().get("list");
		if (null == lt) {
			lt = categoryMapper.list();
			redisTemplate.opsForValue().set("list", lt);
			System.out.println("通过数据库查询数据");
		}else {
			System.out.println("通过redis查询数据");
		}
		return lt;
	}
}
