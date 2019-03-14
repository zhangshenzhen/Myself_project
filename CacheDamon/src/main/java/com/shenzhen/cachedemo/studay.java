package com.shenzhen.cachedemo;

import android.view.View;
import android.view.ViewGroup;

public abstract class studay {

    /* 虚电路：
    在虚电路操作方式中，为了进行数据传输，网络的源节点和目的节点之间
    要建立一条逻辑通路，因为这条逻辑通路不是专用的，所以称为虚电路，
    每个节点到其他任意节点之间可能有若干条虚电路支持特定的另个端系统之间的数据传输
    两个端点系统之间可能有多条虚电路为不同的进程服务，这些虚电路可能相同也可能不同。
    * */
    public View getView(int position, View convertView, ViewGroup parent) {
        return MyView(position, convertView, parent);
    }
    /*
     * 防火墙：
     * 防火墙可以起到安全作用，
     * 1,集中地安全网络，2、安全警报、3重新部署网络地址、4监视
     * 局限性：无法防范来自防火墙以外的其他途径的网络攻击；
     * */
    public abstract int getNum();
    /*
     * 终端接入方式以及各特点
     * 仿真终端方式：用户计算机安装仿真软件后像终端一样与ISP 连接，
     * 拨号IP 方式：也称SLP/PPP方式，通过电话拨号将用户主机与IS连接
     * 局域网连接方式：一种是通过局域网服务器区域因特网连接，局域网上的主机共享服务器皮革IP地址
     * */
    public abstract View MyView(int position, View convertView, ViewGroup parent);

    public static void main() {

    }

}
