package cn.itcast.travel.service;

public interface FavoriteService {

    /**
     * 查询是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    public void addFavorite(String rid, int uid);
}
