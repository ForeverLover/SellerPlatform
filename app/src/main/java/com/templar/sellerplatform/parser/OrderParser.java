package com.templar.sellerplatform.parser;

import com.templar.sellerplatform.entity.Order;
import com.templar.sellerplatform.entity.OrderProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/19 14:46
 * 描述：${todo}
 */
public class OrderParser {
    private static OrderParser instance = null;

    public static OrderParser getInstance() {
        if (instance == null)
            instance = new OrderParser();
        return instance;
    }

    public List<Order> parseOrderList(/*String data*/int state) {
//        if (StringUtils.isEmpty(data)) return null;
        List<Order> orderList = null;

        orderList = new ArrayList<Order>();
        Order order = new Order();
        order.setOrderId("1");
        order.setBuyerName("張先生");
        order.setOrderNo("S023");
        order.setOrderAddr("台北市大安區忠孝東路27號-3");
        order.setOrderTime("2011-12-34 19:18");
        order.setOrderType("外帶");
        order.setOrderRemark("玉山銀行旁");
        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setAmount("2");
        orderProduct.setProductName("珍珠奶茶");
        orderProduct.setProductType("大杯");
        orderProduct.setProductDescribe("去冰 微塘");
        orderProductList.add(orderProduct);
        orderProduct.setAmount("1");
        orderProduct.setProductName("卡拉雞腿堡");
        orderProduct.setProductType(" 不加洋蔥");
        orderProduct.setProductDescribe("+蛋 +起司");
        orderProductList.add(orderProduct);
        order.setProductList(orderProductList);
        order.setOrderPrice("310");
        int state1 = -1;
        switch (state) {
            case 0:
                state1 = 0;
                break;
            case 1:
                state1 = 2;
                break;
            case 2:
                state1 = 3;
                break;
            case 3:
                state1 = 5;
                break;
        }
        order.setState(state1);
        order.setRemainingTime("20");
        orderList.add(order);

        order = new Order();
        order.setOrderId("2");
        order.setBuyerName("李先生");
        order.setOrderNo("S043");
        order.setOrderAddr("台北市大安區忠孝東路27號-3");
        order.setOrderTime("2011-12-31 19:20");
        order.setOrderType("外帶");
        order.setOrderRemark("玉山銀行旁");
        List<OrderProduct> orderProductList1 = new ArrayList<OrderProduct>();
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setAmount("2");
        orderProduct1.setProductName("黑糖刨冰");
        orderProduct1.setProductType("大碗");
        orderProduct1.setProductDescribe("珍珠 花生 土豆");
        orderProductList1.add(orderProduct1);
        orderProduct1.setAmount("2");
        orderProduct1.setProductName("多多綠茶");
        orderProduct1.setProductType(" 中杯");
        orderProduct1.setProductDescribe("少冰 少糖珍珠");
        orderProductList1.add(orderProduct1);
        order.setProductList(orderProductList1);
        order.setOrderPrice("280");
        int state2 = -1;
        switch (state) {
            case 0:
                state2 = 1;
                break;
            case 1:
                state2 = 2;
                break;
            case 2:
                state2 = 4;
                break;
            case 3:
                state2 = 5;
                break;
        }
        order.setState(state2);
        order.setRemainingTime("15");
        orderList.add(order);


        return orderList;
    }
}
