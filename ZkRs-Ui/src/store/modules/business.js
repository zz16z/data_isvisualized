

const state = {
  tempData:{fig:0 ,//0表示没有数据、1表示有数据
    data:{
      xmid:'',
      operation_status:'', //操作状态0增加、1编辑、2查看
      isFormShow: '' //控制显示状态的
    }
  }
}

const mutations = {


  DELETE_TEMP_DATA: (state) => {
    state.tempData.fig = 0
    state.tempData.data.xmid =''
    state.tempData.data.operation_status='' //操作状态0增加、1编辑、2查看
    state.tempData.data.isFormShow=''  //控制显示状态的
  },

  SET_TEMP_XMID: (state, data) => {
    state.tempData.fig=1
    state.tempData.data.xmid =data
  },
  SET_OPERATION_STATUS: (state, data) => {
    state.tempData.fig=1
    state.tempData.data.operation_status=''
  },
  SET_ISFORMSHOW: (state, data) => {
    state.tempData.fig=1
    state.tempData.data.isFormShow=''
  },
}

const actions = {


  deleteTempData({ commit }) {
    commit('DELETE_TEMP_DATA')
  },
  setXmid({ commit }, data) {
    commit('SET_TEMP_XMID',data)
  },
  setOperation_status({ commit }, data) {
    commit('SET_OPERATION_STATUS',data)
  },


  setIsFormShow({ commit }, data) {
    commit('SET_ISFORMSHOW',data)
  },


}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
