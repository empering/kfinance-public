module.exports =
    process.env.NODE_ENV === 'production' ? require('./vue-prod.config.js') : require('./vue-dev.config.js');