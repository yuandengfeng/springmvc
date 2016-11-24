/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mqtt.curl.mqtt.util;

import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public interface ICMDCallback {
    public void process(CMD cmd, JSONObject response) throws Throwable;
}
