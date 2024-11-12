<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目id" prop="xjlyjqkProjectId">
        <el-input
          v-model="queryParams.xjlyjqkProjectId"
          placeholder="请输入项目id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="xjlyjqkRq">
        <el-date-picker clearable
          v-model="queryParams.xjlyjqkRq"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="已经出售总价" prop="xjlyjqkYjcszj">
        <el-input
          v-model="queryParams.xjlyjqkYjcszj"
          placeholder="请输入已经出售总价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计EPC成本" prop="xjlyjqkYjecpcb">
        <el-input
          v-model="queryParams.xjlyjqkYjecpcb"
          placeholder="请输入预计EPC成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计项目公司成本" prop="xjlyjqkYjxmgscb">
        <el-input
          v-model="queryParams.xjlyjqkYjxmgscb"
          placeholder="请输入预计项目公司成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收款额" prop="xjlyjqkSke">
        <el-input
          v-model="queryParams.xjlyjqkSke"
          placeholder="请输入收款额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="融资款" prop="xjlyjqkRzk">
        <el-input
          v-model="queryParams.xjlyjqkRzk"
          placeholder="请输入融资款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程款" prop="xjlyjqkGck">
        <el-input
          v-model="queryParams.xjlyjqkGck"
          placeholder="请输入工程款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电费收款" prop="xjlyjqkDfsk">
        <el-input
          v-model="queryParams.xjlyjqkDfsk"
          placeholder="请输入电费收款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="xjlyjqkHj">
        <el-input
          v-model="queryParams.xjlyjqkHj"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备款" prop="xjlyjqkSbk">
        <el-input
          v-model="queryParams.xjlyjqkSbk"
          placeholder="请输入设备款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建安款" prop="xjlyjqkJak">
        <el-input
          v-model="queryParams.xjlyjqkJak"
          placeholder="请输入建安款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他款项" prop="xjlyjqkQtkx">
        <el-input
          v-model="queryParams.xjlyjqkQtkx"
          placeholder="请输入其他款项"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="xjlyjqkhHj">
        <el-input
          v-model="queryParams.xjlyjqkhHj"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除：未删除 0、  删除 1" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入是否删除：未删除 0、  删除 1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['system:cash:add']"
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
          v-hasPermi="['system:cash:edit']"
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
          v-hasPermi="['system:cash:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:cash:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cashList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="现金流情况id" align="center" prop="xjlyjqkId" />
      <el-table-column label="项目id" align="center" prop="xjlyjqkProjectId" />
      <el-table-column label="日期" align="center" prop="xjlyjqkRq" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.xjlyjqkRq, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已经出售总价" align="center" prop="xjlyjqkYjcszj" />
      <el-table-column label="预计EPC成本" align="center" prop="xjlyjqkYjecpcb" />
      <el-table-column label="预计项目公司成本" align="center" prop="xjlyjqkYjxmgscb" />
      <el-table-column label="收款额" align="center" prop="xjlyjqkSke" />
      <el-table-column label="融资款" align="center" prop="xjlyjqkRzk" />
      <el-table-column label="工程款" align="center" prop="xjlyjqkGck" />
      <el-table-column label="电费收款" align="center" prop="xjlyjqkDfsk" />
      <el-table-column label="合计" align="center" prop="xjlyjqkHj" />
      <el-table-column label="设备款" align="center" prop="xjlyjqkSbk" />
      <el-table-column label="建安款" align="center" prop="xjlyjqkJak" />
      <el-table-column label="其他款项" align="center" prop="xjlyjqkQtkx" />
      <el-table-column label="合计" align="center" prop="xjlyjqkhHj" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cash:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cash:remove']"
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

    <!-- 添加或修改现金流预计情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" append-to-body>

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目id" prop="xjlyjqkProjectId" hidden="hidden">
          <el-input v-model="form.xjlyjqkProjectId" placeholder="请输入项目id" />
        </el-form-item>

        <el-row>
          <el-col :span="8">
        <el-form-item label="日期" prop="xjlyjqkRq">
          <el-date-picker clearable
            v-model="form.xjlyjqkRq"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="已经出售总价" prop="xjlyjqkYjcszj">
          <el-input v-model="form.xjlyjqkYjcszj" placeholder="请输入已经出售总价" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="预计EPC成本" prop="xjlyjqkYjecpcb">
          <el-input v-model="form.xjlyjqkYjecpcb" placeholder="请输入预计EPC成本" />
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
        <el-form-item label="预计项目公司成本" prop="xjlyjqkYjxmgscb">
          <el-input v-model="form.xjlyjqkYjxmgscb" placeholder="请输入预计项目公司成本" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="收款额" prop="xjlyjqkSke">
          <el-input v-model="form.xjlyjqkSke" placeholder="请输入收款额" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="融资款" prop="xjlyjqkRzk">
          <el-input v-model="form.xjlyjqkRzk" placeholder="请输入融资款" />
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
        <el-form-item label="工程款" prop="xjlyjqkGck">
          <el-input v-model="form.xjlyjqkGck" placeholder="请输入工程款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="电费收款" prop="xjlyjqkDfsk">
          <el-input v-model="form.xjlyjqkDfsk" placeholder="请输入电费收款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="合计" prop="xjlyjqkHj">
          <el-input v-model="form.xjlyjqkHj" placeholder="请输入合计" />
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
        <el-form-item label="设备款" prop="xjlyjqkSbk">
          <el-input v-model="form.xjlyjqkSbk" placeholder="请输入设备款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="建安款" prop="xjlyjqkJak">
          <el-input v-model="form.xjlyjqkJak" placeholder="请输入建安款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="其他款项" prop="xjlyjqkQtkx">
          <el-input v-model="form.xjlyjqkQtkx" placeholder="请输入其他款项" />
        </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="合计" prop="xjlyjqkhHj">
          <el-input v-model="form.xjlyjqkhHj" placeholder="请输入合计" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCash, getCash, delCash, addCash, updateCash } from "@/api/system/expectCash";

