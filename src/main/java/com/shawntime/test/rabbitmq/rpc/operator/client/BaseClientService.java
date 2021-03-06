package com.shawntime.test.rabbitmq.rpc.operator.client;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.alibaba.fastjson.JSON;
import com.shawntime.test.rabbitmq.rpc.JsonHelper;
import com.shawntime.test.rabbitmq.rpc.RpcInvokeModel;
import com.shawntime.test.rabbitmq.rpc.operator.IBaseClientService;
import com.shawntime.test.rabbitmq.rpc.operator.ParamBean;
import com.shawntime.test.rabbitmq.rpc.operator.RegisterCenter;
import com.shawntime.test.rabbitmq.rpc.operator.bean.User;
import com.shawntime.test.rabbitmq.rpc.rabbit.Client;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by shma on 2017/5/8.
 */
public class BaseClientService implements IBaseClientService {

    private Client client;

    public BaseClientService(Client client) {
        this.client = client;
    }

    public User getUserInfo(Integer userId) {
        ParamBean methodBean = RegisterCenter.getMethodBean(1);
        RpcInvokeModel model = new RpcInvokeModel();
        model.setMethodName(methodBean.getMethodName());
        model.setClassName(methodBean.getClassName());
        model.setArguments(new Object[]{userId});
        byte[] data;
        try {
            data = client.call(model);
            if (data != null) {
                return JsonHelper.deSerialize(new String(data, "UTF-8"), User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int save(User user) {
        ParamBean methodBean = RegisterCenter.getMethodBean(2);
        RpcInvokeModel model = new RpcInvokeModel();
        model.setMethodName(methodBean.getMethodName());
        model.setClassName(methodBean.getClassName());
        model.setArguments(new Object[]{user});
        byte[] data;
        try {
            data = client.call(model);
            if (data != null) {
                return JsonHelper.deSerialize(new String(data, "UTF-8"), Integer.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
