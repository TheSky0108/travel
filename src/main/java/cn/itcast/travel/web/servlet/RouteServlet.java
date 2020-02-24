package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPagestr = request.getParameter("currentPage"); //当前页数
        String pageSizestr = request.getParameter("pageSize"); //每页显示的条数
        String cidstr = request.getParameter("cid"); //分类的id

        String rname = request.getParameter("rname"); //线路名称
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        //2.处理参数
        //分类的id
        int cid = 0;
        if (cidstr != null && cidstr.length() > 0 && !"null".equals(cidstr)) {
            cid = Integer.parseInt(cidstr);
        }

        //当前页数
        int currentPage = 0;
        if (currentPagestr != null && currentPagestr.length() > 0) {
            currentPage = Integer.parseInt(currentPagestr);
        } else {
            currentPage = 1;
        }

        //每页显示的条数
        int pageSize = 0;
        if (pageSizestr != null && pageSizestr.length() > 0) {
            pageSize = Integer.parseInt(pageSizestr);
        } else {
            pageSize = 5;
        }

        //3.调用service查询PageBean对象
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //4.将PageBean对象序列化为json,返回
        writeValue(response, routePageBean);
    }

    /**
     * 根据rid查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受rid
        String rid = request.getParameter("rid");

        //2.调用service查询route对象
        Route route = routeService.findOne(rid);

        //3.转为json，写回客户端
        writeValue(response, route);
    }

    /**
     * 判断当前登录用户是否收藏过该线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");

        //2.获取当前登录用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid; //用户的id
        if (user == null) {
            //用户尚未登录
            uid = 0;
        } else {
            //用户已经登录
            uid = user.getUid();
        }

        //3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //4.将flag返回客户端
        writeValue(response, flag);
    }

    /**
     * 添加收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");

        //2.获取当前登录用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid; //用户的id
        if (user == null) {
            //用户尚未登录
            return;
        } else {
            //用户已经登录
            uid = user.getUid();
        }

        //3.调用FavoriteService添加收藏
        favoriteService.addFavorite(rid, uid);
    }
}