export default {
  //现金流预计
  name: "expectCash",
  data() {
    return {
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
      // 现金流预计情况表格数据
      cashList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xjlyjqkProjectId: null,
        xjlyjqkRq: null,
        xjlyjqkYjcszj: null,
        xjlyjqkYjecpcb: null,
        xjlyjqkYjxmgscb: null,
        xjlyjqkSke: null,
        xjlyjqkRzk: null,
        xjlyjqkGck: null,
        xjlyjqkDfsk: null,
        xjlyjqkHj: null,
        xjlyjqkSbk: null,
        xjlyjqkJak: null,
        xjlyjqkQtkx: null,
        xjlyjqkhHj: null,
        isDelete: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询现金流预计情况列表 */
    getList() {
      this.loading = true;
      listCash(this.queryParams).then(response => {
        this.cashList = response.rows;
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
        xjlyjqkId: null,
        xjlyjqkProjectId: null,
        xjlyjqkRq: null,
        xjlyjqkYjcszj: null,
        xjlyjqkYjecpcb: null,
        xjlyjqkYjxmgscb: null,
        xjlyjqkSke: null,
        xjlyjqkRzk: null,
        xjlyjqkGck: null,
        xjlyjqkDfsk: null,
        xjlyjqkHj: null,
        xjlyjqkSbk: null,
        xjlyjqkJak: null,
        xjlyjqkQtkx: null,
        xjlyjqkhHj: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        isDelete: null
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
      this.ids = selection.map(item => item.xjlyjqkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加现金流预计情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const xjlyjqkId = row.xjlyjqkId || this.ids
      getCash(xjlyjqkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改现金流预计情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.xjlyjqkId != null) {
            updateCash(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCash(this.form).then(response => {
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
      const xjlyjqkIds = row.xjlyjqkId || this.ids;
      this.$modal.confirm('是否确认删除现金流预计情况编号为"' + xjlyjqkIds + '"的数据项？').then(function() {
        return delCash(xjlyjqkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/expect/cash/export', {
        ...this.queryParams
      }, `cash_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
