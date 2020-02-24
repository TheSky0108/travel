package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @ClassName RouteServiceImpl
 * @Author ZYQ
 * @Date 2020/2/18
 **/
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    
    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();

        //设置当前页码
        pb.setCurrentPage(currentPage);

        //设置每页显示的条数
        pb.setPageSize(pageSize);

        //设置总的记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);

        //设置当前页面显示的数据集合
        int start = (currentPage - 1) * pageSize; //开始的记录数(开始的索引)
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        //设置总页数=总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    /**
     * 根据rid查询一个旅游线路的详细信息
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        //1.根据rid在tab_route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));

        //2.根据route的rid在tab_route_img表中查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(Integer.parseInt(rid));

        //3.将集合设置到route对象
        route.setRouteImgList(routeImgList);

        //4.根据route的sid(商家id)查询商家对象
        Seller seller = sellerDao.findBySid(route.getSid());

        //5.将seller设置到route对象
        route.setSeller(seller);

        //6.查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }
}