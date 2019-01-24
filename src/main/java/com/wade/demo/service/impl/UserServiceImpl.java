package com.wade.demo.service.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.demo.domain.entity.User;
import com.wade.demo.domain.entity.User.Field;
import com.wade.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Dao dao;

	@Override
	public List<User> listUsers() {
		return dao.query(User.class, Cnd.where("deleted_at", "=", 0));
	}

	@Override
	public User getById(long id) {
		Sql sql = Sqls.queryRecord(dao.sqls().get("get.by.id"));
		sql.setParam(Field.ID, id);
		return dao.execute(sql).getObject(User.class);
	}
}
