<template>
<!--项目管理二级详情页-->
  <div class="project_details">
    <div class="project_name">
      <span>{{middleStatistical.xmqkXmmc}}</span>
<!--      <span>风电场项目</span>-->
    </div>
    <div class="main">
      <div class="details_left side">
        <div class="details_left_first details_left_block chilren">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn">
                <button class="chilren title_btn" :class="defaultTab === 0 ? 'chilren_active' : 'chilren_focus'" @click="tab_money(0)">预计情况</button>
                <button class="chilren title_btn" :class="defaultTab === 1 ? 'chilren_active' : 'chilren_focus'"  @click="tab_money(1)">预计收入</button>
                <button class="chilren title_btn" :class="defaultTab === 2 ? 'chilren_active' : 'chilren_focus'" @click="tab_money(2)">预计支出</button>
              </div>
              <div class="select_view">
                <div class="year">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="yjqk.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="开始时间"
                    @change="initYjqkData">
                  </el-date-picker>
                </div>
                <div class="year">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="yjqk.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="结束时间"
                    @change="initYjqkData">
                  </el-date-picker>
                </div>
              </div>
            </div>
            <div class="title_statistical" v-show="defaultTab === 0">
              <div>
                <span class="text-color-size" style="margin-right: 10px">预计利润</span><span class="text-color-size"  style="margin-right: 10px">{{yjqkTjData.xjlyjqkYjlr}}万元</span>
                <span class="text-color-size" style="margin-right: 10px">预计总成本</span><span class="text-color-size"  style="margin-right: 10px">{{yjqkTjData.xjlyjqkYjzcb}}万元</span>
                <span class="text-color-size" style="margin-right: 10px">EPC结算价</span><span class="text-color-size"  style="margin-right: 10px">{{yjqkTjData.xjlyjqkYjepcjsj}}万元</span>
              </div>
              <div v-show="yjqk.month">
                <span class="text-color-size" style="margin-right: 10px">预计EPC结算价格</span><span class="text-color-size">{{yjqkTjData.xjlyjqkYjepcjsj}}万元</span>
              </div>
            </div>

            <div class="title_statistical" v-show="defaultTab === 1">
              <div>
                <span class="text-color-size" style="margin-right: 10px">{{yjqk.year ? yjqk.year.getFullYear(): ''}}年预计总收入</span><span class="text-color-size">{{yjqkTjData.xjlyjqkSrHj}}万元</span>
              </div>
              <div v-show="yjqk.month">
                <span class="text-color-size" style="margin-right: 10px" >{{yjqk.month}}月预计总支出</span><span class="text-color-size">{{yjqkTjData.yXjlyjqkSrHj}}万元</span>
              </div>
            </div>

            <div class="title_statistical" v-show="defaultTab === 2">
              <div>
                <span class="text-color-size" style="margin-right: 10px">{{yjqk.year ? yjqk.year.getFullYear() : ''}}年预计总支出</span><span class="text-color-size">{{yjqkTjData.YXjlyjqkZcHj}}万元</span>
              </div>
              <div v-show="yjqk.month">
                <span class="text-color-size" style="margin-right: 10px" v-show="yjqk.month">{{yjqk.month}}月预计总支出</span><span class="text-color-size">{{yjqkTjData.xjlyjqkZcHj}}万元</span>
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="yjqkEcharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>
        <div class="details_left_last details_left_block chilren">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn">
                <button class="chilren title_btn" :class="sjsrDefaultTab === 0 ? 'chilren_active' : 'chilren_focus'" @click="sjsrDefault(0)">实际收入</button>
                <button class="chilren title_btn" :class="sjsrDefaultTab === 1 ? 'chilren_active' : 'chilren_focus'"  @click="sjsrDefault(1)">实际支出</button>
              </div>
              <div class="select_view">

                <div class="year ">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="sjsr.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="开始时间"
                    @change="initSjsrData">
                  </el-date-picker>
                </div>
                <div class="year ">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="sjsr.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="结束时间"
                    @change="initSjsrData">
                  </el-date-picker>
                </div>


              </div>
            </div>
            <div class="title_statistical" v-show="sjsrDefaultTab === 0">
              <div class="line">
                <div>
                  <span class="text-color-size mar-right" >应收总额</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkXmyszkze}}万元</span>
                </div>
                <div>
                  <span class="text-color-size mar-right">应收余额</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkYsye}}万元</span>
                </div>
              </div>
              <div class="line">
                <div>
                  <span class="text-color-size mar-right" >{{sjsr.year ? sjsr.year.getFullYear(): ''}}年实际总收入</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkSrHj}}万元</span>
                </div>
                <div v-show="sjsr.month">
                  <span class="text-color-size mar-right">{{sjsr.month}}月实际总收入</span><span class="text-color-size">{{sjsrStatistical.YXjlsjqkSrHj}}万元</span>
                </div>
              </div>
            </div>

            <div class="title_statistical" v-show="sjsrDefaultTab === 1">
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">应付总额</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkXmyszkze}}万元</span>
                </div>
                <div>
                  <span class="text-color-size" style="margin-right: 10px">应付余额</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkYsye}}万元</span>
                </div>
              </div>
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">{{sjsr.year ? sjsr.year.getFullYear() : ''}}年实际总支出</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkZcHj}}万元</span>
                </div>
                <div  v-show="sjsr.month">
                  <span class="text-color-size" style="margin-right: 10px">{{sjsr.month}}月实际总支出</span><span class="text-color-size">{{sjsrStatistical.YXjlsjqkZcHj}}万元</span>
                </div>
              </div>
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">应付未付</span><span class="text-color-size">{{sjsrStatistical.xjlsjqkYfwsk}}万元</span>
                </div>
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="sjsrEcharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
      <div class="details_middle middle">
        <div class="middle_statistical chilren">
          <div class="article">
            <span class="text-size">动工时间：</span>
            <span class="text-size">{{ middleStatistical.xmqkHtsj }}</span>
          </div>
          <div class="article">
            <span class="text-size">并网时间：</span>
            <span class="text-size">{{middleStatistical.xmqkBwsj}}</span>
          </div>
          <div class="article">
            <span class="text-size">合作方：</span>
            <span class="text-size">{{middleStatistical.xmqkHzf}}</span>
          </div>
          <div class="article">
            <span class="text-size">建设状态：</span>
            <span class="text-size">{{middleStatistical.xmqkJszt}}</span>
          </div>
          <div class="article">
            <span class="text-size">项目现状：</span>
            <span class="text-size">{{middleStatistical.xmqkXmxz}}</span>
          </div>
          <div class="article">
            <span class="text-size">融资方：</span>
            <span class="text-size">{{middleStatistical.xmqkRzf}}</span>
          </div>
          <div class="article">
            <span class="text-size">履约分公司：</span>
            <span class="text-size">{{middleStatistical.xmqkLyfgs}}</span>
          </div>
<!--          <div class="article">-->
<!--            <span class="text-size">项目当前产值：</span>-->
<!--            <span class="text-size">{{middleStatistical.xmqkXmdqz}}</span>-->
<!--          </div>-->
          <div class="article">
            <span class="text-size">收购方：</span>
            <span class="text-size">{{middleStatistical.xmqkSgf}}</span>
          </div>
          <div class="article">
            <span class="text-size">融资经理：</span>
            <span class="text-size">{{middleStatistical.xmqkRzjl}}</span>
          </div>
          <div class="article">
            <span class="text-size">商务经理：</span>
            <span class="text-size">{{middleStatistical.xmqkSwjl}}</span>
          </div>
          <div class="article">
            <span class="text-size">合同支付余额：</span>
            <span class="text-size">{{middleStatistical.xmqkQzzyf}}</span>
          </div>
          <div class="article">
            <span class="text-size">{{middleStatistical.xmqkXmlx === '光伏项目' ? '组件厂商' : '风机厂商'}}：</span>
            <span>{{middleStatistical.xmqkFjcs}}</span>
          </div>
          <div class="article">
            <span class="text-size">{{middleStatistical.xmqkXmlx === '光伏项目' ? '支架厂商' : '塔筒厂商'}}：</span>
            <span class="text-size">{{middleStatistical.xmqkTtcs}}</span>
          </div>
          <div class="article">
            <span class="text-size">出售时间：</span>
            <span class="text-size">{{middleStatistical.xmqkYcsrq}}</span>
          </div>
