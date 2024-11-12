import request from '@/utils/request'

// 查询项目情况列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

// 查询项目情况详细
export function getInfo(xmqkProjectId) {
  return request({
    url: '/system/info/' + xmqkProjectId,
    method: 'get'
  })
}

// 新增项目情况
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 修改项目情况
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除项目情况
export function delInfo(xmqkProjectId) {
  return request({
    url: '/system/info/' + xmqkProjectId,
    method: 'delete'
  })
}

//导出
export function importBox(data) {
  return request({
    url: '/system/info/importBox',
    method: 'post',
    data: data
  })
}


export function importBoxConstruction(data) {
  return request({
    url: '/system/info/importBoxConstruction',
    method: 'post',
    data: data
  })
}

export function importBoxManagement(data) {
  return request({
    url: '/system/info/importBoxManagement',
    method: 'post',
    data: data
  })
}

export function importBoxElectricity(data) {
  return request({
    url: '/system/info/importBoxElectricity',
    method: 'post',
    data: data
  })
}

export function importBoxFinancing(data) {
  return request({
    url: '/system/info/importBoxFinancing',
    method: 'post',
    data: data
  })
}

export function importBoxExpectedFinancing(data) {
  return request({
    url: '/system/info/importBoxExpectedFinancing',
    method: 'post',
    data: data
  })
}

export function importBoxExpectedTrueFinancing(data) {
  return request({
    url: '/system/info/importBoxExpectedTrueFinancing',
    method: 'post',
    data: data
  })
}

export function importProjectCash(data) {
  return request({
    url: '/system/info/importProjectCash',
    method: 'post',
    data: data
  })
}
export function importProjectCashIn(data) {
  return request({
    url: '/system/info/importProjectCashIn',
    method: 'post',
    data: data
  })
}

export function importRepayment(data) {
  return request({
    url: '/system/info/importRepayment',
    method: 'post',
    data: data
  })
}

//验证
export function check(data) {
  return request({
    url: '/system/info/check',
    method: 'post',
    data: data
  })
}


/*数据清空
 */
// 项目情况
export function getDataClearInfo() {
  return request({
    url: '/system/dataClear/dataClearInfo',
    method: 'get'
  })
}


//清除经营情况数据
export function getDataClearManagement() {
  return request({
    url: '/system/dataClear/dataClearManagement',
    method: 'get'
  })
}



//清除融资情况数据
export function getDataClearFinancing() {
  return request({
    url: '/system/dataClear/dataClearFinancing',
    method: 'get'
  })
}
//清除还款情况数据
export function getdataClearTepayment() {
  return request({
    url: '/system/dataClear/dataClearTepayment',
    method: 'get'
  })
}
//清除电量收支情况数据
export function getDataClearElectricity() {
  return request({
    url: '/system/dataClear/dataClearElectricity',
    method: 'get'
  })
}


//清除预计现金流出情况数据
export function getDataClearProjectCash() {
  return request({
    url: '/system/dataClear/dataClearProjectCash',
    method: 'get'
  })
}


//清除预计现金流入情况数据
export function getDataClearProjectCashIn() {
  return request({
    url: '/system/dataClear/dataClearProjectCashIn',
    method: 'get'
  })
}




