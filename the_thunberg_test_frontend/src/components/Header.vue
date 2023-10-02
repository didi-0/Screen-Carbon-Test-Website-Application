<template>
  <div class="header">
    <nav class="navbar navbar-expand-lg bg-dark navbar-dark">
      <div class="container">
        <a class="navbar-brand" href="#">Screen Carbon Test</a>
        <div class="collapse navbar-collapse" id="navmenu">
          <div
            class="navbar-nav ms-auto justify-content-center align-items-center"
          >
            <form
              role="search"
              method="get"
              class="search-form d-flex ms justify-content-center align-items-center"
              action="#"
              style="height: 20px"
            >
              <input
                type="search"
                class="search-field"
                placeholder="Search …"
                value=""
                name="s"
                style="height: 20px"
              />
              <div class="dropdown">
                <button
                  class="btn btn-secondary btn-sm dropdown-toggle d-flex justify-content-center align-items-center"
                  type="button"
                  id="dropdownMenu2"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  style="height: 20px"
                >
                  MOVIES
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                  <li>
                    <button class="dropdown-item" type="button">
                      TV SHOWS
                    </button>
                  </li>
                  <li>
                    <button class="dropdown-item" type="button">
                      CAST AND CREW
                    </button>
                  </li>
                </ul>
              </div>
              <button
                type="submit"
                class="btn btn-light d-flex justify-content-center align-items-center"
                style="height: 20px"
              >
                <i class="bi bi-search"></i>
              </button>
            </form>
            <a class="nav-link active" href="/">HOME</a>
            <a class="nav-link" href="/movieSearch">MOVIES</a>
            <a class="nav-link" href="/tvSearch">TV SHOWS</a>
            <a class="nav-link" href="/questions">Q&A</a>
            <a class="nav-link" href="/artistSearch">CAST AND CREW</a>
            <a class="nav-link" href="/signup" v-if="!token">SIGN UP</a>
            <a class="nav-link" v-else>
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  <img
                    class="user"
                    src="../assets/logo.png"
                    alt=""
                    style="width: 40px; height: 40px; border-radius: 50%"
                  />
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>Profile</el-dropdown-item>
                  <el-dropdown-item command="logout">Exit</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </a>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { RouterLink } from "vue-router";
import Cookie from "js-cookie";

export default {
  name: "Header",
  components: {
    RouterLink,
  },
  data() {
    return {
      token: Cookie.get("token"),
    };
  },
  methods: {
    handleCommand(command) {
      if (command === "logout") {
        Cookie.remove("token");
        if (this.$route.path !== "/") {
          this.$router.push("/");
        }
      }
    },
  },
};
</script>