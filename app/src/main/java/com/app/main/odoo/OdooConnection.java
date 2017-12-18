package com.app.main.odoo;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
/**
 * Created by bima on 14/12/17.
 */

public class OdooConnection {
    protected String url = "";
    protected String password = "";
    protected String login = "";


    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        String url = "http://localhost:8079"; // work with odoo.com account!!
        String db = "odoo9";
        String username = "admin";
        String password = "admin";
        System.out.println("Get database list");
        System.out.println("Login");
        System.out.println("--------------");
        int uid = login(url,db,username,password);
        if (uid >0) {
            System.out.printf(String.valueOf(uid));
            System.out.println("Login Ok");
        } else {
            System.out.println("Login Fail");
        }

    }
    static int login(String url, String db, String login, String password) throws XmlRpcException, MalformedURLException {
        XmlRpcClient client = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExtensions(true);
        config.setServerURL(new URL(url+"/xmlrpc/2/common"));
        client.setConfig(config);
        //Connect
        //Object[] empty = null; // Ok
        //Object[] params = new Object[] {db,login,password, empty}; // Ok
        Object[] params = new Object[] {db,login,password}; // Ok & simple
        Object uid = client.execute("login", params);
        if (uid instanceof Integer)
            return (int) uid;
        return -1;
    }
}