<!--          <div class="article">-->
<!--            <span class="text-size">最新数据：</span>-->
<!--            <span class="text-size">{{middleStatistical.zxshjrq}}</span>-->
<!--          </div>-->
<!--          <div class="article">-->
<!--            <span class="text-size">最新月报：</span>-->
<!--            <span class="text-size">{{middleStatistical.zxybrq}}</span>-->
<!--          </div>-->
        </div>
        <div class="middle_echarts chilren">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn">
                <span>项目现金流</span>
              </div>
              <div class="select_view">

                <div class="year time">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="xmxjl.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="开始时间"
                    @change="initXmxjlData">
                  </el-date-picker>
                </div>
                <div class="year time">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="xmxjl.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="结束时间"
                    @change="initXmxjlData">
                  </el-date-picker>
                </div>

              </div>
            </div>
            <div class="title_statistical">
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">预计总收入</span><span class="text-color-size">{{xmxjlTjObj.xjlyjqkSrHj ? xmxjlTjObj.xjlyjqkSrHj : 0}}万元</span>
                </div>
                <div>
                  <span class="text-color-size" style="margin-right: 10px">预计总支出</span><span class="text-color-size">{{xmxjlTjObj.xjlyjqkZcHj ? xmxjlTjObj.xjlyjqkZcHj : 0}}万元</span>
                </div>
              </div>
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">实际总收入</span><span class="text-color-size">{{xmxjlTjObj.xjlsjqkSrHj ? xmxjlTjObj.xjlsjqkSrHj : 0}}万元</span>
                </div>
                <div>
                  <span class="text-color-size" style="margin-right: 10px">实际总支出</span><span class="text-color-size">{{xmxjlTjObj.xjlsjqkZcHj ? xmxjlTjObj.xjlsjqkZcHj : 0}}万元</span>
                </div>
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="moddleEcharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
      <div class="details_right side">
        <!--说明：不同的项目建设状态要分不同的图表-->
        <!--在建状态环形图-->
        <div class="details_left_first details_left_block chilren" v-if="!isComplete">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn title_general title_btn">
                施工情况
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="dltjRingEcharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>
        <!--全井状态柱状图-->
        <div class="details_left_first details_left_block chilren" v-if="isComplete">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn">
                <button class="chilren title_btn" :class="dltjDefaultTab === 0 ? 'chilren_active' : 'chilren_focus'" @click="dltjClick(0)">电量统计</button>
                <button class="chilren title_btn" :class="dltjDefaultTab === 1 ? 'chilren_active' : 'chilren_focus'"  @click="dltjClick(1)">电费收入</button>
              </div>
              <div class="select_view ">
                <div class="year time">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="dltj.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="开始时间"
                    @change="initDltjDfsrData">
                  </el-date-picker>
                </div>
                <div class="year time">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="dltj.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="结束时间"
                    @change="initDltjDfsrData">
                  </el-date-picker>
                </div>
                <div class="time" style="margin-left: 10px;line-height: 20px" @click="dltjDetalis">
                  <span class="title_btn">查看详情</span>
                </div>
              </div>
            </div>
            <div class="title_statistical" v-show="dltjDefaultTab === 0">
              <div>
                <span class="text-color-size" style="margin-right: 10px">累计发电量</span><span class="text-color-size">{{dltjStatistical.dltjLjfdl}}kWh </span>
              </div>
            </div>
            <div class="title_statistical" v-show="dltjDefaultTab === 1">
              <div>
                <span class="text-color-size" style="margin-right: 10px">电费总收入</span><span class="text-color-size">{{dltjStatistical.dltjDfsy}}万元</span>
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="dltjBareCharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>

        <div class="details_left_last details_left_block chilren">
          <div class="first_titile">
            <div class="title_tab">
              <div class="select_btn">
                <span class="title_general">项目融资</span>
              </div>
              <div class="select_view ">
                <div class="year">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="xmrzEchartsParams.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="开始时间"
                    @change="initXmrzData">
                  </el-date-picker>
                </div>
                <div class="year">
                  <el-date-picker
                    style="width: 7rem"
                    v-model="xmrzEchartsParams.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="结束时间"
                    @change="initXmrzData">
                  </el-date-picker>
                </div>


                <div class="" style="line-height: 1rem" @click="xmrzDetails">
                  <span class="title_btn">查看详情</span>
                </div>
              </div>


            </div>
            <div class="title_statistical">
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">融资总金额：</span><span class="text-color-size">{{xmrzStatistical.xmrzRzze}}万元</span>
                </div>
                <div>
                  <span class="text-color-size" style="margin-right: 10px">已放款：</span><span class="text-color-size">{{xmrzStatistical.xmrzYfkze}}万元</span>
                </div>
              </div>
              <div class="line">
                <div>
                  <span class="text-color-size" style="margin-right: 10px">预计下笔放款：</span><span class="text-color-size">{{xmrzStatistical.xmrzYjxbfk}}万元</span>
                </div>
                <div>
                  <span class="text-color-size" style="margin-right: 10px">预计下笔放款时间：</span><span class="text-color-size">{{xmrzStatistical.xmrzYjxbfksj}}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="first_charts">
            <div class="chart-wrapper">
              <vue-e-charts
                autoresize
                :option="xmrzEcharts"
              ></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--电力统计详情-->
    <el-dialog
      :visible.sync="dltjDialog"
      :before-close="handleClose"
      title="发电详情"
      width="80%">

      <div class="dltjDialog" style="border-top: 0">
        <div class="dltjDialog_params">
          <div class="left odltj_btn">
            <div class="year time">
              <el-date-picker
                v-model="fdtj.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="选择开始时间">
              </el-date-picker>
            </div>
            <div class="year time">
              <el-date-picker
                v-model="fdtj.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="选择结束时间">
              </el-date-picker>
            </div>

            <button class="chilren" :class="dltjDefaultTab === 2 ? 'chilren_active' : 'chilren_focus'"  @click="dltjDialogQuery">搜索</button>
          </div>
          <div class="right">
            <span>电量单位：万kWh </span><span>电费单位：万元</span>
          </div>
        </div>
        <div class="dltjDialog_title">
          <span>{{fdtj.year }}<span v-show="fdtj.month && yjqk.year"> - </span>{{fdtj.month}} 发电数据情况</span>
        </div>
        <div class="dltjDialog_charts">
          <div class="charts_statistiacl chilren width">

            <div class="article">
              <span class="title_general">预计发电量</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjYjfdl }}</span>
            </div>
            <div class="article">
              <span class="title_general">累计上网电量</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjLjswdl }}</span>
            </div>
            <div class="article">
              <span class="title_general">本月上网电量</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjSjfdl }}</span>
            </div>
            <div class="article">
              <span class="title_general">累计结算电量</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjLjjsdl }}</span>
            </div>
            <div class="article">
              <span class="title_general">上网电量偏差</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjPc }}</span>
            </div>
            <div class="article">
              <span class="title_general">结算累计应收</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjLjfdlData }}</span>
            </div>
            <div class="article">
              <span class="title_general">本月结算电量</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjSjjsdlData }}</span>
            </div>
            <div class="article">
              <span class="title_general">结算累计到账</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjDfjsljsjdzData }}</span>
            </div>
            <div class="article">
              <span class="title_general">本月结算电费</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjSjdfsr }}</span>
            </div>
            <div class="article">
              <span class="title_general">结算累计未到账</span>
              <span class="title_general">{{ dltjDialogStatistcal.dltjLjwdzdfData }}</span>
            </div>


          </div>
          <div class="charts_income shelf chilren width">
            <div class="shelf_header">
              <div class="shelf_title">实际收入</div>
              <div class="shelf_num">
                <p>{{realIncome.value}}</p>
                <p>{{realIncome.name}}</p>
              </div>
            </div>
            <div class="shelf_charts">
              <div class="chart-wrapper">
                <vue-e-charts
                  autoresize
                  :option="dltjIncomeCharts"
                  style="height: 27vh"
                ></vue-e-charts>
              </div>
            </div>
          </div>
          <div class="charts_spending shelf chilren width">
            <div class="shelf_header">
              <div class="shelf_title">实际支出</div>
              <div class="shelf_num">
                <p>{{realSpending.value}}</p>
                <p>{{realSpending.name}}</p>
              </div>
            </div>
            <div class="shelf_charts">
              <div class="chart-wrapper">
                <vue-e-charts
                  autoresize
                  :option="dltjSpendingCharts"
                  style="height: 27vh"
                ></vue-e-charts>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

    <!--项目融资弹框-->
    <el-dialog
      title="融资情况"
      :visible.sync="xmrzDialog"
      width="80%">
      <div class="xmrzDialog">
        <div class="xmrz_header">
          <div class="flex-css">
            <div class="article">
              <span class="title_general">项目名称:</span>
              <span class="title_general">{{xmrzBasicData.xmqkXmmc}}</span>
            </div>
            <div class="article">
              <span class="title_general">融资机构:</span>
              <span class="title_general">{{xmrzBasicData.xmrzRzjg}}</span>
            </div>
            <div class="article">
              <span class="title_general">融资经理:</span>
              <span class="title_general">{{xmrzBasicData.xmqkRzjl}}</span>
            </div>
            <div class="article">
              <span class="title_general">签约日期:</span>
              <span class="title_general">{{xmrzBasicData.xmrzQyrq}}</span>
            </div>

            <div class="article">
              <span class="title_general">融资总金额:</span>
              <span class="title_general">{{ xmrzBasicData.xmrzRzze }}</span>
            </div>
            <div class="article">
              <span class="title_general">累计已放款:</span>
              <span class="title_general">{{xmrzBasicData.xmrzYfkze}}</span>
            </div>
            <div class="article">
              <span class="title_general">未放款:</span>
              <span class="title_general">{{xmrzBasicData.xmrzWfkze}}</span>
            </div>
            <div class="article">
              <span class="title_general">预计下笔放款:</span>
              <span class="title_general">{{xmrzBasicData.xmrzYjxbfk}}</span>
            </div>

            <div class="article">
              <span class="title_general">融资利率:</span>
              <span class="title_general">{{ percentage(xmrzBasicData.xmrzRzll) }}</span>
            </div>
            <div class="article">
              <span class="title_general">首年IRR:</span>
              <span class="title_general">{{percentage(xmrzBasicData.xmrzSnirr)}}</span>
            </div>
            <div class="article">
              <span class="title_general">全周期综合IRR:</span>
              <span class="title_general">{{percentage(xmrzBasicData.xmrzQzqzhirr)}}</span>
            </div>
            <div class="article">
              <span class="title_general">预计下笔放款时间:</span>
              <span class="title_general">{{xmrzBasicData.xmrzYjxbfksj}}</span>
            </div>
          </div>

          <div class="customize-css">
           <div class="line">
             <div class="article-a">
               <span class="title_general">保证金总额:</span>
               <span class="title_general">{{xmrzBasicData.xmrzBzj}}</span>
             </div>
             <div class="article-b">
               <span class="title_general">手续费收取方式:</span>
               <span class="title_general">{{xmrzBasicData.financingSxffs}}</span>
             </div>
           </div>

           <div class="line">
             <div class="article">
               <span class="title_general">下笔放款条件:</span>
               <span class="title_general">{{xmrzBasicData.xmrzXbfktj}}</span>
             </div>
           </div>

            <div class="line">
              <div class="article">
                <span class="title_general">风险提示:</span>
                <span class="title_general">{{xmrzBasicData.xmrzFxts}}</span>
              </div>
            </div>

          </div>
        </div>

        <div class="xmrz_footer">
          <div class="xmrz_footer_left footer_unified">
            <div class="footer_unified_title">
              <div>
                <span class="title_general">放款情况</span>
               <div>
                 <el-date-picker
                   v-model="fkqk.beginTime"
                   type="month"
                   value-format="yyyy-MM"
                   placeholder="选择开始时间"
                   @change="getFkqkData">
                 </el-date-picker>
                 <el-date-picker
                   v-model="fkqk.endTime"
                   value-format="yyyy-MM"
                   type="month"
                   placeholder="选择结束时间"
                   @change="getFkqkData">
                 </el-date-picker>
               </div>

              </div>
              <div>
                <span class="title_general">放款合计： {{fkTATS}}</span>
                <span class="title_general">单位： 万元</span>
              </div>
            </div>
            <div class="footer_unified_content">
                <div class="article" v-for="(item,index) in xmrzMonthStatistical" :key="index + 'g'">
                  <span class="title_general">{{item.rq}}</span>
                  <span class="title_general">{{item.totalLoans}}</span>
                </div>
            </div>
          </div>
          <div class="xmrz_footer_right footer_unified">
            <div class="footer_unified_title">
              <div>
                <span class="title_general">资金还款</span>
                <div>
                  <el-date-picker
                    v-model="rzqkDialog.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="选择开始时间"
                    @change="getZjhkData">
                  </el-date-picker>
                  <el-date-picker
                    v-model="rzqkDialog.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="选择结束时间"
                    @change="getZjhkData">
                  </el-date-picker>

                </div>
              </div>
              <div class="footer_title_last">
                <div class="footer_title_last_left">
                  <div><span class="title_general">还款合计 ：<span>{{ hkhj.value }}</span></span></div>
                  <div v-if="xmrzHkrq.financingRq"><span class="title_general">还款日期：<span v-if="xmrzHkrq.financingRq">{{xmrzHkrq.financingRq}}</span></span></div>
                </div>
                <span class="title_general">单位： 万元</span>
              </div>
            </div>
            <div class="footer_unified_content">
              <div class="footer_ontent_left">
                <div class="chart-wrapper">
                  <vue-e-charts
                    autoresize
                    :option="xmrzBasicEcharts"
                    style="height: 23vh"
                  ></vue-e-charts>
                </div>
              </div>
              <div class="footer_ontent_right">
                <div class="article" v-for="(item,index) in xmrzEchartsBasic" :key="index + 'i'">
                  <p class="title_general">{{item.value}}</p>
                  <p class="title_general">{{item.name}}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  dataSTAT,
  dltjAndDfsr,
  leftActual,
  leftConditionsQuery, rzqkDataFkqkTitle, rzqkDataSTATTitle, rzqkDataZjhkTitle, sgqkFdData, sgqkGfData,
  xmrz,
  xmxjMonthCharts,
  xmxjlTitle, xmxjYearCharts
} from "@/api/screen/projectDetails";
import {processing, processingString} from "@/views/visualizationScreen/pulic";
import {getFdqkObject, getFdqkSjsr, getFdqkSjzc, getSubsidiaryData, getXmgsList} from "@/api/screen/zl";
import {fontSize} from "@/views/visualizationScreen/automatic/adapter";


