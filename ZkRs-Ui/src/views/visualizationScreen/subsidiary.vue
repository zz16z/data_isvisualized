<template>
  <!--总览模块-->
  <div class="subsidiary">
    <div class="selected_unit ">
      <div class="unit_select">
        <el-select v-model="unitValue" placeholder="请选择"  class="details_select" @change="subsidiarySwitch">
          <el-option
            v-for="item in unitSelect"
            :key="item.id"
            :label="item.zdName"
            :value="item.id">
          </el-option>
        </el-select>
      </div>
    </div>
    <div class="main project">
      <div class="project_left left side">
        <div class="project_type chilren">
          <div class="project_type_title">
            <p>项目分类</p>
          </div>
          <div class="project_type_details">
            <div class="content chilren" v-for="(item,index) in xmflData" :key="index">
              <div class="font-flex">
                <p>{{item.projectCount}}个</p>
                <p>{{item.projectName}}</p>

              </div>
              <div class="font-flex">
                <p>{{item.xmqkRlTtal}}MW</p>
                <P>容量总数</P>
              </div>
            </div>
          </div>
        </div>
        <div class="project_condition chilren">
          <div class="project_condition_title">
            <div class="project-btn">
              <div>
                <span style="cursor:pointer">项目现状</span>
              </div>
            </div>
            <span class="p_details" @click="queryDeteils(1)">查看详情</span>
          </div>
          <div class="project_condition_charts">
            <div class="chart-wrapper" v-if="ProjectSituation">
              <vue-e-charts autoresize :option="option_column"></vue-e-charts>
            </div>
          </div>
        </div>
        <div class="project_status chilren">
          <div class="project_condition_title">
            <span>项目建设情况</span>
            <span class="p_details" @click="queryDeteils(2)">查看详情</span>
          </div>
          <div class="project_condition_charts">
            <div class="chart-wrapper">
              <vue-e-charts autoresize :option="construction_state"></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
      <div class="project_middle middle ">
        <div class="middle_statistical ">
          <div class="data_statistical ">
            <div class="details">
              <div>
                <span>{{firstData.xmZs}}</span>
                <span>个</span>
              </div>
              <div><span>项目总数</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{firstData.xmRlzs}}</span>
                <span>MW</span>
              </div>
              <div><span>容量总数</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{firstData.xmYjcsze}}</span>
                <span>万元</span>
              </div>
              <div><span>预计出售总额</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{firstData.xmYjepcjszj}}</span>
                <span>万元</span>
              </div>
              <div><span>预计项目总成本</span></div>
            </div>
          </div>
          <div class="project_statistical">
            <div class="details">
              <div>
                <span>{{laseData.xmZs}}</span>
                <span>个</span>
              </div>
              <div><span>新项目总数</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{laseData.xmRlzs}}</span>
                <span>MW</span>
              </div>
              <div><span>容量总数</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{laseData.xmYjcsze}}</span>
                <span>万元</span>
              </div>
              <div><span>预计出售总额</span></div>
            </div>
            <div class="details">
              <div>
                <span>{{laseData.xmYjepcjszj}}</span>
                <span>万元</span>
              </div>
              <div><span>预计项目总成本</span></div>
            </div>
          </div>
        </div>
        <div class="middle_unit chilren">
          <div class="unit_header">
            <p>项目公司情况</p>
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


              <span class="p_details title_general" @click="projectParty">查看详情</span>
            </div>
          </div>
          <div class="unit_details">
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                ref="myCharts"
                autoresize
                :option="middleIemStiDdata.dltjLjfdl"
              ></vue-e-charts>
            </div>
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                autoresize
                :option="middleIemStiDdata.dltjYjfdl"
              ></vue-e-charts>
            </div>
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                autoresize
                :option="middleIemStiDdata.dltjSjjsdl"
              ></vue-e-charts>
            </div>
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                autoresize
                :option="middleIemStiDdata.dltjBysrhj"
              ></vue-e-charts>
            </div>
            <div class="chart-wrapper unit_chart">
              <vue-e-charts
                autoresize
                :option="middleIemStiDdata.dltjByzchj"
              ></vue-e-charts>
            </div>
          </div>
          <div class="uniot_projectTitle">
            <div class="project_title" v-for="(item, index) in arrTitle" :key="index">{{item}}</div>
          </div>
        </div>
        <div class="operating_condition chilren">
          <div class="title_details_btn">
            <span>项目经营数据</span>
            <div class="select_view">

              <el-date-picker
                style="width: 7rem"
                v-model="beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="开始时间"
                @change="selectCharts">
              </el-date-picker>
              <el-date-picker
                style="width: 7rem"
                v-model="endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="结束时间"
                @change="selectCharts">
              </el-date-picker>

              <span class="p_details" @click="projectBusiness">查看详情</span>
            </div>
          </div>
          <div class="title_details_content">
            <span class="text-color-size-16" style="color: #4db3e2; margin: 0 2rem" v-for="(item, index) in BusinessSitTitle" :key="index">{{ item.name }}：{{ item.value }}万元</span>
          </div>
          <div class="details_echats">
            <div class="chart-wrapper">
              <vue-e-charts autoresize :option="business_option"></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
      <div class="project_right side">
        <div class="right_condition chilren">
          <div class="condition_tab">
            <div class="tab">
              <button class="chilren title_btn" :class="defaultTab === 1 ? 'chilren_active' : 'chilren_focus'" @click="tab_money(1)">资金预警</button>
              <button class="chilren title_btn" :class="defaultTab === 2 ? 'chilren_active' : 'chilren_focus'" @click="tab_money(2)">融资预警</button>
              <button class="chilren title_btn" :class="defaultTab === 3 ? 'chilren_active' : 'chilren_focus'"  @click="tab_money(3)">工期预警</button>
              <button class="chilren title_btn" :class="defaultTab === 4 ? 'chilren_active' : 'chilren_focus'" @click="tab_money(4)">出售预警</button>
            </div>
          </div>

          <!--资金预警-->
          <div class="condition_list" ref="list" v-show="defaultTab === 1">
            <ul class="list_title list_public">
              <li>序号</li>
              <li>项目名称</li>
              <li>收入（万元）</li>
              <li>支出（万元）</li>
            </ul>
            <vue-seamless :class-option="classOption" :data="condition_list" class="seamless-warp" v-loading="condition_loading" element-loading-background="rgba(4,15,54, 1)" style="margin: 5px">
              <template>
                <ul
                  class="list_data list_public list_context"
                  v-for="(item, index) in condition_list"
                  :key="index + 'o'"
                  @click="rowClick(item)"
                >
                  <li>{{ index + 1 }}</li>
                  <li>{{ item.xmqkXmmc }}</li>
                  <li>{{ item.xjlsjqkSrHj }}</li>
                  <li>{{ item.xjlsjqkZcHj }}</li>
                </ul>
              </template>
            </vue-seamless>
          </div>
          <!--融资预警-->
          <div class="condition_list" ref="list" v-show="defaultTab === 2">
            <ul class="list_title list_public list_title">
              <li>序号</li>
              <li>项目名称</li>
              <li>还款日期</li>
              <li>还款金额</li>
            </ul>
            <vue-seamless :class-option="classOption" :data="condition_list" class="seamless-warp" v-loading="condition_loading" element-loading-background="rgba(4,15,54, 1)" style="margin: 5px">
              <template>
                <ul
                  class="list_data list_public list_context"
                  v-for="(item, index) in condition_list"
                  :key="index + 'k'"
                  @click="rowClick(item)"
                >
                  <li>{{ index + 1}}</li>
                  <li>{{ item.xmqkXmmc }}</li>
                  <li>{{ item.financingRq }}</li>
                  <li>{{ item.financingJe }}</li>
                </ul>
              </template>
            </vue-seamless>
          </div>
          <!--工期预警-->
          <div class="condition_list" ref="list" v-show="defaultTab === 3">
            <ul class="list_title list_public list_title">
              <li>序号</li>
              <li>项目名称</li>
              <li>施工进度</li>
              <li>应并网日期</li>
            </ul>
            <vue-seamless :class-option="classOption" :data="condition_list" class="seamless-warp" v-loading="condition_loading" element-loading-background="rgba(4,15,54, 1)" style="margin: 5px">
              <template>
                <ul
                  class="list_data list_public list_context"
                  v-for="(item, index) in condition_list"
                  :key="index + 'm'"
                  @click="rowClick(item)"
                >
                  <li>{{ index + 1}}</li>
                  <li>{{ item.xmqkXmmc }}</li>
                  <li>{{ item.xmqkSgjd }}</li>
                  <li>{{ item.xmqkBwsj }}</li>
                </ul>
              </template>
            </vue-seamless>
          </div>
          <!--出售预警-->
          <div class="condition_list" ref="list" v-show="defaultTab === 4">
            <ul class="list_title list_public list_title">
              <li>序号</li>
              <li>项目名称</li>
              <li>应出售日期</li>
            </ul>
            <vue-seamless :class-option="classOption" :data="condition_list" class="seamless-warp" v-loading="condition_loading" element-loading-background="rgba(4,15,54, 1)" style="margin: 5px">
              <template>
                <ul
                  class="list_data list_public list_context"
                  v-for="(item, index) in condition_list"
                  :key="index + 'p'"
                  @click="rowClick(item)"
                >
                  <li>{{ index + 1 }}</li>
                  <li>{{ item.xmqkXmmc }}</li>
                  <li>{{ item.xmqkYcsrq }}</li>
                </ul>
              </template>
            </vue-seamless>
          </div>

          <div class="condition_details">
            <span class="p_details" @click="zjyjClick">查看详情</span>
          </div>
        </div>
        <div class="project_right_status chilren">
          <div class="project_condition_title">
            <span>现金流情况</span>
            <div class="select_view">
              <el-date-picker
                style="width: 7rem"
                @change="xjlCharts"
                v-model="xjl.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="开始时间">
              </el-date-picker>
              <el-date-picker
                style="width: 7rem"
                @change="xjlCharts"
                v-model="xjl.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="结束时间">
              </el-date-picker>

            </div>
          </div>
          <div class="project_condition_charts">
            <div class="chart-wrapper">
              <vue-e-charts autoresize :option="subsidiaryEcharts" style="height: 23vh"></vue-e-charts>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--资金预警/融资预警/工期预警/出售预警-->
    <el-dialog
      title="资金预警"
      :visible.sync="zjyjVisible"
      width="70%">
      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="zjyjList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails">
          <el-table-column
            prop="xmqkXmmc"
            label="项目名称"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkRl"
            label="容量（MW）"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmlx"
            label="项目分类"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmxz"
            label="项目现状"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkJszt"
            label="建设状态"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xjlsjqkSrHj"
            label="实际收入"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xjlsjqkZcHj"
            label="实际支出"
            align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="zjyjSizeChange"
            @current-change="zjyjCurrentChange"
            :current-page="zjyjPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="zjyjTotal">
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--融资预警-->
    <el-dialog
      title="融资预警"
      :visible.sync="rzyjVisible"
      width="70%">
      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="rzyjList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails">
          <el-table-column
            prop="xmqkXmmc"
            label="项目名称"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkRl"
            label="容量（MW）"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmlx"
            label="项目分类"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmxz"
            label="项目现状"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkJszt"
            label="建设状态"
            align="center">
          </el-table-column>
          <el-table-column
            prop="financingRq"
            label="还款日期"
            align="center">
          </el-table-column>
          <el-table-column
            prop="financingJe"
            label="还款金额"
            align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="rzyjSizeChange"
            @current-change="rzyjCurrentChange"
            :current-page="rzyjPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="rzyjTotal">
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--工期预警-->
    <el-dialog
      title="工期预警"
      :visible.sync="gqyjVisible"
      width="70%">
      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="gqyjList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails">
          <!-- <el-table-column
            prop="sort"
            label="序号"
            align="center">
          </el-table-column> -->
          <el-table-column
            prop="xmqkXmmc"
            label="项目名称"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkRl"
            label="容量（MW）"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmlx"
            label="项目分类"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmxz"
            label="项目现状"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkJszt"
            label="建设状态"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkSgjd"
            label="施工进度"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkBwsj"
            label="应并网日期"
            align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="gqyjSizeChange"
            @current-change="gqyjleCurrentChange"
            :current-page="gqyjPageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="gqyjTotal">
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--出售预警-->
    <el-dialog
      title="出售预警"
      :visible.sync="csyjVisible"
      width="70%">
      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="csyjList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails">
          <el-table-column
            prop="xmqkXmmc"
            label="项目名称"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkRl"
            label="容量（MW）"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmlx"
            label="项目分类"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmxz"
            label="项目现状"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkJszt"
            label="建设状态"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkYcsrq"
            label="应出售日期"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkYjyy"
            label="预警原因"
            align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="csyjSizeChange"
            @current-change="csyjCurrentChange"
            :current-page="csyjCurPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="csyjTotal">
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--项目建设/项目现状，详情组件-->
    <el-dialog
      :title="leftDetailsTile"
      :visible.sync="isOpenDetails"
      :before-close='xmxzClose'
      width="80%"
      class="detalis">
      <!--搜索-->
      <div class="pad-b">

        <el-date-picker
          v-model="xmqk.beginTime"
          type="month"
          value-format="yyyy-MM"
          placeholder="选择开始时间">
        </el-date-picker>
        <el-date-picker
          v-model="xmqk.endTime"
          value-format="yyyy-MM"
          type="month"
          placeholder="选择结束时间">
        </el-date-picker>

        <el-select v-model="xmqk.projectStatus" clearable placeholder="请选择项目现状">
          <el-option
            v-for="item in xmzt"
            :key="item.id"
            :label="item.zdName"
            :value="item.id">
          </el-option>
        </el-select>
        <el-select
          v-model="xmqk.xmFag"
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
        <el-select
          v-model="xmqk.xmrzRzqk"
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
        <el-select
          v-model="xmqk.xmqkJszt"
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
        <el-button @click=xmqkQuery>搜索</el-button>
      </div>

      <div style="overflow: hidden" class="dialog_ul">
        <el-table
          header-align="ceneter"
          :data="xmxzDataList"
          style="width: 100%"
          :row-class-name="tableClassName"
          @cell-dblclick="rowDetails">
          <!-- <el-table-column
            prop="sort"
            label="序号"
            align="center">
          </el-table-column> -->
          <el-table-column
            prop="xmqkXmmc"
            label="项目名称"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkRl"
            label="容量（MW）"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmlx"
            label="项目分类"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkXmxz"
            label="项目现状"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkJszt"
            label="建设状态"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkLyfgs"
            label="履约分公司"
            align="center">
          </el-table-column>
          <el-table-column
            prop="xmqkHtsj"
            label="动工时间"
            align="center">
          </el-table-column>
        </el-table>

        <div class="paging">
          <el-pagination
            @size-change="xmxzSizeChange"
            @current-change="xmxzCurrentChange"
            :current-page="projectStctiaclPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pageSizeXmqk"
            layout="total, sizes, prev, pager, next, jumper"
            :total="xmqkTatol">
          </el-pagination>
        </div>
      </div>
    </el-dialog>

    <!--项目公司情况/发电-->
    <el-dialog
      :visible.sync="unitElectricity"
      :before-close="handleClose"
      width="80%">

      <el-tabs v-model="activeName" @tab-click="handleXmgsClick">
        <el-tab-pane label="发电详情" name="fdxq">
          <div class="dltjDialog" style="border-top: none">
            <div class="dltjDialog_params">
              <div class="left option_btn">
                <div class="year time">
                  <el-date-picker
                    v-model="yjqk.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="选择开始时间">
                  </el-date-picker>
                </div>
                <div class="year time">
                  <el-date-picker
                    v-model="yjqk.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="选择结束时间">
                  </el-date-picker>
                </div>

                <div class="month time">
                  <el-select v-model="yjqk.xmqkXmlx" clearable placeholder="选择项目分类">
                    <el-option
                      v-for="item in xjlOptions"
                      :key="item.id"
                      :label="item.zdName"
                      :value="item.id"
                    >
                    </el-option>
                  </el-select>
                </div>
                <div class="month time">
                  <el-select
                    style="width: 100%"
                    v-model="yjqk.xmFag"
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
                <div class="month time">
                  <el-select style="width: 100%" v-model="yjqk.xmqkXmxz" clearable placeholder="请选择项目现状">
                    <el-option
                      v-for="item in projectStateOption"
                      :key="item.id"
                      :label="item.zdName"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </div>
                <div class="month time">
                  <el-select style="width: 100%" v-model="yjqk.xmqkLyfgs" clearable placeholder="请选择履约分公司">
                    <el-option
                      v-for="item in unitSelect"
                      :key="item.id"
                      :label="item.zdName"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </div>
                <button class="chilren" :class="dltjDefaultTab === 2 ? 'chilren_active' : 'chilren_focus'"  @click="dltjDialogQuery">搜索</button>
              </div>
              <div class="right">
                <span>电量单位：kWh </span><span>电费单位：万元</span>
              </div>
            </div>
            <div class="dltjDialog_title">
              <span>{{yjqk.year }}<span v-show="yjqk.month && yjqk.year"> - </span>{{yjqk.month}} 发电数据情况</span>
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
        </el-tab-pane>
        <el-tab-pane label="项目公司" name="xmgs">
          <div class="dltjDialog" style="border-top: none">
            <div class="dltjDialog_params">
              <div class="left option_btn">
                <div class="year time">
                  <el-date-picker
                    v-model="xmgsParams.beginTime"
                    type="month"
                    value-format="yyyy-MM"
                    placeholder="选择开始时间">
                  </el-date-picker>
                </div>
                <div class="year time">
                  <el-date-picker
                    v-model="xmgsParams.endTime"
                    value-format="yyyy-MM"
                    type="month"
                    placeholder="选择结束时间">
                  </el-date-picker>
                </div>

                <div class="month time">
                  <el-select
                    v-model="xmgsParams.xmqkXmlx"
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
                <div class="month time">
                  <el-input v-model="xmgsParams.xmqkQy" placeholder="请输入区域" clearable></el-input>
                </div>

                <div class="month time">
                  <el-input v-model="xmgsParams.xmqkXmmc" placeholder="请输入项目名称" clearable></el-input>
                </div>
                <div class="month time">
                  <el-select
                    style="width: 100%"
                    v-model="xmgsParams.xmFag"
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
                <div class="month time">
                  <el-select style="width: 100%" v-model="xmgsParams.xmqkXmxz" clearable placeholder="请选择项目现状">
                    <el-option
                      v-for="item in projectStateOption"
                      :key="item.id"
                      :label="item.zdName"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </div>
                <button class="chilren" :class="dltjDefaultTab === 2 ? 'chilren_active' : 'chilren_focus'"  @click="getXmgsDataList">搜索</button>
              </div>
            </div>
          </div>

          <el-table
            header-align="ceneter"
            :data="xmgsTable"
            style="width: 100%"
            @cell-dblclick="rowDetails"
            :row-class-name="tableClassName">
            <el-table-column
              prop="xmqkXmmc"
              label="项目名称"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkRl"
              label="容量(MW)"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkXmlx"
              label="项目分类"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkLyfgs"
              label="履约分公司"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkQy"
              label="区域"
              align="center">
            </el-table-column>
            <el-table-column
              prop="dltjLjjsdl"
              label="累计发电量"
              align="center">
            </el-table-column>
            <el-table-column
              prop="dltjYjfdl"
              label="预计发电量"
              align="center">
            </el-table-column>
            <el-table-column
              prop="dltjSjfdl"
              label="实际发电量"
              align="center">
            </el-table-column>
            <el-table-column
              prop="dltjBysrhj"
              label="电费收入"
              align="center">
            </el-table-column>
            <el-table-column
              prop="dltjByzchj"
              label="电费支出"
              align="center">
            </el-table-column>
          </el-table>

          <div class="paging">
            <el-pagination
              @size-change="xmgsSizeChange"
              @current-change="xmgsCurrentChange"
              :current-page="xmgsParams.pageNum"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="xmgsParams.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="xmgsTotal">
            </el-pagination>
          </div>

        </el-tab-pane>
      </el-tabs>

    </el-dialog>

    <!--项目经营数据-->
    <el-dialog
      :visible.sync="projectBusinessStatus"
      width="85%">
      <div class="dltjDialog" style="border-top: none">
        <div class="dltjDialog_params">
          <div class="left option_btn">

            <div class="year time">
              <el-date-picker
                v-model="xmjyqk.beginTime"
                type="month"
                value-format="yyyy-MM"
                placeholder="选择开始时间">
              </el-date-picker>
            </div>
            <div class="year time">
              <el-date-picker
                v-model="xmjyqk.endTime"
                value-format="yyyy-MM"
                type="month"
                placeholder="选择结束时间">
              </el-date-picker>
            </div>


            <div class="month time">
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
            </div>
            <div class="month time">
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
            </div>
            <div class="month time">
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
            </div>
            <div class="month time">
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
            </div>
            <div class="month time">
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
            </div>
            <div class="month time">
              <el-input v-model="xmjyqk.xmqkQy" placeholder="请输入区域" clearable></el-input>
            </div>

            <div class="month time">
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
            </div>
            <div class="month time">
              <el-input v-model="xmjyqk.xmqkRzf" placeholder="请输入融资方" clearable></el-input>
            </div>
            <div class="month time">
              <button style="height: 100%; margin: 0" class="chilren" :class="dltjDefaultTab === 2 ? 'chilren_active' : 'chilren_focus'"  @click="xmjyDataQuery">搜索</button>
            </div>
          </div>
        </div>
      </div>
      <el-tabs v-model="projectBusinessName" @tab-click="handleXmjysjClick">
        <el-tab-pane label="项目经营数据" name="xmjysj">
          <div class="dltjDialog">

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
                      <span class="title_general" style="margin-right: 10px">{{item.name}}</span>
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
                  <div class="title"> <p class="h3_title">实际付款情况</p></div>
                  <div class="money">
                    <div class="money_num">
                      <span class="title_general pad-right">应付账款总额</span>
                      <span  class="h3_title">{{sjfkStatistical.xjlsjqkXmyfzkzje}}</span>
                    </div>
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
                    <div style=" padding: 0">
                      <span class="title_general">{{actualStatisticalTitle1.name}}</span>
                      <span  class="h3_title">{{actualStatisticalTitle1.value}}</span>
                    </div>
                    <div style="padding: 0">
                      <span class="title_general">{{actualStatisticalTitle.name}}</span>
                      <span  class="h3_title">{{actualStatisticalTitle.value}}</span>
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
        </el-tab-pane>
        <el-tab-pane label="项目清单" name="xmqd">


          <el-table
            header-align="ceneter"
            :data="tableData"
            style="width: 100%"
            :row-class-name="tableClassName"
            @cell-dblclick="rowDetails"
            element-loading-background="rgba(4,15,54, 1)">
            <el-table-column
              prop="xmqkXmmc"
              label="项目名称"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjcszj"
              label="预计出售总价"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjepcjsj"
              label="预计EPC结算"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjlr"
              label="预计EPC利润"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjxmgscb"
              label="预计项目公司成本 "
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjecpcb"
              label="预计EPC成本"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlyjqkYjxmcb"
              label="预计总成本"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkXmyfzkzje"
              label="应付账款总额"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkZcHj"
              label="已付总金额"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkYfzkye"
              label="应付账款余额"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkYfwsk"
              label="实际应付未付"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkYslybzj"
              label="应收履约保证金"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkSk"
              label="预收购款"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkRzk"
              label="融资款"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkGck"
              label="工程款"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xjlsjqkDfsk"
              label="已收电费"
              align="center">
            </el-table-column>
          </el-table>

          <div class="paging">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-size="xmjyqk.pageSize"
              layout="total, prev, pager, next, jumper"
              :total="total">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>

    </el-dialog>

  </div>
