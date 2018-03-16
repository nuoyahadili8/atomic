import request from '../utils/request';

/**
 * 获取所有菜单
 * @returns {*}
 */
export function getAllMenu () {
    return request({
        url: '/uc/resource/all',
        method: 'get'
    });
}
