import request from '@/utils/request'

// 获取字典数据
export function getDictList(query) {
  return request({
    url: '/system/dictionary/list',
    method: 'get',
    params: query
  })
}



