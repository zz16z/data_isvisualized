import request from '@/utils/request'

// 查询现金流实际情况列表
export function listCash(query) {
  return request({
    url: '/system/actual/cash/list',
    method: 'get',
    params: query
  })
}

// 查询现金流实际情况详细
export function getCash(xjlsjqkId) {
  return request({
    url: '/system/actual/cash/' + xjlsjqkId,
    method: 'get'
  })
}


// 根据关联id获取现金流实际情况详细信息
export function getCashGl(query) {
  return request({
    url: '/system/actual/cash/get_info_gl',
    method: 'get',
    params: query
  })
}




// 新增现金流实际情况
export function addCash(data) {
  return request({
    url: '/system/actual/cash',
    method: 'post',
    data: data
  })
}

// 修改现金流实际情况
export function updateCash(data) {
  return request({
    url: '/system/actual/cash',
    method: 'put',
    data: data
  })
}

// 删除现金流实际情况
export function delCash(xjlsjqkId) {
  return request({
    url: '/system/actual/cash/' + xjlsjqkId,
    method: 'delete'
  })
}
