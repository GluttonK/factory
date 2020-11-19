package com.nobugs.service.impl;

import com.nobugs.dao.TEquipmentMapper;
import com.nobugs.dao.TProductOrderMapper;
import com.nobugs.entity.OStatusData;
import com.nobugs.entity.Page;
import com.nobugs.entity.TEquipment;
import com.nobugs.entity.TProductOrder;
import com.nobugs.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;

@Service("indexService")
public class IndexServiceImpl implements IndexService {


    @Resource
    private TProductOrderMapper tProductOrderMapper;

    @Resource
    private TEquipmentMapper tEquipmentMapper;

    /**
     * 查询各个状态的订单的个数，将数据封装返回一个ArrayList
     * @return
     */
    @Override
    public ArrayList<OStatusData> getNumByStatus() {
        ArrayList<OStatusData> oStatusDatas = new ArrayList<>();
        int noNum = tProductOrderMapper.selectNumByStatus(1);
        int acNum = tProductOrderMapper.selectNumByStatus(2);
        int reNum = tProductOrderMapper.selectNumByStatus(3);
        int woNum = tProductOrderMapper.selectNumByStatus(4);
        int comNum = tProductOrderMapper.selectNumByStatus(5);
        oStatusDatas.add(new OStatusData(noNum, "未处理"));
        oStatusDatas.add(new OStatusData(acNum, "已接受"));
        oStatusDatas.add(new OStatusData(reNum, "已拒绝"));
        oStatusDatas.add(new OStatusData(woNum, "生产中"));
        oStatusDatas.add(new OStatusData(comNum, "已完成"));
        return oStatusDatas;
    }


    /**
     * 查询每个月份的订单个数，返回ArrayList
     * @return
     */
    @Override
    public int[] getNumByMonth() {
        ArrayList<TProductOrder> tProductOrders = tProductOrderMapper.selectAll();
        int[] num = new int[12];
        for(TProductOrder order : tProductOrders){
            if(order.getCreateTime() == null) continue;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getCreateTime());
            int m = calendar.get(Calendar.MONTH);
            num[m]++;
        }
        return num;
    }


    /**
     * 获取各种状态设备的个数。
     * @return
     */
    @Override
    public int[] getEquipmentStatus() {
        ArrayList<TEquipment> tEquipments = tEquipmentMapper.selectByFactoryId(1);
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num0 = tEquipments.size();
        System.out.println("num0: " + num0);
        for(TEquipment tEquipment : tEquipments){
            System.out.println(tEquipment.getEquipmentStatus());
            if(tEquipment.getEquipmentStatus() == 10) num1++;
            if(tEquipment.getEquipmentStatus() == 20) num2++;
            if(tEquipment.getEquipmentStatus() == 30) num3++;
        }
        int[] res = new int[3];
        if(num0 == 0) return res;
        res[0] = num1 * 100 / num0;
        res[1] = num2 * 100 / num0;
        res[2] = num3 * 100/ num0;
        System.out.println("num1 : " + num1);
        System.out.println("res[0] : " + res[0]);
        return res;
    }

    /**
     * 分页获取设备
     * @return
     */
    @Override
    public ArrayList<TEquipment> getEquipmentsByPage(Page<Integer> pages) {
        return tEquipmentMapper.getEquipmentByPage(pages);
    }
}
