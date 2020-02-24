package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 根据rid查询图片集合信息
     *
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid (int rid);
}
