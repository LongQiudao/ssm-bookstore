package bookstore.controller;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.Mod;

import cn.itcast.commons.CommonUtils;

import bookstore.mapper.BookMapper;
import bookstore.mapper.OrderitemMapper;
import bookstore.mapper.OrdersMapper;
import bookstore.po.Book;
import bookstore.po.Cart;
import bookstore.po.CartItem;
import bookstore.po.OrderCustom;
import bookstore.po.Orderitem;
import bookstore.po.OrderitemCustom;
import bookstore.po.Orders;
import bookstore.po.TbUser;
import bookstore.service.OrderService;

import sun.jdbc.odbc.OdbcDef;

@Controller
public class OrderController {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderitemMapper orderitemMapper;

	/**
	 * 添加订单
	 * */
	@RequestMapping("/addOrder")
	public ModelAndView addOrder(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		TbUser user = (TbUser) request.getSession()
				.getAttribute("session_user");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		OrderCustom orders = new OrderCustom();
		orders.setOid(CommonUtils.uuid());
		orders.setOrdertime(new Date());
		orders.setState((short) 1);
		orders.setUid(user.getUid());
		orders.setUser(user);
		orders.setTotal(cart.getTotal());
		List<OrderitemCustom> orderItemList = new ArrayList<OrderitemCustom>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderitemCustom oi = new OrderitemCustom();
			oi.setIid(CommonUtils.uuid());
			oi.setCount(cartItem.getCount());
			oi.setBook(cartItem.getBook());
			oi.setSubtotal(cartItem.getSubtotal());
			oi.setOrder(orders);
			orderItemList.add(oi);	
		}
		orders.setOrdercCustomList(orderItemList);
		cart.clear();
		ordersMapper.insert(orders);
		orderItemList=orders.getOrdercCustomList();
		for(OrderitemCustom orderItem:orderItemList){
			Orderitem orderitem2=new Orderitem();
			orderitem2.setBid(orderItem.getBook().getBid());
			orderitem2.setOid(orderItem.getOrder().getOid());
			orderitem2.setCount(orderItem.getCount());
			orderitem2.setSubtotal(orderItem.getSubtotal());
			orderitem2.setIid(orderItem.getIid());
			orderitemMapper.insert(orderitem2);
		}
		modelAndView.addObject("order", orders);
		modelAndView.setViewName("/jsps/order/desc.jsp");
		return modelAndView;
	}

	/**
	 * 易宝支付
	 * */
	@RequestMapping("/pay")
	public void pay(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		props.load(input);

		/*
		 * 准备13个参数
		 */
		String p0_Cmd = "Buy";
		String p1_MerId = props.getProperty("p1_MerId");
		String p2_Order = request.getParameter("oid");
		String p3_Amt = "0.01";// req.getParamater("total")测试状态米有钱财，就一分吧
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = props.getProperty("p8_Url");
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";

		/*
		 * 计算hmac
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		/*
		 * 连接支付网关
		 */
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		/*
		 * 重定向到易宝
		 */
		response.sendRedirect(url.toString());

	}

	@RequestMapping("/back")
	public ModelAndView back(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * 修改订单的状态
		 */
		/*
		 * 获取11+1个参数
		 */
		ModelAndView modelAndView = new ModelAndView();
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String hmac = request.getParameter("hmac");
		/*
		 * 校验访问者是否为易宝
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		props.load(input);
		String keyValue = props.getProperty("keyValue");
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (!bool) {
			/*
			 * 校验失败
			 */
			modelAndView.addObject("msg", "你需要一个b number");
			modelAndView.setViewName("f:/jsps/msg.jsp");
			return modelAndView;
		}
		/*
		 * 校验成功 获取订单状态，确定是否要修改订单状态以及添加积分等业务操作
		 */
		Orders order = ordersMapper.selectByPrimaryKey(r6_Order);// 有可能对数据库进行操作有可能不操作
		if (order.getState() == (short) 1) {
			order.setState((short) 2);
			ordersMapper.updateByPrimaryKey(order);
		}
		/*
		 * 判断当前回调方式 如果为点对点需要回馈以sucess开头的字符窜
		 */
		if (r9_BType.equals("2")) {
			response.getWriter().print("sucessclearlove43967");
		}
		/*
		 * 保存成功信息转发到msg.jsp
		 */
		modelAndView.addObject("msg", "恭喜支付成功,等待卖家发货");
		modelAndView.setViewName("f:/jsps/msg.jsp");
		return modelAndView;
	}

	/**
	 * 我的订单
	 * */
	@RequestMapping("/myOrders")
	public ModelAndView myOrders(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		TbUser user = (TbUser) request.getSession()
				.getAttribute("session_user");
		List<Orders> orderList = orderService.findByUid(user.getUid());
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/jsps/order/list.jsp");
		return modelAndView;
	}

	/**
	 * 确认收货功能
	 * */
	@RequestMapping("/comfirm")
	public ModelAndView comfirm(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String oid = request.getParameter("oid");
		Orders order = ordersMapper.selectByPrimaryKey(oid);
		short state = order.getState();
		if (state != (short) 3) {
			modelAndView.addObject("msg", "嗯？没付钱你想干嘛？");
			modelAndView.setViewName("/jsps/msg.jsp");
			return modelAndView;
		}
		order.setState((short) 4);
		ordersMapper.updateByPrimaryKey(order);
		modelAndView.addObject("msg", "恭喜啦，交易成功");
		modelAndView.setViewName("/jsps/msg.jsp");
		return modelAndView;
	}

	/**
	 * 付款
	 * */
	@RequestMapping("/payOrder")
	public ModelAndView payOrder(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Orders order = ordersMapper.selectByPrimaryKey(request
				.getParameter("oid"));
		List<OrderitemCustom > orderitemCustomList = orderService
				.findOrderItemByOid(order.getOid());
		OrderCustom orderCustom = new OrderCustom();
		orderCustom.setOrdercCustomList(orderitemCustomList);
		orderCustom.setOid(order.getOid());
		orderCustom.setState(order.getState());
		orderCustom.setOrdertime(order.getOrdertime());
		orderCustom.setTotal(order.getTotal());
		for(OrderitemCustom orderitem: orderitemCustomList){
			Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
			orderitem.setBook(book);
		}
		modelAndView.addObject("order",orderCustom);
		modelAndView.setViewName("/jsps/order/desc.jsp");
		return modelAndView;
	}

}
