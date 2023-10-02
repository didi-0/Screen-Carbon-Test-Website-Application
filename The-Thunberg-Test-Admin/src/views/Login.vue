<template>
  <el-form
    ref="myForm"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
  >
    <h3 class="login_title">System Login</h3>
    <el-form-item label="username" prop="username">
      <el-input
        v-model="form.username"
        placeholder="Please type your username"
      ></el-input>
    </el-form-item>
    <el-form-item label="password" prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="Please type your password"
      ></el-input>
    </el-form-item>
    <el-form-item class="btn-login">
      <el-button type="primary" @click="submit">Login</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import Cookie from "js-cookie";
import Mock from "mockjs";

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            trigger: "blur",
            message: "Please type your username",
          },
        ],
        password: [
          {
            required: true,
            trigger: "blur",
            message: "Please type your password",
          },
        ],
      },
    };
  },
  methods: {
    submit() {
      this.$refs.myForm.validate((valid) => {
        if (valid) {
          const token = Mock.Random.guid();
          Cookie.set("token", token);
          this.$router.push("/Home");
        } else {
          alert("Please complete your information");
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.login-container {
  width: 370px;
  border: 1px solid #eaeaea;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  box-sizing: border-box;
  .login_title {
    text-align: center;
    margin-bottom: 40px;
    color: #505458;
  }
  .el-input {
    width: 208px;
  }
  .btn-login {
    display: flex;
    justify-content: center;
  }
}
</style>