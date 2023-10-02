<template>
  <div class="content">
    <el-card class="box-card">
      <div slot="header" class="clearfix d-flex justify-content-center">
        <h3>Login</h3>
      </div>
      <el-form action="##" :model="ruleForm" ref="ruleForm">
        <el-form-item
          class="input-text clearFix"
          prop="username"
          :rules="[{ required: true, message: 'cannot be null' }]"
        >
          <!-- <span></span> -->
          <el-input
            clearable
            v-model="ruleForm.username"
            type="text"
            placeholder="username"
          >
            ><i class="el-icon-user" slot="prepend"></i>
          </el-input>
        </el-form-item>
        <div class="input-text clearFix">
          <!-- <span class="pwd"></span> -->
          <el-form-item
            class="input-text clearFix"
            prop="password"
            :rules="rules.password"
          >
            <el-input
              show-password
              v-model="ruleForm.password"
              type="text"
              placeholder="password"
            >
              <i class="el-icon-lock" slot="prepend"></i>
            </el-input>
          </el-form-item>
        </div>
        <!-- <div class="setting clearFix mt-3">
            <span class="forget">forget password?</span>
          </div> -->
        <div class="d-flex justify-content-center mt-3">
          <button @click="userLogin('ruleForm')" class="btn-dark btn-sm">
            Login&nbsp;&nbsp;in
          </button>
        </div>
        <div class="d-flex justify-content-center mt-3">
          <router-link class="register" to="/signup">Register</router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>
      
  <script>
import { serverUserLogin } from "@/api/film";
import { serverQueryUserInfo } from "@/api/film";
import Cookie from "js-cookie";

export default {
  data() {
    return {
      token: null,
      ruleForm: {
        username: "",
        password: "",
      },
      rules: {
        password: [
          { required: true, message: "Please type password", trigger: "blur" },
          {
            trigger: "blur",
            validator: (rule, value, callback) => {
              var passwordreg =
                /(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,20}/;
              if (!passwordreg.test(value)) {
                callback(
                  new Error(
                    "Password must consist of a combination of digits, letters, and special characters, and be 8-20 characters in length."
                  )
                );
              } else {
                callback();
              }
            },
          },
        ],
      },
    };
  },
  methods: {
    userLogin(ruleForm) {
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          const requestData = {
            username: this.ruleForm.username,
            password: this.ruleForm.password,
          };
          serverUserLogin(requestData).then((res) => {
            this.token = res.data;
            serverQueryUserInfo(this.token).then((res) => {
              console.log(res);
              if (res.data === null) {
                alert("Wrong account!");
              } else {
                alert("Login Success!");
                Cookie.set("token", this.token);
                this.$router.push("/");
              }
            });
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>
  
  <style>
.content {
  position: relative;
  width: 100%;
  height: 75vh;
}

.box-card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>