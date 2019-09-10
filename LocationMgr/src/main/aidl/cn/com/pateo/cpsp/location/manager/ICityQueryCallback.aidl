package cn.com.pateo.cpsp.location.manager;

/**
 * query callback
 */
interface ICityQueryCallback {
    /**
     * 城市查询回调
     *
     * @param city 城市
     */
    void onCityQuery(String city);
}