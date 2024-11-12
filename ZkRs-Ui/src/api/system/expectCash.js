import request from '@/utils/request'

// 查询现金流预计情况列表
export function listCash(query) {
  return request({
    url: '/system/expect/cash/list',
    method: 'get',
    params: query
  })
}

// 查询现金流预计情况详细
export function getCash(xjlyjqkId) {
  return request({
    url: '/system/expect/cash/' + xjlyjqkId,
    method: 'get'
  })
}

// 新增现金流预计情况
export function addCash(data) {
  return request({
    url: '/system/expect/cash',
    method: 'post',
    data: data
  })
}

// 修改现金流预计情况
export function updateCash(data) {
  return request({
    url: '/system/expect/cash',
    method: 'put',
    data: data
  })
}

// 删除现金流预计情况
export function delCash(xjlyjqkId) {
  return request({
    url: '/system/expect/cash/' + xjlyjqkId,
    method: 'delete'
  })
}

// 预计导入
export function importBox(data) {
  return request({
    url: '/system/expect/cash/importBox',
    method: 'post',
    data: data
  })
}
  // 实际导入
  export function importSjBox(data) {
    return request({
      url: '/system/actual/cash/importBox',
      method: 'post',
      data: data
    })
  }
