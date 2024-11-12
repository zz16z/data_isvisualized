<template>
  <!--资金管理模块-->
  <div class="money">
    <div class="money_header">
      <div class="mouey_left chilren pad-10">
        <div class="mouey_title">
          <span class="title_general">项目建设收支情况</span>
          <span class="p_details title_general" @click="xmjsDetails(1)">查看详情</span>
        </div>
        <div class="chart-wrapper">
          <vue-e-charts
            autoresize
            :option="balance_payments"
            class="mouey_left_charts"
          ></vue-e-charts>
        </div>
      </div>
      <div class="mouey_moddle">
         <div class="data_statistical ">
          <div class="details">
            <div>
              <span>{{allLatestDataSet.xmZs}}</span><span>个</span>
            </div>
            <div><span>项目总数</span></div>
          </div>
          <div class="details">
            <div>
              <span class="h3_title">{{allLatestDataSet.xmRlzs}}</span><span class="title_general">MW</span>
            </div>
            <div><span class="title_general">容量总数</span></div>
          </div>
          <div class="details">
            <div>
              <span  class="h3_title">{{allLatestDataSet.xmYjcsze}}</span><span class="title_general">万元</span>
            </div>
            <div><span  class="title_general">预计出售总额</span></div>
          </div>
          <div class="details">
            <div>
              <span class="h3_title">{{allLatestDataSet.xmYjepcjszj}}</span><span class="title_general">万元</span>
            </div>
            <div><span class="title_general">预计EPC结算总价</span></div>
          </div>
        </div>
         <div class="project_statistical">
          <div class="details_num">
            <div class="details">
              <div><span>{{zjStatistical.xjlsjqkYsye}}</span><span>万元</span></div>
              <div><span>应收余额</span></div>
            </div>
            <div class="details">
              <div><span>{{zjStatistical.xjlsjqkYfwsk}}</span><span>万元</span></div>
              <div><span>应付余额</span></div>
            </div>
          </div>
          <div class="details_num">
            <div class="details">
              <div><span>{{zjStatistical.xjlsjqkYfwsk}}</span><span>万元</span></div>
              <div><span>应付未付</span></div>
            </div>
            <div class="details">
              <div><span>{{zjStatistical.xmqkYflybzj}}</span><span>万元</span></div>
              <div><span>应收履约保证金</span></div>
            </div>
          </div>
        </div>
      </div>
      <div class="mouey_right chilren pad-10">
        <div class="mouey_title">
          <span class="title_general">分公司收款情况</span>
          <div class="select_view">
            <div class="year time">
              <el-date-picker
                style="width: 7rem"
                v-model="fgs.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="开始时间"
                @change="fgsYearQuery">
              </el-date-picker>
            </div>
            <div class="year time">
              <el-date-picker
                style="width: 7rem"
                v-model="fgs.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="结束时间"
                @change="fgsYearQuery">
              </el-date-picker>
            </div>


            <span class="p_details title_general" @click="xmjsDetails(2)">查看详情</span>
          </div>
        </div>
        <div class="chart-wrapper">
          <vue-e-charts
            autoresize
            :option="payments"
          ></vue-e-charts>
        </div>
      </div>
    </div>
    <div class="money_main">
      <div class="chilren main_project main_style left_right">
        <div class="mouey_title">
          <div class="tab">
            <button
              class="chilren"
              :class="defaultTab === 30 ? 'chilren_active' : 'chilren_focus'"
              @click="tab_money(30)"
            >
              风电项目
            </button>
            <button
              class="chilren"
              :class="defaultTab === 31 ? 'chilren_active' : 'chilren_focus'"
              @click="tab_money(31)"
            >
              光伏项目
            </button>
          </div>
          <div class="select_view">

            <div class="year">
              <el-date-picker
                style="width: 7rem"
                v-model="xmfl.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="开始时间"
                @change="xmflYearQuery">
              </el-date-picker>
            </div>
            <div class="year">
              <el-date-picker
                style="width: 7rem"
                v-model="xmfl.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="结束时间"
                @change="xmflYearQuery">
              </el-date-picker>
            </div>
            <span class="p_details title_general" @click="projectBusiness">查看详情</span>
          </div>
        </div>
        <div class="number_statistical" v-show="statistical">
          <div
            v-for="(item, index) in statistical"
            :key="index"
            class="text-spacing"
            style="display: inline-block"
          >
            <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{ item.name }}</span>
            <span class="text-color-size-16" style="color:#4db3e2">{{ item.value }}万元</span>
          </div>
        </div>
        <div class="chart-wrapper">
          <vue-e-charts
            autoresize
            :option="type_project_option"
          ></vue-e-charts>
        </div>
      </div>
      <div class="chilren main_financing main_style left_right">
        <div class="mouey_title">
          <span class="title_general">融资情况</span>
          <div class="tab-select select_view">
            <div class="month">
              <el-select
                v-model="rzqk.projectType"
                clearable
                placeholder="总计"
                @change="rzqkTypeQuery"
              >
                <el-option
                  v-for="item in xjlOptions"
                  :key="item.id + 'c'"
                  :label="item.zdName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </div>

            <div class="year">
              <el-date-picker
                style="width: 7rem"
                v-model="rzqk.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="开始时间"
                @change="rzqkYearQuery">
              </el-date-picker>
            </div>
            <div class="year">
              <el-date-picker
                style="width: 7rem"
                v-model="rzqk.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="结束时间"
                @change="rzqkYearQuery">
              </el-date-picker>
            </div>
<!--            -->
<!--            <div class="year time">-->
<!--              <el-date-picker-->
<!--                v-model="value3"-->
<!--                type="year"-->
<!--                value-format="yyyy"-->
<!--                placeholder="选择年"-->
<!--                @change="rzqkYearQuery"-->
<!--              >-->
<!--              </el-date-picker>-->
<!--            </div>-->
<!--            <div class="month time">-->
<!--              <el-select-->
<!--                v-model="rzqk.monthValue"-->
<!--                clearable-->
<!--                placeholder="选择月"-->
<!--                @change="rzqkMonthQuery"-->
<!--              >-->
<!--                <el-option-->
<!--                  v-for="item in month"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value"-->
<!--                >-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </div>-->
            <span class="p_details title_general" @click="rzqkDetailsOpen">查看详情</span>
          </div>
        </div>

        <div class="number_statistical">
          <span class="text-color-size-16 text-spacing" >{{
            rzStatistical.title
          }}</span>
          <span class="text-color-size-16" style="color:#4db3e2">{{ rzStatistical.value }}万元</span>
        </div>

        <div class="chart-wrapper">
          <vue-e-charts
            autoresize
            :option="financing"
          ></vue-e-charts>
        </div>
      </div>
    </div>
    <div class="money_footer">
      <div class="chartts chilren">
        <div class="mouey_title">
          <div class="tab">
            <span class="title_general">现金流情况</span>
          </div>
          <div class="select_view">
            <div class="month time">
              <el-select
                v-model="xjl.projectType"
                clearable
                placeholder="总计"
                @change="xjlTyoeQuery"
              >
                <el-option
                  v-for="item in xjlOptions"
                  :key="item.id + 'c'"
                  :label="item.zdName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </div>

            <div class="year time">
              <el-date-picker
                v-model="xjl.yearValue"
                type="year"
                placeholder="选择年"
                value-format="yyyy"
                @change="xjlYearQuery"
              >
              </el-date-picker>
            </div>
            <div class="month time">
              <el-select
                v-model="xjl.monthValue"
                clearable
                placeholder="选择月"
                @change="xjlMonthQuery"
              >
                <el-option
                  v-for="item in month"
                  :key="item.value + 'c'"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
            <!--            <span class="p_details">查看详情</span>-->
          </div>
        </div>
        <div class="number_statistical">
          <div class="item">
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[0].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[0].hj}}元</span>
            </div>
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[1].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[1].hj}}元</span>
            </div>
          </div>
          <div class="item">
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[2].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[2].hj}}元</span>
            </div>
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[3].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[3].hj}}元</span>
            </div>
          </div>
          <div class="item">
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[4].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[4].hj}}元</span>
            </div>
            <div>
              <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{xjlDate[5].fname}}</span>
              <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2">{{xjlDate[5].hj}}元</span>
            </div>
          </div>
