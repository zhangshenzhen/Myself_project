package cn.com.pateo.cpsp.location.manager;
import cn.com.pateo.cpsp.location.manager.ICityChangeCallback;
import cn.com.pateo.cpsp.location.manager.ICityQueryCallback;

/**
  * service aidl
  */
interface ILocationAgent {
    /**
     * 添加城市变化回调
     *
     * @param callback 　回调
     */
    void addLocationCallback(in ICityChangeCallback callback);

    /**
     * 移除城市变化回调
     * @param callback
     */
    void removeLocationCallback(in ICityChangeCallback callback);

    /**
     * 获取当前定位城市名称
     *
     * @param callback
     */
    void getLocationCity(in ICityQueryCallback callback);
}
