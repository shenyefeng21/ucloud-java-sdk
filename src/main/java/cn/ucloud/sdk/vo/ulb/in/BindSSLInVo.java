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
package cn.ucloud.sdk.vo.ulb.in;

import cn.ucloud.sdk.enums.ActionEnum;
import cn.ucloud.sdk.vo.PageVo;

/**
 * 
 * @author Jack shen<37393993@qq.com>
 * 
 */
public class BindSSLInVo extends PageVo {
    private String sSLId;
    private String uLBId;
    private String vServerId;

    public BindSSLInVo() {
        super(ActionEnum.BindSSL.name());
    }

    public String getsSLId() {
        return sSLId;
    }

    public void setsSLId(String sSLId) {
        this.sSLId = sSLId;
    }

    public String getuLBId() {
        return uLBId;
    }

    public void setuLBId(String uLBId) {
        this.uLBId = uLBId;
    }

    public String getvServerId() {
        return vServerId;
    }

    public void setvServerId(String vServerId) {
        this.vServerId = vServerId;
    }

}