export default {
  name: "projectDetails",
  computed:{
    startTime (){
      return function (val){
        return val.substring(0,val.length-3 )
      }
    },
    percentage(){
      return function (val){
        //return val*100 + '%'
        return Math.round((val*100)*1000)/1000 + '%'
      }
    },
  },
  data(){
    return {
      xmid:'',

      time:new Date('2021'),
      //公共
      month:[
        {value:1,label:'1月'},
        {value:2,label:'2月'},
        {value:3,label:'3月'},
        {value:4,label:'4月'},
        {value:5,label:'5月'},
        {value:6,label:'6月'},
        {value:7,label:'7月'},
        {value:8,label:'8月'},
        {value:9,label:'9月'},
        {value:10,label:'10月'},
        {value:11,label:'11月'},
        {value:12,label:'12月'},
      ],
      //预计情况块
      defaultTab:0,
      yjqk:{
        beginTime:'',
        endTime: '',
      },
      yjqkStatistical:{title1:'预计利润',value1:2356,title2:'预计EPC结算价格',value2:'234'},
      yjqkSeriesData:[120, 200, 150],
      yjqkTjData:{},
      yjqkEcharts:{},

      //实际收入块
      sjsrDefaultTab:0,
      sjsr:{
        beginTime:'',
        endTime:''
      },
      sjsrStatistical:{},
      sjsrEcharts:{},

      //中间顶部统计块
      middleDefaultTab:1,
      middleStatistical:{},
      //中间echarts图
      xmxjl:{
        beginTime:'',
        endTime:''
      },
      xmxjlTjObj:{},
      moddleEcharts:{},

      //右边上面的图
      dltjDefaultTab:0,
      dltjStatistical:{},
      dltjDialog:false,
      //全口状态echarts
      dltj:{
        beginTime:'',
        endTime: ''
      },
      dltjBareCharts:{},
      //在建环形图
      dataRing:[
        {
          name: "使用中资源量",
          value: 754
        },
        {
          name: "维修中资源量",
          value: 611
        },
        {
          name: "保养中资源量",
          value: 400
        },
        {
          name: "已损坏资源量",
          value: 200
        },
        {
          name: "测试2",
          value: 30
        }
      ],
      dltjRingEcharts:{},
      sumValue:'',
      //右边地下图表
      xmrzEcharts:{},
      xmrzEchartsParams:{
        beginTime:'',
        endTime:''
      },
      xmrzDialog:false,
      xmrzStatistical:{title1:'融资金额', value1:120,title2:'已放款',value2:69,title3: '预计下次放款',value3:31,title4: '预计下次放款时间',value4:'2022-07-08'},

      //电量统计详情弹框内容
      fdtj:{
        beginTime:'',
        endTime:''
      },
      dltjDialogStatistcal:[],
      activeName:'fdxq',
      xmgsTable:[],
      xmgsParams:{
        isFull:'1',
        yearValue:'',
        monthValue: '',
        pageNum:1,
        pageSize:10,
        xmqkLyfgs:'',
        xmqkQy:'',
        xmqkXmlx:'',
        xmqkXmmc:'',
      },
      //收入
      dltjIncomeEchartsData:[
        { value: 12350, name: '电费收益' },
        { value: 12350, name: '补贴' },
        { value: 12350, name: '其他' }
      ],
      dltjIncomeCharts:{},
      realIncome:{},
      //支出
      dltjSpendingEchartsData:[
        { value: 12350, name: '下网电费' },
        { value: 12350, name: '人工工资' },
        { value: 12350, name: '报销费' },
        { value: 12350, name: '文件办理及其他' },
        { value: 12350, name: '利息' },
      ],
      dltjSpendingCharts:{},
      realSpending:{},

      //项目融资详情弹框内容
      xmrzBasicData:{},
      fkqk:{
        beginTime:'',
        endTime:''
      },
      fkTATS: '',
      xmrzMonthStatistical:[],
      xmrzBasicEcharts:{},
      hkhj:{},
      xmrzEchartsBasic:[],
      xmrzHkrq:{},
      rzqkDialog:{
        beginTime:'',
        endTime:''
      },

      //跳转过来的项目是全并还是在建
      isComplete:false,
      //跳转过来是风电项目还是光伏项目
      isWind:false

    }
  },
  methods:{
    tableClassName({row, rowIndex}){
      return 'row-active-style';
    },
    handleXmgsClick(){
      switch (this.activeName){
        case 'fdxq':
          let params = {
            monthValue:'',
            xmid: this.xmid,
            yearValue:''
          }
          this.initElectricityData(params)
          break
        case 'xmgs':
          this.getXmgsDataList()
          break
      }
    },

    xmgsSizeChange(val){
      this.xmgsParams.pageSize = val
      this.getXmgsDataList()
    },

    xmgsCurrentChange(val){
      this.xmgsParams.pageNum = val
      this.getXmgsDataList()
    },

    getXmgsDataList(){
      this.xmgsParams.xmid = this.xmid
      getXmgsList(this.xmgsParams).then(res => {
        this.xmgsTable = res.data.rsList
        res.data.itme.xmqkXmmc = '总计'
        this.xmgsTable.unshift(res.data.itme)
        this.xmgsTotal = res.data.total
      })
    },

    /**预计情况*/
    tab_money(index){
      /**切换三种不同颜色的echarts图
       * 换数据，
       * 换title
       * */
      this.defaultTab = index
      this.initYjqkData()
    },

    /**实际收入*/
    sjsrDefault(index){
      this.sjsrDefaultTab = index
      this.initSjsrData()
    },

    /**电量统计*/
    dltjClick(index){
      this.dltjDefaultTab = index
      this.initDltjDfsrData()
    },

    /**电力统计详情*/
    dltjDetalis(){
      this.dltjDialog = true
      var IncomeCharts = this.arrayA(this.dltjIncomeEchartsData, 'name')
      this.dltjIncomeCharts = {
        tooltip: {
          trigger: 'item'
        },
        grid: {
          top: '16%',
          bottom: '53%',
          left: "30%",
          containLabel: false
        },
        legend: {
          show: true,
          icon:"circle",
          top: "center",
          left: '60%',
          width:50,
          padding: [0, 5],
          itemGap: 25,
          textStyle: {
            color: "rgb(0, 178, 246)",
            rich: {
              title: {
                lineHeight: 15,
                color: "rgb(0, 178, 246)"
              },
              value: {
                lineHeight: 20,
                color: "#fff"
              }
            }
          },
          formatter: function(name) {
            return "{title|" + name + "}\n{value|  " + (IncomeCharts[name].value) +"}"
          },
        },
        series: [
          {
            name: '实际收入',
            type: 'pie',
            radius: ['40%', '70%'],
            center: ["30%", "55%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontWeight: 'bold',
                color:'#fff'
              }
            },
            labelLine: {
              show: false
            },
            data: this.dltjIncomeEchartsData
          }
        ]
      }
      var spendingCharts = this.arrayA(this.dltjSpendingEchartsData, 'name')
      this.dltjSpendingCharts = {
        tooltip: {
          trigger: 'item'
        },
        grid: {
          top: '16%',
          bottom: '53%',
          left: "30%",
          containLabel: false
        },
        legend: {
          show: true,
          icon:"circle",
          top: "center",
          left: '60%',
          width:50,
          padding: [0, 5],
          itemGap: 25,
          textStyle: {
            color: "rgb(0, 178, 246)",
            rich: {
              title: {
                lineHeight: 15,
                color: "rgb(0, 178, 246)"
              },
              value: {
                lineHeight: 20,
                color: "#fff"
              }
            }
          },
          formatter: function(name) {
            return "{title|" + name + "}\n{value|  " + (spendingCharts[name].value) +"}"
          },
        },
        series: [
          {
            name: '实际支出',
            type: 'pie',
            radius: ['40%', '70%'],
            center: ["30%", "55%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontWeight: 'bold',
                color:'#fff'
              }
            },

            labelLine: {
              show: false
            },
            data: this.dltjSpendingEchartsData
          }
        ]
      }

      let params = {
        monthValue:'',
        xmid:this.xmid,
        yearValue:''
      }
      this.initElectricityData(params)
    },

    /**项目融资详情*/
    xmrzDetails(){
      this.xmrzDialog = true
      var IncomeCharts = this.arrayA(this.dltjIncomeEchartsData, 'name')
      let params = {
        xmid:this.xmid,
      }
      rzqkDataSTATTitle(params).then(res => {
        if(res.code === 200 && res.data){
          this.xmrzBasicData = res.data
        }
      })



      this.getFkqkData()

      this.getZjhkData()
    },



    /**放款情况*/
    getFkqkData(){
      let fkqkParams = {
        beginTime: this.fkqk.beginTime,
        endTime: this.fkqk.endTime,
        xmid:this.xmid
      }
      rzqkDataFkqkTitle(fkqkParams).then(res => {
        if(res.code === 200 && res.data){
          this.xmrzMonthStatistical = res.data.listData
          this.fkTATS = res.data.totalLoans
        }else{
          this.xmrzMonthStatistical = []
          this.fkTATS = ''
        }
      })
    },

    /**资金还款*/
    getZjhkData(){
      let params = {
        beginTime: this.rzqkDialog.beginTime,
        endTime: this.rzqkDialog.endTime,
        xmid:this.xmid
      }
      let charts,arr
      rzqkDataZjhkTitle(params).then(res => {
        if(res.code === 200 && res.data){
          console.log(res, '资金还款')
          this.xmrzHkrq = res.data.item
          charts = processing(res.data.tbData.key, res.data.tbData.value)
          this.hkhj = charts.splice(charts.length-1, 1)[0]
          this.xmrzEchartsBasic = charts
        }else{
          charts = []
          this.hkhj = ''
          this.xmrzEchartsBasic = []
          this.xmrzHkrq = {}
        }
        this.xmrzBasicEcharts = {
          tooltip: {
            trigger: 'item'
          },
          grid: {
            top: '16%',
            bottom: '53%',
            left: "30%",
            containLabel: false
          },

          series: [
            {
              name: '还款情况',
              type: 'pie',
              center: ["50%", "50%"],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              labelLine: {
                show: false
              },
              data: charts
            }
          ]
        }
      })
    },

    //电力统计自定义数据处理
    arrayA(array,key) {
      var resObj = {};
      for(var i=0;i<array.length;i++){
        resObj[array[i][key]] = array[i];
      }
      return resObj;
    },

    /**左则第一个接口*/
    initYjqkData(){
      let params = {
        beginTime:this.yjqk.beginTime,
        selectType:this.defaultTab,
        endTime:this.yjqk.endTime,
        xmid:this.xmid
      }

      let value,key
      leftConditionsQuery(params).then(res => {
        if(res.code === 200 && res.data){
          this.yjqkTjData = res.data
          key = res.data.data.key
          value = res.data.data.value
        }else{
          key = []
          value = []
        }
        this.yjqkEcharts = {
          color:[  "rgba(89,219,255,0.5)", "rgba(255,201,166,0.5)", "rgba(156,203,255,0.5)"],
            grid: {
              top:'5%',
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          tooltip: {
            trigger: 'axis',
              axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            show:true
          },
          xAxis: {
            type: 'category',
            data: key,
            axisLine: {
              onZero: false,
              lineStyle: {
                color: '#c0c0c0'
              }
            },
            axisLabel:{
              fontSize:fontSize(11)
            }
          },
          yAxis: {
            type: 'value',
              show:true,
              splitLine:{
              show:true,
                lineStyle:{
                type:'dashed',
                  color: 'rgba(35, 145, 162, 0.5)'
              }
            },
            axisLabel:{
              fontSize:fontSize(16)
            }
          },
          series: [
            {
              data: value,
              colorBy:'data',
              type: 'bar',
              barWidth:40,
              label: {
                show: true,
                position: 'top',
                color:'#fff',
                fontSize:fontSize(15)
              },
              // itemStyle: {
              //   normal: {
              //     //这里是颜色
              //     color: function(params) {
              //       //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
              //       var colorList = ['rgba(50,145,248,0.5)','rgba(126,230,151,0.5)', 'rgba(248,210,50,0.5)'];
              //       return colorList[params.dataIndex]
              //     }
              //   }
              // }
            }
          ]
        }
      })

    },

    /***左则第二个接口*/
    initSjsrData(){
      let params = {
        beginTime:this.sjsr.beginTime,
        endTime:this.sjsr.endTime,
        xmid:this.xmid,
        selectType:this.sjsrDefaultTab
      }
      let key,value
      leftActual(params).then(res => {
        if(res.code === 200 && res.data){
          key = res.data.data.key
          value = res.data.data.value
          this.sjsrStatistical = res.data
        }else{
          key = []
          value = []
        }
        this.sjsrEcharts = {
          color:["rgba(50,145,248,0.5)", "rgba(126,230,151,0.5)", "rgba(248,210,50,0.5)", "rgba(0, 255, 255, 0.5)"],
            grid: {
              top:'10%',
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          tooltip: {
            trigger: 'axis',
              axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            show:true
          },
          xAxis: {
            type: 'category',
            data:key,
            axisLine: {
              onZero: false,
              lineStyle: {
                color: '#c0c0c0'
              }
            },
          },
          yAxis: {
            type: 'value',
              show:true,
              splitLine:{
              show:true,
                lineStyle:{
                type:'dashed',
                  color: 'rgba(35, 145, 162, 0.5)'
              }
            }
          },
          series: [
            {
              data: value,
              type: 'bar',
              barWidth:40,
              colorBy:"data",
              label: {
                show: true,
                position: 'top',
                color:'#fff'
              },
            }
          ]
        }
      })
    },

    /**中间数据统计*/
    initSTATData(){
      let params = {
        xmid:this.xmid
      }
      dataSTAT(params).then(res => {
        if(res.code === 200 && res.data){
          this.middleStatistical = res.data
          this.middleStatistical.xmqkHtsj = this.middleStatistical.xmqkHtsj.substring(0,  this.middleStatistical.xmqkHtsj.length - 3)
        }
      })
    },

    /**项目现金流数据*/
    initXmxjlData(){
      let params = {
        beginTime:this.xmxjl.beginTime,
        endTime:this.xmxjl.endTime,
        xmid:this.xmid
      }
      xmxjlTitle(params).then(res => {
        if(res.code === 200 && res.data){
          this.xmxjlTjObj = res.data
        }else{
          this.xmxjlTjObj = {}
        }
      })
      let key, value1, value2, value3, value4
      xmxjMonthCharts(params).then(res => {
        if(res.code === 200 && res.data){
          key = res.data[0].key
          value1 = res.data[0].value
          value2 = res.data[1].value
          value3 = res.data[2].value
          value4 = res.data[3].value
        }else{
          key = []
          value1 = []
          value2 = []
          value3 = []
          value4 = []
        }
        this.moddleEcharts = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['预计总收入', '实际总收入', '预计总支出', '实际总支出'],
            textStyle:{
              color:'#fff',
              fontSize:fontSize(15)
            }
          },
          grid: {
            top:'15%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: key,
            axisLine: {
              onZero: false,
              lineStyle: {
                color: '#c0c0c0'
              }
            },
            axisLabel:{
              fontSize:fontSize(15)
            }
          },
          yAxis: {
            type: 'value',
            show:true,
            splitLine:{
              show:true,
              lineStyle:{
                type:'dashed',
                color: 'rgba(35, 145, 162, 0.5)'
              }
            },
            axisLabel:{
              fontSize:fontSize(15)
            }
          },
          series: [
            {
              name: '预计总收入',
              type: 'line',
              data: value1
            },
            {
              name: '实际总收入',
              type: 'line',
              data: value2
            },
            {
              name: '预计总支出',
              type: 'line',
              data: value3
            },
            {
              name: '实际总支出',
              type: 'line',
              data: value4
            }
          ]
        }
      })

    },

    /**右则电量统计and电费收入*/
    initDltjDfsrData(){

      let yjfd,sjfd,xAxisTitle, chartsData
      let params = {
        selectType:this.dltjDefaultTab,
        beginTime:this.dltj.beginTime,
        endTime: this.dltj.endTime,
        xmid:this.xmid
      }

      if(this.dltj.month){
        xAxisTitle = [this.dltj.month + '月']
      }else{
        xAxisTitle = [(this.dltj.year ? this.dltj.year.getFullYear() : '') + '年']
      }
      dltjAndDfsr(params).then(res => {
        if(res.code === 200 && res.data){
          chartsData = processing(res.data.data.key, res.data.data.value)
          yjfd = chartsData[0]
          sjfd = chartsData[1]
          this.dltjStatistical = res.data
        }else{
          yjfd = []
          sjfd = []
        }
        this.dltjBareCharts = {
          color:["rgba(102, 205, 170, 0.5)", "rgba(0, 191, 255, 0.5)"],
          legend: {
            textStyle:{
              color:'#fff'
            },
          },
          grid: {
            left: '10%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          tooltip: {
            trigger: 'axis',
              axisPointer: {
              type: 'shadow'
            }
          },
          textStyle:{
            color:'#fff',
            fontSize:fontSize(15)
          },
          xAxis: {
            type: 'category',
              data: xAxisTitle,
            axisLabel:{
              fontSize:fontSize(15)
            }

          },
          yAxis: {
            type: 'value',
              splitLine:{
              show:true,
                lineStyle:{
                type:'dashed',
                  color: 'rgba(35, 145, 162, 0.5)'
              }
            },
            axisLabel:{
              fontSize:fontSize(15)
            }
          },
          series: [
            {
              data: [yjfd.value],
              type: 'bar',
              name:yjfd.name,
              barWidth:40,
              label: {
                show: true,
                position: 'top',
                color:'#fff',
                fontSize:fontSize(15)
              },
            },
            {
              data: [sjfd.value],
              type: 'bar',
              name:sjfd.name,
              barWidth:40,
              label: {
                show: true,
                position: 'top',
                color:'#fff'
              },
            }
          ]
        }
      })
    },

    /**右则项目融资*/
    initXmrzData(){
      let fkDate,hkDate
      let { beginTime, endTime} = this.xmrzEchartsParams
      console.log(this.xmrzEchartsParams)
      let params = {
        xmid:this.xmid,
        beginTime,
        endTime
      }
      xmrz(params).then(res => {
        if(res.code === 200 && res.data){
          this.xmrzStatistical = res.data.tileValue
          fkDate = res.data.fkDate
          hkDate = res.data.hkDate
        }else{
          fkDate = []
          hkDate = []
        }
        this.xmrzEcharts = {
          tooltip: {
            trigger: 'axis',
          },
          legend: {
            data: ['已放款', '已还款'],
            textStyle:{
              color:'#fff',
              fontSize:fontSize(15)
            }
          },
          grid: {
            left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              data: fkDate.key,
              axisLine: {
                onZero: false,
                lineStyle: {
                  color: '#c0c0c0'
                }
              },
              axisLabel:{
                fontSize:fontSize(15)
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              show:true,
              splitLine:{
                show:true,
                lineStyle:{
                  type:'dashed',
                  color: 'rgba(35, 145, 162, 0.5)'
                }
              },
              axisLabel:{
                fontSize:fontSize(15)
              }
            }
          ],
            series: [
            {
              name: '已放款',
              type: 'line',
              smooth: true,
              lineStyle: {
                normal: {
                  width: 1
                }
              },
              symbol: 'circle',
              symbolSize: 5,
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: 'rgba(128, 255, 165, 0.5)'
              },
              itemStyle: {
                normal: {
                  color: 'rgb(128, 255, 165)',
                  borderColor: 'rgb(128, 255, 165)',
                  borderWidth: 12,
                  fontSize:fontSize(15)
                }
              },
              data: fkDate.value
            },
            {
              name: '已还款',
              type: 'line',
              smooth: true,
              lineStyle: {
                normal: {
                  width: 1
                }
              },
              symbol: 'circle',
              symbolSize: 5,
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: 'rgba(0, 221, 255, 0.5)'
              },
              itemStyle: {
                normal: {
                  color: 'rgb(0, 221, 255)',
                  borderColor: 'rgb(0, 221, 255)',
                  borderWidth: 12,
                  fontSize:fontSize(15)
                }
              },
              data: hkDate.value
            }
          ]
        }
      })
    },

    /**发电详情*/
    /**电量弹框数据请求*/
    initElectricityData(params){
      this.dltjIncomeCharts = {}
      this.dltjSpendingCharts = {}
      //电量数据面板
      getFdqkObject(params).then( res => {
        if(res.code === 200 && res.data){
          this.dltjDialogStatistcal = res.data
        }else{
          this.dltjDialogStatistcal = {}
        }
      })

      //实际收入
      getFdqkSjsr(params).then(res => {
        let zcData,IncomeCharts
        if(res.code === 200 && res.data){
          console.log(123)
          zcData = processing(res.data.key, res.data.value)
          this.realIncome = zcData.splice(zcData.length - 1, 1)[0]
          IncomeCharts = this.arrayA(zcData, 'name')
          this.dltjIncomeCharts = {
            tooltip: {
              trigger: 'item'
            },
            grid: {
              top: '16%',
              bottom: '53%',
              left: "30%",
              containLabel: false
            },
            legend: {
              show: true,
              icon:"circle",
              top: "center",
              left: '60%',
              width:50,
              padding: [0, 5],
              itemGap: 25,
              textStyle: {
                color: "rgb(0, 178, 246)",
                rich: {
                  title: {
                    lineHeight: 15,
                    color: "rgb(0, 178, 246)",
                    fontSize:fontSize(16),
                  },
                  value: {
                    lineHeight: 20,
                    color: "#fff",
                    fontSize:fontSize(16),
                  }
                }
              },
              formatter: function(name) {
                return "{title|" + name + "}\n{value|  " + (IncomeCharts[name].value) +"}"
              },
            },
            series: [
              {
                name: '实际收入',
                type: 'pie',
                radius: ['40%', '70%'],
                center: ["30%", "55%"],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontWeight: 'bold',
                    color:'#fff'
                  }
                },
                labelLine: {
                  show: false
                },
                data: zcData
              }
            ]
          }
        }else{
          zcData = []
          this.realIncome = {}
          this.dltjIncomeCharts = {
            tooltip: {
              trigger: 'item'
            },
            grid: {
              top: '16%',
              bottom: '53%',
              left: "30%",
              containLabel: false
            },
            legend: {
              show:false
            },
            series: [
              {
                name: '实际收入',
                type: 'pie',
                radius: ['40%', '70%'],
                center: ["30%", "55%"],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontWeight: 'bold',
                    color:'#fff'
                  }
                },
                labelLine: {
                  show: false
                },
                data: []
              }
            ]
          }
        }
      })

      //实际支出
      getFdqkSjzc(params).then(res => {
        let srData, spendingCharts
        if(res.code === 200 && res.data){
          srData = processing(res.data.key, res.data.value)
          this.realSpending = srData.splice(srData.length - 1, 1)[0]
          spendingCharts = this.arrayA(srData, 'name')
          this.dltjSpendingCharts = {
            tooltip: {
              trigger: 'item'
            },
            grid: {
              top: '16%',
              bottom: '53%',
              left: "30%",
              containLabel: false
            },
            legend: {
              show: true,
              icon:"circle",
              top: "center",
              left: '60%',
              width:50,
              padding: [0, 5],
              itemGap: 25,
              textStyle: {
                color: "rgb(0, 178, 246)",
                rich: {
                  title: {
                    lineHeight: 15,
                    color: "rgb(0, 178, 246)",
                    fontSize:fontSize(16),
                  },
                  value: {
                    lineHeight: 20,
                    color: "#fff",
                    fontSize:fontSize(16),
                  }
                }
              },
              formatter: function(name) {
                return "{title|" + name + "}\n{value|  " + (spendingCharts[name].value) +"}"
              },
            },
            series: [
              {
                name: '实际支出',
                type: 'pie',
                radius: ['40%', '70%'],
                center: ["30%", "55%"],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontWeight: 'bold',
                    color:'#fff'
                  }
                },

                labelLine: {
                  show: false
                },
                data: srData
              }
            ]
          }
        }else{
          this.realSpending = {}
          srData = []
          this.dltjSpendingCharts = {
            tooltip: {
              trigger: 'item'
            },
            grid: {
              top: '16%',
              bottom: '53%',
              left: "30%",
              containLabel: false
            },
            legend: {
              show: false},
            series: [
              {
                name: '实际支出',
                type: 'pie',
                radius: ['40%', '70%'],
                center: ["30%", "55%"],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontWeight: 'bold',
                    color:'#fff'
                  }
                },

                labelLine: {
                  show: false
                },
                data: []
              }
            ]
          }
        }

      })
    },

    /**关闭*/
    handleClose(done){
      this.fdtj = {
        year: '',
        month: ''
      }
      done()
    },

    /**电量详情查看*/
    projectParty(){
      this.unitElectricity = true
      let params = {
        monthValue:'',
        xmid:this.xmid,
        yearValue:''
      }
      this.initElectricityData(params)
    },

    /**项目经营数据搜索发放*/
    dltjDialogQuery(){
      let params = {
        beginTime:this.fdtj.beginTime,
        xmid:this.xmid,
        endTime:this.fdtj.endTime
      }
      console.info(params, '请求参数')
      this.initElectricityData(params)
    },

    /**环形charts处理*/
    charts(data) {
      let sumValue = this.sumValue
      console.log(this.sumValue)
      var res = {
        series: [],
        yAxis: []
      };
      for (let i = 0; i < data.length; i++) {
        // console.log([70 - i * 15 + '%', 67 - i * 15 + '%']);
        res.series.push({
          name: '',
          type: 'pie',
          clockWise: false, //顺时加载
          hoverAnimation: false, //鼠标移入变大
          radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
          center: ["30%", "55%"],
          label: {
            show: false
          },
          itemStyle: {
            label: {
              show: false,
            },
            labelLine: {
              show: false
            },
            borderWidth: 5,
          },
          data: [{
            value: data[i].value,
            name: data[i].name
          }, {
            value: sumValue - data[i].value,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }]
        });
        res.series.push({
          name: '',
          type: 'pie',
          silent: true,
          z: 1,
          clockWise: false, //顺时加载
          hoverAnimation: false, //鼠标移入变大
          radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
          center: ["30%", "55%"],
          label: {
            show: false
          },
          itemStyle: {
            label: {
              show: false,
            },
            labelLine: {
              show: false
            },
            borderWidth: 5,
          },
          data: [{
            value: 7.5,
            itemStyle: {
              color: "rgb(3, 31, 62)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }, {
            value: 2.5,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }]
        });
      }
      return res;
    },
    getArrayValue(array, key) {
      var key = key || "value";
      var res = [];
      if (array) {
        array.forEach(function(t) {
          res.push(t[key]);
        });
      }
      return res;
    },
    array2obj(array,key) {
      var resObj = {};
      for(var i=0;i<array.length;i++){
        resObj[array[i][key]] = array[i];
      }
      return resObj;
    },

    /**施工情况（风电/光伏）*/
    initSgqkData(){
      let params = {
        monthValue: '',
        yearValue: '',
        xmid: this.xmid
      }
      console.log(this.isWind, '判断是？')
      let arr1 = [
        {name: '升压站安装', value:0.5},
        {name: '基础开挖', value:0.8},
        {name: '设备到货', value:0.2},
        {name: '设备安装', value:0.3},
        {name: '外线安装', value:0.54}
      ]
      let dataRing = [
        {
          name: "使用中资源量",
          value: 754
        },
        {
          name: "维修中资源量",
          value: 611
        },
        {
          name: "保养中资源量",
          value: 400
        },
        {
          name: "已损坏资源量",
          value: 200
        },
        {
          name: "测试2",
          value: 30
        }
      ]
      let arrName,arrValue,sumValue,objData,optionData,chartsData,value
      if(this.isWind){
        /**施工情况（风电）*/
        sgqkFdData(params).then(res => {
          if(res.code === 200 && res.data){
            value = res.data.value.render(item => {
              return item * 100
            })
            console.log(value)
            chartsData = processing(res.data.key, res.data.value)
            arrName = this.getArrayValue(arr1, "name");
            arrValue = this.getArrayValue(arr1, "value");
            this.sumValue = eval(arrValue.join('+'));
            console.log(this.sumValue, '什么东西22')
            sumValue = this.sumValue
            objData = this.array2obj(arr1, "name");
            optionData = this.charts(arr1)
          }else{
            arrName = []
            objData = []
            optionData = []
          }
          this.dltjRingEcharts = {
            legend: {
              show: true,
                icon:"circle",
                top: "center",
                left: '60%',
                data: arrName,
                width:50,
                padding: [0, 5],
                itemGap: 25,
                formatter: function(name) {
                return "{title|" + name + "}{value|  " + objData[name].value.toString() +"}  {title|%}"
              },

              textStyle: {
                rich: {
                  title: {
                    lineHeight: 15,
                      color: "rgb(0, 178, 246)",
                      fontSize:fontSize(11)
                  },
                  value: {
                    lineHeight: 20,
                      color: "#fff",
                      fontSize:fontSize(11)
                  }
                }
              },
            },
            tooltip: {
              show: true,
                trigger: "item",
                formatter: function(a){
                  return a.name +":" + a.value.toString() + "%"
                }
            },
            color: ['rgb(24, 183, 142)', 'rgb(1, 179, 238)', 'rgb(22, 75, 205)', 'rgb(52, 52, 176)'],
              grid: {
              top: '16%',
                bottom: '53%',
                left: "30%",
                containLabel: false
            },
            yAxis: [{
              type: 'category',
              inverse: true,
              axisLine: {
                show: false
              },
              axisTick: {
                show: false
              },
              axisLabel: {
                interval: 0,
                inside: true,
                textStyle: {
                  color: "#fff",
                  fontSize: fontSize(15),
                },
                show: true
              },
              data: optionData.yAxis
            }],
            xAxis: [{
              show: false,
              axisLabel:{
                fontSize:fontSize(11)
              }
            }],
              series: optionData.series
          }
        })
      }else{
        /**施工情况（光伏）*/
        sgqkGfData(params).then(res => {
          if(res.code === 200 && res.data){
            value = res.data.rs.value.filter(item => {
              if(item / 100 === 0){
                return item / 100 === 0
              }
              return item / 100
            })
            chartsData = processing(res.data.rs.key, value)
            arrName = this.getArrayValue(chartsData, "name");
            arrValue = this.getArrayValue(chartsData, "value");
            this.sumValue = eval(arrValue.join('+'));
            sumValue = this.sumValue
            objData = this.array2obj(chartsData, "name");
            optionData = this.charts(chartsData)
          }else{
            chartsData = []
            arrName = []
            arrValue = []
            sumValue = []
            objData = []
            optionData = []
          }
          this.dltjRingEcharts = {
            legend: {
              show: true,
              icon:"circle",
              top: "center",
              left: '60%',
              data: arrName,
              width:50,
              padding: [0, 5],
              itemGap: 25,
              formatter: function(name) {
                console.log(name,objData[name].value,'tishi ')
                return "{title|" + name + "}{value|  " + objData[name].value.toString() +"}  {title|%}"
              },

              textStyle: {
                rich: {
                  title: {
                    lineHeight: 15,
                    color: "rgb(0, 178, 246)",
                    fontSize:fontSize(14)
                  },
                  value: {
                    lineHeight: 20,
                    color: "#fff",
                    fontSize:fontSize(14)
                  }
                }
              },
            },
            tooltip: {
              show: true,
              trigger: "item",
              formatter: function(a){
                return a.name +":" + a.value.toString() + "%"
              }
            },
            color: ['rgb(24, 183, 142)', 'rgb(1, 179, 238)', 'rgb(22, 75, 205)', 'rgb(52, 52, 176)'],
            grid: {
              top: '16%',
              bottom: '53%',
              left: "30%",
              containLabel: false
            },
            yAxis: [{
              type: 'category',
              inverse: true,
              axisLine: {
                show: false
              },
              axisTick: {
                show: false
              },
              axisLabel: {
                interval: 0,
                inside: true,
                textStyle: {
                  color: "#fff",
                  fontSize: 16,
                },
                show: true
              },
              data: optionData.yAxis
            }],
            xAxis: [{
              show: false
            }],
            series: optionData.series
          }
        })
      }
    },

  },
  created() {
    this.xmid = this.$route.query.xmid
    console.log(this.$route.query, '参数')
    if(Number(this.$route.query.xmqkJsztId) === 32){
      this.isComplete = false
      if(Number(this.$route.query.xmqkXmlxId) === 30){
        this.isWind = true
      }else if(Number(this.$route.query.xmqkXmlxId) === 31){
        this.isWind = false
      }
      this.initSgqkData()
    }else if(Number(this.$route.query.xmqkJsztId) === 15){
      this.isComplete = true
      this.initDltjDfsrData()
    }

    this.initYjqkData()

    this.initSTATData()

    this.initXmxjlData()

    this.initSjsrData()

    this.initXmrzData()
  },
  mounted() {
  }
}
</script>

