package cn.com.pateo.netinterface;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cn.com.pateo.cpsp.manager.CpspIInitCallback;
import cn.com.pateo.cpsp.specialcar.IAirPortCityInfoCallback;
import cn.com.pateo.cpsp.specialcar.ICityInfoCallback;
import cn.com.pateo.cpsp.specialcar.ICityListCallback;
import cn.com.pateo.cpsp.specialcar.INearByCarCallback;
import cn.com.pateo.cpsp.specialcar.IOrderDetailInfoCallback;
import cn.com.pateo.cpsp.specialcar.IPriceWithCouponInfoCallback;
import cn.com.pateo.cpsp.specialcar.SpecialCarAgent;
import cn.com.pateo.cpsp.specialcar.bean.AirPortCityInfo;
import cn.com.pateo.cpsp.specialcar.bean.CityInfo;
import cn.com.pateo.cpsp.specialcar.bean.CityListInfo;
import cn.com.pateo.cpsp.specialcar.bean.NearByCarInfo;
import cn.com.pateo.cpsp.specialcar.bean.OrderDetailInfo;
import cn.com.pateo.cpsp.specialcar.bean.PriceWithCouponInfo;
import cn.com.pateo.cpsp.specialcar.bean.PriceWithCouponReqBean;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //RecyclerView mRecyclerView;
    String TAG = "MainActivity";
    byte[] bytes = new byte[]{(byte) 0x81,0x01,0x06,0x01,0x05,0x04,0x03,0x02,
            (byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xFF, (byte) 0xDE};

    PopupWindow popupWindow;
    String EstimateId = "";

    LinearLayout linearLayout;
    private SpecialCarAgent specialCarAgent;

    private PopupWindow window;
    TextView tv;


    Handler handler   = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
//            if(0x03==msg.what){
//                Log.d(TAG, "handleMessage: ");
//                myAdapter.setmDataList((List<CityListInfo>) msg.obj);
//                return false;
//            }
            String s = String.valueOf(msg.obj);
            initPop(s);
            return false;
        }
    });
//    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.parent);
        findViewById(R.id.get_city_info).setOnClickListener(this);
        findViewById(R.id.get_city_list).setOnClickListener(this);
        findViewById(R.id.get_ariport_list).setOnClickListener(this);
        findViewById(R.id.connect).setOnClickListener(this);
        findViewById(R.id.get_price).setOnClickListener(this);
        findViewById(R.id.get_nearby_car).setOnClickListener(this);
        findViewById(R.id.get_order_info).setOnClickListener(this);
        findViewById(R.id.get_order_info_detail).setOnClickListener(this);
        findViewById(R.id.get_order_list).setOnClickListener(this);
