package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName RouteImgDaoImpl
 * @Author ZYQ
 * @Date 2020/2/21
 **/
public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据rid查询图片集合信息
     *
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        //1.定义sql
        String sql = "select * from tab_route_img where rid = ?";

        //2.执行sql
        List<RouteImg> routeImgs = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);

        return routeImgs;
    }
}
