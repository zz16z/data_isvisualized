import request from '@/utils/request'

// 查询电量统计列表
export function listStatistics(query) {
  return request({
    url: '/system/statistics/list',
    method: 'get',
    params: query
  })
}

// 查询电量统计详细
export function getStatistics(dltjId) {
  return request({
    url: '/system/statistics/' + dltjId,
    method: 'get'
  })
}

// 新增电量统计
export function addStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'post',
    data: data
  })
}

// 修改电量统计
export function updateStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'put',
    data: data
  })
}

// 删除电量统计
export function delStatistics(dltjId) {
  return request({
    url: '/system/statistics/' + dltjId,
    method: 'delete'
  })
}

// 导入
export function importBox(data) {
  return request({
    url: '/system/statistics/importBox',
    method: 'post',
    data: data
  })
}

