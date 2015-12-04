// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package cn.ucloud.sdk;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import cn.ucloud.sdk.utils.HttpClientUtils;
import cn.ucloud.sdk.utils.PropertiesUtils;
import cn.ucloud.sdk.utils.SignatureUtils;
import cn.ucloud.sdk.utils.VoUtils;
import cn.ucloud.sdk.vo.UcloudInVo;

/**
 * 
 * @author Jack shen<37393993@qq.com>
 * 
 */
public class UcloudClient {
    private static final Logger logger = Logger.getLogger(UcloudClient.class);

    private final String apiUrl;
    private final String region;
    private final String publicKey;
    private final String privateKey;

    /**
     * constructor
     * @param publicKey
     * @param privateKey
     */
    public UcloudClient(String publicKey, String privateKey) {
        this.region = null;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        apiUrl = PropertiesUtils.getProperty("api_url");
    }

    /**
     * constructor
     * @param region
     * @param publicKey
     * @param privateKey
     */
    public UcloudClient(String region, String publicKey, String privateKey) {
        this.region = region;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        apiUrl = PropertiesUtils.getProperty("api_url");
    }

    /**
     * Create client without region
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static UcloudClient newClient(String publicKey, String privateKey) {
        return new UcloudClient(null, publicKey, privateKey);
    }

    /**
     * Create client with region
     * @param region
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static UcloudClient newClient(String region, String publicKey, String privateKey) {
        return new UcloudClient(region, publicKey, privateKey);
    }

    /**
     * Executor
     * @param inVo
     * @param type
     * @return
     */
    public <T> T exec(UcloudInVo inVo, Class<T> type) {
        return VoUtils.buildOutVo(execute(inVo), type);
    }
    
    /**
     * Execute the request
     * @param vo
     * @return
     */
    private String execute(UcloudInVo vo) {
        if(vo.getRegion() == null || vo.getRegion().length() == 0) {
            vo.setRegion(region);
        }
        vo.setPublicKey(publicKey);
        TreeMap<String, Object> map = VoUtils.genMap(vo);
        String param = SignatureUtils.signature(privateKey, map);
        String url = apiUrl + param;
        logger.debug("Request url is: " + url);
        String res = HttpClientUtils.getByHttps(url);
        logger.debug("Call result is: " + res);
        return res;
    }
}
