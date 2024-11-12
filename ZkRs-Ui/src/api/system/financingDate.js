import request from '@/utils/request'

// 查询融资时间列表
export function listDate(query) {
  return request({
    url: '/system/date/list',
    method: 'get',
    params: query
  })
}

// 查询融资时间详细
export function getDate(id) {
  return request({
    url: '/system/date/' + id,
    method: 'get'
  })
}

// 新增融资时间
export function addDate(data) {
  return request({
    url: '/system/date',
    method: 'post',
    data: data
  })
}

// 修改融资时间
export function updateDate(data) {
  return request({
    url: '/system/date',
    method: 'put',
    data: data
  })
}

// 删除融资时间
export function delDate(id) {
  return request({
    url: '/system/date/' + id,
    method: 'delete'
  })
}


// 新增融资时间
export function addDateString(data) {
  return request({
    url: '/system/date/addList',
    method: 'post',
    data: data
  })
}
// 导入
export function importBox(data) {
  return request({
    url: '/system/date/importBox',
    method: 'post',
    data: data
  })
}
