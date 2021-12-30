package com.st.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.model.Goods;
import com.st.model.GoodsSearchBean;
import com.st.service.GoodsService;
import com.st.utils.DateRange;
import com.st.utils.DateUtil;
import com.st.utils.PaginateInfo;

@WebServlet("/goods/*")
public class GoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GoodsService service = new GoodsService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		PaginateInfo pi = PaginateInfo.DEFAULT;
		String action = req.getPathInfo();
		// System.out.println(action);
		switch (action) {
		// 选中查询
		case "/list":
			list(req, resp);
			break;
		// 选中删除
		case "/delete":
			delete(req, resp);
			break;
		// ajax删除
		case "/ajaxDelete":
			ajaxDelete(req, resp);
			break;
		case "/add":
			if (req.getMethod().toLowerCase().equals("get")) {
				gotoAdd(req, resp);// 跳转到添加页面
			} else {
				submitAdd(req, resp);
			}
			break;
		case "/edit":
			if (req.getMethod().toLowerCase().equals("get")) {
				gotoEdit(req, resp);// 跳转到修改页面
			} else {
				submitEdit(req, resp);
			}

		}

	}

	// 重构
	/**
	 * 创建列表查询
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		GoodsSearchBean gsb = new GoodsSearchBean();
		String id = req.getParameter("id");
		String goodsId = req.getParameter("goodsId");
		String name = req.getParameter("name");

		String staff = req.getParameter("staff");
		String timeRange = req.getParameter("timeRange");
		String isarrival = req.getParameter("isarrival");

		if (id != null && !(id.trim().length() == 0)) {
			gsb.setId(Integer.valueOf(id));
		}
		if (goodsId != null && !(goodsId.trim().length() == 0)) {
			gsb.setGoodsId(goodsId);
		}
		if (name != null && !(name.trim().length() == 0)) {
			gsb.setName(name);
		}
		if (staff != null && !(staff.trim().length() == 0)) {
			gsb.setStaff(staff);
		}
		if (isarrival != null && !(isarrival.trim().length() == 0)) {
			gsb.setIsArrival(isarrival);
		}
		if (timeRange != null && !(timeRange.trim().length() == 0)) {
			DateRange dr = DateRange.of(timeRange, " - ");
			gsb.setTimeRange(dr);
		}

		// 获取前端表单提交的参数
		Integer pageNo = 1;
		// 每页展示多少条信息
		Integer pageSize = 10;
		String sPageNo = req.getParameter("pageNo");
		if (sPageNo != null && !sPageNo.trim().equals("")) {// 意味着前端传递了pageNo这个参数
			pageNo = Integer.valueOf(sPageNo);

		}
		String sPageSize = req.getParameter("pageSize");
		if (sPageSize != null && !sPageSize.trim().equals("")) {
			pageSize = Integer.valueOf(sPageSize);
		}

		PaginateInfo pi = new PaginateInfo(pageNo, pageSize);
		pi.setNavItemCount(5);// 设置导航页个数

		List<Goods> goods1 = service.findAll(gsb, pi);

		Integer pages = pi.getTotalPages();
		req.setAttribute("pages", pages);

		// 当前页
		req.setAttribute("pageNo", pi.getPageNo());

		req.setAttribute("goods1", goods1);
//				System.out.println(goods1.size());
		req.setAttribute("pi", pi);

		req.setAttribute("gsb", gsb);

		// 转发到视图
		req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);

	}

	/**
	 * 创建批量删除操作
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deleteIds = req.getParameter("deleteIds");
		// System.out.println(deleteIds);
		if (deleteIds == null || deleteIds.trim().length() == 0) {
			// 后端为空判断
			// 没有传输要删除的数据
			req.setAttribute("error", "未选中数据");
			// 跳转到视图,会有页面刷新
			req.getRequestDispatcher("/goods/list").forward(req, resp);
		} else {
			// 用数组拆分
			String[] arrVals = deleteIds.split(",");
			Integer[] intIds = new Integer[arrVals.length];
			for (int i = 0; i < intIds.length; i++) {
				intIds[i] = Integer.valueOf(arrVals[i]);
			}

			int rows = service.deleteByIds(intIds);
			req.getRequestDispatcher("/goods/list").forward(req, resp);
			// System.out.println(rows);
		}

	}

	/**
	 * ajax删除
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	// 用ajax方法删除商品数据
	private void ajaxDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 响应的数据类型
		resp.setCharacterEncoding("utf-8");
		// 响应成json类型数据
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		String[] strIds = req.getParameterValues("ids");
		if (strIds == null || strIds.length == 0) {
			pw.write("{\"success\":false,\"error\":\"未选中要删除的数据\"}");
			pw.flush();
			pw.close();
		} else {
			Integer[] ids = new Integer[strIds.length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.valueOf(strIds[i]);
			}
			int rows = service.deleteByIds(ids);
			// 引号为转义字符
			pw.write("{\"success\":true,\"rows\":\"" + rows + "\"}");
			pw.flush();
			pw.close();
		}

	}

	/**
	 * 跳转到增加数据页面
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gotoAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳到新jsp
		req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
	}

	/**
	 * 提交表单
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void submitAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("utf-8");

		String goodsId = req.getParameter("goodsId");
		String name = req.getParameter("name");
		String unit = req.getParameter("unit");
		String inNum = req.getParameter("inNum");// 进货数
		String outNum = req.getParameter("outNum");// 出货数
		String price = req.getParameter("price");
		String place = req.getParameter("place");
		String staff = req.getParameter("staff");
		String time = req.getParameter("time");
		String isarrival = req.getParameter("isarrival");

		// 请求中的参数集合
		Map<String, String[]> params = req.getParameterMap();

		if (goodsId == null || goodsId.trim().length() == 0) {
			req.setAttribute("error", "商品号不能为空");
			req.setAttribute("params", params);
			req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
			return;
		}

		if (name == null || name.trim().length() == 0) {
			req.setAttribute("error", "商品名不能为空");
			req.setAttribute("params", params);
			req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
			return;
		}

		Goods good = new Goods();
		if (inNum != null && inNum.matches("^[0-9]+$") || outNum != null && outNum.matches("^[0-9]+$")) {
			good.setInNum(Integer.parseInt(inNum));// 是数字
			good.setOutNum(Integer.parseInt(outNum));
		} else {
			req.setAttribute("error", "数据类型不匹配");// 提示输入的格式不正确
			req.setAttribute("params", params);
			req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
		}

		good.setGoodsId(goodsId);
		good.setName(name);
		good.setUnit(unit);
		// good.setInNum(Integer.parseInt(inNum));
		// good.setOutNum(Integer.parseInt(outNum));
		good.setPrice(Float.parseFloat(price));
		good.setPlace(place);
		good.setStaff(staff);
		good.setTime(DateUtil.parseDate(time));
		good.setIsArrival(isarrival);

		boolean success = service.save(good);
		if (success) {// 成功
			req.getRequestDispatcher("/goods/list").forward(req, resp);
		} else {// 保存失败
			req.setAttribute("error", "保存信息失败");
			req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
		}

	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gotoEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null || id.trim().length() == 0) {// 前端未传递参数
			req.setAttribute("error", "请求修改的商品号不可为空");
			req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
			return;
		}

		Goods good = service.findById(Integer.valueOf(id));
		if (good == null) {
			req.setAttribute("error", "请求修改的商品记录不存在");
			req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
			return;
		}

		req.setAttribute("good", good);
		req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
	}

	/**
	 * 提交表单，修改学生信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void submitEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");// 编号
		String goodsId = req.getParameter("goodsId");
		String name = req.getParameter("name");
		String unit = req.getParameter("unit");
		String inNum = req.getParameter("inNum");// 进货数
		String outNum = req.getParameter("outNum");// 出货数
		String price = req.getParameter("price");
		String place = req.getParameter("place");
		String staff = req.getParameter("staff");
		String time = req.getParameter("time");
		String isarrival = req.getParameter("isarrival");

		Goods good = new Goods();
		good.setId(Integer.valueOf(id));
		good.setGoodsId(goodsId);
		good.setName(name);
		good.setUnit(unit);
		good.setInNum(Integer.parseInt(inNum));
		good.setOutNum(Integer.parseInt(outNum));
		good.setPrice(Float.parseFloat(price));
		good.setPlace(place);
		good.setStaff(staff);
		if (time != null && !(time.trim().length() == 0)) {
			good.setTime(DateUtil.parseDate(time));
		}

		good.setIsArrival(isarrival);

		if (id == null || id.trim().length() == 0) {
			req.setAttribute("error", "编不允许为空");
			resp.sendRedirect(req.getContextPath() + "/goods/edit");// 重定向到edit页面
			return;
		}
		if (goodsId == null || goodsId.trim().length() == 0) {
			req.setAttribute("error", "商品号不能为空");
			req.setAttribute("good", good);
			req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
			return;
		}

		if (name == null || name.trim().length() == 0) {
			req.setAttribute("error", "商品名不能为空");
			req.setAttribute("good", good);
			req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
			return;
		}

		boolean success;
		try {
			success = service.update(good);
		} catch (Exception e) {
			req.setAttribute("error", "操作出现异常，检查后重试");
			req.setAttribute("good", good);
			req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
			return;
		}
		if (success) {// 成功
			req.getRequestDispatcher("/goods/list").forward(req, resp);
		} else {// 保存失败
			req.setAttribute("error", "修改信息失败");
			req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
		}

	}
}
