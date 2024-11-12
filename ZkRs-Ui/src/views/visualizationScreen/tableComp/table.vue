<template>
<!--查看详情-->
  <div class="details">
    <el-dialog
      title="项目现状"
      :visible.sync="open"
      width="80%"
      :before-close="handleClose">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          prop="date"
          label="日期"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
         <el-pagination
           @size-change="handleSizeChange"
           @current-change="handleCurrentChange"
           :current-page="currentPage4"
           :page-sizes="[100, 200, 300, 400]"
           :page-size="100"
           layout="total, sizes, prev, pager, next, jumper"
           :total="400">
    </el-pagination>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "vue",
  props:{
    open:{
      type:Number,
      default:1
    }
  },
  data(){
    return {
      currentPage4: 4,
     tableData: [{
       date: '2016-05-02',
       name: '王小虎',
       address: '上海市普陀区金沙江路 1518 弄'
     }, {
       date: '2016-05-04',
       name: '王小虎',
       address: '上海市普陀区金沙江路 1517 弄'
     }, {
       date: '2016-05-01',
       name: '王小虎',
       address: '上海市普陀区金沙江路 1519 弄'
     }, {
       date: '2016-05-03',
       name: '王小虎',
       address: '上海市普陀区金沙江路 1516 弄'
     }]
    }
  },
  methods:{
    /**项目现状*/
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }

  }
}
</script>

<style scoped lang="">

</style>
