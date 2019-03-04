function fn() {
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);

  var config = {};
  config.baseUrl = 'http://localhost:8081/api';

  return config;
}