//        mRecyclerView = findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter = new MyAdapter();
//        mRecyclerView.setAdapter(myAdapter);
//        forTest();
//        dateTest();
    }




    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.connect:
               specialCarAgent = SpecialCarAgent.getInstance();

               specialCarAgent.init(MainActivity.this, new CpspIInitCallback() {
                   @Override
                   public void onInitFinish(boolean b) {
                       Log.d(TAG, "onInitFinish: "+b);

                   }

                   @Override
                   public void onServiceDied() {

                   }
               });


               break;
           case  R.id.get_city_info:

               specialCarAgent.getCityInfo(30.50622, 114.164686, new ICityInfoCallback.Stub() {
                   @Override
                   public void onResult(CityInfo cityInfo) throws RemoteException {
                       getCityInfo(cityInfo);

                   }

                   @Override
                   public void onError(String s, String s1) throws RemoteException {

                   }
               });
               break
           case R.id.get_city_list:
               Log.d("BUTTON" ,"get_city_list");
               specialCarAgent.getCityListInfo("end", 20, new ICityListCallback.Stub() {
                   @Override
                   public void onResult(List<CityListInfo> list, List<CityListInfo> list1) throws RemoteException {
                       getCityList(list,list1);
                       Log.d(TAG, "onResult: ");
                   }

                   @Override
                   public void onError(String s, String s1) throws RemoteException {

                   }
               });

               break;
           case R.id.get_ariport_list:

               specialCarAgent.getAirPortCityInfo(20, new IAirPortCityInfoCallback.Stub() {
                   @Override
                   public void onResult(List<AirPortCityInfo> list) throws RemoteException {
                    getAirPortCityList(list);

                   }

                   @Override
                   public void onError(String s, String s1) throws RemoteException {

                   }
               });




               break;
           case R.id.get_price:
               getPrice();
               break;
           case R.id.get_nearby_car:
               getNearByCar();
               break;
           case R.id.get_order_info:
               getCreatOrder();
               break;
           case R.id.get_order_info_detail:
               getCreateOrderDetail();
               break;
           case R.id.get_order_list:
               getOrderList();
               break;
           default:
               break;
       }


    }







    private void initPop(String s){

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("返回数据");
        builder.setMessage(s);
        builder.setIcon(R.mipmap.ic_launcher_round);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    //获取城市信息
    private void getCityInfo(CityInfo cityInfo){

        CityInfo cityInfo1 = new CityInfo();
        Gson gson = new Gson();
        StringBuilder sb  = new StringBuilder("");
        sb.append("cityName: " + cityInfo.getCityName()+"\n");
        sb.append("cityId: " + cityInfo.getCityId()+"\n");
        sb.append("支持的服务类型 ");
        for(CityInfo.servicesBean bean : cityInfo.getServices()){
            sb.append(bean.getName()+ ";");
        }
        Message msg = Message.obtain();
        msg.obj = sb.toString();
        handler.sendMessage(msg);
    }

    //获取城市列表信息
    private void getCityList(List<CityListInfo> list, List<CityListInfo> list1){
        StringBuilder sb  = new StringBuilder("");
        sb.append("热门城市：");
        for(CityListInfo city : list){
            sb.append(city.getCityName()+";");
        }
        sb.append("\n"+"城市列表:");
        for(CityListInfo city : list1){
            sb.append(city.getCityName()+";");
        }


        Message msg = Message.obtain();
        msg.what = 0x03;
        msg.obj = list1;
        handler.sendMessage(msg);
    }

    //获取城市列表
    private void getAirPortCityList(List<AirPortCityInfo> list){

        Log.d(TAG, "getAirPortCityList: "+list.size());
        StringBuilder sb  = new StringBuilder("");
        sb.append("机场列表：" +"\n");
        for(AirPortCityInfo info : list){
            sb.append("----"+info.getName()+"\n");
        }

        Message msg = Message.obtain();
        msg.obj = sb.toString();
        handler.sendMessage(msg);
    }




    private void getOrderList(){
       /* String orderStatus = "00";
        String pageNumber = "1";
        String pageSize = "20";
        specialCarAgent.getOrderList(orderStatus, pageNumber, pageSize, new IOrderListCallback.Stub() {
            @Override
            public void onResult(OrderListInfo orderListInfo) throws RemoteException {
                Gson gson = new Gson();
               // Log.d(TAG, "onResult: "+orderListInfo.getList().size());
                Log.d(TAG, "onResult: "+gson.toJson(orderListInfo));
            }

            @Override
            public void onError(String s, String s1) throws RemoteException {

            }
        });*/
    }



    private void getPrice(){
                PriceWithCouponReqBean reqBean =new PriceWithCouponReqBean();

//                reqBean.setSlat(30.506223);
//                reqBean.setSlng(114.164686);
        reqBean.setAirCode("WUH");
        reqBean.setFlightDate(1560555555);
        reqBean.setFlightDelayTime(20);
        reqBean.setFlt("MCU123");
                reqBean.setElat(30.606816);
                reqBean.setElng(114.424366);
                reqBean.setIsUseCoupon(1);
                reqBean.setPassengerMobile("18351972778");
                reqBean.setServiceId(7);
                reqBean.setCityId(20);
//                reqBean.setDepartureTime(1560555566);

                specialCarAgent.getPrice(reqBean, new IPriceWithCouponInfoCallback.Stub() {
                    @Override
                    public void onResult(PriceWithCouponInfo priceWithCouponInfo) throws RemoteException {

                        Gson gson = new Gson();
                        Log.d(TAG, "onResult: "+gson.toJson(priceWithCouponInfo));
                        StringBuilder sb  = new StringBuilder("");
                        EstimateId = priceWithCouponInfo.getEstimateId();
                        sb.append("各种价格明细：" +"\n");
                        for(PriceWithCouponInfo.PricesBean pricesBean :priceWithCouponInfo.getPrices()){
                            sb.append(pricesBean.getName()+"价格："+pricesBean.getPrice() +"\n");

                        }

                        Message msg = Message.obtain();
                        msg.obj = sb.toString();
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(String s, String s1) throws RemoteException {

                    }
                });
    }



    private void getNearByCar(){
        //95.369826&slat=29.735952
        specialCarAgent.getNearByCarInfo(30.506223, 114.164686, new INearByCarCallback.Stub() {
            @Override
            public void onResult(NearByCarInfo nearByCarInfo) throws RemoteException {
                Log.d(TAG, "onResult: ");
                StringBuilder sb  = new StringBuilder("");
                sb.append("附近一共有"+nearByCarInfo.getNumber()+"车" +"\n");

                for(int i = 0;i<nearByCarInfo.getCarList().size();i++){
                    sb.append("第"+(i+1)+"辆 位置:"+"经度"+nearByCarInfo.getCarList().get(i).getLat() +
                            "纬度"+nearByCarInfo.getCarList().get(i).getLng()+"\n");
                }

                Message msg = Message.obtain();
                msg.obj = sb.toString();
                handler.sendMessage(msg);
            }

            @Override
            public void onError(String s, String s1) throws RemoteException {
                Log.d(TAG, "onError: "+s  +"ERROR"+s1);
            }
        });
    }

    private void getCreatOrder(){
        /*CreateOrderReqBean createOrderReqBean = new CreateOrderReqBean();

        createOrderReqBean.setIsInvoice(0);
        createOrderReqBean.setUid("1031631");
        createOrderReqBean.setGoodsPrice("0.01");
        createOrderReqBean.setGoodsName("神舟专车订单");
        createOrderReqBean.setOrderChannel("OBU");
        createOrderReqBean.setOrderBizType("shenzhou");
        createOrderReqBean.setGoodsid("1");
        ExtensionInfo extensionInfo  = new ExtensionInfo();
        Gson  gson = new Gson();
        extensionInfo =  gson.fromJson(getString(R.string.city_list),ExtensionInfo.class);
        extensionInfo.setEstimateId(EstimateId);

      // \"slng\":\"114.164686\",\"startName\":\"东合中心\",\"startAddress\":\"东合中心北门\",\"elat\":\"30.606816\",\"elng\":\"114.424366\",\"endName\":\"武汉站\",\"endAddress\":\"武汉站\",\"departureTime\":\"1559952488\",\"estimateId\":\"75d834c992fd4dc699dbdc4422b1b660\",\"serviceId\":\"7\",\"carGroupId\":\"2\",\"passengerMobile\":\"15671564568\",\"passengerName\":\"sxm\"};
        specialCarAgent.getOrderInfo(createOrderReqBean, extensionInfo, new IOrderInfoCallback.Stub() {

            @Override
            public void onResult(OrderInfo orderInfo, RespExtensionInfo respExtensionInfo) throws RemoteException {
                StringBuilder sb  = new StringBuilder("");
                orderInfo.getOrderGenRes().getGoodsNo();

                sb.append("订单ID ："+orderInfo.getOrderGenRes().getOrderNo() +"\n");
                sb.append("订单号 ："+orderInfo.getOrderGenRes().getGoodsNo() +"\n");
                Message msg = Message.obtain();
                msg.obj = sb.toString();
                handler.sendMessage(msg);
            }

            @Override
            public void onError(String s, String s1) throws RemoteException {

            }
        });*/
    }

    private void getCreateOrderDetail(){
        String s = "31415926";
        specialCarAgent.getOrderDetailInfo(s, new IOrderDetailInfoCallback.Stub() {
            @Override
            public void onResult(OrderDetailInfo orderDetailInfo) throws RemoteException {

                if(orderDetailInfo!=null){

                }

                StringBuilder sb  = new StringBuilder("");
                sb.append("订单详细："+"\n");
                sb.append("乘客信息："+orderDetailInfo.getOrder().getPassengerName()
                        +" 手机号:"+orderDetailInfo.getOrder().getPassengerMobile()+"\n");
                sb.append("司机信息："+"姓名："+orderDetailInfo.getDriver().getDriverName()
                        +" 手机号:"+orderDetailInfo.getDriver().getVirtualPhone4Passenger()+"\n");
                sb.append("出发地："+orderDetailInfo.getOrder().getRealStartName()+"\n");
                sb.append("目的地："+orderDetailInfo.getOrder().getRealEndAddress()+"\n");
                sb.append("价钱："+orderDetailInfo.getPrice().getTotalPrice());
                Message msg = Message.obtain();
                msg.obj = sb.toString();
                handler.sendMessage(msg);
            }

            @Override
            public void onError(String s, String s1) throws RemoteException {

            }
        });
    }



   /* protected void forTest(){
        String s  = getString(R.string.city_list);
        Gson gson = new Gson();
        SpecialCarCityListRespBean specialCarCityListRespBean = gson.fromJson(s,SpecialCarCityListRespBean.class);
        CityListInfo cityListInfo;
        List<CityListInfo> list = new ArrayList<>();
        for(SpecialCarCityListRespBean.DataBean dataBean:specialCarCityListRespBean.getData()){
            cityListInfo = gson.fromJson(gson.toJson(dataBean),CityListInfo.class);
            list.add(cityListInfo);
            Log.d(TAG, "forTest: "+gson.toJson(cityListInfo));
        }

        Collections.sort(list, new Comparator<CityListInfo>() {
            @Override
            public int compare(CityListInfo o1, CityListInfo o2) {

                return o1.getCitySpell().compareTo(o2.getCitySpell());
            }
        });

    //    myAdapter.setmDataList(list);

    }*/

}