<!--          <div-->
<!--            v-for="(item, index) in xjlDate"-->
<!--            :key="index"-->
<!--            class="number_statistical_item"-->
<!--          >-->
<!--            <span class="text-color-size-16 text-spacing" style="color:#4db3e2">{{ item.fname }}</span>-->
<!--            <span class="text-color-size-16" style="margin-right: 30px;color:#4db3e2"-->
<!--              >{{ item.hj }}元</span-->
<!--            >-->
<!--          </div>-->
        </div>
        <div class="chart-wrapper">
          <vue-e-charts
            autoresize
            :option="footerEcharts"
          ></vue-e-charts>
        </div>
      </div>
    </div>

    <!--项目建设and分公司收支情况-->
    <el-dialog
      :title="balanceTitle"
      :visible.sync="balanceVisible"
      :before-close="xmjsszClose"
      width="85%"
      class="detalis"
    >
      <!--搜索-->
      <div class="pad-b flex">
        <div class="year time">
          <el-date-picker
            v-model="xmjs.beginTime"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择开始时间">
          </el-date-picker>
        </div>
        <div class="year time">
          <el-date-picker
            v-model="xmjs.endTime"
            value-format="yyyy-MM"
            type="month"
            placeholder="选择结束时间">
          </el-date-picker>
        </div>

        <div class="year time"  v-show="isCorporation">
          <el-input
            placeholder="请输入项目名称"
            v-model="xmjs.xmmc"
            clearable
          >
          </el-input>
        </div>
        <div class="year time">
          <el-select
            v-model="xmjs.corporation"
            clearable
            placeholder="请选择分公司"
          >
            <el-option
              v-for="item in fgsOption"
              :key="item.id"
              :label="item.zdName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>
        <div class="year time">
          <el-select
            v-model="xmjs.projectState"
            clearable
            placeholder="请选择项目现状"
          >
            <el-option
              v-for="item in projectStateOption"
              :key="item.id"
              :label="item.zdName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>
        <div class="year time">
          <el-select
            v-model="xmjs.xmqkJszt"
            clearable
            placeholder="请选择建设状态"
          >
            <el-option
              v-for="item in xmjsState"
              :key="item.id"
              :label="item.zdName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>
        <div class="year time">
          <el-select
            v-model="xmjs.xmqkXmlx"
            clearable
            placeholder="请选择项目分类"
          >
            <el-option
              v-for="item in xjlOptions"
              :key="item.id"
              :label="item.zdName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>
        <div class="year time">
          <el-select
            v-model="xmjs.xmFag"
            clearable
            placeholder="请选择新老项目"
          >
            <el-option
              v-for="item in xmFagOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>

        <!--新增，未协调接口-->
        <div class="year time"  v-show="isCorporation">
          <el-select
            v-model="xmjs.xmrzRzqk"
            clearable
            placeholder="请选择融资情况"
          >
            <el-option
              v-for="item in xmrzRzqkOption"
              :key="item.id"
              :label="item.zdName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </div>
        <div class="year time"  v-show="isCorporation">
          <el-input v-model="xmjs.xmqkHzf" clearable placeholder="请输入合作方"></el-input>
        </div>
        <div class="year time"  v-show="isCorporation">
          <el-input v-model="xmjs.xmqkSgf" clearable placeholder="请输入收购方"></el-input>
        </div>
        <div class="year time"  v-show="isCorporation">
          <el-input v-model="xmjs.xmqkRzf" clearable placeholder="请输入融资方"></el-input>
        </div>


        <div class="year time">
          <el-button @click="xmjsQuery">搜索</el-button>
        </div>


      </div>

      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="XmjsList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails"
        >
          <el-table-column prop="xmqkXmxz" label="项目现状" align="center">
          </el-table-column>
          <el-table-column prop="xmqkXmmc" label="项目名称" align="center">
          </el-table-column>
          <el-table-column
            prop="xjlyjqkSrHj"
            label="预计收入（万元）"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="xjlyjqkZcHj"
            label="预计支出（万元）"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="xjlsjqkSrHj"
            label="实际收入（万元）"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="xjlsjqkZcHj"
            label="实际支出（万元）"
            align="center"
          >
          </el-table-column>
          <el-table-column prop="xjlsjqkYsye" label="应收余额" align="center">
          </el-table-column>
          <el-table-column prop="xjlsjqkYfwsk" label="应付未付" align="center">
          </el-table-column>
          <el-table-column prop="xmqkQzyf" label="应付余额" align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="xmjsSzSizeChange"
            @current-change="xmjsSzCurrentChange"
            :current-page="xmjsPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="xmjsTotal"
          >
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--融资还款详情-->
    <el-dialog
      :visible.sync="rzqkVisible"
      :before-close="rzqkClose"
      width="80%"
      class="detalis"
    >
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="融资情况" name="rzqk">
          <div class="pad-b">
            <el-select
              v-model="rzqkDetails.xllx"
              clearable
              placeholder="请选择项目类型"
            >
              <el-option
                v-for="item in rzqkOption"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-date-picker
              v-model="rzqkDetails.yearValue"
              type="year"
              value-format="yyyy"
              placeholder="选择年"
            >
            </el-date-picker>
            <el-select
              v-model="rzqkDetails.monthValue"
              clearable
              placeholder="选择月"
            >
              <el-option
                v-for="item in month"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-input v-model="rzqkDetails.financeManager"  clearable placeholder="请输入融资经理"></el-input>
            <el-input v-model="rzqkDetails.financingInstitutions"  clearable placeholder="请输入融资机构"></el-input>
            <el-select
              v-model="rzqkDetails.financingSituation"
              clearable
              placeholder="选择融资情况"
            >
              <el-option
                v-for="item in financingSituation"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>


            <el-select
              v-model="rzqkDetails.xmFag"
              clearable
              placeholder="请选择新老项目"
            >
              <el-option
                v-for="item in xmFagOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-select v-model="rzqkDetails.xmqkXmxz" clearable placeholder="请选择项目现状">
              <el-option
                v-for="item in projectStateOption"
                :key="item.id"
                :label="item.zdName"
                :value="item.id">
              </el-option>
            </el-select>
            <el-select style="margin-top:5px" v-model="rzqkDetails.xmqkJszt" clearable placeholder="请选择建设状态">
              <el-option
                v-for="item in xmjsState"
                :key="item.id"
                :label="item.zdName"
                :value="item.id">
              </el-option>
            </el-select>
            <el-date-picker
              v-model="rzqkDetails.xmrzQyrq"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择签约日期">
            </el-date-picker>
            <el-button @click="rzqkQuery">搜索</el-button>
          </div>

          <div style="overflow: hidden" class="dialog_ul">
            <el-table
              v-loading="rzqkLoading"
              element-loading-background="rgba(4,15,54, 0.1)"
              header-align="ceneter"
              :data="rzqkList"
              style="width: 100%"
              :row-class-name="tableClassName"
              @row-dblclick="rowDetails"
            >
              <el-table-column prop="xmqkXmmc" label="项目名称" align="center">
              </el-table-column>
              <el-table-column prop="xmrzRzjg" label="融资机构" align="center">
              </el-table-column>
              <el-table-column prop="xmrzSnirr" label="首年IRR" align="center">
              </el-table-column>
              <el-table-column prop="xmrzQzqzhirr" label="全周期综合IRR" align="center">
              </el-table-column>
              <el-table-column
                prop="xmrzRzze"
                label="融资总金额(万元)"
                align="center"
              >
              </el-table-column>
              <el-table-column prop="xmrzYfkze" label="累计已放款" align="center">
              </el-table-column>
              <el-table-column
                prop="byyjfk"
                label="本月预计放款(万元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="bysjfk"
                label="本月实际放款(万元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="xmrzXbfktj"
                label="预计下笔放款(万元)"
                align="center"
              >
              </el-table-column>
            </el-table>

            <div class="paging">
              <el-pagination
                @size-change="rzqkSizeChange"
                @current-change="rzqlCurrentChange"
                :current-page="rzqkPageNum"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="rzqkPageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="rzqkDetailsTotal"
              >
              </el-pagination>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="资金还款" name="zjhk">
        <div class="pad-b">
            <el-select
              v-model="zjhkDetails.xllx"
              clearable
              placeholder="请选择项目类型"
            >
              <el-option
                v-for="item in rzqkOption"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-date-picker
              v-model="zjhkDetails.yearValue"
              type="year"
              value-format="yyyy"
              placeholder="选择年"
            >
            </el-date-picker>
            <el-select
              v-model="zjhkDetails.monthValue"
              clearable
              placeholder="选择月"
            >
              <el-option
                v-for="item in month"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          <el-input v-model="zjhkDetails.financeManager"  clearable placeholder="请输入融资经理"></el-input>
          <el-input v-model="zjhkDetails.financingInstitutions"  clearable placeholder="请输入融资机构"></el-input>
          <el-select
            v-model="zjhkDetails.financingSituation"
            clearable
            placeholder="选择融资情况"
          >
            <el-option
              v-for="item in financingSituation"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>

          <el-select
            v-model="zjhkDetails.xmFag"
            clearable
            placeholder="请选择新老项目"
          >
            <el-option
              v-for="item in xmFagOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
          <el-select v-model="zjhkDetails.xmqkXmxz" clearable placeholder="请选择项目现状">
            <el-option
              v-for="item in projectStateOption"
              :key="item.id"
              :label="item.zdName"
              :value="item.id">
            </el-option>
          </el-select>
          <el-select style="margin-top:5px" v-model="zjhkDetails.xmqkJszt" clearable placeholder="请选择建设状态">
            <el-option
              v-for="item in xmjsState"
              :key="item.id"
              :label="item.zdName"
              :value="item.id">
            </el-option>
          </el-select>
          <el-date-picker
            v-model="zjhkDetails.xmrzQyrq"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择签约日期">
          </el-date-picker>

            <el-button @click="zjhkQuery">搜索</el-button>
          </div>

          <div style="overflow: hidden" class="dialog_ul">
            <el-table
              v-loading="zjhkLoading"
              element-loading-background="rgba(4,15,54, 0.51)"
              header-align="ceneter"
              :data="zjhkList"
              style="width: 100%"
              :row-class-name="tableClassName"
              @row-click="rowDetails"
            >
              <el-table-column prop="xmakXmmc" label="项目名称" align="center">
              </el-table-column>
              <el-table-column prop="fknf" label="还款年份" align="center">
              </el-table-column>
              <el-table-column prop="fkyf" label="还款月份" align="center">
              </el-table-column>
              <el-table-column prop="financingCb" label="本金（万元）" align="center">
              </el-table-column>
              <el-table-column
                prop="financingLx"
                label="利息(万元)"
                align="center"
              >
              </el-table-column>
              <el-table-column prop="financingSxf" label="手续费（万元）" align="center">
              </el-table-column>
              <el-table-column
                prop="financingBzj"
                label="保证金(万元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="financingBysjhk"
                label="合计"
                align="center"
              >
              </el-table-column>
            </el-table>

            <div class="paging">
              <el-pagination
                @size-change="zjhkSzSizeChange"
                @current-change="zjhkSzCurrentChange"
                :current-page="zjhkPageNum"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="10"
                layout="total, sizes, prev, pager, next, jumper"
                :total="zjhketailsTotal"
              >
              </el-pagination>
            </div>
          </div>

        </el-tab-pane>
      </el-tabs>

      <!--搜索-->
    </el-dialog>

    <!--项目经营数据-->
    <el-dialog
      title="项目经营数据"
      :visible.sync="projectBusinessStatus"
      width="85%">
      <div class="dltjDialog">
        <!--搜索条件-->
        <div class="dltjDialog_params">
          <div class="pad-b">
            <el-date-picker
              v-model="xmjyqk.year"
              type="year"
              placeholder="选择年"
              value-format="yyyy">
            </el-date-picker>
            <el-select v-model="xmjyqk.month" clearable placeholder="选择月">
              <el-option
                v-for="item in month"
                :key="item.value + 'b'"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            <el-select
              v-model="xmjyqk.xmqkXmxz"
              clearable
              placeholder="请选择项目现状"
            >
              <el-option
                v-for="item in projectStateOption"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-select
              v-model="xmjyqk.xmqkJszt"
              clearable
              placeholder="请选择建设状态"
            >
              <el-option
                v-for="item in xmjsState"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-select
              v-model="xmjyqk.xmqkXmlx"
              clearable
              placeholder="请选择项目分类"
            >
              <el-option
                v-for="item in xjlOptions"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-select
              v-model="xmjyqk.corporation"
              clearable
              placeholder="请选择分公司"
            >
              <el-option
                v-for="item in unitSelect"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-select
              v-model="xmjyqk.xmFag"
              clearable
              placeholder="请选择新老项目"
            >
              <el-option
                v-for="item in xmFagOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-input v-model="xmjyqk.xmqkQy" placeholder="请输入区域" clearable></el-input>

            <el-select
              v-model="xmjyqk.xmrzRzqk"
              clearable
              placeholder="请选择融资情况"
            >
              <el-option
                v-for="item in xmrzRzqkOption"
                :key="item.id"
                :label="item.zdName"
                :value="item.id"
              >
              </el-option>
            </el-select>
            <el-input v-model="xmjyqk.xmqkRzf" placeholder="请输入融资方" clearable></el-input>


            <el-button class="chilren" :class="dltjDefaultTab === 2 ? 'chilren_active' : 'chilren_focus'"  @click="xmjyDataQuery">搜索</el-button>
          </div>
        </div>

        <!--项目数量统计-->
        <div class="dltjDialog_total">
          <span class="h3_title">项目数量</span><span class="h3_title">{{xmjyshTotal}}</span>
        </div>
        <!--具体内容-->
        <div class="business_payment_container">
          <div class="business_situation">
            <div class="business_title">
              <div style="display: flex; justify-content: space-between"><p class="h3_title">预计经营情况</p><p class="title_general">单位：万元</p></div>
              <div style="display: flex; width: 100%; justify-content: space-between">
                <div v-for="(item,index) in yjjyqkStatistical" :key="index">
                  <span  style="margin-right: 10px" class="title_general">{{item.name}}</span>
                  <span class="h3_title" style="color: #00afff ;margin-right: 10px">{{item.value}}</span>
                </div>
                <div>
                  <span style="margin-right: 10px" class="title_general">预计EPC利润</span>
                  <span class="h3_title" style="color: #00afff ;margin-right: 10px">{{yjjyqkItem.xjlyjqkYjlr}}</span>
                </div>
              </div>
            </div>
            <div class="business_charts">
              <div class="chart-wrapper unit_chart">
                <vue-e-charts
                  ref="myCharts"
                  autoresize
                  :option="expect_business_data"
                ></vue-e-charts>
              </div>
            </div>
          </div>
          <div class="payment_situation">
            <div class="payment_title">
              <div class="title"> <p  class="h3_title">实际付款情况</p></div>
              <div class="money">
                <div class="money_num">
                  <span class="title_general pad-right">已付总金额</span>
                  <span  class="h3_title">{{sjfkStatistical.xjlsjqkZcHj}}</span>
                </div>
                <div class="money_num">
                  <span class="title_general pad-right">应付账款余额</span>
                  <span  class="h3_title">{{sjfkStatistical.xjlsjqkYfzkye}}</span>
                </div>
                <div class="money_num">
                  <span class="title_general pad-right">实际应付未付</span>
                  <span  class="h3_title">{{sjfkStatistical.xjlsjqkYfwsk}}</span>
                </div>
                <div class="money_num">
                  <span class="title_general pad-right">应收履约保证金</span>
                  <span  class="h3_title">{{sjfkStatistical.xjlsjqkLybzj}}</span>
                </div>
                <div class="money_num">
                  <span class="title_general pad-right">应付账款总额</span>
                  <span  class="h3_title">{{sjfkStatistical.xjlsjqkXmyfzkzje}}</span>
                </div>
              </div>

            </div>
            <div class="payment_charts">
              <div class="chart-wrapper unit_chart">
                <vue-e-charts
                  ref="myCharts"
                  autoresize
                  :option="payment_business_data"
                ></vue-e-charts>
              </div>
            </div>
          </div>
        </div>
        <div class="actual_payment">
          <div class="actual_situation">
            <div class="actual_situation_title">
              <div style="display: flex; justify-content: space-between">
                <p class="h3_title">实际收入情况</p>
                <p class="title_general">单位：万元</p>
              </div>
              <div style="display: flex">
                <div style="padding: 0">
                  <span class="title_general">{{actualStatisticalTitle.name}}</span>
                  <span  class="h3_title">{{actualStatisticalTitle.value}}</span>
                </div>
                <div style=" padding: 0">
                  <span class="title_general">{{actualStatisticalTitle1.name}}</span>
                  <span  class="h3_title">{{actualStatisticalTitle1.value}}</span>
                </div>
              </div>
            </div>
            <div class="actual_charts_container">
              <div class="actual_bar">
                <div class="chart-wrapper unit_chart">
                  <vue-e-charts
                    ref="myCharts"
                    autoresize
                    :option="various_chartsData"
                  ></vue-e-charts>
                </div>
              </div>
              <div class="actual_bar">
                <div class="chart-wrapper unit_chart">
                  <vue-e-charts
                    ref="myCharts"
                    autoresize
                    :option="various_chartsData1"
                  ></vue-e-charts>
                </div>
              </div>
              <div class="actual_bar">
                <div class="chart-wrapper unit_chart">
                  <vue-e-charts
                    ref="myCharts"
                    autoresize
                    :option="various_chartsData2"
                  ></vue-e-charts>
                </div>
              </div>
              <div class="actual_bar">
                <div class="chart-wrapper unit_chart">
                  <vue-e-charts
                    ref="myCharts"
                    autoresize
                    :option="various_chartsData3"
                  ></vue-e-charts>
                </div>
              </div>
            </div>
          </div>
          <div class="actual_charts">
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                ref="myCharts"
                autoresize
                :option="actual_statistical"
              ></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import countTo from "vue-count-to";
