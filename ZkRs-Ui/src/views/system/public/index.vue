<template>

  <el-tabs v-model="activeName" @tab-click="handleClick"   class="xuanzq">
    <el-tab-pane label="项目情况" name="xmqk">
      <addProject :msg_xmid = fzj_value  :msg_status=operation_status  v-on:child_string_parent="child_string_parent"></addProject>
    </el-tab-pane>

    <el-tab-pane label="施工情况" name="sgqk">
      <construction :msg_xmid = fzj_value :msg_status=operation_status ></construction>
    </el-tab-pane>

    <el-tab-pane label="经营情况" name="xjl">
      <indexExpext :msg_xmid = fzj_value :msg_status=operation_status ></indexExpext>
    </el-tab-pane>

    <el-tab-pane label="项目融资" name="xmrz"    >
     <financing :msg_xmid = fzj_value :msg_status=operation_status   ></financing>
    </el-tab-pane>

    <el-tab-pane label="电量统计" name="dltj">
      <statistics  :msg_xmid = fzj_value :msg_status=operation_status   ></statistics>
    </el-tab-pane>

    <el-tab-pane label="预计现金流出" name="cashout">
      <cashoutflow  :msg_xmid = fzj_value :msg_status=operation_status   ></cashoutflow>
    </el-tab-pane>

    <el-tab-pane label="预计现金流入" name="cashin">
      <cashinflow  :msg_xmid = fzj_value :msg_status=operation_status   ></cashinflow>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
  import addProject  from "@/views/system/public/addProject";
  import addExpect  from "@/views/system/public/addExpect";
  import addActual  from "@/views/system/public/addActual";
  import construction  from "@/views/system/construction/index";
  import financing  from "@/views/system/financing/index";
  import statistics  from "@/views/system/statistics/index";
  import cashoutflow  from "@/views/system/cashoutflow/index";
  import cashinflow  from "@/views/system/cashinflow/index";
  import indexExpext  from "@/views/system/public/indexExpext";

  export default {
    props: {


    },
    components: {
      addProject,
      addExpect,
      addActual,
      financing,
      construction,
      statistics,
      indexExpext,
      cashoutflow,
      cashinflow
    },
    data() {
      return {
        fzj_value:'',
        operation_status:'',
        activeName: 'xmqk'
      };
    },
    // created() {
    //
    // },

    methods: {
      child_string_parent(id,status){
        //获取项目id
        this.fzj_value=id
        this.operation_status=status

      },
      handleClick(tab, event) {
        console.log(tab.index);
        console.log(tab, event);
      },

    }
  };
</script>

<style>
  .xuanzq {
    margin-left: 15px
  }


</style>
