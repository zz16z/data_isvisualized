<template>
  <div class="app-container">

    <el-tabs v-model="activeName" @tab-click="handleClickAdd"   class="xuanzq">

      <el-tab-pane  label="预计现金流" name="yjxjl">
        <addExpect :msg_xmid_itemv = msg_xmid_item  :msg_operation_idv=msg_operation_id :msg_isFormShowv=msg_isFormShow    v-on:child_ysxjl_parent="child_ysxjl"  >

        </addExpect>
      </el-tab-pane>

      <el-tab-pane label="实际现金流" name="sjxjl"    >
        <addActual  :msg_xmid_itemv = msg_xmid_item   :msg_yjxjl_item=yjxjlId   :msg_isFormShowv=msg_isFormShow  ></addActual>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>

  import addExpect  from "@/views/system/public/addExpect";
  import addActual  from "@/views/system/public/addActual";

  export default {
    name: "indexXjglAdd",
    props: {
      //项目id
      msg_xmid_item:String,
      msg_isFormShow: Boolean,
      //预计与实际表关联id
      msg_yjxjl_item:String,
      //编辑查看数据id
      msg_operation_id:String
    },
    components: {

      addExpect,
      addActual
    },
    data() {
      return {
        fzj_value:'',
        activeName: 'yjxjl',
        yjxjlId:'',
        xjlsjqkProjectId:''
      };
    },
    // created() {
    //
    // },
    methods: {
      // child_string_parent(_row){
      //   this.fzj_value=_row
      //   console.log('_row=>0000',_row)
      // },
      handleClickAdd(tab, event) {
        console.log(tab.index);
        console.log(tab, event);
      },
      child_ysxjl(id){
        //获取项目id
        this.yjxjlId=id

      },
    }
  };
</script>

<style>
  .xuanzq {
    margin-left: 15px
  }


</style>
