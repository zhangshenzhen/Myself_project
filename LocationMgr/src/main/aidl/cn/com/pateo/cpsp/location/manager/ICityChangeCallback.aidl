package cn.com.pateo.cpsp.location.manager;


/**
 * 城市变化回调
 * Created by yuanchangzhu on 19-2-19.
 */

interface ICityChangeCallback {
    /**
     * 城市变化回调
     *
     * @param city    城市
     */
    void onCityChanged(String city);
}
