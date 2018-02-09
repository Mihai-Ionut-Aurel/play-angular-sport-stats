const PROXY_CONFIG = {
  "/api/*": {

    "target": "localhost:9000",
    "secure": false,
    "changeOrigin": true,
    "logLevel": "info"
  }
};

module.exports = PROXY_CONFIG;