import {
  fgsSkqk,
  getLatestData,
  getSubsidiaryData,
  rzqkCharts,
  xjlCharts,
  xjlTitle,
  xmjsStatistical,
  xmjsSzqk,
  xmlxCharts,
  xmjsAnd,
  getZjhkList, getRzqkList, getSjfkqk, getSjskqk, getXmjyqk, getSjTotal, getCash,
} from "@/api/screen/zl";
import { processing } from "@/views/visualizationScreen/pulic";
import {fontSize} from "@/views/visualizationScreen/automatic/adapter";


var echarts = require("echarts");
var colorArray = [
  {
    top: '#4f9aff', //深蓝
    bottom: 'rgba(11,42,84,.3)'
  }
];

export default {
  name: "money",
  components: {
    countTo,
  },
  data() {
    return {
      month: [
        { value: 1, label: "1月" },
        { value: 2, label: "2月" },
        { value: 3, label: "3月" },
        { value: 4, label: "4月" },
        { value: 5, label: "5月" },
        { value: 6, label: "6月" },
        { value: 7, label: "7月" },
        { value: 8, label: "8月" },
        { value: 9, label: "9月" },
        { value: 10, label: "10月" },
        { value: 11, label: "11月" },
        { value: 12, label: "12月" },
      ],
      startVal: 0,
      endVal: 2530,
      //分公司
      fgsOption: [],
      //项目现状
      projectStateOption:[],

      //项目建设收支情况
      balance_payments: {},
      //项目建设收支详情弹框
      balanceVisible: false,
      balanceTitle:'',
      xmjs: {
        isFull: 1,
        xmmc: "",
        corporation: "",
        beginTime: "",
        endTime: "",
        projectStatus: "",
        projectState:"",
        xmqkJszt:'',
        xmqkXmlx: '',
        xmFag:'',

  },
      isCorporation: false,
      xmjsPageNum: 1,
      xmjsPageSize: 10,
      xmjsTotal: 0,
      XmjsList: [],

      options: {},

      defaultTab: 30,

      value2: "",
      value3: "",

      allLatestDataSet: {
        xmZs:0,
        xmRlzs:0,
        xmYjcsze:0,
        xmYjepcjszj:0
      },

      zjStatistical: {
        xjlsjqkYsye:0,
        xjlsjqkYfwsk:0,
        xmqkQzzyf:0,
        xmqkQzzyfXmgs:0,
        xmqkYflybzj:0
      },

      type_project_option: {},


      financing: {},

      rzStatistical: {},

      footerEcharts: {},

      payments: {},

      //分公司收支情况条件查询+
      fgs: {
        beginTime: "",
        endTime: "",
      },

      //风电、光伏
      xmfl: {
        beginTime: "",
        endTime: "",
        xmTYpe: "",
      },
      //项目分类的统计数据
      statistical: {},

      //融资情况
      rzqk: {
        beginTime: "",
        endTime: "",
        projectType: "",
      },

      //融资情况详情查询条件
      rzqkLoading:false,
      rzqkOption: [],
      rzqkList: [],
      rzqkDetails: {
        isFull: 1,
        monthValue: "",
        xllx: "",
        yearValue: "",
        financeManager:'',
        financingInstitutions:'',
        financingSituation:'',
        xmFag:'',
        xmqkXmxz:'',
        xmqkJszt:''

      },
      rzqkPageNum: 1,
      rzqkPageSize: 10,
      activeName: "rzqk",
      rzqkVisible: false,
      rzqkDetailsTotal: 0,

      //资金还款
      zjhkLoading:false,
      zjhkList:[],
      zjhkDetails: {
        isFull: 1,
        monthValue: "",
        xllx: "",
        yearValue: "",
        financeManager:'',
        financingInstitutions:'',
        financingSituation:'',
        xmFag:'',
        xmqkXmxz:'',
        xmqkJszt:'',
        xmrzQyrq:'',

      },
      financingSituation:[{ label: '已放完', value:111 },{ label: '已融资', value:109 },{ label: '未融资', value:110 }],
      zjhkPageNum: 1,
      zjhkPageSize: 10,
      zjhketailsTotal:0,


      //现金流情况
      xjl: {
        yearValue: "",
        monthValue: "",
        projectType: "",
      },
      xjlDate: [],

      //现金流类型选择
      xjlOptions: [],

      //项目经营数据情况详情
      projectBusinessStatus:false,
      xmjyshTotal:0,
      expect_business_data:{},
      payment_business_data:{},
      actual_statistical:{},
      actualStatisticalTitle:{},
      actualStatisticalTitle1:{},
      xmjyqk:{
        year:'',
        month:'',
        xmqkXmxz:'',//项目现状
        xmqkJszt:'',//建设状态
        xmqkXmlx:'',//项目分类（光伏、光电）
        corporation:'',//分公司
        xmFag:'',//项目标记：0老项目、1新项目
        xmqkQy:'',//区域
        xmqkRzf:'',
        xmrzRzqk:'',

      },
      //项目类型
      projectTypeOptions:[],
      //建设状态
      xmjsState:[],
      //融资情况条件
      xmrzRzqkOption:[],
      //f分公司
      unitSelect:[],
      xmFagOptions:[{name:'老项目',id:0},{name:'新项目',id:1}],

      various_chartsData:{},
      various_chartsData1:{},
      various_chartsData2:{},
      various_chartsData3:{},
      //项目经营数据
      yjjyqkData:[],
      yjjyqkStatistical:[],
      yjjyqkItem:{},

      //实际付款情况
      sjfkqkData:[],
      sjfkStatistical:{},

      //实际收入情况
      sjsrqkData:[],
      sjsrhjStatistical:{},
      dltjDefaultTab:'',
    };
  },
  methods: {
    /**弹框table*/
    tableClassName({ row, rowIndex }) {
      return "row-active-style";
    },

    tab_money(e) {
      this.defaultTab = e;
      this.xmfl.xmTYpe = e;
      this.getXmlx();
    },

    //中间第1行数据统计
    getLatest() {
      /**isDq:1表示统计全部数据*/
      getLatestData({isDq:1}).then((res) => {
        if(res.code === 200 && res.data){
          for (let i in res.data) {
            res.data[i] = Number(res.data[i]);
          }
          this.allLatestDataSet = res.data;
        }else{
          this.allLatestDataSet = {
            xmZs:0,
            xmRlzs:0,
            xmYjcsze:0,
            xmYjepcjszj:0
          }
        }
      });

      xmjsStatistical().then((res) => {
        if (res.code === 200 && res.data) {
          this.zjStatistical = res.data;
          for(let i in res.data){
            res.data[i] = Number(res.data[i])
          }
        }else{
          this.zjStatistical =  {

          }
        }

      });
    },

    //项目建设收支情况
    getXmjsSz() {
      xmjsSzqk().then((res) => {
        let chartsData = processing(res.data.key, res.data.value);
        if (res.code === 200 && res.data) {
          this.balance_payments = {
            tooltip: {
              trigger: "item",
              formatter: "{b} : {d}%",
            },
            series: [
              {
                type: "pie",
                radius: ["0%", "70%"],
                avoidLabelOverlap: false,
                itemStyle: {
                  borderColor: "#040f36",
                  borderWidth: 5,
                },
                label: {
                  show: false,
                  position: "center",
                },
                labelLine: {
                  show: false,
                },
                data: chartsData,
              },
            ],
          };
        }else{
          this.balance_payments = {
            tooltip: {
              trigger: "item",
              formatter: "{b} : {d}%",
            },
            series: [
              {
                type: "pie",
                radius: ["0%", "70%"],
                avoidLabelOverlap: false,
                itemStyle: {
                  borderColor: "#040f36",
                  borderWidth: 5,
                },
                label: {
                  show: false,
                  position: "center",
                },
                labelLine: {
                  show: false,
                },
                data: [],
              },
            ],
          };
        }
      });
    },

    xmjsszClose(done) {
      (this.xmjs = {
        isFull: 1,
        xmmc: "",
        corporation: "",
        yearValue: "",
        month: "",
        projectStatus: "",
        projectState:"",
        xmqkJszt:'',
        xmqkXmlx: '',
        xmFag:'',
      }),
        done();
    },

    xmjsDetails(index) {
      /**如果是项目建设收支情况(1)则需要项目名称这个参数,如果不是则不需要 */
      switch (index) {
        case 1:
          this.balanceTitle = '项目建设收支情况'
          this.xmjs = {
            isFull: 1,
            xmmc: "",
            corporation: "",
            yearValue: "",
            month: "",
            projectStatus: "",
            projectState:"",
            xmqkJszt:'',
            xmqkXmlx: '',
            xmFag:'',
          };
          this.isCorporation = true;
          break;
        case 2:
          this.balanceTitle = '分公司收支情况'
          this.xmjs = {
            isFull: 2,
            corporation: "",
            yearValue: "",
            month: "",
            projectStatus: "",
            projectState:"",
            xmqkJszt:'',
            xmqkXmlx: '',
            xmFag:'',
          };
          this.isCorporation = false;
          break;
      }
      this.balanceVisible = true;
      this.xmjsQuery();
    },

    xmjsQuery() {
      this.xmjs.pageNum = this.xmjsPageNum;
      this.xmjs.pageSize = this.xmjsPageSize;
      xmjsAnd(this.xmjs).then((res) => {
        if (res.code === 200 && res.data) {
          this.XmjsList = res.data.rsList;
          this.xmjsTotal = res.data.total;
          res.data.apiXmXmjsszqkTotal.xmqkXmmc = '总计'
          res.data.apiXmXmjsszqkTotal.xmqkXmxz = '...'
          this.XmjsList.unshift(res.data.apiXmXmjsszqkTotal)
        }else{
          this.XmjsList = []
          this.xmjsTotal = 0
        }
      });
    },

    xmjsSzSizeChange(val) {
      this.xmjsPageSize = val;
      this.xmjsQuery();
    },

    xmjsSzCurrentChange(val) {
      this.xmjsPageNum = val;
      this.xmjsQuery();
    },

    //分公司收款情况
    getFgsSkqk() {
      fgsSkqk(this.fgs).then((res) => {
        let chartsData;
        let value;
        if (res.code === 200 && res.data) {
          chartsData = processing(res.data.key, res.data.value);
          value = res.data.value;
          this.payments = {
            grid: {
              left: "1%",
              right: "1%",
              bottom: "1%",
              containLabel: true,
            },
            series: [
              {
                type: "pie",
                radius: ["28%", "31%"],
                startAngle: 100,
                data: value,
                labelLine: {
                  show: false,
                },
                z: 3,
                emphasis: {
                  show: false,
                },
              },
              {
                type: "pie",
                startAngle: 100,
                radius: ["31%", "75%"],
                roseType: "radius",
                emphasis: {
                  label: {
                    show: true,
                  },
                  labelLine: {
                    lineStyle: {
                      color: "#00ffff",
                    },
                  },
                },
                label: {
                  show: true,
                  color: "#fff",
                  fontSize:fontSize(15),
                  formatter: "{b} : {d}%",
                },
                tooltip: {
                  show: true,
                  formatter: "{b} : {d}%",
                },
                data: chartsData,
              },
            ],
          };
        } else {
          chartsData = [];
          value = [0];
          this.payments = {
            grid: {
              left: "1%",
              right: "1%",
              bottom: "1%",
              containLabel: true,
            },
            series: [
              {
                type: "pie",
                radius: ["28%", "31%"],
                startAngle: 100,
                data: [],
                labelLine: {
                  show: false,
                },
                z: 3,
                emphasis: {
                  show: false,
                },
              },
              {
                type: "pie",
                startAngle: 100,
                radius: ["31%", "75%"],
                roseType: "radius",
                emphasis: {
                  label: {
                    show: true,
                  },
                  labelLine: {
                    lineStyle: {
                      color: "#00ffff",
                    },
                  },
                },
                label: {
                  show: true,
                  color: "#fff",
                  fontSize:fontSize(15),
                  formatter: "{b} : {d}%",
                },
                tooltip: {
                  show: true,
                  formatter: "{b} : {d}%",
                },
                data: [],
              },
            ],
          };
        }
      });
    },

    //分公司收款情况详情
    getFgsSkList() {},

    //分公司收款情况年份查询
    fgsYearQuery() {
      this.getFgsSkqk();
    },

    //分公司收款情况按月份查询
    fgsMonthQuery(val) {
      this.fgs.monthValue = val;
      this.getFgsSkqk();
    },

    //风电/光伏项目
    getXmlx() {
      this.xmfl.xmTYpe = this.defaultTab

      xmlxCharts(this.xmfl).then((res) => {
        let chartsData;
        let keyArr, valueArr, title1, chartsValue1, chartsValue2;
        if (res.code === 200 && res.data) {
          keyArr = res.data.content.key.splice(res.data.content.key.length - 3, 3);
          valueArr = res.data.content.value.splice(res.data.content.value.length - 3, 3);
          title1 = [
            [res.data.content.key[0], res.data.content.key[1]],
            [res.data.content.key[2], res.data.content.key[3]],
            [res.data.content.key[4], res.data.content.key[5]],
          ];

          chartsValue1 = [Number(res.data.content.value[0]), Number(res.data.content.value[2]), Number(res.data.content.value[4])]
          chartsValue2 = [{value:Number(res.data.content.value[1]),results:Number(res.data.contentSbu.value[0])},{value:Number(res.data.content.value[3]),results:Number(res.data.contentSbu.value[1])},{value:Number(res.data.content.value[5]),results:Number(res.data.contentSbu.value[2])}]

          this.statistical = processing(keyArr, valueArr);
        } else {
          this.statistical = [];
          title1 = [];
          chartsValue1 = [];
          chartsValue2 = [];
        }
        this.type_project_option = {
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
            formatter: function (params) {
              var title = "";
              switch (params[0].dataIndex) {
                case 0:
                  title = "预计利润";
                  break;
                case 1:
                  title = "应收余额";
                  break;
                case 2:
                  title = "合同余额";
                  break;
              }
              var str = `${title}:${
                params[1].data.results
              }万元<br/>`;
              str += `${params[0].name.split(",")[0]}:${
                params[0].value
              }万元<br/>`;
              str += `${params[1].name.split(",")[1]}:${params[1].value}万元`;
              return str;
            },
          },
          grid: {
            top: "10%",
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              axisTick: { show: false },
              axisLine: {
                onZero: false,
                lineStyle: {
                  color: '#c0c0c0'
                }
              },
              axisLabel: {
                fontSize:fontSize(12)
              },
              data: title1,
            },
          ],
          yAxis: {
            type: "value",
            show: true,
            splitLine: {
              show: true,
              lineStyle: {
                type: "dashed",
                color: "rgba(35, 145, 162, 0.5)",
              },
            },
            axisLabel: {
              fontSize:fontSize(15)
            }
          },
          series: [
            {
              name: "Forest",
              type: "bar",
              data: chartsValue1,
              label: {
                show: true,
                position: 'top',
                color:'#fff',
                fontSize:fontSize(15)
              },
              itemStyle: {
                normal: {
                  //这里是颜色
                  color: function (params) {
                    //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                    var colorList = [
                      "rgba(50,145,248,0.5)",
                      "rgba(126,230,151,0.5)",
                      "rgba(248,210,50,0.5)",
                    ];
                    return colorList[params.dataIndex];
                  },
                },
              },
            },
            {
              name: "Steppe",
              type: "bar",
              barGap:'40%',
              data: chartsValue2,
              label: {
                show: true,
                position: 'top',
                color:'#fff',
                fontSize:fontSize(15)
              },
              itemStyle: {
                normal: {
                  //这里是颜色
                  color: function (params) {
                    //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                    var colorList = [
                      "rgba(89,219,255,0.5)",
                      "rgba(255,201,166,0.5)",
                      "rgba(156,203,255,0.5)",
                    ];
                    return colorList[params.dataIndex];
                  },
                },
              },
            },
          ],
        };
      });
    },

    //项目分类年份
    xmflYearQuery() {
      this.getXmlx();
    },

    //融资情况
    getRzqkData() {
      rzqkCharts(this.rzqk).then((res) => {
        let keyData, valueData;
        if (res.code === 200 && res.data) {
          keyData = res.data.key;
          valueData = res.data.value;
          this.rzStatistical = {
            title: res.data.key.splice(res.data.key.length - 1, 1)[0],
            value: res.data.value.splice(res.data.value.length - 1, 1)[0],
          };
        } else {
          keyData = [];
          valueData = [];
          this.rzStatistical = {
            title: "",
            value: "",
          };
        }
        this.financing = {
          color:["rgba(30, 144, 255, 0.5)", 'rgba(32, 178, 170, 0.5)', 'rgba(216, 191, 216, 0.5)', 'rgba(255, 255, 0, 0.5)'],
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "none",
            },
            formatter: function (params) {
              return params[0].axisValue + " : " + params[0].value + "万元";
            },
          },
          grid: {
            top: "10%",
            left: "2%",
            right: "2%",
            bottom: "5%",
            containLabel: true,
          },
          xAxis: {
            type: "category",
            axisLine: {
              onZero: false,
              lineStyle: {
                color: '#c0c0c0'
              }
            },
            axisLabel:{
              fontSize:fontSize(15)
            },
            data: keyData,
          },
          yAxis: {
            type: "value",
            show: true,
            splitLine: {
              show: true,
              lineStyle: {
                type: "dashed",
                color: "rgba(35, 145, 162, 0.5)",
              },
            },
            axisLabel:{
              fontSize:fontSize(15)
            },
          },
          series: [
            {
              data: valueData,
              label: {
                show: true,
                position: 'top',
                color:'#fff',
                fontSize:fontSize(15)
              },
              type: "bar",
              barWidth: 60,
              colorBy: "data",
              showBackground: true,
              // label: {
              //   show: true,
              //   position: 'inside',
              //   textStyle: {
              //     color:'#fff'
              //   }
              // },
              backgroundStyle: {
                color: "rgba(127,122,122,0.2)",
              },
            },
          ],
        };
      });
    },

    //融资情况类型
    rzqkTypeQuery(val) {
      this.getRzqkData();
    },

    //融资情况年份
    rzqkYearQuery() {
      this.getRzqkData();
    },


    //tabs切换
    handleClick(tab, event) {
      //切换融资情况何资金还款接口
      switch (this.activeName){
        case 'rzqk':
          this.rzqkLoading = true
          getRzqkList(this.rzqkDetails).then((res) => {
            this.rzqkLoading = false
            if (res.code === 200 && res.data) {
              this.rzqkDetailsTotal = res.data.total;
              this.rzqkList = res.data.rsList;
              res.data.apiXmZjrzTotal.xmqkXmmc = '总计'
              this.rzqkList.unshift(res.data.apiXmZjrzTotal)
            } else {
              this.rzqkDetailsTotal = 0;
              this.rzqkList = [];
            }
          });
          break;
        case  'zjhk':
          this.zjhkLoading = true
          getZjhkList(this.zjhkDetails).then((res) => {
            this.zjhkLoading = false
            if (res.code === 200 && res.data) {
              this.zjhketailsTotal = res.data.total;
              this.zjhkList = res.data.rsList;
              res.data.apiXmZjrzTotal.xmakXmmc = '总计'
              this.zjhkList.unshift(res.data.apiXmZjrzTotal)
            } else {
              this.zjhketailsTotal = 0;
              this.zjhkList = [];
            }
          });
          break;
      }
    },

    rzqkSizeChange(val){
      this.rzqkPageSize = val
      this.rzqkQuery()
    },

    rzqlCurrentChange(val){
      this.rzqkPageNum = val
      this.rzqkQuery()
    },

    zjhkSzSizeChange(val){
      console.log('Size', val)
      this.zjhkPageSize = val
      this.zjhkQuery()
    },

    zjhkSzCurrentChange(val){
      console.log('current', val)
      this.zjhkPageNum = val
      this.zjhkQuery()
    },

    rzqkClose(done) {
      this.rzqkPageNum = 1
      this.rzqkPageSize = 10
      this.rzqkDetails = {
        isFull: 1,
        monthValue: "",
        xllx: "",
        yearValue: "",
      };
      done();
    },

    //打开详情融资情况/资金还款弹框
    rzqkDetailsOpen() {
      this.rzqkLoading = true
      this.rzqkVisible = true;
      //建设状态查询
      getSubsidiaryData(8).then((res) => {
        if (res.code === 200 && res.data) {
          this.rzqkOption = res.data;
        } else {
          this.rzqkOption = [];
        }
      });
      this.rzqkDetails.pageNum = this.rzqkPageNum;
      this.rzqkDetails.pageSize = this.rzqkPageSize;
      getRzqkList(this.rzqkDetails).then((res) => {
        this.rzqkLoading = false
        if (res.code === 200 && res.data) {
          this.rzqkDetailsTotal = res.data.total;
          this.rzqkList = res.data.rsList;
          res.data.apiXmZjrzTotal.xmqkXmmc = '总计'
          this.rzqkList.unshift(res.data.apiXmZjrzTotal)
        } else {
          this.rzqkDetailsTotal = 0;
          this.rzqkList = [];
        }
      });
    },

    //融资情况条件查询
    rzqkQuery() {
      this.rzqkDetails.pageNum = this.rzqkPageNum;
      this.rzqkDetails.pageSize = this.rzqkPageSize;
      getRzqkList(this.rzqkDetails).then((res) => {
        if (res.code === 200 && res.data) {
          this.rzqkDetailsTotal = res.data.total;
          this.rzqkList = res.data.rsList;
          res.data.apiXmZjrzTotal.xmqkXmmc = '总计'
          this.rzqkList.unshift(res.data.apiXmZjrzTotal)
        } else {
          this.rzqkDetailsTotal = 0;
          this.rzqkList = [];
        }
      });
    },

    //资金还款条件查询
    zjhkQuery(){
      this.zjhkDetails.pageNum = this.zjhkPageNum;
      this.zjhkDetails.pageSize = this.zjhkPageSize;
      getZjhkList(this.zjhkDetails).then((res) => {
        this.zjhkLoading = false
        if (res.code === 200 && res.data) {
          this.zjhketailsTotal = res.data.total;
          this.zjhkList = res.data.rsList;
          res.data.apiXmZjrzTotal.xmakXmmc = '总计'
          this.zjhkList.unshift(res.data.apiXmZjrzTotal)
        } else {
          this.zjhketailsTotal = 0;
          this.zjhkList = [];
        }
      });

    },

    //现金流情况
    getXjlData() {
      xjlTitle(this.xjl).then((res) => {
        if (res.code === 200) {
          this.xjlDate = res.data;
        }
      });
    },

    //现金流情况echarts图
    getXjlEcharts() {
      xjlCharts(this.xjl).then((res) => {
        let chartsData1, chartsData2, chartsData3, chartsData4;
        if (res.code === 200 && res.data) {
          chartsData1 = res.data[0].value;
          chartsData2 = res.data[1].value;
          chartsData3 = res.data[2].value;
          chartsData4 = res.data[3].value;
        } else {
          chartsData1 = [];
          chartsData2 = [];
          chartsData3 = [];
          chartsData4 = [];
        }
        this.footerEcharts = {
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["预计收入", "预计支出", "实际收入", "实际支出"],
            textStyle: {
              color: "#fff",
              fontSize:fontSize(15)
            },
          },
          grid: {
            left: "1%",
            right: "1%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月",
            ],
            axisLine: {
              onZero: false,
              lineStyle: {
                color: '#c0c0c0'
              }
            },
            axisLabel:{
              fontSize:fontSize(15)
            },
          },
          yAxis: {
            type: "value",
            splitLine: {
              show: true,
              lineStyle: {
                type: "dashed",
                color: "rgba(35, 145, 162, 0.5)",
              },
            },
            axisLabel:{
              fontSize:fontSize(15)
            },
          },
          series: [
            {
              name: "预计收入",
              type: "line",
              data: chartsData1,
            },
            {
              name: "预计支出",
              type: "line",
              data: chartsData2,
            },
            {
              name: "实际收入",
              type: "line",
              data: chartsData3,
            },
            {
              name: "实际支出",
              type: "line",
              data: chartsData4,
            },
          ],
        };
      });
    },

    //现金流年份查询
    xjlYearQuery(val) {
      this.xjl.yearValue = val;
      this.getXjlData();
      this.getXjlEcharts();
    },

    //现金流约份查询
    xjlMonthQuery(val) {
      this.xjl.monthValue = val;
      this.getXjlData();
      this.getXjlEcharts();
    },

    //现金流项目类型
    xjlTyoeQuery(val) {
      this.xjl.projectType = val;
      this.getXjlData();
      this.getXjlEcharts();
    },

    /**项目经营数据搜索*/
    xmjyDataQuery(){
      this.projectBusiness()
    },

    /**打开项目经营数据*/
    projectBusiness(){
      this.projectBusinessStatus = true
      let params = {
        monthValue:this.xmjyqk.month,
        xmid:'',
        yearValue:this.xmjyqk.year,
        xmqkXmxz:this.xmjyqk.xmqkXmxz,
        xmqkJszt:this.xmjyqk.xmqkJszt,
        xmqkXmlx:this.xmjyqk.xmqkXmlx,
        corporation:this.xmjyqk.corporation,//分公司
        xmFag:this.xmjyqk.xmFag,//项目标记：0老项目、1新项目
        xmqkQy:this.xmjyqk.xmqkQy,//区域
        xmrzRzqk:this.xmjyqk.xmrzRzqk,//融资情况
        xmqkRzf:this.xmjyqk.xmqkRzf//融资方

      }

      getSjfkqk(params).then(res => {
        if(res.code === 200 && res.data){
          this.sjfkqkData = processing(res.data.key, res.data.value)
          this.sjfkStatistical = res.data.item
        }else{
          this.sjfkqkData = []
          this.sjfkStatistical = {}
        }

        this.payment_business_data = {
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          label: {
            show: true,
            color:'#fff'
          },
          legend: {
            show: false,
            // icon: "circle",
            // top: "center",
            // left: '64%',
            // width: 50,
            // padding: [0, 5],
            // itemGap: 25,
            // textStyle: {
            //   color: "rgb(0, 178, 246)",
            //   rich: {
            //     title: {
            //       lineHeight: 5,
            //       color: "rgb(0, 178, 246)"
            //     },
            //     value: {
            //       lineHeight: 5,
            //       color: "#fff"
            //     }
            //   }
            // },
          },
          series: [{
            name: '实际付款情况',
            type: 'pie',
            radius: [10, 70],
            roseType: 'area',
            data: this.sjfkqkData
          }
          ]
        }
      })


      let arrData , arr = []
      getSjskqk(params).then(res => {
        if(res.code === 200 && res.data){
          arrData = processing(res.data.key, res.data.value)

          res.data.key.map((v, j) => {
            const obj = {}
            obj[v] = res.data.value[j]
            arr.push(obj)
          })
          this.actualStatisticalTitle = arrData[4]
          this.actualStatisticalTitle1 = arrData[6]
        }
        this.various_chartsData = {
          grid: {
            show: 'true',
            borderWidth: '0',
            height: '10%',
            width: '55%',
            x: '25%',
            top:'35%'
            // y:"20%",
          },
          xAxis: {
            show: false, //是否显示x轴
            type: 'value',
          },
          yAxis: [
            {
              type: 'category',
              inverse: true, //让y轴数据逆向
              axisLabel: {
                show: true,
                // textStyle: {
                //   color: '#26bcf8',
                //   fontWeight: 'bold',
                // },
                verticalAlign: 'bottom',
                align: 'left',
                padding: [0, 0, fontSize(-30), fontSize(10)],
                textStyle: {
                  color: '#befbff',
                  fontSize: fontSize(15),
                }
              },
              splitLine: { show: false }, //横向的线
              axisTick: { show: false }, //y轴的端点
              axisLine: { show: false }, //y轴的线
              data: [res.data.key[0]],
            },
            {
              type: 'category',
              inverse: true,
              axisTick: 'none',
              axisLine: 'none',
              show: true,
              axisLabel: {
                textStyle: {
                  color: '#ffffff',
                  verticalAlign: 'bottom',
                  align: 'right',
                  padding: [0, fontSize(55), fontSize(10), 0],
                  fontSize:fontSize(20)
                },
                formatter: function (value) {
                  return value + '';
                },
              },
              data: [res.data.value[0]],
            },
          ],
          series: [
            {
              name: '产出',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: new echarts.graphic.LinearGradient(1, 0, 0, 1, [
                    {
                      offset: 1,
                      color: 'rgba(165, 222, 71, 1)',
                    },
                    {
                      offset: 0,
                      color: 'rgba(11, 193, 234, 0.5)',
                    },
                  ]),
                },
              },
              label: {
                normal: {
                  show: false,
                  position: 'inside',
                  color: 'black',
                  fontSize: fontSize(15)
                },
              },
              barWidth: 20,
              data: [res.data.value[0]],
            },
            {
              name: '计划数',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: 'rgba(29,108,194,0.4)',
                },
              },
              barGap: '-100%',
              z: 0,
              barWidth: 20,
              data: [res.data.value[4]],
            },
          ]
        }
        this.various_chartsData1 = {
          grid: {
            show: 'true',
            borderWidth: '0',
            height: '10%',
            width: '55%',
            x: '25%',
            top:'35%'
            // y:"20%",
          },
          xAxis: {
            show: false, //是否显示x轴
            type: 'value',
          },
          yAxis: [
            {
              type: 'category',
              inverse: true, //让y轴数据逆向
              axisLabel: {
                show: true,
                // textStyle: {
                //   color: '#26bcf8',
                //   fontWeight: 'bold',
                // },
                verticalAlign: 'bottom',
                align: 'left',
                padding: [0, 0, fontSize(-30), fontSize(10)],
                textStyle: {
                  color: '#befbff',
                  fontSize: fontSize(15),
                }
              },
              splitLine: { show: false }, //横向的线
              axisTick: { show: false }, //y轴的端点
              axisLine: { show: false }, //y轴的线
              data: [res.data.key[1]],
            },
            {
              type: 'category',
              inverse: true,
              axisTick: 'none',
              axisLine: 'none',
              show: true,
              axisLabel: {
                textStyle: {
                  color: '#ffffff',
                  verticalAlign: 'bottom',
                  align: 'right',
                  padding: [0, fontSize(55), fontSize(10), 0],
                  fontSize:fontSize(20)
                },
                formatter: function (value) {
                  return value + '';
                },
              },
              data: [res.data.value[1]],
            },
          ],
          series: [
            {
              name: '产出',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: new echarts.graphic.LinearGradient(1, 0, 0, 1, [
                    {
                      offset: 1,
                      color: 'rgba(165, 222, 71, 1)',
                    },
                    {
                      offset: 0,
                      color: 'rgba(11, 193, 234, 0.5)',
                    },
                  ]),
                },
              },
              label: {
                normal: {
                  show: false,
                  position: 'inside',
                  color: 'black',
                  fontSize: fontSize(15),
                },
              },
              barWidth: 20,
              data: [res.data.value[1]],
            },
            {
              name: '计划数',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: 'rgba(29,108,194,0.4)',
                },
              },
              barGap: '-100%',
              z: 0,
              barWidth: 20,
              data: [res.data.value[4]],
            },
          ]
        }
        this.various_chartsData2 = {
          grid: {
            show: 'true',
            borderWidth: '0',
            height: '10%',
            width: '55%',
            x: '25%',
            top:'35%'
            // y:"20%",
          },
          xAxis: {
            show: false, //是否显示x轴
            type: 'value',
          },
          yAxis: [
            {
              type: 'category',
              inverse: true, //让y轴数据逆向
              axisLabel: {
                show: true,
                // textStyle: {
                //   color: '#26bcf8',
                //   fontWeight: 'bold',
                // },
                verticalAlign: 'bottom',
                align: 'left',
                padding: [0, 0, fontSize(-30), fontSize(10)],
                textStyle: {
                  color: '#befbff',
                  fontSize: fontSize(15),
                }
              },
              splitLine: { show: false }, //横向的线
              axisTick: { show: false }, //y轴的端点
              axisLine: { show: false }, //y轴的线
              data: [res.data.key[2]],
            },
            {
              type: 'category',
              inverse: true,
              axisTick: 'none',
              axisLine: 'none',
              show: true,
              axisLabel: {
                textStyle: {
                  color: '#ffffff',
                  verticalAlign: 'bottom',
                  align: 'right',
                  padding: [0, fontSize(55), fontSize(10), 0],
                  fontSize:fontSize(20)
                },
                formatter: function (value) {
                  return value + '';
                },
              },
              data: [res.data.value[2]],
            },
          ],
          series: [
            {
              name: '产出',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: new echarts.graphic.LinearGradient(1, 0, 0, 1, [
                    {
                      offset: 1,
                      color: 'rgba(165, 222, 71, 1)',
                    },
                    {
                      offset: 0,
                      color: 'rgba(11, 193, 234, 0.5)',
                    },
                  ]),
                },
              },
              label: {
                normal: {
                  show: false,
                  position: 'inside',
                  color: 'black',
                  fontSize: 14,
                },
              },
              barWidth: 20,
              data: [res.data.value[2]],
            },
            {
              name: '计划数',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: 'rgba(29,108,194,0.4)',
                },
              },
              barGap: '-100%',
              z: 0,
              barWidth: 20,
              data: [res.data.value[4]],
            },
          ]
        }
        this.various_chartsData3 = {
          grid: {
            show: 'true',
            borderWidth: '0',
            height: '10%',
            width: '55%',
            x: '25%',
            top:'35%'
            // y:"20%",
          },
          xAxis: {
            show: false, //是否显示x轴
            type: 'value',
          },
          yAxis: [
            {
              type: 'category',
              inverse: true, //让y轴数据逆向
              axisLabel: {
                show: true,
                // textStyle: {
                //   color: '#26bcf8',
                //   fontWeight: 'bold',
                // },
                verticalAlign: 'bottom',
                align: 'left',
                padding: [0, 0, fontSize(-30), fontSize(10)],
                textStyle: {
                  color: '#befbff',
                  fontSize: fontSize(15),
                }
              },
              splitLine: { show: false }, //横向的线
              axisTick: { show: false }, //y轴的端点
              axisLine: { show: false }, //y轴的线
              data: [res.data.key[3]],
            },
            {
              type: 'category',
              inverse: true,
              axisTick: 'none',
              axisLine: 'none',
              show: true,
              axisLabel: {
                textStyle: {
                  color: '#ffffff',
                  verticalAlign: 'bottom',
                  align: 'right',
                  padding: [0, fontSize(55), fontSize(10), 0],
                  fontSize:fontSize(20)
                },
                formatter: function (value) {
                  return value + '';
                },
              },
              data: [res.data.value[3]],
            },
          ],
          series: [
            {
              name: '产出',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: new echarts.graphic.LinearGradient(1, 0, 0, 1, [
                    {
                      offset: 1,
                      color: 'rgba(165, 222, 71, 1)',
                    },
                    {
                      offset: 0,
                      color: 'rgba(11, 193, 234, 0.5)',
                    },
                  ]),
                },
              },
              label: {
                normal: {
                  show: false,
                  position: 'inside',
                  color: 'black',
                  fontSize: fontSize(15),
                },
              },
              barWidth: 20,
              data: [res.data.value[3]],
            },
            {
              name: '计划数',
              type: 'bar',
              itemStyle: {
                normal: {
                  barBorderRadius: 30,
                  color: 'rgba(29,108,194,0.4)',
                },
              },
              barGap: '-100%',
              z: 0,
              barWidth: 20,
              data: [res.data.value[4]],
            },
          ]
        }

        this.actual_statistical = {
          tooltip: { show: false },
          legend: { show: false },
          polar: {
            radius: ['68%', '78%'],
            center: ['50%', '50%'],
          },
          angleAxis: { max: res.data.value[4], show: false },
          radiusAxis: {
            type: 'category',
            show: true,
            axisLabel: { show: false },
            axisLine: { show: false },
            axisTick: { show: false },
          },

          series: [
            {
              name: '业务完成率',
              type: 'bar',
              silent: true,
              roundCap: true,
              showBackground: true,
              backgroundStyle: { color: '#2b525b' },
              coordinateSystem: 'polar',
              itemStyle: { color: '#22f5ff' },
              data: [res.data.value[5]],
            },
            {
              type: 'pie',
              silent: true,
              color: '#2b525b',
              data: [1],
              label: {
                show: true,
                position: 'center',
                color: '#fff',
                fontSize:fontSize(20),
                formatter: `${res.data.value[5]}\n应收余额`,
              },
              radius: ['60%', '63%'],
              center: ['50%', '50%'],
            },
          ],
        }
      })

      let  key1, value1
      getXmjyqk(params).then(res => {
        if(res.code === 200 && res.data){
          key1 = res.data.key.splice(0, 2)
          value1 = res.data.value.splice(0, 2)
          this.yjjyqkStatistical = processing(key1, value1)
          this.yjjyqkData = res.data
          this.yjjyqkItem = res.data.item
        }else{
          this.yjjyqkStatistical = []
          this.yjjyqkData = []
          this.yjjyqkItem = {}
        }

        this.expect_business_data = {
          tooltip: {
            show: true,
            formatter: "{b}:{c}"
          },
          grid: {
            left: '5%',
            top: '5%',
            right: '8%',
            bottom: '8%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            show:false,
            position: 'top',
            axisTick: {
              show: false
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: '#fff',
              }
            },
            splitLine: {
              show: false
            },
          },
          yAxis: [{
            type: 'category',
            axisTick: {
              show: false,
              alignWithLabel: false,
              length: 5,

            },
            splitLine: { //网格线
              "show": false
            },
            inverse: 'true', //排序
            axisLine: {
              show: false,
              lineStyle: {
                color: '#fff',

              }
            },
            data: this.yjjyqkData.key
          }
          ],
          series: [{
            name: '能耗值',
            type: 'bar',
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{c}',
                textStyle: {
                  color: 'white', //color of value
                  fontSize:fontSize(16)
                }
              }
            },
            itemStyle: {
              normal: {
                show: true,
                color: function(params) {
                  let num = colorArray.length;
                  return {
                    type: 'linear',
                    colorStops: [{
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }, {
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }, {
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }, {
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }, {
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }, {
                      offset: 0,
                      color: colorArray[params.dataIndex % num].bottom
                    }, {
                      offset: 1,
                      color: colorArray[params.dataIndex % num].top
                    }]
                  }
                },
                barBorderRadius: 70,
                borderWidth: 0,
                borderColor: '#333',
              }
            },
            barGap: '0%',
            barCategoryGap: '50%',
            data: this.yjjyqkData.value
          }
          ]
        }
      })

      getSjTotal(params).then(res => {
        this.xmjyshTotal = res.data.xmTotal
      })
    },

    rowDetails(row, column,event){
      this.$router.push({name:'projectDetails',query:{xmid:row.xmid, xmqkJsztId:row.xmqkJsztId, xmqkJszt:row.xmqkXmlxId}})
    },
  },
  created() {

    this.getXmjsSz();

    this.getFgsSkqk();

    this.getXmlx();

    this.getLatest();

    this.getXjlData();

    this.getXjlEcharts();

    this.getRzqkData();

    getSubsidiaryData(8).then((res) => {
      if (res.code === 200 && res.data) {
        this.xjlOptions = res.data;
      }
    });

    getSubsidiaryData(50).then((res) => {
      if (res.code === 200 && res.data) {
        this.fgsOption = res.data;
      }
    });

    getSubsidiaryData(7).then((res) => {
      if (res.code === 200 && res.data) {
        this.projectStateOption = res.data;
      }
    });

    getSubsidiaryData(9).then((res) => {
      if (res.code === 200 && res.data) {
        this.xmjsState = res.data
      }
    });

    //分公司请求
    getSubsidiaryData(50).then(res => {
      this.unitSelect = res.data
    })

    //融资情况
    getSubsidiaryData(108).then(res => {
      this.xmrzRzqkOption = res.data
    })

  },
};
</script>

