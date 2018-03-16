import cookieUtil from 'js-cookie';

const accessTokenKey = 'ATOMIC_ACCESS_TOKEN';
const refreshTokenKey = 'ATOMIC_REFRESH_TOKEN';

export function getToken () {
    return cookieUtil.get(accessTokenKey);
}

export function getRefreshToken () {
    return cookieUtil.get(refreshTokenKey);
}

export function setToken (token, refToken) {
    cookieUtil.set(accessTokenKey, token);
    cookieUtil.set(refreshTokenKey, refToken);
}

export function removeToken () {
    cookieUtil.remove(accessTokenKey);
    cookieUtil.remove(refreshTokenKey);
}
