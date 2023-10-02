import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "@/components/Layout";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Layout,
    children: [
      {
        path: "",
        component: () => import("@/components/Home"),
      },
      {
        path: "/film",
        component: () => import("@/views/movie/filmDetail"),
      },
      {
        path: "/Rate",
        component: () => import("@/views/Rate/Rate"),
        children: [
          {
            path: "",
            component: () => import("@/views/Rate/slider"),
          },
          {
            path: "Congratulations",
            component: () => import("@/views/Rate/Congratulations"),
          },
        ],
      },
      {
        path: "/questions",
        component: () => import("@/views/Questions/questions"),
      },
      {
        path: "/Artist",
        component: () => import("@/views/Artist/Artist"),
      },
      {
        path: "/movieSearch",
        component: () => import("@/views/movie/search/search"),
      },
      {
        path: "/tvSearch",
        component: () => import("@/views/tv/search/search"),
      },
      {
        path: "/artistSearch",
        component: () => import("@/views/Artist/search/search"),
      },
      {
        path: "/signup",
        component: () => import("@/views/sign/signup"),
      },
      {
        path: "/signin",
        component: () => import("@/views/sign/signin"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
