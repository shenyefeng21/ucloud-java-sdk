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
package cn.ucloud.sdk.vo.ulb.out;

/**
 * 
 * @author Jack shen<37393993@qq.com>
 * 
 */
public class SSL {
    private String sSLId;
    private String sSLName;
    private String sSLType;
    private String sSLContent;
    private String hashValue;
    private Integer createTime;
    private String vServerId;

    public String getsSLId() {
        return sSLId;
    }

    public void setsSLId(String sSLId) {
        this.sSLId = sSLId;
    }

    public String getsSLName() {
        return sSLName;
    }

    public void setsSLName(String sSLName) {
        this.sSLName = sSLName;
    }

    public String getsSLType() {
        return sSLType;
    }

    public void setsSLType(String sSLType) {
        this.sSLType = sSLType;
    }

    public String getsSLContent() {
        return sSLContent;
    }

    public void setsSLContent(String sSLContent) {
        this.sSLContent = sSLContent;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getvServerId() {
        return vServerId;
    }

    public void setvServerId(String vServerId) {
        this.vServerId = vServerId;
    }
}
