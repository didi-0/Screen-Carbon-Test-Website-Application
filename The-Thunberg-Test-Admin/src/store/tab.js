export default {
  state: {
    //fold and expand panels
    isCollapse: false,
    //breadcrumb
    tabsList: [
      {
        path: "/",
        name: "Home",
        label: "Home",
        icon: "el-icon-s-home",
        url: "Home/Home",
      },
    ],
  },
  mutations: {
    collapseMenu(state) {
      state.isCollapse = !state.isCollapse;
    },
    // update breadcrumb
    selectMenu(state, val) {
      if (val.name !== "Home") {
        const index = state.tabsList.findIndex(
          (item) => item.name === val.name
        );
        if (index === -1) {
          state.tabsList.push(val);
        }
      }
    },

    closeTag(state, item) {
      const index = state.tabsList.findIndex((val) => val.name === item.name);
      state.tabsList.splice(index, 1);
    },
  },
};