<style lang="scss" scoped>
@import "./index.scss";
  /**公共*/


  .details_left_block{
    height: 48.5%;
    padding: 10px;
    .first_titile{
      height: 20%;
      .title_tab{
        display: flex;
        align-items: center;
        justify-content: space-between;
      }
      .title_statistical{
        .line{
          padding: 0;
          div:first-child{
            padding-right: fontSize(10);
          }
          div{
            padding:fontSize(10) 0;
          }
        }
        div{
          padding: fontSize(10) fontSize(5);
        }

      }
    }
    .first_charts{
      height: 80%;
      .chart-wrapper{
        height: 100%;
        .echarts{
          height: 100%;
        }
      }
    }


  }

  .project_details{
    height: 100%;
    .project_name{
      height: 6.5%;
      padding: 35px 0 0 0;
      display: flex;
      justify-content: center;
      align-items: center;
      span{
        line-height: 40px;
        font-size: 20px;
      }
      span:first-child{
        padding-right: 15px;
      }
    }
    .main{
      height: 92.5%;
      padding-top: 35px;
      display: flex;
      justify-content: space-around;
      .details_left{
        /*左边Echarts布局一样 公用此样式**/
        .details_left_first{
          .first_titile{
            .title_statistical{
              display: flex;
            }
          }
        }
        .details_left_last{
          .first_titile{
            height: 35%;
            .title_tab{
              display: flex;
              align-items: center;
              justify-content: space-between;
              .select_btn{
                width: 35%;
                display: flex;
                justify-content: space-between;
              }
              .option_btn{
                width: 65%;
              }
            }
            .title_statistical{
              .line{
                display: flex;
                &:last-child{
                  padding-top: 0;
                  div{
                    padding-top: 0;
                  }
                }
              }
            }
          }
          .first_charts{
            height: 65%;
            .chart-wrapper{
              height: 100%;
              .echarts{
                height: 100%;
              }
            }
          }
        }
      }
      .details_middle{
        .middle_statistical{
          height: 38.5%;
          padding: fontSize(10);
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
          justify-content: flex-start;
          .article{
            width: 32%;
            padding: fontSize(10);
            .text-size{
              font-size: fontSize(16);
            }
          }
        }
        .middle_echarts{
          height: 58.5%;
          padding: 10px;
          .first_titile{
            height: 20%;
            .title_tab{
              width: 100%;
              display: flex;
              justify-content: space-between;
              .select_btn{
                width: 50%;
                display: flex;
                justify-content: flex-start;
                button{
                  margin-right: 10px;
                }
              }
            }
            .title_statistical{
              width: 80%;
              .line{
                display: flex;
                margin-right: fontSize(10);
                padding: 0 fontSize(6);
                div{
                  margin-right: fontSize(20);
                  padding-top: fontSize(10);
                }

                span:first-child{
                  padding-right: fontSize(10);
                }
              }
            }
          }
          .first_charts{
            height: 80%;
            .chart-wrapper{
              height: 100%;
              .echarts{
                height: 100%;
              }
            }
          }
        }
      }
      .details_right{
        .details_left_block{
          .first_titile{
            .title_tab{
              .select_btn{
                width: 50%;
                display: flex;
                justify-content: flex-start;
                button:first-child{
                  margin-right: 10px;
                }
              }
            }
          }
        }
        .details_left_last{
          .first_titile{
            height: 25%;
            .title_tab{
              .select_btn{
                width: 35%;
                display: flex;
                justify-content: space-between;
              }
              .option_btn{
                width: 65%;
              }
            }
            .title_statistical{
              .line{
                display: flex;
                &:last-child{
                  padding-top: 0;
                  div{
                    padding-top: 0;
                  }
                }
              }
            }
          }
        }
        .first_charts{
          height: 75%;
          .chart-wrapper{
            height: 100%;
            .echarts{
              height: 100%;
            }
          }
        }
        .details_left_first{
          .first_titile{
            .title_tab{
              display: flex;
              justify-content: space-between;
              .select_btn{
                width: 30%;
              }
              .option_btn{
                width: 70%;
              }
            }
          }
        }
      }
      .p_details{font-size: 12px}


    }
    /**电量统计弹框*/
    .dltjDialog{
      color: #FFFFFF;
      padding: 10px;
      border-top: 1px solid #818e93;
      .dltjDialog_params{
        padding: fontSize(10);
        display: flex;
        justify-content: space-between;
        .left{
          width: 20%;
          display: flex;
          justify-content: space-between;
          button{
            width: 84px;
            color: #FFFFFF;
            border-radius: 0px;
          }
        }
        .right{
          width: 20%;
          display: flex;
          justify-content: space-between;
        }
        .odltj_btn{
          width: 30%;
          .chilren_focus{
            border-radius: 5px;
          }
        }
      }
      .dltjDialog_title{
        height: 150px;
        display: flex;
        align-items: center;
        justify-content: center;
        span{
          font-size: fontSize(20);
        }
      }
      .dltjDialog_charts{
        display: flex;
        justify-content: space-around;
        .width{
          width: 30%;
          height: 40vh;
        }
        .charts_statistiacl{
          display: flex;
          flex-wrap: wrap;
          justify-content: space-around;
          align-items: center;
          .article{
            width: 45%;
            height: 20%;
            display: flex;
            flex-direction: column;
            color: #fff;
            span{
              width: 100%;
              //height: 50%;
              //line-height: 250%;
            }
            span:first-child{
              padding-top: fontSize(5);
              padding-bottom: fontSize(5);
            }
            span:last-child{
              color: #40abf3;
              font-size: fontSize(14);
            }

          }
        }
        .shelf{
          .shelf_header{
            height: 30%;
            padding: fontSize(10);
            div{
              height: 50%;
            }
            .shelf_title{

            }
            .shelf_num{
              p{
                text-align: center;
                &:first-child{
                  font-size: fontSize(24);
                }
              }
            }
          }
        }
      }
    }

    /**项目融资详情*/
    .xmrzDialog{
      color: #FFFFFF;
      padding: fontSize(10) fontSize(20);
      border-top: 1px solid #818e93;
      .xmrz_header{
        border: 1px solid #2183c4;
        border-radius: 0.3125rem;
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
        align-items: center;

        .flex-css{
          display: flex;
          flex-wrap: wrap;
          justify-content: flex-start;
          align-items: center;
          .article{
            width: 25%;
            padding-left: fontSize(40);
            padding-top: fontSize(10);
            padding-bottom: fontSize(10);
            span:first-child{
              padding-right: fontSize(15);
            }
          }
        }

        .customize-css{
          width: 100%;
          .line{
            width: 100%;
            display: flex;
            .article-a{
              width: 25%;
              padding-left: fontSize(40);
              padding-top: fontSize(10);
              padding-bottom: fontSize(10);
              span:first-child{
                padding-right: fontSize(15);
              }
            }
            .article-b{
              width: 75%;
              padding-left: fontSize(40);
              padding-top: fontSize(10);
              padding-bottom: fontSize(10);
              span:first-child{
                padding-right: fontSize(15);
              }
            }
            .article{
              width: 100%;
              padding-left: fontSize(40);
              padding-top: fontSize(10);
              padding-bottom: fontSize(10);
              span:first-child{
                padding-right: fontSize(15);
              }
            }
          }
        }

      }
      .xmrz_footer{
        margin-top: fontSize(20);
        height: 35vh;
        display: flex;
        justify-content: space-between;
        .footer_unified{
          padding: 10px;
          width: 49%;
          height: 100%;
          border: 1px solid #2183c4;
          border-radius: 0.3125rem;
          .footer_unified_title{
            height: 25%;
            div{
              display: flex;
              justify-content: space-between;
              .el-input{

              }
            }
            div:first-child{
              margin-bottom: 1%;
            }

          }
          .footer_unified_content{
            margin-top: 1%;
            height: 73%;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            align-items: center;
            .article{
              width: 24%;
              height: 60px;
              background: rgba(84,145,188,0.26);
              display: flex;
              justify-content: center;
              align-items: center;
              span:first-child{
                padding-right: fontSize(10);
                color: #00afff;
              }
              span:last-child{
                color: yellow;
                font-size: fontSize(15);
              }
            }
          }
        }
        .xmrz_footer_left{
          .footer_unified_title{
            div:last-child{
              span:last-child{
                font-size: fontSize(12);
              }
            }
          }
        }
        .xmrz_footer_right{
          .footer_unified_title {
            .footer_title_last{
              .footer_title_last_left{
                height: 100%;
                div:first-child{
                  margin-right: fontSize(15);
                }
              }
              span{
                font-size: fontSize(12);
              }
            }
          }
          .footer_unified_content{
            .footer_ontent_left{
              width: 50%;
              height: 100%;

            }
            .footer_ontent_right{
              width: 50%;
              height: 100%;
              display: flex;
              flex-wrap: wrap;
              justify-content: space-around;
              align-items: center;
              .article{
                width: 40%;
                height: 30%;
                display: flex;
                flex-direction: column;
                p:first-child{
                  color: #00afff;
                  font-size: fontSize(15);
                }
              }
            }
          }
        }
      }
    }
    /**element弹框样式修改*/
    .el-dialog{
        top:fontSize(150);
        box-shadow: 0 0 0.3125rem #2183c4 inset;
        border-radius: 0.3125rem;
        background: rgba(6,92,189,0.5);
        .el-dialog__body{
          padding: fontSize(10) 0;
        }
        .el-dialog__header{
          .el-dialog__title{
            color: #FFFFFF;
          }
        }
    }

    .select_btn{
      width: 50%;
      display: flex;
      justify-content: space-around;
      button{
        width: fontSize(70);
        height: fontSize(25);
        color: #c1cbcb;
        vertical-align: middle;
        line-height: 10px;
      }
    }

    .dltjDialog{
      color: #FFFFFF;
      padding: 10px;
      border-top: 1px solid #818e93;
      .dltjDialog_params{
        padding: 10px;
        display: flex;
        justify-content: space-between;
        .left{

          width: 100%;
          display: flex;
          justify-content: flex-start;
          flex-wrap: wrap;
          .time{
            margin: fontSize(5) 0;
          }
          button{
            color: #FFFFFF;
            border-radius: 5px;
            margin: fontSize(5) 0;
          }
        }
        .right{
          width: 25%;
          display: flex;
          justify-content: space-between;
        }
      }
      .dltjDialog_total{
        padding: fontSize(10);
        margin-bottom: fontSize(10);
        border-radius: 5px;
        background-color: rgba(27, 73, 112, 0.5);
        span:last-child{
          margin-left: fontSize(15);
        }
      }
      .dltjDialog_title{
        height: fontSize(150);
        display: flex;
        align-items: center;
        justify-content: center;
        span{
          font-size: fontSize(20);
        }
      }
      .dltjDialog_charts{
        display: flex;
        justify-content: space-around;
        .width{
          width: 30%;
          height: 40vh;
        }
        .charts_statistiacl{
          display: flex;
          flex-wrap: wrap;
          justify-content: space-around;
          align-items: center;
          .article{
            width: 45%;
            height: 20%;
            display: flex;
            flex-direction: column;
            color: #fff;
            span{
              width: 100%;
              //height: 50%;
              //line-height: 250%;
            }
            span:first-child{
              padding-top: fontSize(5);
              padding-bottom: fontSize(5);
            }
            span:last-child{
              color: #40abf3;
              font-size: fontSize(14);
            }
          }
        }
        .shelf{
          .shelf_header{
            height: 30%;
            padding: 10px;
            div{
              height: 50%;
            }
            .shelf_title{

            }
            .shelf_num{
              p{
                text-align: center;
                &:first-child{
                  font-size: fontSize(24);
                }
              }
            }
          }
        }
      }

      .business_payment_container{
        height: vh(280);
        padding-bottom: fontSize(15);
        display: flex;
        justify-content: space-between;
        .business_situation{
          width: 49.5%;
          padding: fontSize(10) fontSize(20);
          border-radius: 5px;
          background: rgba(27,73,112,0.5);
          .business_title{
            height: 25%;
            div:first-child{
              p{
                padding-bottom: fontSize(10);
              }
            }
          }
          .business_charts{
            height: calc(100% - 30%);
            .unit_chart{
              height: 100%;
              .expect_business_data_class{
                height: 100%;
              }
            }
          }
        }
        .payment_situation{
          width: 49.5%;
          display: flex;
          border-radius: 5px;
          padding: fontSize(10) 0;
          background: rgba(27,73,112,0.5);
          .payment_title{
            width: 40%;
            .title{
              height: 20%;
              padding-left: fontSize(20);
              //text-align: center;
            }

          }
          .payment_charts{
            width: 60%;
            .chart-wrapper{
              height: 100%;
              .echarts{
                height: 100%;
              }
            }
          }
        }
      }
      .actual_payment{
        height: fontSize(250);
        border-radius: 5px;
        background: rgba(27,73,112,0.5);
        display: flex;
        .actual_situation{
          width: 70%;
          .actual_situation_title{
            height: 40%;
            padding:0 fontSize(20);
            div:first-child{
              padding: fontSize(15) 0;
            }
            div:last-child{
              span:not(:first-child){
                padding: 0 fontSize(15);
              }
              span:nth-child(2){
                font-size: fontSize(18);
                color: #00afff;
              }
            }
          }
          .actual_charts_container{
            height: 60%;
            display: flex;
            .actual_bar{
              width: 25%;
              height: 100%;
              .unit_chart{
                height: 100%;
                .echarts{
                  height: 100%;
                }
              }
            }
          }
        }
        .actual_charts{
          width: 30%;
          height: 100%;
          .chart-wrapper{
            height: 100%;
            .echarts{
              height: 100%;
            }
          }
        }
      }

    }
    /**弹框样式修改*/
    ::v-deep  .el-dialog{
      top:80px;
      box-shadow: 0 0 0.3125rem #2183c4 inset;
      border-radius: 0.3125rem;
      background: rgba(6,92,189,0.5);
      .el-dialog__body{
        padding: 10px 0;
        .el-tabs{
          padding: 0 30px;
        }
      }
      .el-dialog__header{
        .el-dialog__title{
          color: #FFFFFF;
        }
      }
      .el-input{
        width: 150px;
        margin-right: 10px;
        &:first-child{
          margin-left: 10px;
        }
        .el-input__inner{
          background: none !important;
          border: 1px solid #2183c4;
          color: #cedee2;
        }
      }
      .el-button--default{
        background: none;
        box-shadow: 0 0 1.25rem #4097e7 inset;
        border: none;
      }

      .dialog_ul{
        .el-table{
          &::before{
            height: 0;
          }
        }
        .paging{
          padding: 10px;
          display: flex;
          justify-content: flex-end;
          .el-pagination{
            .el-pagination__sizes{
              .el-select{
                .el-input{
                  .el-input__inner{
                    background: none;
                    border: none;
                    box-shadow: 0 0 10px #2183c4 inset;
                  }
                }
              }
            }
            .btn-prev{
              background: none;
              box-shadow: 0 0 10px #2183c4 inset;
            }
            .el-pager{
              .number{
                background: none;
                box-shadow: 0 0 10px #2183c4 inset;
                color: #FFFFFF;
              }
              .more{
                background: none;
                color: #FFFFFF;
                box-shadow: 0 0 10px #2183c4 inset;
              }
            }
            .btn-next{
              background: none;
              box-shadow: 0 0 10px #2183c4 inset;
              color: #FFFFFF;
            }
            .el-pagination__jump{
              .el-input{
                .el-input__inner{
                  background: none;
                  border: none;
                  box-shadow: 0 0 10px #2183c4 inset;
                }
              }
            }
          }
        }
      }
    }
  }


::v-deep .el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell{
  background-color: rgba(3, 131, 172, 0.5);
}

::v-deep .el-table{
  color: #e6eff3;
  background: none;
}
::v-deep .el-table .rowBackground{
  background: #00afff;
  opacity: 0.1;
}
::v-deep .has-gutter{
  tr{
    background: rgba(43, 131, 172, 0.5);
    .el-table__cell{
      color: #a8a0a0;
      background: none;
      border-bottom: 0.0625rem solid #345b7c !important;
    }
  }
}

::v-deep .el-table__body{
  tbody{
    .el-table__row{
      background: none;
      .el-table__cell{
        border-bottom: 0.0625rem solid #345b7c !important;
      }
    }
  }
}

.v-modal{
  opacity: 0.9 !important;
}


</style>
