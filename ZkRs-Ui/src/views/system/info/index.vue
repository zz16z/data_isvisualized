<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="项目名称" prop="xmqkXmmc">
        <el-input
          v-model="queryParams.xmqkXmmc"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="项目现状" prop="xmqkXmxz">
        <el-select v-model="queryParams.xmqkXmxz" placeholder="请输入项目现状"  clearable style="width: 100%">
          <el-option
            v-for="dict in xmqkXmxzList"
            :key="dict.id"
            :label="dict.zdName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="履约分公司" prop="xmqkLyfgs" label-width="75px">

        <el-select v-model="queryParams.xmqkLyfgs" placeholder="请输入履约分公司"  clearable style="width: 100%">
          <el-option
            v-for="dict in xmqkLyfgsList"
            :key="dict.id"
            :label="dict.zdName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="建设状态" prop="xmqkJszt">
        <el-select v-model="queryParams.xmqkJszt" placeholder="请输入建设状态"  clearable style="width: 100%">
          <el-option
            v-for="dict in xmqkJsztList"
            :key="dict.id"
            :label="dict.zdName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>


    <!--  <el-form-item label="动工时间" prop="xmqkHtsj">
        <el-date-picker clearable
                        v-model="queryParams.xmqkHtsj"
                        type="month"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择动工时间">
        </el-date-picker>
      </el-form-item> -->

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:info:remove']"
        >删除</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-upload2"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:info:export']"
        >导出项目情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-upload2"
          size="mini"
          @click="handleExportConstruction"
          v-hasPermi="['system:info:export']"
        >导出施工情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-upload2"
          size="mini"
          @click="handleExportManagement"
          v-hasPermi="['system:info:export']"
        >导出经营情况</el-button>
      </el-col>
       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportFinancing"
           v-hasPermi="['system:info:export']"
         >导出融资情况</el-button>
       </el-col>
	   <el-col :span="1.5">
	     <el-button
	       type="warning"
	       icon="el-icon-upload2"
	       size="mini"
	       @click="handleExportRepayment"
	       v-hasPermi="['system:info:export']"
	     >导出还款情况</el-button>
	   </el-col>
	   <!-- handleExportRepayment-->
<!--       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportPreFinancing"
           v-hasPermi="['system:info:export']"
         >导出预计融资情况</el-button>
       </el-col>
       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportTrueFinancing"
           v-hasPermi="['system:info:export']"
         >导出实际融资情况</el-button>
       </el-col> -->
       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportTrueElectricity"
           v-hasPermi="['system:info:export']"
         >导出电量收支情况</el-button>
       </el-col>
       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportProjectCash"
           v-hasPermi="['system:info:export']"
         >导出预计现金流出情况</el-button>
       </el-col>
       <el-col :span="1.5">
         <el-button
           type="warning"
           icon="el-icon-upload2"
           size="mini"
           @click="handleExportProjectCashIn"
           v-hasPermi="['system:info:export']"
         >导出预计现金流入情况</el-button>
       </el-col>

      </el-row>
      <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImport"
        >导入项目情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportConstruction"
        >导入施工情况</el-button>
       </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportManagement"
        >导入经营情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportFinancing"
        >导入融资情况</el-button>
      </el-col>
	  <el-col :span="1.5">
	    <el-button
	      type="warning"
	      plain
	      icon="el-icon-download"
	      size="mini"
	      @click="handleImportepayment"
	    >导入还款情况</el-button>
	  </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportExpectedFinancing"
        >导入预计融资情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportActualFinancing"
        >导入实际融资情况</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"M
          @click="handleImportElectricity"
        >导入电量收支情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"M
          @click="handleImportProjectCash"
        >导入预计现金流出情况</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImportProjectCashIn"
        >导入预计现金流入情况</el-button>
      </el-col>

        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-upload2"
            size="mini"
            @click="dataClear"
          >数据清除</el-button>
        </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目id" align="center" prop="xmqkProjectId" v-if="false" />
      <el-table-column label="项目名称" align="center" prop="xmqkXmmc" />
      <el-table-column label="容量(KW)" align="center" prop="xmqkRl" />
      <el-table-column label="项目类型" align="center" prop="xmqkXmlxName" />
      <el-table-column label="项目现状" align="center" prop="xmqkXmxzName" />
      <el-table-column label="履约分公司" align="center" prop="xmqkLyfgsName" />
      <el-table-column label="建设状态" align="center" prop="xmqkJsztName" />

      <el-table-column label="区域" align="center" prop="xmqkQy" />
      <el-table-column label="动工时间" align="center" prop="xmqkHtsj" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.xmqkHtsj, '{y}-{m}') }}</span>
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleLook(scope.row)"
            v-hasPermi="['system:info:edit']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!--删除表对话框 -->
    <el-dialog :title="dataClearTitle" :visible.sync="dataClearOpen" v-if="dataClearOpen"  width="85%" append-to-body>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="dataClearInfo"
        >清除项目情况数据</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="dataClearConstruction"-->
