export function getLogoSrcBySuffix(suffix) {
    if (suffix === 'black_g') {
        suffix = '';
    }
    if (suffix !== '') {
        suffix = ('_' + suffix);
    }
    return ['/static/logo/logo-full' + suffix + '.svg', '/static/logo/logo-mini' + suffix + '.svg']
};


