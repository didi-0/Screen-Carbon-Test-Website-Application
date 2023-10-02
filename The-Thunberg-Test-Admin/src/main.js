import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./api/mock";
import Cookies from "js-cookie";

Vue.config.productionTip = false;

Vue.use(ElementUI);

router.beforeEach((to, from, next) => {
  const token = Cookies.get("token");
  if (!token && to.name !== "Login") {
    next({ name: "Login" });
  } else if (token && to.name === "Login") {
    next({ name: "Home" });
  } else {
    next();
  }
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
