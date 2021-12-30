package com.st.service;

import java.util.List;

import com.st.dao.GoodsDao;
import com.st.model.Goods;
import com.st.model.GoodsSearchBean;
import com.st.utils.PaginateInfo;

public class GoodsService {
	private GoodsDao dao = new GoodsDao();

	// 返回所有商品
	public List<Goods> findAll(GoodsSearchBean gsb, PaginateInfo p) {

		return dao.findAll(gsb, p);
	}

	// 根据ID删除商品数据
	public int deleteByIds(Integer[] ids) {
		return dao.deleteByIds(ids);
	}

	// 保存商品数据
	public boolean save(Goods goods) {
		return dao.save(goods);
	}

	// 更具Id查数据
	public Goods findById(Integer id) {
		return dao.findById(id);
	}

	// 修改商品信息
	public boolean update(Goods goods) {
		return dao.update(goods);
	}
}
