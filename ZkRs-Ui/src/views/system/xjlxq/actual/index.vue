<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目id" prop="xjlsjqkProjectId">
        <el-input
          v-model="queryParams.xjlsjqkProjectId"
          placeholder="请输入项目id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收款额" prop="xjlsjqkSke">
        <el-input
          v-model="queryParams.xjlsjqkSke"
          placeholder="请输入收款额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="融资款" prop="xjlsjqkRzk">
        <el-input
          v-model="queryParams.xjlsjqkRzk"
          placeholder="请输入融资款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯收费" prop="xjlsjqkDtsf">
        <el-input
          v-model="queryParams.xjlsjqkDtsf"
          placeholder="请输入电梯收费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="xjlsjqkHj">
        <el-input
          v-model="queryParams.xjlsjqkHj"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备款" prop="xjlsjqkSbk">
        <el-input
          v-model="queryParams.xjlsjqkSbk"
          placeholder="请输入设备款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建安款" prop="xjlsjqkJak">
        <el-input
          v-model="queryParams.xjlsjqkJak"
          placeholder="请输入建安款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="xjlsjqkYjHj">
        <el-input
          v-model="queryParams.xjlsjqkYjHj"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应付未收款" prop="xjlsjqkYfwsk">
        <el-input
          v-model="queryParams.xjlsjqkYfwsk"
          placeholder="请输入应付未收款"
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
      <el-table-column label="现金流情况id" align="center" prop="xjlsjqkId" />
      <el-table-column label="项目id" align="center" prop="xjlsjqkProjectId" />
      <el-table-column label="收款额" align="center" prop="xjlsjqkSke" />
      <el-table-column label="融资款" align="center" prop="xjlsjqkRzk" />
      <el-table-column label="电梯收费" align="center" prop="xjlsjqkDtsf" />
      <el-table-column label="合计" align="center" prop="xjlsjqkHj" />
      <el-table-column label="设备款" align="center" prop="xjlsjqkSbk" />
      <el-table-column label="建安款" align="center" prop="xjlsjqkJak" />
      <el-table-column label="合计" align="center" prop="xjlsjqkYjHj" />
      <el-table-column label="应付未收款" align="center" prop="xjlsjqkYfwsk" />
      <el-table-column label="是否删除：未删除 0、  删除 1" align="center" prop="isDelete" />
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

    <!-- 添加或修改现金流实际情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目id" prop="xjlsjqkProjectId" hidden="hidden">
          <el-input v-model="form.xjlsjqkProjectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-row>
          <el-col :span="8">
        <el-form-item label="收款额" prop="xjlsjqkSke">
          <el-input v-model="form.xjlsjqkSke" placeholder="请输入收款额" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="融资款" prop="xjlsjqkRzk">
          <el-input v-model="form.xjlsjqkRzk" placeholder="请输入融资款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="电梯收费" prop="xjlsjqkDtsf">
          <el-input v-model="form.xjlsjqkDtsf" placeholder="请输入电梯收费" />
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
        <el-form-item label="合计" prop="xjlsjqkHj">
          <el-input v-model="form.xjlsjqkHj" placeholder="请输入合计" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
        <el-form-item label="设备款" prop="xjlsjqkSbk">
          <el-input v-model="form.xjlsjqkSbk" placeholder="请输入设备款" />
        </el-form-item>
          </el-col>

          <el-col :span="8">
        <el-form-item label="建安款" prop="xjlsjqkJak">
          <el-input v-model="form.xjlsjqkJak" placeholder="请输入建安款" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="合计" prop="xjlsjqkYjHj">
          <el-input v-model="form.xjlsjqkYjHj" placeholder="请输入合计" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="应付未收款" prop="xjlsjqkYfwsk">
          <el-input v-model="form.xjlsjqkYfwsk" placeholder="请输入应付未收款" />
        </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCash, getCash, delCash, addCash, updateCash } from "@/api/system/actualCash";

export default {
  //现金流实际
  name: "Cash",
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
      // 现金流实际情况表格数据
      cashList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xjlsjqkProjectId: null,
        xjlsjqkSke: null,
        xjlsjqkRzk: null,
        xjlsjqkDtsf: null,
        xjlsjqkHj: null,
        xjlsjqkSbk: null,
        xjlsjqkJak: null,
        xjlsjqkYjHj: null,
        xjlsjqkYfwsk: null,
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
    /** 查询现金流实际情况列表 */
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
        xjlsjqkYjxjlId:null,
        xjlsjqkId: null,
        xjlsjqkProjectId: null,
        xjlsjqkSke: null,
        xjlsjqkRzk: null,
        xjlsjqkDtsf: null,
        xjlsjqkHj: null,
        xjlsjqkSbk: null,
        xjlsjqkJak: null,
        xjlsjqkYjHj: null,
        xjlsjqkYfwsk: null,
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
      this.ids = selection.map(item => item.xjlsjqkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加现金流实际情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const xjlsjqkId = row.xjlsjqkId || this.ids
      getCash(xjlsjqkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改现金流实际情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.xjlsjqkId != null) {
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
      const xjlsjqkIds = row.xjlsjqkId || this.ids;
      this.$modal.confirm('是否确认删除现金流实际情况编号为"' + xjlsjqkIds + '"的数据项？').then(function() {
        return delCash(xjlsjqkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/actual/cash/export', {
        ...this.queryParams
      }, `cash_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
