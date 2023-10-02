import Vue from "vue";
import App from "./App.vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import Vuex from "vuex";

Vue.use(Vuex);

axios.defaults.baseURL = "http://localhost:8088";
Vue.prototype.$http = axios;

Vue.config.productionTip = false;
Vue.use(ElementUI);

const store =  new Vuex.Store({
  state: {
    writerList: [],
    producerList: [],
    directorList: [],
    actorList: [],
  },
  mutations: {
    setWriterList(state, payload) {
      state.writerList = payload;
    },
    setProducerList(state, payload) {
      state.producerList = payload;
    },
    setDirectorList(state, payload) {
      state.directorList = payload;
    },
    setActorList(state, payload) {
      state.actorList = payload;
    },
  },
});

new Vue({
  el: "#app",
  router,
  store,
  render: (h) => h(App),
});