</template>

<script>
  import countTo from 'vue-count-to'
  import {
    fgsXjlqk,
    getConstructionSituation, getFdqkObject, getFdqkSjsr, getFdqkSjzc,
    getLatestData, getPagingProjectCsyj, getProjectBusinessSit, getProjectCsyj, getProjectGqyj,
    getProjectSituation, getProjectUnitSituation, getProjectZjyj, getProjectZjyjList, getSjfkqk, getSjskqk, getSjTotal,
    getSubsidiaryData,
    getText, getXmgsList, getXmjyqk, getXzAndJsqkDetalis, projectRzyj, xmglQuery, xmqdQuery
  } from "@/api/screen/zl";
  import {processing, projectStatusCharts} from "@/views/visualizationScreen/pulic";
  import {fontSize} from "@/views/visualizationScreen/automatic/adapter";
  import vueSeamless from 'vue-seamless-scroll'

  var colorArray = [
    {
      top: '#4f9aff', //深蓝
      bottom: 'rgba(11,42,84,.3)'
    }
  ];
  var salvProName =["全井","在建","未开工"];
  var salvProValue =[239,181,154];
  var salvProMax =[];//背景按最大值
  for (let i = 0; i < salvProValue.length; i++) {
    salvProMax.push(salvProValue[0])
  }
  var echarts = require("echarts");
  export default {
    name: "overview",
    components:{
      countTo,
      vueSeamless
    },
    computed:{
      numberFormat : () => {
        return function (val){
          let num = 10000;
          var sizesValue = ''
          var sizes;
          if(val > 0){
            /**
             * 判断去那个单位
             * */

            if(val < 10000){
              sizesValue = ''
            }else if(val > 1000 && val < 9999){
              sizesValue = '千'
            }else if(val > 10000 && val < 99999999){
              sizesValue = '万'
            }else if(val > 100000000){
              sizesValue = '亿'
            }


            /**
             * 大于一万则
             * */
            let i = Math.floor(Math.log(val) / Math.log(num))
            /**
             * toFixed(2)
             * */
            sizes = ((val / Math.pow(num, i))).toFixed(2)
            sizes = sizes + sizesValue
          }else{
            sizes = 0
          }
          return sizes
        }
      },
      classOption(){
        return {
          step: 0.1
        }
      },
    },
    data(){
      return {
        monthOptions:[
          {
            value:'1',
            label:'1月'
          },
          {
            value:'2',
            label:'2月'
          },
          {
            value:'3',
            label:'3月'
          },
          {
            value:'4',
            label:'4月'
          },
          {
            value:'5',
            label:'5月'
          },
          {
            value:'6',
            label:'6月'
          },
          {
            value:'7',
            label:'7月'
          },
          {
            value:'8',
            label:'8月'
          },{
            value:'9',
            label:'9月'
          },{
            value:'10',
            label:'10月'
          },{
            value:'11',
            label:'11月'
          },{
            value:'12',
            label:'12月'
          }
        ],

        startVal: 0,
        h:'200px',
        defaultTab:1,
        //项目现状
        option_column:{},
        ProjectSituation:false,

        construction_state:{},

        condition_list:[],
        condition_loading:false,

        subsidiaryEcharts:{},

        value:'',
        beginTime:'',
        endTime:'',
        businessData:[
          {
          value: '选项1',
          label: '选项1'
        }, {
          value: '选项2',
          label: '选项2'
        }, {
          value: '选项3',
          label: '选项3'
        }, {
          value: '选项4',
          label: '选项4'
        }, {
          value: '选项5',
          label: '选项5'
        }],
        unitValue:'',
        unitSelect:[],

        business_option:{},

        //项目分类数据
        xmflData:[],
        //羡慕现状数据
        xmxzEcharts:{},
        //中间第一行
        firstData:{
          xmZs:0,
          xmRlzs:0,
          xmYjcsze:0,
          xmYjepcjszj:0
        },
        //中间第二行
        laseData:{
          xmZs:0,
          xmRlzs:0,
          xmYjcsze:0,
          xmYjepcjszj:0
        },
        //中间第三行项目公司情况
        middleIemStiDdata:{
          dltjLjfdl:{},
          dltjYjfdl:{},
          dltjSjjsdl:{},
          dltjBysrhj:{},
          dltjByzchj:{}
        },
        dltjLjfdl:0,
        dltjYjfdl:0,
        dltjSjjsdl:0,
        dltjBysrhj:0,
        dltjByzchj:0,
        //动画
        angle:0,
        angleNum:14551,
        timerId:undefined,

        //现金流时间选择器、
        xjlValue:'',
        xjl:{
          beginTime:'',
          endTime:''
        },
        BusinessSitTitle:[],


        //资金预警详情分页
        zjyjTotal:0,
        zjyjPageNum:1,
        zjyjPageSize:10,
        zjyjList:[],
        zjyjVisible:false,

        //融资预警详情分页
        rzyjTotal:0,
        rzyjPageNum:1,
        rzyjPageSize:10,
        rzyjList:[],
        rzyjVisible:false,

        //工期预警详情分页
        gqyjTotal:0,
        gqyjPageNum:1,
        gqyjPageSize:10,
        gqyjList:[],
        gqyjVisible:false,

        //出售预警详情分页
        csyjTotal:0,
        csyjCurPage:1,
        csyjPageSize:10,
        csyjList:[],
        csyjVisible:false,

        //项目现状，项目建设情况
        leftDetailsTile:'',
        isOpenDetails:false,
        //搜索条件
        xmqk:{
          beginTime:'',
          endTime:'',
          projectStatus:'',
          xmFag:'',
          xmrzRzqk:'',
          xmqkJszt:''
        },
        xmFagOptions:[{name:'老项目',id:0},{name:'新项目',id:1}],
        xmzt:[],
        projectStctiaclPage:1,
        xmqkTatol:0,
        //项目现状and项目建设情况请求参数
        xmqkAndXmztParams:{
          isFull:1,
          pageNum:1,
          pageSize:10,
          corporation:'',
          jszt:'',
          xmxz:'',
          yearValue:'',
          monthValue:'',
        },
        pageNumXmqk:1,
        pageSizeXmqk:10,
        xmxzDataList:[],

        //项目公司情况
        //电量统计详情弹框内容
        dltjDialogStatistcal:[],
        dltjIncomeCharts:{},
        realIncome:'',
        realSpending:'',
        yjqk:{
          beginTime:'',
          endTime:'',
          xmqkXmlx:''
        },
        xjlOptions:[],

        //项目公司
        activeName:'fdxq',
        xmgsTable:[],
        xmgsParams:{
          isFull:'1',
          beginTime:'',
          endTime: '',
          pageNum:1,
          pageSize:10,
          xmqkLyfgs:'',
          xmqkQy:'',
          xmqkXmlx:'',
          xmqkXmmc:'',
        },
        xmgsTotal:0,

        //经营项目数据
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
        dltjDefaultTab:'',
        dltjSpendingCharts:{},
        unitElectricity:false,


        params:{
          //aggregation:10,
          isFull:1,
          pageNum:1,
          pageSize:8,
          xmqkHzf:'',
          xmqkJszt:'',
          xmqkLyfgs:'',
          xmqkRl:'',
          xmqkRzf:'',
          xmqkSgf:'',
          xmqkXmlx:'',
          xmqkXmmc:'',
          xmqkXmxz:'',
          aggregation:'',
          //新增
          xmqkHtsjYear: '',
          xmqkHtsjMonth:'',
          xmqkQy:'',

        },
        total:0,
        currentPage4: 1,
        projectBusinessName:'xmjysj',
        tableData:[],
        //项目经营数据情况详情
        projectBusinessStatus:false,
        expect_business_data:{},
        payment_business_data:{},
        actual_statistical:{},
        actualStatisticalTitle:{},
        actualStatisticalTitle1:{},
        xmjyqk:{
          yearValue:'',
          monthValue:'',
          xmqkXmxz:'',//项目现状
          xmqkJszt:'',//建设状态
          xmqkXmlx:'',//项目分类（光伏、光电）
          corporation: '',//分公司
          xmFag:'',//项目标记：0老项目、1新项目
          xmqkQy:'',//区域
          xmrzRzqk:'',//融资情况
          xmqkRzf:'',//融资方
          isFull: 1,
          pageNum: 1,
          pageSize: 8,
        },


        xmrzRzqkOption: [],
        //项目现状
        projectStateOption:[],
        //建设状态
        xmjsState:[],

        various_chartsData:{},
        various_chartsData1:{},
        various_chartsData2:{},
        various_chartsData3:{},
        //项目经营数据
        yjjyqkData:[],
        yjjyqkItem:{},
        yjjyqkStatistical:[],

        //实际付款情况
        sjfkqkData:[],
        sjfkStatistical:{},

        //实际收入情况
        sjsrqkData:[],
        sjsrhjStatistical:{},

        xmjyshTotal:0,

        arrTitle:['累计发电量', '预计发电量', '实际发电量', '电费收入', '电费支出'],


        //后期优化
        //项目公司情况增加年月查询条件
        fgs:{
          beginTime:'',
          endTime:'',
        },
      }
    },

    methods:{
      handleXmjysjClick(){
        switch (this.projectBusinessName){
          case 'xmjysj':
            this.reset()
            this.xmjyDataQuery()
            break
          case 'xmqd':
            this.reset()
            this.xmjyqk.corporation = this.unitValue,
            this.initXmqd(this.xmjyqk)
            break
        }
      },

      reset(){
        this.xmjyqk = {
          beginTime:'',
          entTime:'',
          xmqkXmxz:'',//项目现状
          xmqkJszt:'',//建设状态
          xmqkXmlx:'',//项目分类（光伏、光电）
          corporation:'',//分公司
          xmFag:'',//项目标记：0老项目、1新项目
          xmqkQy:'',//区域
          xmrzRzqk:'',//融资情况
          xmqkRzf:'',//融资方
          isFull: 1,
          pageNum: 1,
          pageSize: 8,
        }
      },


      initXmqd(params){
        this.loading = true
        xmqdQuery(params).then(res => {
          if(res.code === 200 && res.data){
            this.tableData = res.data.rsList
            res.data.itme.xmqkXmmc = '总计'
            this.tableData.unshift(res.data.itme)
            this.total = res.data.total
          }else{
            this.tableData = []
            this.total = 0
          }
          this.loading = false
        })
      },

      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.xmjyqk.pageSize = val
        this.initXmqd(this.xmjyqk)
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.xmjyqk.pageNum = val
        this.initXmqd(this.xmjyqk)

      },


      handleXmgsClick(){
        switch (this.activeName){
          case 'fdxq':
            let params = {
              monthValue:'',
              xmid:'',
              corporation:this.unitValue,
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
        this.xmgsParams.xmqkLyfgs = this.unitValue
        getXmgsList(this.xmgsParams).then(res => {
          if(res.data){
            this.xmgsTable = res.data.rsList
            res.data.itme.xmqkXmmc = '总计'
            this.xmgsTable.unshift(res.data.itme)
            this.xmgsTotal = res.data.total
          }else{
            this.xmgsTable = []
            this.xmgsTable.unshift({})
            this.xmgsTotal = 0
          }
        })
      },

      tab_money(id) {
        this.condition_loading = true
        this.defaultTab = id;
        switch (id) {
          case 1:
            getProjectZjyj({corporation:this.unitValue}).then(res => {
              if(res.code === 200 && res.data){
                this.condition_list = res.data
              }else{
                this.condition_list = []
              }
              this.condition_loading = false
            })
            break;
          case 2:
            projectRzyj({corporation:this.unitValue, isFull:0}).then(res => {
              if(res.code === 200 && res.data){
                this.condition_list = res.data.rsList
              }else{
                this.condition_list = []
              }
              this.condition_loading = false
            })
            break;
          case 3:
            getProjectGqyj({corporation:this.unitValue,isFull:0}).then(res => {
              if(res.code === 200 && res.data){
                this.condition_list = res.data.rsList
              }else{
                this.condition_list = []
              }
              this.condition_loading = false
            })
            break;
          case 4:
            getProjectCsyj({corporation:this.unitValue,isFull:0}).then(res => {
              if(res.code === 200 && res.data){
                this.condition_list = res.data.rsList
              }else{
                this.condition_list = []
              }
              this.condition_loading = false
            })
            break;
        }
      },

      selectCharts(){
        let params
        params = {beginTime:this.beginTime, endTime:this.endTime, corporation:this.unitValue}
        getProjectBusinessSit(params).then(res => {
          let title1,data1,data2, titleKey, titleVal
          if(res.code === 200 && res.data){
            titleKey = res.data.content.key.splice(res.data.content.key.length - 5, 2)
            titleVal = res.data.content.value.splice(res.data.content.value.length - 5, 2)
            this.BusinessSitTitle = processing(titleKey, titleVal)
            title1 = [
              [res.data.content.key[0], res.data.content.key[1]],
              [res.data.content.key[2], res.data.content.key[3]],
              [res.data.content.key[4], res.data.content.key[5]]
            ]
            data1 = [Number(res.data.content.value[0]), Number(res.data.content.value[2]), Number(res.data.content.value[4])]
            data2 = [{value:Number(res.data.content.value[1]),results:Number(res.data.contentSbu.value[0])},{value:Number(res.data.content.value[3]),results:Number(res.data.contentSbu.value[1])},{value:Number(res.data.content.value[5]),results:Number(res.data.contentSbu.value[2])}]

          }else{
            title1 = []
            data1 = []
            data2 = []
            this.BusinessSitTitle = []
          }
          this.business_option = {
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
                var str = `${title}:${params[1].data.results}万元<br/>`;
                str += `${params[0].name.split(",")[0]}:${
                  params[0].value
                }万元<br/>`;
                str += `${params[1].name.split(",")[1]}:${params[1].value}万元`;
                return str;
              },
            },
            grid: {
              top: "5%",
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
                    color: '#c0c0c0',
                  }
                },
                axisLabel:{
                  interval:0,
                  fontSize:fontSize(15)
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
              axisLabel:{
                fontSize:fontSize(15)
              },
            },
            series: [
              {
                name: "Forest",
                type: "bar",
                barWidth: 60,
                data: data1,
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
                barWidth: 60,
                barGap:'50%',
                data: data2,
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
          }
        })
      },

      /**分公司切换*/
      subsidiarySwitch(){
        this.allData()
        this.xmfl()
        this.xmxz()
        this.xmjsqk()
        this.selectCharts()
        this.getItemSTI()
        this.getFgsXjl()
        this.tab_money(1)
      },

      //项目现状
      xmfl(){
        getText({corporation:this.unitValue}).then(res => {
          if(res.code === 200){
            this.xmflData = res.data
          }
        })
      },

      //项目现状
      xmxz(){
        getProjectSituation({corporation:this.unitValue}).then(res => {
          let key1, newList, oldList, echartsData
          if(res.code === 200 && res.data){
            key1 = res.data.key[0].map(item => {
              return item.substring(1)
            })
            newList = res.data.value[0]
            oldList = res.data.value[1]
            this.ProjectSituation = res.data.value[0].concat(res.data.value[1]).some(item => {
              return item > 0
            })
            echartsData = projectStatusCharts(key1, newList, oldList)
          }else{
            echartsData = []
            this.ProjectSituation = false
          }
          let temporaryProcessing = []
          echartsData.forEach((item) => {
            if(item.value > 0 ){
              temporaryProcessing.push(item)
            }
          })
          echartsData = temporaryProcessing

          this.option_column = {
            color:['#2DC191', '#00E1EF', '#F26C50', '#D1E2FE', '#047AD4'],
            tooltip: {
              trigger: 'item',
              formatter: (item) => {
                return '<span>'+ item.name+ '</span>' + '<br/>'+ '<span>新项目：' + item.data.newData + '，老项目：' + item.data.oldData + '</span>'
              }
            },
            series: [
              {
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                roseType: 'area',
                itemStyle: {
                  borderColor: '#040e2f',
                  borderWidth: 5
                },
                label: {
                  show: true,
                  color: '#fff',
                  fontSize:fontSize(16)
                },
                emphasis: {
                  label: {
                    show: true,
                    fontWeight: 'bold',
                    color:'#fff'
                  }
                },
                labelLine: {
                  show: true
                },
                data: echartsData
              }
            ]

          }
        })

      },

      //项目建设情况
      xmjsqk(){
        getConstructionSituation({corporation:this.unitValue}).then(res => {
          let max = []
          for (let i=0; i<res.data.value.length;i++){
            max.push(res.data.value.reduce((val, n) => {return Number(val) + Number(n)}))
          }
          this.construction_state = {
            grid: {
              left: "2%",
              right: "2%",
              bottom: "5%",
              top: "10%",
              containLabel: true,
            },
            tooltip: {
              trigger: "axis",
              axisPointer: {
                type: "none",
              },
              formatter: function (params) {
                return params[0].name + " : " + params[0].value;
              },
            },
            xAxis: {
              show: false,
              type: "value",
            },
            yAxis: [
              {
                type: "category",
                inverse: true,
                axisLabel: {
                  show: true,
                  textStyle: {
                    color: "#fff",
                    fontSize:fontSize(15)
                  },
                },
                splitLine: {
                  show: false,
                },
                axisTick: {
                  show: false,
                },
                axisLine: {
                  show: false,
                },
                data: res.data.key,
              },
              {
                type: "category",
                inverse: true,
                axisTick: "none",
                axisLine: "none",
                show: true,
                axisLabel: {
                  textStyle: {
                    color: "#ffffff",
                    fontSize:fontSize(15)
                  },
                },
                data: res.data.value,
              },
            ],
            series: [
              {
                name: "值",
                type: "bar",
                zlevel: 1,
                itemStyle: {
                  normal: {
                    barBorderRadius: 30,
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                      {
                        offset: 0,
                        color: "rgb(57,89,255,1)",
                        fontSize:fontSize(15)
                      },
                      {
                        offset: 1,
                        color: "rgb(46,200,207,1)",
                        fontSize:fontSize(15)
                      },
                    ]),
                  },
                },
                barWidth: fontSize(20),
                data: res.data.value,
              },
              {
                name: "背景",
                type: "bar",
                barWidth: fontSize(20),
                barGap: "-100%",
                data: max,
                itemStyle: {
                  normal: {
                    color: "rgba(24,31,68,1)",
                    barBorderRadius: 30,
                  },
                },
              },
            ],
          }
        })
      },

      //上面总览两个统计数据
      allData(){
        //第1行
        getLatestData({corporation:this.unitValue, isDq:1}).then(res => {

          if(res.code === 200){
            for(let i in res.data){
              res.data[i] = Number(res.data[i])
            }
            this.firstData = res.data
          }else{
            this.firstData = {
              xmZs:0,
              xmRlzs:0,
              xmYjcsze:0,
              xmYjepcjszj:0
            }
          }
        })

        //第2行
        getLatestData({corporation:this.unitValue, isDq:0}).then(res => {
          if(res.code === 200 && res.data){
            for(let i in res.data){
              res.data[i] = Number(res.data[i])
            }
            this.laseData = res.data
          }else{
            this.laseData = {
              xmZs:0,
                xmRlzs:0,
                xmYjcsze:0,
                xmYjepcjszj:0
            }
          }
        })
      },

      //分公司收款情况年份查询
      fgsYearQuery() {
        this.getItemSTI();
      },

      //分公司收款情况按月份查询
      fgsMonthQuery() {
        this.getItemSTI();
      },

      //项目公司情况
      getItemSTI(){
        let { beginTime, endTime} = this.fgs
        let params = {
          corporation:this.unitValue,
          beginTime,
          endTime
        }
        getProjectUnitSituation(params).then(res => {
          if(res.code === 200 && res.data){
            this.dltjLjfdl = res.data.dltjLjfdl
            this.dltjYjfdl = res.data.dltjYjfdl
            this.dltjSjjsdl = res.data.dltjSjfdl
            this.dltjBysrhj = res.data.dltjBysrhj
            this.dltjByzchj = res.data.dltjByzchj
            console.log(this.dltjLjfdl, res.data.dltjLjfdl, 'echarts累计发电连')
          }else{
            this.dltjLjfdl = 0
            this.dltjYjfdl = 0
            this.dltjSjjsdl = 0
            this.dltjBysrhj = 0
            this.dltjByzchj = 0
          }
        })
      },


      //项目公司情况查看详情
      projectParty(){
        this.unitElectricity = true
        let params = {
          monthValue:'',
          xmid:'',
          corporation:this.unitValue,
          yearValue:''
        }
        this.initElectricityData(params)
      },

      /**项目经营数据搜索发放*/
      dltjDialogQuery(){
        let params = {
          beginTime:this.yjqk.beginTime,
          xmid:'',
          corporation:this.unitValue,
          endTime:this.yjqk.endTime,
          xmFag:this.yjqk.xmFag,
          xmqkXmxz:this.yjqk.xmqkXmxz,
          xmqkLyfgs:this.yjqk.xmqkLyfgs
        }
        console.info(params, '请求参数')
        this.initElectricityData(params)
      },

      /**关闭*/
      handleClose(done){
        this.yjqk = {
          year: '',
          month: ''
        }
        done()
      },
      /**电力统计自定义数据处理*/
      arrayA(array,key) {
        var resObj = {};
        for(var i=0;i<array.length;i++){
          resObj[array[i][key]] = array[i];
        }
        return resObj;
      },
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
                      fontSize:fontSize(16)
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

        //项目公司
        this.getXmgsDataList()
      },

      //动画
      draw(){
        this.angle = this.angle + 3
        this.middleIemStiDdata.dltjLjfdl =  this.setOption(this.dltjLjfdl, '累计发电量', '万KWh')
        this.middleIemStiDdata.dltjYjfdl =  this.setOption(this.dltjYjfdl, '预计发电量', '万KWh')
        this.middleIemStiDdata.dltjSjjsdl =  this.setOption(this.dltjSjjsdl, '实际发电量', '万KWh')
        this.middleIemStiDdata.dltjBysrhj =  this.setOption(this.dltjBysrhj, '电费收入', '万元')
        this.middleIemStiDdata.dltjByzchj =  this.setOption(this.dltjByzchj, '电费支出', '万元')

        if (this.timerId) {
          clearInterval(this.timerId);
        }
        var than = this
        this.timerId = setInterval(function() {
          //用setInterval做动画感觉有问题
          than.draw()
        }, 100);
      },

      getCirlPoint(x0, y0, r, angle) {
        let x1 = x0 + r * Math.cos(angle * Math.PI / 180)
        let y1 = y0 + r * Math.sin(angle * Math.PI / 180)
        return {
          x: x1,
          y: y1
        }
      },

      setOption(num, title, unit){
        var angle = this.angle
        var getCirlPoint = this.getCirlPoint
        return {
          title: {
            text: '{a|' + num + '}\n{c| ' +unit +' }',
            x: 'center',
            y: 'center',
            textStyle: {
              rich: {
                a: {
                  color: '#29EEF3',
                  padding: [0, 0, 5, 0],
                  fontSize:fontSize(12)
                },

                c: {

                  color: '#ffffff',
                  fontSize:fontSize(12)
                  // padding: [5,0]
                }
              }
            }
          },
          grid:{
            left:'15%'
          },
          series: [
            // 紫色
            {
              name: "ring5",
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: Math.min(api.getWidth(), api.getHeight()) / 2 * 0.7,
                    startAngle: (0 + angle) * Math.PI / 180,
                    endAngle: (90 + angle) * Math.PI / 180
                  },
                  style: {
                    stroke: "#8383FA",
                    fill: "transparent",
                    lineWidth: 1.5
                  },
                  silent: true
                };
              },
              data: [0]
            }, {
              name: "ring5", //紫点
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                let x0 = api.getWidth() / 2;
                let y0 = api.getHeight() / 2;
                let r = Math.min(api.getWidth(), api.getHeight()) / 2 * 0.7;
                let point = getCirlPoint(x0, y0, r, (90 + angle))
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 2
                  },
                  style: {
                    stroke: "#8450F9", //绿
                    fill: "#8450F9"
                  },
                  silent: true
                };
              },
              data: [0]
            },
            // 蓝色

            {
              name: "ring5",
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: Math.min(api.getWidth(), api.getHeight()) / 2 * 0.7,
                    startAngle: (180 + angle) * Math.PI / 180,
                    endAngle: (270 + angle) * Math.PI / 180
                  },
                  style: {
                    stroke: "#4386FA",
                    fill: "transparent",
                    lineWidth: 1.5
                  },
                  silent: true
                };
              },
              data: [0]
            },
            {
              name: "ring5", // 蓝色
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                let x0 = api.getWidth() / 2;
                let y0 = api.getHeight() / 2;
                let r = Math.min(api.getWidth(), api.getHeight()) / 2 * 0.7;
                let point = getCirlPoint(x0, y0, r, (270 + angle))
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 2
                  },
                  style: {
                    stroke: "#4386FA", //绿
                    fill: "#4386FA"
                  },
                  silent: true
                };
              },
              data: [0]
            },

            {
              name: "ring5",
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: Math.min(api.getWidth(), api.getHeight()) / 2 * 0.8,
                    startAngle: (270 + -angle) * Math.PI / 180,
                    endAngle: (40 + -angle) * Math.PI / 180
                  },
                  style: {
                    stroke: "#0CD3DB",
                    fill: "transparent",
                    lineWidth: 1.5
                  },
                  silent: true
                };
              },
              data: [0]
            },
            // 橘色

            {
              name: "ring5",
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: Math.min(api.getWidth(), api.getHeight()) / 2 * 0.8,
                    startAngle: (90 + -angle) * Math.PI / 180,
                    endAngle: (220 + -angle) * Math.PI / 180
                  },
                  style: {
                    stroke: "#FF8E89",
                    fill: "transparent",
                    lineWidth: 1.5
                  },
                  silent: true
                };
              },
              data: [0]
            }, {
              name: "ring5",
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                let x0 = api.getWidth() / 2;
                let y0 = api.getHeight() / 2;
                let r = Math.min(api.getWidth(), api.getHeight()) / 2 * 0.8;
                let point = getCirlPoint(x0, y0, r, (90 + -angle))
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 2
                  },
                  style: {
                    stroke: "#FF8E89", //粉
                    fill: "#FF8E89"
                  },
                  silent: true
                };
              },
              data: [0]
            }, {
              name: "ring5", //绿点
              type: 'custom',
              coordinateSystem: "none",
              renderItem: function(params, api) {
                let x0 = api.getWidth() / 2;
                let y0 = api.getHeight() / 2;
                let r = Math.min(api.getWidth(), api.getHeight()) / 2 * 0.8;
                let point = getCirlPoint(x0, y0, r, (270 + -angle))
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 2
                  },
                  style: {
                    stroke: "#0CD3DB", //绿
                    fill: "#0CD3DB"
                  },
                  silent: true
                };
              },
              data: [0]
            }

          ]
        }
      },

      //分公司现金流情况
      getFgsXjl(){
        let params = {corporationId:this.unitValue, beginTime:this.xjl.beginTime, endTime: this.xjl.endTime}
        fgsXjlqk(params).then(res => {
          let chartData = []
            if(res.code === 200 && res.data){
              chartData = res.data
            }else{
              chartData = [
                {
                  key:[],
                  value:[]
                },
                {
                  key:[],
                  value:[]
                },
                {
                  key:[],
                  value:[]
                },
                {
                  key:[],
                  value:[]
                }
              ]
            }
            console.info(chartData,'echarts数据')
          this.subsidiaryEcharts = {
            tooltip: {
              trigger: 'axis',

            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            legend: {
              data: ['预计收入', '预计支出', '实际收入', '实际支出'],
              textStyle:{
                color:'#fff',
                fontSize:fontSize(12)
              },
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              axisLine: {
                onZero: false,
                lineStyle: {
                  color: '#c0c0c0'
                }
              },
              axisLabel:{
                fontSize:fontSize(15)
              },
              data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
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
              },
            },
            series: [
              {
                name: '预计收入',
                type: 'line',
                stack: 'Total',
                data: chartData[0].value
              },
              {
                name: '预计支出',
                type: 'line',
                stack: 'Total',
                data: chartData[1].value
              },
              {
                name: '实际收入',
                type: 'line',
                stack: 'Total',
                data: chartData[2].value
              },
              {
                name: '实际支出',
                type: 'line',
                stack: 'Total',
                data: chartData[3].value
              }
            ]
          }
        })
      },

      //现金流年份选择器
      xjlCharts(){
        console.info(this.xjlValue,'现金流年份')
        this.getFgsXjl()
      },

      //
      rowClick(row) {
      },

      rowDetails(row, column,event){
        this.$router.push({name:'projectDetails',query:{xmid:row.xmid, xmqkJsztId:row.xmqkJsztId, xmqkJszt:row.xmqkJszt}})
      },

      init(){
        this.xmfl()

        this.xmxz()

        this.xmjsqk()

        this.allData()

        this.selectCharts()

        this.getItemSTI()

        this.getFgsXjl()
      },

      tableClassName({row, rowIndex}){
        return 'row-active-style';
      },

      /**资金预警分页*/
      zjyjSizeChange(val) {
        this.zjyjPageSize = val
        //1, this.csyjCurPage, this.csyjPageSize
        getProjectZjyjList({isFull:1, corporation:this.unitValue, pageNum:this.zjyjPageNum, pageSize:this.zjyjPageSize}).then(res => {
          if(res.code === 200){
            this.zjyjList = res.data.rsList
            this.zjyjTatol = res.data.total
          }
        })
      },

      zjyjCurrentChange(val) {
        this.zjyjPageNum = val
        getProjectZjyjList({isFull:1, corporation:this.unitValue, pageNum:this.zjyjPageNum, pageSize:this.zjyjPageSize}).then(res => {
          if(res.code === 200){
            this.zjyjList = res.data.rsList
            this.zjyjTatol = res.data.total
          }
        })
      },

      /**融资预警分页*/
      getRzyjData(){
        projectRzyj({ isFull:1, corporation:this.unitValue, pageNum:this.rzyjPageNum, pageSize:this.rzyjPageSize }).then(res => {
          if(res.code === 200 && res.data){
            this.rzyjTableList = res.data.rsList
            this.rzyjTotal = res.data.total
          }else{
            this.rzyjTableList = []
            this.rzyjTotal = 0
          }
        })
      },

      rzyjSizeChange(val){
        this.rzyjPageSize = val
        this.getRzyjData()
      },

      rzyjCurrentChange(val){
        this.rzyjPageNum = val
        this.getRzyjData()
      },

      /**工期预警分页*/
      initGqyjList(){
        getProjectGqyj({ isFull:1, pageNum:this.gqyjPageNum, pageSize:this.gqyjPageSize }).then(res => {
          if(res.code === 200 && res.data){
            this.gqyjTableList = res.data.rsList
            this.detalisTotal = res.data.total
          }else{
            this.gqyjTableList = []
            this.detalisTotal = 0
          }
        })
      },

      gqyjSizeChange(val){
        this.gqyjPageSize = val
        this.initGqyjList()
      },

      gqyjleCurrentChange(val){
        this.gqyjPageNum = val
        this.initGqyjList()
      },

      /**出售预警分页*/
      csyjSizeChange(val) {
        this.csyjPageSize = val
        getProjectCsyj({isFull:1, pageNum:this.csyjCurPage, pageSize:this.csyjPageSize}).then(res => {
          if(res.code === 200 && res.data){
            this.projectStctiacl = res.data.rsList
            this.csyjTatol = res.data.total
          }else{
            this.projectStctiacl = []
            this.csyjTatol = 0
          }
        })
      },

      csyjCurrentChange(val) {
        this.csyjCurPage = val
        getProjectCsyj({isFull:1, pageNum:this.csyjCurPage, pageSize:this.csyjPageSize}).then(res => {
          if(res.code === 200 && res.data){
            this.projectStctiacl = res.data.rsList
            this.csyjTatol = res.data.total
          }else{
            this.projectStctiacl = []
            this.csyjTatol = 0
          }
        })
      },

      /**资金预警，融资预警，工期预警，出售预警*/
      zjyjClick(){
        switch (this.defaultTab){
          case 1://资金预警
            this.zjyjVisible = true
            getProjectZjyjList({ corporation:this.unitValue, pageNum:this.zjyjPageNum, pageSize:this.zjyjPageSize }).then(res => {
              if(res.code === 200 && res.data){
                this.zjyjList = res.data.rsList
                this.zjyjTotal = res.data.total
              }else{
                this.zjyjList = []
                this.zjyjTotal = 0
              }
            })
            break;
          case 2://融资预警
            this.rzyjVisible = true
            projectRzyj({ corporation:this.unitValue, isFull:1,pageNum:this.rzyjPageNum, pageSize:this.rzyjPageSize }).then(res => {
              if(res.code === 200 && res.data){
                this.rzyjList = res.data.rsList
                this.rzyjTotal = res.data.total
              }else{
                this.rzyjList = []
                this.rzyjTotal = 0
              }
            })
            break;
          case 3://工期预警
            this.gqyjVisible = true
            getProjectGqyj({ corporation:this.unitValue, isFull:1, pageNum:this.gqyjPageNum, pageSize:this.gqyjPageSize }).then(res => {
              if(res.code === 200 && res.data){
                this.gqyjList = res.data.rsList
                this.gqyjTotal = res.data.total
              }else{
                this.gqyjList = []
                this.gqyjTotal = 0
              }
            })
            break;
          case 4://出售预警
            getProjectCsyj({corporation:this.unitValue, isFull:1, pageNum:this.csyjCurPage, pageSize:this.csyjPageSize}).then(res => {
              if(res.code === 200 && res.data){
                this.csyjList = res.data.rsList
                this.csyjTotal = res.data.total
              }else{
                this.csyjList = []
                this.csyjTotal = 0
              }
            })
            this.csyjVisible = true
            break;
        }
      },

      /**项目现状，项目建设情况*/
      queryDeteils(num){
        this.selectedDetails = num
        switch (num){
          case 1:
            this.leftDetailsTile = '项目现状'
            getSubsidiaryData(7).then(res => {
              if(res.code === 200 && res.data){
                this.xmzt = res.data
              }else{
                this.xmzt = []
              }
            })
            break;
          case 2:
            this.leftDetailsTile = '项目建设状态'
            getSubsidiaryData(9).then(res => {
              if(res.code === 200 && res.data){
                this.xmzt = res.data
              }else{
                this.xmzt = []
              }
            })
            break;
        }
        this.isOpenDetails = true
        let params = {
          isFull:1,
          corporation:this.unitValue,
          pageNum:this.pageNumXmqk,
          pageSize:this.pageSizeXmqk,
        }
        this.initXmxzAndxmjsztData(params)
      },

      /**关闭项目现状/建设情况弹框*/
      xmxzClose(done){
        this.xmqkAndXmztParams = {
          isFull:1,
          pageNum:1,
          pageSize:10,
          yearValue:'',
          monthValue:'',
        }
        this.xmqk = {
          year:'',
          month:'',
          projectStatus:''
        }
        done()
      },

      /**详情中搜索*/
      xmqkQuery(){
        this.xmqkAndXmztParams = {
          isFull:1,
          pageNum:this.pageNumXmqk,
          pageSize:this.pageSizeXmqk,
          beginTime:'',
          endTime:'',
          corporation:this.unitValue,
          xmFag:'',
          xmqkJszt:this.xmqk.xmqkJszt,
          xmrzRzqk:this.xmqk.xmrzRzqk,
        }
        this.xmqkAndXmztParams.beginTime = this.xmqk.beginTime
        this.xmqkAndXmztParams.endTime = this.xmqk.endTime
        this.xmqkAndXmztParams.xmFag = this.xmqk.xmFag
        switch (this.selectedDetails){
          case 1:
            this.deteilsTile = '项目现状'
            this.xmqkAndXmztParams.xmxz = this.xmqk.projectStatus
            break;
          case 2:
            this.deteilsTile = '项目建设状态'
            this.xmqkAndXmztParams.jszt = this.xmqk.projectStatus
            break;
        }
        this.initXmxzAndxmjsztData(this.xmqkAndXmztParams)
      },

      /**项目现状and项目建设情况分页*/
      xmxzSizeChange(val){
        this.pageSizeXmqk = val
        this.xmqkAndXmztParams.corporation = this.unitValue
        this.xmqkAndXmztParams.pageSize = val
        console.log(this.xmqkAndXmztParams, 'size')
        this.initXmxzAndxmjsztData(this.xmqkAndXmztParams)
      },

      xmxzCurrentChange(val){
        this.pageNumXmqk = val
        this.xmqkAndXmztParams.corporation = this.unitValue
        this.xmqkAndXmztParams.pageNum = val
        console.log(this.xmqkAndXmztParams, 'num')
        this.initXmxzAndxmjsztData(this.xmqkAndXmztParams)
      },

      /**项目现状和项目建设状态数据请求*/
      initXmxzAndxmjsztData(params){
        getXzAndJsqkDetalis(params).then(res => {
          if(res.code === 200 && res.data){
            this.xmqkTatol = res.data.total
            this.xmxzDataList = res.data.rsList
          }else{
            this.xmqkTatol = 0
            this.xmxzDataList = []
          }

        })
      },

      /**项目经营数据搜索*/
      xmjyDataQuery(){
        if(this.projectBusinessName === 'xmjysj'){
          this.projectBusiness()
        }else{
          this.initXmqd(this.xmjyqk)
        }
      },

      /**打开项目经营数据*/
      projectBusiness(){
        this.projectBusinessStatus = true
        let params = {
          monthValue:this.xmjyqk.month,
          xmid:'',
          corporation:this.unitValue,
          yearValue:this.xmjyqk.year
        }
        this.xmjyqk.corporation = this.unitValue
        getSjfkqk(this.xmjyqk).then(res => {
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
        getSjskqk(this.xmjyqk).then(res => {
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
                    fontSize: fontSize(20),
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
                  padding: [0, 0, fontSize(-30), 10],
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
                    fontSize: fontSize(20),
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
                    fontSize: fontSize(20),
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
                    fontSize: fontSize(15),
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
                    fontSize: fontSize(20),
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
                    fontSize: fontSize(15)
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
              radius: ['58%', '68%'],
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
                  formatter: `${res.data.value[5]}\n应收余额`,
                  fontSize:fontSize(20)
                },
                radius: ['50%', '53%'],
                center: ['50%', '50%'],
              },
            ],
          }
        })

        let  key1, value1
        getXmjyqk(this.xmjyqk).then(res => {
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
              left: '0%',
              top: '0%',
              right: '15%',
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
              axisLabel: {
                fontSize: fontSize(15)
              },
              data: this.yjjyqkData.key
            }],
            series: [{
              radius: [20, 100],
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

        getSjTotal(this.xmjyqk).then(res => {
          this.xmjyshTotal = res.data.xmTotal
        })
      },

    },
    created() {
      //分公司请求
      getSubsidiaryData(50).then(res => {
        this.unitSelect = res.data
        this.unitValue = res.data[0].id
        this.init()
        this.tab_money(1)
      })

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
      //融资情况
      getSubsidiaryData(108).then(res => {
        if(res.code === 200 && res.data) this.xmrzRzqkOption = res.data
      })

      getSubsidiaryData(8).then((res) => {
        if (res.code === 200 && res.data) {
          this.xjlOptions = res.data;
        }
      });
    },
    mounted() {
      this.setOption()
      this.draw()

      let date = new Date()
      this.yjqk.year = date.getFullYear().toString()
      this.yjqk.month = (date.getMonth() + 1).toString()
    }
  }
