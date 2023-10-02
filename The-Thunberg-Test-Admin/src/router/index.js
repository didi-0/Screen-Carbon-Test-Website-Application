import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import User from "../views/User.vue";
import Main from "../views/Main";
import Table from "../views/Table";
import Charts from "../views/Charts";
import Login from "../views/Login";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Main,
    redirect: "/Login",
    children: [
      { name: "Home", path: "Home", component: Home },
      { name: "User", path: "User", component: User },
      { name: "Table", path: "Table", component: Table },
      { name: "Charts", path: "Charts", component: Charts },
    ],
  },
  {
    name: "Login",
    path: "/Login",
    component: Login,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