<style lang="scss" scoped>
@import "./index.scss";
.money {
  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
  height: 100%;
  padding: 80px 0 0 0;
  .money_header {
    height: 40%;
    margin-bottom: 1%;
    padding: 0 18px;
    display: flex;
    justify-content: space-between;
    .mouey_left {
      width: 25.3%;
      .mouey_title {
        height: 10%;
        display: flex;
        justify-content: space-between;
      }
      .chart-wrapper{
        height: 90%;
        .mouey_left_charts{
          height: 100%;
        }
      }
    }
    .mouey_moddle {
      width: 45%;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      .data_statistical {
        width: 80%;
        height: 4.25rem;
        margin-top: -3rem;
        display: flex;
        justify-content: space-around;
        align-items: center;
        .details {
          height: 100%;
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          div:first-child {
            span:first-child {
              text-align: center;
              color: #8ab8cd;
              font-size: 1.5rem;
              padding-right: 5px;
            }
          }
          div:last-child {
            span {
              text-align: center;
              font-size: 1rem;
              font-weight: bold;
            }
          }
        }
      }
      .project_statistical {
        width: 85%;
        height: 200px;
        .details_num {
          height: 48%;
          margin-bottom: 15px;
          display: flex;
          justify-content: space-around;
          &:first-child {
            .details:first-child {
             // margin-right: -192px;
            }
          }
          .details {
            width: fontSize(160);
            height: 70%;
            background: url("../../assets/screen/zj.png") no-repeat;
            background-size: 100% 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            div:first-child {
              span:first-child {
                font-size: fontSize(20);
                padding-right: fontSize(5);
              }
              span:last-child {
                font-size:  fontSize(10);
              }
            }
            div:last-child {
              span {
                text-align: center;
                font-size: fontSize(15);
              }
            }
          }
        }
      }
    }
    .mouey_right {
      width: 25.3%;
      .mouey_title {
        height: 10%;

        .option_btn {
          width: 63%;
        }
      }
      .chart-wrapper{
        height: 90%;
        .echarts{
          height: 100%;
        }
      }
    }
  }
  .money_main {
    height: 56%;
    padding: 0 18px;
    display: flex;
    justify-content: space-between;
    .left_right {
      width: 49.5%;
      height: 100%;
      padding: 15px;
    }
    .main_style {
      .mouey_title {
        height: 8%;
        .option_btn {
          width: 35%;
        }
        .tab {
          width: 20%;
          display: flex;
          justify-content: space-between;
          button {
            width: fontSize(80);
            height: fontSize(25);
            color: #c1cbcb;
            vertical-align: middle;
            line-height: 10px;
            border-radius: 0;
            font-size: fontSize(13);
          }
        }
        .tab-select {
          display: flex;
          justify-content: space-between;
        }
      }
      .number_statistical {
        height: 12%;
      }
      .chart-wrapper{
        height: 80%;
        .echarts{
          height: 100%;
        }
      }
    }
    .main_financing {
      .mouey_title{
        height: 8%;
      }
    }
  }
  .money_footer {
    height: fontSize(500);
    padding: 18px;
    background: rgba(4,15,54, 0.2);
    .chartts {
      height: 100%;
      padding: 18px;
      .mouey_title {
        height: 8%;
        .tab {
          margin-left: 20px;
          width: 20%;
          display: flex;
          justify-content: space-between;
          button {
            width: 80px;
            height: 25px;
            color: #c1cbcb;
            vertical-align: middle;
            line-height: 10px;
            border-radius: 0;
          }
        }
        .option_btn {
          width: 20%;
        }
      }
      .number_statistical {
        height: 15%;
        padding-left: fontSize(20);
        min-width: 50%;
        display: flex;
        flex-wrap: wrap;
        .item{
          margin-right: 10px;
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          span{
            display: inline-block;
            padding: 5px 0;
          }
        }
        .number_statistical_item {
          width: 50%;
          height: fontSize(30);
        }
      }
      .chart-wrapper{
        height: 77%;
        .echarts{
          height: 100%;
        }
      }
    }
  }

  .flex{
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    .time{
      margin: fontSize(5) 0;
    }
  }

  //选择器
  .option_btn {
    width: 55%;
    display: flex;
    justify-content: space-between;
    .time {

     ::v-deep .el-input--medium {
        width: vw(100);
        background: none;
        color: #c1cbcb;
        box-shadow: none;
        .el-input__inner {
          height: vh(25);
          background: none;
          border-color: #2183c4;
          line-height: 1.25rem;
          font-size: 0.75rem !important;
          border-radius: 0 !important;
          box-shadow: none;
          color: #cedee2;
        }
        .el-input__suffix {
          top: 2px;
        }
        .el-input__icon {
          font-size: 10px;
          line-height: inherit;
        }
        .el-input__suffix-inner {
          display: inline-block;
        }
      }

      .el-select {
        .el-input {
          .el-input__inner {
            color: #c1cbcb;
            height: vh(25);
            background: none;
            border-color: #2183c4;
            line-height: 1.25rem;
            font-size: 0.75rem !important;
            border-radius: 0 !important;
            box-shadow: none;
            padding-left: 10px;
          }
          .el-input__suffix {
            top: 2px;
          }
          .el-input__icon {
            line-height: inherit;
          }
          .el-input__suffix-inner {
            display: inline-block;
          }
        }
      }
    }
  }


  .dltjDialog{
    .dltjDialog_params{
      .pad-b{
        .el-input{ margin-bottom: vh(15) }
        .el-select{ margin-bottom: vh(15) }
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

    .business_payment_container{
     .payment_situation{
       .payment_title{
         width: 40%;
         .title{
           height: 20%;
           padding-left: fontSize(20);
           //text-align: center;
         }
        .money{
          height: 80%;
          display: flex;
          flex-direction: column;
          padding-left: fontSize(20);
          padding-top: 0;
        }
       }
     }
    }
  }

  /**电量统计弹框*/


  .el-tabs__item{
    color: #FFFFFF;
  }


  /**table*/
  ::v-deep .el-input__inner{
    &::placeholder{
      color: #a8a0a0;
    }
  }
  .active{
    border: none;
    box-shadow: 0 0 20px #2183c4 inset;
  }
  .focus{
    border: none;
    box-shadow: 0 0 20px #515557 inset;
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

.mouey_title {
  display: flex;
  justify-content: space-between;
}

::v-deep .el-tabs__item{
  color: #ffffff;
}

::v-deep .el-tabs__item.is-active{
  color: #1890ff;
}
</style>
