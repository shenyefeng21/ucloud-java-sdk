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
package cn.ucloud.sdk.vo.unet.in;

import cn.ucloud.sdk.enums.ActionEnum;
import cn.ucloud.sdk.vo.PageVo;

/**
 * 
 * @author Jack shen<37393993@qq.com>
 * 
 */
public class GetEIPPriceInVo extends PageVo {
    private String operatorName;
    private Integer bandwidth;
    private String chargeType;

    public GetEIPPriceInVo() {
        super(ActionEnum.GetEIPPrice.name());
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }
}