<!--        >清除施工情况数据</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="dataClearManagement"
        >清除经营情况数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="dataClearFinancing"
        >清除融资情况数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="dataClearTepayment"
        >清除还款情况数据</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"M
          @click="dataClearElectricity"
        >清除电量收支情况数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"M
          @click="dataClearProjectCash"
        >清除预计现金流出情况数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="dataClearProjectCashIn"
        >清除预计现金流入情况数据</el-button>
      </el-col>

    </el-dialog>

    <!-- 添加或修改项目情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目id" prop="xmqkProjectId" hidden="hidden">
          <el-input v-model="form.xmqkProjectId" placeholder="请输入项目id" />
        </el-form-item>

        <el-row>
          <el-col :span="8">
            <el-form-item label="项目名称" prop="xmqkXmmc">
              <el-input v-model="form.xmqkXmmc" placeholder="请输入项目名称" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="容量(KW)" prop="xmqkRl">
              <el-input v-model="form.xmqkRl" placeholder="请输入容量(KW)" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="项目类型" prop="xmqkXmlx">
              <el-input v-model="form.xmqkXmlx" placeholder="请输入项目类型" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="8">
            <el-form-item label="项目现状" prop="xmqkXmxz">
              <el-input v-model="form.xmqkXmxz" placeholder="请输入项目现状" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="履约分公司" prop="xmqkLyfgs">
              <el-input v-model="form.xmqkLyfgs" placeholder="请输入履约分公司" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="建设状态" prop="xmqkJszt">
              <el-input v-model="form.xmqkJszt" placeholder="请输入建设状态" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="区域" prop="xmqkQy">
              <el-input v-model="form.xmqkQy" placeholder="请输入区域" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="动工时间" prop="xmqkHtsj">
              <el-date-picker clearable
                              v-model="form.xmqkHtsj"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择动工时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="并网时间" prop="xmqkBwsj">
              <el-date-picker clearable
                              v-model="form.xmqkBwsj"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择并网时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="融资方" prop="xmqkRzf">
              <el-input v-model="form.xmqkRzf" placeholder="请输入融资方" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收购方" prop="xmqkSgf">
              <el-input v-model="form.xmqkSgf" placeholder="请输入收购方" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
             <el-form-item label="业主方" prop="xmqkHzf">
              <el-input v-model="form.xmqkHzf" placeholder="请输入业主方" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="融资经理" prop="xmqkRzjl">
              <el-input v-model="form.xmqkRzjl" placeholder="请输入融资经理" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="商务经理" prop="xmqkSwjl">
              <el-input v-model="form.xmqkSwjl" placeholder="请输入商务经理" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="项目当前值" prop="xmqkXmdqz">
              <el-input v-model="form.xmqkXmdqz" placeholder="请输入项目当前值" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="升压站开挖" prop="xmqkSyzkw">
              <el-input v-model="form.xmqkSyzkw" placeholder="请输入升压站开挖" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="基础开挖" prop="xmqkJckw">
              <el-input v-model="form.xmqkJckw" placeholder="请输入基础开挖" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="建设情况" prop="xmqkJsqk">
              <el-input v-model="form.xmqkJsqk" placeholder="请输入建设情况" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="设备到货" prop="xmqkSbdh">
              <el-input v-model="form.xmqkSbdh" placeholder="请输入设备到货" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="调整预计出售单价(元/千瓦)" prop="xmqkTzyjcsdj">
              <el-input v-model="form.xmqkTzyjcsdj" placeholder="请输入调整预计出售单价(元/千瓦)" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预计出售总价" prop="xmqkYjcszj">
              <el-input v-model="form.xmqkYjcszj" placeholder="请输入预计出售总价" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="8">
            <el-form-item label="EPC结算价 " prop="xmqkEpcJsj">
              <el-input v-model="form.xmqkEpcJsj" placeholder="请输入EPC结算价 " />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="增加EPC结算价" prop="xmqkEpcZjjsj">
              <el-input v-model="form.xmqkEpcZjjsj" placeholder="请输入增加EPC结算价" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="预计收入合计" prop="xmqkYjsyhj">
              <el-input v-model="form.xmqkYjsyhj" placeholder="请输入预计收入合计" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="项目公司成本" prop="xmqkXmgscb">
              <el-input v-model="form.xmqkXmgscb" placeholder="请输入项目公司成本" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="项目EPC成本" prop="xmqkXmepccb">
              <el-input v-model="form.xmqkXmepccb" placeholder="请输入项目EPC成本" />
            </el-form-item>
          </el-col>

          <el-col :span="8">

            <el-form-item label="其中资源费" prop="xmqkQzzyf">
              <el-input v-model="form.xmqkQzzyf" placeholder="请输入其中资源费" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="总成本" prop="xmqkZcb">
              <el-input v-model="form.xmqkZcb" placeholder="请输入总成本" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已收预收购款" prop="xmqkSjYsysgk">
              <el-input v-model="form.xmqkSjYsysgk" placeholder="请输入已收预收购款" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已收融资款" prop="xmqkYsrzk">
              <el-input v-model="form.xmqkYsrzk" placeholder="请输入已收融资款" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="工程回款(业主回款)" prop="xmqkGzk">
              <el-input v-model="form.xmqkGzk" placeholder="请输入工程回款(业主回款)" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已收电费" prop="xmqkYsdf">
              <el-input v-model="form.xmqkYsdf" placeholder="请输入已收电费" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="实际收入合计" prop="xmqkSjsrhj">
              <el-input v-model="form.xmqkSjsrhj" placeholder="请输入实际收入合计" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="应收余额" prop="xmqkYsye">
              <el-input v-model="form.xmqkYsye" placeholder="请输入应收余额" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已付设备款" prop="xmqkYfsbk">
              <el-input v-model="form.xmqkYfsbk" placeholder="请输入已付设备款" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已付建安款" prop="xmqkYfjak">
              <el-input v-model="form.xmqkYfjak" placeholder="请输入已付建安款" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="已付其他款项" prop="xmqkYfqtkx">
              <el-input v-model="form.xmqkYfqtkx" placeholder="请输入已付其他款项" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="已付总金额" prop="xmqkYfzje">
              <el-input v-model="form.xmqkYfzje" placeholder="请输入已付总金额" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="应收余额" prop="xmqkYssjye">
              <el-input v-model="form.xmqkYssjye" placeholder="请输入应收余额" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="实际应付未付款项" prop="xmqkSjyfwfkx">
              <el-input v-model="form.xmqkSjyfwfkx" placeholder="请输入实际应付未付款项" />
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="合同支付余额" prop="xmqkHtzfye">
              <el-input v-model="form.xmqkHtzfye" placeholder="请输入合同支付余额" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="已付履约保证金" prop="xmqkYflybzj">
              <el-input v-model="form.xmqkYflybzj" placeholder="请输入已付履约保证金" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="已退履约保证金" prop="xmqkYtlybzj">
          <el-input v-model="form.xmqkYtlybzj" placeholder="请输入已退履约保证金" />
        </el-form-item>
        <el-form-item label="备注" prop="xmqkRemark">
          <el-input v-model="form.xmqkRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" v-if="upload.open"  width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
         drag
        :http-request="uploadFileok"
        action
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listInfo, getInfo, delInfo, addInfo, updateInfo ,importBox,importBoxConstruction,importBoxManagement,importBoxElectricity,importBoxFinancing,importBoxExpectedFinancing,importBoxExpectedTrueFinancing,importProjectCash,importProjectCashIn,importRepayment
  ,getDataClearInfo,getDataClearManagement,getDataClearFinancing,getdataClearTepayment,getDataClearElectricity,getDataClearProjectCash,getDataClearProjectCashIn
  } from "@/api/system/info";
  import { getToken } from "@/utils/auth";
  import {listDictionary} from "@/api/system/dictionaryData";
  export default {
    name: "Info",
    // registerRules: {
    //   username: [
    //     { required: true, trigger: "blur", message: "请输入您的账号" },
    //     { min: 2, max: 20, message: '用户账号长度必须介于 2 和 20 之间', trigger: 'blur' }
    //   ],
    //   // password: [
    //   //   { required: true, trigger: "blur", message: "请输入您的密码" },
    //   //   { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
    //   // ],
    //   // confirmPassword: [
    //   //   { required: true, trigger: "blur", message: "请再次输入您的密码" },
    //   //   { required: true, validator: equalToPassword, trigger: "blur" }
    //   // ],
    //   // code: [{ required: true, trigger: "change", message: "请输入验证码" }]
    // },
    data() {
      return {
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址，请求地址
          // url: process.env.VUE_APP_BASE_API + "/system/info/importBox"
        },
        //导入模板 1.项目情况导入   2.施工情况导入
        templateType:1,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 项目情况表格数据
        infoList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        dataClearOpen:false,
        dataClearTitle:'数据清除',
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          xmqkProjectId: null,
          xmqkXmmc: null,
          xmqkRl: null,
          xmqkXmlx: null,
          xmqkXmxz: null,
          xmqkLyfgs: null,
          xmqkJszt: null,
          xmqkQy: null,
          xmqkHtsj: null,
          xmqkBwsj: null,
          xmqkRzf: null,
          xmqkSgf: null,
          xmqkHzf: null,
          xmqkRzjl: null,
          xmqkSwjl: null,
          xmqkXmdqz: null,
          xmqkSyzkw: null,
          xmqkJckw: null,
          xmqkJsqk: null,
          xmqkSbdh: null,
          xmqkTzyjcsdj: null,
          xmqkYjcszj: null,
          xmqkEpcJsj: null,
          xmqkEpcZjjsj: null,
          xmqkYjsyhj: null,
          xmqkXmgscb: null,
          xmqkXmepccb: null,
          xmqkQzzyf: null,
          xmqkZcb: null,
          xmqkSjYsysgk: null,
          xmqkYsrzk: null,
          xmqkGzk: null,
          xmqkYsdf: null,
          xmqkSjsrhj: null,
          xmqkYsye: null,
          xmqkYfsbk: null,
          xmqkYfjak: null,
          xmqkYfqtkx: null,
          xmqkYfzje: null,
          xmqkYsye1: null,
          xmqkSjyfwfkx: null,
          xmqkHtzfye: null,
          xmqkYflybzj: null,
          xmqkYtlybzj: null,
          xmqkRemark: null
        },
        //项目类型字典
        xmqkXmlxList:[],
        xmqkXmxzList:[],
        xmqkJsztList:[],
        xmqkLyfgsList:[],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
      this.getListXmxzDir(7)
      //项目类型
      this.getListDir(8)
      //建设状态
      this.getListJsztDir(9)
      //履约分公司
      this.getListlyfgs(50)
    },
    methods: {
      getListDir( pid) {//
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkXmlxList = response.rows;
        });
      },

      getListXmxzDir( pid) { //7
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkXmxzList = response.rows;
        });
      },
      getListJsztDir( pid) { //9
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkJsztList = response.rows;
        });
      },
      getListlyfgs( pid) { //50
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkLyfgsList = response.rows;
        });
      },
      /** 查询项目情况列表 */
      getList() {
        this.loading = true;
        listInfo(this.queryParams).then(response => {
          this.infoList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          xmqkProjectId: null,
          xmqkXmmc: null,
          xmqkRl: null,
          xmqkXmlx: null,
          xmqkXmxz: null,
          xmqkLyfgs: null,
          xmqkJszt: null,
          xmqkQy: null,
          xmqkHtsj: null,
          xmqkBwsj: null,
          xmqkRzf: null,
          xmqkSgf: null,
          xmqkHzf: null,
          xmqkRzjl: null,
          xmqkSwjl: null,
          xmqkXmdqz: null,
          xmqkSyzkw: null,
          xmqkJckw: null,
          xmqkJsqk: null,
          xmqkSbdh: null,
          xmqkTzyjcsdj: null,
          xmqkYjcszj: null,
          xmqkEpcJsj: null,
          xmqkEpcZjjsj: null,
          xmqkYjsyhj: null,
          xmqkXmgscb: null,
          xmqkXmepccb: null,
          xmqkQzzyf: null,
          xmqkZcb: null,
          xmqkSjYsysgk: null,
          xmqkYsrzk: null,
          xmqkGzk: null,
          xmqkYsdf: null,
          xmqkSjsrhj: null,
          xmqkYsye: null,
          xmqkYfsbk: null,
          xmqkYfjak: null,
          xmqkYfqtkx: null,
          xmqkYfzje: null,
          xmqkYsye1: null,
          xmqkSjyfwfkx: null,
          xmqkHtzfye: null,
          xmqkYflybzj: null,
          xmqkYtlybzj: null,
          xmqkRemark: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          isDelete: null,
          xmqkFjcs: null,
          xmqkTtcs: null,
          xmqkZjcs: null,
          xmqkZjiacs: null,

        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.xmqkProjectId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        // this.reset();
        // this.open = true;
        // this.title = "添加项目情况";

        this.$router.push({path:'/add_management',query: {operationStatus:0}})
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const xmqkProjectId = row.xmqkProjectId || this.ids
        //0新增、1修改、2查看
        this.$router.push({path:'/add_management_edit',query: {xmqkProjectId:xmqkProjectId,operationStatus:1}})
        // getInfo(xmqkProjectId).then(response => {
        //   this.form = response.data;
        //   this.open = true;
        //   this.title = "修改项目情况";
        //
        //   //this.$router.push({path:'/add_management',query: {xmqkProjectId:'1'}})
        // });
      },
      /** 查看 */
      handleLook(row) {
        this.reset();
        const xmqkProjectId = row.xmqkProjectId || this.ids
        //0新增、1修改、2查看
        this.$router.push({path:'/add_management_look',query: {xmqkProjectId:xmqkProjectId,operationStatus:2}})
        //  let tep={"test":"123"}
        //传值
        //  console.info("99999:"+this.$store.dispatch('business/setXmid', '11111'))
        //  console.info("8888888:"+this.$store.getters.tempData.data.xmid)

      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {

          if (valid) {
            if (this.form.xmqkProjectId != null) {
              updateInfo(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addInfo(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const xmqkProjectIds = row.xmqkProjectId || this.ids;
        this.$modal.confirm('是否确认删除项目情况编号为"' + xmqkProjectIds + '"的数据项？').then(function() {
          return delInfo(xmqkProjectIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
		filterTime(time) {
		  const date = new Date(time)
		  const Y = date.getFullYear()
		  const M = date.getMonth() + 1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1
		  const D = date.getDate()
		  return `${Y}_${M}_${D}`
		},
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/info/export', {
          ...this.queryParams
        }, `项目情况_${this.filterTime(this.filterTime(new Date().getTime()))}.xlsx`)
      },
      handleExportConstruction() {
        this.download('system/info/exportConstruction', {
          ...this.queryParams
        }, `施工情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportManagement() {
        this.download('system/info/exportManagement', {
          ...this.queryParams
        }, `经营情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportFinancing() {
        this.download('system/info/exportFinancing', {
          ...this.queryParams
        }, `融资情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
	  handleExportRepayment() {
	    this.download('system/info/exportRepayment', {
	      ...this.queryParams
	    }, `还款情况_${this.filterTime(new Date().getTime())}.xlsx`)
	  },
      handleExportPreFinancing() {
        this.download('system/info/exportPreFinancing', {
          ...this.queryParams
        }, `预计融资情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportTrueFinancing() {
        this.download('system/info/exportTrueFinancing', {
          ...this.queryParams
        }, `实际融资情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportTrueElectricity() {
        this.download('system/info/exportElectricity', {
          ...this.queryParams
        }, `电量收支情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportProjectCash() {
        this.download('system/info/exportProjectCash', {
          ...this.queryParams
        }, `预计现金流出情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      handleExportProjectCashIn() {
        this.download('system/info/exportProjectCashIn', {
          ...this.queryParams
        }, `预计现金流入情况_${this.filterTime(new Date().getTime())}.xlsx`)
      },
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${this.filterTime(new Date().getTime())}.xlsx`)
        if(this.templateType == 1){
          this.download('/system/info/downloadTemplate', {
          }, `项目情况${this.filterTime(new Date().getTime())}.xlsx`)

          this.upload.open = false;
          this.upload.isUploading = false;
          this.$refs.upload.clearFiles();
        }
        if(this.templateType == 2){
         this.download('/system/info/downloadTemplateConstruction', {
         }, `施工情况${this.filterTime(new Date().getTime())}.xlsx`)

         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 3){
         this.download('/system/info/downloadTemplateManagement', {
         }, `经营情况${this.filterTime(new Date().getTime())}.xlsx`)

         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 4){
         this.download('/system/info/downloadElectricity', {
         }, `电量情况${this.filterTime(new Date().getTime())}.xlsx`)

         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 5){
         this.download('/system/info/handleImportFinancing', {
         }, `项目融资情况${this.filterTime(new Date().getTime())}.xlsx`)
         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 6){
         this.download('/system/info/handleImportExpectedFinancing', {
         }, `预计融资情况${this.filterTime(new Date().getTime())}.xlsx`)
         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 7){
         this.download('/system/info/handleImportActualFinancing', {
         }, `实际融资情况${this.filterTime(new Date().getTime())}.xlsx`)
         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 8){
         this.download('/system/info/handleImportProjectCashMode', {
         }, `预计现金流出情况${this.filterTime(new Date().getTime())}.xlsx`)
         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
        if(this.templateType == 9){
         this.download('/system/info/handleImportProjectCashIn', {
         }, `预计现金流入情况${this.filterTime(new Date().getTime())}.xlsx`)
         this.upload.open = false;
         this.upload.isUploading = false;
         this.$refs.upload.clearFiles();
        }
		if(this.templateType == 10){
		 this.download('/system/info/handleImportRepayment', {
		 }, `还款情况${this.filterTime(new Date().getTime())}.xlsx`)
		 this.upload.open = false;
		 this.upload.isUploading = false;
		 this.$refs.upload.clearFiles();
		}

      },
      //数据清除
      dataClear(){
        this.dataClearOpen=true;
      },
      //清除项目情况数据
      dataClearInfo(){
        this.$modal.confirm('是否确认删除项目情况数据数据？').then(function() {
          return getDataClearInfo();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

        this.dataClearOpen=false;
      },
      //清除施工情况数据
      dataClearConstruction(){

      },
      //清除经营情况数据
      dataClearManagement(){


        this.$modal.confirm('是否确认删除经营情况数据数据？').then(function() {
          return   getDataClearManagement();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

      },
      //清除融资情况数据
      dataClearFinancing(){

        this.$modal.confirm('是否确认删除融资情况数据？').then(function() {
          return getDataClearFinancing();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

      },
      //清除还款情况数据
      dataClearTepayment(){


        this.$modal.confirm('是否确认删除还款情况数据数据？').then(function() {
          return getdataClearTepayment();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

      },
      //清除电量收支情况数据
      dataClearElectricity(){


        this.$modal.confirm('是否确认删除电量收支情况数据？').then(function() {
          return getDataClearElectricity();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

      },
      //清除预计现金流出情况数据
      dataClearProjectCash(){
        this.$modal.confirm('是否确认删除预计现金流出情况数据？').then(function() {
          return getDataClearProjectCashIn();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});

      },
      //清除预计现金流入情况数据
      dataClearProjectCashIn(){
        this.$modal.confirm('是否确认删除预计现金流入情况数据？').then(function() {
          return getDataClearProjectCash();
        }).then(() => {

          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      // 提交上传文件
      submitFileForm() {
        //触发uploadFileok函数
        this.$refs.upload.submit();
      },
      /** 导入按钮操作 */
      handleImport() {
        this.templateType = 1;
        this.upload.title = "项目情况导入";
        this.upload.open = true;
      },
      handleImportConstruction(){
        this.templateType = 2;
        this.upload.title = "施工情况导入";
        this.upload.open = true;
      },
      handleImportManagement(){
        this.templateType = 3;
        this.upload.title = "经营情况导入";
        this.upload.open = true;
      },
      handleImportElectricity(){
        this.templateType = 4;
        this.upload.title = "电量情况导入";
        this.upload.open = true;
      },
      handleImportFinancing(){
        this.templateType = 5;
        this.upload.title = "融资情况导入";
        this.upload.open = true;
      },
      handleImportExpectedFinancing(){
        this.templateType = 6;
        this.upload.title = "预计融资情况导入";
        this.upload.open = true;
      },
      handleImportActualFinancing(){
        this.templateType = 7;
        this.upload.title = "实际融资情况导入";
        this.upload.open = true;
      },
      handleImportProjectCash(){
        this.templateType = 8;
        this.upload.title = "预计现金流出情况导入";
        this.upload.open = true;
      },
      handleImportProjectCashIn(){
        this.templateType = 9;
        this.upload.title = "预计现金流入情况导入";
        this.upload.open = true;
      },
	  handleImportepayment(){
	    this.templateType = 10;
	    this.upload.title = "还款情况导入";
	    this.upload.open = true;
	  },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert("" +
          "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
          " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
          "导入结果", {
            dangerouslyUseHTMLString: true
          });
        if(response.code=="200"){
          this.getList()
        }
      },
      uploadFileok(item){
        if(this.msg_xmid==""){
          this.$alert("" +
            "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
            " 0vh;padding: 10px 20px 0;'>" + "请在对应的序号下导入" + "</div>",
            "导入结果", {
              dangerouslyUseHTMLString: true
            });
          return
        }
        let FormDatas = new FormData()
        FormDatas.append('file',item.file);
        FormDatas.append("xmid",this.msg_xmid)
        if(this.templateType == 1){
          importBox(FormDatas).then(response => {
            this.upload.open = false;
            this.upload.isUploading = false;
            this.$refs.upload.clearFiles();
            this.$alert("" +
              "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
              " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
              "导入结果", {
                dangerouslyUseHTMLString: true
              });
            if(response.code=="200"){
              this.getList()
            }
          })
        }
        if(this.templateType == 2){
            importBoxConstruction(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 3){
            importBoxManagement(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 4){
            importBoxElectricity(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 5){
            importBoxFinancing(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 6){
            importBoxExpectedFinancing(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 7){
            importBoxExpectedTrueFinancing(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 8){
            importProjectCash(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
        if(this.templateType == 9){
            importProjectCashIn(FormDatas).then(response => {
              this.upload.open = false;
              this.upload.isUploading = false;
              this.$refs.upload.clearFiles();
              this.$alert("" +
                "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
                " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
                "导入结果", {
                  dangerouslyUseHTMLString: true
                });
              if(response.code=="200"){
                this.getList()
              }
            })
        }
		if(this.templateType == 10){
		    importRepayment(FormDatas).then(response => {
		      this.upload.open = false;
		      this.upload.isUploading = false;
		      this.$refs.upload.clearFiles();
		      this.$alert("" +
		        "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
		        " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
		        "导入结果", {
		          dangerouslyUseHTMLString: true
		        });
		      if(response.code=="200"){
		        this.getList()
		      }
		    })
		}
      }
    }
  };
</script>
