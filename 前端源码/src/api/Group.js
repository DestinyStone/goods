import request from './common/Request';

/**
 * 分类接口
 */
export default class Group {
  // 新增
  static save(data) {
    return request({
      url: '/group/save',
      method: 'post',
      data: data,
    })
  }
  // 删除
  static delete(id) {
    return request({
      url: '/group/delete',
      method: 'get',
      params: {id}
    })
  }
  // 更新
  static update(id, data) {
    return request({
      url: `/group/update/${id}`,
      method: 'post',
      data: data,
    })
  }
  // 根据id查询数据
  static getById(id) {
    return request({
      url: `/group/id`,
      method: 'get',
      params: {id}
    })
  }
  // 返回所有数据
  static list(param) {
    return request({
      url: `/group/list`,
      method: 'get',
      params:{
        ...param,
      }
    })
  }
}
