package com.st.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.st.common.Global;
import com.st.model.Goods;
import com.st.model.GoodsSearchBean;
import com.st.utils.DateRange;
import com.st.utils.JdbcUtil;
import com.st.utils.PaginateInfo;

public class GoodsDao {
	// 结果集转化器
	BeanListHandler<Goods> hangler = new BeanListHandler<>(Goods.class);

	// 查询数据库数据
	public List<Goods> findAll(GoodsSearchBean gsb, PaginateInfo p) {
		Connection conn = Global.getConnection();
		QueryRunner qr = new QueryRunner();

		// 查询总共多少行数据
		String sql = "select count(id) from t_goods";// 总记录数

		String condition = " where 1=1 ";// 查询条件
		List<Object> args = new ArrayList<>();

		if (gsb.getId() != null) {
			condition += " and id = ?";
			args.add(gsb.getId());
		}
		if (gsb.getGoodsId() != null && !(gsb.getGoodsId().trim().length() == 0)) {
			condition += " and goods_id = ?";
			args.add(gsb.getGoodsId());
		}
		if (gsb.getName() != null && !(gsb.getName().trim().length() == 0)) {
			condition += " and name like ?";
			args.add("%" + gsb.getName() + "%");
		}
		if (gsb.getStaff() != null && !(gsb.getStaff().trim().length() == 0)) {
			condition += " and staff like ?";
			args.add("%" + gsb.getStaff() + "%");
		}
		if (gsb.getIsArrival() != null && !(gsb.getIsArrival().trim().length() == 0)) {
			condition += " and isarrival like ?";
			args.add("%" + gsb.getIsArrival() + "%");
		}
		if (gsb.getTimeRange() != null) {
			DateRange dr = gsb.getTimeRange();
			if (dr.getFrom() != null) {
				condition += " and time >= ?";
				args.add(dr.getFrom());
			}
			if (dr.getTo() != null) {
				condition += " and time < ?";
				args.add(dr.getTo());
			}
		}

		Integer count = null;
		try {
			count = qr.query(conn, sql + condition, new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					rs.next();
					return rs.getInt(1);
				}
			}, args.toArray());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		p.setCount(count);// 将记录总数设置到分页对象中

		// 查询所有记录数据
		sql = "select id,goods_id as goodsId,name,unit,innum,outnum,price,place,staff,time,isarrival from t_goods "
				+ condition + " limit ?,?";

		List<Goods> goods1 = null;
		try {
			args.add(p.getoffset());// 分页参数
			args.add(p.getlimit());
			goods1 = qr.query(conn, sql, hangler, args.toArray());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询学生数据异常，联系管理员");
		} finally {
			JdbcUtil.closeConnection(conn);

		}
		return goods1;
	}

	// 根据ID删除数据
	public int deleteByIds(Integer[] ids) {
		// 对id数组的数据个数进行判断
		if (ids == null || ids.length == 0) {
			return -1;
		}
		Connection conn = Global.getConnection();
		QueryRunner qr = new QueryRunner();
		// 当选中的数据不为空时
		String sql = "";
		for (int i = 0; i < ids.length; i++) {
			sql += "?,";
		}
		sql = sql.substring(0, sql.length() - 1);

		// 数据库删除语句
		sql = "delete from t_goods where id in (" + sql + ")";

		//
		try {
			// 删除的行数
			int rows = qr.update(conn, sql, (Object[]) ids);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("删除商品时异常");
		}

	}

	// 保存数据
	public boolean save(Goods goods) {
		// 对id数组的数据个数进行判断
		if (goods == null) {
			throw new RuntimeException("保存的学生数据为空");
		}
		Connection conn = Global.getConnection();
		QueryRunner qr = new QueryRunner();

		String sql = "insert into t_goods (goods_id,name,unit,innum,outnum,price,place,staff,time,isarrival) values (?,?,?,?,?,?,?,?,?,?)";

		//
		try {
			// 删除的行数
			int rows = qr.update(conn, sql, goods.getGoodsId(), goods.getName(), goods.getUnit(), goods.getInNum(),
					goods.getOutNum(), goods.getPrice(), goods.getPlace(), goods.getStaff(), goods.getTime(),
					goods.getIsArrival());
			return rows > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("插入商品时异常");
		}
	}

	// 根据id查一个数据
	public Goods findById(Integer id) {
		Connection conn = Global.getConnection();
		QueryRunner qr = new QueryRunner();

		// 查询所有记录数据
		String sql = "select id,goods_id as goodsId,name,unit,innum,outnum,price,place,staff,time,isarrival from t_goods where id=?";

		List<Goods> goods1 = null;
		try {
			goods1 = qr.query(conn, sql, hangler, id);
			if (goods1 == null || goods1.size() == 0) {
				return null;
			} else {
				return goods1.get(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询学生数据异常，联系管理员");
		} finally {
			JdbcUtil.closeConnection(conn);

		}

	}

	public boolean update(Goods goods) {
		if (goods == null) {
			throw new RuntimeException("要修改的学生实例为空");
		}

		Connection conn = Global.getConnection();
		QueryRunner qr = new QueryRunner();

		String sql = "update t_goods set goods_id=?,name=?,unit=?,innum=?,outnum=?,price=?,place=?,staff=?,time=?,isarrival=? where id=?";

		try {
			int rows = qr.update(conn, sql, goods.getGoodsId(), goods.getName(), goods.getUnit(), goods.getInNum(),
					goods.getOutNum(), goods.getPrice(), goods.getPlace(), goods.getStaff(), goods.getTime(),
					goods.getIsArrival(), goods.getId());
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改学生信息时异常");
		}
	}

}
