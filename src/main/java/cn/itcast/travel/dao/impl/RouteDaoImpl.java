package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RouteDaoImpl
 * @Author ZYQ
 * @Date 2020/2/18
 **/
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     *
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList(); //参数

        //2.判断参数是否有值，从而拼接sql
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid); //添加?对应的值
        }

        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }

        sql = sb.toString();

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? limit ?, ?";
        //1.定义sql模板
        String sql = "select * from tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList(); //参数

        //2.判断参数是否有值，从而拼接sql
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid); //添加?对应的值
        }

        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }

        sb.append("limit ?, ?");
        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /**
     * 根据rid查询
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        //1.定义sql
        String sql = "select * from tab_route where rid = ?";

        //2.执行sql
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);

        return route;
    }
}
