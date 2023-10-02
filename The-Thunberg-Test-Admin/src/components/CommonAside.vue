<template>
  <el-menu
    default-active="1-4-1"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    :collapse="isCollapse"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <h3 v-show="!isCollapse">Admin System</h3>
    <el-menu-item
      @click="clickMenu(item)"
      v-for="item in noChildren"
      :key="item.name"
      :index="item.name"
    >
      <i :class="item.icon"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>
  </el-menu>
</template>

  
<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu {
  height: 100vh;
  h3 {
    color: #fff;
    text-align: center;
    line-height: 48px;
    font-weight: 400px;
  }
}
</style>
  
<script>
export default {
  data() {
    return {
      menuData: [
        {
          path: "/Home",
          name: "Home",
          label: "Home",
          icon: "el-icon-s-home",
          url: "Home/Home",
        },
        {
          path: "/Table",
          name: "Table",
          label: "Table",
          icon: "el-icon-s-data",
          url: "Table/Table",
        },
        {
          path: "/Charts",
          name: "Charts",
          label: "Charts",
          icon: "el-icon-pie-chart",
          url: "Charts/Charts",
        },
        {
          path: "/User",
          name: "User",
          label: "User",
          icon: "el-icon-user",
          url: "UserManage/UserManage",
        },
      ],
    };
  },
  computed: {
    noChildren() {
      return this.menuData.filter((element) => !element.children);
    },
    isCollapse() {
      return this, this.$store.state.tab.isCollapse;
    },
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item) {
      if (
        this.$route.path !== item.path &&
        !(this.$route.path === "/Home" && item.path === "/")
      ) {
        this.$router.push(item.path);
      }
      this.$store.commit("selectMenu", item);
    },
  },
};
</script>

<style scoped>
.el-menu {
  border-right: none;
}
</style>