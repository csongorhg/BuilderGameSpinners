package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

import java.util.HashMap;

/**
 * Created by tuskeb on 2017. 02. 12..
 */

public class HttpCommand extends HttpConnect {
    private boolean locked = false;
    private HashMap<String, String> send = new HashMap<String, String>();
    private HashMap<String, String> receive = new HashMap<String, String>();

    public HashMap<String, String> getReceive() {
        return receive;
    }

    public HashMap<String, String> getSend() {
        return send;
    }

    public HttpCommand(String Url) {
        super(Url);
    }

    public String sendCommand(){
        System.out.println("Flush start");
        httpRequest.setContent(HttpMapUtil.mapToString(send));
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("Result:\n" + httpResponse.getResultAsString());
                HttpCommand.this.response(HttpMapUtil.stringToMap(httpResponse.getResultAsString()));
            }

            @Override
            public void failed(Throwable t) {
                System.out.println("Send command failed: " + t.getMessage());
                HttpCommand.this.failed(HttpErrors.timeout);
            }

            @Override
            public void cancelled() {
                System.out.println("Send command cancelled");
                HttpCommand.this.failed(HttpErrors.cancelled);
            }
        });
        System.out.println("Send done");

        return "";
    }

    protected void response(HashMap<String, String> map){
        receive = map;
    }

    protected void failed(HttpErrors httpErrors) {

    }

    public boolean isLocked() {
        return locked;
    }

}