</script>

<style lang="scss" scoped>
  @import './index.scss';
  .subsidiary{
    padding-bottom: 10px;
    height: 100%;
    .selected_unit{
      height: 80px;
      display: flex;
      justify-content: center;
      padding-top: 15px;
      .unit_select{
        ::v-deep .el-select{
          .el-input--medium{
            border: 1px solid #2183c4;
            border-radius: 5px;
            .el-input__inner{
              color: #FFFFFF;
              height: 2rem;
              background: none;
              border-color: #07222f;
              line-height: 1.25rem;
              font-size: 0.75rem !important;
              box-shadow: none;
            }
            .el-input__suffix{
              top: 0.5rem;
            }
            .el-input__icon{
              line-height: inherit;
            }
            .el-input__suffix-inner{
              display: inline-block;
            }
          }
        }
      }
    }
    .project{
      padding: 0;
      height: calc(100% - 80px);
      .project_left{
        .project_type{
          .project_type_title{
            height: 30%;
            p{
              font-size: fontSize(16);
            }
          }
          .project_type_details{
            height: 50%;
            display: flex;
            justify-content: space-around;
            .content{
              width: 40%;
              height: 100%;
              border-radius: 5px;
              box-shadow: 0 0 5px #2183c4 inset;
            }
          }

        }
        .project_condition{
          .project_condition_title{
            height: 10%;
            display: flex;
            justify-content: space-between;
            .project-btn{
              width: 45%;display: flex;justify-content: space-between
            }
          }
          .project_condition_charts{
            height: 90%;
            .chart-wrapper{
              height: 100%;
              .echarts{
                height:100%
              }
            }
          }
        }
        .project_status{
          .project_condition_title{
            height: 10%;
            display: flex;
            justify-content: space-between;
          }
          .project_condition_charts{
            height: 90%;
            display: flex;
            justify-content: space-around;
            .chart-wrapper{
              width: 100%;
              .echarts{
                height: 100%;
              }
            }
            .condition_name{
              display: flex;
              flex-direction: column;
              justify-content: space-around;

            }

          }
        }
      }
      .project_middle{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        .middle_statistical{
          height: 25%;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          align-items: center;
          .unit_select{
            margin-top: -120px;
            ::v-deep .el-select{
              .el-input--medium{
                border: 1px solid #2183c4;
                .el-input__inner{
                  color: #FFFFFF;
                  height: 1.25rem;
                  background: none;
                  border-color: #07222f;
                  line-height: 1.25rem;
                  font-size: 0.75rem !important;
                  border-radius: 0 !important;
                  box-shadow: none;
                }
                .el-input__suffix{
                  top: 2px;
                }
                .el-input__icon{
                  line-height: inherit;
                }
                .el-input__suffix-inner{
                  display: inline-block;
                }
              }
            }
          }
          .data_statistical{
            width: 80%;
            height: 4.25rem;
            margin-top: -80px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            .details{
              height: 100%;
              display: flex;
              flex-direction: column;
              justify-content: space-around;
              div:first-child{
                span:first-child{
                  text-align: center;
                  color: #8ab8cd;
                  font-size: 1.5rem;
                  padding-right: 5px;
                }
              }
              div:last-child{
                span{
                  text-align: center;
                  font-size: 1rem;
                  font-weight: bold;
                }
              }
            }
          }
          .project_statistical{
            width: 65%;
            height: 100px;
            margin-top: -80px;
            display: flex;
            background: url("../../assets/screen/xxm.png") no-repeat;
            background-size: 100% 100%;
            justify-content: space-around;
            align-items: center;
            .details{
              div:first-child{
                span:first-child{font-size: fontSize(20);padding-right: fontSize(5); }
                span:last-child{font-size: fontSize(10);}
              }
              div:last-child{
                span{
                  text-align: center;
                  font-size: 1rem;
                }
              }
            }
          }
        }
        .middle_unit{
          height: 29%;
          padding: 10px;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          align-items: center;
          .unit_header{
            width: 100%;
            height: 20%;
            display: flex;
            justify-content: space-between;
            span{
              text-align: center;
            }
            .option_btn{
              width: 35%;
            }

          }
          .unit_details{
            width: 95%;
            height: 60%;
            display: flex;
            justify-content: space-around;
            align-items: center;
            .unit_chart{
              width: 20%;
              height: 100%;
              .echarts{
                height: 100%;
              }
            }

          }
          .uniot_projectTitle{
            width: 95%;
            height: 20%;
            display: flex;
            justify-content: space-around;
            align-items: center;
            .project_title{
              width: 20%;
              height: 100%;
              text-align: center;
            }
          }
        }
        .operating_condition{
          height: 40%;
          padding: 10px;
          .title_details_btn{
            height: 10%;
            display: flex;
            justify-content: space-between;
            .select_view{
              display: flex;
              .details_select{
                width: 6rem;
                background: none;
                color: #c1cbcb;
                margin-right: 15px;

              }
              .business{
                color:#c1cbcb ;
                background: none;
              }
              ::v-deep .el-input--medium {
                width: 6rem;
                background: none;
                color: #c1cbcb;
                margin-right: 15px;
                box-shadow: none;
                .el-input__inner {
                  height: vh(20);
                  background: none;
                  border-color: #2183c4;
                  line-height: vh(20);
                  font-size: 0.75rem !important;
                  border-radius: 0 !important;
                  box-shadow: none;
                }
                .el-input__suffix {
                  top: 2px;
                }
                .el-input__icon {
                  font-size: fontSize(10);
                  line-height: vh(20);
                  height: vh(20);
                }
                .el-input__suffix-inner {
                  display: inline-block;
                }

                .el-input__prefix{
                  .el-input__icon{
                    line-height: vh(20);
                  }
                }
              }

              .el-date-editor{

              }
            }
          }
          .title_details_content{
            height: 10%;
            display: flex;justify-content: flex-start
          }
          .details_echats{
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
      .project_right{

        .right_condition{
          height: 57%;
          position: relative;
          .condition_tab{
            padding: fontSize(15) fontSize(10);
            display: flex;
            justify-content: space-between;
            .tab {
              width: 90%;
              display: flex;
              justify-content: space-around;
              button {
                width: vw(90);
                height: vh(28);
                color: #c1cbcb;
                vertical-align: middle;
                line-height: fontSize(10);
              }
            }
          }
          .condition_list{
            height: 78%;
            overflow: hidden;
            .list_public{
              display: flex;
              justify-content: space-between;
              padding: 0 10px;
              margin: 0;
              height: 40px;
              line-height: 40px;
              overflow: hidden;
              li {
                list-style-type: none;
                text-align: center;
                width: 22.5%;
                &:first-child{
                  width: 10%;
                }
                &:nth-child(2) {
                  width: 45%;
                  overflow: hidden;
                  text-overflow: ellipsis;
                  white-space: nowrap;
                }
              }
            }
            .list_title{
              background: rgba(33, 131, 196, 0.2);
            }
            .list_data{
            }
          }
          .condition_details{
            padding-bottom: fontSize(10);
            padding-right: fontSize(10);
            position: absolute;
            right: 0;
            bottom: 0;
          }
        }
        .project_right_status{
          height: 40%;
          padding: fontSize(10);
          .project_condition_title{
            height: 10%;
            display: flex;
            justify-content: space-between;
            .select_view{
              //width: 6rem;
              background: none;
              color: #c1cbcb;
              margin-right: 15px;
              ::v-deep .el-input--medium {
                width: 6rem;
                background: none;
                color: #c1cbcb;
                margin-right: 15px;
                box-shadow: none;
                .el-input__inner {
                  height: vh(20);
                  background: none;
                  border-color: #2183c4;
                  line-height: vh(20);
                  font-size: 0.75rem !important;
                  border-radius: 0 !important;
                  box-shadow: none;
                }
                .el-input__suffix {
                  top: 2px;
                }
                .el-input__icon {
                  font-size: fontSize(10);
                  line-height: vh(20);
                }
                .el-input__suffix-inner {
                  display: inline-block;
                }

                .el-input__prefix{
                  .el-input__icon{
                    line-height: vh(20);
                  }
                }
              }
            }
          }
          .project_condition_charts{
            height: 90%;
            .chart-wrapper{
              .chart_h{
                height: 100%;
              }
            }
          }
        }
      }

    }
    /**弹框样式修改*/
    ::v-deep   .el-dialog{
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
        }
      }
      .el-button--default{
        background: none;
        box-shadow: 0 0 1.25rem #4097e7 inset;
        border: none;
      }

      .dialog_ul{


        .el-table .rowBackground{
          background: #00afff;
          opacity: 0.1;
        }
        .el-table{
          color: #e6eff3;
          background: none;
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



    .font-flex{
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      p{
        height: 50%;
        padding: 0;
        margin: 0;
        text-align: center;
        font-size: fontSize(16);
        line-height: 250%;
      }
    }

    .content{
      padding: 5px;
      height: 80%;
      display: flex;
      justify-content: space-around;
    }

    .p_details{
      font-size: fontSize(16);
    }
    hoverBtn{
      box-shadow: 0 0 5px #49a7e5 inset;
    }
    ::v-deep  .el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell{
      background-color: rgba(3, 131, 172, 0.5);
    }
    ::v-deep  .has-gutter{
      tr{
        background: rgba(43, 131, 172, 0.5);
        .el-table__cell{
          color: #a8a0a0;
          background: none;
          border-bottom: 0.0625rem solid #345b7c !important;
        }
      }
    }
    ::v-deep  .el-table__body{
      tbody{
        .el-table__row{
          background: none;
          .el-table__cell{
            border-bottom: 0.0625rem solid #345b7c !important;
          }
        }
      }
    }


  }


 ::v-deep .el-dialog{
    top:80px;
    box-shadow: 0 0 0.3125rem #2183c4 inset;
    border-radius: 0.3125rem;
    background: rgba(6,92,189,0.5);
    .el-dialog__body{
      padding: 10px 0;
      .dltjDialog{
        color: #FFFFFF;
        padding: 10px;
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
              }
              span:first-child{
                padding-top: fontSize(5);
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
              height: 75%;
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
              padding:0 20px;
              div:first-child{
                padding: 15px 0;
              }
              div:last-child{
                span:not(:first-child){
                  padding: 0 10px;
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
                .chart-wrapper{
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
    }

   ::v-deep .el-dialog__header{
      .el-dialog__title{
        color: #FFFFFF;
      }
    }

   ::v-deep .dialog_ul{
      .el-table{
        &::before{
          height: 0;
        }
      }
      .paging{
        padding: 10px;
        display: flex;
        justify-content: flex-end;
        /*position: fixed;*/
        /*bottom:0;*/
        /*right:0;*/
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


  .seamless-warp{
    height: calc(100% - 40px);
    overflow: hidden;
  }
  /**遮罩层颜色加深*/

</style>
