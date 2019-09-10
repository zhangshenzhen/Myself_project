package cn.com.pateo.cpsp.location.manager;

import android.os.IBinder;
import android.os.RemoteException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import cn.com.pateo.cpsp.manager.CpspAgent;

import cn.com.pateo.cpsp.manager.utils.MZLog;


/**
 * locaction agent
 * Created by yuanchangzhu on 17-11-6.
 */

public class LocationAgent extends CpspAgent<ILocationAgent> {
    private static final String TAG = "LocationAgent";

    private static final LocationAgent mInstance = new LocationAgent();

    /**
     * service名称
     */
    public static final String SERVICE_NAME = "LocationService";

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static LocationAgent getInstance() {
        return mInstance;
    }

    @Override
    protected String getVersionCode() {
        return MgrVersion.getMgrVersion();
    }

    @Override
    protected String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    protected ILocationAgent createIInterface(IBinder binder) {
        return ILocationAgent.Stub.asInterface(binder);
    }

    //********************************service方法 开始*****************************//

    /**
     * 添加城市变化回调
     *
     * @param callback 　回调
     */
    public void addLocationCallback(ICityChangeCallback.Stub callback) {
        if (null != mIInterface) {
            try {
                mIInterface.addLocationCallback(callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 移除城市变化回调
     *
     * @param callback 回调
     */
    public void removeLocationCallback(ICityChangeCallback.Stub callback) {
        if (null != mIInterface) {
            try {
                mIInterface.removeLocationCallback(callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前定位城市名称
     *
     * @param callback 回调
     */
    public void getLocationCity(ICityQueryCallback.Stub callback) {
        if (null != mIInterface) {
            try {
                mIInterface.getLocationCity(callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //********************************service方法 结束*****************************//
}
