package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {

    /**
     * 根据route的sid(商家id)查询商家对象
     *
     * @param sid
     * @return
     */
    public Seller findBySid(int sid);
}
