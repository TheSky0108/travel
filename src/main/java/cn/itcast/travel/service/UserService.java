package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 邮件激活
     *
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user);
}
