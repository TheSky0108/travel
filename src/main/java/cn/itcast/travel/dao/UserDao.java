package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     *
     * @param user
     */
    public void save(User user);

    /**
     * 根据激活码查询用户对象
     *
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 修改激活状态
     *
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根据用户名密码查询用户对象
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
