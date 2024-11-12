import request from '@/utils/request'

// 查询业务字典列表
export function listDictionary(query) {
  return request({
    url: '/system/dictionary/list',
    method: 'get',
    params: query
  })
}

// 查询业务字典详细
export function getDictionary(id) {
  return request({
    url: '/system/dictionary/' + id,
    method: 'get'
  })
}

// 新增业务字典
export function addDictionary(data) {
  return request({
    url: '/system/dictionary',
    method: 'post',
    data: data
  })
}

// 修改业务字典
export function updateDictionary(data) {
  return request({
    url: '/system/dictionary',
    method: 'put',
    data: data
  })
}

// 删除业务字典
export function delDictionary(id) {
  return request({
    url: '/system/dictionary/' + id,
    method: 'delete'
  })
}
